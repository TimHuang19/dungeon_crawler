package unsw.dungeon;

public class UnlitState implements BombState {
	Bomb bomb;
	
	public UnlitState(Bomb bomb) {
		this.bomb = bomb;
	}

	@Override
	public void dropBomb() {
		bomb.setState(bomb.getLitState());
	}
}