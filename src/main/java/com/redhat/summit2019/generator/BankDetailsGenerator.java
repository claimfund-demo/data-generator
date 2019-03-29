package com.redhat.summit2019.generator;

import java.util.Formatter;
import java.util.concurrent.ThreadLocalRandom;

public class BankDetailsGenerator {
    private ThreadLocalRandom random = ThreadLocalRandom.current();

    public String generateSortCode() {
        return new Formatter().format("%d-%d-%d",
                random.nextInt(10, 99),
                random.nextInt(10, 99),
                random.nextInt(10, 99))
                .toString();
    }

    public String generateBankAccount() {
        return new Formatter().format("%d%d%d%d",
                random.nextInt(10, 99),
                random.nextInt(10, 99),
                random.nextInt(10, 99),
                random.nextInt(10, 99))
                .toString();
    }
}
