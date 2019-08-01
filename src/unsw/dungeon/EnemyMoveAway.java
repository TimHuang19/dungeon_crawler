package unsw.dungeon;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The Class EnemyMoveAway.
 */
public class EnemyMoveAway implements EnemyMovementStrategy {
    
	/**
	 * Instantiates a new enemy move away class.
	 */
	public EnemyMoveAway() {
		
	}
	
	/**
	 * Enemy movement.
	 *
	 * @param playerX the player X position
	 * @param playerY the player Y position
	 * @param enemyX the enemy X position
	 * @param enemyY the enemy Y position
	 * @param dungeon the dungeon
	 * @return the direction the enemy shoudl move toward
	 */
	public Direction enemyMovement(int playerX, int playerY, int enemyX, int enemyY, Dungeon dungeon) {
		int width = dungeon.getWidth();
		int height = dungeon.getHeight();
		Direction moveDirection = Direction.UNABLE;
		boolean[][] visited = new boolean[width][height];
		Queue<Point> q = new LinkedList<>();
		
		if(isWithinBounds(enemyX+1, enemyY, dungeon) && !isObstacle(enemyX+1, enemyY,dungeon)) {
			q.add(new Point(enemyX+1,enemyY,Direction.LEFT));
		}
		
		if(isWithinBounds(enemyX-1, enemyY, dungeon) && !isObstacle(enemyX-1, enemyY,dungeon)) {
			q.add(new Point(enemyX-1,enemyY,Direction.RIGHT));
		}
		
		if(isWithinBounds(enemyX, enemyY-1, dungeon) && !isObstacle(enemyX, enemyY-1,dungeon)) {
			q.add(new Point(enemyX,enemyY-1,Direction.DOWN));
		}
		
		if(isWithinBounds(enemyX, enemyY+1, dungeon) && !isObstacle(enemyX, enemyY+1,dungeon)) {
			q.add(new Point(enemyX,enemyY+1,Direction.UP));
		}
		
		while(q.size()>0) {
			Point b = q.remove();
			
			if(b.getX() == playerX && b.getY() == playerY) {
				moveDirection = b.getSignature();
				break;
			}
			
			if (visited[b.getX()][b.getY()]) continue;
			
			visited[b.getX()][b.getY()]= true;
			
			if (isWithinBounds(b.getX() + 1, b.getY(),dungeon) && !isObstacle(b.getX() + 1, b.getY(),dungeon) && !visited[b.getX() + 1][b.getY()]) {
				q.add(new Point(b.getX() + 1,b.getY(),b.getSignature()));
			}
			
			if (isWithinBounds(b.getX() - 1, b.getY(),dungeon) && !isObstacle(b.getX() - 1, b.getY(),dungeon) && !visited[b.getX() - 1][b.getY()]) {
				q.add(new Point(b.getX() - 1,b.getY(),b.getSignature()));
			}	
			
			if (isWithinBounds(b.getX(), b.getY() + 1,dungeon) && !isObstacle(b.getX(), b.getY() + 1,dungeon) && !visited[b.getX()][b.getY() + 1]) {
				q.add(new Point(b.getX(),b.getY() + 1,b.getSignature()));
			}	
			
			if (isWithinBounds(b.getX(), b.getY() - 1,dungeon) && !isObstacle(b.getX(), b.getY() - 1,dungeon) && !visited[b.getX()][b.getY() - 1]) {
				q.add(new Point(b.getX(),b.getY() - 1,b.getSignature()));
			}
		}
		
		if (moveDirection == Direction.LEFT) {
			if (isWithinBounds(enemyX-1, enemyY,dungeon) && !isObstacle(enemyX-1, enemyY,dungeon)) {
				moveDirection = Direction.LEFT;
			} else if(isWithinBounds(enemyX, enemyY-1,dungeon) && !isObstacle(enemyX, enemyY-1,dungeon)) {
				moveDirection = Direction.UP;
			} else if(isWithinBounds(enemyX, enemyY+1,dungeon) && !isObstacle(enemyX, enemyY+1,dungeon)) {
				moveDirection = Direction.DOWN;
			} else {
				moveDirection = Direction.RIGHT;
			}
		}
		
		if(moveDirection == Direction.RIGHT) {
			if (isWithinBounds(enemyX+1, enemyY,dungeon) && !isObstacle(enemyX+1, enemyY,dungeon)) {
				moveDirection = Direction.RIGHT;
			} else if(isWithinBounds(enemyX, enemyY+1,dungeon) && !isObstacle(enemyX, enemyY+1,dungeon)) {
				moveDirection = Direction.DOWN;
			} else if(isWithinBounds(enemyX, enemyY-1,dungeon) && !isObstacle(enemyX, enemyY-1,dungeon)) {
				moveDirection = Direction.UP;
			} else {
				moveDirection = Direction.LEFT;
			}
		}
		
		if (moveDirection == Direction.UP) {
			if (isWithinBounds(enemyX, enemyY-1,dungeon) && !isObstacle(enemyX, enemyY-1,dungeon)) {
				moveDirection = Direction.UP;
			} else if(isWithinBounds(enemyX+1, enemyY,dungeon) && !isObstacle(enemyX+1, enemyY,dungeon)) {
				moveDirection = Direction.RIGHT;
			} else if(isWithinBounds(enemyX-1, enemyY,dungeon) && !isObstacle(enemyX-1, enemyY,dungeon)) {
				moveDirection = Direction.LEFT;
			} else {
				moveDirection = Direction.DOWN;
			}
		}
		
		if (moveDirection == Direction.DOWN) {
			if(isWithinBounds(enemyX, enemyY+1,dungeon) && !isObstacle(enemyX, enemyY+1,dungeon)) {
				moveDirection = Direction.DOWN;
			} else if(isWithinBounds(enemyX-1, enemyY,dungeon) && !isObstacle(enemyX-1, enemyY,dungeon)) {
				moveDirection = Direction.LEFT;
			} else if(isWithinBounds(enemyX+1, enemyY,dungeon) && !isObstacle(enemyX+1, enemyY,dungeon)) {
				moveDirection = Direction.RIGHT;
			} else {
				moveDirection = Direction.UP;
			}
		}
		return moveDirection;
		
	}
	
    /**
     * Checks if is obstacle.
     *
     * @param targetX the target X position
     * @param targetY the target Y position
     * @param dungeon the dungeon
     * @return true, if is obstacle
     */
    private boolean isObstacle(int targetX, int targetY, Dungeon dungeon) {
    	if(targetX<0 || targetX >= dungeon.getWidth() || targetY < 0 || targetY >= dungeon.getHeight()) {
    		return false;
    	}
    	ArrayList<Entity> entities = dungeon.getEntities(targetX, targetY);
    	boolean obstacle = false;
    	for (Entity e : entities) {
    		obstacle = obstacle || e.isObstacle(this);
    	}
    	return obstacle;
    }
    
    /**
     * Checks if is within bounds.
     *
     * @param targetX the target X position
     * @param targetY the target Y position
     * @param dungeon the dungeon
     * @return true, if is within bounds
     */
    private boolean isWithinBounds(int targetX, int targetY, Dungeon dungeon) {
    	if(targetX<0 || targetX > dungeon.getWidth()-1 || targetY < 0 || targetY > dungeon.getHeight()-1) {
    		return false;
    	}
    	return true;
    }
    
    /**
     * The Helper Class Point.
     */
    private class Point {
    	
	    /** The x position. */
	    private int x;
    	
	    /** The y position. */
	    private int y;
    	
	    /** The signature, the first move traversed in a path */
	    private Direction signature;
    	
    	/**
	     * Instantiates a new point.
	     *
	     * @param x the x position
	     * @param y the y position
	     * @param signature the signature
	     */
	    public Point(int x, int y, Direction signature) {
    		this.x = x;
    		this.y = y;
    		this.signature = signature;
    	}
    	
    	/**
	     * Gets the x position.
	     *
	     * @return the x
	     */
	    public int getX() {
    		return x;
    	}
    	
    	/**
	     * Gets the y position.
	     *
	     * @return the y
	     */
	    public int getY() {
    		return y;
    	}
    	
    	/**
	     * Gets the signature.
	     *
	     * @return the signature
	     */
	    public Direction getSignature() {
    		return this.signature;
    	}
    }

}

