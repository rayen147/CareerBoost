package com.example.picloud.repository;

import com.example.picloud.entity.Event;
import com.example.picloud.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository extends JpaRepository<Event,Long> {

}
