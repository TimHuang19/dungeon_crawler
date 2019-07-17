/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public Entity getEntity(int x, int y) {
    	for (Entity e : entities) {
			if (x == e.getX() && y == e.getY()) {
				return e;
			}
    	}
    	return null;
    }
    
    public boolean isWall(int x, int y) {
    	for (Entity e : entities) {
    		if (e instanceof Wall) {
    			if (x == e.getX() && y == e.getY()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    public boolean isClosedDoor(int x, int y) {
    	for (Entity e : entities) {
    		if (e instanceof Door) {
    			if (x == e.getX() && y == e.getY() && ((Door) e).isClosed()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
}
