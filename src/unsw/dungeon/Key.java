package unsw.dungeon;

public class Key extends Entity {
	private int id;
	
	public Key(int x, int y, int id) {
		super(x, y);
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if(obj instanceof Key) {
			Key k = (Key) obj;
			
			return id == k.getId(); 
		}
		return false;
	}

}
