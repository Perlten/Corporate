package facade;

import dto.CompanyDTO;
import dto.HobbyDTO;
import dto.PersonContactDTO;
import dto.PersonDTO;
import entity.Company;
import entity.Hobby;
import entity.Person;
import java.util.List;

public interface FacadeInterface {
    
    public List<PersonDTO> getAllPersons();

    public PersonDTO getInformation(int phonenumber);
    
    public PersonContactDTO getContactInformation(int phonenumber);
    
    public CompanyDTO companyInformationOnPhone(int phonenumber);

    public CompanyDTO companyInformationOnCVR(int cvr);

    // Hobbyname has to be unique
    public List<PersonDTO> getPersonsByHobby(String hobby);

    public List<PersonDTO> getPersonsInCity(int zip);

    public int countOfPeopleByHobby(String hobby);

    public List<Integer> listOfAllZipcodes();

    public List<CompanyDTO> companyWithMoreThanXEmployees(int employeeCount);

    //Person CRUD
    public Person findPersonById(int id) throws KrakException;
    
    public PersonDTO findPersonDTOById(int id) throws KrakException;

    public PersonDTO addPerson(Person person);

    public PersonDTO editPerson(Person person);

    public PersonDTO deletePerson(int id) throws KrakException;

    //Company CRUD
    public Company findCompanyByID(int id) throws KrakException;
    
    public CompanyDTO findCompanyDTOByID(int id) throws KrakException;

    public CompanyDTO addCompany(Company company);

    public CompanyDTO editCompany(Company company);

    public CompanyDTO deleteCompany(int id) throws KrakException;

    //Hobby CRUD
    public Hobby findHobbyByID(int id) throws KrakException;
    
    public HobbyDTO findHobbyDTOByID(int id) throws KrakException;

    public HobbyDTO addHobby(Hobby hobby);

    public HobbyDTO editHobby(Hobby hobby);

    public HobbyDTO deleteHobby(int id) throws KrakException;
}
