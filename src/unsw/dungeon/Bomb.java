package unsw.dungeon;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Bomb extends Entity {
	private BombState unlitState;
	private BombState litState;
	
	private BombState state;
	private Dungeon dungeon;
	
	public Bomb(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		
		unlitState = new UnlitState(this);
		litState = new LitState(this);
		
		state = unlitState;
	}
	
	public void dropBomb() {
		state.dropBomb();
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		KeyFrame kf = new KeyFrame(Duration.seconds(3), (ActionEvent event) -> explode());
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	public void explode() {
		ArrayList<Entity> targets;
		targets = dungeon.getExplosionTargets(getX(), getY());
		notifyDungeonObservers();
		for (Entity e : targets) {
			if (e instanceof Player) {
				if (!((Player) e).isInvincible()) {
					dungeon.gameOver();
					dungeon.removeEntity(this);
					return;
				}
			} else if (e instanceof Boulder) {
				Boulder b = (Boulder) e;
				b.destroy();
			} else if (e instanceof Enemy) {
				dungeon.killEnemy((Enemy) e);
			}
		}
		dungeon.removeEntity(this);
	}

    public void setState(BombState state) {
    	this.state = state;
    }
    
    public BombState getState() {
    	return state;
    }
    
    public BombState getLitState() {
    	return litState;
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
		if (state instanceof LitState) {
			return false;
		} else if (p.getBombs().size() == 3) {
			return false;
		}
		p.addBomb(this);
		return true;
	}
    
}
