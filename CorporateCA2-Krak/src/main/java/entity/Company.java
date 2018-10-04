/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dto.AddressDTO;
import dto.CompanyDTO;
import dto.PhoneDTO;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Company extends InfoEntity {

    private String name;
    private String description;
    private int cvr;
    private int numEmployees;
    private int marketValue;

    public Company() {
    }

    public Company(CompanyDTO companyDTO) {
        this.name = companyDTO.name;
        this.email = companyDTO.email;
        this.description = companyDTO.description;
        this.cvr = companyDTO.cvr;
        this.numEmployees = companyDTO.numEmployees;
        this.marketValue = companyDTO.marketValue;
        this.id = companyDTO.id;
        for(PhoneDTO phone : companyDTO.phoneList){
            phones.add(new Phone(phone));
        }
        for (AddressDTO addressDTO : companyDTO.addressList) {
            addresses.add(new Address(addressDTO));
        }
    }
    
    public Company(String name, String description, int cvr, int marketValue, String email) {
        super(email);
        this.name = name;
        this.description = description;
        this.cvr = cvr;
        this.marketValue = marketValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCvr() {
        return cvr;
    }

    public void setCvr(int cvr) {
        this.cvr = cvr;
    }

    public int getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(int numEmployees) {
        this.numEmployees = numEmployees;
    }

    public int getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(int marketValue) {
        this.marketValue = marketValue;
    }

}
