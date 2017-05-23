package com.hugo.camel.tests.excel.sample;

import java.util.List;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.Link;

@CsvRecord(separator = ",", crlf = "UNIX")
public class CustomerOutput {

	@Link
	private List<CustomerData> customers;

	public List<CustomerData> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerData> customers) {
		this.customers = customers;
	}

}
