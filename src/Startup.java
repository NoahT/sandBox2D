import java.util.ArrayList;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

import processing.core.PApplet;
import processing.core.PVector;

public class Startup extends PApplet implements ContactListener {
	private static Box2DProcessing box2D;
	private Baseplate baseplate;
	private ArrayList<Box2DMover> movers;
	private Box2DMouseJoint mouseJoint;
	private Player player;

	public static Box2DProcessing getWorld() {
		return box2D;
	}

	public void settings() {
		size(1000, 500);//fullScreen();
	}

	public void setup() {
		smooth();
		frameRate(120);
		background(0);

		box2D = new Box2DProcessing(this);
		box2D.createWorld();
		box2D.setGravity(0, -50);
		box2D.listenForCollisions();

		ArrayList<Vec2> points = new ArrayList<Vec2>();
		float xGap = 1,
				yBase = height * 1f,
				angle = 0,
				angleVelocity = radians(0),
				amplitude = 100;

		for(int index = 0; index < (width) / xGap; index++) {
			points.add(new Vec2((0) + (index * xGap), yBase + (sin(angle) * amplitude)));
			angle += angleVelocity;
		}

		baseplate = new Baseplate(this, color(255), points);

		movers = new ArrayList<Box2DMover>();

		/*
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
		 */
		player = new Player(this);
	}

	public void draw() {
		background(0);
		box2D.step();
		fill(255);

		float random = (float) new java.util.Random().nextDouble();
		int color = (random < .33f) ? color(255, 0, 0, 255 / 2) : (random < .66f) ? color(0, 255, 0, 255 / 2) : color(0, 0, 255, 255 / 2);
		if(frameCount % 2 == 0) {
			Box2DMover mover = new Box2DMover(this, new PVector(10, 10), new PVector(width / 2, height / 2), color, false);
			movers.add(mover);
		}
		for(int index = 0; index < movers.size(); index++) {
			movers.get(index).sketch();
		}

		if(keyPressed) {
			if(key == 'a')
				player.moveLeft();
			if(key == 'd')
				player.moveRight();
			if(key == ' ')
				player.jump();
		}

		Vec2 position = Startup.getWorld().getBodyPixelCoord(this.player.getTorso().getBody());

		pushMatrix();
		player.sketch();
		baseplate.sketch();
		popMatrix();
	}
	/*
	@Override
	public void keyTyped() {
		if(keyPressed) {
			switch(key) {
			case 'a':
				player.moveLeft();
				break;
			case 'd':
				player.moveRight();
				break;
			case ' ':
				player.jump();
				break;
			default:
				break;
			}
		}else {
			player.stop();
		}
	}
	 */

	@Override
	public void beginContact(Contact contact) {
		Fixture f1 = contact.getFixtureA(),
				f2 = contact.getFixtureB();
		Body b1 = f1.getBody(),
				b2 = f2.getBody();
		Object o1 = b1.getUserData(),
				o2 = b2.getUserData();

		try {
			if(o1.getClass() == Player.class) {
				Player p1 = (Player) o1;
				p1.setMidAir(false);
			}else if(o2.getClass() == Player.class) {
				Player p2 = (Player) o2;
				p2.setMidAir(false);
			}
		}catch(NullPointerException exception) {
		}
	}

	@Override
	public void endContact(Contact contact) {
		Fixture f1 = contact.getFixtureA(),
				f2 = contact.getFixtureB();
		Body b1 = f1.getBody(),
				b2 = f2.getBody();
		Object o1 = b1.getUserData(),
				o2 = b2.getUserData();

		try {
			if(o1.getClass() == Player.class) {
				Player p1 = (Player) o1;
				p1.setMidAir(true);
			}else if(o2.getClass() == Player.class) {
				Player p2 = (Player) o2;
				p2.setMidAir(true);
			}
		}catch(NullPointerException exception) {
		}
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
	}


}
