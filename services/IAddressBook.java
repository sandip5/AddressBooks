package com.bridgelab.addressbook.services;

public interface IAddressBook {
    void addPerson();
    void openAddressBook();
    void editPerson();
    void deletePerson();
    void sortByName();
    void sortByCity();
    void sortByState();
    void sortByZip();
    void searchByCityAndState();
    void searchByCityOrState();
}
