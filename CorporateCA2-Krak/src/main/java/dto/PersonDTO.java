/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Address;
import entity.Hobby;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesper
 */
public class PersonDTO {
    public int id;
    public String firstname, lastname, email;
    public List<HobbyDTO> hobbies = new ArrayList();
    public List<AddressDTO> addresses = new ArrayList();;
    public List<PhoneDTO> phones = new ArrayList();;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.firstname = person.getFirstname();
        this.lastname = person.getLastname();
        this.email = person.getEmail();
        for (Hobby hobby : person.getHobbies()) {
            hobbies.add(new HobbyDTO(hobby));
        }
        for (Address address : person.getAddress()) {
            addresses.add(new AddressDTO(address));
        }
        for (Phone phone : person.getPhones()) {
            phones.add(new PhoneDTO(phone));
        }
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public List<HobbyDTO> getHobbies() {
        return hobbies;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

}
