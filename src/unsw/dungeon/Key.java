package unsw.dungeon;

public class Key extends Entity {
	private int id;
	
	public Key(int x, int y, int id) {
		super(x, y);
		this.id = id;
	}
	
	public int getId() {
		return this.id;
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
		int oldKeyId = p.getKeyId();
		if (oldKeyId == -1) {
			p.setKeyId(id);
			return true;
		} else {
			p.setKeyId(id);
			id = oldKeyId;
			return false;
		}
	}

}