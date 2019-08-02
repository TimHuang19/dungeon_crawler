package unsw.dungeon;

/**
 * The Class Sword.
 */
public class Sword extends Entity {
	
	/** The number of swing left on the sword. */
	private int swings;
	
	/**
	 * Instantiates a new sword.
	 *
	 * @param x 		The x position
	 * @param y 		The y position
	 */
	public Sword(int x, int y) {
		super(x, y);
		swings = 5;
	}
	
	public int getSwings() {
		return swings;
	}
	
	/**
	 * Swing the sword.
	 *
	 * @return true, if successful
	 */
	public boolean swing() {
		swings--;
		if (swings == 0) {
			return false;
		}
		return true;
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
	 * Determines if the sword blocks a boulder.
	 *
	 * @return true, if sword blocks the boulder
	 */
	@Override
	public boolean blocksBoulder() {
		return true;
	}

	/**
	 * Pick up.
	 *
	 * @param p 	The player
	 * @return true, if successful
	 */
	@Override
	public boolean pickUp(Player p) {
		if (p.getSword() == null) {
			p.setSword(this);
			return true;
		}
		return false;
	}
}
