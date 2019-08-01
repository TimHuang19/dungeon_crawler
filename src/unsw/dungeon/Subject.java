package unsw.dungeon;


/**
 * The Interface Subject.
 */
public interface Subject {
	
	/**
	 * Register observer.
	 *
	 * @param o the observer
	 */
	public void registerObserver(Observer o);
	
	/**
	 * Removes the observer.
	 *
	 * @param o the observer
	 */
	public void removeObserver(Observer o);
	
	/**
	 * Notify observers.
	 */
	public void notifyObservers();
}
