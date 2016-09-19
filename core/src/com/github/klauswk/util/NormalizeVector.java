package com.github.klauswk.util;

import com.badlogic.gdx.math.Vector2;

public class NormalizeVector implements NormalizeValue<Vector2,Vector2>{

	@Override
	public Vector2 normalize(Vector2 vector) {
		while (vector.x % 16 != 0) {
			vector.x--;
		}
		while (vector.y % 16 != 0) {
			vector.y--;
		}
		return vector;
	}
}
