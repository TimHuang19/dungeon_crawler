package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.BasicGoal;
import unsw.dungeon.Bomb;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;

public class PlayerPickUpPotionTest {
	private Dungeon d;
	private Player p;
	private Potion pot;

	@Before
	public void setUp() throws Exception {
		d = new Dungeon(20, 20);
		p = new Player(d, 5, 5);
		pot = new Potion(5, 5);
		
		d.setPlayer(p);
		d.setGoals(new BasicGoal(Goal.TREASURE));
		d.addEntity(p);
		d.addEntity(pot);
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
		pot = null;
	}

	@Test
	public void playerShouldBeAbletoPickUpPotionUsingPickUp() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Potion X starting position is 5", 5, pot.getX());
		assertEquals("Potion Y starting position is 5", 5, pot.getY());
		
		boolean potionAtSquare = false;
		
		for (Entity e : d.getEntities(5, 5)) {
			if (e instanceof Potion) {
				potionAtSquare = true;
			}
		}
		
		assertTrue("Potion in square (5, 5)", potionAtSquare);			
		
		p.pickUp();
		
		boolean potionInDungeon = false;
		
		for (Entity e : d.getAllEntities()) {
			if (e instanceof Potion) {
				potionInDungeon = true;
			}
		}
		
		assertTrue("Potion no longer in dungeon", !potionInDungeon);		
	}
	
	@Test
	public void playerShouldBecomeInvincibleAfterPickingUpPotion() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Potion X starting position is 5", 5, pot.getX());
		assertEquals("Potion Y starting position is 5", 5, pot.getY());
		
		assertTrue("Player is not invincible", !p.isInvincible());
		
		p.pickUp();
		
		assertTrue("Player is invincible", p.isInvincible());
	}
	
	@Test
	public void playerShouldLoseInvincibilityAfter20Steps() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Potion X starting position is 5", 5, pot.getX());
		assertEquals("Potion Y starting position is 5", 5, pot.getY());
		
		assertTrue("Player is not invincible", !p.isInvincible());
		//assertEquals("Number of invincible steps should be 0", 0, p.getInvincibleSteps());
		
		p.pickUp();
		
		assertTrue("Player is invincible", p.isInvincible());
		
		//assertEquals("Number of invincible steps should be 20", 20, p.getInvincibleSteps());
		
		int stepCounter = 0;
		while (p.isInvincible()) {
			if (stepCounter % 4 == 0) {
				p.moveRight();
			} else if (stepCounter % 4 == 1) {
				p.moveDown();
			} else if (stepCounter % 4 == 2) {
				p.moveLeft();
			} else {
				p.moveUp();
			}
			stepCounter++;
		}
		
		assertEquals("Invincibility status expires after 20 steps", 20, stepCounter);
		//assertEquals("Number of invincible steps should be 0", 0, p.getInvincibleSteps());
		assertTrue("Player is not invincible", !p.isInvincible());
	}
	
	@Test
	public void invinciblePlayerShouldNotBeKilledByBombExplosion() {
		Bomb b = new Bomb(d, 6, 5);
		d.addEntity(b);
		
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Potion X starting position is 5", 5, pot.getX());
		assertEquals("Potion Y starting position is 5", 5, pot.getY());
		
		assertEquals("Bomb X starting position is 6", 6, b.getX());
		assertEquals("Bomb Y starting position is 5", 5, b.getY());
		
		assertTrue("Player is not invincible", !p.isInvincible());
		
		p.pickUp();
		
		assertTrue("Player is invincible", p.isInvincible());
				
		b.explode();
		
		assertTrue("Game is not over", !d.isGameOver());
	}
	
	@Test
	public void invinciblePlayerShouldNotBeKilledByEnemy() {
		Enemy e = new Enemy(d, 6, 5);
		d.addEntity(e);
		d.setGoals(new BasicGoal(Goal.TREASURE));
		
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Potion X starting position is 5", 5, pot.getX());
		assertEquals("Potion Y starting position is 5", 5, pot.getY());
		
		assertEquals("Enemy X starting position is 6", 6, e.getX());
		assertEquals("Enemy Y starting position is 5", 5, e.getY());
		
		assertTrue("Player is not invincible", !p.isInvincible());
		
		p.pickUp();
		
		assertTrue("Player is invincible", p.isInvincible());
		
		p.moveRight();
		
		assertEquals("Player X new position is 6", 6, p.getX());
		assertEquals("Player Y new position is 5", 5, p.getY());
		
		assertTrue("Game is not over", !d.isGameOver());
	}
	
	@Test
	public void nonInvinciblePlayerShouldBeKilledByEnemy() {
		Enemy e = new Enemy(d, 6, 5);
		d.addEntity(e);
		d.setGoals(new BasicGoal(Goal.TREASURE));
		
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Potion X starting position is 5", 5, pot.getX());
		assertEquals("Potion Y starting position is 5", 5, pot.getY());
		
		assertEquals("Enemy X starting position is 6", 6, e.getX());
		assertEquals("Enemy Y starting position is 5", 5, e.getY());
		
		assertTrue("Player is not invincible", !p.isInvincible());
		
		p.moveRight();
		
		assertEquals("Player X new position is 6", 6, p.getX());
		assertEquals("Player Y new position is 5", 5, p.getY());
		
		
		assertTrue("Game is over", d.isGameOver());
	}

	@Test
	public void pickingUpNewPotionShouldRefresh20StepCounter() {
		Potion pot2 = new Potion(6, 5);
		d.addEntity(pot2);
		
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("First Potion X starting position is 5", 5, pot.getX());
		assertEquals("First Potion Y starting position is 5", 5, pot.getY());
		
		assertEquals("Second Potion X starting position is 6", 6, pot2.getX());
		assertEquals("Second Potion Y starting position is 5", 5, pot2.getY());
		
		assertTrue("Player is not invincible", !p.isInvincible());
		
		p.pickUp();
		
		assertTrue("Player is invincible", p.isInvincible());
		
		p.moveRight();
		
		assertTrue("Player is invincible", p.isInvincible());
		//assertEquals("Number of invincible steps should be reduced by 1 to 19", 19, p.getInvincibleSteps());
		
		p.pickUp();
		
		assertTrue("Player is invincible", p.isInvincible());
		//assertEquals("Number of invincible steps should be refreshed to 20", 20, p.getInvincibleSteps());
	}

}
