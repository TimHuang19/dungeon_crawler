package unsw.dungeon;

public class Telepad extends Entity{
	
	private int id;
	
	private Dungeon dungeon;

	public Telepad(int x, int y, Dungeon dungeon, int id) {
		super(x, y);
		this.dungeon = dungeon;
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
	public boolean isObstacle(EnemyMovementStrategy strategy) {
		return false;
	}

	@Override
	public boolean blocksBoulder() {
		return false;
	}

	@Override
	public boolean pickUp(Player p) {
		Telepad matchingTelepad = dungeon.getMatchingTelepad(this);
		
		p.x().set(matchingTelepad.getX());
		p.y().set(matchingTelepad.getY());
		return false;
	}

}
