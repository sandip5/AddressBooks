package com.bridgelab.addressbook.services;

import com.bridgelab.addressbook.model.Person;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

import static java.util.Comparator.*;

public class ImplAddressBook implements IAddressBook {
    private static int counter = 0;
    Scanner scanner = new Scanner(System.in);
    int indexOfPerson;
    static ArrayList<Person> persons = new ArrayList<>();

    public static void printPersonDetails(ArrayList<Person> persons) {
        if (counter > 0) {
            System.out.println("Printing all records...");
            persons.forEach(System.out::println);
        } else {
            System.out.println("There is no record to print...");
        }
    }

    @Override
    public void openAddressBook() {
        System.out.println("-----------------------Open Address Book-----------------------");
        AtomicBoolean close = new AtomicBoolean(false);
        while (!close.get()) {
            System.out.println(
                    "Select option: \n1.add\n2.print\n3.quit\n4.edit\n5.delete\n6.sort by name\n7.sort by city\n8.sort by state\n9.sort by zip\n10.search by city and state\n11.search by city or state");
            switch (scanner.nextInt()) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    printPersonDetails(persons);
                    break;
                case 3:
                    close.set(true);
                    break;
                case 4:
                    editPerson();
                    break;
                case 5:
                    deletePerson();
                    break;
                case 6:
                    sortByName();
                    break;
                case 7:
                    sortByCity();
                    break;
                case 8:
                    sortByState();
                    break;
                case 9:
                    sortByZip();
                    break;
                case 10:
                    searchByCityAndState();
                    break;
                case 11:
                    searchByCityOrState();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    /**
     * Method for adding person
     */
    @Override
    public void addPerson() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Add person details...");
        Person person = new Person();
        System.out.println("Enter person name: ");
        String personName = scan.nextLine();
        person.setName(personName);
        if (counter > 0) {
            for (Person value : persons) {
                if (personName.equals(value.getName())) {
                    System.out.println("Duplicate Entries Not Allowed, Try Again... ");
                    openAddressBook();
                }
            }
        }
        person.setName(personName);
        System.out.println("Enter Mobile Number: ");
        Long mobile = scanner.nextLong();
        person.setMobile(mobile);
        System.out.println("Enter city: ");
        String city = scanner.next();
        person.setCity(city);
        System.out.println("Enter state: ");
        String state = scanner.next();
        person.setState(state);
        System.out.println("Enter zip: ");
        int zip = scanner.nextInt();
        person.setZip(zip);
        persons.add(person);
        System.out.println();
        System.out.println("Person added");
        counter++;
    }

    @Override
    public void editPerson() {
        if (counter > 0) {
            System.out.println("Enter Persons First name you want to edit:");
            String searchFirstName = scanner.next();
            indexOfPerson = 0;
            boolean isFoundPerson = false;
            for (int i = 0; i < persons.size(); i++) {
                if (searchFirstName.equals(persons.get(i).getName())) {
                    isFoundPerson = true;
                    indexOfPerson = i;
                    break;
                }
            }
            if (isFoundPerson) {
                System.out.println("Select To Edit:\n 1. Mobile Number\n 2. City\n 3. State\n 4. Zip ");
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("enter new mobile number");
                        persons.get(indexOfPerson).setMobile(scanner.nextLong());
                        break;
                    case 2:
                        System.out.println("enter new city name");
                        persons.get(indexOfPerson).setCity(scanner.next());
                        break;
                    case 3:
                        System.out.println("enter new state name");
                        persons.get(indexOfPerson).setState(scanner.next());
                        break;
                    case 4:
                        System.out.println("enter new zip");
                        persons.get(indexOfPerson).setZip(scanner.nextInt());
                        break;
                    default:
                        System.out.println("Invalid Selection, Try Again...");
                        editPerson();
                }
                System.out.println();
                System.out.println("Edit completed");
            } else
                System.out.println("No person found with this First Name");
        } else
            System.out.println("There is no record to edit");
    }

    @Override
    public void deletePerson() {
        if (counter > 0) {
            System.out.println("Enter Persons Name you want to delete:");
            String searchFirstName = scanner.next();
            if (persons.removeIf(person -> person.getName().equals(searchFirstName))) {
                counter--;
            } else {
                System.out.println("No Matching Found, Try Again... :");
                deletePerson();
            }
        } else
            System.out.println("No records to delete");
    }

    @Override
    public void sortByName() {
        if (counter > 1) {
            System.out.println("Sorting by Last name is selected");
            persons.sort(comparing(Person::getName));
            System.out.println("Please wait...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sorting is completed to see the result select print option");
        } else
            System.out.println("Less records to sort");
    }

    //Method for sorting persons list according to city
    public void sortByCity() {
        if (counter > 1) {
            System.out.println("Sorting by City name is selected");
            persons.sort(comparing(Person::getCity));
            System.out.println("Please wait...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sorting is completed to see the result select print option");
        } else
            System.out.println("Less records to sort");
    }

    public void sortByState() {
        if (counter > 1) {
            System.out.println("Sorting by state name is selected");
            persons.sort(comparing(Person::getState));
            System.out.println("Please wait...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sorting is completed to see the result select print option");
        } else
            System.out.println("Less records to sort");
    }

    public void sortByZip() {
        if (counter > 1) {
            System.out.println("Sorting by zip");
            persons.sort(comparing(Person::getZip));
            System.out.println("Please wait...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sorting is completed to see the result select print option");
        } else
            System.out.println("Less records to sort");
    }

    public void searchByCityAndState() {
        if (counter > 0) {
            System.out.println("Enter city:");
            String searchCity = scanner.next();
            System.out.println("Enter state:");
            String searchState = scanner.next();
            int indexOfPerson;
            for (int i = 0; i < persons.size(); i++) {
                if (persons.get(i).getState().equals(searchState) && persons.get(i).getCity().equals(searchCity)) {
                    indexOfPerson = i;
                    System.out.println(persons.get(indexOfPerson).getName() + " " + persons.get(indexOfPerson)
                            + " " + persons.get(indexOfPerson).getCity() + " " + persons.get(indexOfPerson).getState() + " "
                            + persons.get(indexOfPerson).getZip() + " " + persons.get(indexOfPerson).getMobile());
                }
            }
        }
    }

    public void searchByCityOrState() {
        if (counter > 0) {
            System.out.println("Type:\n 1 -> Search By City\n 2 -> Search By State");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Enter city:");
                    iteratingCityOrState();
                    break;
                case 2:
                    System.out.println("Enter state:");
                    iteratingCityOrState();
                    break;
                default:
                    System.out.println("Invalid Selection, Try Again...");
                    searchByCityOrState();
            }
        }
    }

    private void iteratingCityOrState() {
        String elements = scanner.next();
        IntStream.range(0, persons.size()).filter(counters -> persons.get(counters).getState().equals(elements) || persons.get(counters).getCity().equals(elements)).forEach(counters -> {
            indexOfPerson = counters;
            System.out.println(persons.get(indexOfPerson).getName() + " " +
                    persons.get(indexOfPerson).getMobile() +
                    " " + persons.get(indexOfPerson).getCity() + " " + persons.get(indexOfPerson).getState() + " "
                    + persons.get(indexOfPerson).getZip() + " ");
        });
    }
}
