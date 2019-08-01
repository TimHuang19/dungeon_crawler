package unsw.dungeon;

import javafx.scene.image.ImageView;

/**
 * The Class Door.
 */
public class Door extends Entity {
	
	/** The closed state. */
	private DoorState closedState;
	
	/** The open state. */
	private DoorState openState;
	
	/** The actual state. */
	private DoorState state;
	
	/** The id. */
	private int id;
	
	/** The open door view. */
	private ImageView openDoorView;

    /**
     * Instantiates a new door.
     *
	 * @param x 		The x position
	 * @param y 		The y position
     * @param id 		The id
     */
    public Door(int x, int y, int id) {
        super(x, y);
        
        closedState = new ClosedState(this);
        openState = new OpenState(this);
        
        this.id = id;
        state = closedState;
    }
    
    /**
     * Sets the state.
     *
     * @param state 	The new state
     */
    public void setState(DoorState state) {
    	this.state = state;
    }
    
    /**
     * Checks if door is closed.
     *
     * @return true, if it is closed
     */
    public boolean isClosed() {
    	if (state instanceof ClosedState) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Determines if the key matches the door.
     *
     * @param keyId 	The key id
     * @return true, if key and door id match
     */
    public boolean matchingKey(int keyId) {
    	if (keyId == id) {
    		openDoor();
    		return true;
    	}
    	return false;
    }
    
    /**
     * Opens door.
     */
    public void openDoor() {
    	state.openDoor();
    }
    
    /**
     * Gets the open state.
     *
     * @return the open state
     */
    public DoorState getOpenState() {
    	return openState;
    }
    
    /**
     * Adds the open door view.
     *
     * @param openDoorView the open door view
     */
    public void addOpenDoorView(ImageView openDoorView) {
    	this.openDoorView = openDoorView;
    }
    
    /**
     * Gets the open door view.
     *
     * @return the open door view
     */
    public ImageView getOpenDoorView() {
    	return openDoorView;
    }
	
	/**
	 * Checks if is obstacle to a player.
	 *
	 * @param p 	The player
	 * @return true, if door is an obstacle
	 */
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

	/**
	 * Checks if is obstacle to an enemy.
	 *
	 * @param e 	The enemy
	 * @return true, if door is an obstacle
	 */
	@Override
	public boolean isObstacle(Enemy e) {
		if (isClosed()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if a bomb is an obstacle to an enemy using a particular movement strategy
	 *
	 * @param e 		The enemy
	 * @return true, if it is obstacle
	 */
	@Override
	public boolean isObstacle(EnemyMovementStrategy strategy) {
		if (isClosed()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines if door blocks boulder.
	 *
	 * @return true, if it blocks boulder
	 */
	@Override
	public boolean blocksBoulder() {
		if (isClosed()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Pick up.
	 *
	 * @param p 	The player
	 * @return true, if successful
	 */
	@Override
	public boolean pickUp(Player p) {
		return false;
	}

}