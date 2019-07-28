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
    private IntegerProperty x, y;
    private ImageView view;
    private ArrayList<DungeonObserver> dungeonObservers;
    
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.dungeonObservers = new ArrayList<>();
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    public void setImageView(ImageView view) {
    	this.view = view;
    }
    
    public ImageView getImageView() {
    	return view;
    }
    
    @Override
	public void registerDungeonObserver(DungeonObserver o) {
		dungeonObservers.add(o);
	}
	
    @Override
	public void removeDungeonObserver(DungeonObserver o) {
		dungeonObservers.remove(o);
	}
    
    @Override
	public void notifyDungeonObservers() {
		for (DungeonObserver o : dungeonObservers) {
			o.update(this);
		}
	}
    
    public abstract boolean isObstacle(Player p);
    
    public abstract boolean isObstacle(Enemy e);
    
    public abstract boolean blocksBoulder();
    
    public abstract boolean pickUp(Player p);
}
