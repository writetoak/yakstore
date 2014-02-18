package com.app.yakstore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import com.app.yakstore.domain.Herd;
import com.app.yakstore.domain.Yak;

public class XmlParser 
{
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	
	public Object convertFromXMLToObject(String xmlfile,boolean isAbsolutePath) throws Exception {
		 
		InputStream is = null;
		
		try 
		{
			if(isAbsolutePath)
			{
				is = new FileInputStream(xmlfile);
			}
			else
			{
				 is = getClass().getResourceAsStream(xmlfile);
			}
			
			return getUnmarshaller().unmarshal(new StreamSource(is));
		} 
		finally {
			if (is != null) {
				is.close();
			}
		}
	}


	public Marshaller getMarshaller() {
		return marshaller;
	}


	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}


	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}


	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
	
	
	
	public static void main(String[] args) throws Exception
	{
		//String XML_FILE_NAME = "E:\\amitk\\stsworkspace\\yakstore\\target\\classes\\herd.xml";
		
		
		if(args==null || args.length !=2)
		{
			throw new IllegalArgumentException("Two arguments are required, fileName space day");
		}
		
		
		String fileName = args[0];
		int nthDay = 0;
		
		File file = new File(fileName);
		
		if(!file.exists()){
			throw new IllegalArgumentException("Xml file to parse does not Exists.");
		}
		
		
		String nthDayStr = args[1];
		
		try
		{
			nthDay = Integer.parseInt(nthDayStr);
		}
		catch(NumberFormatException e)
		{
			throw new IllegalArgumentException("2nd argument should be integer.");
		}
		
		System.out.println("fileName="+fileName+", nthDay="+nthDay);
		
		
		ApplicationContext appContext = new ClassPathXmlApplicationContext("xml-config.xml");
		XmlParser converter = (XmlParser) appContext.getBean("XmlParser");
		Herd herd = (Herd)converter.convertFromXMLToObject(fileName,true);
		
		StringBuilder herdPrint = new StringBuilder(200);
		StringBuilder stockPrint = new StringBuilder(200);
		float ageOnNthDay=0.0f;
		float milkStockOnNthDay=0.0f;
		int noOfSkins = 0;
		
		for(Yak yak:herd.getYaks())
		{
			System.out.println(yak);
			ageOnNthDay = yak.getAgeInYears() + (nthDay/100);
			herdPrint.append(yak.getName()).append(" ").append(ageOnNthDay).append(" years old").append(System.lineSeparator());
			
			milkStockOnNthDay += yak.milkProducedOn(nthDay); 
			noOfSkins += yak.getSkinsAvailable(nthDay);
		}
		
		stockPrint.append(milkStockOnNthDay).append(" liters of milk").append(System.lineSeparator()).append(noOfSkins).append(" skins of wool");
		
		System.out.println("In Stock:");
		System.out.println(stockPrint);
		System.out.println("Herd:");
		System.out.println(herdPrint);
	}
	
	
}
