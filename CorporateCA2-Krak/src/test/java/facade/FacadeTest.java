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
public class FacadeTest {
    
    public FacadeTest() {
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
     * Test of getInformation method, of class Facade.
     */
    @Test
    public void testGetInformation() {
        int phonenumber = 20681825;
        
    }

    /**
     * Test of getContactInformation method, of class Facade.
     */
    @Test
    public void testGetContactInformation() {
        System.out.println("getContactInformation");
        int phonenumber = 0;
        Facade instance = null;
        PersonContactDTO expResult = null;
        PersonContactDTO result = instance.getContactInformation(phonenumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of companyInformationOnPhone method, of class Facade.
     */
    @Test
    public void testCompanyInformationOnPhone() {
        System.out.println("companyInformationOnPhone");
        int phone = 0;
        Facade instance = null;
        CompanyDTO expResult = null;
        CompanyDTO result = instance.companyInformationOnPhone(phone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of companyInformationOnCVR method, of class Facade.
     */
    @Test
    public void testCompanyInformationOnCVR() {
        System.out.println("companyInformationOnCVR");
        int cvr = 0;
        Facade instance = null;
        CompanyDTO expResult = null;
        CompanyDTO result = instance.companyInformationOnCVR(cvr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonsByHobby method, of class Facade.
     */
    @Test
    public void testGetPersonsByHobby() {
        System.out.println("getPersonsByHobby");
        String hobby = "";
        Facade instance = null;
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getPersonsByHobby(hobby);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonsInCity method, of class Facade.
     */
    @Test
    public void testGetPersonsInCity() {
        System.out.println("getPersonsInCity");
        int zipcode = 0;
        Facade instance = null;
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getPersonsInCity(zipcode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countOfPeopleByHobby method, of class Facade.
     */
    @Test
    public void testCountOfPeopleByHobby() {
        System.out.println("countOfPeopleByHobby");
        String hobby = "";
        Facade instance = null;
        int expResult = 0;
        int result = instance.countOfPeopleByHobby(hobby);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listOfAllZipcodes method, of class Facade.
     */
    @Test
    public void testListOfAllZipcodes() {
        System.out.println("listOfAllZipcodes");
        Facade instance = null;
        List<Integer> expResult = null;
        List<Integer> result = instance.listOfAllZipcodes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of companyWithMoreThanXEmployees method, of class Facade.
     */
    @Test
    public void testCompanyWithMoreThanXEmployees() {
        System.out.println("companyWithMoreThanXEmployees");
        int employeeCount = 0;
        Facade instance = null;
        List<CompanyDTO> expResult = null;
        List<CompanyDTO> result = instance.companyWithMoreThanXEmployees(employeeCount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findPersonById method, of class Facade.
     */
    @Test
    public void testFindPersonById() {
        System.out.println("findPersonById");
        int id = 0;
        Facade instance = null;
        PersonDTO expResult = null;
        PersonDTO result = instance.findPersonById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPerson method, of class Facade.
     */
    @Test
    public void testAddPerson() {
        System.out.println("addPerson");
        Person person = null;
        Facade instance = null;
        PersonDTO expResult = null;
        PersonDTO result = instance.addPerson(person);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editPerson method, of class Facade.
     */
    @Test
    public void testEditPerson() {
        System.out.println("editPerson");
        Person person = null;
        Facade instance = null;
        PersonDTO expResult = null;
        PersonDTO result = instance.editPerson(person);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePerson method, of class Facade.
     */
    @Test
    public void testDeletePerson() {
        System.out.println("deletePerson");
        int id = 0;
        Facade instance = null;
        PersonDTO expResult = null;
        PersonDTO result = instance.deletePerson(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCompanyByID method, of class Facade.
     */
    @Test
    public void testFindCompanyByID() {
        System.out.println("findCompanyByID");
        int id = 0;
        Facade instance = null;
        CompanyDTO expResult = null;
        CompanyDTO result = instance.findCompanyByID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCompany method, of class Facade.
     */
    @Test
    public void testAddCompany() {
        System.out.println("addCompany");
        Company company = null;
        Facade instance = null;
        CompanyDTO expResult = null;
        CompanyDTO result = instance.addCompany(company);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editCompany method, of class Facade.
     */
    @Test
    public void testEditCompany() {
        System.out.println("editCompany");
        Company company = null;
        Facade instance = null;
        CompanyDTO expResult = null;
        CompanyDTO result = instance.editCompany(company);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCompany method, of class Facade.
     */
    @Test
    public void testDeleteCompany() {
        System.out.println("deleteCompany");
        int id = 0;
        Facade instance = null;
        CompanyDTO expResult = null;
        CompanyDTO result = instance.deleteCompany(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findHobbyByID method, of class Facade.
     */
    @Test
    public void testFindHobbyByID() {
        System.out.println("findHobbyByID");
        int id = 0;
        Facade instance = null;
        HobbyDTO expResult = null;
        HobbyDTO result = instance.findHobbyByID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addHobby method, of class Facade.
     */
    @Test
    public void testAddHobby() {
        System.out.println("addHobby");
        Hobby hobby = null;
        Facade instance = null;
        HobbyDTO expResult = null;
        HobbyDTO result = instance.addHobby(hobby);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editHobby method, of class Facade.
     */
    @Test
    public void testEditHobby() {
        System.out.println("editHobby");
        Hobby hobby = null;
        Facade instance = null;
        HobbyDTO expResult = null;
        HobbyDTO result = instance.editHobby(hobby);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteHobby method, of class Facade.
     */
    @Test
    public void testDeleteHobby() {
        System.out.println("deleteHobby");
        int id = 0;
        Facade instance = null;
        HobbyDTO expResult = null;
        HobbyDTO result = instance.deleteHobby(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllPersons method, of class Facade.
     */
    @Test
    public void testGetAllPersons() {
        System.out.println("getAllPersons");
        Facade instance = null;
        List<PersonDTO> expResult = null;
        List<PersonDTO> result = instance.getAllPersons();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
