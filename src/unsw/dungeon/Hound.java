package unsw.dungeon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

public class Hound extends Enemy {
	public Hound(Dungeon dungeon, int x, int y) {
		super(dungeon,x ,y);
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.2), (ActionEvent event) -> enemyMovement());
		timeline.getKeyFrames().add(kf);
		timeline.play();
		this.setTimeline(timeline);
	}
}
