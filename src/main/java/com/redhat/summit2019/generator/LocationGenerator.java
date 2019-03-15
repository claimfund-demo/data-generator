package com.redhat.summit2019.generator;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class LocationGenerator {
    private Map.Entry<String, String>[] locations;

    public LocationGenerator() {
        List<AbstractMap.SimpleEntry> locationList = new ArrayList<>();
        locationList.add(new AbstractMap.SimpleEntry("Dunfermline", "Fife"));
        locationList.add(new AbstractMap.SimpleEntry("Kirkcaldy", "Fife"));
        locationList.add(new AbstractMap.SimpleEntry("Greenock", "Inverclyde"));
        locationList.add(new AbstractMap.SimpleEntry("Port Glasgow", "Inverclyde"));
        locationList.add(new AbstractMap.SimpleEntry("Airdrie", "North Lanarkshire"));
        locationList.add(new AbstractMap.SimpleEntry("Coatbridge", "North Lanarkshire"));
        locationList.add(new AbstractMap.SimpleEntry("Hamilton", "South Lanarkshire"));
        locationList.add(new AbstractMap.SimpleEntry("Larkhall", "South Lanarkshire"));
        locationList.add(new AbstractMap.SimpleEntry("Bathgate", "West Lothian"));
        locationList.add(new AbstractMap.SimpleEntry("Livingston", "West Lothian"));
        locations = locationList.toArray(new AbstractMap.SimpleEntry[0]);
    }

    public Map.Entry<String, String> getLocation() {
        int random = ThreadLocalRandom.current().nextInt(locations.length);
        return locations[random];
    }
}
