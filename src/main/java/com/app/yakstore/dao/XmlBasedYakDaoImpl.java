package com.app.yakstore.dao;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.yakstore.domain.Herd;
import com.app.yakstore.domain.Yak;
import com.app.yakstore.util.XmlParser;

@Repository
public class XmlBasedYakDaoImpl implements YakDao
{
	private List yaksList;
	
	@Autowired
	private XmlParser parser;
	
	@PostConstruct
	public void parseXmlFile()
	{
		try
		{
			Herd herd = (Herd)parser.convertFromXMLToObject("/herd.xml",false);
			yaksList = herd.getYaks();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Exception while parsing xml file", e);
		}
	}
	
	public List<Yak> getAllYaksOnDayZero()
	{
		return Collections.unmodifiableList(yaksList);
	}


	public XmlParser getParser() {
		return parser;
	}


	public void setParser(XmlParser parser) {
		this.parser = parser;
	}
	
	
	
}
