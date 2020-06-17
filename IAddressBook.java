package com.bridgelab.addressbook;

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

}
