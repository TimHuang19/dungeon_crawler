package unsw.dungeon.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import unsw.dungeon.BasicGoal;
import unsw.dungeon.Bomb;
import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Exit;
import unsw.dungeon.Goal;
import unsw.dungeon.Key;
import unsw.dungeon.LitState;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Switch;
import unsw.dungeon.Sword;
import unsw.dungeon.Treasure;
import unsw.dungeon.Wall;

public class LitBombDestroyTest {
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
	public void litBombShouldDestroyBouldersInAdjacentSquares() {
		p.moveLeft();
		p.moveLeft();
		p.moveLeft();
		
		Boulder left = new Boulder(d, 4, 5);
		Boulder right = new Boulder(d, 6, 5);
		Boulder above = new Boulder(d, 5, 4);
		Boulder below = new Boulder(d, 5, 6);
		Boulder topLeft = new Boulder(d, 4, 4);
		Boulder topRight = new Boulder(d, 6, 4);
		Boulder botLeft = new Boulder(d, 4, 6);
		Boulder botRight = new Boulder(d, 6, 6);
		Boulder aboveAbove = new Boulder(d, 5, 3);
		Boulder belowBelow = new Boulder(d, 5, 7);
		Boulder rightRight = new Boulder(d, 7, 5);
		Boulder leftLeft = new Boulder(d, 3, 5);
		
		d.addEntity(left);
		d.addEntity(right);
		d.addEntity(above);
		d.addEntity(below);
		d.addEntity(topLeft);
		d.addEntity(topRight);
		d.addEntity(botLeft);
		d.addEntity(botRight);
		d.addEntity(aboveAbove);
		d.addEntity(rightRight);
		d.addEntity(belowBelow);
		d.addEntity(leftLeft);
		
		b.setState(new LitState(b));
		assertTrue("Bomb is in lit state", b.getState() instanceof LitState);
		b.explode();
		
		assertFalse("Immediate left bomb no longer in dungeon", d.getAllEntities().contains(left));
		assertFalse("Immediate right bomb no longer in dungeon", d.getAllEntities().contains(right));
		assertFalse("Immediate above bomb no longer in dungeon", d.getAllEntities().contains(above));
		assertFalse("Immediate below bomb no longer in dungeon", d.getAllEntities().contains(below));

		assertTrue("Diagonally top left bomb still in dungeon", d.getAllEntities().contains(topLeft));
		assertTrue("Diagonally top right bomb still longer in dungeon", d.getAllEntities().contains(topRight));
		assertTrue("Diagonally bot left bomb still longer in dungeon", d.getAllEntities().contains(botLeft));
		assertTrue("Diagonally bot right bomb still longer in dungeon", d.getAllEntities().contains(botRight));
		
		assertTrue("Two squares left bomb still in dungeon", d.getAllEntities().contains(leftLeft));
		assertTrue("Two squares right bomb still longer in dungeon", d.getAllEntities().contains(rightRight));
		assertTrue("Two squares above bomb still longer in dungeon", d.getAllEntities().contains(aboveAbove));
		assertTrue("Two squares below bomb still longer in dungeon", d.getAllEntities().contains(belowBelow));
	}
	
	@Test
	public void litBombShouldDestroyEnemiesInAdjacentSquares() {
		p.moveLeft();
		p.moveLeft();
		p.moveLeft();
		
		Enemy on = new Enemy(d, 5, 5);
		Enemy left = new Enemy(d, 4, 5);
		Enemy right = new Enemy(d, 6, 5);
		Enemy above = new Enemy(d, 5, 4);
		Enemy below = new Enemy(d, 5, 6);
		Enemy topLeft = new Enemy(d, 4, 4);
		Enemy topRight = new Enemy(d, 6, 4);
		Enemy botLeft = new Enemy(d, 4, 6);
		Enemy botRight = new Enemy(d, 6, 6);
		Enemy aboveAbove = new Enemy(d, 5, 3);
		Enemy belowBelow = new Enemy(d, 5, 7);
		Enemy rightRight = new Enemy(d, 7, 5);
		Enemy leftLeft = new Enemy(d, 3, 5);
		
		d.addEntity(on);
		d.addEntity(left);
		d.addEntity(right);
		d.addEntity(above);
		d.addEntity(below);
		d.addEntity(topLeft);
		d.addEntity(topRight);
		d.addEntity(botLeft);
		d.addEntity(botRight);
		d.addEntity(aboveAbove);
		d.addEntity(rightRight);
		d.addEntity(belowBelow);
		d.addEntity(leftLeft);
		
		b.setState(new LitState(b));
		assertTrue("Bomb is in lit state", b.getState() instanceof LitState);
		b.explode();
		
		assertFalse("Enemy same square as bomb explosion no longer in dungeon", d.getAllEntities().contains(on));
		assertFalse("Immediate left enemy no longer in dungeon", d.getAllEntities().contains(left));
		assertFalse("Immediate right enemy no longer in dungeon", d.getAllEntities().contains(right));
		assertFalse("Immediate above enemy no longer in dungeon", d.getAllEntities().contains(above));
		assertFalse("Immediate below enemy no longer in dungeon", d.getAllEntities().contains(below));

		assertTrue("Diagonally top left enemy still in dungeon", d.getAllEntities().contains(topLeft));
		assertTrue("Diagonally top right enemy still longer in dungeon", d.getAllEntities().contains(topRight));
		assertTrue("Diagonally bot left enemy still longer in dungeon", d.getAllEntities().contains(botLeft));
		assertTrue("Diagonally bot right enemy still longer in dungeon", d.getAllEntities().contains(botRight));
		
		assertTrue("Two squares left enemy still in dungeon", d.getAllEntities().contains(leftLeft));
		assertTrue("Two squares right enemy still longer in dungeon", d.getAllEntities().contains(rightRight));
		assertTrue("Two squares above enemy still longer in dungeon", d.getAllEntities().contains(aboveAbove));
		assertTrue("Two squares below enemy still longer in dungeon", d.getAllEntities().contains(belowBelow));
	}
	
	@Test
	public void playerOnBombExplosionSquareShouldEndGame() {
		assertFalse("Game not over", d.isGameOver());
		
		b.setState(new LitState(b));
		b.explode();
		
		assertTrue("Game is over since player died to bomb", d.isGameOver());
	}
	
	@Test
	public void playerImmediatelyLeftOfBombExplosionSquareShouldEndGame() {
		p.moveLeft();
		assertFalse("Game not over", d.isGameOver());
		
		b.setState(new LitState(b));
		b.explode();
		
		assertTrue("Game is over since player died to bomb", d.isGameOver());
	}
	
	@Test
	public void playerImmediatelyRightOfBombExplosionSquareShouldEndGame() {
		p.moveRight();
		assertFalse("Game not over", d.isGameOver());
		
		b.setState(new LitState(b));
		b.explode();
		
		assertTrue("Game is over since player died to bomb", d.isGameOver());
	}
	
	@Test
	public void playerImmediatelyAboveBombExplosionSquareShouldEndGame() {
		p.moveUp();
		assertFalse("Game not over", d.isGameOver());
		
		b.setState(new LitState(b));
		b.explode();
		
		assertTrue("Game is over since player died to bomb", d.isGameOver());
	}
	
	@Test
	public void playerImmediatelyBelowBombExplosionSquareShouldEndGame() {
		p.moveDown();
		assertFalse("Game not over", d.isGameOver());
		
		b.setState(new LitState(b));
		b.explode();
		
		assertTrue("Game is over since player died to bomb", d.isGameOver());
	}
	
	@Test
	public void playerNotInImmediateSquareOfBombExplosionShouldNotEndGame() {
		p.moveLeft();
		p.moveUp();
		d.addEntity(new Player(d, 6, 4));
		d.addEntity(new Player(d, 6, 6));
		d.addEntity(new Player(d, 4, 6));
		d.addEntity(new Player(d, 5, 3));
		d.addEntity(new Player(d, 7, 5));
		d.addEntity(new Player(d, 5, 7));
		d.addEntity(new Player(d, 3, 5));

		assertFalse("Game not over", d.isGameOver());
		
		b.setState(new LitState(b));
		b.explode();
		
		assertFalse("Game not over since no player died to bomb", d.isGameOver());
	}
	
	@Test
	public void bombShouldOnlyAffectPlayerBoulderAndEnemies() {
		p.moveDown();
		p.moveDown();
		p.moveDown();
		d.addEntity(new Switch(5, 5));
		d.addEntity(new Door(4, 5, 0));
		d.addEntity(new Exit(5, 4));
		d.addEntity(new Key(5, 5, 0));
		d.addEntity(new Potion(5, 5));
		d.addEntity(new Sword(5, 5));
		d.addEntity(new Treasure(5, 5));
		d.addEntity(new Wall(5, 6));
		
		assertSame("There must be 8 non-Player, non-Enemy and non-Boulder entities and 1 Bomb entity on immediate squares before explosion", 9,
				(d.getEntities(5, 5).size() + d.getEntities(4, 5).size() + d.getEntities(5, 4).size() + d.getEntities(5, 6).size()));
		
		b.setState(new LitState(b));
		b.explode();
		
		assertFalse("Game not over", d.isGameOver());
		assertSame("There remains only 8 non-Player, non-Enemy and non-Boulder entities on immediate squares after explosion", 8,
				(d.getEntities(5, 5).size() + d.getEntities(4, 5).size() + d.getEntities(5, 4).size() + d.getEntities(5, 6).size()));
	}
	/*
	@Test
	public void bombShouldExplodeAfterBeingDropped() {
		assertTrue("Bomb is in unlit state before pick up", b.getState() instanceof UnlitState);
		
		p.pickUp();
		
		boolean bombHasExploded = false;
		
		p.dropBomb();
		assertTrue("Bomb is in lit state after being dropped", b.getState() instanceof LitState);
		while(d.getAllEntities().contains(b)) {
			bombHasExploded = true;
		}
		
		assertTrue("Bomb must explode a short delay after being dropped by player", bombHasExploded);
	}*/
}
