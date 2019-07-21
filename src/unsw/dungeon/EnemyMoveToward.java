package unsw.dungeon;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class EnemyMoveToward implements EnemyMovementStrategy {
	
	public EnemyMoveToward() {
		
	}
    
	public Direction enemyMovement(int playerX, int playerY, int enemyX, int enemyY, Dungeon dungeon) {
		int width = dungeon.getWidth();
		int height = dungeon.getHeight();
		Direction moveDirection = Direction.UNABLE;
		boolean[][] visited = new boolean[width][height];
		
		Queue<Point> q = new LinkedList<>();
		
		if(isWithinBounds(enemyX+1, enemyY, dungeon) && !isObstacle(enemyX+1, enemyY,dungeon)) {

			q.add(new Point(enemyX+1,enemyY,Direction.RIGHT));
		}
		
		if(isWithinBounds(enemyX-1, enemyY, dungeon) && !isObstacle(enemyX-1, enemyY,dungeon)) {

			q.add(new Point(enemyX-1,enemyY,Direction.LEFT));
		}
		
		if(isWithinBounds(enemyX, enemyY-1, dungeon) && !isObstacle(enemyX, enemyY-1,dungeon)) {

			q.add(new Point(enemyX,enemyY-1,Direction.UP));
		}
		
		if(isWithinBounds(enemyX, enemyY+1, dungeon) && !isObstacle(enemyX, enemyY+1,dungeon)) {

			q.add(new Point(enemyX,enemyY+1,Direction.DOWN));
		}
		
		while(q.size()>0) {
			Point b = q.remove();

			if(b.getX() == playerX && b.getY() == playerY) {
				moveDirection = b.getSignature();
				break;
			}
			
			if(visited[b.getX()][b.getY()]) continue;
			
			visited[b.getX()][b.getY()]= true;
			
			if(isWithinBounds(b.getX() + 1, b.getY(),dungeon) && !isObstacle(b.getX() + 1, b.getY(),dungeon) && !visited[b.getX() + 1][b.getY()]) {
				q.add(new Point(b.getX() + 1,b.getY(),b.getSignature()));
			}
			
			if(isWithinBounds(b.getX() - 1, b.getY(),dungeon) && !isObstacle(b.getX() - 1, b.getY(),dungeon) && !visited[b.getX() - 1][b.getY()]) {
				q.add(new Point(b.getX() - 1,b.getY(),b.getSignature()));
			}			
			
			if(isWithinBounds(b.getX(), b.getY() + 1,dungeon) && !isObstacle(b.getX(), b.getY() + 1,dungeon) && !visited[b.getX()][b.getY() + 1]) {
				q.add(new Point(b.getX(),b.getY() + 1,b.getSignature()));
			}		
			
			if(isWithinBounds(b.getX(), b.getY() - 1,dungeon) && !isObstacle(b.getX(), b.getY() - 1,dungeon) && !visited[b.getX()][b.getY() - 1]) {
				q.add(new Point(b.getX(),b.getY() - 1,b.getSignature()));
			}
		}
		
		return moveDirection;
		
	}
	
    private boolean isObstacle(int targetX, int targetY, Dungeon dungeon) {
    	ArrayList<Entity> entities = dungeon.getEntities(targetX, targetY);
    	for (Entity e : entities) {
	    	if (e instanceof Wall) {
	    		return true;
	    	} else if (e instanceof Door) {
	    		Door d = (Door) e;
	    		if (d.isClosed()) {
	    			return true;
	    		}
	    	} else if (e instanceof Boulder) {
	    		return true;
	    	}else if (e instanceof Enemy) {
	    		return true;
	    	}
    	}
    	return false;
    }
    
    private boolean isWithinBounds(int targetX, int targetY, Dungeon dungeon) {
    	if(targetX<0 || targetX > dungeon.getWidth()-1 || targetY < 0 || targetY > dungeon.getHeight()-1) {
    		return false;
    	}
    	return true;
    }
    
    private class Point {
    	private int x;
    	private int y;
    	private Direction signature;
    	
    	public Point(int x, int y, Direction signature) {
    		this.x = x;
    		this.y = y;
    		this.signature = signature;
    	}
    	
    	public int getX() {
    		return x;
    	}
    	
    	public int getY() {
    		return y;
    	}
    	
    	public Direction getSignature() {
    		return this.signature;
    	}
    }
	
}