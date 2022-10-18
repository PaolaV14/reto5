package com.example.solucionreto3.services;

import com.example.solucionreto3.entities.Client;
import com.example.solucionreto3.entities.Reservation;
import com.example.solucionreto3.reports.ContadorClientes;
import com.example.solucionreto3.reports.StatusReservas;
import com.example.solucionreto3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation p) {
        if (p.getIdReservation() == null) {
            return reservationRepository.save(p);
        } else {
            Optional<Reservation> e = reservationRepository.getReservation(p.getIdReservation());
            if (e.isPresent()) {
                return p;
            } else {
                return reservationRepository.save(p);
            }
        }
    }

    public Reservation update(Reservation p) {
        if (p.getIdReservation() != null) {
            Optional<Reservation> q = reservationRepository.getReservation(p.getIdReservation());
            if (!q.isEmpty()) {
                if (p.getStartDate() != null) {
                    q.get().setStartDate(p.getStartDate());
                }
                if (p.getDevolutionDate() != null) {
                    q.get().setDevolutionDate(p.getDevolutionDate());
                }
                if (p.getStatus() != null) {
                    q.get().setStatus(p.getStatus());
                }
                return reservationRepository.save(q.get());
            }
        }
        return p;
    }

    public boolean delete(int id) {
        boolean flag = false;
        Optional<Reservation> p = reservationRepository.getReservation(id);
        if (p.isPresent()) {
            reservationRepository.delete(p.get());
            flag = true;
        }
        return flag;
    }

    public StatusReservas ReservacionStatus() {

        List<Reservation> completed = reservationRepository.ReservacionStatus("completed");
        List<Reservation> cancelled = reservationRepository.ReservacionStatus("cancelled");

        return new StatusReservas(completed.size(), cancelled.size());
    }

    public List<Reservation> ReservacionTiempo(String fechaInicial, String fechaFinal) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        Date fechaUno = new Date();
        Date fechaDos = new Date();

        try {
            fechaUno = parser.parse(fechaInicial);
            fechaDos = parser.parse(fechaFinal);
        } catch (ParseException evt) {
            evt.printStackTrace();
        }
        if (fechaUno.before(fechaDos)) {
            return reservationRepository.ReservacionTiempo(fechaUno, fechaDos);
        } else {
            return new ArrayList<>();
        }
    }


    public List<ContadorClientes> reporteClientes() {
        String estadoCompletado = "completed";
        List<ContadorClientes> resultado = new ArrayList<>();
        List<Object[]> reporte = reservationRepository.reporteClientes(estadoCompletado);
        System.out.println(reporte);
        for (int i = 0; i < reporte.size(); i++) {
            resultado.add(new ContadorClientes((Long) reporte.get(i)[1], (Client) reporte.get(i)[0]));
        }
        return resultado;
    }


}
