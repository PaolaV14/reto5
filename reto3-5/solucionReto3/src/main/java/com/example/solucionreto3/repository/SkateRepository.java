package com.example.solucionreto3.repository;

import com.example.solucionreto3.entities.Reservation;
import com.example.solucionreto3.entities.Skate;
import com.example.solucionreto3.repository.crudRepository.ReservationCrudRepository;
import com.example.solucionreto3.repository.crudRepository.SkateCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SkateRepository {
    @Autowired
    private SkateCrudRepository skateCrudRepository;


    public List<Skate> getAll(){
        return (List<Skate>) skateCrudRepository.findAll();
    }

    public Optional<Skate> getSkate (int id){
        return skateCrudRepository.findById(id);
    }

    public Skate save(Skate c){
        return skateCrudRepository.save(c);
    }

    public void delete(Skate c){
        skateCrudRepository.delete(c);
    }
}
