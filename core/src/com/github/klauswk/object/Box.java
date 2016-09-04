package com.github.klauswk.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.klauswk.drawable.Drawable;

public class Box implements Drawable {

	private Vector2 position;
	private Texture texture;
	private Rectangle rectangle;

	public Box(int x, int y) {
		this(new Vector2(x, y));
	}

	public Box(Vector2 position) {
		texture = new Texture("box.png");
		rectangle = new Rectangle(position.x,position.y,texture.getWidth(),texture.getHeight());
		this.position = position;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(texture,position.x,position.y);
		sb.end();
	}

	@Override
	public void update(float dt) {
		
	}
}
