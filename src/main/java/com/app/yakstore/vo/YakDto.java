package com.app.yakstore.vo;

public class YakDto 
{
	private String name;
	private float age;
	private float ageLastShaved;
	private int noOfDaysToReadyForShave;
	
	private int noOfDaysInYakYear = 100;
	
	public YakDto(String name, float age, float ageLastShaved, int noOfDaysToReadyForShave) {
		super();
		this.name = name;
		this.age = age;
		this.ageLastShaved = ageLastShaved;
		this.noOfDaysToReadyForShave = noOfDaysToReadyForShave;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getAge() {
		return age;
	}
	public void setAge(float age) {
		this.age = age;
	}
	public float getAgeLastShaved() {
		return ageLastShaved;
	}
	public void setAgeLastShaved(float ageLastShaved) {
		this.ageLastShaved = ageLastShaved;
	}
	
	public void addDaysInAge(int noOfDaysToAdd)
	{
		float noOfDaysToBeAdded = noOfDaysToAdd;
		this.age = age +(noOfDaysToBeAdded/noOfDaysInYakYear);
		int noOfShaves = (int)(noOfDaysToBeAdded/(noOfDaysToReadyForShave+1));
		this.ageLastShaved = ageLastShaved +((noOfShaves * noOfDaysToReadyForShave)/noOfDaysInYakYear);
	}
	
	@Override
	public String toString() {
		return "YakDto [name=" + name + ", age=" + age + ", ageLastShaved="
				+ ageLastShaved + "]";
	}
	
	
	
}
