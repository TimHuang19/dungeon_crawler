package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.BasicGoal;
import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;
import unsw.dungeon.Switch;

public class PlayerMovementBlockedBoulderTest {
	private Dungeon d;
	private Player p;
	private Boulder b;

	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 5, 5);
		b = new Boulder(d, 5, 4);
		
		d.setPlayer(p);
		d.setGoals(new BasicGoal(Goal.TREASURE));
		d.addEntity(p);
		d.addEntity(b);
		d.addEntity(new Boulder(d, 5, 3));
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
		b = null;
	}

	@Test
	public void playerMovementShouldBeBlockedByBlockedBoulder() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Boulder X starting position is 5", 5, b.getX());
		assertEquals("Boulder Y starting position is 4", 4, b.getY());
		
		assertTrue("A non-Switch entity exists in (5, 3) square", d.getEntities(5, 3).size() == 1 && !(d.getEntities(5, 3).get(0) instanceof Switch));
				
		p.moveUp();
		
		assertEquals("Player X must be the same", 5, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
	}
	
	@Test
	public void playerShouldNotBeAbleToMoveBlockedBoulder() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Boulder X starting position is 5", 5, b.getX());
		assertEquals("Boulder Y starting position is 4", 4, b.getY());
		
		assertTrue("A non-Switch entity exists in (5, 3) square", d.getEntities(5, 3).size() == 1 && !(d.getEntities(5, 3).get(0) instanceof Switch));
		
		p.moveUp();
		
		assertEquals("Boulder X must be the same", 5, b.getX());
		assertEquals("Boulder Y must be the same", 4, b.getY());
	}

}
