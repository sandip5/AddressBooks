package com.bridgelab.addressbook.utility;

import com.bridgelab.addressbook.model.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IoOperation {
    String FILE_PATH = "C:\\Users\\aple\\IdeaProjects\\AddressBook\\src\\main\\resources\\AddressBook.json";

    public void writeListToFile(ArrayList<Person> persons) {
        JSONArray personList = new JSONArray();
        for (Person value : persons) {
            JSONObject personDetails = new JSONObject();
            personDetails.put("name", value.getName());
            personDetails.put("mobile", value.getMobile());
            personDetails.put("city", value.getCity());
            personDetails.put("state", value.getState());
            personDetails.put("zip", value.getZip());
            JSONObject personObject = new JSONObject();
            personObject.put("person", personDetails);
            personList.add(personObject);
        }
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.append(personList.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> readFromJson() {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Person> addressBookList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Object obj = jsonParser.parse(reader);
            JSONArray jsonList = (JSONArray) obj;
            jsonList.forEach(person -> addressBookList.add(parseAddressBook((JSONObject) person)));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return addressBookList;
    }

    private Person parseAddressBook(JSONObject personJson) {
        Person person = new Person();
        JSONObject personObj = (JSONObject) personJson.get("person");
        String personName = (String) personObj.get("name");
        person.setName(personName);
        Long mobile = (Long) personObj.get("mobile");
        person.setMobile(mobile);
        String city = (String) personObj.get("city");
        person.setCity(city);
        String state = (String) personObj.get("state");
        person.setState(state);
        Long zip = (Long) personObj.get("zip");
        person.setZip(zip.intValue());
        return person;
    }
}
