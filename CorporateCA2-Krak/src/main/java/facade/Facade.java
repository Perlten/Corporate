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
import exception.KrakException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
    public CompanyDTO companyInformationOnPhone(int phonenumber) {
        EntityManager em = getEm();
        try {
            TypedQuery<CompanyDTO> tq = em.createQuery("select new dto.CompanyDTO(c) From Company as c join c.phones ph where ph.number = :phonenumber", CompanyDTO.class);
            tq.setParameter("phonenumber", phonenumber);
            return tq.getSingleResult();
        }
        finally {
            em.close();
        }
    }

    @Override
    public CompanyDTO companyInformationOnCVR(int cvr) {
        EntityManager em = getEm();
        try {
            TypedQuery<CompanyDTO> tq = em.createQuery("select new dto.CompanyDTO(c) From Company as c where c.cvr = :cvr", CompanyDTO.class);
            tq.setParameter("cvr", cvr);
            return tq.getSingleResult();
        }
        finally {
            em.close();
        }
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
        EntityManager em = getEm();
        try {
            TypedQuery<Integer> tq = em.createQuery("select size(h.persons) from Hobby h where h.name = :hobby", Integer.class);
            tq.setParameter("hobby", hobby);
            return tq.getSingleResult();
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<Integer> listOfAllZipcodes() {
        EntityManager em = getEm();
        try {
            TypedQuery<Integer> tq = em.createQuery("select c.zip FROM CityInfo c", Integer.class);
            return tq.getResultList();
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<CompanyDTO> companyWithMoreThanXEmployees(int employeeCount) {
        EntityManager em = getEm();
        try {
            TypedQuery<CompanyDTO> tq = em.createQuery("select new dto.CompanyDTO(c) FROM Company as c where c.numEmployees = :employeeCount", CompanyDTO.class);
            tq.setParameter("employeeCount", employeeCount);
            return tq.getResultList();
        }
        finally {
            em.close();
        }
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        EntityManager em = getEm();
        try {
            TypedQuery<PersonDTO> tq = em.createQuery("SELECT new dto.PersonDTO(p) from Person p", PersonDTO.class);
            return tq.getResultList();
        }
        finally {
            em.close();
        }
    }

    @Override
    public PersonDTO findPersonDTOById(int id) throws KrakException {
        EntityManager em = getEm();
        Person p = null;
        try {
            em.getTransaction().begin();
            p = em.find(Person.class, id);
            if (p == null) {
                throw new KrakException("Could not find that person", 404);
            }
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return new PersonDTO(p);
    }

    @Override
    public Person findPersonById(int id) throws KrakException {
        EntityManager em = getEm();
        Person res = null;
        try {
            em.getTransaction().begin();
            res = em.find(Person.class, id);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        if (res == null) {
            throw new KrakException("The person was not found!", id);
        }
        return res;
    }

    @Override
    public PersonDTO addPerson(Person person) {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    @Override
    public PersonDTO editPerson(Person person) {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    @Override
    public PersonDTO deletePerson(int id) throws KrakException {
        EntityManager em = getEm();
        Person person = null;
        try {
            em.getTransaction().begin();
            person = em.find(Person.class, id);
            if (person == null) {
                throw new KrakException("Person does not exist!", id);
            }
            em.remove(person);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    @Override
    public CompanyDTO findCompanyDTOByID(int id) throws KrakException {
        EntityManager em = getEm();
        CompanyDTO res = null;
        try {
            em.getTransaction().begin();
            res = em.find(CompanyDTO.class, id);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        if (res == null) {
            throw new KrakException("The company was not found!", id);
        }
        return res;
    }

    @Override
    public Company findCompanyByID(int id) throws KrakException {
        EntityManager em = getEm();
        Company res = null;
        try {
            em.getTransaction().begin();
            res = em.find(Company.class, id);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        if (res == null) {
            throw new KrakException("The company was not found!", id);
        }
        return res;
    }

    @Override
    public CompanyDTO addCompany(Company company) {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return new CompanyDTO(company);
    }

    @Override
    public CompanyDTO editCompany(Company company) {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            em.merge(company);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return new CompanyDTO(company);
    }

    @Override
    public CompanyDTO deleteCompany(int id) throws KrakException {
        EntityManager em = getEm();
        Company company = null;
        try {
            em.getTransaction().begin();
            company = em.find(Company.class, id);
            if (company == null) {
                throw new KrakException("The company does not exist", id);
            }
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return new CompanyDTO(company);
    }

    @Override
    public HobbyDTO findHobbyDTOByID(int id) throws KrakException {
        EntityManager em = getEm();
        Hobby res = null;
        try {
            em.getTransaction().begin();
            res = em.find(Hobby.class, id);
            if (res == null) {
                throw new KrakException("Hobby not found", id);
            }
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return new HobbyDTO(res);
    }

    @Override
    public Hobby findHobbyByID(int id) throws KrakException {
        EntityManager em = getEm();
        Hobby res = null;
        try {
            em.getTransaction().begin();
            res = em.find(Hobby.class, id);
            if (res == null) {
                throw new KrakException("Hobby not found", id);
            }
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return res;
    }

    @Override
    public HobbyDTO addHobby(Hobby hobby) {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return new HobbyDTO(hobby);
    }

    @Override
    public HobbyDTO editHobby(Hobby hobby) {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            em.merge(hobby);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return new HobbyDTO(hobby);

    }

    @Override
    public HobbyDTO deleteHobby(int id) throws KrakException {
        EntityManager em = getEm();
        Hobby res = null;
        try {
            em.getTransaction().begin();
            res = em.find(Hobby.class, id);
            if (res == null) {
                throw new KrakException("Could not find Hobby", id);
            }
            em.remove(res);
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }
        return new HobbyDTO(res);

    }

}
