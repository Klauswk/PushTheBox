package com.github.klauswk;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.github.klauswk.drawable.Drawable;
import com.github.klauswk.object.Box;
import com.github.klauswk.object.BoxService;
import com.github.klauswk.player.Colliable;
import com.github.klauswk.player.MapDetectionImpl;
import com.github.klauswk.player.Player;

public class PushTheBox extends ApplicationAdapter {

	
	private SpriteBatch batch;
	private TiledMap tilemap;
	private TiledMapRenderer tilemaprenderer;
	private OrthographicCamera camera;
	private TiledMapTileLayer tileMapTileLayer;
	private Player player;
	private ArrayList<Colliable> colliables = new ArrayList<Colliable>();
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private BoxService boxService;

	@Override
	public void create() {
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 256, 256);
		camera.update();
		tilemap = new TmxMapLoader().load("firstmap.tmx");
		tilemaprenderer = new OrthogonalTiledMapRenderer(tilemap);
		tilemaprenderer.setView(camera);

		tileMapTileLayer = (TiledMapTileLayer) tilemap.getLayers().get(0);
		
		boxService = new BoxService(tileMapTileLayer);
		
		player = new Player((int) camera.viewportWidth, (int) camera.viewportHeight);
		player.setMapDetection(new MapDetectionImpl(player, tileMapTileLayer));
		Box box = boxService.getInstance();
		batch.setProjectionMatrix(camera.combined);
		
		colliables.add(box);
		drawables.add(box);
		drawables.add(player);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// batch.setProjectionMatrix(camera.combined);
		tilemaprenderer.render();

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.move(-16, 0);
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.move(16, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			player.move(0, 16);
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player.move(0, -16);
		}
		
		for(Colliable colliable : colliables){colliable.isColliding(player.getPlayerBounds());}
		for(Drawable draw : drawables){draw.render(batch);}
	}

	@Override
	public void dispose() {
		/*
		 * batch.dispose(); img.dispose();
		 */
	}
}
