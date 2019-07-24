package unsw.dungeon;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }

	@Override
	public boolean isObstacle(Player p) {
		return true;
	}

	@Override
	public boolean isObstacle(Enemy e) {
		return true;
	}

	@Override
	public boolean blocksBoulder() {
		return true;
	}

	@Override
	public boolean pickUp(Player p) {
		return false;
	}

}
