package unsw.dungeon;

/**
 * The Class Key.
 */
public class Key extends Entity {
	
	/** The id. */
	private int id;
	
	/**
	 * Instantiates a new key.
	 *
	 * @param x 		The x position
	 * @param y 		The y position
	 * @param id 		The id
	 */
	public Key(int x, int y, int id) {
		super(x, y);
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
	 * Checks if is obstacle to a player.
	 *
	 * @param p 	The player
	 * @return true, if it is an obstacle to the player
	 */
	@Override
	public boolean isObstacle(Player p) {
		return false;
	}

	/**
	 * Checks if is obstacle to an enemy.
	 *
	 * @param e 	The enemy
	 * @return true, if it is obstacle to an enemy
	 */
	@Override
	public boolean isObstacle(Enemy e) {
		return false;
	}

	
	/**
	 * Checks if a bomb is an obstacle to an enemy using a particular movement strategy
	 *
	 * @param e 		The enemy
	 * @return true, if it is obstacle
	 */
	@Override
	public boolean isObstacle(EnemyMovementStrategy strategy) {
		return false;
	}
	
	/**
	 * Determines if the key blocks a boulder.
	 *
	 * @return true, if key blocks the boulder
	 */
	@Override
	public boolean blocksBoulder() {
		return true;
	}

	/**
	 * Pick up.
	 *
	 * @param p 		The player
	 * @return true, if successful
	 */
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