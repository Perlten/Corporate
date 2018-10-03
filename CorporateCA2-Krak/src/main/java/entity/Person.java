/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dto.PersonDTO;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("P")
public class Person extends InfoEntity {

    @ManyToMany(mappedBy = "persons")
    private List<Hobby> hobbies;

    private String firstname;
    private String lastname;

    public Person() {
    }
    
    public Person(PersonDTO person) {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
    
    public void addHobbies(Hobby hobby) {
        this.hobbies.add(hobby);
    }
}
