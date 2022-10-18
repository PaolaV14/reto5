package com.example.solucionreto3.repository.crudRepository;

import org.springframework.data.jpa.repository.Query;
import com.example.solucionreto3.entities.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.*;


public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {

    public List<Reservation> findAllByStatus(String status);


    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date fechaInicio, Date fechaFin);

     @Query("SELECT c.client, COUNT(c.client) from Reservation  AS  c group by c.client order by COUNT(c.client)DESC ")
    public List<Object[]> reporteClientes(String estadoCompletado);

}
