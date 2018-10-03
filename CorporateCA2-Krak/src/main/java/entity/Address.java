/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dto.AddressDTO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jesper
 */
@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<InfoEntity> infoEntitys;
    @NotNull
    private String street;
    private String additionalInfo;

    
    @ManyToOne()
    @NotNull
    private CityInfo cityInfo;

    public Address() {
    }

    public Address(AddressDTO addressDTO) {
        this.street = addressDTO.street;
        this.additionalInfo = addressDTO.additionalInfo;
        this.id = addressDTO.id;
        this.cityInfo = new CityInfo(addressDTO);
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

}
