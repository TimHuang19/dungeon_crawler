package unsw.dungeon;

public interface EnemyMovementStrategy {
	public Direction enemyMovement(int playerX, int playerY, int enemyX, int enemyY, Dungeon dungeon);
}
