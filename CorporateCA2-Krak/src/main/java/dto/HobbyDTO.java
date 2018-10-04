/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Hobby;
import entity.Person;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesper
 */
public class HobbyDTO {
    public int id;
    public String name, description;
    public List<Integer> personIds;
    

    public HobbyDTO(Hobby hobby) {
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.description = hobby.getDescription();
        this.personIds = new ArrayList<>();
        for (Person person : hobby.getPersons()) {
            personIds.add(person.getId());
        }
    }
}
