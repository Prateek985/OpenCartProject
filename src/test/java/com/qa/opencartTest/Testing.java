package com.qa.opencartTest;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class Testing {
	
	@DataProvider
	public Object[][] csvData() throws IOException, CsvException {
		String csvFile = "./src/test/resource/testdata/register.csv";
		CSVReader reader = new CSVReader(new FileReader(csvFile));
		
		List<String[]> rows = reader.readAll();
		
		reader.close();
		Object[][]data = new Object[rows.size()][];
		for(int i=0;i<rows.size();i++) {
			data[i] = rows.get(i);
		}
		return data;
	}
	
	@Test(dataProvider = "csvData")
	public void TestData(String param1,String param2,String param3,String param4,String param5) {
		System.out.println("Param1: " + param1 + ", Param2: " + param2 + ", Param3: " + param3 + ", Param4: " + param4 + ", Param5: " + param5);
	}
	

}
