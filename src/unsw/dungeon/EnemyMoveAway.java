package unsw.dungeon;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class EnemyMoveAway implements EnemyMovementStrategy {
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static final int UNABLE = 4;
    
	//More complex movement Pattern
	public int enemyMovement(int playerX, int playerY, int enemyX, int enemyY, Dungeon dungeon) {
		int width = dungeon.getWidth();
		int height = dungeon.getHeight();
		int moveDirection = UNABLE;
		boolean[][] visited = new boolean[width][height];
		Queue<Point> q = new LinkedList<>();
		if(isWithinBounds(enemyX+1, enemyY, dungeon) && !isObstacle(enemyX+1, enemyY,dungeon)) {
			q.add(new Point(enemyX+1,enemyY,LEFT));
		}
		if(isWithinBounds(enemyX-1, enemyY, dungeon) && !isObstacle(enemyX-1, enemyY,dungeon)) {
			q.add(new Point(enemyX-1,enemyY,RIGHT));
		}
		if(isWithinBounds(enemyX, enemyY-1, dungeon) && !isObstacle(enemyX, enemyY-1,dungeon)) {
			q.add(new Point(enemyX,enemyY-1,DOWN));
		}
		if(isWithinBounds(enemyX, enemyY+1, dungeon) && !isObstacle(enemyX, enemyY+1,dungeon)) {
			q.add(new Point(enemyX,enemyY+1,UP));
		}
		
		while(q.size()>0) {
			Point b = q.remove();
			if(b.x==playerX && b.y==playerY) {
				moveDirection = b.signature;
				break;
			}
			if(visited[b.x][b.y]) continue;
			visited[b.x][b.y]= true;
			if(isWithinBounds(b.x + 1, b.y,dungeon) && !isObstacle(b.x + 1, b.y,dungeon) && !visited[b.x + 1][b.y]) {
				q.add(new Point(b.x + 1,b.y,b.signature));
			}
			if(isWithinBounds(b.x - 1, b.y,dungeon) && !isObstacle(b.x - 1, b.y,dungeon) && !visited[b.x - 1][b.y]) {
				q.add(new Point(b.x - 1,b.y,b.signature));
			}			
			if(isWithinBounds(b.x, b.y + 1,dungeon) && !isObstacle(b.x, b.y + 1,dungeon) && !visited[b.x][b.y + 1]) {
				q.add(new Point(b.x,b.y + 1,b.signature));
			}			
			if(isWithinBounds(b.x, b.y - 1,dungeon) && !isObstacle(b.x, b.y - 1,dungeon) && !visited[b.x][b.y - 1]) {
				q.add(new Point(b.x,b.y - 1,b.signature));
			}
		}
		if(moveDirection == LEFT) {
			if(isWithinBounds(enemyX-1, enemyY,dungeon) && !isObstacle(enemyX-1, enemyY,dungeon)) {
				moveDirection=LEFT;
			}
			else if(isWithinBounds(enemyX, enemyY-1,dungeon) && !isObstacle(enemyX, enemyY-1,dungeon)) {
				moveDirection=UP;
			}
			else if(isWithinBounds(enemyX, enemyY+1,dungeon) && !isObstacle(enemyX, enemyY+1,dungeon)) {
				moveDirection=DOWN;
			}
			else {
				moveDirection=RIGHT;
			}
		}
		if(moveDirection == RIGHT) {
			if(isWithinBounds(enemyX+1, enemyY,dungeon) && !isObstacle(enemyX+1, enemyY,dungeon)) {
				moveDirection=RIGHT;
			}
			else if(isWithinBounds(enemyX, enemyY+1,dungeon) && !isObstacle(enemyX, enemyY+1,dungeon)) {
				moveDirection=DOWN;
			}
			else if(isWithinBounds(enemyX, enemyY-1,dungeon) && !isObstacle(enemyX, enemyY-1,dungeon)) {
				moveDirection=UP;
			}
			else {
				moveDirection=LEFT;
			}
		}
		if(moveDirection == UP) {
			if(isWithinBounds(enemyX, enemyY-1,dungeon) && !isObstacle(enemyX, enemyY-1,dungeon)) {
				moveDirection=UP;
			}
			else if(isWithinBounds(enemyX+1, enemyY,dungeon) && !isObstacle(enemyX+1, enemyY,dungeon)) {
				moveDirection=RIGHT;
			}
			else if(isWithinBounds(enemyX-1, enemyY,dungeon) && !isObstacle(enemyX-1, enemyY,dungeon)) {
				moveDirection=LEFT;
			}
			else {
				moveDirection=DOWN;
			}
		}
		if(moveDirection == DOWN) {
			if(isWithinBounds(enemyX, enemyY+1,dungeon) && !isObstacle(enemyX, enemyY+1,dungeon)) {
				moveDirection=DOWN;
			}
			else if(isWithinBounds(enemyX-1, enemyY,dungeon) && !isObstacle(enemyX-1, enemyY,dungeon)) {
				moveDirection=LEFT;
			}
			else if(isWithinBounds(enemyX+1, enemyY,dungeon) && !isObstacle(enemyX+1, enemyY,dungeon)) {
				moveDirection=RIGHT;
			}
			else {
				moveDirection=UP;
			}
		}
		return moveDirection;
		
	}
	
    private boolean isObstacle(int targetX, int targetY, Dungeon dungeon) {
    	if(targetX<0 || targetX >= dungeon.getWidth() || targetY < 0 || targetY >= dungeon.getHeight()) {
    		return false;
    	}
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
	    	}
    	}
    	return false;
    }
    
    private boolean isWithinBounds(int targetX, int targetY, Dungeon dungeon) {
    	if(targetX<0 || targetX > dungeon.getWidth()-1 || targetY < 0 || targetY > dungeon.getHeight()) {
    		return false;
    	}
    	return true;
    }
	
    class Point{
    	int x,y,count;
    	int signature;
    	public Point(int x, int y, int signature) {
    		this.x=x;
    		this.y=y;
    		this.signature = signature;
    	}
    }
}

