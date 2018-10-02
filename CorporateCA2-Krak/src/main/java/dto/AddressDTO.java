package dto;

import entity.Address;

public class AddressDTO {

    //Address
    public String street, additionalInfo;
    //City
    public int zip;
    public String city;

    public AddressDTO(Address address) {
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        
        this.zip = address.getCityInfo().getZip();
        this.city = address.getCityInfo().getCity();
    }
    
}
