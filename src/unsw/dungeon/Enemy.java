package unsw.dungeon;

import java.util.ArrayList;

public class Enemy extends Entity implements Observer, DungeonSubject {
	
	private ArrayList<DungeonObserver> listDungeonObservers = new ArrayList<DungeonObserver>();
	private Dungeon dungeon;
	private int playerX, playerY;
	
	public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }
	
	
	@Override
    public void registerDungeonObserver(DungeonObserver o) {
    	if(! listDungeonObservers.contains(o)) listDungeonObservers.add(o);
    }
    @Override
	public void removeDungeonObserver(DungeonObserver o) {
		listDungeonObservers.remove(o);
	}
    @Override
	public void notifyDungeonObservers() {
		for( DungeonObserver obs : listDungeonObservers) {
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
		if(hasCollided()) System.out.println("Game Over!");
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
    	moveUp();
    	moveLeft();
    	moveDown();
    	moveRight();
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
