package com.bridgelab.addressbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ImplAddressBook implements IAddressBook {
    Scanner scanner = new Scanner(System.in);
    int counter = 0;
    int indexOfPerson;
    static ArrayList<Person> persons = new ArrayList<Person>();

    public static void printPersonDetails(ArrayList<Person> persons) {
        persons.forEach(i -> {
            System.out.println(i.getFirstname() + " " + i.getLastname() + " " + i.getAddressObj().getAddressLocal()
                    + " " + i.getAddressObj().getCity() + " " + i.getAddressObj().getState() + " "
                    + i.getAddressObj().getZip() + " " + i.getMobile());
        });
    }

    @Override
    public void openAddressBook() {
        System.out.println("-----------------------Open Address Book-----------------------");
        boolean close = false;
        while (!close) {
            System.out.println(
                    "Select option: \n1.add\n2.print\n3.quit\n4.edit\n5.delete\n6.sort by name\n7.sort by city" +
                            "\n8.sort by state\n9.sort by zip\n10.search by city and state\n11.search by city or state");
            switch (scanner.nextInt()) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    // print
                    if (counter > 0) {
                        System.out.println("Printing all records...");

                        ImplAddressBook.printPersonDetails(persons);

                    } else
                        System.out.println("There is no record to print...");

                    break;
                case 3:
                    // close
                    close = true;
                    System.out.println("Closing...");
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
                default:
                    System.out.println("Invalid option");
            }
        }

    }

    @Override
    public void addPerson() {

        boolean isFoundPerson = true;
        if (counter > 0) {
            System.out.println("Enter Persons First name:");
            String searchFirstName = scanner.next();
            indexOfPerson = 0;
            for (int i = 0; i < persons.size(); i++) {
                if (searchFirstName.equals(persons.get(i).getFirstname())) {
                    isFoundPerson = false;
                    indexOfPerson = i;
                    break;
                }
            }
        }
        if (isFoundPerson) {
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
            System.out.println("Enter country name: ");
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
            counter++;
        } else {
            System.out.println("Duplicate entry of person's name not allowed...");
        }
    }

    @Override
    public void editPerson() {
        if (counter > 0) {
            System.out.println("Enter Persons First name you want to edit:");
            String searchFirstName = scanner.next();
            indexOfPerson = 0;
            boolean isFoundPerson = false;
            for (int i = 0; i < persons.size(); i++) {
                if (searchFirstName.equals(persons.get(i).getFirstname())) {
                    isFoundPerson = true;
                    indexOfPerson = i;
                    break;
                }
            }
            if (isFoundPerson) {
                System.out.println("enter country name");
                persons.get(indexOfPerson).getAddressObj().setAddressLocal(scanner.next());
                System.out.println("enter new city name");
                persons.get(indexOfPerson).getAddressObj().setCity(scanner.next());
                System.out.println("enter new state name");
                persons.get(indexOfPerson).getAddressObj().setState(scanner.next());
                System.out.println("enter new zip");
                persons.get(indexOfPerson).getAddressObj().setZip(scanner.nextInt());
                System.out.println("enter new mobile number");
                persons.get(indexOfPerson).setMobile(scanner.nextLong());
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
            System.out.println("Enter Persons First Name you want to delete:");
            String searchFirstName = scanner.next();
            indexOfPerson = 0;
            boolean isFoundPerson = false;
            for (int i = 0; i < persons.size(); i++) {
                if (searchFirstName.equals(persons.get(i).getFirstname())) {
                    isFoundPerson = true;
                    indexOfPerson = i;
                    break;
                }
            }
            if (isFoundPerson) {
                persons.remove(indexOfPerson);
                counter--;
                System.out.println();
                System.out.println("Delete completed");
            } else
                System.out.println("No person found with this number");
        } else
            System.out.println("No records to delete");
    }

    @Override
    public void sortByName() {
        if (counter > 1) {
            System.out.println("Sorting by Last name is selected");
            Collections.sort(persons, (e1, e2) -> e1.getFirstname().compareTo(e2.getFirstname()));
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

    public void sortByCity() {
        if (counter > 1) {
            System.out.println("Sorting by City name is selected");
            Collections.sort(persons, (e1, e2) -> String.valueOf(e1.getAddressObj().getCity())
                    .compareTo(String.valueOf(e2.getAddressObj().getCity())));
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
            Collections.sort(persons, (e1, e2) -> String.valueOf(e1.getAddressObj().getState())
                    .compareTo(String.valueOf(e2.getAddressObj().getState())));
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
            Collections.sort(persons, (e1, e2) -> String.valueOf(e1.getAddressObj().getZip())
                    .compareTo(String.valueOf(e2.getAddressObj().getZip())));
            System.out.println("Please wait...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
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
            int indexOfPerson = 0;

            for (int i = 0; i < persons.size(); i++) {
                if (persons.get(i).getAddressObj().getState().equals(searchState)) {
                    if (persons.get(i).getAddressObj().getCity().equals(searchCity)) {
                        indexOfPerson = i;
                        System.out.println(persons.get(indexOfPerson).getFirstname() + " " + persons.get(indexOfPerson).getLastname() + " " + persons.get(indexOfPerson).getAddressObj().getAddressLocal()
                                + " " + persons.get(indexOfPerson).getAddressObj().getCity() + " " + persons.get(indexOfPerson).getAddressObj().getState() + " "
                                + persons.get(indexOfPerson).getAddressObj().getZip() + " " + persons.get(indexOfPerson).getMobile());
                    }
                }
            }
        }
    }

    public void searchByCityOrState() {
        if (counter > 0) {
            System.out.println("Enter city:");
            String searchCity = scanner.next();
            System.out.println("Enter state:");
            String searchState = scanner.next();
            int indexOfPerson = 0;
            for (int i = 0; i < persons.size(); i++) {
                if (persons.get(i).getAddressObj().getState().equals(searchState)) {
                    indexOfPerson = i;
                    System.out.println(persons.get(indexOfPerson).getFirstname() + " " + persons.get(indexOfPerson).getLastname() + " " + persons.get(indexOfPerson).getAddressObj().getAddressLocal()
                            + " " + persons.get(indexOfPerson).getAddressObj().getCity() + " " + persons.get(indexOfPerson).getAddressObj().getState() + " "
                            + persons.get(indexOfPerson).getAddressObj().getZip() + " " + persons.get(indexOfPerson).getMobile());
                } else if (persons.get(i).getAddressObj().getCity().equals(searchCity)) {
                    indexOfPerson = i;
                    System.out.println(persons.get(indexOfPerson).getFirstname() + " " + persons.get(indexOfPerson).getLastname() + " " + persons.get(indexOfPerson).getAddressObj().getAddressLocal()
                            + " " + persons.get(indexOfPerson).getAddressObj().getCity() + " " + persons.get(indexOfPerson).getAddressObj().getState() + " "
                            + persons.get(indexOfPerson).getAddressObj().getZip() + " " + persons.get(indexOfPerson).getMobile());
                } else {
                    System.out.println("neither state match nor city");
                }
            }
        }
    }
}
