package unsw.dungeon;

public class Enemy extends Entity implements Observer {
	
	private Dungeon dungeon;
	private int playerX, playerY;
	
	public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }
	
	public void update(Subject obj) {
		if(obj instanceof Player) {
			update((Player) obj);
		}
	}
	
	public void update(Player obj) {
		this.playerX = obj.getX();
		this.playerY = obj.getY();
		if(hasCollided()) dungeon.gameOver();
	}
	
	public Boolean hasCollided() {
		if (this.playerX == getX() && this.playerY == getY()) {
			return true;
		}
		return false;
	}
	
    private void moveUp() {
    	Entity e = dungeon.getEntity(getX(), getY() - 1);

        if (getY() > 0 && !isObstacle(e)) {
            y().set(getY() - 1);
            
        }
    }

    private void moveDown() {
    	Entity e = dungeon.getEntity(getX(), getY() + 1);

        if (getY() < dungeon.getHeight() - 1 && !isObstacle(e)) {
            y().set(getY() + 1);
        }
    }

    private void moveLeft() {
    	Entity e = dungeon.getEntity(getX() - 1, getY());

        if (getX() > 0 && !isObstacle(e)) {
            x().set(getX() - 1);
        }
    }

    private void moveRight() {
    	Entity e = dungeon.getEntity(getX() + 1, getY());

        if (getX() < dungeon.getWidth() - 1 && !isObstacle(e) ){
            x().set(getX() + 1);
        }
    }
    
    public void enemyMovement() {
    	moveUp();
    	moveLeft();
    	moveDown();
    	moveRight();
    }
    
    private boolean isObstacle(Entity e) {

    	if (e == null) {
    		return false;
    	} 
    	else return true;
    }
}
