package sample;

public class fin {

	public String Id;
	public String name;
	public String com;
	public int percentage;
	public boolean insurance;
	
	public fin(String id, String name, String com, int percentage, boolean insurance) {
		super();
		Id = id;
		this.name = name;
		this.com = com;
		this.percentage = percentage;
		this.insurance = insurance;
	}
	
	
	
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public boolean isInsurance() {
		return insurance;
	}
	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}
	
	
}
