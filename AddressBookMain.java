package com.bridgelab.addressbook;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");
        ImplAddressBook util = new ImplAddressBook();
        //Calling openAddressBook Method From ImplAddressBook Class
        util.openAddressBook();
    }
}
