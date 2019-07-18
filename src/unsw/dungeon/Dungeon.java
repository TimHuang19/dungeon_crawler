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
    
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    }
    
    public ArrayList<Entity> getEntities(int x, int y) {
    	ArrayList<Entity> entities = new ArrayList<Entity>();
    	for (Entity e : this.entities) {
			if (x == e.getX() && y == e.getY()) {
				entities.add(e);
			}
    	}
    	return entities;
    }
    
    public ArrayList<Entity> getExplosionTargets(int x, int y) {
    	ArrayList<Entity> entities = new ArrayList<Entity>();
    	for (Entity e : this.entities) {
    		if ((x == e.getX() && y == e.getY()) ||
    				((x + 1) == e.getX() && y == e.getY()) ||
    				((x - 1) == e.getX() && y == e.getY()) ||
    				(x == e.getX() && (y + 1) == e.getY()) ||
    				(x == e.getX() && (y - 1) == e.getY())) {
    			entities.add(e);
    		}
    	}
    	return entities;
    }
}
