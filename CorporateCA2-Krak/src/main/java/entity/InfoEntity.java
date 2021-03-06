/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
public class InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    
    @ManyToMany(mappedBy = "infoEntitys", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    protected List<Address> addresses = new ArrayList<>();
    
    
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "INFOENTITY_ID")
    protected List<Phone> phones = new ArrayList<>();
    @NotNull
    protected String email;

    public InfoEntity() {
    }

    public InfoEntity(List<Address> addresses, List<Phone> phones, String email) {
        this.addresses = addresses;
        this.phones = phones;
        this.email = email;
    }

    public InfoEntity(String email) {
        this.email = email;
    }

    public InfoEntity(Integer id, String email) {
        this.id = id;
        this.email = email;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Phone> getPhones() {
        return phones;
    }
    
}
