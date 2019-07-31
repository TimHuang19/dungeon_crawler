package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private int keyId;
    private int doorId;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
        keyId = 0;
        doorId = 0;
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        loadGoals(dungeon, jsonGoals);
        
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
        	Exit exit = new Exit(x, y);
        	onLoad(exit);
        	entity = exit;
        	break;
    	case "enemy":
    		Enemy enemy = new Enemy(dungeon, x, y);
    		onLoad(enemy);
    		entity = enemy;
    		break;
        case "boulder":
        	Boulder boulder = new Boulder(dungeon, x, y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	Switch s = new Switch(x, y);
        	onLoad(s);
        	entity = s;
        	break;
        case "invincibility":
        	Potion potion = new Potion(x, y);
        	onLoad(potion);
        	entity = potion;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "bomb":
        	Bomb bomb = new Bomb(dungeon, x, y);
        	onLoad(bomb);
        	entity = bomb;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "key":
        	Key key = new Key(x, y, keyId);
        	onLoad(key);
        	entity = key;
        	keyId++;
        	break;
        case "door":
        	Door door = new Door(x, y, doorId);
        	onLoad(door);
        	entity = door;
        	doorId++;
        	break;
        }
        dungeon.addEntity(entity);
    }

    private void loadGoals(Dungeon dungeon, JSONObject json) {
    	JSONArray subgoals;
    	BasicGoal b;
    	ComplexGoal c;
    	//top level goal
    	String top = json.getString("goal");
        switch (top) {
        case "AND":
        	c = new ComplexGoal(Goal.AND);
        	subgoals = json.getJSONArray("subgoals");
        	loadSubgoals(c,subgoals);
        	dungeon.setGoals(c);
        	break;
        case "OR":
        	c = new ComplexGoal(Goal.OR);
        	subgoals = json.getJSONArray("subgoals");
        	loadSubgoals(c,subgoals);
        	dungeon.setGoals(c);
        	break;
        case "treasure":
        	b = new BasicGoal(Goal.TREASURE);
        	dungeon.setGoals(b);
        	break;
        case "enemies":
        	b = new BasicGoal(Goal.ENEMIES);
        	dungeon.setGoals(b);
        	break;
        case "exit":
        	b = new BasicGoal(Goal.EXIT);
        	dungeon.setGoals(b);
        	break;
        case "boulders":
        	b = new BasicGoal(Goal.BOULDERS);
        	dungeon.setGoals(b);
        	break;
        }
    }
    
    private void loadSubgoals(ComplexGoal c, JSONArray subgoals) {
    	JSONObject subgoal;
    	JSONArray subgoals2;
    	String goalType;
    	BasicGoal b;
    	ComplexGoal c1;
    	for (int i = 0; i < subgoals.length(); i++) {
            subgoal = subgoals.getJSONObject(i);
            goalType = subgoal.getString("goal");
            switch (goalType) {
            case "AND":
            	c1 = new ComplexGoal(Goal.AND);
            	subgoals2 = subgoal.getJSONArray("subgoals");
            	loadSubgoals(c1,subgoals2);
            	c.addSubGoal(c1);
            	break;
            case "OR":
            	c1 = new ComplexGoal(Goal.OR);
            	subgoals2 = subgoal.getJSONArray("subgoals");
            	loadSubgoals(c1,subgoals2);
            	c.addSubGoal(c1);
            	break;
            case "treasure":
            	b = new BasicGoal(Goal.TREASURE);
            	c.addSubGoal(b);
            	break;
            case "enemies":
            	b = new BasicGoal(Goal.ENEMIES);
            	c.addSubGoal(b);
            	break;
            case "exit":
            	b = new BasicGoal(Goal.EXIT);
            	c.addSubGoal(b);
            	break;
            case "boulders":
            	b = new BasicGoal(Goal.BOULDERS);
            	c.addSubGoal(b);
            	break;
            }
        }
    }
    
    public abstract void onLoad(Player player);

    public abstract void onLoad(Wall wall);

    // TODO Create additional abstract methods for the other entities
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Switch s);
    
    public abstract void onLoad(Potion potion);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Bomb bomb);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Door door);
}
