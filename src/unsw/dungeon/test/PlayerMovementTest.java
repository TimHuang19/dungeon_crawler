package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;

public class PlayerMovementTest {
	private Dungeon d;
	private Player p;

	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 5, 5);
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
	}

	@Test
	public void playerShouldMoveUpWhenMoveUpIsCalled() {
		p.moveUp();
		assertEquals("Player X position must be the same", 5, p.getX());
		assertEquals("Player Y position must decrease by 1", 4, p.getY());
	}
	
	@Test
	public void playerShouldMoveDownWhenMoveDownIsCalled() {
		p.moveDown();
		assertEquals("Player X position must be the same", 5, p.getX());
		assertEquals("Player Y position must increase by 1", 6, p.getY());
	}
	
	@Test
	public void playerShouldMoveRightWhenMoveRightIsCalled() {
		p.moveRight();
		assertEquals("Player X position must increase by 1", 6, p.getX());
		assertEquals("Player Y position must remain the same", 5, p.getY());
	}
	
	@Test
	public void playerShouldMoveLeftWhenMoveLeftIsCalled() {
		p.moveLeft();
		assertEquals("Player X position must decrease by 1", 4, p.getX());
		assertEquals("Player Y position must remain the same", 5, p.getY());
	}
	
	@Test
	public void dungeonBoundariesShouldRestrictPlayerMovement() {
		// Move player to top left square
		p.x().set(0);
		p.y().set(0);
		
		p.moveUp();
		assertEquals("Player X position must be the same", 0, p.getX());
		assertEquals("Player Y position must be the same", 0, p.getY());

		p.moveLeft();
		assertEquals("Player X position must be the same", 0, p.getX());
		assertEquals("Player X position must be the same", 0, p.getY());
		
		// Move player to bottom right square
		p.x().set(9);
		p.y().set(9);
		
		p.moveRight();
		assertEquals("Player X position must be the same", 9, p.getX());
		assertEquals("Player Y position must be the same", 9, p.getY());

		p.moveDown();
		assertEquals("Player X position must be the same", 9, p.getX());
		assertEquals("Player X position must be the same", 9, p.getY());
	}

}
