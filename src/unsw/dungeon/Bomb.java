package unsw.dungeon;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * The Class Bomb.
 */
public class Bomb extends Entity {
	
	/** The unlit state. */
	private BombState unlitState;
	
	/** The lit state. */
	private BombState litState;
	
	/** The explode state. */
	private BombState explodeState;
	
	/** The actual state of the bomb */
	private BombState state;
	
	/** The dungeon. */
	private Dungeon dungeon;
	
	/** The imageView of bomb stage zero */
	private ImageView zero;
	
	/** The imageView of bomb stage one */
	private ImageView one;
	
	/** The imageView of bomb stage two */
	private ImageView two;
	
	/** The image view of an exploding bomb */
	private ImageView explode;
	
	/** The timeline. */
	private Timeline timeline;
	
	/**
	 * Instantiates a new bomb.
	 *
	 * @param dungeon 	The dungeon
	 * @param x 		The x position
	 * @param y 		The y position
	 */
	public Bomb(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.timeline = new Timeline();
		
		unlitState = new UnlitState(this);
		litState = new LitState(this);
		explodeState = new ExplodeState(this);
		
		state = unlitState;
	}
	
	/**
	 * Drop bomb.
	 */
	public void dropBomb() {
		state.dropBomb();
		notifyDungeonObservers();
		timeline.setCycleCount(1);
		KeyFrame kf = new KeyFrame(Duration.seconds(3), (ActionEvent event) -> explode());
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	/**
	 * Explode.
	 */
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
	
	/**
	 * Checks if bomb is lit.
	 *
	 * @return true, if is lit
	 */
	public boolean isLit() {
		return (state instanceof LitState) ? true : false;
	}
	
	/**
	 * Checks if bomb is exploded.
	 *
	 * @return true, if bomb is exploded
	 */
	public boolean isExplode() {
		return (state instanceof ExplodeState) ? true : false;
	}
	
    /**
     * Sets the state.
     *
     * @param state 	The new state
     */
    public void setState(BombState state) {
    	this.state = state;
    }
    
    /**
     * Gets the state.
     *
     * @return the state
     */
    public BombState getState() {
    	return state;
    }
    
    /**
     * Gets the lit state.
     *
     * @return the lit state
     */
    public BombState getLitState() {
    	return litState;
    }
    
    /**
     * Gets the explode state.
     *
     * @return the explode state
     */
    public BombState getExplodeState() {
    	return explodeState;
    }
    
    /**
     * Adds the bomb stage zero image.
     *
     * @param view 		The view of bomb stage zero
     */
    public void addZeroImage(ImageView view) {
    	this.zero = view;
    }
    
    /**
     * Gets the bomb stage zero image.
     *
     * @return the bomb stage zero image
     */
    public ImageView getZeroImage() {
    	return zero;
    }
    
    /**
     * Adds the bomb stage one image.
     *
     * @param view 		The view of bomb stage one
     */
    public void addOneImage(ImageView view) {
    	this.one = view;
    }
    
    /**
     * Gets the one image.
     *
     * @return the one image
     */
    public ImageView getOneImage() {
    	return one;
    }
    
    /**
     * Adds the bomb stage two image.
     *
     * @param view 		The view of bomb stage two
     */
    public void addTwoImage(ImageView view) {
    	this.two = view;
    }
    
    /**
     * Gets the bomb stage two image.
     *
     * @return the bomb stage two image
     */
    public ImageView getTwoImage() {
    	return two;
    }
    
    /**
     * Adds the explode image.
     *
     * @param view 		The view of an Exploded bomb
     */
    public void addExplodeImage(ImageView view) {
    	this.explode = view;
    }
    
    /**
     * Gets the explode image.
     *
     * @return the explode image
     */
    public ImageView getExplodeImage() {
    	return explode;
    }
    
	/**
	 * Checks if a bomb is an obstacle to an player
	 *
	 * @param p 		The player
	 * @return true, if it is obstacle
	 */
	@Override
	public boolean isObstacle(Player p) {
		return false;
	}

	/**
	 * Checks if a bomb is an obstacle to an enemy
	 *
	 * @param e 		The enemy
	 * @return true, if it is obstacle
	 */
	@Override
	public boolean isObstacle(Enemy e) {
		return false;
	}

	/**
	 * Determines if the bomb blocks a boulder.
	 *
	 * @return true, if bomb blocks the boulder
	 */
	@Override
	public boolean blocksBoulder() {
		return true;
	}

	/**
	 * Pick up.
	 *
	 * @param p 		The player
	 * @return true, if successful
	 */
	@Override
	public boolean pickUp(Player p) {
		if (state instanceof LitState) {
			return false;
		} else if (p.getBombs().size() == 3) {
			return false;
		}
		p.addBomb(this);
		dungeon.getPlayer().notifyDungeonObservers();
		return true;
	}

	/**
	 * Checks if a bomb is an obstacle to an enemy using a particular movement strategy
	 *
	 * @param e 		The enemy
	 * @return true, if it is obstacle
	 */
	@Override
	public boolean isObstacle(EnemyMovementStrategy strategy) {
		return false;
	}
    
}
