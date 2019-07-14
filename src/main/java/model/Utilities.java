package model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utilities {
	private static final String PERSISTENCE_UNIT_NAME = "characterapi";
	private final static EntityManagerFactory factory = 
	    Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	private final static JSONParser parser = new JSONParser();
	
	// Utilities for PUT method
	@Produces(MediaType.APPLICATION_JSON)
	public static String consumeAPI(String url) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		String result = target.request(MediaType.APPLICATION_JSON).get(String.class);
		return result;
	}
	
	public static String getNameFromURL(String url, String field) {
		String fullData = consumeAPI(url);
		try {
			JSONObject obj = (JSONObject) parser.parse(fullData);
			return (String) obj.get(field);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getIdFromURL(String url) {
		Pattern p = Pattern.compile("(\\D*)(\\d+)(\\D*)");
		Matcher m = p.matcher(url);
		m.matches();
		return Integer.valueOf(m.group(2));
	}
	
	public static void addRecordToTable(Object obj) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			em.persist(obj);
			et.commit();
		} catch(Exception e) {
			if (et != null)
				et.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	// Utilities for GET method
	
	public static Criteria getCriteria() {
		EntityManager em = factory.createEntityManager();
		String query = "SELECT c FROM Criteria c";
		Criteria criteria = null;
		try {
			criteria=  (Criteria) em.createQuery(query).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return criteria;
	}
	
	public static List<ReportRecord> getReport() {
		EntityManager em = factory.createEntityManager();
		String query = "SELECT r FROM ReportRecord r";
		List<ReportRecord> result = null;
		
		try {
			result = (List<ReportRecord>) em.createQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return result;
	}
	
	
	// Utilities for DELETE method
	public static void deleteReportAndCriteria() {
		EntityManager em = factory.createEntityManager();
		String q1 = "DELETE FROM Criteria";
		String q2 = "DELETE FROM ReportRecord";
		EntityTransaction et = null;
		
		try {
			et = em.getTransaction();
			et.begin();
			em.createQuery(q1).executeUpdate();
			em.createQuery(q2).executeUpdate();
			et.commit();
		} catch (Exception e) {
			if (et != null) 
				et.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
}
