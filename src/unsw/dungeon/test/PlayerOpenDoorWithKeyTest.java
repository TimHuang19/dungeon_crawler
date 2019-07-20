package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.BasicGoal;
import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Goal;
import unsw.dungeon.Key;
import unsw.dungeon.Player;

public class PlayerOpenDoorWithKeyTest {
	private Dungeon d;
	private Player p;
	private Door door;
	private Boulder b;

	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 5, 5);
		door = new Door(7, 5, 0);
		b = new Boulder(d, 7, 4);
		
		d.setPlayer(p);
		d.setGoals(new BasicGoal(Goal.TREASURE));
		d.addEntity(p);
		d.addEntity(door);
		d.addEntity(new Key(6, 5, 0));
		d.addEntity(new Key(5, 5, 1));
		d.addEntity(b);
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
		door = null;
		b = null;
	}

	@Test
	public void playerWithMatchingKeyShouldBeAbleToMoveIntoClosedDoorSquare() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Door X starting position is 7", 7, door.getX());
		assertEquals("Door Y starting position is 5", 5, door.getY());
		
		p.moveRight();
		
		assertTrue("Both player and key on square (6, 5)", d.getEntities(6, 5).size() == 2);

		p.pickUp();
		
		assertEquals("Player X new position is 6", 6, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
		
		assertTrue("Key disappears from (6, 5) square", d.getEntities(6, 5).size() == 1);
		
		p.moveRight();
		
		assertEquals("Player X must increase by 1", 7, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
	}
	
	@Test
	public void closedDoorShouldBeOpenAfterBeingOpened() {
		p.moveRight();
		p.pickUp();
		
		assertTrue("Door is in closed state", door.isClosed());
		
		p.moveRight();
		
		assertFalse("Door is in open state", door.isClosed());
	}
	
	@Test
	public void openDoorShouldBehaveLikeEmptySquare() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Door X starting position is 7", 7, door.getX());
		assertEquals("Door Y starting position is 5", 5, door.getY());
		
		assertEquals("Boulder X starting position is 7", 7, b.getX());
		assertEquals("Boulder Y starting position is 4", 4, b.getY());
		
		p.moveRight();
		p.pickUp();
		
		assertEquals("Player X new position is 6", 6, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
		
		p.moveRight();
		
		assertFalse("Door is in open state", door.isClosed());
		
		assertEquals("Player X new position is 7", 7, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
		
		p.moveRight();
		p.moveUp();
		p.moveUp();
		p.moveLeft();
		
		assertEquals("Player X new position is 7", 7, p.getX());
		assertEquals("Player Y new position is 3", 3, p.getY());
				
		p.moveDown();
		
		assertEquals("Player X new position is 7", 7, p.getX());
		assertEquals("Player Y new position is 4", 4, p.getY());
		
		assertEquals("Boulder X must be the same", 7, b.getX());
		assertEquals("Boulder Y new position is 5", 5, b.getY());
		
		p.moveDown();

		assertEquals("Player X new position is 7", 7, p.getX());
		assertEquals("Player Y new position is 5", 5, p.getY());
		
		assertEquals("Boulder X must be the same", 7, b.getX());
		assertEquals("Boulder Y new position is 6", 6, b.getY());
	}

}
