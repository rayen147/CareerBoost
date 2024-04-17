package com.example.picloud.services;
import com.example.picloud.entity.Event;
import com.example.picloud.entity.Reservation;
import com.example.picloud.repository.IEventRepository;
import com.example.picloud.repository.IReservRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservService implements IReservService{
IReservRepository IReservRepository;
    @Override
    public Reservation addReservation(Reservation reservation) {
        return IReservRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservation() {
        return IReservRepository.findAll();
    }

    @Override
    public Reservation getReservationById(long idReserv) {
        return IReservRepository.findById(idReserv).get();
    }

    @Override
    public void deleteReservation(long idReserv) {
        IReservRepository.deleteById(idReserv);

    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return IReservRepository.save(reservation);
    }
}
