package com.sahce.ufcg.services;

import com.sahce.ufcg.models.Place;
import com.sahce.ufcg.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
    @Autowired
    private PlaceRepository repository;

    public HttpStatus save(Place place) throws IllegalArgumentException{
        repository.save(place);
        return HttpStatus.OK;
    }

    public List<Place> getAll(){
        return repository.findAll();
    }
}
