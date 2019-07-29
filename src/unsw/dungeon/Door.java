package unsw.dungeon;

import javafx.scene.image.ImageView;

public class Door extends Entity {
	private DoorState closedState;
	private DoorState openState;
	
	private DoorState state;
	private int id;
	private ImageView openDoorView;

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
    
    public boolean matchingKey(int keyId) {
    	if (keyId == id) {
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
    
    public void addOpenDoorView(ImageView openDoorView) {
    	this.openDoorView = openDoorView;
    }
    
    public ImageView getOpenDoorView() {
    	return openDoorView;
    }
	@Override
	public boolean isObstacle(Player p) {
		int keyId = p.getKeyId();
		if (keyId != -1 && isClosed() && matchingKey(keyId)) {
			p.setKeyId(-1);
			notifyDungeonObservers();
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