package com.example.solucionreto3.services;

import com.example.solucionreto3.entities.Reservation;
import com.example.solucionreto3.entities.Skate;
import com.example.solucionreto3.repository.ReservationRepository;
import com.example.solucionreto3.repository.SkateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkateService {
    @Autowired
    private SkateRepository skateRepository;

    public List<Skate> getAll(){
        return skateRepository.getAll();
    }

    public Optional<Skate> getSkate(int id){
        return skateRepository.getSkate(id);
    }

    public Skate save(Skate p){
        if(p.getId()==null){
            return  skateRepository.save(p);
        }else{
            Optional<Skate> e = skateRepository.getSkate(p.getId());
            if(e.isPresent()){
                return p;
            }else{
                return  skateRepository.save(p);
            }
        }
    }

    public Skate update(Skate skate) {
        if (skate.getId() != null) {
            Optional<Skate> g = skateRepository.getSkate(skate.getId());
            if (!g.isEmpty()) {
                if (skate.getDescription() != null) {
                    g.get().setDescription(skate.getDescription());
                }
                if (skate.getName() != null) {
                    g.get().setName(skate.getName());
                }
                if (skate.getBrand() != null) {
                    g.get().setBrand(skate.getBrand());
                }
                if (skate.getYear() != null) {
                    g.get().setYear(skate.getYear());
                }

                return skateRepository.save(g.get());
            }
        }
        return skate;
    }

    public boolean delete(int id){
            boolean flag = false;
            Optional<Skate> p = skateRepository.getSkate(id);
            if (p.isPresent()) {
                skateRepository.delete(p.get());
                flag = true;
            }
            return flag;
        }

}
