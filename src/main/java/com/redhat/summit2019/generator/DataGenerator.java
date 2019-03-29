package com.redhat.summit2019.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.summit2019.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {
    public String generateJsonData() throws JsonProcessingException {
        return generateJsonData(1);
    }

    public String generateJsonData(int quantity) throws JsonProcessingException {
        if (quantity > 500) {
            throw new RuntimeException("Cannot produce more than 500 items at a time");
        }
        var personGenerator = new PersonGenerator();
        var farmGenerator = new FarmGenerator();
        var locationGenerator = new LocationGenerator();
        var jsonWriter = new ObjectMapper();

        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Map<String, Object> map = new HashMap<>();
            var random = ThreadLocalRandom.current().nextInt(3);
            Person person;
            switch(random) {
                case 0:
                    person = personGenerator.getPerson("f");
                    break;
                case 1:
                    person = personGenerator.getPerson("nb");
                    break;
                case 2:
                    person = personGenerator.getPerson("m");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + random);
            }

            map.put("Person", person);
            map.put("Farm", farmGenerator.getFarm());
            map.put("Location", locationGenerator.getLocation());
            list.add(map);
        }
        return jsonWriter.writeValueAsString(list);
    }
}
