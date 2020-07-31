package com.bridgelab.addressbook.model;

import com.opencsv.bean.CsvBindByName;

public class Person {

    @CsvBindByName(required = true, column = "name")
    private String name;

    @CsvBindByName(required = true, column = "mobile")
    private Long mobile;

    @CsvBindByName(required = true, column = "city")
    private String city;

    @CsvBindByName(required = true, column = "state")
    private String state;

    @CsvBindByName(required = true, column = "zip")
    private int zip;

    //use of POJO Class by getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getNAME_PATTERN() {
        return "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{2,}$";
    }

    public String getCOMMON_PATTERN() {
        return "^[A-Z]{1}[a-z]{2,}$";
    }

    public String getZIP_PATTERN() {
        return "^[1-9][0-9]{5}$";
    }

    public String getPHONE_NUMBER_PATTERN() {
        return "^[1-9][0-9]{9}$";
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", mobile=" + mobile +
                ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
    }
}