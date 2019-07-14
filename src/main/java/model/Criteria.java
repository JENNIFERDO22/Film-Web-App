package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="criteria")
public class Criteria {
	private static int id = 1;
	
	@Id 
	@Column(name="CriteriaId", nullable=false)
	private int criteriaId;
	
	@Column(name="CharacterPhrase", nullable=false)
	@XmlElement private String characterPhrase;
	
	@Column(name="PlanetName", nullable=false)
	@XmlElement private String planetName;
	
	public String getCharacterPhrase() {
		return characterPhrase;
	}
	
	public String getPlanetName() {
		return planetName;
	}
	
	public void setCriteriaId() {
		criteriaId = id++;
	}
}
