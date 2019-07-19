package unsw.dungeon;

import java.util.ArrayList;

public class Boulder extends Entity implements Subject {
	ArrayList<Observer> observers;
	private boolean onSwitch;
	private boolean destroyed;

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
	
	public void setOnSwitch(Boolean onSwitch) {
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

}
