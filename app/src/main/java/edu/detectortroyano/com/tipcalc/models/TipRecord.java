package edu.detectortroyano.com.tipcalc.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by detectortroyano on 12/06/2016.
 */
public class TipRecord {
    private double bill;
    private int tipPercentaje;
    private Date timestamp;

    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
    }

    public int getTipPercentaje() {
        return tipPercentaje;
    }

    public void setTipPercentaje(int tipPercentaje) {
        this.tipPercentaje = tipPercentaje;
    }

    public Date getTimestap() {
        return timestamp;
    }

    public void setTimestap(Date timestap) {
        this.timestamp = timestap;
    }

    public double getTip(){
        return bill*(tipPercentaje/100d);
    }

    public String getDateFormatted(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm");
        return simpleDateFormat.format(timestamp);
    }

}
