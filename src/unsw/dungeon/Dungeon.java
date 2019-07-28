/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon implements Observer {

    private int width, height;
    private ArrayList<Entity> entities;
    private Player player;
    private GoalExpression goals;
    
    private boolean complete;
    private boolean gameOver;
    
    private int pressedSwitches;
    private int treasureCount;
    private int enemyCount;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.goals = new BasicGoal(Goal.EXIT);
        this.complete = false;
        this.gameOver = false;
        this.pressedSwitches = 0;
        this.treasureCount = 0;
        this.enemyCount = 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public int getTreasureCount() {
    	return this.treasureCount;
    }
    
    public ArrayList<Entity> getAllEntities() {
    	return entities;
    }
    
    public ArrayList<Entity> getEnemies(){
    	ArrayList<Entity> enemies = new ArrayList<>();
    	for (Entity e: entities) {
    		if (e instanceof Enemy) {
    			enemies.add(e);
    		}
    	}
    	return enemies;
    }
    
    public void setGoals(GoalExpression goals) {
    	this.goals = goals;
    }
    
    public void gameOver() {
    	this.gameOver = true;
    	System.out.println("GAME OVER");
    }
    
    public boolean isGameOver() {
    	return this.gameOver;
    }
    
    public boolean isGameComplete() {
    	return this.complete;
    }
    public void reduceTreasures() {
    	this.treasureCount--;
    	if (treasureCount == 0) {
    		setComplete(Goal.TREASURE, true);
    	}
    }
    public void killEnemy(Enemy e) {
    	e.notifyDungeonObservers();
    	removeEntity(e);
    	player.removeObserver((Observer) e);
    	this.enemyCount--;
    	if(enemyCount == 0) {
    		setComplete(Goal.ENEMIES, true);
    	}
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity instanceof Switch) {
        	this.pressedSwitches++;
        } else if (entity instanceof Treasure) {
        	this.treasureCount++;
        } else if (entity instanceof Enemy) {
        	this.enemyCount++;
        }
    }
    
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    }
    
    public void setComplete(Goal goal, boolean complete) {
    	goals.setComplete(goal, complete);
    	
    	if (goals.isComplete()) {
    		this.complete = true;
    		System.out.println("DUNGEON COMPLETE");
    	} else {
    		this.complete = false;
    		System.out.println("DUNGEON NOT COMPLETE");
    	}
    }
    
    public ArrayList<Entity> getEntities(int x, int y) {
    	ArrayList<Entity> entities = new ArrayList<Entity>();
    	for (Entity e : this.entities) {
			if (x == e.getX() && y == e.getY()) {
				entities.add(e);
			}
    	}
    	return entities;
    }
    
    public ArrayList<Entity> getExplosionTargets(int x, int y) {
    	ArrayList<Entity> entities = new ArrayList<Entity>();
    	for (Entity e : this.entities) {
    		if ((x == e.getX() && y == e.getY()) ||
    				((x + 1) == e.getX() && y == e.getY()) ||
    				((x - 1) == e.getX() && y == e.getY()) ||
    				(x == e.getX() && (y + 1) == e.getY()) ||
    				(x == e.getX() && (y - 1) == e.getY())) {
    			entities.add(e);
    		}
    	}
    	return entities;
    }
    
	@Override
	public void update(Subject obj) {
		if (obj instanceof Boulder) {
			boolean onSwitch = false;
			Boulder b = (Boulder) obj;
			
			if (b.gotDestroyed()) {
				this.pressedSwitches++;
				if (this.pressedSwitches == 1) {
					setComplete(Goal.BOULDERS, false);
				}
				removeEntity((Entity) b);
				return;
			}
			
			for (Entity e : entities) {
				if (e instanceof Switch && b.getX() == e.getX() && b.getY() == e.getY()) {
					onSwitch = true;
					this.pressedSwitches--;
					if (this.pressedSwitches == 0) {
						setComplete(Goal.BOULDERS, true);
					}
				}
			}
			
			if (b.getOnSwitch() && onSwitch == false) {
				this.pressedSwitches++;
				if (this.pressedSwitches == 1) {
					setComplete(Goal.BOULDERS, false);
				}
			}
			b.setOnSwitch(onSwitch);
		}
	}

	public void setController(DungeonController dungeonController) {		
		for (Entity e : this.entities) {
			e.registerDungeonObserver(dungeonController);
		}
	}
}
