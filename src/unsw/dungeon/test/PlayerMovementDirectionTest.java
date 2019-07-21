package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.BasicGoal;
import unsw.dungeon.Direction;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;

public class PlayerMovementDirectionTest {
	private Dungeon d;
	private Player p;

	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 5, 5);
		
		d.setPlayer(p);
		d.setGoals(new BasicGoal(Goal.TREASURE));
		d.addEntity(p);
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
	}


	@Test
	public void playerShouldBeFacingUpAfterMoveUp() {
		p.moveUp();
		assertEquals("Player must be facing up", Direction.UP, p.getDirection());
	}
	
	@Test
	public void playerShouldBeFacingDownAfterMoveDown() {
		p.moveDown();
		assertEquals("Player must be facing down", Direction.DOWN, p.getDirection());
	}
	
	@Test
	public void playerShouldBeFacingRightAfterMoveRight() {
		p.moveRight();
		assertEquals("Player must be facing right", Direction.RIGHT, p.getDirection());
	}
	
	@Test
	public void playerShouldBeFacingLeftAfterMoveLeft() {
		p.moveLeft();
		assertEquals("Player must be facing left", Direction.LEFT, p.getDirection());
	}

}
