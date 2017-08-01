import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import processing.core.PApplet;
import processing.core.PVector;

public class Box2DMover extends PApplet {
	Startup startup;

	PVector size;

	int baseColor;

	Body body;
	BodyDef bodyDef;

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
	
	Box2DMover(Startup startup, PVector size, Vec2 location, int baseColor, boolean anchored) {
		this(startup, size, new PVector(location.x, location.y), baseColor, anchored);
	}

	Box2DMover(Startup startup, PVector size, PVector location, int baseColor, boolean anchored) {
		this.startup = startup;

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
		fixtureDef.restitution = .5f;

		body.createFixture(fixtureDef);
	}

	void sketch() {
		Vec2 position = Startup.getWorld().getBodyPixelCoord(this.body);
		float angle = body.getAngle();

		startup.pushMatrix();
		startup.translate(position.x, position.y);
		startup.rotate(-angle); //counter clockwise in box2D

		startup.fill(this.baseColor);
		startup.stroke(this.baseColor);

		startup.rectMode(CENTER);

		startup.rect(0, 0, this.size.x, this.size.y);
		startup.popMatrix();
	}

	void removeBody() {
		Startup.getWorld().destroyBody(this.body);
	}
}
