package unsw.dungeon;


/**
 * The Interface DungeonSubject.
 */
public interface DungeonSubject {
	
	/**
	 * Register dungeon observer.
	 *
	 * @param o the o
	 */
	public void registerDungeonObserver(DungeonObserver o);
	
	/**
	 * Removes the dungeon observer.
	 *
	 * @param o the o
	 */
	public void removeDungeonObserver(DungeonObserver o);
	
	/**
	 * Notify dungeon observers.
	 */
	public void notifyDungeonObservers();
}
