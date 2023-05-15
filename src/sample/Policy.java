package sample;

public class Policy {
	
	int id;
	String title;
	String description;
	int percentage;
	public int getId() {
		return id;
	}
	public Policy(int id, String title, String description, int percentage) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.percentage = percentage;
	}
	public Policy() {
		super();
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

}
