package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.*;

public class EnemyCollisionTest {
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
		
		d.setGoals(new BasicGoal(Goal.EXIT));
	}

	@After
	public void tearDown() throws Exception {
		e = null;
		p = null;
		d = null;
	}

	@Test
	public void playerShouldDieWhenMovedIntoSquareWithEnemy() {
		p.x().set(6);
		p.y().set(5);
		assertEquals("Player X start position is 6", 6, p.getX());
		assertEquals("Player Y start position is 5", 5, p.getY());
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		p.moveRight();
		assertEquals("Player X should increase by 1", 7, p.getX());
		assertEquals("Player Y should stay the same", 5, p.getY());
		assertTrue("Game should end when player moves into enemy", d.isGameOver());
	}
	
	@Test
	public void playerShouldDieWhenEnemyMovesIntoSquareWithPlayer() {
		p.x().set(6);
		p.y().set(5);
		assertEquals("Player X start position is 6", 6, p.getX());
		assertEquals("Player Y start position is 5", 5, p.getY());
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		e.enemyMovement();
		assertTrue("Game should end when player moves into enemy", d.isGameOver());
	}
}
