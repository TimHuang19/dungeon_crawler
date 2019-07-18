package unsw.dungeon;

public class Boulder extends Entity {

	public Boulder(int x, int y) {
		super(x, y);
	}
	
	public void moveBoulder(int x, int y) {
		x().set(x);
		y().set(y);
	}
}
