package unsw.dungeon;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject, Observer {
	private ArrayList<Observer> observers;
    
    private boolean initialisedObservers;
    private Dungeon dungeon;
    private int keyId;
    private Sword sword;
    private Direction direction;
    private boolean invincible;
    private int invincibleSteps;
    private ArrayList<Bomb> bombs;
    
    private ImageView downView;
    private ImageView upView;
    private ImageView leftView;
    private ImageView rightView;

    private ImageView downSwordView;
    private ImageView upSwordView;
    private ImageView leftSwordView;
    private ImageView rightSwordView;
    
    private ImageView downInvincibleView;
    private ImageView upInvincibleView;
    private ImageView leftInvincibleView;
    private ImageView rightInvincibleView;
    
    private ImageView downSwordInvincibleView;
    private ImageView upSwordInvincibleView;
    private ImageView leftSwordInvincibleView;
    private ImageView rightSwordInvincibleView;
    
    private ArrayList<ImageView> views;

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
        this.direction = Direction.DOWN;
        this.invincible = false;
        this.invincibleSteps = 0;
        this.views = new ArrayList<ImageView>();
        this.bombs = new ArrayList<Bomb>();
        this.observers = new ArrayList<Observer>();
        this.initialisedObservers = false;
    }
    
    public void addView(ImageView view) {
    	views.add(view);
    }
    
    public ArrayList<ImageView> getViews() {
    	return views;
    }
    
    public void setDownView(ImageView downView) {
    	this.downView = downView;
    }
    
    public ImageView getDownView() {
    	return downView;
    }
    
    public void setUpView(ImageView upView) {
    	this.upView = upView;
    }
    
    public ImageView getUpView() {
    	return upView;
    }
    
    public void setLeftView(ImageView leftView) {
    	this.leftView = leftView;
    }
    
    public ImageView getLeftView() {
    	return leftView;
    }
    
    public void setRightView(ImageView rightView) {
    	this.rightView = rightView;
    }
    
    public ImageView getRightView() {
    	return rightView;
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
    	notifyDungeonObservers();
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
		if (this.invincible && invincible) {
			return;
		}
		this.invincible = invincible;
		notifyDungeonObservers();
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
    		setInvincible(false);
    	}
    }

    public void moveUp() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() - 1);

        if (getY() > 0 && !isObstacle(entities)) {
            y().set(getY() - 1);
            setDirection(Direction.UP);
            updateInvincibility();
            updateExitGoal();
            notifyObservers();
        	notifyDungeonObservers();
        }
    }

    public void moveDown() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() + 1);

        if (getY() < dungeon.getHeight() - 1 && !isObstacle(entities)) {
            y().set(getY() + 1);
            setDirection(Direction.DOWN);
            updateInvincibility();
            updateExitGoal();
            notifyObservers();
        	notifyDungeonObservers();
        }
    }

    public void moveLeft() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX() - 1, getY());

        if (getX() > 0 && !isObstacle(entities)) {
            x().set(getX() - 1);
            setDirection(Direction.LEFT);
            updateInvincibility();
            updateExitGoal();
            notifyObservers();
        	notifyDungeonObservers();
        }
    }

    public void moveRight() {
    	initialiseObs();
    	ArrayList<Entity> entities = dungeon.getEntities(getX() + 1, getY());
    	
        if (getX() < dungeon.getWidth() - 1 && !isObstacle(entities)) {
            x().set(getX() + 1);
            setDirection(Direction.RIGHT);
            updateInvincibility();
            updateExitGoal();
            notifyObservers();
        	notifyDungeonObservers();
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
    		setSword(null);    	
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
    			((Enemy) e).registerObserver(this);
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
	
    public ImageView getDownSwordView() {
		return downSwordView;
	}

	public void setDownSwordView(ImageView downSwordView) {
		this.downSwordView = downSwordView;
	}

	public ImageView getUpSwordView() {
		return upSwordView;
	}

	public void setUpSwordView(ImageView upSwordView) {
		this.upSwordView = upSwordView;
	}

	public ImageView getLeftSwordView() {
		return leftSwordView;
	}

	public void setLeftSwordView(ImageView leftSwordView) {
		this.leftSwordView = leftSwordView;
	}

	public ImageView getRightSwordView() {
		return rightSwordView;
	}

	public void setRightSwordView(ImageView rightSwordView) {
		this.rightSwordView = rightSwordView;
	}

	public ImageView getDownInvincibleView() {
		return downInvincibleView;
	}

	public void setDownInvincibleView(ImageView downInvincibleView) {
		this.downInvincibleView = downInvincibleView;
	}

	public ImageView getUpInvincibleView() {
		return upInvincibleView;
	}

	public void setUpInvincibleView(ImageView upInvincibleView) {
		this.upInvincibleView = upInvincibleView;
	}

	public ImageView getLeftInvincibleView() {
		return leftInvincibleView;
	}

	public void setLeftInvincibleView(ImageView leftInvincibleView) {
		this.leftInvincibleView = leftInvincibleView;
	}

	public ImageView getRightInvincibleView() {
		return rightInvincibleView;
	}

	public void setRightInvincibleView(ImageView rightInvincibleView) {
		this.rightInvincibleView = rightInvincibleView;
	}

	public ImageView getDownSwordInvincibleView() {
		return downSwordInvincibleView;
	}

	public void setDownSwordInvincibleView(ImageView downSwordInvincibleView) {
		this.downSwordInvincibleView = downSwordInvincibleView;
	}

	public ImageView getUpSwordInvincibleView() {
		return upSwordInvincibleView;
	}

	public void setUpSwordInvincibleView(ImageView upSwordInvincibleView) {
		this.upSwordInvincibleView = upSwordInvincibleView;
	}

	public ImageView getLeftSwordInvincibleView() {
		return leftSwordInvincibleView;
	}

	public void setLeftSwordInvincibleView(ImageView leftSwordInvincibleView) {
		this.leftSwordInvincibleView = leftSwordInvincibleView;
	}

	public ImageView getRightSwordInvincibleView() {
		return rightSwordInvincibleView;
	}

	public void setRightSwordInvincibleView(ImageView rightSwordInvincibleView) {
		this.rightSwordInvincibleView = rightSwordInvincibleView;
	}

}
