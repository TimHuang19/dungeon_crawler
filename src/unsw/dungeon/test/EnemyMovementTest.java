package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.*;

public class EnemyMovementTest {
	private Dungeon d;
	private Player p;
	private Enemy e;
	
	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10,10);
		p = new Player(d,3,5);
		e = new Enemy(d,7,5);
		d.addEntity(p);
		d.setPlayer(p);
		d.addEntity(e);
	}

	@After
	public void tearDown() throws Exception {
		e = null;
		p = null;
		d = null;
	}

	@Test
	public void enemyShouldMoveUpWhenMoveUpIsCalled() {
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		e.moveUp();
		assertEquals("Enemy X position must be the same", 7, e.getX());
		assertEquals("Enemy Y position must decrease by 1", 4, e.getY());
	}

	@Test
	public void enemyShouldMoveDownWhenMoveDownIsCalled() {
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		e.moveDown();
		assertEquals("Enemy X position must be the same", 7, e.getX());
		assertEquals("Enemy Y position must increase by 1", 6, e.getY());
	}
	
	@Test
	public void enemyShouldMoveLeftWhenMoveLeftIsCalled() {
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		e.moveLeft();
		assertEquals("Enemy X position must decrease by 1", 6, e.getX());
		assertEquals("Enemy Y position must be the same", 5, e.getY());
	}
	
	@Test
	public void enemyShouldMoveRightWhenMoveRightIsCalled() {
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		e.moveRight();
		assertEquals("Enemy X position must increase by 1", 8, e.getX());
		assertEquals("Enemy Y position must be the same", 5, e.getY());
	}
	
	@Test
	public void dungeonBoundariesShouldRestrictEnemyMovement() {
		// Move Enemy to top left square
		e.x().set(0);
		e.y().set(0);
		
		e.moveUp();
		assertEquals("Enemy X position must be the same", 0, e.getX());
		assertEquals("Enemy Y position must be the same", 0, e.getY());

		e.moveLeft();
		assertEquals("Enemy X position must be the same", 0, e.getX());
		assertEquals("Enemy X position must be the same", 0, e.getY());
		
		// Move Enemy to bottom right square
		e.x().set(9);
		e.y().set(9);
		
		e.moveRight();
		assertEquals("Enemy X position must be the same", 9, e.getX());
		assertEquals("Enemy Y position must be the same", 9, e.getY());

		e.moveDown();
		assertEquals("Enemy X position must be the same", 9, e.getX());
		assertEquals("Enemy X position must be the same", 9, e.getY());
	}
	
	@Test
	public void enemyMovementShouldMakeEnemyChasePlayer() {
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		e.enemyMovement();
		assertEquals("Enemy X position must decrease by 1 to chase player", 6, e.getX());
		assertEquals("Enemy Y position must be the same", 5, e.getY());
	}
	
	@Test
	public void enemyShouldNotMoveIntoWall() {
		d.addEntity(new Wall(7,3));
		
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		e.moveUp();
		assertEquals("Enemy X position must be the same", 7, e.getX());
		assertEquals("Enemy Y position must decrease by 1", 4, e.getY());
		e.moveUp();
		assertEquals("Enemy X position must be the same", 7, e.getX());
		assertEquals("Enemy Y position must not change further as there is a wall", 4, e.getY());
	}
	
	@Test
	public void enemyShouldNotMoveIntoClosedDoor() {
		d.addEntity(new Door(7,7,0));
		
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		e.moveDown();
		assertEquals("Enemy X position must be the same", 7, e.getX());
		assertEquals("Enemy Y position must increase by 1", 6, e.getY());
		e.moveDown();
		assertEquals("Enemy X position must be the same", 7, e.getX());
		assertEquals("Enemy Y position must not change further as there is a closed door", 6, e.getY());
	}
	
	@Test
	public void enemyShouldNotMoveIntoBoulder() {
		d.addEntity(new Boulder(d,9,5));
		
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		e.moveRight();
		assertEquals("Enemy X position must increase by 1", 8, e.getX());
		assertEquals("Enemy Y position must be the same", 5, e.getY());
		e.moveRight();
		assertEquals("Enemy X position must not change further as there is a boulder", 8, e.getX());
		assertEquals("Enemy Y position must be the same", 5, e.getY());
	}
	
	@Test
	public void enemyShouldNotMoveIfThereIsNoPathToPlayer() {
		d.addEntity(new Wall(5,0));
		d.addEntity(new Wall(5,1));
		d.addEntity(new Wall(5,2));
		d.addEntity(new Wall(5,3));
		d.addEntity(new Wall(5,4));
		d.addEntity(new Wall(5,5));
		d.addEntity(new Wall(5,6));
		d.addEntity(new Wall(5,7));
		d.addEntity(new Wall(5,8));
		d.addEntity(new Wall(5,9));
		
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		e.enemyMovement();
		assertEquals("Enemy X position must be the same", 7, e.getX());
		assertEquals("Enemy Y position must be the same", 5, e.getY());
	}
	
	@Test
	public void enemyShouldChasePlayerAroundBlock() {
		d.addEntity(new Wall(5,5));
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		e.enemyMovement();
		assertEquals("Enemy X position must decrease by 1 to chase player", 6, e.getX());
		assertEquals("Enemy Y position must be the same", 5, e.getY());
		e.enemyMovement();
		assertEquals("Enemy X position must stay same to move around block", 6, e.getX());
		assertNotEquals("Enemy Y position must not be the same to move around block", 5, e.getY());
	}
}
