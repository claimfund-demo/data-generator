package com.redhat.summit2019;

import com.redhat.summit2019.generator.PersonGenerator;
import com.redhat.summit2019.model.Person;

public class Main {
    private static PersonGenerator personGenerator = new PersonGenerator();
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Person person;
            if (i % 2 == 0) {
                person = personGenerator.getPerson("f");
            } else {
                person = personGenerator.getPerson("f");
            }
            System.out.println(person);
            System.out.println(person.toJSON());
        }

    }
}
