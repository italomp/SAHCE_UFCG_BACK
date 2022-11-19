package com.sahce.ufcg.repositories;

import com.sahce.ufcg.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query(value = "SELECT * FROM place p WHERE LOWER(p.name) = LOWER(:pname)", nativeQuery = true)
    Optional<Place> findByName(@Param("pname") String name);
}
