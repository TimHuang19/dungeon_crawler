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

public class PlayerMoveBoulderTest {
	private Dungeon d;
	private Player p;
	private Boulder b;
	private Switch s;
	
	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 5, 5);
		b = new Boulder(d, 5, 4);
		s = new Switch(5, 2);
		
		d.setPlayer(p);
		d.setGoals(new BasicGoal(Goal.TREASURE));
		d.addEntity(p);
		d.addEntity(b);
		d.addEntity(s);
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
		b = null;
	}

	@Test
	public void playerShouldBeAbleToMoveBoulderIfNotBlocked() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Boulder X starting position is 5", 5, b.getX());
		assertEquals("Boulder Y starting position is 4", 4, b.getY());
		
		assertEquals("Switch X starting position", 5, s.getX());
		assertEquals("Switch Y starting position", 2, s.getY());
		
		assertTrue("(5, 3) square is empty", d.getEntities(5, 3).size() == 0);
		
		p.moveUp();
		
		assertEquals("Player X new position is 5", 5, p.getX());
		assertEquals("Player Y new position is 4", 4, p.getY());
		
		assertEquals("Boulder X new position is 5", 5, b.getX());
		assertEquals("Boulder Y new position is 3", 3, b.getY());
		
		
		assertTrue("(5, 3) square only contains a Switch entity ", d.getEntities(5, 2).size() == 1 && d.getEntities(5, 2).get(0) instanceof Switch);
		
		p.moveUp();
		
		assertEquals("Player X new position is 5", 5, p.getX());
		assertEquals("Player Y new position is 3", 3, p.getY());
		
		assertEquals("Boulder X new position is 5", 5, b.getX());
		assertEquals("Boulder Y new position is 2", 2, b.getY());
		
		assertEquals("Switch X same position", 5, s.getX());
		assertEquals("Switch Y same position", 2, s.getY());
		
	}
	
	@Test
	public void playerShouldNotBeAbleToMoveBlockedBoulder() {
		d.addEntity(new Boulder(d, 5, 3));
		
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
