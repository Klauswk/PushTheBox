package com.github.klauswk.state;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class State {
	
	private OrthographicCamera cam;
	
	public abstract void update();
	public abstract void render();
	
	
}
