package unsw.dungeon;

public class Door extends Entity {
	DoorState closedState;
	DoorState openState;
	
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
}
