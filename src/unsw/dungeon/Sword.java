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
		if (p.getSword() == null) {
			notifyDungeonObservers();
			p.setSword(this);
			return true;
		}
		return false;
	}
}
