package com.example.data.entity.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMapper {

    private SimpleDateFormat sdf;

    public DateMapper(){
        sdf = new SimpleDateFormat("dd-MM-yyyy");
    }

    public Date transformToDate(String dateString) throws ParseException {
        return sdf.parse(dateString);
    }

    public String transformFromDate(Date date){
        return sdf.format(date);
    }
}
