package unsw.dungeon;

public interface DungeonSubject {
	public void registerDungeonObserver(DungeonObserver o);
	public void removeDungeonObserver(DungeonObserver o);
	public void notifyDungeonObservers();
}
