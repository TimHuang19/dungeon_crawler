package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.*;

public class GoalsSwitchTest {
	private Dungeon d;
	private Player p;
	private Enemy e;
	
	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10,10);
		p = new Player(d,3,5);
		e = new Enemy(d,9,9);
		d.addEntity(p);
		d.setPlayer(p);
		d.addEntity(e);
		d.addEntity(new Exit(3,3));
		d.addEntity(new Boulder(d,4,5));
		d.addEntity(new Switch(5,5));
	}

	@After
	public void tearDown() throws Exception {
		e = null;
		p = null;
		d = null;
	}
	@Test
	public void dungeonShouldCompleteIfPlayerMovesBoulderOntoSwitchAndIsOnlyGoal() {
		d.setGoals(new BasicGoal(Goal.BOULDERS));
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		assertTrue("Game should complete when player moves boulder onto only switch",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldCompleteIfPlayerMovesBoulderOntoSwitchAndIsLastGoal() {
		BasicGoal b1 = new BasicGoal(Goal.BOULDERS);
		BasicGoal b2 = new BasicGoal(Goal.ENEMIES);
		ComplexGoal c1 = new ComplexGoal(Goal.AND);
		c1.addSubGoal(b1);
		c1.addSubGoal(b2);
		d.setGoals(c1);
		c1.setComplete(Goal.ENEMIES, true);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		assertTrue("Game should complete when player moves boulder onto only switch",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldNotCompleteIfPlayerBoulderOntoSwitchAndIsNotLastGoal() {
		BasicGoal b1 = new BasicGoal(Goal.BOULDERS);
		BasicGoal b2 = new BasicGoal(Goal.ENEMIES);
		ComplexGoal c1 = new ComplexGoal(Goal.AND);
		c1.addSubGoal(b1);
		c1.addSubGoal(b2);
		d.setGoals(c1);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		assertFalse("Game should complete when player moves boulder onto only switch if there are other goals to complete",d.isGameComplete());
	}
	@Test
	public void dungeonShouldCompleteOnlyWhenPlayerHasMovedLastBoulderOntoSwitchInsteadOfOnlyOne() {
		d.addEntity(new Boulder(d,4,4));
		d.addEntity(new Switch(4,3));
		d.setGoals(new BasicGoal(Goal.BOULDERS));
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		assertFalse("Game should complete when player has only covered one of two switches",d.isGameComplete());
		p.moveUp();
		assertTrue("Game should complete when player moves boulder onto last switch",d.isGameComplete());
	}
	
}
