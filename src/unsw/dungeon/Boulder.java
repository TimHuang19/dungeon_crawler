package unsw.dungeon;

import java.util.ArrayList;

/**
 * The Class Boulder.
 */
public class Boulder extends Entity implements Subject {
	
	/** The observers. */
	private ArrayList<Observer> observers;
	
	/** Boolean indicating whether the boulder is on switch */
	private boolean onSwitch;
	
	/** Boolean indicating if boulder is destroyed */
	private boolean destroyed;
	
	/**
	 * Instantiates a new boulder.
	 *
	 * @param dungeon 	The dungeon
	 * @param x 		The x position
	 * @param y 		The y position
	 */
	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y);
		observers = new ArrayList<Observer>();
		this.registerObserver(dungeon);
		onSwitch = false;
		destroyed = false;
		ArrayList<Entity> entities = dungeon.getEntities(x, y);
		for (Entity e : entities) {
			if (e instanceof Switch) {
				onSwitch = true;
				notifyObservers();
			}
		}
		
	}
	
	/**
	 * Move boulder.
	 *
	 * @param x 	The new x position
	 * @param y 	The new y position
	 */
	public void moveBoulder(int x, int y) {
		x().set(x);
		y().set(y);
		notifyObservers();
	}
	
	/**
	 * Destroy.
	 */
	public void destroy() {
		destroyed = true;
		notifyDungeonObservers();
		notifyObservers();
	}
	
	/**
	 * Determines if boulder is destroyed
	 *
	 * @return true, if boulder is destroyed
	 */
	public boolean gotDestroyed() {
		return destroyed;
	}
	
	/**
	 * Determines if boulder is on a switch
	 *
	 * @return true, if boulder is on a switch
	 */
	public boolean getOnSwitch() {
		return onSwitch;
	}
	
	/**
	 * Sets the boolean value of whether the boulder is on a switch
	 *
	 * @param onSwitch 		The new boolean value
	 */
	public void setOnSwitch(boolean onSwitch) {
		this.onSwitch = onSwitch;
	}
	
	/**
	 * Register observer.
	 *
	 * @param o 	The observer
	 */
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	/**
	 * Removes the observer.
	 *
	 * @param o the o
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
			o.update(this);
		}
	}

	/**
	 * Checks if boulder is an obstacle to the player.
	 *
	 * @param p 	The player
	 * @return true, if the boulder is an obstacle to the player
	 */
	@Override
	public boolean isObstacle(Player p) {
		if (blockedBoulder(p)) {
			return true;
		}
		return false;
	}
	
    /**
     * Blocked boulder.
     *
     * @param p 	The player
     * @return true, if the boulder is blocked relative to the player
     */
    private boolean blockedBoulder(Player p) {
		int playerX = p.getX();
		int playerY = p.getY();
		
		int boulderX = getX();
		int boulderY = getY();
		
		int targetX;
		int targetY;
				
		if (playerX == boulderX) {
			targetX = playerX;
			targetY = (boulderY > playerY) ? boulderY + 1 : boulderY - 1;
			if (targetY == -1 || targetY == p.getHeight()) {
				return true;
			}
			
		} else {
			targetY = playerY;
			targetX = (boulderX > playerX) ? boulderX + 1 : boulderX - 1;
			if (targetX == -1 || targetX == p.getWidth()) {
				return true;
			}
		}
				
		ArrayList<Entity> entities = p.getEntities(targetX, targetY);
		
		boolean canMove = true;
		for (Entity e : entities) {
			if (e.blocksBoulder()) {
				canMove = false;
			}
		}

		if (canMove) {
			moveBoulder(targetX, targetY);
			return false;
		}
		
		return true;
    }

	/**
	 * Checks if boulder is an obstacle to an enemy
	 *
	 * @param e 	The enemy
	 * @return true, if the boulder is an obstacle to the enemy
	 */
	@Override
	public boolean isObstacle(Enemy e) {
		return true;
	}

	/**
	 * Checks if a bomb is an obstacle to an enemy using a particular movement strategy
	 *
	 * @param e 		The enemy
	 * @return true, if it is obstacle
	 */
	@Override
	public boolean isObstacle(EnemyMovementStrategy strategy) {
		return true;
	}
	
	/**
	 * Pick up.
	 *
	 * @param p 	The player
	 * @return true, if successful
	 */
	@Override
	public boolean pickUp(Player p) {
		return false;
	}

	/**
	 * Determines if this object blocks a boulder
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean blocksBoulder() {
		return true;
	}
}
