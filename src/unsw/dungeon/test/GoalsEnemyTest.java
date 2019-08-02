package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.*;

public class GoalsEnemyTest {
	private Dungeon d;
	private Player p;
	private Enemy e;
	
	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10,10);
		p = new Player(d,3,5);
		e = new Enemy(d,4,5);
		d.addEntity(p);
		d.setPlayer(p);
		d.addEntity(e);
		d.addEntity(new Exit(7,7));
	}

	@After
	public void tearDown() throws Exception {
		e = null;
		p = null;
		d = null;
	}
	@Test
	public void dungeonShouldCompleteIfPlayerKillsEnemyAndEnemyIsOnlyGoal() {
		d.setGoals(new BasicGoal(Goal.ENEMIES));
		p.setInvincible(true);
		//p.setInvincibleSteps(20);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		d.killEnemy(e);
		assertTrue("Game should complete when invincible player moves into only enemy",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldCompleteIfPlayerKillsEnemyAndEnemyIsLastGoal() {
		BasicGoal b1 = new BasicGoal(Goal.TREASURE);
		BasicGoal b2 = new BasicGoal(Goal.ENEMIES);
		ComplexGoal c1 = new ComplexGoal(Goal.AND);
		c1.addSubGoal(b1);
		c1.addSubGoal(b2);
		d.setGoals(c1);
		c1.setComplete(Goal.TREASURE, true);
		p.setInvincible(true);
		//p.setInvincibleSteps(20);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		d.killEnemy(e);
		assertTrue("Game should complete when player moves into only enemy",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldNotCompleteIfPlayerKillsEnemyAndEnemyIsLastGoal() {
		BasicGoal b1 = new BasicGoal(Goal.EXIT);
		BasicGoal b2 = new BasicGoal(Goal.ENEMIES);
		ComplexGoal c1 = new ComplexGoal(Goal.AND);
		c1.addSubGoal(b1);
		c1.addSubGoal(b2);
		d.setGoals(c1);
		p.setInvincible(true);
		//p.setInvincibleSteps(20);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		assertFalse("Game should complete when player kills only enemy if there are other goals to complete",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldCompleteOnlyWhenPlayerHasKilledAllEnemies() {
		e = new Enemy(d,5,5);
		d.addEntity(e);
		p.setInvincible(true);
		//p.setInvincibleSteps(20);
		d.setGoals(new BasicGoal(Goal.ENEMIES));
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		for (Entity f : d.getAllEntities()) {
			if (f instanceof Enemy) {
				d.killEnemy((Enemy) f);
				break;
			}
		}
		assertFalse("Game should not complete when player has only killed one of two enemies",d.isGameComplete());
		p.moveRight();
		p.moveRight();
		
		for (Entity f : d.getAllEntities()) {
			if (f instanceof Enemy) {
				d.killEnemy((Enemy) f);
				break;
			}
		}
		assertTrue("Game should complete when player has killed all enemies",d.isGameComplete());
	}
}
