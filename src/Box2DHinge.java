import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

import processing.core.PApplet;
import processing.core.PVector;

public class Box2DHinge {
	private PApplet pApplet;
	
	Box2DMover mover1,
	mover2;
	RevoluteJointDef rjd;
	RevoluteJoint rj;

	Box2DHinge(PApplet pApplet, Box2DMover mover1, Box2DMover mover2) {
		this(pApplet, mover1, mover2, mover2.getBody().getWorldCenter());
	}

	Box2DHinge(PApplet pApplet, Box2DMover mover1, Box2DMover mover2, PVector origin) {
		this(pApplet, mover1, mover2, Startup.getWorld().coordPixelsToWorld(origin));
	}
	
	Box2DHinge(PApplet pApplet, Box2DMover mover1, Box2DMover mover2, Vec2 origin) {
		this.mover1 = mover1;
		this.mover2 = mover2;

		this.rjd = new RevoluteJointDef();
		this.rjd.initialize(mover1.getBody(), mover2.getBody(), origin);

		this.rj = (RevoluteJoint) Startup.getWorld().world.createJoint(rjd);
	}

	void setMotorSpeed(float motorSpeed) {
		this.rjd.motorSpeed = motorSpeed;
		this.rj = (RevoluteJoint) Startup.getWorld().world.createJoint(rjd);
	}

	void setMaxMotorTorque(float maxMotorTorque) {
		this.rjd.maxMotorTorque = maxMotorTorque;
		this.rj = (RevoluteJoint) Startup.getWorld().world.createJoint(rjd);
	}

	void toggleMotor() {
		this.rj.enableMotor(!this.rj.isMotorEnabled());
	}

	void setMotorOn(boolean isMotorOn) {
		this.rj.enableMotor(isMotorOn);
	}
}
