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

    /** The entities. */
    private List<ImageView> entities;

    /** The player down image. */
    private Image playerDownImage;
    
    /** The player facing up image. */
    private Image playerUpImage;
    
    /** The player facing right image. */
    private Image playerRightImage;
    
    /** The player facing left image. */
    private Image playerLeftImage;
    
    /** The player facing down with sword image. */
    private Image playerDownSwordImage;
    
    /** The player facing up with sword image. */
    private Image playerUpSwordImage;
    
    /** The player facing right with sword image. */
    private Image playerRightSwordImage;
    
    /** The player facing left with sword image. */
    private Image playerLeftSwordImage;
    
    /** The invincible player facing down image. */
    private Image playerDownInvincibleImage;
    
    /** The invincible player facing up image. */
    private Image playerUpInvincibleImage;
    
    /** The invincible player facing right image. */
    private Image playerRightInvincibleImage;
    
    /** The invincible player facing left image. */
    private Image playerLeftInvincibleImage;
    
    /** The invincible player facing down with sword image. */
    private Image playerDownSwordInvincibleImage;
    
    /** The invincible player facing up with sword image. */
    private Image playerUpSwordInvincibleImage;
    
    /** The invincible player facing right with sword image. */
    private Image playerRightSwordInvincibleImage;
    
    /** The invincible player facing left with sword image. */
    private Image playerLeftSwordInvincibleImage;
    
    /** The wall image. */
    private Image wallImage;
    
    /** The exit image. */
    private Image exitImage;
    
    /** The enemy image. */
    private Image enemyImage;
    
    /** The hound image. */
    private Image houndImage;
    
    /** The boulder image. */
    private Image boulderImage;
    
    /** The switch image. */
    private Image switchImage;
    
    /** The potion image. */
    private Image potionImage;
    
    /** The sword image. */
    private Image swordImage;
    
    /** The left slash image. */
    private Image leftSlashImage;
    
    /** The right slash image. */
    private Image rightSlashImage;
    
    /** The up slash image. */
    private Image upSlashImage;
    
    /** The down slash image. */
    private Image downSlashImage;
    
    /** The telepad image. */
    private Image telepadImage;
    
    /** The bomb image. */
    private Image bombImage;
    
    /** The bomb lit stage zero image. */
    private Image bombLitZeroImage;
    
    /** The bomb lit stage one image. */
    private Image bombLitOneImage;
    
    /** The bomb lit stage two image. */
    private Image bombLitTwoImage;
    
    /** The bomb explode image. */
    private Image bombExplodeImage;
    
    /** The treasure image. */
    private Image treasureImage;
    
    /** The key image. */
    private Image keyImage;
    
    /** The door image. */
    private Image doorImage;
    
    /** The open door image. */
    private Image openDoorImage;

    /**
     * Instantiates a new dungeon controller loader.
     *
     * @param filename the filename
     * @throws FileNotFoundException the file not found exception
     */
    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerDownImage = new Image("/human_down.png");
        playerUpImage = new Image("/human_up.png");
        playerLeftImage = new Image("/human_left.png");
        playerRightImage = new Image("/human_right.png");
        playerDownSwordImage = new Image("/human_down_sword.png");
        playerUpSwordImage = new Image("/human_up_sword.png");
        playerLeftSwordImage = new Image("/human_left_sword.png");
        playerRightSwordImage = new Image("/human_right_sword.png");
        playerDownInvincibleImage = new Image("/human_down_invincible.png");
        playerUpInvincibleImage = new Image("/human_up_invincible.png");
        playerLeftInvincibleImage = new Image("/human_left_invincible.png");
        playerRightInvincibleImage = new Image("/human_right_invincible.png");
        playerDownSwordInvincibleImage = new Image("/human_down_sword_invincible.png");
        playerUpSwordInvincibleImage = new Image("/human_up_sword_invincible.png");
        playerLeftSwordInvincibleImage = new Image("/human_left_sword_invincible.png");
        playerRightSwordInvincibleImage = new Image("/human_right_sword_invincible.png");
        wallImage = new Image("/brick_brown_0.png");
        exitImage = new Image("/exit.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        houndImage = new Image("/hound.png");
        boulderImage = new Image("/boulder.png");
        switchImage = new Image("/pressure_plate.png");
        potionImage = new Image("/brilliant_blue_new.png");
        swordImage = new Image("/greatsword_1_new.png");
        leftSlashImage = new Image("/slash_left.png");
        rightSlashImage = new Image("/slash_right.png");
        downSlashImage = new Image("/slash_down.png");
        upSlashImage = new Image("/slash_up.png");
        telepadImage = new Image("/telepad.png");
        bombImage = new Image("/bomb_unlit.png");
        bombLitZeroImage = new Image("/bomb_lit_1.png");
        bombLitOneImage = new Image("/bomb_lit_2.png");
        bombLitTwoImage = new Image("/bomb_lit_3.png");
        bombExplodeImage = new Image("/bomb_lit_4.png");
        treasureImage = new Image("/gold_pile.png");
        keyImage = new Image("/key.png");
        doorImage = new Image("/closed_door.png");
        openDoorImage = new Image("/open_door.png");
    }

    /**
     * On load.
     *
     * @param player the player
     */
    @Override
    public void onLoad(Player player) {
        ImageView down = new ImageView(playerDownImage);
        ImageView right = new ImageView(playerRightImage);
        ImageView left = new ImageView(playerLeftImage);
        ImageView up = new ImageView(playerUpImage);
        
        ImageView downSword = new ImageView(playerDownSwordImage);
        ImageView rightSword = new ImageView(playerRightSwordImage);
        ImageView leftSword = new ImageView(playerLeftSwordImage);
        ImageView upSword = new ImageView(playerUpSwordImage);
        
        ImageView downInvincible = new ImageView(playerDownInvincibleImage);
        ImageView rightInvincible = new ImageView(playerRightInvincibleImage);
        ImageView leftInvincible = new ImageView(playerLeftInvincibleImage);
        ImageView upInvincible = new ImageView(playerUpInvincibleImage);
        
        ImageView downSwordInvincible = new ImageView(playerDownSwordInvincibleImage);
        ImageView rightSwordInvincible = new ImageView(playerRightSwordInvincibleImage);
        ImageView leftSwordInvincible = new ImageView(playerLeftSwordInvincibleImage);
        ImageView upSwordInvincible = new ImageView(playerUpSwordInvincibleImage);
        
        ImageView leftSlash = new ImageView(leftSlashImage);
        ImageView rightSlash = new ImageView(rightSlashImage);
        ImageView upSlash = new ImageView(upSlashImage);
        ImageView downSlash = new ImageView(downSlashImage);

        player.setDownView(down);
        player.addView(down);
        player.setRightView(right);
        player.addView(right);
        player.setUpView(up);
        player.addView(up);
        player.setLeftView(left);
        player.addView(left);

        player.setDownSwordView(downSword);
        player.addView(downSword);
        player.setRightSwordView(rightSword);
        player.addView(rightSword);
        player.setUpSwordView(upSword);
        player.addView(upSword);
        player.setLeftSwordView(leftSword);
        player.addView(leftSword);
        
        player.setDownInvincibleView(downInvincible);
        player.addView(downInvincible);
        player.setRightInvincibleView(rightInvincible);
        player.addView(rightInvincible);
        player.setUpInvincibleView(upInvincible);
        player.addView(upInvincible);
        player.setLeftInvincibleView(leftInvincible);
        player.addView(leftInvincible);
        
        player.setDownSwordInvincibleView(downSwordInvincible);
        player.addView(downSwordInvincible);
        player.setRightSwordInvincibleView(rightSwordInvincible);
        player.addView(rightSwordInvincible);
        player.setUpSwordInvincibleView(upSwordInvincible);
        player.addView(upSwordInvincible);
        player.setLeftSwordInvincibleView(leftSwordInvincible);
        player.addView(leftSwordInvincible);
        
        player.setLeftSlashView(leftSlash);
        player.setRightSlashView(rightSlash);
        player.setUpSlashView(upSlash);
        player.setDownSlashView(downSlash);
        
        trackPosition(player, right);
        trackPosition(player, up);
        trackPosition(player, left);
        
        trackPosition(player, rightSword);
        trackPosition(player, upSword);
        trackPosition(player, leftSword);
        trackPosition(player, downSword);
        
        trackPosition(player, rightInvincible);
        trackPosition(player, upInvincible);
        trackPosition(player, leftInvincible);
        trackPosition(player, downInvincible);

        trackPosition(player, rightSwordInvincible);
        trackPosition(player, upSwordInvincible);
        trackPosition(player, leftSwordInvincible);
        trackPosition(player, downSwordInvincible);
        
        trackLeftPosition(player, leftSlash);
        trackRightPosition(player, rightSlash);
        trackUpPosition(player, upSlash);
        trackDownPosition(player, downSlash);

        addEntity(player, down);
    }

    /**
     * On load.
     *
     * @param wall the wall
     */
    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        wall.setImageView(view);
        addEntity(wall, view);
    }
    
    /**
     * On load.
     *
     * @param exit the exit
     */
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        exit.setImageView(view);
        addEntity(exit, view);
    }
    
    /**
     * On load.
     *
     * @param enemy the enemy
     */
    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        enemy.setImageView(view);
        addEntity(enemy, view);
    }
    
    /**
     * On load.
     *
     * @param hound the hound
     */
    @Override
    public void onLoad(Hound hound) {
        ImageView view = new ImageView(houndImage);
        hound.setImageView(view);
        addEntity(hound, view);
    }
    
    /**
     * On load.
     *
     * @param boulder the boulder
     */
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	boulder.setImageView(view);
    	addEntity(boulder, view);
    }
    
    /**
     * On load.
     *
     * @param s the switch
     */
    @Override
    public void onLoad(Switch s) {
    	ImageView view = new ImageView(switchImage);
    	s.setImageView(view);
    	addEntity(s, view);
    }
    
    /**
     * On load.
     *
     * @param potion the potion
     */
    @Override
    public void onLoad(Potion potion) {
    	ImageView view = new ImageView(potionImage);
    	potion.setImageView(view);
    	addEntity(potion, view);
    }

	/**
	 * On load.
	 *
	 * @param sword the sword
	 */
	@Override
	public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
    	sword.setImageView(view);
    	addEntity(sword, view);		
	}
	
	/**
	 * On load.
	 *
	 * @param telepad the telepad
	 */
	@Override
	public void onLoad(Telepad telepad) {
		ImageView view = new ImageView(telepadImage);
		telepad.setImageView(view);
		addEntity(telepad, view);
	}

	/**
	 * On load.
	 *
	 * @param bomb the bomb
	 */
	@Override
	public void onLoad(Bomb bomb) {
    	ImageView view = new ImageView(bombImage);
    	ImageView litZero = new ImageView(bombLitZeroImage);
    	ImageView litOne = new ImageView(bombLitOneImage);
    	ImageView litTwo = new ImageView(bombLitTwoImage);
    	ImageView litExplode = new ImageView(bombExplodeImage);
    	bomb.setImageView(view);
    	bomb.addZeroImage(litZero);
    	bomb.addOneImage(litOne);
    	bomb.addTwoImage(litTwo);
    	bomb.addExplodeImage(litExplode);
    	trackPosition(bomb, litZero);
    	trackPosition(bomb, litOne);
    	trackPosition(bomb, litTwo);
    	trackPosition(bomb, litExplode);
    	addEntity(bomb, view);		
	}

	/**
	 * On load.
	 *
	 * @param treasure the treasure
	 */
	@Override
	public void onLoad(Treasure treasure) {
    	ImageView view = new ImageView(treasureImage);
    	treasure.setImageView(view);
    	addEntity(treasure, view);		
	}
	
	/**
	 * On load.
	 *
	 * @param key the key
	 */
	@Override
	public void onLoad(Key key) {
    	ImageView view = new ImageView(keyImage);
    	key.setImageView(view);
    	addEntity(key, view);		
	}

	/**
	 * On load.
	 *
	 * @param door the door
	 */
	@Override
	public void onLoad(Door door) {
    	ImageView view = new ImageView(doorImage);
    	ImageView openDoorView = new ImageView(openDoorImage);
    	door.setImageView(view);
    	door.addOpenDoorView(openDoorView);
    	trackPosition(door, openDoorView);
    	addEntity(door, view);
	}
	
    /**
     * Adds the entity.
     *
     * @param entity the entity
     * @param view the view
     */
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
     *
     * @param entity the entity
     * @param node the node
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
     * Track left position.
     *
     * @param entity the entity
     * @param node the node
     */
    private void trackLeftPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX()-1);
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	if (newValue.intValue() == 0) {
            		GridPane.setColumnIndex(node, newValue.intValue());
            	} else {
            		GridPane.setColumnIndex(node, newValue.intValue()-1);
            	}
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
     * Track right position.
     *
     * @param entity the entity
     * @param node the node
     */
    private void trackRightPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX()+1);
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue()+1);
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
     * Track up position.
     *
     * @param entity the entity
     * @param node the node
     */
    private void trackUpPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY()-1);
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
            	if (newValue.intValue() == 0) {
            		GridPane.setRowIndex(node, newValue.intValue());
            	} else {
            		GridPane.setRowIndex(node, newValue.intValue()-1);
            	}
            }
        });
    }
    
    /**
     * Track down position.
     *
     * @param entity the entity
     * @param node the node
     */
    private void trackDownPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY()+1);
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
                GridPane.setRowIndex(node, newValue.intValue()+1);
            }
        });
    }
    
    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     *
     * @return the dungeon controller
     * @throws FileNotFoundException the file not found exception
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

}
