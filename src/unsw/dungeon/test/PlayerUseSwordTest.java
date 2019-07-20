package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;

public class PlayerUseSwordTest {
	private Dungeon d;
	private Player p;
	private Sword s1;
	private Sword s2;
	private Enemy e;

	@Before
	public void setUp() throws Exception {
		d = new Dungeon(10, 10);
		p = new Player(d, 5, 5);
		s1 = new Sword(5, 5);
		s2 = new Sword(4, 5);
		e = new Enemy(d, 5, 7);
		
		d.setPlayer(p);
		d.addEntity(p);
		d.addEntity(s1);
		d.addEntity(s2);
		d.addEntity(e);
	}
	
	@After
	public void tearDown() throws Exception {
		d = null;
		p = null;
		s1 = null;
		s2 = null;
		e = null;
	}

	@Test
	public void playerShouldHave5SwordSwing() {
		assertTrue("Player not holding sword", p.getSword() == null);

		p.pickUp();
		
		assertSame("Player holding sword", s1, p.getSword());
		
		int swingCounter = 0;
		
		while(p.getSword() != null) {
			p.swingSword();
			swingCounter++;
		}
		
		assertSame("Sword should have 5 swings", 5, swingCounter);
	}
	
	@Test
	public void swordSwingShouldKillEnemyInOneHit() {
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Sword X starting position is 5", 5, s1.getX());
		assertEquals("Sword Y starting position is 5", 5, s1.getY());
		
		assertEquals("Enemy X starting position is 5", 5, e.getX());
		assertEquals("Enemy Y starting position is 7", 7, e.getY());
		
		p.pickUp();
		p.moveDown();
		
		assertEquals("One enemy in dungeon", 1, d.getEnemies().size());
		
		p.swingSword();
		
		assertEquals("No enemy in dungeon after being killed in one swing", 0, d.getEnemies().size());
	}
	
	@Test
	public void swordSwingShouldKillEnemyInDirectionPlayerIsFacing() {		
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Sword X starting position is 5", 5, s1.getX());
		assertEquals("Sword Y starting position is 5", 5, s1.getY());
		
		assertEquals("Enemy X starting position is 5", 5, e.getX());
		assertEquals("Enemy Y starting position is 7", 7, e.getY());
		
		p.pickUp();
		
		p.moveDown();
		
		p.setDirection(0);
		assertEquals("Player facing upwards", 0, p.getDirection());
		assertEquals("Enemy X position is 5", 5, e.getX());
		assertEquals("Enemy Y position is 7", 7, e.getY());
		
		assertEquals("One enemy in dungeon", 1, d.getEnemies().size());
		assertEquals("Enemy X position is 5", 5, e.getX());
		assertEquals("Enemy Y position is 7", 7, e.getY());
		
		p.swingSword();
		
		assertEquals("Still one enemy in dungeon", 1, d.getEnemies().size());
		
		p.setDirection(2);
		assertEquals("Player facing left", 2, p.getDirection());
		
		assertEquals("Enemy X position is 5", 5, e.getX());
		assertEquals("Enemy Y position is 7", 7, e.getY());
		
		p.swingSword();
		
		assertEquals("Still one enemy in dungeon", 1, d.getEnemies().size());
		
		p.setDirection(3);
		assertEquals("Player facing right", 3, p.getDirection());
		
		assertEquals("Enemy X position is 5", 5, e.getX());
		assertEquals("Enemy Y position is 7", 7, e.getY());
		
		p.swingSword();
		
		assertEquals("Still one enemy in dungeon", 1, d.getEnemies().size());
		
		p.setDirection(1);
		assertEquals("Player facing down", 1, p.getDirection());
		
		p.swingSword();

		assertEquals("No enemy in dungeon after being killed", 0, d.getEnemies().size());
	}

	@Test
	public void swordSwingShouldKillEnemyInDirectionPlayerIsFacingOneSquareAway() {		
		assertEquals("Player X starting position is 5", 5, p.getX());
		assertEquals("Player Y starting position is 5", 5, p.getY());
		
		assertEquals("Sword X starting position is 5", 5, s1.getX());
		assertEquals("Sword Y starting position is 5", 5, s1.getY());
		
		assertEquals("Enemy X starting position is 5", 5, e.getX());
		assertEquals("Enemy Y starting position is 7", 7, e.getY());
		
		p.setDirection(1);
		
		assertEquals("Player facing downwards", 1, p.getDirection());

		p.pickUp();
		
		assertEquals("Enemy X position is 5", 5, e.getX());
		assertEquals("Enemy Y position is 7", 7, e.getY());
		
		assertEquals("One enemy in dungeon", 1, d.getEnemies().size());
		
		assertEquals("Enemy is 2 squares away", 2, ((e.getY()) - p.getY()));

		p.swingSword();
		
		assertEquals("Still one enemy in dungeon", 1, d.getEnemies().size());

		p.moveDown();
		
		assertEquals("Player facing downwards", 1, p.getDirection());
		
		assertEquals("Enemy is 1 square away", 1, (e.getY()) - p.getY());
		
		assertEquals("Still one enemy in dungeon", 1, d.getEnemies().size());
		
		p.swingSword();
		
		assertEquals("No enemy in dungeon after being killed", 0, d.getEnemies().size());
	}

}
