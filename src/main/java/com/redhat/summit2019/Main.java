package com.redhat.summit2019;

import com.redhat.summit2019.generator.PersonGenerator;
import com.redhat.summit2019.model.Person;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 15; i++) {
            int random = ThreadLocalRandom.current().nextInt(3);
            Person person;
            if (random == 0) {
                person = PersonGenerator.getPerson("f");
            } else if (random == 1) {
                person = PersonGenerator.getPerson("nb");
            } else {
                person = PersonGenerator.getPerson("m");
            }
            System.out.println(person.toJSON());
        }

    }
}
