package sample;

public class Insurance {

	public Insurance(int id, String name, String companyName, Policy policies) {
		super();
		this.id = id;
		this.name = name;
		this.companyName = companyName;
		this.policies = policies;
	}

	private int id;
	private String name;
	private String companyName;
	Policy policies;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Policy getPolicies() {
		return this.policies;
	}
	public void setPolicies(Policy policies) {
		this.policies = policies;
	}
	
	public int claimPolicy(int cID , int pID) {
		
		int x=0;
		return x;
	}
	
	
}
