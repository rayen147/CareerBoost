package org.carrerboost.carrerboost.serviceinterface;


import org.carrerboost.carrerboost.entity.Gestionidee;

import java.util.List;

public interface IGestionideeService {
    List<Gestionidee> getallidee();

    Gestionidee createIdee(Gestionidee gestionidee);
    Gestionidee getideeById(Long id);



    void updateIdee(Gestionidee gestionidee);

    void deleteideeById(Long id);
    List<Gestionidee> searchIdeas(String motcle);
    List<Gestionidee> findByDescriptionContainingIgnoreCase(String description);

    List<Gestionidee> findByTitleContainingIgnoreCase(String title);

}

