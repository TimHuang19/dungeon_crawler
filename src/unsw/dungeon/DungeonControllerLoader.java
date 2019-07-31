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
    private Image playerDownImage;
    private Image playerUpImage;
    private Image playerRightImage;
    private Image playerLeftImage;
    private Image playerDownSwordImage;
    private Image playerUpSwordImage;
    private Image playerRightSwordImage;
    private Image playerLeftSwordImage;
    private Image playerDownInvincibleImage;
    private Image playerUpInvincibleImage;
    private Image playerRightInvincibleImage;
    private Image playerLeftInvincibleImage;
    private Image playerDownSwordInvincibleImage;
    private Image playerUpSwordInvincibleImage;
    private Image playerRightSwordInvincibleImage;
    private Image playerLeftSwordInvincibleImage;
    private Image wallImage;
    private Image exitImage;
    private Image enemyImage;
    private Image boulderImage;
    private Image switchImage;
    private Image potionImage;
    private Image swordImage;
    private Image bombImage;
    private Image bombLitZeroImage;
    private Image bombLitOneImage;
    private Image bombLitTwoImage;
    private Image bombExplodeImage;
    private Image treasureImage;
    private Image keyImage;
    private Image doorImage;
    private Image openDoorImage;

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
        boulderImage = new Image("/boulder.png");
        switchImage = new Image("/pressure_plate.png");
        potionImage = new Image("/brilliant_blue_new.png");
        swordImage = new Image("/greatsword_1_new.png");
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

        addEntity(player, down);
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
