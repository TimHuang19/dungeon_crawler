package unsw.dungeon;

public class ClosedState implements DoorState {
	Door door;
	
	public ClosedState(Door door) {
		this.door = door;
	}

	@Override
	public void openDoor() {
		door.setState(door.getOpenState());
	}

}
