package com.app.yakstore.domain;

import javax.xml.bind.annotation.XmlType;


@XmlType(name = "labyak")
public class LabYak extends Yak
{
	 
	
	
	public LabYak(){
		super();
	}
	
	
	public LabYak(String name, float age, String sex) {
		super(name,age, sex);
		this.name = name;
		this.ageInYears = age;
		this.sex = sex;
	}
	
	
	
	public float milkProducedOn(int nthDay)
	{
		float milkQuantity = 0;
		
		for(int day=0;day<nthDay;day++)
		{
			int ageInDays =(int)(ageInYears * 100);
			milkQuantity += (float)(50 - ((ageInDays+day) * 0.03));
		}
		
		return milkQuantity; 
	}
	
	
	public int getSkinsAvailable(int nthDay)
	{
		float recurringShavingInterval = 8 + (ageInYears * 100 * 0.01f);
		return (int)((nthDay-2)/recurringShavingInterval) + 1;
	}

	
	public int getNoOfDaysToGetReadyForShave()
	{
		return  (int)(8 + (ageInYears * 100 * 0.01f));
	}

	@Override
	public String toString() {
		return "LabYak [ name=" + name
				+ ", age=" + ageInYears + ", sex=" + sex + "]";
	}
	
	
	
}


/*public Milk milkProducedOn(int nthDay)
{
	float milkQuantity = (float)(50 - (ageInDays * 0.03));
	Milk milkProduced = new Milk(milkQuantity,MilkUnitEnum.LITER);
	return milkProduced; 
}*/
