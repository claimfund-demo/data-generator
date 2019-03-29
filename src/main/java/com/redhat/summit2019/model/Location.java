package com.redhat.summit2019.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableLocation.class)
@JsonDeserialize(as = ImmutableLocation.class)
public abstract class Location {
    @Value.Parameter
    public abstract String name();
    @Value.Parameter
    public abstract String council();

    @Override
    public String toString() {
        return name() + ", " + council();
    }
}
