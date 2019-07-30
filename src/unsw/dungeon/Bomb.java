package unsw.dungeon;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Bomb extends Entity {
	private BombState unlitState;
	private BombState litState;
	private BombState explodeState;
	
	private BombState state;
	private Dungeon dungeon;
	
	private ImageView zero;
	private ImageView one;
	private ImageView two;
	private ImageView explode;
	
	public Bomb(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		
		unlitState = new UnlitState(this);
		litState = new LitState(this);
		explodeState = new ExplodeState(this);
		
		state = unlitState;
	}
	
	public void dropBomb() {
		state.dropBomb();
		notifyDungeonObservers();
		Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		KeyFrame kf = new KeyFrame(Duration.seconds(3), (ActionEvent event) -> explode());
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	public void explode() {
		ArrayList<Entity> targets;
		targets = dungeon.getExplosionTargets(getX(), getY());
		state.explode();
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
	
	public boolean isLit() {
		return (state instanceof LitState) ? true : false;
	}
	
	public boolean isExplode() {
		return (state instanceof ExplodeState) ? true : false;
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
    
    public BombState getExplodeState() {
    	return explodeState;
    }
    
    public void addZeroImage(ImageView view) {
    	this.zero = view;
    }
    
    public ImageView getZeroImage() {
    	return zero;
    }
    
    public void addOneImage(ImageView view) {
    	this.one = view;
    }
    
    public ImageView getOneImage() {
    	return one;
    }
    
    public void addTwoImage(ImageView view) {
    	this.two = view;
    }
    
    public ImageView getTwoImage() {
    	return two;
    }
    
    public void addExplodeImage(ImageView view) {
    	this.explode = view;
    }
    
    public ImageView getExplodeImage() {
    	return explode;
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
