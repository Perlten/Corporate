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
    private String firstname, lastname, email;
    private List<HobbyDTO> hobbies = new ArrayList();
    private List<AddressDTO> addresses = new ArrayList();;
    private List<PhoneDTO> phones = new ArrayList();;

    public PersonDTO(Person person) {
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
    
    
}
