package com.client;

import java.util.ArrayList;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

/**
 * Hello world!
 *
 */
public class App {
    static Client client = ClientBuilder.newClient();

    public static void main(String[] args) {

        ArrayList<Person> resualt = new ArrayList<>();
        resualt = client.target("http://localhost:9090/rest/api/persons")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<ArrayList<Person>>() {
                });

        for (Person person : resualt) {
            System.out.println(person.getName() + "loop" + "\n");
        }

        //////////////////////////////////////////////////////////////////////////////

        ArrayList<Person> resualtPost = new ArrayList<>();
        Person p1 = new Person();
        p1.setName("ay7aga");
        p1.setAge(23);
        resualtPost = client.target("http://localhost:9090/rest/api/persons")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.entity(p1, MediaType.APPLICATION_JSON), new GenericType<ArrayList<Person>>() {
                });

        for (Person person1 : resualtPost) {
            System.out.println(person1.getName() + "loop2" + "\n");
        }
        
        //////////////////////////////////////////////////////////////////////////////////////////////
        
        ArrayList<Person> resultDelete = new ArrayList<>();
        resultDelete = client.target("http://localhost:9090/rest/api/persons").path("{name}").resolveTemplate("name","hassan").request().delete(new GenericType<ArrayList<Person>>(){});

        for (Person person1 : resultDelete) {
            System.out.println(person1.getName() + "loop3" + "\n");
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
       
        ArrayList<Person> resultPut = new ArrayList<>();
        resultPut = client.target("http://localhost:9090/rest/api/persons").path("{name}").resolveTemplate("name","hassan").request().put(Entity.entity("play", MediaType.TEXT_PLAIN),new GenericType<ArrayList<Person>>(){});
        for (Person person1 : resultPut) {
            System.out.println(person1.getName() + "loop4" + "\n");
        }


    }
}
