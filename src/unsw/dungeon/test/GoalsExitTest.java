package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.*;

public class GoalsExitTest {
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
		d.addEntity(new Exit(4,5));
	}

	@After
	public void tearDown() throws Exception {
		e = null;
		p = null;
		d = null;
	}
	@Test
	public void dungeonShouldCompleteIfPlayerMovesIntoExitAndExitIsOnlyGoal() {
		d.setGoals(new BasicGoal(Goal.EXIT));
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		assertTrue("Game should complete when player moves into exit",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldCompleteIfPlayerMovesIntoExitAndExitIsLastGoal() {
		BasicGoal b1 = new BasicGoal(Goal.EXIT);
		BasicGoal b2 = new BasicGoal(Goal.ENEMIES);
		ComplexGoal c1 = new ComplexGoal(Goal.AND);
		c1.addSubGoal(b1);
		c1.addSubGoal(b2);
		d.setGoals(c1);
		c1.setComplete(Goal.ENEMIES, true);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		assertTrue("Game should complete when player moves into exit",d.isGameComplete());
	}
	
	@Test
	public void dungeonShouldNotCompleteIfPlayerMovesIntoExitAndExitIsNotLastGoal() {
		BasicGoal b1 = new BasicGoal(Goal.EXIT);
		BasicGoal b2 = new BasicGoal(Goal.ENEMIES);
		ComplexGoal c1 = new ComplexGoal(Goal.AND);
		c1.addSubGoal(b1);
		c1.addSubGoal(b2);
		d.setGoals(c1);
		assertFalse("Game should not be complete when nothing has happened",d.isGameComplete());
		p.moveRight();
		assertFalse("Game should complete when player moves into exit if there are other goals to complete",d.isGameComplete());
	}
}
