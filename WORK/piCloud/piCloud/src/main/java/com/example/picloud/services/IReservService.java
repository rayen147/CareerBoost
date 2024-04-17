package com.example.picloud.services;

import com.example.picloud.entity.Reservation;

import java.util.List;

public interface IReservService {
    Reservation addReservation(Reservation reservation);
    List<Reservation> getAllReservation();
    Reservation getReservationById(long idReserv);
    void deleteReservation(long idReserv);
    Reservation updateReservation(Reservation reservation);
}
