/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Address;
import entity.Company;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesper
 */
public class CompanyDTO {
    public int id;
    public String name;
    public String description;
    public int cvr;
    public int numEmployees;
    public int marketValue;
    public String email;
    public List<PhoneDTO> phoneList = new ArrayList<>();
    public List<AddressDTO> addressList = new ArrayList<>();

    public CompanyDTO(Company c) {
        this(c.getId(), c.getName(), c.getDescription(), c.getCvr(), c.getNumEmployees(), c.getMarketValue(), c.getEmail());
        for(Phone phone : c.getPhones()){
            phoneList.add(new PhoneDTO(phone));
        }
        for (Address addresse : c.getAddresses()) {
            addressList.add(new AddressDTO(addresse));
        }
    }

    public CompanyDTO(String name, String description) {
        this(-1, name, description, -1, -1, -1,null);
    }
    
    public CompanyDTO(int id, String name, String description, int cvr, int numEmployees, int marketValue, String email) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cvr = cvr;
        this.numEmployees = numEmployees;
        this.marketValue = marketValue;
        this.email = email;
    }
    
    public CompanyDTO(String name, String description, int cvr, int numEmployees, int marketValue, String email){
        this(-1, name, description, cvr, numEmployees, marketValue, email);
    }
    
}
