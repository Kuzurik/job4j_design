package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean go;

    @XmlAttribute
    private double volume;

    @XmlAttribute
    private String name;

    private Engine engine;

    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] places;

    public Car() { }

    public Car(boolean go, double volume, String name, Engine engine, String[] places) {
        this.go = go;
        this.volume = volume;
        this.name = name;
        this.engine = engine;
        this.places = places;
    }

    public boolean isGo() {
        return go;
    }

    public void setGo(boolean go) {
        this.go = go;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String[] getPlaces() {
        return places;
    }

    public void setPlaces(String[] places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return "Car{"
                + "go=" + go
                + ", volume=" + volume
                + ", name='" + name + '\''
                + ", engine=" + engine
                + ", places=" + Arrays.toString(places)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        Car car = new Car(false, 2.3, "mazda",
                new Engine("ST1343412312"), new String[]{"driver", "passenger next to the driver"});
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
