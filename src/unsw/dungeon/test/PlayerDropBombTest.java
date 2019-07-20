package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.BasicGoal;
import unsw.dungeon.Bomb;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Goal;
import unsw.dungeon.LitState;
import unsw.dungeon.Player;
import unsw.dungeon.UnlitState;

public class PlayerDropBombTest {
	private Dungeon d;
	private Player p;
	private Bomb b;

	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 5, 5);
		b = new Bomb(d, 5, 5);
		
		d.setPlayer(p);
		d.setGoals(new BasicGoal(Goal.TREASURE));
		d.addEntity(p);
		d.addEntity(b);
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
		b = null;
	}
	
	@Test
	public void droppedBombShouldBeLitAndInThePlayersSquare() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Bomb X starting position is 5", 5, b.getX());
		assertEquals("Bomb Y starting position is 5", 5, b.getY());
		
		assertTrue("Bomb is in unlit state before pick up", b.getState() instanceof UnlitState);
		
		p.pickUp();
		p.moveRight();
		p.moveRight();
		
		assertEquals("Player X new position is 7", 7, p.getX());
		assertEquals("Player Y new position is 5", 5, p.getY());
		
		p.dropBomb();
		
		assertTrue("Bomb is in lit state after being dropped", b.getState() instanceof LitState);
		assertEquals("Bomb X new position is 5", 7, b.getX());
		assertEquals("Bomb Y new position is 5", 5, b.getY());
	}
	
	@Test
	public void playerNotHoldingBombsDroppingBombShouldNotAffectAnything() {
		p.moveRight();
		p.moveRight();
		
		assertTrue("Player not holding any bombs", p.getBombs().size() == 0);
		assertEquals("Player X starting position is 7", 7, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		assertTrue("Player is the only entity at (7, 5) square", d.getEntities(7, 5).size() == 1);
		assertTrue("Total of 2 entities in the dungeon", d.getAllEntities().size() == 2);
		assertTrue("Game is not over", !d.isGameOver());
		assertTrue("Game is not complete", !d.isGameComplete());
		
		p.dropBomb();
		
		assertTrue("Player is still the only entity at (7, 5) square after dropping nothing", d.getEntities(7, 5).size() == 1);
		assertTrue("Game is still not over", !d.isGameOver());
		assertTrue("Game is still not complete", !d.isGameComplete());
		assertTrue("Still total of 2 entities in the dungeon", d.getAllEntities().size() == 2);

	}

}
