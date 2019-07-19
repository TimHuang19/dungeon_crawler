package unsw.dungeon;

public interface EnemyMovementStrategy {
	public String enemyMovement(int playerX, int playerY, int enemyX, int enemyY, Dungeon dungeon);
}
