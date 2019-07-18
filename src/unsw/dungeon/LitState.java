package unsw.dungeon;

public class LitState implements BombState {
	Bomb bomb;
	
	public LitState(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void dropBomb() {
		// do nothing
	}
}
