package unsw.dungeon;

public class Treasure extends Entity {

	public Treasure(int x, int y) {
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
		return true;
	}

	@Override
	public boolean pickUp(Player p) {
		p.reduceTreasures();
		return true;
	}

}
