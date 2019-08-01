package unsw.dungeon;


/**
 * An asynchronous update interface for receiving notifications
 * about Dungeon information as the Dungeon is constructed.
 */
public interface DungeonObserver{
	
	/**
	 * This method is called when information about an Dungeon
	 * which was previously requested using an asynchronous
	 * interface becomes available.
	 *
	 * @param obj the obj
	 */
	public void update(DungeonSubject obj);
}
