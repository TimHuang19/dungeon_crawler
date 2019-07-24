package unsw.dungeon;

public class Door extends Entity {
	private DoorState closedState;
	private DoorState openState;
	
	private DoorState state;
	private int id;

    public Door(int x, int y, int id) {
        super(x, y);
        
        closedState = new ClosedState(this);
        openState = new OpenState(this);
        
        this.id = id;
        state = closedState;
    }
    
    public void setState(DoorState state) {
    	this.state = state;
    }
    
    public boolean isClosed() {
    	if (state instanceof ClosedState) {
    		return true;
    	}
    	return false;
    }
    
    public boolean matchingKey(Key key) {
    	if (key.getId() == id) {
    		openDoor();
    		return true;
    	}
    	return false;
    }
    
    public void openDoor() {
    	state.openDoor();
    }
    
    public DoorState getOpenState() {
    	return openState;
    }

	@Override
	public boolean isObstacle(Player p) {
		Key key = p.getKey();
		if (key != null && matchingKey(key) && isClosed()) {
			p.setKey(null);
			return false;
		}
		if (isClosed()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isObstacle(Enemy e) {
		if (isClosed()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean blocksBoulder() {
		if (isClosed()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean pickUp(Player p) {
		return false;
	}

}