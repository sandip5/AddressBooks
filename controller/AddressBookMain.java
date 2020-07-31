package com.bridgelab.addressbook.controller;

import com.bridgelab.addressbook.model.Person;
import com.bridgelab.addressbook.services.ImplAddressBook;
import com.bridgelab.addressbook.utility.IoOperation;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class AddressBookMain {

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");
        AddressBookMain addressBookMain = new AddressBookMain();

        addressBookMain.openAddressBook();
    }

    public void openAddressBook() {
        Scanner scanner = new Scanner(System.in);
        ImplAddressBook util = new ImplAddressBook();
        System.out.println("Select option: \n1.Read File Using Simple Json" +
                "\n2.Read File Using OpenCSV" +
                "\n3.Do Operation In Empty List");
        int select = scanner.nextInt();
        if (1 == select) {
            ArrayList<Person> addressBookList = new IoOperation().readFromJson();
            util.setList(addressBookList);
        }
        if (2 == select) {
            ArrayList<Person> addressBookList;
            addressBookList = new IoOperation().readFromCSV();
            util.setList(addressBookList);
        }
        if (3 == select) {
            ArrayList<Person> addressBookList = new ArrayList<>();
            util.setList(addressBookList);
        }
        System.out.println("-----------------------Open Address Book-----------------------");
        AtomicBoolean close = new AtomicBoolean(false);
        while (!close.get()) {
            System.out.println(
                    "Select option: \n1.add" +
                            "\n2.print" +
                            "\n3.quit" +
                            "\n4.edit" +
                            "\n5.delete" +
                            "\n6.sort by name" +
                            "\n7.sort by city" +
                            "\n8.sort by state" +
                            "\n9.sort by zip" +
                            "\n10.search by city and state" +
                            "\n11.search by city or state" +
                            "\n12. write JSON file" +
                            "\n13. Write CSV File");
            switch (scanner.nextInt()) {
                case 1:
                    util.addPerson();
                    break;
                case 2:
                    util.printPersonDetails();
                    break;
                case 3:
                    close.set(true);
                    break;
                case 4:
                    util.editPerson();
                    break;
                case 5:
                    util.deletePerson();
                    break;
                case 6:
                    util.sortByName();
                    break;
                case 7:
                    util.sortByCity();
                    break;
                case 8:
                    util.sortByState();
                    break;
                case 9:
                    util.sortByZip();
                    break;
                case 10:
                    util.searchByCityAndState();
                    break;
                case 11:
                    util.searchByCityOrState();
                    break;
                case 12:
                    util.readJsonFile();
                    break;
                case 13:
                    util.readCSVFile();
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }
}