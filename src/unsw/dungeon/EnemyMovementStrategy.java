package unsw.dungeon;

public interface EnemyMovementStrategy {
	public int enemyMovement(int playerX, int playerY, int enemyX, int enemyY, Dungeon dungeon);
}
