package unsw.dungeon;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject {
	ArrayList<Observer> listObservers = new ArrayList<Observer>();
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    private Dungeon dungeon;
    private Key key;
    private boolean sword;
    private int direction;
    private boolean invincible;
    private int invincibleSteps;
    private ArrayList<Bomb> bombs;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.key = null;
        this.sword = false;
        this.direction = RIGHT;
        this.invincible = false;
        this.invincibleSteps = 0;
        this.bombs = new ArrayList<Bomb>();
    }
    
    public boolean isInvincible() {
    	return invincible;
    }
    
    @Override
    public void registerObserver(Observer o){
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
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() - 1);

        if (getY() > 0 && !isObstacle(entities)) {
            y().set(getY() - 1);
            updateInvincibility();
            direction = UP;
            
        }
    }

    public void moveDown() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() + 1);

        if (getY() < dungeon.getHeight() - 1 && !isObstacle(entities)) {
            y().set(getY() + 1);
            updateInvincibility();
            direction = DOWN;
        }
    }

    public void moveLeft() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX() - 1, getY());

        if (getX() > 0 && !isObstacle(entities)) {
            x().set(getX() - 1);
            updateInvincibility();
            direction = LEFT;
        }
    }

    public void moveRight() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX() + 1, getY());
    	
        if (getX() < dungeon.getWidth() - 1 && !isObstacle(entities)) {
            x().set(getX() + 1);
            updateInvincibility();
            direction = RIGHT;
        }
    }
    
    public void pickUp() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY());
    	
    	for (Entity e : entities) {
    		if (e instanceof Key) {
    			Key newKey = (Key) e;
    			dungeon.removeEntity(e);
    			if (this.key == null) {
    				this.key = newKey;
    			} else {
    				this.key.x().set(getX());
    				this.key.y().set(getY());
    				dungeon.addEntity(this.key);
    				this.key = newKey;
    			}
    		} else if (e instanceof Sword) {
    			if (this.sword == false) {
    				this.sword = true;
    				dungeon.removeEntity(e);
    			}
    		} else if (e instanceof Potion) {
    			this.invincibleSteps = 20;
    			this.invincible = true;
    		} else if (e instanceof Bomb) {
    			if (bombs.size() == 3) {
    				return;
    			}
    			bombs.add((Bomb) e);
    			dungeon.removeEntity(e);
    		} else if (e instanceof Treasure) {
    			dungeon.removeEntity(e);
    		}
    	}
    }
    
    public void swingSword() {

    	if (sword == false) {
    		return;
    	}
    	
    	int x, y;
    	
    	switch (direction) {
    	case UP:
    		x = getX();
    		y = getY() - 1;
    		break;
    	case DOWN:
    		x = getX();
    		y = getY() + 1;

    		break;
    	case LEFT:
    		x = getX() - 1;

    		y = getY();
    		break;
    	default:
    		x = getX() + 1;
    		y = getY();
    	}
    	
    	ArrayList<Entity> entities = dungeon.getEntities(x, y);
    	for (Entity e : entities) {
    		if (e instanceof Enemy) {
    			dungeon.removeEntity(e);
    		}
    	}

    }
    
    public void dropBomb() {
    	if (bombs.size() == 0) {
    		return;
    	}
    	
    	Bomb b = bombs.remove(0);
    	b.dropBomb();
    	b.x().set(getX());
    	b.y().set(getY());
    	dungeon.addEntity(b);
    	
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
			if (targetY == -1 || targetY == dungeon.getHeight()) {
				return true;
			}
			
		} else {
			targetY = playerY;
			targetX = (boulderX > playerX) ? boulderX + 1 : boulderX - 1;
			if (targetX == -1 || targetX == dungeon.getWidth()) {
				return true;
			}
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
    
    private void updateInvincibility() {
    	System.out.println(invincibleSteps);
    	if (!invincible) {
    		return;
    	}
    	if (invincibleSteps > 0) {
    		invincibleSteps--;
    	}
    	if (invincibleSteps == 0) {
    		invincible = false;
    	}
    }
    
}
