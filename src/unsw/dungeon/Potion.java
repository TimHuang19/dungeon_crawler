package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Potion extends Entity {
	private Timeline timeline;
	
	public Potion(int x, int y) {
		super(x, y);
		timeline = new Timeline();
	}

	@Override
	public boolean isObstacle(Player p) {
		return false;
	}

	@Override
	public boolean isObstacle(Enemy e) {
		return false;
	}

	@Override
	public boolean blocksBoulder() {
		return true;
	}

	@Override
	public boolean pickUp(Player p) {
		p.setInvincible(true);
		p.setInvincibleSteps(20);
		return true;
	}

}
