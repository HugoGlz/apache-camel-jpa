package com.hugo.camel.tests.excel.sample;

import java.util.Calendar;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", crlf = "UNIX")
public class CustomerData {

	@DataField(pos = 1)
	private Integer id;
	
	@DataField(pos = 2)
	private Double price;
	
	@DataField(pos = 3)
	private Double total;
	
	@DataField(pos = 4)
	private Calendar date;
	
	@DataField(pos = 5)
	private Double quantity;
	
	@DataField(pos = 6)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String toString(){
		return String.format(
				"The response is %s", this.getName()
				);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
}
