package com.github.klauswk.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.klauswk.drawable.Moveable;

public class Player implements Moveable{
	public static final int HEROICIMA = 3;

	public static final int HEROIBAIXO = 0;

	public static final int HEROIDIREITA = 2;

	public static final int HEROIESQUERDA = 1;

	private Texture heroi;
	public static int spriteatual = 0;
	private Animation animation;
	private int widht, height;
	private int posx, posy;
	private Body body;

	public Player(int width, int height) {
		this.widht = width;
		this.height = height;
		heroi = new Texture("trainer.png");
		
		TextureRegion[][] sprites = TextureRegion.split(heroi, 16, 16);
		posx = width / 2;
		posy = height / 2;
		while (posx % 32 != 0) {
			posx--;
		}
		while (posy % 32 != 0) {
			posy--;
		}
		
		animation = new Animation(sprites,1);
	}

	@Override
	public void update(float dt) {
		animation.update(dt);
	}

	@Override
	public void move(int posx , int posy){
		if(posx < 0){
			if(!(this.posx == 0)){
				this.posx += posx;
			}
		}else if(posx > 0){
			if(!(this.posx == 256)){
				this.posx += posx;
			}
		}
		this.posy += posy;
		if(posx > 0){
			spriteatual = HEROIDIREITA;
			return;
		}else if(posx < 0){
			spriteatual = HEROIESQUERDA;
			return;
		}else if(posy > 0){
			spriteatual = HEROICIMA;
			return;
		}else{
			spriteatual = HEROIBAIXO;
			return;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		update(1/30f);
		sb.begin();
		sb.draw(animation.getFrame(spriteatual), posx, posy);
		sb.end();
	}
}
