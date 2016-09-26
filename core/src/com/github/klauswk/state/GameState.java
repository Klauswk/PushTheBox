package com.github.klauswk.state;

import java.util.ArrayList;

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
import com.badlogic.gdx.math.Vector2;
import com.github.klauswk.GameStateManager;
import com.github.klauswk.drawable.Drawable;
import com.github.klauswk.drawable.Moveable;
import com.github.klauswk.object.Box;
import com.github.klauswk.object.BoxService;
import com.github.klauswk.object.MapDetectionBox;
import com.github.klauswk.object.OnCollidleImpl;
import com.github.klauswk.object.Stair;
import com.github.klauswk.player.Colliable;
import com.github.klauswk.player.MapDetection;
import com.github.klauswk.player.Player;

public class GameState extends State {

	private SpriteBatch batch;
	private TiledMap tilemap;
	private TiledMapRenderer tilemaprenderer;
	private OrthographicCamera camera;
	private TiledMapTileLayer tileMapTileLayer;
	private Player player;
	private ArrayList<Colliable> colliables = new ArrayList<Colliable>();
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private BoxService boxService;
	private MapDetection playerDetection;
	private MapDetection boxDetection;
	private OnCollidleImpl onCollidleImpl;
	private GameStateManager gameStateManager;

	public GameState(GameStateManager gameStateManager,OrthographicCamera camera) {
		this.gameStateManager = gameStateManager;
		batch = new SpriteBatch();
		this.camera = camera;
		tilemap = new TmxMapLoader().load("firstmap.tmx");
		tilemaprenderer = new OrthogonalTiledMapRenderer(tilemap);
		tilemaprenderer.setView(camera);
		onCollidleImpl = new OnCollidleImpl();

		tileMapTileLayer = (TiledMapTileLayer) tilemap.getLayers().get(0);

		boxService = new BoxService(tileMapTileLayer);

		player = new Player((int) camera.viewportWidth, (int) camera.viewportHeight);
		playerDetection = new MapDetectionBox(tileMapTileLayer, player.getPosition());
		player.setMapDetection(playerDetection);
		Box box = boxService.getInstance();
		Stair stair = new Stair(32, 32);
		boxDetection = new MapDetectionBox(tileMapTileLayer, box.getPosition());
		batch.setProjectionMatrix(camera.combined);

		colliables.add(box);
		colliables.add(stair);
		drawables.add(box);
		drawables.add(stair);
		drawables.add(player);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		tilemaprenderer.render();

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.move(-16, 0);
			for (Colliable colliable : colliables) {
				if (colliable.isColliding(player.getPlayerBounds())) {
					if (colliable instanceof Moveable) {
						((Moveable<Vector2>) colliable).move(-16, 0);
					}
				}
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.move(16, 0);
			for (Colliable colliable : colliables) {
				if (colliable.isColliding(player.getPlayerBounds())) {
					if (colliable instanceof Moveable) {
						((Moveable<Vector2>) colliable).move(16, 0);
					}
				}
			}
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			player.move(0, 16);
			for (Colliable colliable : colliables) {
				if (colliable.isColliding(player.getPlayerBounds())) {
					if (colliable instanceof Moveable) {
						((Moveable<Vector2>) colliable).move(0, 16);
					}
				}
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player.move(0, -16);
			for (Colliable colliable : colliables) {
				if (colliable.isColliding(player.getPlayerBounds())) {
					if (colliable instanceof Moveable) {
						((Moveable<Vector2>) colliable).move(0, -16);
					}
				}
			}
		} else if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			System.exit(0);
		}

		for (int i = 0; i < colliables.size(); i++) {
			for (int j = i + 1; j < colliables.size(); j++) {
				onCollidleImpl.onCollide(colliables.get(i), colliables.get(j));
			}
		}

		for (Drawable draw : drawables) {
			draw.render(batch);
		}
	}


	@Override
	public void update() {

	}
}
