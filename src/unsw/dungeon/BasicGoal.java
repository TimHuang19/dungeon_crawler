package unsw.dungeon;

import java.util.ArrayList;

/**
 * The Class BasicGoal.
 */
public class BasicGoal implements GoalExpression {
	
	/** The goal. */
	private Goal goal;
	
	/** Boolean value indicating whether the goal has been completed or not */
	private boolean completed;
	
	/**
	 * Instantiates a new basic goal.
	 *
	 * @param goal 		The goal
	 */
	public BasicGoal(Goal goal) {
		this.goal = goal;
		this.completed = false;
	}
	
	/**
	 * Sets whether this goal is complete or not.
	 *
	 * @param completed 		A new boolean indicating whether goal has been completed
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	/**
	 * Checks if goal is complete.
	 *
	 * @return true, if goal is complete
	 */
	@Override
	public boolean isComplete() {
		return completed;
	}

	/**
	 * Sets whether the goal is complete or not, if the goal is the same as this one
	 *
	 * @param goal			The goal
	 * @param completed		A boolean indicating whether the goal has been completed or not
	 */
	@Override
	public void setComplete(Goal goal, boolean completed) {
		if (this.goal == goal) {
			this.completed = completed;
		}
	}
	
	/**
	 * Gets the goal.
	 *
	 * @return The goal
	 */
	@Override
	public Goal getGoal() {
		return this.goal;
	}

	/**
	 * Adds the sub goal.
	 *
	 * @param goal 		The goal
	 */
	@Override
	public void addSubGoal(GoalExpression goal) {
		// do nothing
	}

	/**
	 * Gets the subGoals.
	 *
	 * @return 			The subGoals
	 */
	@Override
	public ArrayList<GoalExpression> getSubGoals() {
		return new ArrayList<GoalExpression>();
	}

}
