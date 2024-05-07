package com.example.techcloudpi.ServiceInterfaces;

import com.example.techcloudpi.entity.Reclamation;
import com.example.techcloudpi.entity.Reponse;

import java.util.List;

public interface Ireponse {
    List<Reponse> retrieveAllreponses();
    Reponse addReponse(Reponse reponse, Long idrec);
    Reponse updateReponse (Reponse reponse);
    Reponse retrieveReponse (Long idrep);
    void removeReponse (Long idrep);

}
