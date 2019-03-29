package com.redhat.summit2019.generator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.summit2019.model.ImmutableLoan;
import com.redhat.summit2019.model.Loan;
import com.redhat.summit2019.model.Person;

import java.util.ArrayList;
import java.util.List;
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
        var bankDetailsGenerator = new BankDetailsGenerator();
        var jsonWriter = new ObjectMapper();

        List<Loan> list = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            var random = ThreadLocalRandom.current();
            var randomGender = random.nextInt(3);

            Person person;
            switch(randomGender) {
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

           list.add(ImmutableLoan.builder()
                    .person(person)
                    .farm(farmGenerator.getFarm())
                    .location(locationGenerator.getLocation())
                    .amount(random.nextLong(1000, 1_000_000L))
                    .sortCode(bankDetailsGenerator.generateSortCode())
                    .bankAccount(bankDetailsGenerator.generateBankAccount())
                    .build());
        }
        return jsonWriter.writeValueAsString(list);
    }
}
