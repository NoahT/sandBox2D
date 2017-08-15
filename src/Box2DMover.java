import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import processing.core.PApplet;
import processing.core.PVector;

public class Box2DMover {
	private PApplet pApplet;

	private PVector size;

	private int baseColor;

	private Body body;
	private BodyDef bodyDef;

	/*
	Box2DMover() {
		this(new PVector(0, 0), new PVector(0, 0), 0, false);
	}

	Box2DMover(PVector size) {
		this(size, new PVector(0, 0), 0, false);
	}

	Box2DMover(PVector size, PVector location, boolean anchored) {
		this(size, location, 0, anchored);
	}
	 */
	
	Box2DMover(PApplet pApplet, PVector size, Vec2 location, int baseColor, boolean anchored) {
		this(pApplet, size, new PVector(location.x, location.y), baseColor, anchored);
	}

	Box2DMover(PApplet pApplet, PVector size, PVector location, int baseColor, boolean anchored) {
		this.pApplet = pApplet;

		this.size = size;
		this.baseColor = baseColor;

		this.bodyDef = new BodyDef();
		bodyDef.type = (anchored ? BodyType.STATIC : BodyType.DYNAMIC);
		bodyDef.position.set(Startup.getWorld().coordPixelsToWorld(location.x, location.y));
		//bodyDef.fixedRotation = true;

		this.body = Startup.getWorld().createBody(bodyDef);

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(Startup.getWorld().scalarPixelsToWorld(this.size.x / 2), Startup.getWorld().scalarPixelsToWorld(this.size.y / 2));

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygonShape;
		fixtureDef.density = 1;
		fixtureDef.friction = .5f;
		fixtureDef.restitution = .1f;

		body.createFixture(fixtureDef);
		body.setUserData(this);
	}
	
	public Body getBody() {
		return this.body;
	}
	
	public BodyDef getBodyDef() {
		return this.bodyDef;
	}
	
	public void sketch() {
		this.sketch(0, 0);
	}
	
	public void sketch(PVector translate) {
		this.sketch(translate.x, translate.y);
	}

	public void sketch(float translateX, float translateY) {
		Vec2 position = Startup.getWorld().getBodyPixelCoord(this.body);
		float angle = body.getAngle();

		pApplet.pushMatrix();
		pApplet.translate(position.x, position.y);
		pApplet.rotate(-angle); //counter clockwise in box2D

		pApplet.fill(this.baseColor);
		pApplet.stroke(this.baseColor);

		pApplet.rectMode(pApplet.CENTER);

		pApplet.rect(translateX, translateY, this.size.x, this.size.y);
		pApplet.popMatrix();
	}

	public void removeBody() {
		Startup.getWorld().destroyBody(this.body);
	}
}
