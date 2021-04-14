package com.example.restaurant.rest;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerRestFormIT {

    private HttpServer server;

    private WebTarget target;

    @Before
    public void setUp() {
        server = Main.startServer();
        target = ClientBuilder.newClient().target(Main.BASE_URI.resolve("customerform"));
    }

    @After
    public void tearDown() {
        server.shutdownNow();
    }

    /**
     * Initial Customer Size should be 0
     */
    @Test
    public void testAInitialCustomerSize() {
        List<Customer> customer = retrieveAllCustomers();
        assertEquals(customer.size(), 0);
    }

    /**
     * Adding 3 customers and checking if we get 200 response for each customer
     */
    @Test
    public void testBAdd3Customers() {
        // 2 TAs and 1 Professor walk into a bar to celebrate the end of COVID
        Response responseA = createCustomer("Nick", "24");
        Response responseB = createCustomer("Ali", "30");
        Response responseC = createCustomer("Hamed", "26");

        assertEquals(Status.OK.getStatusCode(), responseA.getStatus());
        assertEquals(Status.OK.getStatusCode(), responseB.getStatus());
        assertEquals(Status.OK.getStatusCode(), responseC.getStatus());
    }

    /**
     * Adding a Customer with invalid age and expecting response 400
     */
    @Test
    public void testCAddInvalidCustomer() {
        Response invalidCustomer = createCustomer("John", "AgeNotInteger");
        assertEquals(Status.BAD_REQUEST.getStatusCode(), invalidCustomer.getStatus());
    }

    /**
     * Checking that the total number of customers is still 3
     */
    @Test
    public void testDFinalCustomerSize() {
        List<Customer> customer = retrieveAllCustomers();
        assertEquals(customer.size(), 3);
    }

    /**
     * Method to retrieve all customers using api call
     * @return
     */
    public List<Customer> retrieveAllCustomers() {
        target = ClientBuilder.newClient().target(Main.BASE_URI.resolve("customerform"));
        List<Customer> customers =
                target.request(MediaType.APPLICATION_XML)
                        .get(new GenericType<List<Customer>>() {
                        });
        return customers;
    }

    /**
     * Method for creating a customer using the REST API
     * @param name of customer
     * @param age of customer
     */
    private Response createCustomer(String name, String age) {
        Form form = new Form();
        form.param("name", name);
        form.param("age", age);

        target = ClientBuilder.newClient().target(Main.BASE_URI.resolve("customerform"));
        Response response = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        System.out.println(response);

        return response;

    }

}
