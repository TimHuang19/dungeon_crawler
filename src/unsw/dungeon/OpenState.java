package unsw.dungeon;

public class OpenState implements DoorState {
	private Door door;
	
	public OpenState(Door door) {
		this.door = door;
	}

	@Override
	public void openDoor() {
		
	}

}
