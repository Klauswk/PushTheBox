package com.github.klauswk.object;

import com.badlogic.gdx.math.Vector2;
import com.github.klauswk.player.Colliable;

public class OnCollidleImpl implements OnCollide {

	private Vector2 mapAway = new Vector2(-64,-64);
	
	@Override
	public void onCollide(Colliable a, Colliable b) {
		if(a == null || b == null){
			return;
		}
		if(a instanceof Stair){
			if(b instanceof Box){
				if(a.isColliding(((Box)b).getRectangle())){
					((Box) b).setPosition(mapAway);
				}
			}
		}else if(b instanceof Stair){
			if(b.isColliding(((Box)a).getRectangle())){
				((Box) a).setPosition(mapAway);
			}
		}
	}
}
