package com.github.klauswk.player;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class MapDetectionImpl implements MapDetection {

	private Player player;
	private TiledMapTileLayer tileMapTileLayer;

	public MapDetectionImpl(Player player, TiledMapTileLayer tileMapTileLayer) {
		super();
		this.player = player;
		this.tileMapTileLayer = tileMapTileLayer;
	}

	@Override
	public boolean checkIfCanMove(Direction direction) {

		int posx = player.getPosx();
		int posy = player.getPosy();

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
