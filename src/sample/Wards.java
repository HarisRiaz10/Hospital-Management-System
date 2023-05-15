package sample;

public class Wards {
	

    private int id;
    private String type;
    private String name;
    private int room;
    public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	private int cost;
    
    

    public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public Wards(int id, String type, String name, int r,int c) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.room=r;
        this.cost=c;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void decR() {
    	this.room--;
    }

}
