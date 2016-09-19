package com.github.klauswk.drawable;

public interface Moveable<T> extends Drawable{

	public void move(int posx , int posy);
	public T getPosition();
}
