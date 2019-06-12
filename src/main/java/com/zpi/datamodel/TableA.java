package com.zpi.datamodel;

import java.util.List;

public class TableA {
	String table;
    String no;
    String effectiveDate;
    List<TableRateA> rates;
	public String getTable() {
		return table;
	}
	public String getNo() {
		return no;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public List<TableRateA> getRates() {
		return rates;
	}
	@Override
	public String toString() {
		return "TableA [table=" + table + ", no=" + no + ", effectiveDate=" + effectiveDate + "]";
	}
}