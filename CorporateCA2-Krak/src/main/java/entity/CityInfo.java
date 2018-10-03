/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dto.AddressDTO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Jesper
 */
@Entity
public class CityInfo implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private int zip;
    private String city;
    
    @OneToMany(mappedBy = "cityInfo")
    private List<Address> addresss;

    public CityInfo(){
        
    }
    
    public CityInfo(Integer id, int zip, String city) {
        this.id = id;
        this.zip = zip;
        this.city = city;
    }
    public CityInfo(AddressDTO addressDTO){
        this.zip = addressDTO.zip;
        this.city = addressDTO.city;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    
    
}
