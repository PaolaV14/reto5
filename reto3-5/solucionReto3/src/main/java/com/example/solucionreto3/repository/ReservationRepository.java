package com.example.solucionreto3.repository;

import com.example.solucionreto3.entities.Reservation;
import com.example.solucionreto3.repository.crudRepository.ReservationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;


    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id){
        return reservationCrudRepository.findById(id);
    }

    public Reservation save(Reservation c){
        return reservationCrudRepository.save(c);
    }

    public void delete(Reservation c){
        reservationCrudRepository.delete(c);
    }

    public List<Reservation> ReservacionStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }

    public List<Reservation> ReservacionTiempo(Date fechaInicial, Date fechaFinal){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(fechaInicial, fechaFinal);
    }


    public   List<Object[]> reporteClientes(String estadoCompletado) {
        return reservationCrudRepository.reporteClientes(estadoCompletado);

    }



}
