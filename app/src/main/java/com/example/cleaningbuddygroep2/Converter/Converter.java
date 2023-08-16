package com.example.cleaningbuddygroep2.Converter;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converter {
    // van timestamp naar datum
    @TypeConverter
    public static Date fromTimeStamp(Long value){
        return value == null ? null : new Date(value);
    }
    // van datum naar timestamp
    @TypeConverter
    public static Long dateToTimeStamp(Date date){
        return date == null ? null : date.getTime();
    }
}
