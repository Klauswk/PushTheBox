package com.github.klauswk.player;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	private TextureRegion[][] frames;
	private float time;
	private float delay;
	private int currentframe;
	private int posFrame = 0;

	public Animation(TextureRegion[][] frames) {
		this(frames,1);

	}

	public Animation(TextureRegion[][] frames, float time) {
		setFrames(frames, time);
	}

	public void setFrames(TextureRegion[][] frames, float delay) {
		this.frames = frames;
		this.delay = delay;
		time = 0;
		currentframe = 0;
	}

	public void update(float dt) {
		if (delay <= 0)
			return;
		time += dt;
		while (time >= delay) {
			step();
		}
	}

	public void step() {
		time -= delay;
		if (currentframe == frames[posFrame].length -1) {
			currentframe = 0;
		}else{
			currentframe++;
		}

	}

	public TextureRegion getFrame(int frame) {
		posFrame = frame;
		return frames[frame][currentframe];
	}
}