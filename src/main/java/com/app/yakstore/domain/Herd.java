package com.app.yakstore.domain;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Herd 
{
	private List<LabYak> yaks;

	public List<LabYak> getYaks() {
		return yaks;
	}


	@Override
	public String toString() {
		return "Herd [yaks=" + yaks + "]";
	}
	
   
	@XmlElement(name="labyak")
	public void setLabYak(LabYak yak){
		if(yaks == null){
			yaks = new LinkedList<LabYak>();
		}
		yaks.add(yak);
	}
   
	
}
