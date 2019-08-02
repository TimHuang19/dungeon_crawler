package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController implements DungeonObserver{

    /** The squares. */
    @FXML
    private GridPane squares;

    /** The initial entities. */
    private List<ImageView> initialEntities;

    /** The player. */
    private Player player;
    
    /** The dungeon. */
    private Dungeon dungeon;
        
    /** The stage. */
    private Stage stage;
    
    /** The file name. */
    private String fileName;
        
    /** The pause screen. */
    private PauseScreen pauseScreen;
    
    private Label potionLabel;
    
    private Label swordLabel;
    
    private Label bombLabel;
    
    private Label keyLabel;
    
    private Label treasureLabel;
    
    /**
     * Instantiates a new dungeon controller.
     *
     * @param dungeon the dungeon
     * @param initialEntities the initial entities
     */
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.dungeon.registerDungeonObserver(this);
        this.initialEntities = new ArrayList<>(initialEntities);
        this.dungeon.setController(this);
        this.potionLabel = new Label();
        this.swordLabel = new Label();
        this.bombLabel = new Label();
        this.keyLabel = new Label();
        this.treasureLabel = new Label();
        
        potionLabel.setTextFill(Color.YELLOW);
        swordLabel.setTextFill(Color.YELLOW);
        bombLabel.setTextFill(Color.YELLOW);
        keyLabel.setTextFill(Color.YELLOW);
        treasureLabel.setTextFill(Color.YELLOW);
        
        Font font = new Font("Ayuthaya", 12);
        
        potionLabel.setFont(font);
        swordLabel.setFont(font);
        bombLabel.setFont(font);
        keyLabel.setFont(font);
        treasureLabel.setFont(font);
        
        potionLabel.setText("0 steps");
        swordLabel.setText("0 swings");
        bombLabel.setText("0 bombs");
        keyLabel.setText("not held");
        treasureLabel.setText("0 found");
    }
	
    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        
        Image brick = new Image("/grey_dirt1.png");
        
        Image shadow = new Image("/shadow_w.png");
        for (int y = 0; y < dungeon.getHeight(); y++) {
        	squares.add(new ImageView(brick), dungeon.getWidth(), y);
        	squares.add(new ImageView(shadow), dungeon.getWidth(), y);
        	squares.add(new ImageView(brick), dungeon.getWidth()+1, y);
        }
        
        Image potion = new Image("/potion_wide.png");
        squares.add(new ImageView(potion), dungeon.getWidth(), dungeon.getHeight()-5);
        squares.add(potionLabel, dungeon.getWidth()+1, dungeon.getHeight()-5);
        
        Image sword = new Image("/sword_wide.png");
        squares.add(new ImageView(sword), dungeon.getWidth(), dungeon.getHeight()-4);
        squares.add(swordLabel, dungeon.getWidth()+1, dungeon.getHeight()-4);
        
        Image bomb = new Image("/bomb_wide.png");
        squares.add(new ImageView(bomb), dungeon.getWidth(), dungeon.getHeight()-3);
        squares.add(bombLabel, dungeon.getWidth()+1, dungeon.getHeight()-3);
        
        Image treasure = new Image("/treasure_wide.png");
        squares.add(new ImageView(treasure), dungeon.getWidth(), dungeon.getHeight()-2);
        squares.add(treasureLabel, dungeon.getWidth()+1, dungeon.getHeight()-2);
        
        Image key = new Image("/key_wide.png");
        squares.add(new ImageView(key), dungeon.getWidth(), dungeon.getHeight()-1);
        squares.add(keyLabel, dungeon.getWidth()+1, dungeon.getHeight()-1);

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
    }

    /**
     * Handle key press.
     *
     * @param event the event
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case Z:
        	player.pickUp();
        	break;
        case X:
        	player.swingSword();
        	break;
        case SPACE:
        	player.dropBomb();
        	break;
        case ESCAPE:
        	dungeon.pause();
        	pauseScreen.start();
        default:
            break;
        }
    }
    
    /**
     * Un pause.
     */
    public void unPause() {
    	dungeon.unPause();
    }
    
    /**
     * Adds the stage.
     *
     * @param stage the stage
     */
    public void addStage(Stage stage) {
    	this.stage = stage;
    }
    
    /**
     * Adds the name.
     *
     * @param fileName the file name
     */
    public void addName(String fileName) {
    	this.fileName = fileName;
    }
    
    /**
     * Adds the pause screen.
     *
     * @param dungeonScreen the dungeon screen
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void addPauseScreen(DungeonScreen dungeonScreen) throws IOException {
    	this.pauseScreen = new PauseScreen(stage, fileName, dungeonScreen);
    }
    
	/**
	 * Update.
	 *
	 * @param obj the obj
	 */
	@Override
	public void update(DungeonSubject obj) {
		if (obj instanceof Dungeon) {
			Dungeon dungeon = (Dungeon) obj;
			Timeline timeline = new Timeline();
			timeline.setCycleCount(1);
			
			if (dungeon.isGameOver()) {
				KeyFrame kf = new KeyFrame(Duration.seconds(0.1), (ActionEvent event) -> {
					try {
						(new GameOverScreen(stage, fileName)).start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				timeline.getKeyFrames().add(kf);
				timeline.play();
			} else if (dungeon.isGameComplete()) {
				if (fileName.equals("advanced.json")) {
					KeyFrame kf = new KeyFrame(Duration.seconds(0.1), (ActionEvent event) -> {
						try {
							(new CompletedDungeonScreen(stage)).start();
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
					timeline.getKeyFrames().add(kf);
					timeline.play();
				} else {
					KeyFrame kf = new KeyFrame(Duration.seconds(0.1), (ActionEvent event) -> {
						try {
							(new NextLevelScreen(stage, fileName)).start();
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
					timeline.getKeyFrames().add(kf);
					timeline.play();
				}
			}
		} else if (obj instanceof Player) {
			Player player = (Player) obj;
			
			potionLabel.setText(player.getInvincibleSteps() + " steps");
			
			swordLabel.setText(player.getSwings() + " swings");
			
			bombLabel.setText(player.getBombs().size() + " bombs");
			
			treasureLabel.setText(player.getTreasures() + " found");
			
			if (player.getKeyId() == -1) {
				keyLabel.setText("not held");
			} else {
				keyLabel.setText("held");
			}
			
			if (player.isSwinging()) {
				squares.getChildren().remove(player.getLeftSlashView());
				squares.getChildren().remove(player.getRightSlashView());
				squares.getChildren().remove(player.getUpSlashView());
				squares.getChildren().remove(player.getDownSlashView());
				if (player.getDirection() == Direction.LEFT) {
					squares.getChildren().add(player.getLeftSlashView());
					Timeline timeline = new Timeline();
					timeline.setCycleCount(1);
					KeyFrame kf = new KeyFrame(Duration.seconds(0.1), (ActionEvent event) -> squares.getChildren().remove(player.getLeftSlashView()));
					timeline.getKeyFrames().add(kf);
					timeline.play();
				} else if (player.getDirection() == Direction.RIGHT) {
					if (player.atRightBoundary()) {
						return;
					}
					squares.getChildren().add(player.getRightSlashView());
					Timeline timeline = new Timeline();
					timeline.setCycleCount(1);
					KeyFrame kf = new KeyFrame(Duration.seconds(0.1), (ActionEvent event) -> squares.getChildren().remove(player.getRightSlashView()));
					timeline.getKeyFrames().add(kf);
					timeline.play();
				} else if (player.getDirection() == Direction.UP) {
					squares.getChildren().add(player.getUpSlashView());
					Timeline timeline = new Timeline();
					timeline.setCycleCount(1);
					KeyFrame kf = new KeyFrame(Duration.seconds(0.1), (ActionEvent event) -> squares.getChildren().remove(player.getUpSlashView()));
					timeline.getKeyFrames().add(kf);
					timeline.play();
				} else {
					squares.getChildren().add(player.getDownSlashView());
					Timeline timeline = new Timeline();
					timeline.setCycleCount(1);
					KeyFrame kf = new KeyFrame(Duration.seconds(0.1), (ActionEvent event) -> squares.getChildren().remove(player.getDownSlashView()));
					timeline.getKeyFrames().add(kf);
					timeline.play();
				}
				return;
			}
			
			for (ImageView view : player.getViews()) {
				squares.getChildren().remove(view);
			}
			
			if (player.getDirection() == Direction.LEFT) {
				if (player.isInvincible() && player.getSword() != null) {
					squares.getChildren().add(player.getLeftSwordInvincibleView());
				} else if (player.isInvincible() && player.getSword() == null) {
					squares.getChildren().add(player.getLeftInvincibleView());
				} else if (!player.isInvincible() && player.getSword() != null) {
					squares.getChildren().add(player.getLeftSwordView());
				} else {
					squares.getChildren().add(player.getLeftView());
				}
			} else if (player.getDirection() == Direction.RIGHT) {
				if (player.isInvincible() && player.getSword() != null) {
					squares.getChildren().add(player.getRightSwordInvincibleView());
				} else if (player.isInvincible() && player.getSword() == null) {
					squares.getChildren().add(player.getRightInvincibleView());
				} else if (!player.isInvincible() && player.getSword() != null) {
					squares.getChildren().add(player.getRightSwordView());
				} else {
					squares.getChildren().add(player.getRightView());
				}
			} else if (player.getDirection() == Direction.UP) {
				if (player.isInvincible() && player.getSword() != null) {
					squares.getChildren().add(player.getUpSwordInvincibleView());
				} else if (player.isInvincible() && player.getSword() == null) {
					squares.getChildren().add(player.getUpInvincibleView());
				} else if (!player.isInvincible() && player.getSword() != null) {
					squares.getChildren().add(player.getUpSwordView());
				} else {
					squares.getChildren().add(player.getUpView());
				}
			} else {
				if (player.isInvincible() && player.getSword() != null) {
					squares.getChildren().add(player.getDownSwordInvincibleView());
				} else if (player.isInvincible() && player.getSword() == null) {
					squares.getChildren().add(player.getDownInvincibleView());
				} else if (!player.isInvincible() && player.getSword() != null) {
					squares.getChildren().add(player.getDownSwordView());
				} else {
					squares.getChildren().add(player.getDownView());
				}
			}
		} else if (obj instanceof Bomb) {
			Bomb bomb = (Bomb) obj;
			if (bomb.isLit()) {
				squares.getChildren().add(bomb.getZeroImage());
				Timeline timeline = new Timeline();
				timeline.setCycleCount(1);
				KeyFrame kf = new KeyFrame(Duration.seconds(1), (ActionEvent event) -> bombChangeFirst(bomb));
				timeline.getKeyFrames().add(kf);
				timeline.play();
			} else if (bomb.isExplode()) {
				squares.getChildren().remove(bomb.getExplodeImage());
			} else {
				squares.getChildren().remove(bomb.getImageView());
			}
		} else if (obj instanceof Door) {
			Door door = (Door) obj;
			if (!door.isClosed()) {
				squares.getChildren().remove(door.getImageView());
				squares.getChildren().add(door.getOpenDoorView());
			}
		} else {
			squares.getChildren().remove(((Entity) obj).getImageView());
		}
		
	}
	
	/**
	 * Change the bomb to first stage.
	 *
	 * @param bomb the bomb
	 */
	private void bombChangeFirst(Bomb bomb) {
		squares.getChildren().remove(bomb.getZeroImage());
		squares.getChildren().add(bomb.getOneImage());
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), (ActionEvent event) -> bombChangeSecond(bomb));
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	/**
	 * Change the bomb to second stage.
	 *
	 * @param bomb the bomb
	 */
	private void bombChangeSecond(Bomb bomb) {
		squares.getChildren().remove(bomb.getOneImage());
		squares.getChildren().add(bomb.getTwoImage());
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.8), (ActionEvent event) -> bombChangeLast(bomb));
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	/**
	 * Change the bomb to last stage.
	 *
	 * @param bomb the bomb
	 */
	private void bombChangeLast(Bomb bomb) {
		squares.getChildren().remove(bomb.getTwoImage());
		squares.getChildren().add(bomb.getExplodeImage());
	}

}

