package com.redhat.summit2019.generator;

import com.redhat.summit2019.model.Person;

import java.util.Locale;
import java.util.Map;

public class PersonGenerator {
    private static NameGenerator nameGenerator = new NameGenerator();
    private static LocationGenerator locationGenerator = new LocationGenerator();

    public static Person getPerson(String gender) {
        NameGenerator nameGenerator = new NameGenerator();
        if (gender == null || "".equals(gender)) {
            throw new RuntimeException("Gender can't be null or empty.");
        }

        gender = gender.toUpperCase(Locale.ENGLISH);
        Person person = new Person();

        String[] name;
        if ("M".equals(gender)) {
            name = nameGenerator.getMaleName().split(" ");
        } else if ("F".equals(gender)) {
            name = nameGenerator.getFemaleName().split(" ");
        } else {
            throw new RuntimeException("Gender choices are [F]emale or [M]ale.");
        }

        person.setGivenName(name[0]);
        person.setSurname(name[1]);
        person.setGender(gender);
        Map.Entry<String, String> location = locationGenerator.getLocation();
        person.setLocation(location.getKey());
        person.setCouncil(location.getValue());

        return person;
    }
}
