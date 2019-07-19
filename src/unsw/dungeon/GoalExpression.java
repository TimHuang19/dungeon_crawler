package unsw.dungeon;

public interface GoalExpression {

	public boolean isComplete();
	
	public void setComplete(Goal goal, Boolean completed);
	
	public Goal getGoal();
	
	public void addSubGoal(GoalExpression goal);
	
}
