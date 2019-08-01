package unsw.dungeon;

/**
 * The Class ExplodeState.
 */
public class ExplodeState implements BombState {
	
	/** The bomb. */
	private Bomb bomb;
	
	/**
	 * Instantiates a new explode state.
	 *
	 * @param bomb 		The bomb
	 */
	public ExplodeState (Bomb bomb) {
		this.bomb = bomb;
	}
	
	/**
	 * Drop bomb.
	 */
	@Override
	public void dropBomb() {
	}

	/**
	 * Explode.
	 */
	@Override
	public void explode() {		
	}

}
