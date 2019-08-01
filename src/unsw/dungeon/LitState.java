package unsw.dungeon;

/**
 * The Class LitState.
 */
public class LitState implements BombState {
	
	/** The bomb. */
	private Bomb bomb;
	
	/**
	 * Instantiates a new lit state.
	 *
	 * @param bomb the bomb
	 */
	public LitState(Bomb bomb) {
		this.bomb = bomb;
	}

	/**
	 * Drop bomb.
	 */
	@Override
	public void dropBomb() {
		// do nothing
	}

	/**
	 * Explode.
	 */
	@Override
	public void explode() {
		bomb.setState(bomb.getExplodeState());
	}
}