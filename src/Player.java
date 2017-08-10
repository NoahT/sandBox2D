import org.jbox2d.common.Vec2;

import processing.core.PApplet;
import processing.core.PVector;

public class Player {
	private PApplet pApplet;

	private Box2DMover torso;
	private float maxMovingVelocity,
	maxJumpVelocity;
	private boolean midAir;

	public Player(PApplet pApplet) {
		this.pApplet = pApplet;

		this.torso = new Box2DMover(this.pApplet,
				new PVector(40, 80),
				new PVector((this.pApplet.width / 2) - 10, (this.pApplet.height / 2) - 20),
				this.pApplet.color(255),
				false);
		this.torso.getBody().setFixedRotation(true);
		this.torso.getBody().setUserData(this);
		
		this.maxMovingVelocity = 50;
		this.maxJumpVelocity = 500;
		this.midAir = true;
	}
	
	public void setMidAir(boolean midAir) {
		this.midAir = midAir;
	}
	
	public boolean isMidAir() {
		return this.midAir;
	}

	public void setLocation(PVector location) {
		this.torso.getBodyDef().setPosition(Startup.getWorld().coordPixelsToWorld(location));
	}
	
	public void sketch() {
		this.torso.sketch();
	}
	
	public void sketch(PVector translation) {
		this.torso.sketch(translation);
	}
	
	public void sketch(float translateX, float translateY) {
		this.torso.sketch(translateX, translateY);
	}
	
	public Box2DMover getTorso() {
		return this.torso;
	}
	
	public float getXVelocity() {
		return this.torso.getBody().getLinearVelocity().x;
	}

	public float getYVelocity() {
		return this.torso.getBody().getLinearVelocity().y;
	}

	public void moveLeft() {
		float velocityChange = this.maxMovingVelocity - Math.abs(this.getXVelocity());
		this.torso.getBody().applyLinearImpulse(new Vec2(-velocityChange, 0), this.torso.getBody().getWorldCenter(), false);
	}

	public void moveRight() {
		float velocityChange = this.maxMovingVelocity - Math.abs(this.getXVelocity());
		this.torso.getBody().applyLinearImpulse(new Vec2(velocityChange, 0), this.torso.getBody().getWorldCenter(), false);
	}

	public void jump() {
		//this.torso.getBody().applyForce(new Vec2(0, this.maxJumpVelocity), this.torso.getBody().getWorldCenter());
		if(this.midAir)
			return;
		float velocityChange = this.maxJumpVelocity - Math.abs(this.getYVelocity());
		this.torso.getBody().applyLinearImpulse(new Vec2(0, this.maxJumpVelocity), this.torso.getBody().getWorldCenter(), false);
	}

	public void stop() {
		this.torso.getBody().setLinearVelocity(new Vec2(0, this.torso.getBody().getLinearVelocity().y));
	}
}
