package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.Car;
import ru.job4j.serialization.Engine;

import java.util.ArrayList;
import java.util.List;

public class Transform {

    public static void main(String[] args) {

        JSONObject jsonEngine = new JSONObject("{\"vin\":\"ST1343412312\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("driver");
        list.add("passenger next to the driver");
        JSONArray jsonPlaces = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Car car = new Car(false, 2.3, "mazda",
                new Engine("ST1343412312"), new String[]{"driver", "passenger next to the driver"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("go", car.isGo());
        jsonObject.put("volume", car.getVolume());
        jsonObject.put("name", car.getName());
        jsonObject.put("contact", jsonEngine);
        jsonObject.put("statuses", jsonPlaces);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(car).toString());
    }
}
