package unsw.dungeon;

/**
 * The Class Wall.
 */
public class Wall extends Entity {

    /**
     * Instantiates a new wall.
     *
	 * @param x 		The x position
	 * @param y 		The y position
     */
    public Wall(int x, int y) {
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
		return true;
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
	 * Determines if the wall blocks a boulder.
	 *
	 * @return true, if wall blocks the boulder
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
