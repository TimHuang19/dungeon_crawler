package unsw.dungeon;

import java.util.ArrayList;

public class Enemy extends Entity implements Observer {
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;
    private static final int UNABLE = 4;
	
	private ArrayList<Observer> listObservers = new ArrayList<Observer>();
	private EnemyMovementStrategy strategy = new EnemyMoveToward();
	private Dungeon dungeon;
	private int playerX, playerY;
	private boolean invincible;
	private boolean canMove;
	
	public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        invincible = false;
        this.dungeon = dungeon;
        this.canMove = false;
    }
	
	public void update(Subject obj) {
		if(obj instanceof Player) {
			update((Player) obj);
		}
	}
	
	public void update(Player p) {
		this.playerX = p.getX();
		this.playerY = p.getY();
		this.invincible = p.isInvincible();
		
		if (collided() && !invincible) {
			dungeon.gameOver();
		} else if (collided() && invincible) {
			dungeon.killEnemy(this);
		}
		
		if (canMove) {
			enemyMovement();
			canMove = false;
		} else {
			canMove = true;
		}
		
		if (collided() && !invincible) {
			dungeon.gameOver();
		} else if (collided() && invincible) {
			dungeon.killEnemy(this);
		}
	}
	
	public boolean collided() {
		if (playerX == getX() && playerY == getY()) {
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
    	int direction = strategy.enemyMovement(playerX, playerY, getX(), getY(), dungeon);
    	
    	switch(direction) {
    	case LEFT:
    		moveLeft();
    		break;
    	case RIGHT:
    		moveRight();
    		break;
    	case UP:
    		moveUp();
    		break;
    	case DOWN:
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
