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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adamlass
 */
public class FacadeInterfaceTest {
    
    public FacadeInterfaceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInformation method, of class FacadeInterface.
     */
    @Test
    public void testGetInformation() {
        System.out.println("getInformation");
        int phonenumber = 0;
        FacadeInterface instance = new FacadeInterfaceImpl();
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getInformation(phonenumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContactInformation method, of class FacadeInterface.
     */
    @Test
    public void testGetContactInformation() {
        System.out.println("getContactInformation");
        int phonenumber = 0;
        FacadeInterface instance = new FacadeInterfaceImpl();
        List<PersonContactDTO> expResult = null;
        List<PersonContactDTO> result = instance.getContactInformation(phonenumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of companyInformationOnPhone method, of class FacadeInterface.
     */
    @Test
    public void testCompanyInformationOnPhone() {
        System.out.println("companyInformationOnPhone");
        int phone = 0;
        FacadeInterface instance = new FacadeInterfaceImpl();
        CompanyDTO expResult = null;
        CompanyDTO result = instance.companyInformationOnPhone(phone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of companyInformationOnCVR method, of class FacadeInterface.
     */
    @Test
    public void testCompanyInformationOnCVR() {
        System.out.println("companyInformationOnCVR");
        int cvr = 0;
        FacadeInterface instance = new FacadeInterfaceImpl();
        CompanyDTO expResult = null;
        CompanyDTO result = instance.companyInformationOnCVR(cvr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonsByHobby method, of class FacadeInterface.
     */
    @Test
    public void testGetPersonsByHobby() {
        System.out.println("getPersonsByHobby");
        String hobby = "";
        FacadeInterface instance = new FacadeInterfaceImpl();
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getPersonsByHobby(hobby);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonsInCity method, of class FacadeInterface.
     */
    @Test
    public void testGetPersonsInCity() {
        System.out.println("getPersonsInCity");
        int zipcode = 0;
        FacadeInterface instance = new FacadeInterfaceImpl();
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getPersonsInCity(zipcode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countOfPeopleByHobby method, of class FacadeInterface.
     */
    @Test
    public void testCountOfPeopleByHobby() {
        System.out.println("countOfPeopleByHobby");
        String hobby = "";
        FacadeInterface instance = new FacadeInterfaceImpl();
        int expResult = 0;
        int result = instance.countOfPeopleByHobby(hobby);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listOfAllZipcodes method, of class FacadeInterface.
     */
    @Test
    public void testListOfAllZipcodes() {
        System.out.println("listOfAllZipcodes");
        FacadeInterface instance = new FacadeInterfaceImpl();
        List<Integer> expResult = null;
        List<Integer> result = instance.listOfAllZipcodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of companyWithMoreThanXEmployees method, of class FacadeInterface.
     */
    @Test
    public void testCompanyWithMoreThanXEmployees() {
        System.out.println("companyWithMoreThanXEmployees");
        int employeeCount = 0;
        FacadeInterface instance = new FacadeInterfaceImpl();
        List<CompanyDTO> expResult = null;
        List<CompanyDTO> result = instance.companyWithMoreThanXEmployees(employeeCount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findPersonById method, of class FacadeInterface.
     */
    @Test
    public void testFindPersonById() {
        System.out.println("findPersonById");
        int id = 0;
        FacadeInterface instance = new FacadeInterfaceImpl();
        PersonDTO expResult = null;
        PersonDTO result = instance.findPersonById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPerson method, of class FacadeInterface.
     */
    @Test
    public void testAddPerson() {
        System.out.println("addPerson");
        Person person = null;
        FacadeInterface instance = new FacadeInterfaceImpl();
        PersonDTO expResult = null;
        PersonDTO result = instance.addPerson(person);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editPerson method, of class FacadeInterface.
     */
    @Test
    public void testEditPerson() {
        System.out.println("editPerson");
        Person person = null;
        FacadeInterface instance = new FacadeInterfaceImpl();
        PersonDTO expResult = null;
        PersonDTO result = instance.editPerson(person);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePerson method, of class FacadeInterface.
     */
    @Test
    public void testDeletePerson() {
        System.out.println("deletePerson");
        int id = 0;
        FacadeInterface instance = new FacadeInterfaceImpl();
        PersonDTO expResult = null;
        PersonDTO result = instance.deletePerson(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCompanyByID method, of class FacadeInterface.
     */
    @Test
    public void testFindCompanyByID() {
        System.out.println("findCompanyByID");
        int id = 0;
        FacadeInterface instance = new FacadeInterfaceImpl();
        CompanyDTO expResult = null;
        CompanyDTO result = instance.findCompanyByID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCompany method, of class FacadeInterface.
     */
    @Test
    public void testAddCompany() {
        System.out.println("addCompany");
        Company company = null;
        FacadeInterface instance = new FacadeInterfaceImpl();
        CompanyDTO expResult = null;
        CompanyDTO result = instance.addCompany(company);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editCompany method, of class FacadeInterface.
     */
    @Test
    public void testEditCompany() {
        System.out.println("editCompany");
        Company company = null;
        FacadeInterface instance = new FacadeInterfaceImpl();
        CompanyDTO expResult = null;
        CompanyDTO result = instance.editCompany(company);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCompany method, of class FacadeInterface.
     */
    @Test
    public void testDeleteCompany() {
        System.out.println("deleteCompany");
        int id = 0;
        FacadeInterface instance = new FacadeInterfaceImpl();
        CompanyDTO expResult = null;
        CompanyDTO result = instance.deleteCompany(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findHobbyByID method, of class FacadeInterface.
     */
    @Test
    public void testFindHobbyByID() {
        System.out.println("findHobbyByID");
        int id = 0;
        FacadeInterface instance = new FacadeInterfaceImpl();
        HobbyDTO expResult = null;
        HobbyDTO result = instance.findHobbyByID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addHobby method, of class FacadeInterface.
     */
    @Test
    public void testAddHobby() {
        System.out.println("addHobby");
        Hobby hobby = null;
        FacadeInterface instance = new FacadeInterfaceImpl();
        HobbyDTO expResult = null;
        HobbyDTO result = instance.addHobby(hobby);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editHobby method, of class FacadeInterface.
     */
    @Test
    public void testEditHobby() {
        System.out.println("editHobby");
        Hobby hobby = null;
        FacadeInterface instance = new FacadeInterfaceImpl();
        HobbyDTO expResult = null;
        HobbyDTO result = instance.editHobby(hobby);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteHobby method, of class FacadeInterface.
     */
    @Test
    public void testDeleteHobby() {
        System.out.println("deleteHobby");
        int id = 0;
        FacadeInterface instance = new FacadeInterfaceImpl();
        HobbyDTO expResult = null;
        HobbyDTO result = instance.deleteHobby(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class FacadeInterfaceImpl implements FacadeInterface {

        public List<PersonDTO> getInformation(int phonenumber) {
            return null;
        }

        public List<PersonContactDTO> getContactInformation(int phonenumber) {
            return null;
        }

        public CompanyDTO companyInformationOnPhone(int phone) {
            return null;
        }

        public CompanyDTO companyInformationOnCVR(int cvr) {
            return null;
        }

        public List<PersonDTO> getPersonsByHobby(String hobby) {
            return null;
        }

        public List<PersonDTO> getPersonsInCity(int zipcode) {
            return null;
        }

        public int countOfPeopleByHobby(String hobby) {
            return 0;
        }

        public List<Integer> listOfAllZipcodes() {
            return null;
        }

        public List<CompanyDTO> companyWithMoreThanXEmployees(int employeeCount) {
            return null;
        }

        public PersonDTO findPersonById(int id) {
            return null;
        }

        public PersonDTO addPerson(Person person) {
            return null;
        }

        public PersonDTO editPerson(Person person) {
            return null;
        }

        public PersonDTO deletePerson(int id) {
            return null;
        }

        public CompanyDTO findCompanyByID(int id) {
            return null;
        }

        public CompanyDTO addCompany(Company company) {
            return null;
        }

        public CompanyDTO editCompany(Company company) {
            return null;
        }

        public CompanyDTO deleteCompany(int id) {
            return null;
        }

        public HobbyDTO findHobbyByID(int id) {
            return null;
        }

        public HobbyDTO addHobby(Hobby hobby) {
            return null;
        }

        public HobbyDTO editHobby(Hobby hobby) {
            return null;
        }

        public HobbyDTO deleteHobby(int id) {
            return null;
        }
    }
    
}
