package com.app.yakstore.domain;

import javax.xml.bind.annotation.XmlAttribute;

public abstract class Yak 
{
	protected String name;
	protected float ageInYears;
	protected String sex;
	protected float ageLastShaved;
	protected int skinsAvailable;
	
	public Yak(){
	}
	
	public Yak(String name, float age, String sex) {
		super();
		this.name = name;
		this.ageInYears = age;
		this.sex = sex;
	}
	
	public abstract float milkProducedOn(int nthDay);
	
	public abstract int getSkinsAvailable(int nthDay);
	
	public abstract int getNoOfDaysToGetReadyForShave();
	
	public String getName() {
		return name;
	}
	
	
	public float getAgeInYears() {
		return ageInYears;
	}

	@XmlAttribute(name="age")
	public void setAgeInYears(float ageInYears) {
		this.ageInYears = ageInYears;
	}

	public String getSex() {
		return sex;
	}

	@XmlAttribute(name="name")
	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name="sex")
	public void setSex(String sex) {
		this.sex= sex;
	}

	public void setAgeLastShaved(float ageLastShaved) {
		this.ageLastShaved = ageLastShaved;
	}

}
