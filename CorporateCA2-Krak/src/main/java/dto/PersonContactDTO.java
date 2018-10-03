package dto;

import entity.Address;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;

public class PersonContactDTO {
    
    public int id;
    public String firstName, lastName, email;
    
    //Address
    public List<AddressDTO> addressDTOList = new ArrayList<>();
    //Phone
    public List<PhoneDTO> phoneDTOList = new ArrayList<>();
    
    public PersonContactDTO(Person person){
        this.id = person.getId();
        this.firstName = person.getFirstname();
        this.lastName = person.getLastname();
        this.email = person.getEmail();
        for(Address address : person.getAddresses()){
            addressDTOList.add(new AddressDTO(address));
        }
        
        for(Phone phone : person.getPhones()){
            phoneDTOList.add(new PhoneDTO(phone));
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<AddressDTO> getAddressDTOList() {
        return addressDTOList;
    }

    public List<PhoneDTO> getPhoneDTOList() {
        return phoneDTOList;
    }
}
