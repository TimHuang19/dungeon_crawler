package unsw.dungeon;

import java.util.ArrayList;

public class Enemy extends Entity implements Observer, Subject {
	
	private ArrayList<Observer> listObservers = new ArrayList<Observer>();
	private EnemyMovementStrategy strategy = new EnemyMoveToward();
	private Dungeon dungeon;
	private int playerX, playerY;
	private Boolean invincible;
	
	public Enemy(Dungeon dungeon, int x, int y) {
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
		for(Observer obs : listObservers) {
			obs.update(this);
		}
	}
	
	public void update(Subject obj) {
		if(obj instanceof Player) {
			update((Player) obj);
		}
	}
	
	public void update(Player obj) {
		this.playerX = obj.getX();
		this.playerY = obj.getY();
		this.invincible = obj.isInvincible();
		if(hasCollided()) dungeon.gameOver();
	}
	
	public Boolean hasCollided() {
		if (this.playerX == getX() && this.playerY == getY()) {
			return true;
		}
		return false;
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
    
    public void enemyMovement() {
    	if(invincible) {
    		strategy = new EnemyMoveAway();
    	}
    	else {
    		strategy = new EnemyMoveToward();
    	}
    	String direction = strategy.enemyMovement(playerX, playerY, getX(), getY(), dungeon);
    	switch(direction) {
    	case "left":
    		moveLeft();
    		break;
    	case "right":
    		moveRight();
    		break;
    	case "up":
    		moveUp();
    		break;
    	case "down":
    		moveDown();
    		break;
    	}
    }
    
    private boolean isObstacle(ArrayList<Entity> entities) {
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
}
