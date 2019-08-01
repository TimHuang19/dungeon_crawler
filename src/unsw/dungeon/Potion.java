package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 * The Class Potion.
 */
public class Potion extends Entity {
	
	/** The timeline. */
	private Timeline timeline;
	
	/**
	 * Instantiates a new potion.
	 *
	 * @param x 		The x position
	 * @param y 		The y position
	 */
	public Potion(int x, int y) {
		super(x, y);
		timeline = new Timeline();
	}

	/**
	 * Checks if is obstacle to a player.
	 *
	 * @param p 	The player
	 * @return true, if it is an obstacle to the player
	 */
	@Override
	public boolean isObstacle(Player p) {
		return false;
	}

	/**
	 * Checks if is obstacle to an enemy.
	 *
	 * @param e 	The enemy
	 * @return true, if it is obstacle to an enemy
	 */
	@Override
	public boolean isObstacle(Enemy e) {
		return false;
	}

	/**
	 * Determines if the potion blocks a boulder.
	 *
	 * @return true, if potion blocks the boulder
	 */
	@Override
	public boolean blocksBoulder() {
		return true;
	}

	/**
	 * Pick up.
	 *
	 * @param p 	The player
	 * @return true, if successful
	 */
	@Override
	public boolean pickUp(Player p) {
		p.setInvincible(true);
		p.setInvincibleSteps(20);
		return true;
	}

}
