package com.bridgelab.addressbook.main;

import com.bridgelab.addressbook.services.ImplAddressBook;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book");
        ImplAddressBook util = new ImplAddressBook();
        util.openAddressBook();
    }
}
