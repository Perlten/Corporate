package dto;

import entity.Address;

public class AddressDTO {
    
    

    //Address
    public String street, city, additionalInfo;
    //City
    public int id, zip, cityId;

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        
        this.zip = address.getCityInfo().getZip();
        this.city = address.getCityInfo().getCity();
        this.cityId = address.getCityInfo().getId();
        }

}
