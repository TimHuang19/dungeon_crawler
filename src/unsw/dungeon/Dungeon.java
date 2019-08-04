package unsw.dungeon;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon implements DungeonSubject, Observer {

    /** The width and height. */
    private int width, height;
    
    /** The entities. */
    private ArrayList<Entity> entities;
    
    /** The player. */
    private Player player;
    
    /** The goals. */
    private GoalExpression goals;
    
    /** The dungeon observers. */
    private ArrayList<DungeonObserver> dungeonObservers;
    
    /** A boolean indicating if game is complete */
    private boolean complete;
    
    /** A boolean indicating if game is over */
    private boolean gameOver;
    
    /** The number of pressed switches. */
    private int pressedSwitches;
    
    /** The treasure count. */
    private int treasureCount;
    
    /** The enemy count. */
    private int enemyCount;
    
    /** Telepad groupings */
    private LinkedHashMap<Integer, ArrayList<Telepad>> idToTelepads;
    
    /**
     * Instantiates a new dungeon.
     *
     * @param width 	The width
     * @param height 	The height
     */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.goals = null;
        this.dungeonObservers = new ArrayList<>();
        this.complete = false;
        this.gameOver = false;
        this.pressedSwitches = 0;
        this.treasureCount = 0;
        this.enemyCount = 0;
        this.idToTelepads = new LinkedHashMap<>();
    }
    
    /**
     * Pause.
     */
    public void pause() {
    	for (Entity e : entities) {
    		if (e instanceof Enemy) {
    			((Enemy) e).pause();
    		}
    	}
    }
    
    /**
     * Unpause.
     */
    public void unPause() {
    	for (Entity e : entities) {
    		if (e instanceof Enemy) {
    			((Enemy) e).unPause();
    		}
    	}
    }
    
    /**
     * Gets the width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player.
     *
     * @param player 	The new player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    /**
     * Gets the treasure count.
     *
     * @return the treasure count
     */
    public int getTreasureCount() {
    	return this.treasureCount;
    }
    
    /**
     * Gets all entities.
     *
     * @return An ArrayList<Entity> containing all entities
     */
    public ArrayList<Entity> getAllEntities() {
    	return entities;
    }
    
    /**
     * Gets the enemies.
     *
     * @return the enemies
     */
    public ArrayList<Entity> getEnemies(){
    	ArrayList<Entity> enemies = new ArrayList<>();
    	for (Entity e: entities) {
    		if (e instanceof Enemy) {
    			enemies.add(e);
    		}
    	}
    	return enemies;
    }
    
    /**
     * Sets the goals.
     *
     * @param goals 	The new goals
     */
    public void setGoals(GoalExpression goals) {
    	this.goals = goals;
    }
    
    /**
     * Gets the goals.
     *
     * @return the goals
     */
    public GoalExpression getGoals() {
    	return goals;
    }
    
    /**
     * Makes the game over.
     */
    public void gameOver() {
    	this.gameOver = true;
    	pause();
    	notifyDungeonObservers();
    }
    
    /**
     * Checks game is over.
     *
     * @return true, if the game is over
     */
    public boolean isGameOver() {
    	return this.gameOver;
    }
    
    /**
     * Checks if game is complete.
     *
     * @return true, if the game is complete
     */
    public boolean isGameComplete() {
    	return this.complete;
    }
    
    /**
     * Lowers the treasure count by one
     */
    public void reduceTreasures() {
    	this.treasureCount--;
    	if (treasureCount == 0) {
    		setComplete(Goal.TREASURE, true);
    	}
    }
    
    /**
     * Kill enemy.
     *
     * @param e 	The enemy
     */
    public void killEnemy(Enemy e) {
    	e.pause();
    	e.notifyDungeonObservers();
    	removeEntity(e);
    	player.removeObserver((Observer) e);
    	this.enemyCount--;
    	if(enemyCount == 0) {
    		setComplete(Goal.ENEMIES, true);
    	}
    }
    
    /**
     * Get the matching telepad
     *
     * @return the matching telepad
     */
    public Telepad getMatchingTelepad(Telepad telepad) {

    	Telepad matchingTelepad = null;
    	for (Telepad t : idToTelepads.get(telepad.getId())) {
    		if (t.getX() == telepad.getX() && t.getY() == telepad.getY()) {
    			continue;
    		} else {
    			matchingTelepad = t;
    		}
    	}
    	return matchingTelepad;
    }
    
    /**
     * Removes the entity.
     *
     * @param entity	The entity
     */
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    }
    
    /**
     * Sets whether goal is complete.
     *
     * @param goal 		The goal
     * @param complete  Boolean indicating whether goal is complete or not
     */
    public void setComplete(Goal goal, boolean complete) {
    	goals.setComplete(goal, complete);
    	
    	notifyDungeonObservers();
    	if (goals.isComplete()) {
    		this.complete = true;
    		pause();
    		notifyDungeonObservers();
    	} else {
    		this.complete = false;
    	}
    }
    
    /**
     * Gets the entities.
     *
	 * @param x 		The x position
	 * @param y 		The y position
     * @return An ArrayList<Entity> containing the entities at the given position
     */
    public ArrayList<Entity> getEntities(int x, int y) {
    	ArrayList<Entity> entities = new ArrayList<Entity>();
    	for (Entity e : this.entities) {
			if (x == e.getX() && y == e.getY()) {
				entities.add(e);
			}
    	}
    	return entities;
    }
    
    /**
     * Gets the explosion targets.
     *
     * @param x 	The x position
     * @param y 	The y position
     * @return An ArrayList<Entity> containing the entities inside explosion radius
     */
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
    
	/**
	 * Update.
	 *
	 * @param obj the subject
	 */
	@Override
	public void update(Subject obj) {
		if (obj instanceof Boulder) {
			update((Boulder) obj);
		}
	}
	
	/**
	 * Update from boulder.
	 *
	 * @param b the boulder
	 */
	public void update(Boulder b) {
		boolean onSwitch = false;
		
		if (b.gotDestroyed()) {
			if (b.getOnSwitch()) {
				this.pressedSwitches++;
				if (this.pressedSwitches == 1) {
					setComplete(Goal.BOULDERS, false);
				}
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

	/**
	 * Sets the controller.
	 *
	 * @param dungeonController the new controller
	 */
	public void setController(DungeonController dungeonController) {		
		for (Entity e : this.entities) {
			e.registerDungeonObserver(dungeonController);
		}
	}

	/**
	 * Register dungeon observer.
	 *
	 * @param o the observer
	 */
	@Override
	public void registerDungeonObserver(DungeonObserver o) {
		dungeonObservers.add(o);
	}

	/**
	 * Removes the dungeon observer.
	 *
	 * @param o the observer
	 */
	@Override
	public void removeDungeonObserver(DungeonObserver o) {
		dungeonObservers.remove(o);
	}

	/**
	 * Notify dungeon observers.
	 */
	@Override
	public void notifyDungeonObservers() {
		for (DungeonObserver o : dungeonObservers) {
			o.update(this);
		}
	}
	
    /**
     * Adds the entity.
     *
     * @param entity 	The entity
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity instanceof Switch) {
        	this.pressedSwitches++;
        } else if (entity instanceof Treasure) {
        	this.treasureCount++;
        } else if (entity instanceof Enemy) {
        	this.enemyCount++;
        } else if (entity instanceof Telepad) {
        	Telepad telepad = (Telepad) entity;
    		ArrayList<Telepad> telepads;
        	if (idToTelepads.containsKey(telepad.getId())) {
        		telepads = idToTelepads.get(telepad.getId());
        		telepads.add(telepad);
        	} else {
        		telepads = new ArrayList<>();
        		telepads.add(telepad);
        		idToTelepads.put(telepad.getId(), telepads);
        	}
        }
    }
}
