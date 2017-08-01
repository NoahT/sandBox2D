import java.util.ArrayList;

import org.jbox2d.common.Vec2;

import processing.core.PApplet;
import processing.core.PVector;

public class Startup extends PApplet {
	private static Box2DProcessing box2D;
	private Baseplate baseplate;
	private ArrayList<Box2DMover> movers;
	private Box2DMouseJoint mouseJoint;

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
		Box2DMover base = new Box2DMover(this, new PVector(200, 10), new PVector(width / 2, height / 2), color(255), false),
				wheel1 = new Box2DMover(this, new PVector(50, 50), new PVector((width / 2) - 75, height / 2), color(255), false),
				wheel2 = new Box2DMover(this, new PVector(50, 50), new PVector((width / 2) + 75, height / 2), color(255), false);
		movers.add(base);
		movers.add(wheel1);
		movers.add(wheel2);
		
		mouseJoint = new Box2DMouseJoint(box2D.getGroundBody(), base.body, box2D.coordPixelsToWorld(mouseX, mouseY));
		mouseJoint.setMaxForce(10000);
		mouseJoint.setDampingRatio(1);
		mouseJoint.setFrequencyHz(10000);
		
		Box2DHinge hinge1 = new Box2DHinge(base, wheel1),
				hinge2 = new Box2DHinge(base, wheel2);
		hinge1.setMotorSpeed((float) Math.PI * 4);
		hinge1.setMaxMotorTorque(10000);
		hinge2.setMotorSpeed((float) Math.PI * 4);
		hinge2.setMaxMotorTorque(10000);
		hinge1.toggleMotor();
		hinge2.toggleMotor();
	}

	public void draw() {
		background(0);

		box2D.step();
		fill(255);
		
		mouseJoint.setTarget(new PVector(mouseX, mouseY));
		for(Box2DMover mover : movers) {
			mover.sketch();
		}
		baseplate.sketch();
	}

}
