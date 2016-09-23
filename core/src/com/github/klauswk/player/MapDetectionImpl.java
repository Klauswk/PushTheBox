package com.github.klauswk.player;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.github.klauswk.drawable.Moveable;

public class MapDetectionImpl implements MapDetection {

	private Moveable<Vector2> moveable;
	private TiledMapTileLayer tileMapTileLayer;

	public MapDetectionImpl(Moveable<Vector2> moveable, TiledMapTileLayer tileMapTileLayer) {
		super();
		this.moveable = moveable;
		this.tileMapTileLayer = tileMapTileLayer;
	}

	@Override
	public boolean checkIfCanMove(Direction direction) {

		int posx = 12;
		int posy = 12;

		switch (direction) {

		case RIGHT:
			if(!(tileMapTileLayer.getCell((posx+16)/16, posy/16).getTile().getId() == 83)){
				return true;
			}
			break;
		case LEFT:
			if(!(tileMapTileLayer.getCell((posx-16)/16, posy/16).getTile().getId() == 83)){
				return true;
			}

			break;
		case UP:
			if(!(tileMapTileLayer.getCell(posx/16, (posy+16)/16).getTile().getId() == 83)){
				return true;
			}
			break;

		case DOWN:
			if(!(tileMapTileLayer.getCell(posx/16, (posy-16)/16).getTile().getId() == 83)){
				return true;
			}
			break;
		}
		return false;
	}
}
