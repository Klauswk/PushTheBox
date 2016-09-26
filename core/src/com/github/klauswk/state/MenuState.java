package com.github.klauswk.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.klauswk.GameStateManager;

public class MenuState extends State{

	private SpriteBatch sb;
	private BitmapFont font;
	private BitmapFont titleFont;
	
	private String title = "Push The Box";
	
	private int currentItem = 0;
	private String[] menuOptions;
	private OrthographicCamera cam;
	private GameStateManager gameStateManager;
	
	
	public MenuState(GameStateManager gameStateManager,OrthographicCamera cam) {
		this.cam = cam;
		this.gameStateManager = gameStateManager;
		
		sb = new SpriteBatch();
		
		titleFont = new BitmapFont();
		font = new BitmapFont();
		
		titleFont.setColor(Color.BLUE);
		
		menuOptions = new String[]{
				"Play",
				"Quit"
		};
		
		
	}
	
	@Override
	public void render() {
		
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			if(currentItem >= menuOptions.length){
				currentItem = 0;
			}else{
				currentItem++;
			}
		}else if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
			if(currentItem == 1){
				System.exit(0);
			}else{
				gameStateManager.set(new GameState(gameStateManager,cam));
			}
		}
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		titleFont.draw(sb,title,128,240);
		for (int i = 0; i < menuOptions.length; i++) {
			if(i == currentItem){
				font.setColor(Color.CORAL);
				font.draw(sb, menuOptions[i],(128),128- (i*16));
			}else{
				font.setColor(Color.GOLD);
				font.draw(sb, menuOptions[i],(128),128 - (i*16));
			}
		}
		sb.end();
		
	}
	
	@Override
	public void update() {
		
	}
}
