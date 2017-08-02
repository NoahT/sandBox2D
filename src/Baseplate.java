import java.util.ArrayList;

import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import processing.core.PApplet;

public class Baseplate {
	private PApplet pApplet;
	
	int baseColor;

	Body body;
	ArrayList<Vec2> points;

	Baseplate(PApplet pApplet, int baseColor, ArrayList<Vec2> points) {
		this.pApplet = pApplet;
		
		this.baseColor = baseColor;
		this.points = points;

		Vec2[] vertices = new Vec2[this.points.size()];
		
		for(int index = 0; index < vertices.length; index++) {
			vertices[index] = Startup.getWorld().coordPixelsToWorld(this.points.get(index));
		}

		ChainShape chainShape = new ChainShape();
		chainShape.createChain(vertices, vertices.length);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = chainShape;
		fixtureDef.density = 1;
		fixtureDef.friction = .5f;
		fixtureDef.restitution = .2f;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.STATIC;

		this.body = Startup.getWorld().createBody(bodyDef);
		this.body.createFixture(fixtureDef);
	}

	public void removeBody() {
		Startup.getWorld().destroyBody(this.body);
	}

	public void sketch() {
		try {
			pApplet.stroke(this.baseColor);
			pApplet.noFill();
			pApplet.beginShape();
			for(Vec2 vector: this.points) {
				pApplet.vertex(vector.x, vector.y);
			}
			pApplet.endShape();
		}catch(NullPointerException exception) {
			exception.printStackTrace();
		}
	}

	public String toString() {
		return "" + this.baseColor;
	}
}
