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
	private int posx, posy;
	private Rectangle playerBounds;
	private MapDetection mapDetection;

	public Player(int width, int height) {
		heroi = new Texture("trainer.png");

		TextureRegion[][] sprites = TextureRegion.split(heroi, 16, 16);
		posx = width / 2;
		posy = height / 2;
		while (posx % 16 != 0) {
			posx--;
		}
		while (posy % 16 != 0) {
			posy--;
		}

		playerBounds = new Rectangle(posx, posy, 16,16);
		position = new Vector2(posx,posy);
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
				this.posx += posx;
			}
		} else if (posx > 0) {
			if (mapDetection.checkIfCanMove(Direction.RIGHT)) {
				this.posx += posx;
			}
		}
		if (posy < 0) {
			if (mapDetection.checkIfCanMove(Direction.DOWN)) {
				this.posy += posy;
			}
		} else if (posy > 0) {
			if (mapDetection.checkIfCanMove(Direction.UP)) {
				this.posy += posy;
			}
		}
		playerBounds.setX(this.posx);
		playerBounds.setY(this.posy);
		
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
		sb.draw(animation.getFrame(spriteatual), posx, posy);
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

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}

	private Vector2 position;
	
	@Override
	public Vector2 getPosition() {
		position.x = posx;
		position.y = posy;
		return position;
	}

}
