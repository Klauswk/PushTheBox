package com.github.klauswk.object;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.github.klauswk.player.Direction;
import com.github.klauswk.player.MapDetection;

public class MapDetectionBox implements MapDetection {

	private TiledMapTileLayer tileMapTileLayer;
	private Vector2 position;

	public MapDetectionBox(TiledMapTileLayer tileMapTileLayer, Vector2 position) {
		super();
		this.tileMapTileLayer = tileMapTileLayer;
		this.position = position;
	}



	@Override
	public boolean checkIfCanMove(Direction direction) {
		float posx = position.x;
		float posy = position.y;

		switch (direction) {

		case RIGHT:
			if(!(getCellId(16, 0) == 83)){
				return true;
			}
			break;
		case LEFT:
			if(!(getCellId(-16, 0) == 83)){
				return true;
			}

			break;
		case UP:
			if(!(getCellId(0,16) == 83)){
				return true;
			}
			break;

		case DOWN:
			if(!(getCellId(0,-16) == 83)){
				return true;
			}
			break;
		}
		return false;
	}
	
	private int getCellId(float x, float y){
		return tileMapTileLayer.getCell((int)((position.x+x)/16),(int)(position.y+y)/16).getTile().getId();
	}

}
