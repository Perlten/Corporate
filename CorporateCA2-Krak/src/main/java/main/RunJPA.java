/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import dto.PersonDTO;
import facade.Facade;
import javax.persistence.Persistence;

/**
 *
 * @author Jesper
 */
public class RunJPA {
    public static void main(String[] args) {
        Persistence.generateSchema("putest", null);
        
        Facade facade = new Facade(Persistence.createEntityManagerFactory("putest"));
        PersonDTO p = facade.getInformation(20681825);
        System.out.println(p);
        System.out.println(facade.getPersonsByHobby("Chess"));
        System.out.println(facade.getPersonsInCity(2400));
        System.out.println(facade.companyInformationOnPhone(32622145));
        System.out.println(facade.companyInformationOnCVR(39042110));
        System.out.println(facade.countOfPeopleByHobby("Chess"));
        System.out.println(facade.listOfAllZipcodes().size());
        System.out.println(facade.companyWithMoreThanXEmployees(4));
        System.out.println(facade.findPersonById(1).getFirstname());
    }
}