package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.*;

public class GoalsTreasureTest {
	private Dungeon d;
	private Player p;
	private Enemy e;
	
	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10,10);
		p = new Player(d,3,5);
		e = new Enemy(d,7,7);
		d.addEntity(p);
		d.setPlayer(p);
		d.addEntity(e);
		d.addEntity(new Exit(3,3));
		d.addEntity(new Treasure(4,5));
	}

	@After
	public void tearDown() throws Exception {
		e = null;
		p = null;
		d = null;
	}
	@Test
	public void dungeonShouldCompleteIfPlayerPicksUpTreasureAndTreasureIsOnlyGoal() {
		d.setGoals(new BasicGoal(Goal.TREASURE));
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		p.pickUp();
		assertTrue("Game should complete when player picks up only treasure",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldCompleteIfPlayerPicksUpTreasureAndTreasureIsLastGoal() {
		BasicGoal b1 = new BasicGoal(Goal.TREASURE);
		BasicGoal b2 = new BasicGoal(Goal.ENEMIES);
		ComplexGoal c1 = new ComplexGoal(Goal.AND);
		c1.addSubGoal(b1);
		c1.addSubGoal(b2);
		d.setGoals(c1);
		c1.setComplete(Goal.ENEMIES, true);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		p.pickUp();
		assertTrue("Game should complete when player picks up only treasure",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldNotCompleteIfPlayerPicksUpTreasureAndTreasureIsNotLastGoal() {
		BasicGoal b1 = new BasicGoal(Goal.TREASURE);
		BasicGoal b2 = new BasicGoal(Goal.ENEMIES);
		ComplexGoal c1 = new ComplexGoal(Goal.AND);
		c1.addSubGoal(b1);
		c1.addSubGoal(b2);
		d.setGoals(c1);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		p.pickUp();
		assertFalse("Game should complete when player picks up only treasure if there are other goals to complete",d.isGameComplete());
	}
	@Test
	public void dungeonShouldCompleteOnlyWhenPlayerPicksUpAllTreasuresInsteadOfOnlyOne() {
		d.addEntity(new Treasure(5,5));
		d.setGoals(new BasicGoal(Goal.TREASURE));
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		p.pickUp();
		assertFalse("Game should not complete when player has not picked up all treasure",d.isGameComplete());
		p.moveRight();
		p.pickUp();
		assertTrue("Game should complete when player has picked up all treasures",d.isGameComplete());
	}
}
