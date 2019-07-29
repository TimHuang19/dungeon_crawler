package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.BasicGoal;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Goal;
import unsw.dungeon.Key;
import unsw.dungeon.Player;

public class PlayerPickUpKeyTest {
	private Dungeon d;
	private Player p;
	private Key k;

	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 5, 5);
		k = new Key(5, 5, 0);
		
		d.setPlayer(p);
		d.setGoals(new BasicGoal(Goal.TREASURE));
		d.addEntity(p);
		d.addEntity(k);
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
		k = null;
	}
	
	@Test
	public void playerShouldBeAbleToPickUpKeyUsingPickUp() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Key X starting position is 5", 5, k.getX());
		assertEquals("Key Y starting position is 5", 5, k.getY());
				
		assertTrue("Player not holding key", p.getKeyId() == -1);
		
		boolean keyAtSquare = false;
		
		for (Entity e : d.getEntities(5, 5)) {
			if (e instanceof Key) {
				keyAtSquare = true;
			}
		}
		
		assertTrue("Key in square (5, 5)", keyAtSquare);			
				
		p.pickUp();
				
		assertSame("Player holding key", k, p.getKeyId());
		
		boolean keyInDungeon = false;
		
		for (Entity e : d.getAllEntities()) {
			if (e instanceof Key) {
				keyInDungeon = true;
			}
		}
		
		assertTrue("Key no longer in dungeon", !keyInDungeon);	
	}
	
	@Test
	public void pickingUpNewKeyWhileHoldingExistingKeyShouldSwapTheKeys() {
		Key k2 = new Key(6, 5, 1);
		d.addEntity(k2);
		
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("First Key X starting position is 5", 5, k.getX());
		assertEquals("First Key Y starting position is 5", 5, k.getY());
		
		assertEquals("Second Key X starting position is 6", 6, k2.getX());
		assertEquals("Second Key Y starting position is 5", 5, k2.getY());
				
		assertTrue("Player not holding key", p.getKeyId() == -1);
		
		p.pickUp();
		p.moveRight();
		
		assertSame("Player holding first key", k, p.getKeyId());
		
		Key temp = null;
		for (Entity e : d.getEntities(6, 5)) {
			if (e instanceof Key) {
				temp = (Key) e;
			}
		}
		
		assertSame("Second key on ground", k2, temp);
		
		p.pickUp();
		
		assertSame("Player holding second key", k2, p.getKeyId());
		
		temp = null;
		for (Entity e : d.getEntities(6, 5)) {
			if (e instanceof Key) {
				temp = (Key) e;
			}
		}
		
		assertSame("First key on ground", k, temp);
	}
}
