package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            //Начало отсчета времени
            long time = System.currentTimeMillis();
            //Заполнения списка для вывода информации
            Map<String, Abonent> abonentMap = parseCDR("./src/main/resources/cdr.txt");
            for(Map.Entry<String, Abonent> a : abonentMap.entrySet()){
                System.out.printf("Key: %s  Value: %s \n", a.getKey(), a.getValue().toString());
            }
            //Конец отсчета времени
            System.out.println((System.currentTimeMillis() - time) + " мс" + ", Найдено строк: " + abonentMap.size());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Парсинг cdr файла
    private static Map<String, Abonent> parseCDR(String filePath) throws IOException {
        Map<String, Abonent> abonentMap = new HashMap<String, Abonent>();
        //Загрузка строк из файла
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        //Цикл по строкам
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<String>();
            //Цикл по колонкам
            for (int i = 0; i < splitedText.length; i++) {
                //Если колонка начинается на кавычки или заканчиваеться на кавычки
                if (IsColumnPart(splitedText[i])) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + "," + splitedText[i]);
                } else {
                    columnList.add(splitedText[i]);
                }
            }
            //Заполнение cdr
            CDR cdr = new CDR();
            cdr.setTypeOfCall(columnList.get(0));
            cdr.setPhoneNumber(columnList.get(1));
            cdr.setStartOfCall(columnList.get(2));
            cdr.setEndOfCall(columnList.get(3));
            cdr.setTariffType(columnList.get(4));
            //Заполнение абонента
            if(abonentMap.containsKey(columnList.get(1))){
                abonentMap.get(columnList.get(1)).getCalls().add(cdr);
            } else {
                abonentMap.put(columnList.get(1), new Abonent());
            }
        }
        return abonentMap;
    }

    //Проверка является ли колонка частью предыдущей колонки
    private static boolean IsColumnPart(String text) {
        String trimText = text.trim();
        //Если в тексте одна ковычка и текст на нее заканчиваеться значит это часть предыдущей колонки
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }

    //Проверка является ли строка числом
    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            int num = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}