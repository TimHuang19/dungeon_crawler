package unsw.dungeon;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 * The Class Enemy.
 */
public class Enemy extends Entity implements Subject, Observer {

	/** The strategy. */
	private EnemyMovementStrategy strategy;
	
	/** The dungeon. */
	private Dungeon dungeon;
	
	/** The player Y. */
	private int playerX, playerY;
	
	/** The invincible. */
	private boolean invincible;
	
	/** The timeline. */
	private Timeline timeline;
	
	/** The observers. */
	private ArrayList<Observer> observers;
	
	/**
	 * Instantiates a new enemy.
	 *
	 * @param dungeon the dungeon
	 * @param x the x
	 * @param y the y
	 */
	public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        strategy = new EnemyMoveToward();
        invincible = false;
        this.dungeon = dungeon;
        this.observers = new ArrayList<>();
        
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), (ActionEvent event) -> enemyMovement());
		timeline.getKeyFrames().add(kf);
		timeline.play();
    }
	
	/**
	 * Pause.
	 */
	public void pause() {
		timeline.stop();
	}
	
	/**
	 * Un pause.
	 */
	public void unPause() {
		timeline.play();
	}
	
	/**
	 * Update.
	 *
	 * @param obj the obj
	 */
	public void update(Subject obj) {
		if(obj instanceof Player) {
			update((Player) obj);
		}
	}
	
	/**
	 * Update.
	 *
	 * @param p the player
	 */
	public void update(Player p) {
		this.playerX = p.getX();
		this.playerY = p.getY();
		this.invincible = p.isInvincible();
		
		if (collided(p)) {
			if (!invincible) {
				dungeon.gameOver();
			} else {
				dungeon.killEnemy(this);
			}
		} 
	}
	
	/**
	 * Determines if enemy has collided with player.
	 *
	 * @param p the player
	 * @return true, if enemy collides with player
	 */
	public boolean collided(Player p) {
		if (p.getX() == getX() && p.getY() == getY()) {
			timeline.stop();
			return true;
		}
		return false;
	}
	
    /**
     * Move up.
     */
    public void moveUp() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() - 1);

        if (getY() > 0 && !isObstacle(entities)) {
            y().set(getY() - 1);
            
        }
    }
	
	/**
	 * Move down.
	 */
	public void moveDown() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX(), getY() + 1);

        if (getY() < dungeon.getHeight() - 1 && !isObstacle(entities)) {
            y().set(getY() + 1);
        }
    }

    /**
     * Move left.
     */
    public void moveLeft() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX() - 1, getY());

        if (getX() > 0 && !isObstacle(entities)) {
            x().set(getX() - 1);
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
    	ArrayList<Entity> entities = dungeon.getEntities(getX() + 1, getY());
    	
        if (getX() < dungeon.getWidth() - 1 && !isObstacle(entities)) {
            x().set(getX() + 1);
        }
    }
    
    /**
     * Enemy movement.
     */
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
    	
		notifyObservers();
    }
    
    /**
     * Checks if a list of entities are obstacles to the enemy.
     *
     * @param entities 	the list of entities
     * @return true, if is obstacle
     */
    private boolean isObstacle(ArrayList<Entity> entities) {
    	boolean obstacle = false;
    	for (Entity e : entities) {
    		obstacle = obstacle || e.isObstacle(this);
    	}
    	return obstacle;
    }
    
	/**
	 * Sets the player X.
	 *
	 * @param playerX the new player X
	 */
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	/**
	 * Sets the player Y.
	 *
	 * @param playerY the new player Y
	 */
	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

	/**
	 * Sets the boolean indicating if player is invincible.
	 * Used only for unit testing
	 *
	 * @param invincible the new boolean indicating if player is invincible
	 */
	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
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
		return true;
	}
	
	
	/**
	 * Checks if a bomb is an obstacle to an enemy using a particular movement strategy.
	 *
	 * @param strategy the strategy
	 * @return true, if it is obstacle
	 */
	@Override
	public boolean isObstacle(EnemyMovementStrategy strategy) {
		return true;
	}
	
	
	/**
	 * Determines if the enemy blocks a boulder.
	 *
	 * @return true, if enemy blocks the boulder
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
	 * Register observer.
	 *
	 * @param o the observer
	 */
	@Override
	public void registerObserver(Observer o) {
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
		for (Observer o : observers) {
			o.update(this);;
		}
	}

	/**
	 * Sets the timeline.
	 *
	 * @param timeline the new timeline
	 */
	public void setTimeline(Timeline timeline) {
		this.timeline.stop();
		this.timeline = timeline;
	}
}
