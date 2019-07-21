package unsw.dungeon;

public class Sword extends Entity {
	private int swings;
	
	public Sword(int x, int y) {
		super(x, y);
		swings = 5;
	}
	
	public boolean swing() {
		swings--;
		if (swings == 0) {
			return false;
		}
		return true;
	}
}
