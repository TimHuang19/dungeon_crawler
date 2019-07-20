package unsw.dungeon;

import java.util.ArrayList;

public class Bomb extends Entity {
	BombState unlitState;
	BombState litState;
	
	private BombState state;
	private Dungeon dungeon;
	
	public Bomb(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		
		unlitState = new UnlitState(this);
		litState = new LitState(this);
		
		state = unlitState;
	}
	
	public void dropBomb() {
		state.dropBomb();
		
        new java.util.Timer().schedule( 
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                    	explode();
                    }
                }, 
                3000 
        );
	}
	
	public void explode() {
		ArrayList<Entity> targets;
		targets = dungeon.getExplosionTargets(getX(), getY());
		for (Entity e : targets) {
			if (e instanceof Player) {
				if (!((Player) e).isInvincible()) {
					dungeon.gameOver();
					dungeon.removeEntity(this);
					return;
				}
			} else if (e instanceof Boulder) {
				Boulder b = (Boulder) e;
				b.destroy();
			} else if (e instanceof Enemy) {
				dungeon.killEnemy((Enemy) e);
			}
		}
		dungeon.removeEntity(this);
	}

    public void setState(BombState state) {
    	this.state = state;
    }
    
    public BombState getLitState() {
    	return litState;
    }
    
    public BombState getState() {
    	return state;
    }
    
}
