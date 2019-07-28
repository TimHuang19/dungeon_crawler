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
		Key oldKey = p.getKey();
		notifyDungeonObservers();
		if (oldKey == null) {
			p.setKey(this);
		} else {
			oldKey.x().set(p.getX());
			oldKey.y().set(p.getY());
			p.addEntity(oldKey);
			oldKey.notifyDungeonObservers();
			p.setKey(this);
		}
		return true;
	}

}