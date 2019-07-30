package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject {
	private ArrayList<Observer> observers;
    
    private boolean initialisedObservers;
    private Dungeon dungeon;
    private int keyId;
    private Sword sword;
    private Direction direction;
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
        this.keyId = -1;
        this.sword = null;
        this.direction = Direction.RIGHT;
        this.invincible = false;
        this.invincibleSteps = 0;
        this.bombs = new ArrayList<Bomb>();
        this.observers = new ArrayList<Observer>();
        this.initialisedObservers = false;
    }
    
    
    public int getKeyId() {
    	return keyId;
    }
    
    public void setKeyId(int keyId) {
    	this.keyId = keyId;
    }
    
    public void removeFromDungeon(Entity e) {
    	dungeon.removeEntity(e);
    }
    
    public Sword getSword() {
    	return sword;
    }
    
    public void setSword(Sword sword) {
    	this.sword = sword;
    }
    
    public ArrayList<Bomb> getBombs() {
    	return bombs;
    }
    
    public void addBomb(Bomb bomb) {
    	bombs.add(bomb);
    }
    
    public boolean isInvincible() {
    	return invincible;
    }
    
	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
		notifyObservers();
	}
    
    public int getInvincibleSteps() {
    	return invincibleSteps;
    }
    

	public void setInvincibleSteps(int invincibleSteps) {
		this.invincibleSteps = invincibleSteps;
	}

    public Direction getDirection() {
    	return direction;
    }
    
    public void setDirection(Direction direction) {
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

    public void moveUp() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() - 1);

        if (getY() > 0 && !isObstacle(entities)) {
            y().set(getY() - 1);
            direction = Direction.UP;
            updateInvincibility();
    		notifyDungeonObservers();
            updateExitGoal();
            notifyObservers();
        }
    }

    public void moveDown() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() + 1);

        if (getY() < dungeon.getHeight() - 1 && !isObstacle(entities)) {
            y().set(getY() + 1);
            direction = Direction.DOWN;
            updateInvincibility();
    		notifyDungeonObservers();
            updateExitGoal();
            notifyObservers();
        }
    }

    public void moveLeft() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX() - 1, getY());

        if (getX() > 0 && !isObstacle(entities)) {
            x().set(getX() - 1);
            direction = Direction.LEFT;
            updateInvincibility();
    		notifyDungeonObservers();
            updateExitGoal();
            notifyObservers();
        }
    }

    public void moveRight() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX() + 1, getY());
    	
        if (getX() < dungeon.getWidth() - 1 && !isObstacle(entities)) {
            x().set(getX() + 1);
            direction = Direction.RIGHT;
            updateInvincibility();
    		notifyDungeonObservers();
            updateExitGoal();
            notifyObservers();
        }
    }
    
    public void pickUp() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY());
    	
    	for (Entity e : entities) {
    		if (e.pickUp(this)) {
                e.notifyDungeonObservers();
    			dungeon.removeEntity(e);
    		}
    	}
    }
	
	public void reduceTreasures() {
		dungeon.reduceTreasures();
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

	public void addEntity(Entity e) {
		dungeon.addEntity(e);
	}
	
    private boolean isObstacle(ArrayList<Entity> entities) {
    	boolean obstacle = false;
    	for (Entity e : entities) {
    		obstacle = obstacle || e.isObstacle(this);
    	}
    	return obstacle;
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
    
	@Override
	public boolean isObstacle(Player p) {
		return false;
	}

	@Override
	public boolean isObstacle(Enemy e) {
		return false;
	}
	
	@Override
	public boolean blocksBoulder() {
		return true;
	}

	@Override
	public boolean pickUp(Player p) {
		return false;
	}

}
