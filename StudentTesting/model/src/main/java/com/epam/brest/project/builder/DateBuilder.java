package com.epam.brest.project.builder;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateBuilder {
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    private Timestamp getTimestampDate(String date) throws ParseException {
        String format = "yyyy-MM-dd";
        SimpleDateFormat simpleDate = new SimpleDateFormat(format);
        return new Timestamp(simpleDate.parse(date).getTime());
    }

    public Timestamp getTimestampStartDate() throws ParseException {
        return getTimestampDate(this.startDate);
    }

    public Timestamp getTimestampEndDate() throws ParseException {
        return getTimestampDate(this.endDate);
    }
}