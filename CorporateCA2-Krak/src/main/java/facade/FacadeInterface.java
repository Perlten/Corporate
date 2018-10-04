package facade;

import dto.AddressDTO;
import dto.CompanyDTO;
import dto.HobbyDTO;
import dto.PersonContactDTO;
import dto.PersonDTO;
import entity.Company;
import entity.Hobby;
import entity.Person;
import exception.KrakException;
import java.util.List;

public interface FacadeInterface {

    public List<PersonDTO> getAllPersons() throws KrakException;

    public PersonDTO getInformation(int phonenumber) throws KrakException;

    public PersonContactDTO getContactInformation(int phonenumber) throws KrakException;
    
    public List<PersonDTO> getPersonDTOByFirstName(String firstName) throws KrakException;
    
    public List<PersonDTO> getPersonDTOByLastName(String lastName) throws KrakException;

    public CompanyDTO companyInformationOnPhone(int phonenumber) throws KrakException;

    public CompanyDTO companyInformationOnCVR(int cvr) throws KrakException;

    // Hobbyname has to be unique
    public List<PersonDTO> getPersonsByHobby(String hobby) throws KrakException;

    public List<PersonDTO> getPersonsInCity(int zip) throws KrakException;

    public int countOfPeopleByHobby(String hobby) throws KrakException;

    public List<Integer> listOfAllZipcodes() throws KrakException;

    public List<CompanyDTO> companyWithMoreThanXEmployees(int employeeCount) throws KrakException;

    //Person CRUD
    public Person findPersonById(int id) throws KrakException;

    public PersonDTO findPersonDTOById(int id) throws KrakException;

    public PersonDTO addPerson(Person person) throws KrakException;

    public PersonDTO editPerson(Person person) throws KrakException;

    public PersonDTO deletePerson(int id) throws KrakException;

    //Company CRUD
    public Company findCompanyByID(int id) throws KrakException;

    public CompanyDTO findCompanyDTOByID(int id) throws KrakException;
    
    public List<CompanyDTO> findCompanyDTOByName(String name) throws KrakException;

    public CompanyDTO addCompany(Company company) throws KrakException;

    public CompanyDTO editCompany(Company company) throws KrakException;

    public CompanyDTO deleteCompany(int id) throws KrakException;

    //Hobby CRUD
    public Hobby findHobbyByID(int id) throws KrakException;

    public HobbyDTO findHobbyDTOByID(int id) throws KrakException;

    public HobbyDTO addHobby(Hobby hobby) throws KrakException;

    public HobbyDTO editHobby(Hobby hobby) throws KrakException;

    public HobbyDTO deleteHobby(int id) throws KrakException;
    
    public AddressDTO findAddressByStreetName(String streetName) throws KrakException;
    
}
