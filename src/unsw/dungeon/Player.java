package unsw.dungeon;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

/**
 * The player entity.
 *
 * @author Robert Clifton-Everest
 */
public class Player extends Entity implements Subject, Observer {
	
	/** The observers. */
	private ArrayList<Observer> observers;
    
    /** A boolean indicating if observers have been initialised */
    private boolean initialisedObservers;
    
    /** The dungeon. */
    private Dungeon dungeon;
    
    /** The key id of the key it is currently holding */
    private int keyId;
    
    /** The sword. */
    private Sword sword;
    
    /** The direction the player is facing. */
    private Direction direction;
    
    /** A boolean indicating whether player is invincible */
    private boolean invincible;
    
    /** The amount of steps it is invincible for */
    private int invincibleSteps;
    
    /** The bombs. */
    private ArrayList<Bomb> bombs;
    
    /** The view of player facing down */
    private ImageView downView;
    
    /** The view of player facing up */
    private ImageView upView;
    
    /** The view of player facing left */
    private ImageView leftView;
    
    /** The view of player facing right */
    private ImageView rightView;

    /** The view of player with sword facing down */
    private ImageView downSwordView;
    
    /** The view of player with sword facing up */
    private ImageView upSwordView;
    
    /** The view of player with sword facing left */
    private ImageView leftSwordView;
    
    /** The view of player with sword facing right */
    private ImageView rightSwordView;
    
    /** The view of an invincible player facing down */
    private ImageView downInvincibleView;
    
    /** The view of an invincible player facing up */
    private ImageView upInvincibleView;
    
    /** The view of an invincible player facing left */
    private ImageView leftInvincibleView;
    
    /** The view of an invincible player facing right */
    private ImageView rightInvincibleView;
    
    /** The view of an invincible player with a sword facing down */
    private ImageView downSwordInvincibleView;
    
    /** The view of an invincible player with a sword facing up */
    private ImageView upSwordInvincibleView;
    
    /** The view of an invincible player with a sword facing left */
    private ImageView leftSwordInvincibleView;
    
    /** The view of an invincible player with a sword facing right */
    private ImageView rightSwordInvincibleView;
    
    /** The views. */
    private ArrayList<ImageView> views;

	/**
	 * Create a player positioned in square (x,y).
	 *
	 * @param dungeon 	The dungeon
	 * @param x 		The x position
	 * @param y 		The y position
	 */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.keyId = -1;
        this.sword = null;
        this.direction = Direction.DOWN;
        this.invincible = false;
        this.invincibleSteps = 0;
        this.views = new ArrayList<ImageView>();
        this.bombs = new ArrayList<Bomb>();
        this.observers = new ArrayList<Observer>();
        this.initialisedObservers = false;
    }
    
    /**
     * Adds the view.
     *
     * @param view 	The view
     */
    public void addView(ImageView view) {
    	views.add(view);
    }
    
    /**
     * Gets the views.
     *
     * @return the views
     */
    public ArrayList<ImageView> getViews() {
    	return views;
    }
    
    /**
     * Sets the down view.
     *
     * @param downView 	The new down view
     */
    public void setDownView(ImageView downView) {
    	this.downView = downView;
    }
    
    /**
     * Gets the down view.
     *
     * @return the down view
     */
    public ImageView getDownView() {
    	return downView;
    }
    
    /**
     * Sets the up view.
     *
     * @param upView 	The new up view
     */
    public void setUpView(ImageView upView) {
    	this.upView = upView;
    }
    
    /**
     * Gets the up view.
     *
     * @return the up view
     */
    public ImageView getUpView() {
    	return upView;
    }
    
    /**
     * Sets the left view.
     *
     * @param leftView 		The new left view
     */
    public void setLeftView(ImageView leftView) {
    	this.leftView = leftView;
    }
    
    /**
     * Gets the left view.
     *
     * @return 	The left view
     */
    public ImageView getLeftView() {
    	return leftView;
    }
    
    /**
     * Sets the right view.
     *
     * @param rightView		The new right view
     */
    public void setRightView(ImageView rightView) {
    	this.rightView = rightView;
    }
    
    /**
     * Gets the right view.
     *
     * @return The right view
     */
    public ImageView getRightView() {
    	return rightView;
    }
    
    /**
     * Gets the key id.
     *
     * @return the key id
     */
    public int getKeyId() {
    	return keyId;
    }
    
    /**
     * Sets the key id.
     *
     * @param keyId the new key id
     */
    public void setKeyId(int keyId) {
    	this.keyId = keyId;
    }
    
    /**
     * Removes the entity from dungeon.
     *
     * @param e 	The entity
     */
    public void removeFromDungeon(Entity e) {
    	dungeon.removeEntity(e);
    }
    
    /**
     * Gets the sword.
     *
     * @return the sword
     */
    public Sword getSword() {
    	return sword;
    }
    
    /**
     * Sets the sword.
     *
     * @param sword 	The new sword
     */
    public void setSword(Sword sword) {
    	this.sword = sword;
    	notifyDungeonObservers();
    }
    
    /**
     * Gets the bombs.
     *
     * @return the bombs
     */
    public ArrayList<Bomb> getBombs() {
    	return bombs;
    }
    
    /**
     * Adds the bomb.
     *
     * @param bomb 	The bomb
     */
    public void addBomb(Bomb bomb) {
    	bombs.add(bomb);
    }
    
    /**
     * Checks if is invincible.
     *
     * @return true, if is invincible
     */
    public boolean isInvincible() {
    	return invincible;
    }
    
	/**
	 * Sets the boolean value indicating invincibility
	 *
	 * @param invincible the new boolean value indicating invincibility
	 */
	public void setInvincible(boolean invincible) {
		if (this.invincible && invincible) {
			return;
		}
		this.invincible = invincible;
		notifyDungeonObservers();
		notifyObservers();
	}
    
    /**
     * Gets the amount of invincible steps.
     *
     * @return the invincible steps
     */
    public int getInvincibleSteps() {
    	return invincibleSteps;
    }
    

	/**
	 * Sets the amount of invincible steps.
	 *
	 * @param invincibleSteps 	The new amount of invincible steps
	 */
	public void setInvincibleSteps(int invincibleSteps) {
		this.invincibleSteps = invincibleSteps;
	}

    /**
     * Gets the direction.
     *
     * @return the direction
     */
    public Direction getDirection() {
    	return direction;
    }
    
    /**
     * Sets the direction.
     *
     * @param direction 	The new direction
     */
    public void setDirection(Direction direction) {
    	this.direction = direction;
    }
    
    /**
     * Register observer.
     *
     * @param o the observer
     */
    @Override
    public void registerObserver(Observer o){
    	observers.add(o);
    }
    
    /**
     * Removes the observer.
     *
     * @param o the observer
     */
    @Override
	public void removeObserver(Observer o) {
    	observers.remove(o);
	}
    
    /**
     * Notify observers.
     */
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
    
    /**
     * Update invincibility.
     */
    private void updateInvincibility() {
    	if (!invincible) {
    		return;
    	}
    	if (invincibleSteps > 0) {
    		invincibleSteps--;
    	}
    	if (invincibleSteps == 0) {
    		setInvincible(false);
    	}
    }

    /**
     * Move up.
     */
    public void moveUp() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() - 1);
        setDirection(Direction.UP);
    	notifyDungeonObservers();

        if (getY() > 0 && !isObstacle(entities)) {
            y().set(getY() - 1);
            updateInvincibility();
            updateExitGoal();
            notifyObservers();
        }
    }

    /**
     * Move down.
     */
    public void moveDown() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() + 1);
        setDirection(Direction.DOWN);
    	notifyDungeonObservers();

        if (getY() < dungeon.getHeight() - 1 && !isObstacle(entities)) {
            y().set(getY() + 1);
            updateInvincibility();
            updateExitGoal();
            notifyObservers();
        }
    }

    /**
     * Move left.
     */
    public void moveLeft() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX() - 1, getY());
        setDirection(Direction.LEFT);
    	notifyDungeonObservers();

        if (getX() > 0 && !isObstacle(entities)) {
            x().set(getX() - 1);
            updateInvincibility();
            updateExitGoal();
            notifyObservers();
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX() + 1, getY());
        setDirection(Direction.RIGHT);
    	notifyDungeonObservers();

        if (getX() < dungeon.getWidth() - 1 && !isObstacle(entities)) {
            x().set(getX() + 1);
            updateInvincibility();
            updateExitGoal();
            notifyObservers();
        }
    }
    
    /**
     * Pick up.
     */
    public void pickUp() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY());
    	
    	for (Entity e : entities) {
    		if (e.pickUp(this)) {
                e.notifyDungeonObservers();
    			dungeon.removeEntity(e);
    		}
    	}
    }
	
	/**
	 * Reduce count in the the dungeon
	 */
	public void reduceTreasures() {
		dungeon.reduceTreasures();
	}
	
    /**
     * Swing sword.
     */
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
    		setSword(null);    	
    	}

    }
    
    /**
     * Drop bomb.
     */
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

	/**
	 * Adds the entity.
	 *
	 * @param e the entity
	 */
	public void addEntity(Entity e) {
		dungeon.addEntity(e);
	}
	
	/**
	 * Checks if the list of entities are obstacles to the player
	 *
	 * @param p 	The list of entities
	 * @return true, if it is an obstacle to the player
	 */
    private boolean isObstacle(ArrayList<Entity> entities) {
    	boolean obstacle = false;
    	for (Entity e : entities) {
    		obstacle = obstacle || e.isObstacle(this);
    	}
    	return obstacle;
    }
    
    /**
     * Update exit goal.
     */
    private void updateExitGoal() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY());
    	
    	for (Entity e : entities) {
    		if (e instanceof Exit) {
    			dungeon.setComplete(Goal.EXIT, true);
    		}
    	}
    }
    
    /**
     * Initialise observer
     */
    private void initialiseObs() {
    	if (!initialisedObservers) {
    		ArrayList<Entity> enemies = dungeon.getEnemies();
    		for (Entity e: enemies) {
    			registerObserver((Observer) e);
    			((Enemy) e).registerObserver(this);
    		}
    		initialisedObservers = true;
    	}
    }
    
	/**
	 * Checks if is obstacle to a player.
	 *
	 * @param p 	The player
	 * @return true, if it is an obstacle to the player
	 */
	@Override
	public boolean isObstacle(Player p) {
		return false;
	}

	/**
	 * Checks if is obstacle to an enemy.
	 *
	 * @param e 	The enemy
	 * @return true, if it is obstacle to an enemy
	 */
	@Override
	public boolean isObstacle(Enemy e) {
		return false;
	}
	
	
	/**
	 * Checks if a bomb is an obstacle to an enemy using a particular movement strategy
	 *
	 * @param e 		The enemy
	 * @return true, if it is obstacle
	 */
	@Override
	public boolean isObstacle(EnemyMovementStrategy strategy) {
		return false;
	}
	
	/**
	 * Determines if the player blocks a boulder.
	 *
	 * @return true, if player blocks the boulder
	 */
	@Override
	public boolean blocksBoulder() {
		return true;
	}

	/**
	 * Pick up.
	 *
	 * @param p 		The player
	 * @return true, if successful
	 */
	@Override
	public boolean pickUp(Player p) {
		return false;
	}

	/**
	 * Update
	 *
	 * @param obj the subject
	 */
	@Override
	public void update(Subject obj) {
		if (obj instanceof Enemy) {
			Enemy enemy = (Enemy) obj;
			
			if (enemy.collided(this)) {
				if (!invincible) {
					dungeon.gameOver();
				} else {
					dungeon.killEnemy(enemy);
				}
			}
		}
	}
	
    /**
     * Gets the down sword view.
     *
     * @return the down sword view
     */
    public ImageView getDownSwordView() {
		return downSwordView;
	}

	/**
	 * Sets the down sword view.
	 *
	 * @param downSwordView the new down sword view
	 */
	public void setDownSwordView(ImageView downSwordView) {
		this.downSwordView = downSwordView;
	}

	/**
	 * Gets the up sword view.
	 *
	 * @return the up sword view
	 */
	public ImageView getUpSwordView() {
		return upSwordView;
	}

	/**
	 * Sets the up sword view.
	 *
	 * @param upSwordView the new up sword view
	 */
	public void setUpSwordView(ImageView upSwordView) {
		this.upSwordView = upSwordView;
	}

	/**
	 * Gets the left sword view.
	 *
	 * @return the left sword view
	 */
	public ImageView getLeftSwordView() {
		return leftSwordView;
	}

	/**
	 * Sets the left sword view.
	 *
	 * @param leftSwordView the new left sword view
	 */
	public void setLeftSwordView(ImageView leftSwordView) {
		this.leftSwordView = leftSwordView;
	}

	/**
	 * Gets the right sword view.
	 *
	 * @return the right sword view
	 */
	public ImageView getRightSwordView() {
		return rightSwordView;
	}

	/**
	 * Sets the right sword view.
	 *
	 * @param rightSwordView the new right sword view
	 */
	public void setRightSwordView(ImageView rightSwordView) {
		this.rightSwordView = rightSwordView;
	}

	/**
	 * Gets the down invincible view.
	 *
	 * @return the down invincible view
	 */
	public ImageView getDownInvincibleView() {
		return downInvincibleView;
	}

	/**
	 * Sets the down invincible view.
	 *
	 * @param downInvincibleView the new down invincible view
	 */
	public void setDownInvincibleView(ImageView downInvincibleView) {
		this.downInvincibleView = downInvincibleView;
	}

	/**
	 * Gets the up invincible view.
	 *
	 * @return the up invincible view
	 */
	public ImageView getUpInvincibleView() {
		return upInvincibleView;
	}

	/**
	 * Sets the up invincible view.
	 *
	 * @param upInvincibleView the new up invincible view
	 */
	public void setUpInvincibleView(ImageView upInvincibleView) {
		this.upInvincibleView = upInvincibleView;
	}

	/**
	 * Gets the left invincible view.
	 *
	 * @return the left invincible view
	 */
	public ImageView getLeftInvincibleView() {
		return leftInvincibleView;
	}

	/**
	 * Sets the left invincible view.
	 *
	 * @param leftInvincibleView the new left invincible view
	 */
	public void setLeftInvincibleView(ImageView leftInvincibleView) {
		this.leftInvincibleView = leftInvincibleView;
	}

	/**
	 * Gets the right invincible view.
	 *
	 * @return the right invincible view
	 */
	public ImageView getRightInvincibleView() {
		return rightInvincibleView;
	}

	/**
	 * Sets the right invincible view.
	 *
	 * @param rightInvincibleView the new right invincible view
	 */
	public void setRightInvincibleView(ImageView rightInvincibleView) {
		this.rightInvincibleView = rightInvincibleView;
	}

	/**
	 * Gets the down sword invincible view.
	 *
	 * @return the down sword invincible view
	 */
	public ImageView getDownSwordInvincibleView() {
		return downSwordInvincibleView;
	}

	/**
	 * Sets the down sword invincible view.
	 *
	 * @param downSwordInvincibleView the new down sword invincible view
	 */
	public void setDownSwordInvincibleView(ImageView downSwordInvincibleView) {
		this.downSwordInvincibleView = downSwordInvincibleView;
	}

	/**
	 * Gets the up sword invincible view.
	 *
	 * @return the up sword invincible view
	 */
	public ImageView getUpSwordInvincibleView() {
		return upSwordInvincibleView;
	}

	/**
	 * Sets the up sword invincible view.
	 *
	 * @param upSwordInvincibleView the new up sword invincible view
	 */
	public void setUpSwordInvincibleView(ImageView upSwordInvincibleView) {
		this.upSwordInvincibleView = upSwordInvincibleView;
	}

	/**
	 * Gets the left sword invincible view.
	 *
	 * @return the left sword invincible view
	 */
	public ImageView getLeftSwordInvincibleView() {
		return leftSwordInvincibleView;
	}

	/**
	 * Sets the left sword invincible view.
	 *
	 * @param leftSwordInvincibleView the new left sword invincible view
	 */
	public void setLeftSwordInvincibleView(ImageView leftSwordInvincibleView) {
		this.leftSwordInvincibleView = leftSwordInvincibleView;
	}

	/**
	 * Gets the right sword invincible view.
	 *
	 * @return the right sword invincible view
	 */
	public ImageView getRightSwordInvincibleView() {
		return rightSwordInvincibleView;
	}

	/**
	 * Sets the right sword invincible view.
	 *
	 * @param rightSwordInvincibleView the new right sword invincible view
	 */
	public void setRightSwordInvincibleView(ImageView rightSwordInvincibleView) {
		this.rightSwordInvincibleView = rightSwordInvincibleView;
	}

}
