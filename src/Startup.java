import java.util.ArrayList;

import org.jbox2d.common.Vec2;

import processing.core.PApplet;
import processing.core.PVector;

public class Startup extends PApplet {
	private static Box2DProcessing box2D;
	private Baseplate baseplate;
	private ArrayList<Box2DMover> movers;
	
	public static Box2DProcessing getWorld() {
		return box2D;
	}

	public void settings() {
		fullScreen();
	}

	public void setup() {
		smooth();
		frameRate(120);
		background(0);

		box2D = new Box2DProcessing(this);
		box2D.createWorld();
		box2D.setGravity(0, -50);

		ArrayList<Vec2> points = new ArrayList<Vec2>();
		float xGap = 50,
				yBase = height * .8f,
				angle = 0,
				angleVelocity = radians(5),
				amplitude = 100;

		for(int index = 0; index < (width) / xGap; index++) {
			points.add(new Vec2((0) + (index * xGap), yBase + (sin(angle) * amplitude)));
			angle += angleVelocity;
		}

		baseplate = new Baseplate(this, color(255), points);

		movers = new ArrayList<Box2DMover>();
		Box2DMover mover = new Box2DMover(this, new PVector(20, 20), new PVector(width / 2, height / 2), color(255), false);
		movers.add(mover);
	}

	public void draw() {
		background(0);

		box2D.step();
		fill(255);
		for(Box2DMover mover : movers) {
			mover.sketch();
		}
		Box2DMover mover = new Box2DMover(this, new PVector(20, 20), new PVector(width / 2, height / 2), color(255), false);
		movers.add(mover);
		baseplate.sketch();
	}

}
