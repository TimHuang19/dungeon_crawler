package unsw.dungeon;

public class LitState implements BombState {
	private Bomb bomb;
	
	public LitState(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void dropBomb() {
		// do nothing
	}
}
