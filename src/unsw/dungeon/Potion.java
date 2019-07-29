package unsw.dungeon;

public class Potion extends Entity {

	public Potion(int x, int y) {
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
		p.setInvincible(true);
		p.setInvincibleSteps(20);
		return true;
	}

}
