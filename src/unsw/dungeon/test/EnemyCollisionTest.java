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
		p = new Player(d,6,5);
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
		assertEquals("Player X start position is 6", 6, p.getX());
		assertEquals("Player Y start position is 5", 5, p.getY());
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		assertFalse("Game should not be over before anything happens", d.isGameOver());
		p.moveRight();
		assertEquals("Player X should increase by 1", 7, p.getX());
		assertEquals("Player Y should stay the same", 5, p.getY());
		assertTrue("Enemy and player should be registered as collided", e.collided(p));
		assertTrue("Game should end when player moves into enemy", d.isGameOver());
	}
	
	@Test
	public void playerShouldDieWhenEnemyMovesIntoSquareWithPlayer() {
		assertEquals("Player X start position is 6", 6, p.getX());
		assertEquals("Player Y start position is 5", 5, p.getY());
		e.setPlayerX(6);
		e.setPlayerY(5);
		e.setInvincible(false);
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		assertFalse("Game should not be over before anything happens", d.isGameOver());
		e.enemyMovement();
		assertEquals("Enemy X start should decrease by 1 to chase player", 6, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		assertTrue("Enemy and player should be registered as collided", e.collided(p));
		assertTrue("Game should end when enemy moves into player", d.isGameOver());
	}
}
