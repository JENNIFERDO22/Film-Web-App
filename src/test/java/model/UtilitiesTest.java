package model;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import org.junit.Assert;
import model.Utilities;

public class UtilitiesTest {

	@Test
	public void consumeAPI() {
		String url = "https://swapi.co/api/films/1/";
		String actual = Utilities.consumeAPI(url);
		
		String filePath = "resources/film1.txt";
		FileReader file = null;
		FileReader file2 = null;
		try {
			file = new FileReader(filePath);
			file2 = new FileReader(filePath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject actualFilm = (JSONObject) parser.parse(actual);
			JSONObject expectedFilm = (JSONObject) parser.parse(file);
			
			Assert.assertEquals(expectedFilm, actualFilm);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getNameFromURL() {
		String url = "https://swapi.co/api/films/1/";
		String field = "title";
		String actualName = Utilities.getNameFromURL(url, field);
		String expectedName = "A New Hope";
		
		Assert.assertEquals(expectedName, actualName);
	}
	
	@Test
	public void getIdFromURL() {
		String url = "https://swapi.co/api/films/1/";
		int actualId = Utilities.getIdFromURL(url);
		int expectedId = 1;
		
		Assert.assertEquals(expectedId, actualId);
	}
	
	@Test
	public void addRecordToTable() {
		ReportRecord record = new ReportRecord();
		Utilities.addRecordToTable(record);
		List<ReportRecord> records = Utilities.getReport();
		ReportRecord actual = records.get(records.size() - 1);
		
		Assert.assertEquals(record, actual);
	}
	
}