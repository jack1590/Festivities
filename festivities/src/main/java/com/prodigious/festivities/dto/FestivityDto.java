package com.prodigious.festivities.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prodigious.festivities.adapter.DateAdapter;

/**
 * This class represents in a plain object the entity Festivity
 * @author Juan Joya
 *
 */
@XmlRootElement(name = "festivity")
public class FestivityDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private Long id;
	
	@NotNull(message="{festivity.name.not.null}")
	@Size(min=1, max=255, message="{festivity.name.incorrect.size}")
	@XmlElement(name = "name")
	private String name;
	
	@NotNull(message="{festivity.place.not.null}")
	@Size(min=1, max=255, message = "{festivity.place.incorrect.size}")
	@XmlElement(name = "place")
	private String place;
	
	@NotNull(message="{festivity.startDate.not.null}")
	@XmlElement(name = "start")
    @XmlJavaTypeAdapter(type = Date.class, value = DateAdapter.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date start;
	
	@NotNull(message="{festivity.endDate.not.null}")
	@XmlElement(name = "end")
    @XmlJavaTypeAdapter(type = Date.class, value = DateAdapter.class)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date end;
	
	public FestivityDto(){
	}
	
	public FestivityDto(Long id, String name, String place, Date start, Date end) {
		super();
		this.id = id;
		this.name = name;
		this.place = place;
		this.start = start;
		this.end = end;
	}
	
	public Long getId() {
		return id;
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
