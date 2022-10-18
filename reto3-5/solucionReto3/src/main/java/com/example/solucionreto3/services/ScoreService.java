package com.example.solucionreto3.services;

import com.example.solucionreto3.entities.Score;
import com.example.solucionreto3.entities.Skate;
import com.example.solucionreto3.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll(){
        return scoreRepository.getAll();
    }

    public Optional<Score> getScore(int id){
        return scoreRepository.getScore(id);
    }

    public Score save(Score p){
        if(p.getIdScore()==null){
            return  scoreRepository.save(p);
        }else{
            Optional<Score> e = scoreRepository.getScore(p.getIdScore());
            if(e.isPresent()){
                return p;
            }else{
                return  scoreRepository.save(p);
            }
        }
    }

    public Score update(Score p) {
        if (p.getIdScore() != null) {
            Optional<Score> q = scoreRepository.getScore(p.getIdScore());
            if (!q.isEmpty()) {
                if (p.getScore()!= null) {
                    q.get().setScore(p.getScore());
                }
                return scoreRepository.save(q.get());
            }
        }
        return p;
    }

    public boolean delete(int id){
            boolean flag = false;
            Optional<Score> p = scoreRepository.getScore(id);
            if (p.isPresent()) {
                scoreRepository.delete(p.get());
                flag = true;
            }
            return flag;
        }

}
