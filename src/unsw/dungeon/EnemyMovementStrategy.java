package unsw.dungeon;

/**
 * The Interface EnemyMovementStrategy.
 */
public interface EnemyMovementStrategy {
	
	/**
	 * Enemy movement.
	 *
	 * @param playerX the player X
	 * @param playerY the player Y
	 * @param enemyX the enemy X
	 * @param enemyY the enemy Y
	 * @param dungeon the dungeon
	 * @return the direction
	 */
	public Direction enemyMovement(int playerX, int playerY, int enemyX, int enemyY, Dungeon dungeon);
}
