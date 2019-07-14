package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "result")
public class ReportRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int id = 1;
	
	@Id	
	@Column(name="RecordId", nullable=false, unique=true)
	private int RecordId;
	
	@Column(name="FilmId", nullable = false) 
	private int filmId;
	
	@Column(name="FilmName", nullable = false)
	private String filmName;
	
	@Column(name="CharacterId", nullable = false)
	private int characterId;
	
	@Column(name="CharacterName", nullable = false)
	private String characterName;
	
	@Column(name="PlanetId", nullable = false)
	private int planetId;
	
	@Column(name="PlanetName", nullable = false)
	private String planetName;
	
	public ReportRecord(int characterId, String characterName, int planetId, String planetName) {
		RecordId = id++;
		this.characterId = characterId;
		this.characterName = characterName;
		this.planetId = planetId;
		this.planetName = planetName;
	}
	
	public ReportRecord() {}
	
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	public int getCharacterId() {
		return characterId;
	}
	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public int getPlanetId() {
		return planetId;
	}
	public void setPlanetId(int planetId) {
		this.planetId = planetId;
	}
	public String getPlanetName() {
		return planetName;
	}
	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}
	
}
