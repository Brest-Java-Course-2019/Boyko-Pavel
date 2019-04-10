package com.epam.brest.project.builder;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * DateBuilder class.
 */
public class DateBuilder {
    /**
     * Start date.
     */
    private String startDate;
    /**
     * End date.
     */
    private String endDate;

    /**
     * @return startDate type String.
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Set start date <code>startDate</code>.
     *
     * @param startDate start Date.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return startDate type String.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Set end date <code>endDate</code>.
     *
     * @param endDate end Date.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * @param date date
     * @return date type Timestamp.
     */
    private Timestamp getTimestampDate(String date) throws ParseException {
        String format = "yyyy-MM-dd";
        SimpleDateFormat simpleDate = new SimpleDateFormat(format);
        return new Timestamp(simpleDate.parse(date).getTime());
    }

    /**
     * get startDate type Timestamp
     *
     * @return date type Timestamp.
     */
    public Timestamp getTimestampStartDate() throws ParseException {
        return getTimestampDate(this.startDate);
    }

    /**
     * get endDate type Timestamp
     *
     * @return date type Timestamp.
     */
    public Timestamp getTimestampEndDate() throws ParseException {
        return getTimestampDate(this.endDate);
    }
}