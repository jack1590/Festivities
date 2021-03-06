package com.prodigious.festivities.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * This entity represents the table festivity from the database
 * @author Juan Joya
 *
 */
@Entity
@Table(name = "festivity")
@NamedQueries({ 
	@NamedQuery(name = "Festivity.deleteAll", query = "DELETE FROM Festivity"),
	@NamedQuery(name = "Festivity.findAll", query = "SELECT NEW com.prodigious.festivities.dto.FestivityDto(f.id, f.name, f.place, f.start, f.end) FROM Festivity f")
	
})
public class Festivity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "place")
	private String place;

	@Column(name = "start")
	private Date start;

	@Column(name = "end")
	private Date end;

	public Festivity() {
	}

	public Festivity(String name, String place, Date start, Date end) {
		super();
		this.name = name;
		this.place = place;
		this.start = start;
		this.end = end;
	}
	
	public Long getId(){
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

}
