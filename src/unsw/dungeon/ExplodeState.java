package unsw.dungeon;

public class ExplodeState implements BombState {
	private Bomb bomb;
	
	public ExplodeState (Bomb bomb) {
		this.bomb = bomb;
	}
	
	@Override
	public void dropBomb() {
	}

	@Override
	public void explode() {		
	}

}
