package com.github.klauswk.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.github.klauswk.drawable.Moveable;
import com.badlogic.gdx.math.Vector2;

public class Player implements Moveable<Vector2> , Colliable{
	public static final int HEROICIMA = 3;

	public static final int HEROIBAIXO = 0;

	public static final int HEROIDIREITA = 2;

	public static final int HEROIESQUERDA = 1;

	private Texture heroi;
	public static int spriteatual = 0;
	private Animation animation;
	private Vector2 position;
	private Rectangle playerBounds;
	private MapDetection mapDetection;

	public Player(int width, int height) {
		heroi = new Texture("trainer.png");

		position = new Vector2();
		
		TextureRegion[][] sprites = TextureRegion.split(heroi, 16, 16);
		position.x = width / 2;
		position.y = height / 2;
		while (position.x % 16 != 0) {
			position.x--;
		}
		while (position.y % 16 != 0) {
			position.y--;
		}

		playerBounds = new Rectangle(position.x, position.y, 16,16);
		animation = new Animation(sprites, 1);
	}

	public Rectangle getPlayerBounds() {
		return playerBounds;
	}

	public void setPlayerBounds(Rectangle playerBounds) {
		this.playerBounds = playerBounds;
	}

	@Override
	public void update(float dt) {
		animation.update(dt);
	}

	@Override
	public void move(int posx, int posy) {
		if (posx < 0) {
			if (mapDetection.checkIfCanMove(Direction.LEFT)) {
				this.position.x += posx;
			}
		} else if (posx > 0) {
			if (mapDetection.checkIfCanMove(Direction.RIGHT)) {
				this.position.x += posx;
			}
		}
		if (posy < 0) {
			if (mapDetection.checkIfCanMove(Direction.DOWN)) {
				this.position.y += posy;
			}
		} else if (posy > 0) {
			if (mapDetection.checkIfCanMove(Direction.UP)) {
				this.position.y += posy;
			}
		}
		playerBounds.setX(this.position.x);
		playerBounds.setY(this.position.y);
		
		if (posx > 0) {
			spriteatual = HEROIDIREITA;
			return;
		} else if (posx < 0) {
			spriteatual = HEROIESQUERDA;
			return;
		} else if (posy > 0) {
			spriteatual = HEROICIMA;
			return;
		} else {
			spriteatual = HEROIBAIXO;
			return;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		update(1 / 30f);
		sb.begin();
		sb.draw(animation.getFrame(spriteatual), position.x, position.y);
		sb.end();
	}
	
	@Override
	public boolean isColliding(Rectangle rectangle) {
		if(rectangle == null){
			return false;
		}
		return playerBounds.overlaps(rectangle);
	}

	public MapDetection getMapDetection() {
		return mapDetection;
	}

	public void setMapDetection(MapDetection mapDetection) {
		this.mapDetection = mapDetection;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public Vector2 getPosition() {
		return position;
	}

}
