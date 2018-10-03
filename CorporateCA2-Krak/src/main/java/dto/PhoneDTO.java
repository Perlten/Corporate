package dto;

import entity.Phone;


public class PhoneDTO {
    public int id;
    public int number;
    public String description;

    public PhoneDTO(Phone phone) {
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.description = phone.getDescription();
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }
    
    

}
