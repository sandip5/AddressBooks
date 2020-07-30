package com.bridgelab.addressbook.controller;

import com.bridgelab.addressbook.services.ImplAddressBook;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");
        ImplAddressBook util = new ImplAddressBook();
        util.openAddressBook();
    }
}
