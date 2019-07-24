package unsw.dungeon;

public class Switch extends Entity {

	public Switch(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean isObstacle(Player p) {
		return false;
	}

	@Override
	public boolean isObstacle(Enemy e) {
		return false;
	}

	@Override
	public boolean blocksBoulder() {
		return false;
	}

	@Override
	public boolean pickUp(Player p) {
		return false;
	}

}
