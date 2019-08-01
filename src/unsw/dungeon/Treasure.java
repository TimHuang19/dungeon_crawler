package unsw.dungeon;

/**
 * The Class Treasure.
 */
public class Treasure extends Entity {

	/**
	 * Instantiates a new treasure.
	 *
	 * @param x 		The x position
	 * @param y 		The y position
	 */
	public Treasure(int x, int y) {
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
	 * Checks if is obstacle to a enemy.
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
	 * Determines if the treasure blocks a boulder.
	 *
	 * @return true, if treasure blocks the boulder
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
		notifyDungeonObservers();
		p.reduceTreasures();
		return true;
	}

}
