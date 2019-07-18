package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject {
	ArrayList<Observer> listObservers = new ArrayList<Observer>();
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
    @Override
    public void registerObserver(Observer o) {
    	if(! listObservers.contains(o)) listObservers.add(o);
    }
    @Override
	public void removeObserver(Observer o) {
		listObservers.remove(o);
	}
    @Override
	public void notifyObservers() {
		for( Observer obs : listObservers) {
			obs.update(this);
		}
	}
    
    public void moveUp() {
    	Entity e = dungeon.getEntity(getX(), getY() - 1);

        if (getY() > 0 && !isObstacle(e) ){
            y().set(getY() - 1);
            
        }
    }

    public void moveDown() {
    	Entity e = dungeon.getEntity(getX(), getY() + 1);

        if (getY() < dungeon.getHeight() - 1 && !isObstacle(e)) {
            y().set(getY() + 1);
        }
    }

    public void moveLeft() {
    	Entity e = dungeon.getEntity(getX() - 1, getY());

        if (getX() > 0 && !isObstacle(e)) {
            x().set(getX() - 1);
        }
    }

    public void moveRight() {
    	Entity e = dungeon.getEntity(getX() + 1, getY());

        if (getX() < dungeon.getWidth() - 1 && !isObstacle(e)) {
            x().set(getX() + 1);
        }
    }
    
    private boolean isObstacle(Entity e) {

    	if (e == null) {
    		return false;
    	} else if (e instanceof Wall) {
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
    		int playerX = getX();
    		int playerY = getY();
    		
    		
    	}
    	return false;
    }
}
