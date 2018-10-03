/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dto.CompanyDTO;
import dto.HobbyDTO;
import dto.PersonContactDTO;
import dto.PersonDTO;
import entity.Company;
import entity.Hobby;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rasmus
 */
public class Facade implements FacadeInterface {
    EntityManagerFactory emf;

    public Facade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEm() {
        return emf.createEntityManager();
    }
//Edit constructor
    @Override
    public PersonDTO getInformation(int phonenumber) {
        EntityManager em = getEm();
        try {
//            TypedQuery<PersonDTO> tq = em.createQuery("select new dto.PersonDTO(p) From Person as p where p.id = (SELECT ph.infoEntity.id from Phone ph where ph.number = :phonenumber)", PersonDTO.class);
            TypedQuery<PersonDTO> tq = em.createQuery("select new dto.PersonDTO(p) From Person as p join p.phones ph where ph.number = :phonenumber", PersonDTO.class);
            tq.setParameter("phonenumber", phonenumber);
            return tq.getSingleResult();
        }
        finally {
            em.close();
        }
    }
    
    @Override
    public PersonContactDTO getContactInformation(int phonenumber) {
        EntityManager em = getEm();
        try {
//            TypedQuery<PersonContactDTO> tq = em.createQuery("select new dto.PersonContactDTO(p) From Person as p where p.id = (SELECT ph.infoEntity.id from Phone ph where ph.number = :phonenumber)", PersonContactDTO.class);
            TypedQuery<PersonContactDTO> tq = em.createQuery("select new dto.PersonContactDTO(p) From Person as p join p.phones ph where ph.number = :phonenumber", PersonContactDTO.class);
            tq.setParameter("phonenumber", phonenumber);
            return tq.getSingleResult();
        }
        finally {
            em.close();
        }
    }

    @Override
    public CompanyDTO companyInformationOnPhone(int phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyDTO companyInformationOnCVR(int cvr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PersonDTO> getPersonsByHobby(String hobby) {
        EntityManager em = getEm();
        try {
            TypedQuery<PersonDTO> tq = em.createQuery("select new dto.PersonDTO(p) From Person as p join p.hobbies h where h.name = :hobby", PersonDTO.class);
            tq.setParameter("hobby", hobby);
            return tq.getResultList();
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<PersonDTO> getPersonsInCity(int zipcode) {
        EntityManager em = getEm();
        try {
            TypedQuery<PersonDTO> tq = em.createQuery("select new dto.PersonDTO(p) From Person as p join p.addresses a where a.cityInfo.zip = :zipcode", PersonDTO.class);
            tq.setParameter("zipcode", zipcode);
            return tq.getResultList();
        }
        finally {
            em.close();
        }
    }

    @Override
    public int countOfPeopleByHobby(String hobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> listOfAllZipcodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CompanyDTO> companyWithMoreThanXEmployees(int employeeCount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person findPersonById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO addPerson(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO editPerson(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO deletePerson(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyDTO findCompanyDTOByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Company findCompanyByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyDTO addCompany(Company company) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyDTO editCompany(Company company) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CompanyDTO deleteCompany(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HobbyDTO findHobbyDTOByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Hobby findHobbyByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HobbyDTO addHobby(Hobby hobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HobbyDTO editHobby(Hobby hobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HobbyDTO deleteHobby(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO findPersonDTOById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
