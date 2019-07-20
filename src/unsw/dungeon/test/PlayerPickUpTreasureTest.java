package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.BasicGoal;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;

public class PlayerPickUpTreasureTest {
	private Dungeon d;
	private Player p;
	private Treasure t;

	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 5, 5);
		t = new Treasure(5, 5);
		
		d.setPlayer(p);
		d.setGoals(new BasicGoal(Goal.TREASURE));
		d.addEntity(p);
		d.addEntity(t);
	}
	
	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
		t = null;
	}
	
	@Test
	public void playerShouldBeAbleToPickUpTreasureUsingPickUp() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Treasure X starting position is 5", 5, t.getX());
		assertEquals("Treasure Y starting position is 5", 5, t.getY());
		
		boolean treasureAtSquare = false;
		
		for (Entity e : d.getEntities(5, 5)) {
			if (e instanceof Treasure) {
				treasureAtSquare = true;
			}
		}
		
		assertTrue("Treasure in square (5, 5)", treasureAtSquare);
						
		p.pickUp();
				
		for (Entity e : d.getEntities(5, 5)) {
			if (e instanceof Treasure) {
				treasureAtSquare = true;
			}
		}
		
		boolean treasureInDungeon = false;
		
		for (Entity e : d.getAllEntities()) {
			if (e instanceof Treasure) {
				treasureInDungeon = true;
			}
		}
		
		assertTrue("Treasure no longer in dungeon", !treasureInDungeon);	
	}
	
	@Test
	public void pickingUpTreasureShouldReduceDungeonTreasureCountByOne() {
		assertEquals("Treasure count is 1", 1, d.getTreasureCount());
		p.pickUp();
		assertEquals("Treasure count is now 0", 0, d.getTreasureCount());
	}
}
