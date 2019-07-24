package unsw.dungeon;

import java.util.ArrayList;

public class Enemy extends Entity implements Observer {

	private EnemyMovementStrategy strategy;
	private Dungeon dungeon;
	private int playerX, playerY;
	private boolean invincible;
	private boolean canMove;
	
	public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        strategy = new EnemyMoveToward();
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
			return;
		} else if (collided() && invincible) {
			dungeon.killEnemy(this);
			return;
		}
		
		if (canMove) {
			enemyMovement();
			canMove = false;
		} else {
			canMove = true;
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
    	Direction direction = strategy.enemyMovement(playerX, playerY, getX(), getY(), dungeon);
    	
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
    	case UNABLE:
    		break;
    	}
    	
		if (collided() && !invincible) {
			dungeon.gameOver();
			return;
		} else if (collided() && invincible) {
			dungeon.killEnemy(this);
			return;
		}
    }
    
    private boolean isObstacle(ArrayList<Entity> entities) {
    	boolean obstacle = false;
    	for (Entity e : entities) {
    		obstacle = obstacle || e.isObstacle(this);
    	}
    	return obstacle;
    }
    
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	@Override
	public boolean isObstacle(Player p) {
		return false;
	}

	@Override
	public boolean isObstacle(Enemy e) {
		return true;
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
