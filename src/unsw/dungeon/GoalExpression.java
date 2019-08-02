package unsw.dungeon;

import java.util.ArrayList;

/**
 * The Interface GoalExpression.
 */
public interface GoalExpression {

	/**
	 * Checks if goal is complete.
	 *
	 * @return true, if is complete
	 */
	public boolean isComplete();
	
	/**
	 * Sets whether the goal is complete.
	 *
	 * @param goal 			The goal
	 * @param completed 	The boolean value indicating if goal is complete
	 */
	public void setComplete(Goal goal, boolean completed);
	
	/**
	 * Gets the goal.
	 *
	 * @return 			The goal
	 */
	public Goal getGoal();
	
	/**
	 * Gets the subGoals.
	 *
	 * @return 			The subGoals
	 */
	public ArrayList<GoalExpression> getSubGoals();
	
	/**
	 * Adds a sub goal.
	 *
	 * @param goal 		The subgoal
	 */
	public void addSubGoal(GoalExpression goal);
	
}
