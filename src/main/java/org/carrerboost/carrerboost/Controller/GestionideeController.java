package org.carrerboost.carrerboost.Controller;

import org.carrerboost.carrerboost.entity.Gestionidee;
import org.carrerboost.carrerboost.serviceinterface.IGestionideeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GestionideeController {



        IGestionideeService igestionidee;
    @GetMapping("/getallide")
    public List<Gestionidee>   getallidee(){

        return igestionidee.getallidee();


    }
        @PostMapping("/createide")
        public Gestionidee createIdee(Gestionidee gestionidee) {
            return igestionidee.createIdee(gestionidee);
        }
        @GetMapping("/getidee/{id}")
        public Gestionidee  getideebyID( @PathVariable  ("id")Long id) {

            return igestionidee.getideeById(id);


        }
        @PutMapping("/updateidee")
        public void updateidee(Gestionidee gestionidee) {
            igestionidee.updateIdee(gestionidee);
        }
        @DeleteMapping("/deleteidee/{id}")
        public void deleteideeById(@PathVariable  ("id")Long id) {
            igestionidee.deleteideeById(id);
        }
    @GetMapping("/searchidee/{motcle}")
   public List<Gestionidee> searchIdeas(@PathVariable  ("motcle")String motcle){
        return igestionidee.searchIdeas(motcle);

}
    @GetMapping("/searchideebydescreption/{descreption}")
    public List<Gestionidee> findbydescreption(@PathVariable  ("descreption")String descreption){
        return igestionidee.findByDescriptionContainingIgnoreCase(descreption);

    }
    @GetMapping("/searchideebytitle/{title}")
    public List<Gestionidee> findbytitle(@PathVariable  ("title")String title){
        return igestionidee.findByTitleContainingIgnoreCase(title);

    }
}
