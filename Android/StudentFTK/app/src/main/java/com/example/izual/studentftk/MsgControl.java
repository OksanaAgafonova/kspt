package com.example.izual.studentftk;

import android.app.Activity;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oglandx on 17.12.2014.
 */
public class MsgControl {

    // имена атрибутов для Map
    public final static String ATTRIBUTE_NAME_TEXT = "text";
    public final static String ATTRIBUTE_NAME_TIME = "time";
    public final static String ATTRIBUTE_NAME_IMAGE = "image";

    public final static int DATE_ALL = 0;
    public final static int DATE_TIME = 1;
    public final static int DATE_DAY = 2;
    public final static int DATE_DAY_AND_TIME = 3;

    // упаковывает сообщение в нужную структуру
    private static Map<String, Object> PackMessage(String msg_text, String msg_time){
        int img = R.drawable.ic_friends;
        Map<String, Object> msg_pack = new HashMap<String, Object>();
        msg_pack.put(ATTRIBUTE_NAME_TEXT, msg_text);
        msg_pack.put(ATTRIBUTE_NAME_TIME, msg_time);
        msg_pack.put(ATTRIBUTE_NAME_IMAGE, img);
        return msg_pack;
    }

    /**
     * Добавляет сообщение (msg_text, msg_time) в список сообщений msgList.
     * @param msgList список сообщений
     * @param msg_text текст сообщения
     * @param msg_time время отправки сообщения
     */
    public static void AddMessageToList(ArrayList<Map<String, Object>> msgList,
                                    String msg_text, String msg_time){
        msgList.add(PackMessage(msg_text, msg_time));
    }

    /**
     * Создаёт адаптер для вывода списка сообщений на экран
     * @param msgList список сообщений
     * @param activity активити, на котором отображаются сообщения
     * @return адаптер
     */
    public static SimpleAdapter CreateAdapter(ArrayList<Map<String, Object>> msgList,
                                                                                Activity activity){
        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_TIME,
                ATTRIBUTE_NAME_IMAGE };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvText, R.id.tvText1, R.id.ivImg };

        return new SimpleAdapter( activity, msgList, R.layout.item_message, from, to);
    }

    /**
     * Инициализирует список сообщений и создаёт адаптер для его вывода на экран.
     * @param msgList список сообщений
     * @param activity активити
     * @param msg_text список текстов сообщений
     * @param msg_time список времён отправки сообщений
     * @return адаптер
     */
    public static SimpleAdapter InitFramework(ArrayList<Map<String, Object>> msgList,
                       Activity activity, ArrayList<String> msg_text, ArrayList<String> msg_time){
        if(msgList == null){
            return null;
        }
        for (int i = 0; i < msg_text.size(); i++) {
            AddMessageToList(msgList, msg_text.get(i), msg_time.get(i));
        }

        return CreateAdapter(msgList, activity);
    }

    public static String FormatDate(int mode){
        Calendar calendar = Calendar.getInstance();
        String time = calendar.getTime().toString();
        switch(mode){
            case DATE_DAY_AND_TIME:
                return time.substring(0, 19);
            case DATE_DAY:
                return time.substring(0, 10);
            case DATE_TIME:
                return time.substring(10, 19);
            case DATE_ALL:
            default:
                return time;
        }
    }
}
