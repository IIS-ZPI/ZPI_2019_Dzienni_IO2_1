package com.zpi.datamodel;

import java.util.ArrayList;
import java.util.List;

public class CurrencyNoteA {

    private String table;
    private String currency;
    private String code;
    private List<RateA> rates = new ArrayList<>();

    public String getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public List<RateA> getRates() {
        return rates;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRates(List<RateA> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "CurrencyNoteA [table=" + table + ", currency=" + currency + ", code=" + code + ", rates=" + rates + "]";
    }
}
