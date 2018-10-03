package dto;

import entity.Address;

public class AddressDTO {

    //Address
    private String street, city, additionalInfo;
    //City
    private int zip;

    public AddressDTO(Address address) {
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        
        this.zip = address.getCityInfo().getZip();
        this.city = address.getCityInfo().getCity();
    }
    
}
