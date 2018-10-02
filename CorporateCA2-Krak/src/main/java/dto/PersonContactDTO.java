package dto;

import entity.Address;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;

public class PersonContactDTO {
    
    public String firstName, lastName;
    
    //Address
    public List<AddressDTO> addressDTOList = new ArrayList<>();
    //Phone
    public List<PhoneDTO> phoneDTOList = new ArrayList<>();
    
    public PersonContactDTO(Person person){
        this.firstName = person.getFirstname();
        this.lastName = person.getLastname();
        for(Address address : person.getAddress()){
            addressDTOList.add(new AddressDTO(address));
        }
        
        for(Phone phone : person.getPhones()){
            phoneDTOList.add(new PhoneDTO(phone));
        }
    }
}
