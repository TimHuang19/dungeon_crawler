package unsw.dungeon;

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class EnemyMoveToward implements EnemyMovementStrategy {
	
	//More complex movement Pattern
	public String enemyMovement(int playerX, int playerY, int enemyX, int enemyY, Dungeon dungeon) {
		int width = dungeon.getWidth();
		int height = dungeon.getHeight();
		String moveDirection="unable";
		Boolean[][] visited = new Boolean[width][height];
		Queue<Point> q = new LinkedList<>();
		if(!isObstacle(playerX+1, playerY,dungeon)) {
			q.add(new Point(playerX+1,playerY,"right"));
		}
		if(!isObstacle(playerX-1, playerY,dungeon)) {
			q.add(new Point(playerX-1,playerY,"left"));
		}
		if(!isObstacle(playerX, playerY-1,dungeon)) {
			q.add(new Point(playerX,playerY-1,"up"));
		}
		if(!isObstacle(playerX, playerY+1,dungeon)) {
			q.add(new Point(playerX,playerY+1,"down"));
		}
		while(q.size()>0) {
			Point a;
			Point b = q.remove();
			if(b.x==playerX && b.y==playerY) {
				moveDirection = b.signature;
				break;
			}
			if(visited[b.x][b.y]) continue;
			visited[b.x][b.y]= true;
			if(!isObstacle(b.x + 1, b.y,dungeon) && visited[b.x + 1][b.y]) {
				q.add(new Point(b.x + 1,b.y,b.signature));
			}
			if(!isObstacle(b.x - 1, b.y,dungeon) && visited[b.x - 1][b.y]) {
				q.add(new Point(b.x - 1,b.y,b.signature));
			}			
			if(!isObstacle(b.x, b.y + 1,dungeon) && visited[b.x][b.y + 1]) {
				q.add(new Point(b.x,b.y + 1,b.signature));
			}			
			if(!isObstacle(b.x, b.y - 1,dungeon) && visited[b.x][b.y - 1]) {
				q.add(new Point(b.x,b.y - 1,b.signature));
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
	
    class Point{
    	int x,y,count;
    	String signature;
    	public Point(int x, int y, String signature) {
    		this.x=x;
    		this.y=y;
    	}
    }
}