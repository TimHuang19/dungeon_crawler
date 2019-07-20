package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.BasicGoal;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

public class PlayerMovementWallTest {
	private Dungeon d;
	private Player p;

	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 5, 5);
		
		d.setPlayer(p);
		d.setGoals(new BasicGoal(Goal.TREASURE));
		d.addEntity(p);
		d.addEntity(new Wall(6, 5));
		d.addEntity(new Wall(4, 5));
		d.addEntity(new Wall(5, 4));
		d.addEntity(new Wall(5, 6));
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
	}


	@Test
	public void wallAbovePlayerShouldBlockMoveUp() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		p.moveUp();
		assertEquals("Player X must be the same", 5, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
	}
	
	@Test
	public void wallBelowPlayerShouldBlockMoveDown() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		p.moveDown();
		assertEquals("Player X must be the same", 5, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
	}
	
	@Test
	public void wallRightOfPlayerShouldBlockMoveRight() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		p.moveRight();
		assertEquals("Player X must be the same", 5, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
	}
	
	@Test
	public void wallLeftOfPlayerShouldBlockMoveLeft() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		p.moveLeft();
		assertEquals("Player X must be the same", 5, p.getX());
		assertEquals("Player Y must be the same", 5, p.getY());
	}

}
