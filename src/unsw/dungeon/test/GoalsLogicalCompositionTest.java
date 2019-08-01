package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.*;

public class GoalsLogicalCompositionTest {
	private Dungeon d;
	private Player p;
	
	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10,10);
		p = new Player(d,3,5);
		d.addEntity(p);
		d.setPlayer(p);
		d.addEntity(new Exit(3,6));
		d.addEntity(new Treasure(3,4));
		d.addEntity(new Boulder(d,4,5));
		d.addEntity(new Switch(5,5));
	}

	@After
	public void tearDown() throws Exception {
		p = null;
		d = null;
	}
	
	@Test
	public void dungeonShouldCompleteIfPlayerCompletesEitherGoalsInORComposition() {
		BasicGoal b1 = new BasicGoal(Goal.BOULDERS);
		BasicGoal b2 = new BasicGoal(Goal.EXIT);
		ComplexGoal c1 = new ComplexGoal(Goal.OR);
		c1.addSubGoal(b1);
		c1.addSubGoal(b2);
		d.setGoals(c1);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		assertTrue("Game should complete when one goal has been completed in an OR composition",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldCompleteIfPlayerBothGoalsInAndComposition() {
		BasicGoal b1 = new BasicGoal(Goal.BOULDERS);
		BasicGoal b2 = new BasicGoal(Goal.EXIT);
		ComplexGoal c1 = new ComplexGoal(Goal.AND);
		c1.addSubGoal(b1);
		c1.addSubGoal(b2);
		d.setGoals(c1);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		p.moveLeft();
		assertFalse("Game should not be complete when not all goals have happened",d.isGameComplete());
		p.moveDown();
		assertTrue("Game should complete when both goals have been completed",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldNotCompleteIfExitIsNotCompletedLast() {
		BasicGoal b1 = new BasicGoal(Goal.BOULDERS);
		BasicGoal b2 = new BasicGoal(Goal.EXIT);
		ComplexGoal c1 = new ComplexGoal(Goal.AND);
		c1.addSubGoal(b1);
		c1.addSubGoal(b2);
		d.setGoals(c1);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveDown();
		p.moveUp();
		assertFalse("Game should not be complete when not all goals have happened",d.isGameComplete());
		p.moveRight();
		assertFalse("Game should not complete when exit is not reached last",d.isGameComplete());
	}
	@Test
	public void dungeonShouldCompleteInAnORCompoisitionFollowedByTopLevelAND() {
		BasicGoal b1 = new BasicGoal(Goal.BOULDERS);
		BasicGoal b2 = new BasicGoal(Goal.EXIT);
		BasicGoal b3 = new BasicGoal(Goal.TREASURE);
		ComplexGoal c1 = new ComplexGoal(Goal.AND);
		ComplexGoal c2 = new ComplexGoal(Goal.OR);
		c2.addSubGoal(b1);
		c2.addSubGoal(b2);
		c1.addSubGoal(c2);
		c1.addSubGoal(b3);
		d.setGoals(c1);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		p.moveLeft();
		assertFalse("Game should not be complete when not all goals have happened",d.isGameComplete());
		p.moveUp();
		p.pickUp();
		assertTrue("Game should complete when top level AND is completed",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldCompleteInAnORCompoisitionFollowedByTopLevelANDIncludingExit() {
		BasicGoal b1 = new BasicGoal(Goal.BOULDERS);
		BasicGoal b2 = new BasicGoal(Goal.EXIT);
		BasicGoal b3 = new BasicGoal(Goal.TREASURE);
		ComplexGoal c1 = new ComplexGoal(Goal.AND);
		ComplexGoal c2 = new ComplexGoal(Goal.OR);
		c2.addSubGoal(b1);
		c2.addSubGoal(b2);
		c1.addSubGoal(c2);
		c1.addSubGoal(b3);
		d.setGoals(c1);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveUp();
		p.pickUp();
		assertFalse("Game should not be complete when not all goals have happened",d.isGameComplete());
		p.moveDown();
		p.moveDown();
		assertTrue("Game should complete when top level AND is completed",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldCompleteInAnANDCompoisitionFollowedByTopLevelOR() {
		BasicGoal b1 = new BasicGoal(Goal.BOULDERS);
		BasicGoal b2 = new BasicGoal(Goal.EXIT);
		BasicGoal b3 = new BasicGoal(Goal.TREASURE);
		ComplexGoal c1 = new ComplexGoal(Goal.OR);
		ComplexGoal c2 = new ComplexGoal(Goal.AND);
		c2.addSubGoal(b1);
		c2.addSubGoal(b2);
		c1.addSubGoal(c2);
		c1.addSubGoal(b3);
		d.setGoals(c1);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		p.moveLeft();
		assertFalse("Game should not be complete when not all goals have happened",d.isGameComplete());
		p.moveDown();
		assertTrue("Game should complete when top level AND is completed",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldCompleteInAnyTopLevelORGoal() {
		BasicGoal b1 = new BasicGoal(Goal.BOULDERS);
		BasicGoal b2 = new BasicGoal(Goal.EXIT);
		BasicGoal b3 = new BasicGoal(Goal.TREASURE);
		ComplexGoal c1 = new ComplexGoal(Goal.OR);
		ComplexGoal c2 = new ComplexGoal(Goal.AND);
		c2.addSubGoal(b1);
		c2.addSubGoal(b2);
		c1.addSubGoal(c2);
		c1.addSubGoal(b3);
		d.setGoals(c1);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveUp();
		p.pickUp();
		assertTrue("Game should complete when top level OR is completed",d.isGameComplete());
	}
}