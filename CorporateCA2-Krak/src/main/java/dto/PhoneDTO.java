package dto;

import entity.Phone;


public class PhoneDTO {
    
    public int number;
    public String description;

    public PhoneDTO(Phone phone) {
        this.number = phone.getNumber();
        this.description = phone.getDescription();
    }

}
