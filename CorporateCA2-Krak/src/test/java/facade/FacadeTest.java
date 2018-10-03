/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dto.CompanyDTO;
import dto.HobbyDTO;
import dto.PersonDTO;
import entity.Company;
import entity.Hobby;
import entity.Person;
import exception.KrakException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Persistence;
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

    Facade facade;
    Person person1;

    public FacadeTest() {
        this.facade = new Facade(Persistence.createEntityManagerFactory("putest"));
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Persistence.generateSchema("putest", null);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getInformation method, of class Facade.
     */
    @Test
    public void testGetInformation() {
        int expected = 20681825;
        int actual = facade.getInformation(expected).getPhones().get(0).getNumber();
        assertEquals(expected, actual);
    }

    /**
     * Test of getContactInformation method, of class Facade.
     */
    @Test
    public void testGetContactInformation() {
        int expected = 20681825;
        int actual = facade.getContactInformation(expected).phoneDTOList.get(0).getNumber();
        assertEquals(expected, actual);
    }

    /**
     * Test of companyInformationOnPhone method, of class Facade.
     */
    @Test
    public void testCompanyInformationOnPhone() {
        int expected = 2;
        int phone = 32622145;
        int actual = facade.companyInformationOnPhone(phone).id;
        assertEquals(expected, actual);
    }

    /**
     * Test of companyInformationOnCVR method, of class Facade.
     */
    @Test
    public void testCompanyInformationOnCVR() {
        int expected = 2;
        int cvr = 39042110;
        int actual = facade.companyInformationOnCVR(cvr).id;
        assertEquals(expected, actual);
    }

    /**
     * Test of getPersonsByHobby method, of class Facade.
     */
    @Test
    public void testGetPersonsByHobby() {
        int expected = 1;
        String hobbyname = "Chess";
        int actual = facade.getPersonsByHobby(hobbyname).get(0).id;
        assertEquals(expected, actual);
    }

    /**
     * Test of getPersonsInCity method, of class Facade.
     */
    @Test
    public void testGetPersonsInCity() {
        int expected = 1;
        int zip = 2400;
        int actual = facade.getPersonsInCity(zip).get(0).id;
        assertEquals(expected, actual);
    }

    /**
     * Test of countOfPeopleByHobby method, of class Facade.
     */
    @Test
    public void testCountOfPeopleByHobby() {
        int expected = 1;

        String hobbyname = "Chess";
        int actual = facade.countOfPeopleByHobby(hobbyname);
        assertEquals(expected, actual);
    }

    /**
     * Test of listOfAllZipcodes method, of class Facade.
     */
    @Test
    public void testListOfAllZipcodes() {
        Integer[] array = {2400, 2900};
        List<Integer> expected = new ArrayList<>(Arrays.asList(array));
        List<Integer> actual = facade.listOfAllZipcodes();
        assertTrue(expected.equals(actual));
    }

    /**
     * Test of companyWithMoreThanXEmployees method, of class Facade.
     */
    @Test
    public void testCompanyWithMoreThanXEmployees() {
        List<CompanyDTO> actual1 = facade.companyWithMoreThanXEmployees(4);
        assertTrue(actual1.size() == 1);

        List<CompanyDTO> actual2 = facade.companyWithMoreThanXEmployees(5);
        assertTrue(actual2.isEmpty());
    }

    /**
     * Test of findPersonById method, of class Facade.
     */
    @Test
    public void testFindPersonById() {
        String expected1 = "Adam";
        String expected2 = "Lass";

        int personId = 1;
        Person object = facade.findPersonById(personId);
        String actual1 = object.getFirstname();
        String actual2 = object.getLastname();
    }
    
    @Test(expected = KrakException.class)
    public void testFindPersonByIdThrowsKrakException(){
        facade.findPersonById(2);
    }

    /**
     * Test of addPerson method, of class Facade.
     */
    @Test
    public void testAddPerson() {
        Person personCreate = new Person("Kurt", "Wonnegut", "kurt.wonne@gmail.com");
        PersonDTO actual = facade.addPerson(personCreate);
        
        assertEquals("Kurt", actual.firstname);
        assertEquals("Wonnegut", actual.lastname);
        assertEquals("kurt.wonne@gmail.com", actual.email);
    }

    /**
     * Test of editPerson method, of class Facade.
     */
    @Test
    public void testEditPerson() {
       String expected = "Lars";
       
       Person person1 = facade.findPersonById(1);
       person1.setLastname(expected);
       String actual = facade.editPerson(person1).getLastname();
       assertEquals(expected, actual);
       
    }

    /**
     * Test of deletePerson method, of class Facade.
     */
    @Test(expected = KrakException.class)
    public void testDeletePerson() {
        int id = 1;
        facade.deletePerson(id);
        facade.findPersonById(id);
    }

    /**
     * Test of findCompanyByID method, of class Facade.
     */
    @Test(expected = KrakException.class)
    public void testFindCompanyByID() {
        String expected = "Corporate";
        String actual = facade.findCompanyDTOByID(2).name;
        assertEquals(expected, actual);
        
        
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
