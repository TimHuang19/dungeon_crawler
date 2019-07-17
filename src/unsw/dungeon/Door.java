package unsw.dungeon;

public class Door extends Entity {
	DoorState closedState;
	DoorState openState;
	
	private DoorState state;

    public Door(int x, int y) {
        super(x, y);
        
        closedState = new ClosedState(this);
        openState = new OpenState(this);
        
        state = closedState;
    }
    
    public boolean isClosed() {
    	if (state instanceof ClosedState) {
    		return true;
    	}
    	return false;
    }

}
