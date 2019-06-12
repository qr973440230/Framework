package com.qr.core.framework.configration.room.converter;


import androidx.room.TypeConverter;

import com.google.gson.reflect.TypeToken;
import com.qr.core.framework.Application;

import java.util.ArrayList;
import java.util.List;

public class StringListConverter {
    @TypeConverter
    public static List<String> string2StringList(String string){
        try{
            return Application.application.getGson().fromJson(string,new TypeToken<List<String>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @TypeConverter
    public static String stringList2String(List<String> strings){
        return Application.application.getGson().toJson(strings);
    }
}
