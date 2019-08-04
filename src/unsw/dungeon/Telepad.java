package unsw.dungeon;

import java.util.ArrayList;

/**
 * The Class Telepad.
 */
public class Telepad extends Entity{
	
	/** The id. */
	private int id;
	
	/** The dungeon. */
	private Dungeon dungeon;

	/**
	 * Instantiates a new telepad.
	 *
	 * @param x 		the x position
	 * @param y 		the y position
	 * @param dungeon 	the dungeon
	 * @param id 		the id
	 */
	public Telepad(int x, int y, Dungeon dungeon, int id) {
		super(x, y);
		this.dungeon = dungeon;
		this.id = id;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Checks if is obstacle to player.
	 *
	 * @param p the p
	 * @return true, if is obstacle
	 */
	@Override
	public boolean isObstacle(Player p) {
		return false;
	}

	/**
	 * Checks if is obstacle to enemy.
	 *
	 * @param e the e
	 * @return true, if is obstacle
	 */
	@Override
	public boolean isObstacle(Enemy e) {
		return false;
	}

	/**
	 * Checks if is obstacle to an enemy using a particular movement strategy.
	 *
	 * @param strategy 		the strategy
	 * @return true, if is obstacle
	 */
	@Override
	public boolean isObstacle(EnemyMovementStrategy strategy) {
		return false;
	}

	/**
	 * Checks if this entity blocks boulder
	 *
	 * @return true, if telepad blocks boulder
	 */
	@Override
	public boolean blocksBoulder() {
		return false;
	}
	
	/**
	 * Pick up.
	 *
	 * @param p the player
	 * @return true, if successful
	 */
	@Override
	public boolean pickUp(Player p) {
		return false;
	}
	
	/**
	 * Teleport.
	 *
	 * @param p the player
	 */
	public void teleport(Player p) {
		Telepad matchingTelepad = dungeon.getMatchingTelepad(this);
		if (matchingTelepad == null) {
			return;
		}
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
