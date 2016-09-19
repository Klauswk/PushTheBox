package com.github.klauswk.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.klauswk.drawable.Drawable;
import com.github.klauswk.drawable.Moveable;
import com.github.klauswk.player.Colliable;
import com.github.klauswk.player.MapDetection;

public class Box implements Moveable , Colliable {

	private Vector2 position;
	private Texture texture;
	private Rectangle rectangle;
	private MapDetection detection;

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
		this.position = position;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	private boolean overlaps;
	
	@Override
	public boolean isColliding(Rectangle rectangle) {
		overlaps = this.rectangle.overlaps(rectangle);
		//System.out.println(this.rectangle.x + " : " + this.rectangle.y);
		//System.out.println(rectangle.x + " : " + rectangle.y);
		if(overlaps){
			System.out.println("Colliding with " + getClass().getSimpleName());
		}
		return overlaps;
	}
	
	@Override
	public void move(int posx, int posy) {
		
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
