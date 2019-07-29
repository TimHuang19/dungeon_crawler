package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image enemyImage;
    private Image boulderImage;
    private Image switchImage;
    private Image potionImage;
    private Image swordImage;
    private Image bombImage;
    private Image treasureImage;
    private Image keyImage;
    private Image doorImage;
    private Image openDoorImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        exitImage = new Image("/exit.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        boulderImage = new Image("/boulder.png");
        switchImage = new Image("/pressure_plate.png");
        potionImage = new Image("/brilliant_blue_new.png");
        swordImage = new Image("/greatsword_1_new.png");
        bombImage = new Image("/bomb_unlit.png");
        treasureImage = new Image("/gold_pile.png");
        keyImage = new Image("/key.png");
        doorImage = new Image("/closed_door.png");
        openDoorImage = new Image("/open_door.png");
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        player.setImageView(view);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        wall.setImageView(view);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        exit.setImageView(view);
        addEntity(exit, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        enemy.setImageView(view);
        addEntity(enemy, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	boulder.setImageView(view);
    	addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(Switch s) {
    	ImageView view = new ImageView(switchImage);
    	s.setImageView(view);
    	addEntity(s, view);
    }
    
    @Override
    public void onLoad(Potion potion) {
    	ImageView view = new ImageView(potionImage);
    	potion.setImageView(view);
    	addEntity(potion, view);
    }

	@Override
	public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
    	sword.setImageView(view);
    	addEntity(sword, view);		
	}

	@Override
	public void onLoad(Bomb bomb) {
    	ImageView view = new ImageView(bombImage);
    	bomb.setImageView(view);
    	addEntity(bomb, view);		
	}

	@Override
	public void onLoad(Treasure treasure) {
    	ImageView view = new ImageView(treasureImage);
    	treasure.setImageView(view);
    	addEntity(treasure, view);		
	}
	
	@Override
	public void onLoad(Key key) {
    	ImageView view = new ImageView(keyImage);
    	key.setImageView(view);
    	addEntity(key, view);		
	}

	@Override
	public void onLoad(Door door) {
    	ImageView view = new ImageView(doorImage);
    	ImageView openDoorView = new ImageView(openDoorImage);
    	door.setImageView(view);
    	door.addOpenDoorView(openDoorView);
    	trackPosition(door, openDoorView);
    	addEntity(door, view);
	}
	
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

}
