package unsw.dungeon;

/**
 * The Class OpenState.
 */
public class OpenState implements DoorState {
	
	/** The door. */
	private Door door;
	
	/**
	 * Instantiates a new open state.
	 *
	 * @param door the door
	 */
	public OpenState(Door door) {
		this.door = door;
	}

	/**
	 * Opens door.
	 */
	@Override
	public void openDoor() {
		
	}

}
