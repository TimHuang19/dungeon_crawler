package unsw.dungeon;

/**
 * The Class ClosedState.
 */
public class ClosedState implements DoorState {
	
	/** The door. */
	private Door door;
	
	/**
	 * Instantiates a new closed state.
	 *
	 * @param door 	The door
	 */
	public ClosedState(Door door) {
		this.door = door;
	}

	/**
	 * Opens door.
	 */
	@Override
	public void openDoor() {
		door.setState(door.getOpenState());
	}

}
