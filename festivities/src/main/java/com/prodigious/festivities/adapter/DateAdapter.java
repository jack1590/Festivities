package com.prodigious.festivities.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date>{
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

	@Override
	public Date unmarshal(String v) throws Exception {
		return sf.parse(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		return sf.format(v);
	}

}
