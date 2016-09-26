package com.github.klauswk.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.klauswk.drawable.Drawable;
import com.github.klauswk.player.Colliable;

public class Stair implements Colliable	, Drawable{

	private Vector2 pos;
	private Texture texture;
	private Rectangle rectangle;
	
	public Stair(int x, int y) {
		this(new Vector2(x, y));
	}

	public Stair(Vector2 position) {
		texture = new Texture("stair.png");

		while (position.x % 16 != 0) {
			position.x--;
		}
		while (position.y % 16 != 0) {
			position.y--;
		}
		
		rectangle = new Rectangle(position.x,position.y,texture.getWidth(),texture.getHeight());
		this.pos = position;
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
	
	public void setPosition(Vector2 position) {
		this.pos = position;
	}
	
	public Vector2 getPosition() {
		return pos;
	}
	
	private boolean overlaps;
	
	@Override
	public boolean isColliding(Rectangle rectangle) {
		overlaps = this.rectangle.overlaps(rectangle);
		return overlaps;
	}
}
