package ru.mukhametzyanov;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Weather {

    public static void main(String[] args) {

        URL url = null;
        try {
            url = new URL("https://www.metaweather.com/api/location/2122265/?query=moscow");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try (InputStream is = url.openStream();
             Reader reader = new InputStreamReader(is);
             BufferedReader bf = new BufferedReader(reader)) {
            String s = bf.readLine();
            System.out.println(s);
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(s);
            JSONObject jo = (JSONObject) obj;
            //Преобразовывем в массив
            JSONArray arr = (JSONArray) jo.get("consolidated_weather");
            for (int i = 0; i < arr.size(); i++) {
                //Получаем член массива по ключу
                JSONObject value = (JSONObject) arr.get(i);
                System.out.println(value.get("min_temp"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
