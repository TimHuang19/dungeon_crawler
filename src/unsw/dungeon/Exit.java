package unsw.dungeon;

/**
 * The Class Exit.
 */
public class Exit extends Entity{

	/**
	 * Instantiates a new exit.
	 *
	 * @param x 		The x position
	 * @param y 		The y position
	 */
	public Exit(int x, int y) {
		super(x, y);
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
		return true;
	}

	/**
	 * Checks if a bomb is an obstacle to an enemy using a particular movement strategy
	 *
	 * @param e 		The enemy
	 * @return true, if it is obstacle
	 */
	@Override
	public boolean isObstacle(EnemyMovementStrategy strategy) {
		return true;
	}
	
	/**
	 * Determines if the exit blocks a boulder.
	 *
	 * @return true, if exit blocks the boulder
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
		return false;
	}

}
