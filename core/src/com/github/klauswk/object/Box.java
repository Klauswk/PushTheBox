package com.github.klauswk.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.klauswk.drawable.Drawable;
import com.github.klauswk.drawable.Moveable;
import com.github.klauswk.player.Colliable;
import com.github.klauswk.player.Direction;
import com.github.klauswk.player.MapDetection;

public class Box implements Moveable<Vector2> , Colliable {

	private Vector2 pos;
	private Texture texture;
	private Rectangle rectangle;
	private MapDetection mapDetection;

	public Box(int x, int y) {
		this(new Vector2(x, y));
	}

	public Box(Vector2 position) {
		texture = new Texture("box.png");

		while (position.x % 16 != 0) {
			position.x--;
		}
		while (position.y % 16 != 0) {
			position.y--;
		}
		
		rectangle = new Rectangle(position.x,position.y,texture.getWidth(),texture.getHeight());
		this.pos = position;
	}
	
	public void setPosition(Vector2 position) {
		this.pos = position;
	}
	
	@Override
	public Vector2 getPosition() {
		return pos;
	}
	
	private boolean overlaps;
	
	@Override
	public boolean isColliding(Rectangle rectangle) {
		overlaps = this.rectangle.overlaps(rectangle);
		return overlaps;
	}
	
	@Override
	public void move(int posx, int posy) {
		if (posx < 0) {
			if (mapDetection.checkIfCanMove(Direction.LEFT)) {
				this.pos.x += posx;
			}
		} else if (posx > 0) {
			if (mapDetection.checkIfCanMove(Direction.RIGHT)) {
				this.pos.x += posx;
			}
		}
		if (posy < 0) {
			if (mapDetection.checkIfCanMove(Direction.DOWN)) {
				this.pos.y += posy;
			}
		} else if (posy > 0) {
			if (mapDetection.checkIfCanMove(Direction.UP)) {
				this.pos.y += posy;
			}
		}
		rectangle.setX(this.pos.x);
		rectangle.setY(this.pos.y);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(texture,pos.x,pos.y);
		sb.end();
	}

	@Override
	public void update(float dt) {
		
	}
}
