package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.BasicGoal;
import unsw.dungeon.Bomb;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Goal;
import unsw.dungeon.LitState;
import unsw.dungeon.Player;
import unsw.dungeon.UnlitState;

public class PlayerPickUpBombTest {
	private Dungeon d;
	private Player p;
	private Bomb b;

	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 5, 5);
		b = new Bomb(d, 5, 5);
		
		d.setPlayer(p);
		d.setGoals(new BasicGoal(Goal.TREASURE));
		d.addEntity(p);
		d.addEntity(b);
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
		b = null;
	}
	
	@Test
	public void playerShouldBeAbleToPickUpBombUsingPickUp() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Bomb X starting position is 5", 5, b.getX());
		assertEquals("Bomb Y starting position is 5", 5, b.getY());
				
		assertTrue("Player not holding any bombs", p.getBombs().size() == 0);
		
		boolean bombAtSquare = false;
		
		for (Entity e : d.getEntities(5, 5)) {
			if (e instanceof Bomb) {
				bombAtSquare = true;
			}
		}
		
		assertTrue("Bomb in square (5, 5)", bombAtSquare);			
				
		p.pickUp();
				
		assertEquals("Player holding one bomb", 1, p.getBombs().size());
		assertTrue("Player holding correct bomb", p.getBombs().contains(b));
		
		boolean bombInDungeon = false;
		
		for (Entity e : d.getAllEntities()) {
			if (e instanceof Bomb) {
				bombInDungeon = true;
			}
		}
		
		assertTrue("Bomb no longer in dungeon", !bombInDungeon);	
	}

	@Test
	public void playerShouldNotBeAbleToPickUpMoreThan3Bombs() {
		Bomb b2 = new Bomb(d, 5, 6);
		Bomb b3 = new Bomb(d, 5, 7);
		Bomb b4 = new Bomb(d, 5, 8);
		
		d.addEntity(b2);
		d.addEntity(b3);
		d.addEntity(b4);
		
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("First Bomb X starting position is 5", 5, b.getX());
		assertEquals("First Bomb Y starting position is 5", 5, b.getY());
		
		assertEquals("Second Bomb X starting position is 5", 5, b2.getX());
		assertEquals("Second Bomb Y starting position is 6", 6, b2.getY());
		
		assertEquals("Third Bomb X starting position is 5", 5, b3.getX());
		assertEquals("Third Bomb Y starting position is 7", 7, b3.getY());
		
		assertEquals("Fourth Bomb X starting position is 5", 5, b4.getX());
		assertEquals("Fourth Bomb Y starting position is 8", 8, b4.getY());
				
		assertTrue("Player not holding any bombs", p.getBombs().size() == 0);
		
		p.pickUp();
		
		assertEquals("Player holding one bomb", 1, p.getBombs().size());
		
		p.moveDown();
		p.pickUp();
		
		assertEquals("Player holding two bombs", 2, p.getBombs().size());

		p.moveDown();
		p.pickUp();
		
		assertEquals("Player holding three bombs", 3, p.getBombs().size());
		
		p.moveDown();
		p.pickUp();
		
		assertEquals("Player still holding three bombs", 3, p.getBombs().size());

		Bomb bombAtSquare = null;
		
		for (Entity e : d.getEntities(5, 8)) {
			if (e instanceof Bomb) {
				bombAtSquare = (Bomb) e;
			}
		}
		
		assertSame("Fourth Bomb still at (5, 8)", b4, bombAtSquare);
		
		assertFalse("Player not holding Fourth Bomb", p.getBombs().contains(b4));
	}
	
	@Test
	public void playerShouldOnlyBeAbleToPickUpUnlitBombs() {
		Bomb b2 = new Bomb(d, 5, 6);
		d.addEntity(b2);
		
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("First Bomb X starting position is 5", 5, b.getX());
		assertEquals("First Bomb Y starting position is 5", 5, b.getY());
		
		assertEquals("Second Bomb X starting position is 5", 5, b2.getX());
		assertEquals("Second Bomb Y starting position is 6", 6, b2.getY());
		
		assertTrue("First Bomb is unlit", b.getState() instanceof UnlitState);
		assertTrue("Player not holding any bombs", p.getBombs().size() == 0);

		p.pickUp();
		
		assertTrue("Player holding First Bomb", p.getBombs().contains(b));
		
		assertTrue("First Bomb no longer in dungeon", !d.getAllEntities().contains(b));
		
		b2.setState(new LitState(b2));
		assertTrue("Second Bomb is lit", b2.getState() instanceof LitState);
		
		p.moveDown();
		p.pickUp();
		
		assertFalse("Player not holding Second Bomb", p.getBombs().contains(b2));
				
		assertTrue("Second Bomb still on (5, 6) square", d.getEntities(5, 6).contains(b2));
	}
}
