package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;

public class PlayerPickUpSwordTest {
	private Dungeon d;
	private Player p;
	private Sword s1;
	private Sword s2;

	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 5, 5);
		s1 = new Sword(5, 5);
		s2 = new Sword(4, 5);
		
		d.setPlayer(p);
		d.addEntity(p);
		d.addEntity(s1);
		d.addEntity(s2);
	}
	
	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
		s1 = null;
		s2 = null;
	}

	@Test
	public void playerShouldBeAbleToPickUpSwordUsingPickUp() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Sword X starting position is 5", 5, s1.getX());
		assertEquals("Sword Y starting position is 5", 5, s1.getY());
		
		assertTrue("Two entities in square (5, 5)", d.getEntities(5, 5).size() == 2);
		
		assertTrue("Player not holding sword", p.getSword() == null);
				
		p.pickUp();
		
		assertTrue("One entity in square (5, 5) since sword is picked up", d.getEntities(5, 5).size() == 1);
		
		assertSame("Player holding sword", s1, p.getSword());
	}
	
	@Test
	public void playerHoldingSwordShouldNotBeAbleToPickUpAnotherSword() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("First Sword X starting position is 5", 5, s1.getX());
		assertEquals("First Sword Y starting position is 5", 5, s1.getY());
		
		assertEquals("Second Sword X starting position is 4", 4, s2.getX());
		assertEquals("Second Sword Y starting position is 5", 5, s2.getY());
				
		assertTrue("Player not holding sword", p.getSword() == null);
				
		p.pickUp();
				
		assertSame("Player holding sword", s1, p.getSword());
		
		assertTrue("One entity in square (4, 5) which is the Second Sword", d.getEntities(4, 5).size() == 1);
				
		p.moveLeft();
		
		assertTrue("Two entities in square (4, 5) since player moved left", d.getEntities(4, 5).size() == 2);
		
		p.pickUp();
		
		assertTrue("Still two entities in square (4, 5) since player already has sword", d.getEntities(4, 5).size() == 2);
		
		Sword s2Copy = null;

		for (Entity e : d.getEntities(4, 5)) {
			if (e instanceof Sword) {
				s2Copy = (Sword) e;
			}
		}
		
		assertSame("Sword on ground is the same as before pick up", s2, s2Copy);
		
		assertSame("Player still holding original sword", s1, p.getSword());

	}
}
