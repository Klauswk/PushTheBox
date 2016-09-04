package com.github.klauswk.drawable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Drawable {

	public void update(float dt);
	public void render(SpriteBatch sb);
	
}
