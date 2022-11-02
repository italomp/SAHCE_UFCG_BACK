package com.sahce.ufcg.services;

import com.sahce.ufcg.models.MyUser;
import com.sahce.ufcg.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MyUserRepository repository;

    @Autowired
    public CustomUserDetailsService(MyUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUser user = repository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Não há usuário cadastrado com esse email"));
        List<GrantedAuthority> adminAuthoritiesList = getAuthList(user);
        return new User( user.getEmail(), user.getPassword(), adminAuthoritiesList);
    }

    public List<GrantedAuthority> getAuthList(MyUser user){
        List<GrantedAuthority> anonymousAuthoritiesList = new ArrayList<>();
        List<GrantedAuthority> userAuthoritiesList = AuthorityUtils.createAuthorityList("ROLE_USER");
        List<GrantedAuthority> adminAuthoritiesList = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");

        if(!user.getActive()) return anonymousAuthoritiesList;
        if(user.getUserType() == MyUser.UserType.ADMIN) return adminAuthoritiesList;
        return userAuthoritiesList;
    }
}
