package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController implements DungeonObserver{

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;
    
    private Dungeon dungeon;
        
    private Stage stage;
    
    private String fileName;
        
    private PauseScreen pauseScreen;
    
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.dungeon.registerDungeonObserver(this);
        this.initialEntities = new ArrayList<>(initialEntities);
        this.dungeon.setController(this);
    }
	
    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
    }

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
    
    public void unPause() {
    	dungeon.unPause();
    }
    
    public void addStage(Stage stage) {
    	this.stage = stage;
    }
    
    public void addName(String fileName) {
    	this.fileName = fileName;
    }
    
    public void addPauseScreen(DungeonScreen dungeonScreen) throws IOException {
    	this.pauseScreen = new PauseScreen(stage, fileName, dungeonScreen);
    }
    
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
	
	private void bombChangeFirst(Bomb bomb) {
		squares.getChildren().remove(bomb.getZeroImage());
		squares.getChildren().add(bomb.getOneImage());
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), (ActionEvent event) -> bombChangeSecond(bomb));
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	private void bombChangeSecond(Bomb bomb) {
		squares.getChildren().remove(bomb.getOneImage());
		squares.getChildren().add(bomb.getTwoImage());
		
		Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.8), (ActionEvent event) -> bombChangeLast(bomb));
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	private void bombChangeLast(Bomb bomb) {
		squares.getChildren().remove(bomb.getTwoImage());
		squares.getChildren().add(bomb.getExplodeImage());
	}

}

