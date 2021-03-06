/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dto.AddressDTO;
import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PhoneDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("P")
public class Person extends InfoEntity {

    
    @ManyToMany(mappedBy = "persons", cascade = CascadeType.MERGE)
    private List<Hobby> hobbies = new ArrayList<>();

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;

    public Person() {
    }

    public Person(PersonDTO personDTO) {
        super(personDTO.id, personDTO.email);
        this.firstname = personDTO.firstname;
        this.lastname = personDTO.lastname;
        
        if (personDTO.hobbies != null) {
            for (HobbyDTO hobby : personDTO.hobbies) {
                hobbies.add(new Hobby(hobby));
            }
        }
        if (personDTO.phones != null) {
            for (PhoneDTO phone : personDTO.phones) {
                phones.add(new Phone(phone));
            }
        }
        if (personDTO.addresses != null) {
            for (AddressDTO address : personDTO.addresses) {
                addresses.add(new Address(address));
            }
        }

    }

    public Person(String firstname, String lastname, String email) {
        super(email);
        this.firstname = firstname;
        this.lastname = lastname;
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
