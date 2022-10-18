package com.example.solucionreto3.controller;


import com.example.solucionreto3.entities.Score;
import com.example.solucionreto3.entities.Skate;
import com.example.solucionreto3.services.ScoreService;
import com.example.solucionreto3.services.SkateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/Skate")
public class SkateController {

    @Autowired
    private SkateService skateService;

    @GetMapping("/all")
    public List<Skate> getAll(){
        return  skateService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Skate> getSkate (@PathVariable("id") int skateId){
        return  skateService.getSkate(skateId);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Skate save(@RequestBody Skate p) {
        return  skateService.save(p);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Skate update (@RequestBody Skate skate){
        return  skateService.update(skate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int carId){
        return  skateService.delete(carId);
    }
}
