package org.example;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            //Начало отсчета времени
            long time = System.currentTimeMillis();
            //Заполнения списка для вывода информации
            Map<String, Abonent> abonentMap = parseCDR("./src/main/resources/cdr.txt");
            //Конец отсчета времени
            System.out.println((System.currentTimeMillis() - time) + " мс" + ", Найдено строк: " + abonentMap.size());
            //Запись данных в текстовый файл
            PrintStream fileOut = new PrintStream(
                    "./src/main/resources/data");
            for (Map.Entry<String, Abonent> a : abonentMap.entrySet()) {
                fileOut.print(a.getValue().toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Парсинг cdr файла
    private static Map<String, Abonent> parseCDR(String filePath) throws IOException {
        //Устанавливаем формат для чтения даты и времени начала и конца разговора
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
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
                columnList.add(splitedText[i]);
            }
            //Заполнение cdr
            CDR cdr = new CDR();
            cdr.setTypeOfCall(columnList.get(0));
            cdr.setPhoneNumber(columnList.get(1));
            try {
                //Форматирование строки в дату
                cdr.setStartOfCall(format.parse(columnList.get(2).trim()));
                cdr.setEndOfCall(format.parse(columnList.get(3).trim()));
                //Расчет длительности разговора
                cdr.setDuration(new Date(cdr.getEndOfCall().getTime() - cdr.getStartOfCall().getTime() - 14400000));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            cdr.setTariffType(columnList.get(4));
            //Заполнение абонента
            if (abonentMap.containsKey(columnList.get(1))) {
                abonentMap.get(columnList.get(1)).getCalls().add(cdr);
                abonentMap.get(columnList.get(1)).addTotalDuration(cdr.getDuration().getTime());
            } else {
                abonentMap.put(columnList.get(1), new Abonent(cdr));
            }

            //Заполнение цены с учетом тарифа
            //Тариф Поминутный
            if(cdr.getTariffType().trim().equals("03")){
                cdr.setCost((cdr.getDuration().getTime()+14400000)/60_000*1.5 );
                //Тариф обычный
            } else if(cdr.getTariffType().trim().equals("11")){
                if(cdr.getTypeOfCall().trim().equals("01")){
                    if(abonentMap.get(columnList.get(1)).getTotalDuration().getTime() < 100*60*1000){
                        cdr.setCost((cdr.getDuration().getTime()+14400000)/60_000*0.5 );
                    } else {
                        cdr.setCost((cdr.getDuration().getTime()+14400000)/60_000*1.5 );
                    }
                } else {
                    cdr.setCost(0.0);
                }
                //Тариф безлимит
            } else if (cdr.getTariffType().trim().equals("06")){
                if(abonentMap.get(columnList.get(1)).getTotalDuration().getTime() < 300*60*1000){
                    cdr.setCost(0.0);
                    abonentMap.get(columnList.get(1)).setTotalCost(100);
                } else {
                    cdr.setCost((cdr.getDuration().getTime()+14400000)/60_000*1.0);
                }
            }
        }
        return abonentMap;
    }


}