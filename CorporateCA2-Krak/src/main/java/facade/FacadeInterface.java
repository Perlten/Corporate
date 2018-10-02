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
    
    public CompanyDTO companyInformationOnPhone(int phone);

    public CompanyDTO companyInformationOnCVR(int cvr);
    
    // Hobbyname has to be unique
    public List<PersonDTO> getPersonsByHobby(String hobby);

    public List<PersonDTO> getPersonsInCity(int zipcode);

    public int countOfPeopleByHobby(String hobby);

    public List<Integer> listOfAllZipcodes();

    public List<CompanyDTO> companyWithMoreThanXEmployees(int employeeCount);
    
    //Person CRUD

    public PersonDTO findPersonById(int id);    

    public PersonDTO addPerson(Person person);

    public PersonDTO editPerson(Person person);

    public PersonDTO deletePerson(int id);
    
    //Company CRUD
    
    public CompanyDTO findCompanyByID(int id);
    
    public CompanyDTO addCompany(Company company);
    
    public CompanyDTO editCompany(Company company);
    
    public CompanyDTO deleteCompany(int id);
    
    //Hobby CRUD
    
    public HobbyDTO findHobbyByID(int id);
    
    public HobbyDTO addHobby(Hobby hobby);
    
    public HobbyDTO editHobby(Hobby hobby);
    
    public HobbyDTO deleteHobby(int id);

}
