package unsw.dungeon;

import java.util.ArrayList;

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
		return false;
	}
	
	public void teleport(Player p) {
		Telepad matchingTelepad = dungeon.getMatchingTelepad(this);
		int targetX = matchingTelepad.getX();
		int targetY = matchingTelepad.getY();
		
		ArrayList<Entity> entities = dungeon.getEntities(targetX, targetY);
		
		boolean canTeleport = true;
		
		for (Entity e : entities) {
			if (e.isObstacle(p)) {
				canTeleport = false;
			}
		}
		
		if (canTeleport) {
			p.x().set(targetX);
			p.y().set(targetY);
			p.notifyObservers();
		}
	}

}
