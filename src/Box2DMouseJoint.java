import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.joints.MouseJoint;
import org.jbox2d.dynamics.joints.MouseJointDef;

import processing.core.PApplet;
import processing.core.PVector;

public class Box2DMouseJoint extends PApplet {
	Box2DMover mover1,
	mover2;
	MouseJointDef mjd;
	MouseJoint mj;

	Box2DMouseJoint() {
	}

	Box2DMouseJoint(Body mover1, Body mover2, Vec2 target) {
		this(mover1, mover2, new PVector(target.x, target.y));
	}
	
	Box2DMouseJoint(Body mover1, Body mover2, PVector target) {
		this.mjd = new MouseJointDef();
		this.mjd.bodyA = mover1;
		this.mjd.bodyB = mover2;

		this.mj = (MouseJoint) Startup.getWorld().world.createJoint(mjd);
		this.mj.setTarget(Startup.getWorld().coordPixelsToWorld(target.x, target.y));
	}

	void setDampingRatio(float dampingRatio) { //0 to 1
		this.mj.setDampingRatio(dampingRatio);
	}

	void setFrequencyHz(float frequencyHz) {
		this.mj.setFrequency(frequencyHz);
	}

	void setMaxForce(float maxForce) {
		this.mj.setMaxForce(maxForce);
	}

	void setTarget(PVector target) {
		this.mj.setTarget(Startup.getWorld().coordPixelsToWorld(target.x, target.y));
	}
}
