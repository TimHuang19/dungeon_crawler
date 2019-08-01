package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity implements DungeonSubject{

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    /** The x and y position of the entity. */
    private IntegerProperty x, y;
    
    /** The view. */
    private ImageView view;
    
    /** The dungeon observers. */
    private ArrayList<DungeonObserver> dungeonObservers;
    
    /**
     * Create an entity positioned in square (x,y).
     *
     * @param x the x
     * @param y the y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.dungeonObservers = new ArrayList<>();
    }

    /**
     * X position.
     *
     * @return the integer property
     */
    public IntegerProperty x() {
        return x;
    }

    /**
     * Y position.
     *
     * @return the integer property
     */
    public IntegerProperty y() {
        return y;
    }

    /**
     * Gets the y position.
     *
     * @return the y
     */
    public int getY() {
        return y().get();
    }

    /**
     * Gets the x position.
     *
     * @return the x
     */
    public int getX() {
        return x().get();
    }
    
    /**
     * Sets the image view.
     *
     * @param view the new image view
     */
    public void setImageView(ImageView view) {
    	this.view = view;
    }
    
    /**
     * Gets the image view.
     *
     * @return the image view
     */
    public ImageView getImageView() {
    	return view;
    }
    
    /**
     * Register dungeon observer.
     *
     * @param o the observer
     */
    @Override
	public void registerDungeonObserver(DungeonObserver o) {
		dungeonObservers.add(o);
	}
	
    /**
     * Removes the dungeon observer.
     *
     * @param o the observer
     */
    @Override
	public void removeDungeonObserver(DungeonObserver o) {
		dungeonObservers.remove(o);
	}
    
    /**
     * Notify dungeon observers.
     */
    @Override
	public void notifyDungeonObservers() {
		for (DungeonObserver o : dungeonObservers) {
			o.update(this);
		}
	}
    
	/**
	 * Checks if is obstacle to a player.
	 *
	 * @param p 	The player
	 * @return true, if it is an obstacle to the player
	 */
    public abstract boolean isObstacle(Player p);
    
	/**
	 * Checks if is obstacle to an enemy.
	 *
	 * @param e 	The enemy
	 * @return true, if it is obstacle to an enemy
	 */
    public abstract boolean isObstacle(Enemy e);
    
	/**
	 * Checks if is obstacle to an enemy using a particular movement strategy.
	 *
	 * @param e 	The enemy
	 * @return true, if it is obstacle to an enemy
	 */
    public abstract boolean isObstacle(EnemyMovementStrategy strategy);
    
	/**
	 * Determines if the entity blocks a boulder.
	 *
	 * @return true, if entity blocks the boulder
	 */
    
    
    public abstract boolean blocksBoulder();
    
	/**
	 * Pick up.
	 *
	 * @param p 		The player
	 * @return true, if successful
	 */
    public abstract boolean pickUp(Player p);
}
