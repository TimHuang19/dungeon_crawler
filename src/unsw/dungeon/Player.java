package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Key key;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void moveUp() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() - 1);

        if (getY() > 0 && !isObstacle(entities)) {
            y().set(getY() - 1);
            
        }
    }

    public void moveDown() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() + 1);

        if (getY() < dungeon.getHeight() - 1 && !isObstacle(entities)) {
            y().set(getY() + 1);
        }
    }

    public void moveLeft() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX() - 1, getY());

        if (getX() > 0 && !isObstacle(entities)) {
            x().set(getX() - 1);
        }
    }

    public void moveRight() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX() + 1, getY());
    	
        if (getX() < dungeon.getWidth() - 1 && !isObstacle(entities)) {
            x().set(getX() + 1);
        }
    }
    
    private boolean isObstacle(ArrayList<Entity> entities) {
    	for (Entity e : entities) {
	    	if (e instanceof Wall) {
	    		return true;
	    	} else if (e instanceof Door) {
	    		Door d = (Door) e;
	    		if (d.matchingKey(key) && d.isClosed()) {
	    			key = null;
	    			return false;
	    		} else if (d.isClosed()) {
	    			return true;
	    		}
	    	} else if (e instanceof Boulder) {
	    		Boulder b = (Boulder) e;
	    		
	    		if (!blockedBoulder(b)) {
	    			return false;
	    		}
	    		
	    		return true;
	    	}
    	}
    	return false;
    }
    
    private boolean blockedBoulder(Boulder b) {
		int playerX = getX();
		int playerY = getY();
		
		int boulderX = b.getX();
		int boulderY = b.getY();
		
		int targetX;
		int targetY;
		
		if (playerX == boulderX) {
			targetX = playerX;
			targetY = (boulderY > playerY) ? boulderY + 1 : boulderY - 1;
			
		} else {
			targetY = playerY;
			targetX = (boulderX > playerX) ? boulderX + 1 : boulderX - 1;
		}
		
		ArrayList<Entity> entities = dungeon.getEntities(targetX, targetY);
		
		boolean canMove = true;
		for (Entity e : entities) {
			if (!(e instanceof Switch)) {
				canMove = false;
			}
		}

		if (canMove) {
			b.moveBoulder(targetX, targetY);
			return false;
		}
		
		return true;
    }
}
