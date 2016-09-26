package com.github.klauswk;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.github.klauswk.state.MenuState;

public class PushTheBox extends ApplicationAdapter {

	private GameStateManager gameStateManager;
	private OrthographicCamera cam;

	@Override
	public void create() {
		gameStateManager = new GameStateManager();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 256, 256);
		cam.update();

		gameStateManager.push(new MenuState(gameStateManager,cam));
	}	

	@Override
	public void render() {
		gameStateManager.peek().render();
	}

	@Override
	public void dispose() {
	}
}
