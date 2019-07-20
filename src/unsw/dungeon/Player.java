package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject {
	ArrayList<Observer> observers;
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    
    private boolean initialisedObservers;
    private Dungeon dungeon;
    private Key key;
    private Sword sword;
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
        this.sword = null;
        this.direction = RIGHT;
        this.invincible = false;
        this.invincibleSteps = 0;
        this.bombs = new ArrayList<Bomb>();
        this.observers = new ArrayList<Observer>();
        this.initialisedObservers = false;
    }
    
    public Key getKey() {
    	return key;
    }
    
    public Sword getSword() {
    	return sword;
    }
    
    public ArrayList<Bomb> getBombs() {
    	return bombs;
    }
    
    public boolean isInvincible() {
    	return invincible;
    }
    
    public int getInvincibleSteps() {
    	return invincibleSteps;
    }
    
    public int getDirection() {
    	return direction;
    }
    
    public void setDirection(int direction) {
    	this.direction = direction;
    }
    @Override
    public void registerObserver(Observer o){
    	observers.add(o);
    }
    
    @Override
	public void removeObserver(Observer o) {
    	observers.remove(o);
	}
    
    @Override
	public void notifyObservers() {
    	int n = observers.size();
    	for (int i = 0; i < n; i++) {
    		observers.get(i).update(this);
    		if (observers.size() < n) {
    			i--;
    			n--;
    		}
    	}
	}
    
    public void moveUp() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() - 1);

        if (getY() > 0 && !isObstacle(entities)) {
            y().set(getY() - 1);
            direction = UP;
            updateInvincibility();
            updateExitGoal();
            notifyObservers();
        }
    }

    public void moveDown() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() + 1);

        if (getY() < dungeon.getHeight() - 1 && !isObstacle(entities)) {
            y().set(getY() + 1);
            direction = DOWN;
            updateInvincibility();
            updateExitGoal();
            notifyObservers();
        }
    }

    public void moveLeft() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX() - 1, getY());

        if (getX() > 0 && !isObstacle(entities)) {
            x().set(getX() - 1);
            direction = LEFT;
            updateInvincibility();
            updateExitGoal();
            notifyObservers();
        }
    }

    public void moveRight() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX() + 1, getY());
    	
        if (getX() < dungeon.getWidth() - 1 && !isObstacle(entities)) {
            x().set(getX() + 1);
            direction = RIGHT;
            updateInvincibility();
            updateExitGoal();
            notifyObservers();
        }
    }
    
    public void pickUp() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY());
    	
    	for (Entity e : entities) {
    		if (e instanceof Key) {
    			Key newKey = (Key) e;
    			if (this.key == null) {
    				this.key = newKey;
    			} else {
    				this.key.x().set(getX());
    				this.key.y().set(getY());
    				dungeon.addEntity(this.key);
    				this.key = newKey;
    			}
    			dungeon.removeEntity(e);
    		} else if (e instanceof Sword) {
    			if (this.sword == null) {
    				this.sword = (Sword) e;
    				dungeon.removeEntity(e);
    			}
    		} else if (e instanceof Potion) {
    			this.invincibleSteps = 20;
    			this.invincible = true;
    			dungeon.removeEntity(e);
    		} else if (e instanceof Bomb) {
    			if (((Bomb) e).getState() instanceof LitState) {
    				return;
    			}
    			if (bombs.size() == 3) {
    				return;
    			}    			
    			bombs.add((Bomb) e);
    			dungeon.removeEntity(e);
    		} else if (e instanceof Treasure) {
    			dungeon.reduceTreasures();
    			dungeon.removeEntity(e);
    		}
    	}
    }

    public void swingSword() {
    	
    	if (sword == null) {
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
    			dungeon.killEnemy((Enemy) e);
    		}
    	}
    	
    	if (!sword.swing()) {
    		this.sword = null;
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
	    		if (key != null && d.matchingKey(key) && d.isClosed()) {
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
			if (e instanceof Door) {
				Door d = (Door) e;
				if (d.isClosed()) {
					canMove = false;
				}
			} else if (!(e instanceof Switch)) {
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
    
    private void updateExitGoal() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY());
    	
    	for (Entity e : entities) {
    		if (e instanceof Exit) {
    			dungeon.setComplete(Goal.EXIT, true);
    		}
    	}
    }
    
    private void initialiseObs() {
    	if (!initialisedObservers) {
    		ArrayList<Entity> enemies = dungeon.getEnemies();
    		for (Entity e: enemies) {
    			registerObserver((Observer) e);
    		}
    		initialisedObservers = true;
    	}
    }
}
