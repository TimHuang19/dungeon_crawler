package unsw.dungeon;

import java.util.ArrayList;

public class Boulder extends Entity implements Subject {
	private ArrayList<Observer> observers;
	private boolean onSwitch;
	private boolean destroyed;
	private Dungeon dungeon;

	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y);
		observers = new ArrayList<Observer>();
		this.registerObserver(dungeon);
		this.dungeon = dungeon;
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
	
	public void moveBoulder(int x, int y) {
		x().set(x);
		y().set(y);
		notifyObservers();
	}
	
	public void destroy() {
		destroyed = true;
		notifyObservers();
	}
	
	public boolean gotDestroyed() {
		return destroyed;
	}
	
	public boolean getOnSwitch() {
		return onSwitch;
	}
	
	public void setOnSwitch(boolean onSwitch) {
		this.onSwitch = onSwitch;
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
			o.update(this);
		}
	}

	@Override
	public boolean isObstacle(Player p) {
		if (blockedBoulder(p)) {
			return true;
		}
		return false;
	}
	
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
			if (targetY == -1 || targetY == dungeon.getHeight()) {
				return true;
			}
			
		} else {
			targetY = playerY;
			targetX = (boulderX > playerX) ? boulderX + 1 : boulderX - 1;
			if (targetX == -1 || targetX == dungeon.getWidth()) {
				return true;
			}
		}
				
		ArrayList<Entity> entities = dungeon.getEntities(targetX, targetY);
		
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

	@Override
	public boolean isObstacle(Enemy e) {
		return true;
	}

	@Override
	public boolean pickUp(Player p) {
		return false;
	}

	@Override
	public boolean blocksBoulder() {
		return true;
	}
}
