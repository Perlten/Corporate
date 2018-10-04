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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

    private static Connection testConnection;
    private static final String USER = "root";
    private static final String USERPW = "toonage15";
    private static final String DBNAME = "CorporateCA2-Krak-Test";
    private static final String HOST = "localhost";

    @After
    public void setUp() {
        

        try {
            // avoid making a new connection for each test
            if (testConnection == null) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test 
                Connector.setConnection(testConnection);
            }

          
            // reset test database
            try (Statement stmt = testConnection.createStatement()) {
                
                stmt.execute("SET FOREIGN_KEY_CHECKS=0;");
                
                stmt.execute("drop table if exists HOBBY");
                stmt.execute("create table HOBBY like HOBBYTEST");
                stmt.execute("insert into HOBBY select * from HOBBYTEST");
                
                stmt.execute("drop table if exists PERSON");
                stmt.execute("create table PERSON like PERSONTEST");
                stmt.execute("insert into PERSON select * from PERSONTEST");
                
                 stmt.execute("drop table if exists PHONE");
                stmt.execute("create table PHONE like PHONETEST");
                 stmt.execute("insert into PHONE select * from PHONETEST");
                 
                 stmt.execute("drop table if exists INFOENTITY");
                stmt.execute("create table INFOENTITY like INFOENTITYTEST");
                stmt.execute("insert into INFOENTITY select * from INFOENTITYTEST");
       
                 stmt.execute("drop table if exists HOBBY_PERSON");
                stmt.execute("create table HOBBY_PERSON like HOBBY_PERSONTEST");
                stmt.execute("insert into HOBBY_PERSON select * from HOBBY_PERSONTEST");
                
                 stmt.execute("drop table if exists COMPANY");
                stmt.execute("create table COMPANY like COMPANYTEST");
                stmt.execute("insert into COMPANY select * from COMPANYTEST");
                
                 stmt.execute("drop table if exists CITYINFO");
                stmt.execute("create table CITYINFO like CITYINFOTEST");
                stmt.execute("insert into CITYINFO select * from CITYINFOTEST");
                
                 stmt.execute("drop table if exists ADDRESS");
                stmt.execute("create table ADDRESS like ADDRESSTEST");
                stmt.execute("insert into ADDRESS select * from ADDRESSTEST");
                
                 stmt.execute("drop table if exists ADDRESS_INFOENTITY");
                stmt.execute("create table ADDRESS_INFOENTITY like ADDRESS_INFOENTITYTEST");
                stmt.execute("insert into ADDRESS_INFOENTITY select * from ADDRESS_INFOENTITYTEST");
                stmt.execute("SET FOREIGN_KEY_CHECKS=1;");
             
            }
        } catch (SQLException | ClassNotFoundException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
            System.out.println(facade.getAllPersons().size());
    }

     @Test
    public void testSetUpOK() {
        assertNotNull(testConnection);
    }   

    /**
     * Test of getInformation method, of class Facade.
     */
    @Test
    public void testGetInformation() throws KrakException {
        int expected = 20681825;
        int actual = facade.getInformation(expected).getPhones().get(0).getNumber();
        assertEquals(expected, actual);
    }

    /**
     * Test of getContactInformation method, of class Facade.
     */
    @Test
    public void testGetContactInformation() throws KrakException{

        int expected = 20681825;
        int actual = facade.getContactInformation(expected).phoneDTOList.get(0).getNumber();
        assertEquals(expected, actual);
    }

    /**
     * Test of companyInformationOnPhone method, of class Facade.
     */
    @Test

   public void testCompanyInformationOnPhone() throws KrakException {
        int expected = 2;
        int phone = 32622145;
        int actual = facade.companyInformationOnPhone(phone).id;
        assertEquals(expected, actual);
    }

    /**
     * Test of companyInformationOnCVR method, of class Facade.
     */
    @Test

    public void testCompanyInformationOnCVR() throws KrakException {
        int expected = 2;
        int cvr = 39042110;
        int actual = facade.companyInformationOnCVR(cvr).id;
        assertEquals(expected, actual);
    }

    /**
     * Test of getPersonsByHobby method, of class Facade.
     */
    @Test
    public void testGetPersonsByHobby() throws KrakException {
        int expected = 1;
        String hobbyname = "Chess";
        int actual = facade.getPersonsByHobby(hobbyname).get(0).id;
        assertEquals(expected, actual);
    }

    /**
     * Test of getPersonsInCity method, of class Facade.
     */
    @Test

    public void testGetPersonsInCity() throws KrakException {
        int expected = 1;
        int zip = 2400;
        int actual = facade.getPersonsInCity(zip).get(0).id;
        assertEquals(expected, actual);
    }

    /**
     * Test of countOfPeopleByHobby method, of class Facade.
     */
    @Test
    public void testCountOfPeopleByHobby() throws KrakException {
        int expected = 1;

        String hobbyname = "Chess";
        int actual = facade.countOfPeopleByHobby(hobbyname);
        assertEquals(expected, actual);
    }

    /**
     * Test of listOfAllZipcodes method, of class Facade.
     */
    @Test
    public void testListOfAllZipcodes() throws KrakException{
        Integer[] array = {2400, 2900};
        List<Integer> expected = new ArrayList<>(Arrays.asList(array));
        List<Integer> actual = facade.listOfAllZipcodes();
        assertTrue(expected.equals(actual));
    }

    /**
     * Test of companyWithMoreThanXEmployees method, of class Facade.
     */
    @Test
    public void testCompanyWithMoreThanXEmployees() throws KrakException{

        List<CompanyDTO> actual1 = facade.companyWithMoreThanXEmployees(4);
        assertTrue(actual1.size() == 1);

        List<CompanyDTO> actual2 = facade.companyWithMoreThanXEmployees(5);
        assertTrue(actual2.isEmpty());
    }

    /**
     * Test of findPersonById method, of class Facade.
     */
    @Test
    public void testFindPersonById() throws KrakException {
        String expected1 = "Adam";
        String expected2 = "Lass";

        int personId = 1;
        Person object = facade.findPersonById(personId);
        String actual1 = object.getFirstname();
        String actual2 = object.getLastname();

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test(expected = KrakException.class)
    public void testFindPersonByIdThrowsKrakException() throws KrakException {
        facade.findPersonById(2);
    }

//    /**
//     * Test of addPerson method, of class Facade.
//     */
    @Test
    public void testAddPerson() throws KrakException {
        Person personCreate = new Person("Kurt", "Wonnegut", "kurt.wonne@gmail.com");
        PersonDTO actual = facade.addPerson(personCreate);

        assertEquals("Kurt", actual.firstname);
        assertEquals("Wonnegut", actual.lastname);
        assertEquals("kurt.wonne@gmail.com", actual.email);
    }
//
//    /**
//     * Test of editPerson method, of class Facade.
//     */
    @Test
    public void testEditPerson() throws KrakException {
        Person person1 = facade.findPersonById(1);
        person1.setLastname("Lars");
        facade.editPerson(person1);
        Person person2 = facade.findPersonById(1);
        String actual = person2.getLastname();
        assertEquals(person1.getLastname(), actual);

    }
//
    /**
     * Test of deletePerson method, of class Facade.
     */
    @Test(expected = KrakException.class)
    public void testDeletePerson() throws KrakException {
        int id = 1;
        facade.deletePerson(id);
        facade.findPersonById(id);
    }

    /**
     * Test of findCompanyByID method, of class Facade.
     */
    @Test
    public void testFindCompanyByID() throws KrakException {
        String expected = "Corporate";
        String actual = facade.findCompanyByID(2).getName();
        assertEquals(expected, actual);
    }

    @Test(expected = KrakException.class)
    public void testFindCompanyByIdThrowKrakException() throws KrakException {
        facade.findCompanyDTOByID(1);
    }

    /**
     * Test of addCompany method, of class Facade.
     */
    @Test
    public void testAddCompany() throws KrakException {
        Company comp1 = new Company("TestCorp", "This is a test corp", 23154124, 1000, "testcorp@gmail.com");
        CompanyDTO comp2 = facade.addCompany(comp1);
        assertEquals(comp1.getName(), comp2.name);
        assertEquals(comp1.getDescription(), comp2.description);
        assertEquals(comp1.getCvr(), comp2.cvr);
        assertEquals(comp1.getMarketValue(), comp2.marketValue);
        assertEquals(comp1.getEmail(), comp2.email);

    }

    /**
     * Test of editCompany method, of class Facade.
     */
    @Test
    public void testEditCompany() throws KrakException {
        Company company = facade.findCompanyByID(2);
        company.setCvr(42343);
        facade.editCompany(company);
        Company edited = facade.findCompanyByID(2);
        assertEquals(42343, edited.getCvr());

    }

    /**
     * Test of deleteCompany method, of class Facade.
     */
    @Test(expected = KrakException.class)
    public void testDeleteCompany() throws KrakException {
        int id = 1;
        facade.deleteCompany(id);
        facade.findCompanyByID(id);
    }

    /**
     * Test of findHobbyByID method, of class Facade.
     */
    @Test
    public void testFindHobbyByID() throws KrakException {
        String expected = "Chess";
        String actual = facade.findHobbyByID(1).getName();
        assertEquals(expected, actual);
    }

    /**
     * Test of addHobby method, of class Facade.
     */
    @Test
    public void testAddHobby() throws KrakException {
        Hobby hobby1 = new Hobby("Football", "Not American...");
        HobbyDTO hobby2 = facade.addHobby(hobby1);
        assertEquals(hobby1.getName(), hobby2.name);
    }

    /**
     * Test of findHobbyDTOByID method, of class Facade.
     */
    @Test
    public void testFindHobbyDTOByID() throws KrakException {
        String expected = "Chess";
        String actual = facade.findHobbyDTOByID(1).name;
        assertEquals(expected, actual);
    }
    
    /**
     * Test of editHobby method, of class Facade.
     */
    @Test
    public void testEditHobby() throws KrakException {
        Hobby hobby1 = facade.findHobbyByID(1);
        hobby1.setName("Hockey");
        HobbyDTO hobby2 = facade.editHobby(hobby1);
        assertEquals(hobby1.getName(), hobby2.name);
    }

    /**
     * Test of deleteHobby method, of class Facade.
     */
    @Test(expected = KrakException.class)
    public void testDeleteHobby() throws KrakException {
        int id = 1;
        facade.deleteHobby(id);
        facade.findHobbyByID(id);
    }

    /**
     * Test of getAllPersons method, of class Facade.
     */
    @Test
    public void testGetAllPersons() throws KrakException{
        int expected = 1;
        int actual = facade.getAllPersons().size();
        assertEquals(expected, actual);
    }

    /**
     * Test of findCompanyDTOByID method, of class Facade.
     */
    @Test
    public void testFindCompanyDTOByID() throws KrakException {
        String expected = "Corporate";
        String actual = facade.findCompanyDTOByID(2).name;
        assertEquals(expected, actual);
    }

    

    /**
     * Test of findPersonDTOById method, of class Facade.
     */
    @Test
    public void testFindPersonDTOById() throws KrakException {
        String expected1 = "Adam";
        String expected2 = "Lass";

        int personId = 1;
        PersonDTO object = facade.findPersonDTOById(personId);
        String actual1 = object.firstname;
        String actual2 = object.lastname;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

}
  /* HOW TO CREATE THE TEST TABLES
            stmt.execute("create table INFOENTITYTEST like INFOENTITY");
                stmt.execute("create table HOBBY_PERSONTEST like HOBBY_PERSON");
                stmt.execute("create table HOBBYTEST like HOBBY");
                stmt.execute("create table COMPANYTEST like COMPANY");
                stmt.execute("create table CITYINFOTEST like CITYINFO");
                stmt.execute("create table ADDRESS_INFOENTITYTEST like ADDRESS_INFOENTITY");
                stmt.execute("create table ADDRESSTEST like ADDRESS");
            */
            