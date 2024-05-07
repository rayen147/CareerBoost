package com.example.techcloud.serviceinterface;

import com.example.techcloud.entity.Evenement;
import com.example.techcloud.entity.Going;

import java.util.List;

public interface IGoingService {
    public void markAsGoing( Evenement event);
    public List<Going> getGoingByEvent(Evenement event);
}
