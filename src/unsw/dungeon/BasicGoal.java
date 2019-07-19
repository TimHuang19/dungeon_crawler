package unsw.dungeon;

public class BasicGoal implements GoalExpression {
	
	private Goal goal;
	private boolean completed;
	
	public BasicGoal(Goal goal) {
		this.goal = goal;
		this.completed = false;
	}
	
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@Override
	public boolean isComplete() {
		return completed;
	}

	@Override
	public void setComplete(Goal goal, Boolean completed) {
		if (this.goal == goal) {
			this.completed = completed;
		}
	}
	
	@Override
	public Goal getGoal() {
		return this.goal;
	}

	@Override
	public void addSubGoal(GoalExpression goal) {
		// do nothing
	}

}
