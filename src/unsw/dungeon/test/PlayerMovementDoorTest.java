package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.BasicGoal;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Goal;
import unsw.dungeon.Key;
import unsw.dungeon.Player;

public class PlayerMovementDoorTest {
	private Dungeon d;
	private Player p;

	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 5, 5);
		
		d.setPlayer(p);
		d.setGoals(new BasicGoal(Goal.TREASURE));
		d.addEntity(p);
		d.addEntity(new Door(7, 5, 0));
		d.addEntity(new Key(6, 5, 0));
		d.addEntity(new Key(5, 5, 1));
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
	}

	@Test
	public void playerWithoutKeyShouldBeBlockedByClosedDoor() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		p.moveRight();
		
		assertEquals("Player X new position is 6", 6, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
		
		p.moveRight();
		
		assertEquals("Player X must be the same", 6, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
	}
	
	@Test
	public void playerWithWrongKeyShouldBeBlockedByClosedDoor() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		p.pickUp();
		p.moveRight();
		
		assertEquals("Player X new position is 6", 6, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
		
		p.moveRight();
		
		assertEquals("Player X must be the same", 6, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
	}
	
	@Test
	public void playerWithMatchingKeyShouldNotBeBlockedByClosedDoor() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		p.moveRight();
		p.pickUp();
		
		assertEquals("Player X new position is 6", 6, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
		
		p.moveRight();
		
		assertEquals("Player X must increase by 1", 7, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
	}

}
