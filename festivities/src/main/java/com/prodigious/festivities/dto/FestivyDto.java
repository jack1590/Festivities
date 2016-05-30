package com.prodigious.festivities.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.prodigious.festivities.adapter.DateAdapter;

@XmlRootElement(name = "festivity")
public class FestivyDto {
	
	@XmlElement(name = "name")
	private String name;
	
	@XmlElement(name = "place")
	private String place;
	
	@XmlElement(name = "start")
    @XmlJavaTypeAdapter(type = Date.class, value = DateAdapter.class)
	private Date start;
	
	@XmlElement(name = "end")
    @XmlJavaTypeAdapter(type = Date.class, value = DateAdapter.class)
	private Date end;
	
	public FestivyDto(){
	}
	
	public FestivyDto(String name, String place, Date start, Date end) {
		super();
		this.name = name;
		this.place = place;
		this.start = start;
		this.end = end;
	}
	
	public String getName() {
		return name;
	}
    
	public String getPlace() {
		return place;
	}
    
	public Date getStart() {
		return start;
	}
    
	public Date getEnd() {
		return end;
	}

	@Override
	public String toString() {
		return "FestivyDto [name=" + name + ", place=" + place + ", start="
				+ start + ", end=" + end + "]";
	}
	
}
