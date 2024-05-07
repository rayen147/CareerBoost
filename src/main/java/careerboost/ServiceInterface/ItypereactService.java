package careerboost.ServiceInterface;


//import careerboost.entity.TypeReact;
import careerboost.Entity.TypeReact;
//import entity.TypeReact;


import java.util.List;

public interface ItypereactService {
    List<TypeReact> retrieveAllTypeReacts();
    TypeReact addTypeReact(TypeReact typeReact);
    TypeReact updateTypeReact (TypeReact typeReact);
    //TypeReact retrieveTypeReact ( Long id);
    //void removeTypeReact ( Long id);

}












