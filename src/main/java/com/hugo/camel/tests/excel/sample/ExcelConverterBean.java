package com.hugo.camel.tests.excel.sample;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.apache.camel.Body;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelConverterBean {
    private final static Log log = LogFactory.getLog(ExcelConverterBean.class);

    public CustomerOutput processExcelInvoice(@Body InputStream body) {
    	ArrayList<CustomerData> list = new ArrayList<CustomerData>(0);
    	CustomerOutput data = new CustomerOutput();
    	
    	try {
            HSSFWorkbook workbook = new HSSFWorkbook(body);
            HSSFSheet sheet = workbook.getSheetAt(0);
            boolean headersFound = false;
            int colNum;
            for(Iterator rit = sheet.rowIterator(); rit.hasNext();) {
                HSSFRow row = (HSSFRow) rit.next();
                if(!headersFound) {  // Skip the first row with column headers
                    headersFound = true;
                    continue;
                }
                colNum = 0;
                CustomerData customer = new CustomerData();
                for(Iterator cit = row.cellIterator(); cit.hasNext(); ++colNum) {
                    HSSFCell cell = (HSSFCell) cit.next();
                    if(headersFound)
                    switch(colNum) {
                        case 0: // Date
                            GregorianCalendar calendar = new GregorianCalendar();
                            calendar.setTime(cell.getDateCellValue());
                            customer.setDate(calendar);
                            break;
                        case 1: // Price
                        	customer.setPrice(cell.getNumericCellValue());
                            break;
                        case 2: // Quantity
                        	customer.setQuantity(cell.getNumericCellValue());
                            break;
                        case 3: // Total
                        	customer.setTotal(cell.getNumericCellValue());
                            break;
                        case 4: // Name
                        	customer.setName(cell.getRichStringCellValue().getString());
                            break;
                        case 5: // ID
                        	customer.setId((int)cell.getNumericCellValue());
                            break;
                    }
                }
                list.add(customer);
            }
        } catch (Exception e) {
            log.error("Unable to import Excel invoice", e);
            throw new RuntimeException("Unable to import Excel invoice", e);
        }
    	data.setCustomers(list);
    	
        return data;
    }
}