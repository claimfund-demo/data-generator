package com.redhat.summit2019.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableLoan.class)
@JsonDeserialize(as = ImmutableLoan.class)
public abstract class Loan {
    public abstract Person person();
    public abstract Farm farm();
    public abstract Location location();
    public abstract long amount();
    public abstract String sortCode();
    public abstract String bankAccount();
}
