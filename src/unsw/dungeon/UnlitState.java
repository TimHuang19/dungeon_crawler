package unsw.dungeon;

/**
 * The Class UnlitState.
 */
public class UnlitState implements BombState {
	
	/** The bomb. */
	private Bomb bomb;
	
	/**
	 * Instantiates a new unlit state.
	 *
	 * @param bomb the bomb
	 */
	public UnlitState(Bomb bomb) {
		this.bomb = bomb;
	}

	/**
	 * Drop bomb.
	 */
	@Override
	public void dropBomb() {
		bomb.setState(bomb.getLitState());
	}

	/**
	 * Explode.
	 */
	@Override
	public void explode() {		
	}
}
