package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void moveUp() {
        if (getY() > 0 && !isObstacle(getX(), getY() - 1))
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && !isObstacle(getX(), getY() + 1))
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0 && !isObstacle(getX() - 1, getY()))
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && !isObstacle(getX() + 1, getY()))
            x().set(getX() + 1);
    }
    
    private boolean isObstacle(int x, int y) {
    	Entity e = dungeon.getEntity(x, y);
    	
    	if (e == null) {
    		return false;
    	} else if (e instanceof Wall) {
    		return true;
    	} else if (e instanceof Door) {
    		Door d = (Door) e;
    		
    		if (d.isClosed()) {
    			return true;
    		}
    	}
    	return false;
    }
}
