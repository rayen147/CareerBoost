package com.example.techcloud.serviceimpl;

import com.example.techcloud.entity.Evenement;
import com.example.techcloud.entity.Going;
import com.example.techcloud.repository.IGoingRepository;
import com.example.techcloud.serviceinterface.IGoingService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoingService implements IGoingService {
    private IGoingRepository goingRepository;
    @Override
    public void markAsGoing(Evenement event) {
        // Check if this user has already marked this event as going
        if (goingRepository.findAllByEvent(event) != null) {
            throw new IllegalArgumentException("");
        }
        Going going = new Going();
        going.setEvent(event);
        goingRepository.save(going);
    }

    @Override
    public List<Going> getGoingByEvent(Evenement event) {
        return goingRepository.findAllByEvent(event);
    }
}
