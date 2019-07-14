package controller;

//import javax.ws.rs.Consumes;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.ReportRecord;
import model.Utilities;
import model.Criteria;
import model.GETResponse;

@RestController
@RequestMapping("/report")
public class ReportController {
	private final JSONParser parser = new JSONParser();
	ObjectMapper mapper = new ObjectMapper();
	private final String INPUT_URL = "https://swapi.co/api/people/";

	@GetMapping
	public String getReport() {
		GETResponse response = new GETResponse();
		response.setCriteria(Utilities.getCriteria());
		response.setResult(Utilities.getReport());
		try {
			return mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} 
		return "Error";
	}
	
	@DeleteMapping
	public String deleteReport() {
		Utilities.deleteReportAndCriteria();
		return "Successfully deleted the data from database";
	}
	
	@PutMapping
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public String generateReport(@RequestBody Criteria criteria) {
		criteria.setCriteriaId();
		Utilities.addRecordToTable(criteria);
		
		getReportList(INPUT_URL, criteria.getCharacterPhrase(), criteria.getPlanetName());
		System.out.println("Searching finished");
		System.out.println("=============================\n\n");
		return "Successfully generated report";
	}
	
	private void getReportList(String url, String characterPhrase, String targetPlanetName) {
		if (url == null) {
			return;
		} else {
			String characters = Utilities.consumeAPI(url);
			
			try {
				JSONObject response = (JSONObject)parser.parse(characters);
				url = (String) response.get("next");
				
				JSONArray characterArray = (JSONArray)response.get("results");
				
				for (int i=0; i < characterArray.size(); i++) {
					JSONObject ch = (JSONObject)characterArray.get(i);
					String characterName = (String) ch.get("name");
					
					if (characterName.contains(characterPhrase)) {
						String planetURL = (String) ch.get("homeworld");
						String planetName = Utilities.getNameFromURL(planetURL, "name");
						
						if (planetName.contentEquals(targetPlanetName)) {
							JSONArray films = (JSONArray) ch.get("films");
							String characterURL = (String) ch.get("url");
							
							int characterId = Utilities.getIdFromURL(characterURL);
							int planetId = Utilities.getIdFromURL(planetURL);
							
							
							for (int j = 0; j < films.size(); j++) {
								String filmURL = (String) films.get(j);
								ReportRecord record = new ReportRecord(characterId, characterName, planetId, planetName);
								int filmId = Utilities.getIdFromURL(filmURL);
								String filmName = Utilities.getNameFromURL(filmURL, "title");
								record.setFilmId(filmId);
								record.setFilmName(filmName);
								
								Utilities.addRecordToTable(record);
								//result.add(record);
							}
						}						
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			getReportList(url, characterPhrase, targetPlanetName);
		}
	}
	
}
