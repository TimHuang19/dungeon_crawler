package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.*;

public class EnemyInvincibilityTest {
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
	public void enemyShouldDieWhenInvinciblePlayerMovesIntoIt() {
		p.setInvincible(true);
		//p.setInvincibleSteps(20);
		assertEquals("Player X start position is 6", 6, p.getX());
		assertEquals("Player Y start position is 5", 5, p.getY());
		e.setPlayerX(6);
		e.setPlayerY(5);
		e.setInvincible(true);
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		p.moveRight();
		assertEquals("Player X should increase by 1", 7, p.getX());
		assertEquals("Player Y should stay the same", 5, p.getY());
		boolean enemyKilled = true;
		for (Entity e : d.getAllEntities()) {
		    if (e instanceof Enemy) {
		         enemyKilled = false;
		    }
		}
		assertTrue("Enemy should be killed off after player moves into it", enemyKilled);
	}
	
	@Test
	public void enemyShouldRunAwayFromInvinciblePlayer() {
		p.setInvincible(true);
		//p.setInvincibleSteps(20);
		assertEquals("Player X start position is 6", 6, p.getX());
		assertEquals("Player Y start position is 5", 5, p.getY());
		e.setPlayerX(6);
		e.setPlayerY(5);
		e.setInvincible(true);
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		e.enemyMovement();
		assertEquals("Enemy X start should increase by 1 to run away from player", 8, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
	}
	/*
	@Test
	public void invinciblePlayerMovingTwiceShouldMakeEnemyMoveOnce() {
		p.x().set(3);
		p.y().set(5);
		p.setInvincible(true);
		p.setInvincibleSteps(20);
		assertEquals("Player X start position is 3", 3, p.getX());
		assertEquals("Player Y start position is 5", 5, p.getY());
		e.setPlayerX(3);
		e.setPlayerY(5);
		e.setInvincible(true);
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		p.moveRight();
		p.moveRight();
		assertEquals("Enemy X position must increase by 1 to run away from player", 8, e.getX());
		assertEquals("Enemy Y position must be the same", 5, e.getY());
	}
	
	@Test
	public void invinciblePlayerMovingFourTimesShouldMakeEnemyMoveTwice() {
		p.x().set(3);
		p.y().set(5);
		p.setInvincible(true);
		p.setInvincibleSteps(20);
		assertEquals("Player X start position is 3", 3, p.getX());
		assertEquals("Player Y start position is 5", 5, p.getY());
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		p.moveRight();
		p.moveRight();
		assertEquals("Enemy X position must increase by 1 to run away from player", 8, e.getX());
		assertEquals("Enemy Y position must be the same", 5, e.getY());
		p.moveLeft();
		p.moveRight();
		assertEquals("Enemy X position must increase by 1 twice to run away from player", 9, e.getX());
		assertEquals("Enemy Y position must be the same", 5, e.getY());
	}*/
	
	@Test
	public void enemyShouldNotMoveIfThereIsNoPathToInvinciblePlayer() {
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
		
		
		p.x().set(3);
		p.y().set(5);
		p.setInvincible(true);
		//p.setInvincibleSteps(20);
		assertEquals("Player X start position is 3", 3, p.getX());
		assertEquals("Player Y start position is 5", 5, p.getY());
		e.setPlayerX(3);
		e.setPlayerY(5);
		e.setInvincible(true);
		assertEquals("Enemy X start position is 7", 7, e.getX());
		assertEquals("Enemy Y start position is 5", 5, e.getY());
		e.enemyMovement();
		assertEquals("Enemy X position must be the same", 7, e.getX());
		assertEquals("Enemy Y position must be the same", 5, e.getY());
	}
}
