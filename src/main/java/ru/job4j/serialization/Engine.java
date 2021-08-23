package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;

public class Engine {

    @XmlAttribute
    private String vin;

    public Engine() { }

    public Engine(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "vin='" + vin + '\''
                + '}';
    }
}
