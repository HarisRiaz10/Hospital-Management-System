package sample;

import java.time.LocalDate;

public class Patient {
	
	String name;
	String gender;
	int id;
	int age;
	int cost;
	int ph;
	int status;
	
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getPh() {
		return ph;
	}

	public void setPh(int ph) {
		this.ph = ph;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	Patient(String n, String a, int ph,int ag,int c,int p,int s){
		this.name=n;
		this.gender=a;
		this.id=ph;
		this.age=ag;
		this.cost=c;
		this.ph=p;
		this.status=s;
		
	}
	
	void display(){
		System.out.println("Name:"+this.name);
		System.out.println("Gender:"+this.gender);
		System.out.println("Id:"+this.id);
		System.out.println("Age:"+this.age);
	}

}
