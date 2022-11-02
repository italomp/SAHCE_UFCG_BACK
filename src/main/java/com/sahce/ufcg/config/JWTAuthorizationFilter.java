package com.sahce.ufcg.config;

import com.sahce.ufcg.services.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sahce.ufcg.config.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    CustomUserDetailsService customUserDetailsService;
    public JWTAuthorizationFilter(AuthenticationManager authManager, CustomUserDetailsService customUserDetailsService) {
        super(authManager);
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        System.out.println("EXECUTANDO JWTAuthorizationFilter.doFilterInternal");
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        System.out.println("EXECUTANDO JWTAuthorizationFilter.getAuthentication");
        String token = request.getHeader(HEADER_STRING);
        if (token == null) return null;
        // parse the token.
        String userEmail = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        if (userEmail == null) return null;
        UserDetails user = customUserDetailsService.loadUserByUsername(userEmail);
        return new UsernamePasswordAuthenticationToken(userEmail, null, user.getAuthorities());
    }
}
