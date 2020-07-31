package com.bridgelab.addressbook.services;

import com.bridgelab.addressbook.model.Person;
import com.bridgelab.addressbook.utility.IoOperation;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;

public class ImplAddressBook implements IAddressBook {
    private ArrayList<Person> persons;
    Scanner scanner = new Scanner(System.in);
    int indexOfPerson;

    public void setList(ArrayList<Person> addressBookList) {
        this.persons = new ArrayList<>(addressBookList);
    }

    public boolean patternValidation(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(value).matches())
            return false;
        else {
            System.out.println("Enter Valid Input...");
            return true;
        }
    }

    public void printPersonDetails() {
        persons.forEach(System.out::println);
    }

    /**
     * Method for adding person
     */
    @Override
    public void addPerson() {
        Scanner scan = new Scanner(System.in);
        Person person = new Person();
        System.out.println("Enter person name: ");
        String personName = scan.nextLine();
        if (patternValidation(personName, person.getNAME_PATTERN())) {
            addPerson();
        }
        for (Person value : persons) {
            if (personName.equals(value.getName())) {
                System.out.println("Duplicate Entries Not Allowed, Try Again... ");
                addPerson();
            }
        }
        person.setName(personName);
        System.out.println("Enter Mobile Number: ");
        Long mobile = scanner.nextLong();
        if (patternValidation(String.valueOf(mobile), person.getPHONE_NUMBER_PATTERN())) {
            addPerson();
        }
        person.setMobile(mobile);
        System.out.println("Enter city: ");
        String city = scanner.next();
        if (patternValidation(city, person.getCOMMON_PATTERN())) {
            addPerson();
        }
        person.setCity(city);
        System.out.println("Enter state: ");
        String state = scanner.next();
        if (patternValidation(state, person.getCOMMON_PATTERN())) {
            addPerson();
        }
        person.setState(state);
        System.out.println("Enter zip: ");
        int zip = scanner.nextInt();
        if (patternValidation(String.valueOf(zip), person.getZIP_PATTERN())) {
            addPerson();
        }
        person.setZip(zip);
        persons.add(person);
        System.out.println();
        System.out.println("Person added");
    }

    @Override
    public void editPerson() {
        System.out.println("Enter Persons Person Name you want to edit:");
        String searchFirstName = scanner.nextLine();
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
    }

    @Override
    public void deletePerson() {
        System.out.println("Enter Persons Name you want to delete:");
        String searchFirstName = scanner.nextLine();
        persons.removeIf(person -> person.getName().equals(searchFirstName));
    }

    @Override
    public void sortByName() {
        System.out.println("Sorting by Last name is selected");
        persons.sort(comparing(Person::getName));
        System.out.println("Please wait...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sorting is completed to see the result select print option");
    }

    //Method for sorting persons list according to city
    public void sortByCity() {
        System.out.println("Sorting by City name is selected");
        persons.sort(comparing(Person::getCity));
        System.out.println("Please wait...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sorting is completed to see the result select print option");
    }

    public void sortByState() {
        System.out.println("Sorting by state name is selected");
        persons.sort(comparing(Person::getState));
        System.out.println("Please wait...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sorting is completed to see the result select print option");
    }

    public void sortByZip() {
        System.out.println("Sorting by zip");
        persons.sort(comparing(Person::getZip));
        System.out.println("Please wait...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sorting is completed to see the result select print option");
    }

    public void searchByCityAndState() {
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

    public void searchByCityOrState() {
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

    private void iteratingCityOrState() {
        String elements = scanner.next();
        IntStream.range(0, persons.size())
                .filter(counters -> persons.get(counters).getState()
                        .equals(elements) || persons.get(counters)
                        .getCity().equals(elements)).forEach(counters -> {
            indexOfPerson = counters;
            System.out.println(persons.get(indexOfPerson).getName() + " " +
                    persons.get(indexOfPerson).getMobile() +
                    " " + persons.get(indexOfPerson).getCity() + " " +
                    persons.get(indexOfPerson).getState() + " "
                    + persons.get(indexOfPerson).getZip() + " ");
        });
    }

    public void readJsonFile() {
        new IoOperation().writeJsonToFile(persons);
    }

    public void readCSVFile() {
        new IoOperation().writeCsvToFile(persons);
    }
}