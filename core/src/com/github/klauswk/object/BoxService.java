package com.github.klauswk.object;

import java.util.Random;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.github.klauswk.player.MapDetection;
import com.github.klauswk.util.NormalizeValue;
import com.github.klauswk.util.NormalizeVector;
import com.github.klauswk.util.Placeable;

public class BoxService implements BoxFactory, Placeable<Box>{

	private TiledMapTileLayer tiledMapTileLayer;
	private Vector2 mapBounds;
	private Random random;
	private NormalizeValue<Vector2, Vector2> normalizeVector;

	public BoxService(TiledMapTileLayer tiledMapTileLayer) {
		this.tiledMapTileLayer = tiledMapTileLayer;

		mapBounds = new Vector2(256, 256);
		random = new Random();
		normalizeVector = new NormalizeVector();
	}

	@Override
	public Box getInstance() {
		Vector2 vec = normalizeVector
				.normalize(new Vector2(random.nextInt((int) mapBounds.x), random.nextInt((int) mapBounds.y)));
		Box box = new Box(vec);
		checkIfCanBePlaced(box);
		translate(box);
		box.setMapDetection(new MapDetectionBox(this.tiledMapTileLayer, box.getPosition()));
		return box;
	}

	private void translate(Box box) {
		Vector2 vec = box.getPosition();
		for (int i = 0; i < tiledMapTileLayer.getWidth() / 16; i++) {
			for (int j = 0; j < tiledMapTileLayer.getHeight() / 16; j++) {
				if (tiledMapTileLayer.getCell((int) vec.x / 16, (int) vec.y / 16).getTile().getId() == 83) {
					vec.x = 16 * i;
					vec.y = 16 * j;
				} else {
					return;
				}
			}
		}
	}
	@Override
	public boolean checkIfCanBePlaced(Box box) {
		return tiledMapTileLayer.getCell((int) box.getPosition().x / 16, (int) box.getPosition().y / 16).getTile()
				.getId() == 83;
	}
}
