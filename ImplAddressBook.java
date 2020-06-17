package com.bridgelab.addressbook;

import java.util.ArrayList;
import java.util.Scanner;

public class ImplAddressBook implements IAddressBook {
    Scanner scanner = new Scanner(System.in);
    static ArrayList<Person> persons = new ArrayList<Person>();
    public void printPersonDetails(){
       persons.forEach(i -> {
           System.out.println(i.getFirstname() + " " + i.getLastname() + " " + i.getAddressObj().getAddressLocal()
                   + " " + i.getAddressObj().getCity() + " " + i.getAddressObj().getState() + " "
                   + i.getAddressObj().getZip() + " " + i.getMobile());
       });
    }

    @Override
    public void addPerson() {
        System.out.println("Add person details...");
        Person person = new Person();
        System.out.println("Enter person first name: ");
        person.setFirstname(scanner.next().toLowerCase());
        System.out.println("Enter person last name: ");
        person.setLastname(scanner.next().toLowerCase());
        System.out.println("Enter Mobile Number: ");
        person.setMobile(scanner.nextLong());
        System.out.println("Enter address Details: ");
        Address address = new Address();
        System.out.println("Enter address: ");
        address.setAddressLocal(scanner.next());
        System.out.println("Enter city: ");
        address.setCity(scanner.next());
        System.out.println("Enter state: ");
        address.setState(scanner.next());
        System.out.println("Enter zip: ");
        address.setZip(scanner.nextInt());
        person.setAddressObj(address);
        persons.add(person);
        System.out.println();
        System.out.println("Person added");
    }
}
