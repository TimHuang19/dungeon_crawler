package unsw.dungeon;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Enemy extends Entity implements Subject, Observer {

	private EnemyMovementStrategy strategy;
	private Dungeon dungeon;
	private int playerX, playerY;
	private boolean invincible;
	private Timeline timeline;
	private ArrayList<Observer> observers;
	
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
	
	public void update(Subject obj) {
		if(obj instanceof Player) {
			update((Player) obj);
		}
	}
	
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
	
	public boolean collided(Player p) {
		if (p.getX() == getX() && p.getY() == getY()) {
			timeline.stop();
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
    	
		notifyObservers();
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

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update(this);;
		}
	}

}
