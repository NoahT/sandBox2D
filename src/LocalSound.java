import processing.core.PVector;

public class LocalSound extends Sound {
	private PVector position;
	private float maxRunoffDistance;

	public LocalSound() {
		this(null, new PVector(0, 0), 0, 0);
	}

	public LocalSound(String file, float positionX, float positionY, double volume, float maxRunoffDistance) {
		this(file, new PVector(positionX, positionY), volume, maxRunoffDistance);
	}

	public LocalSound(String file, PVector position, double volume, float maxRunoffDistance) {
		super(file);
		this.position = position;
		this.maxRunoffDistance = maxRunoffDistance;
		
		//super.setVolume(.5);
		super.setVolume(volume);
	}
	
	public void setPosition(PVector position) {
		this.position = position;
	}
	
	public float getDistance(PVector hearingPoint) {
		return PVector.sub(this.position, hearingPoint).mag();
	}
	
	public void updatePosition(PVector soundPoint) {
		this.position.set(soundPoint);
	}
	
	public void updateVolume(PVector hearingPoint) {
		float distance = this.getDistance(hearingPoint);
		double volume = (distance > this.maxRunoffDistance) ? 0 : 1 - (distance / maxRunoffDistance);
		super.setVolume(volume);
	}
	
	public void update(PVector soundPoint, PVector hearingPoint) {
		this.updatePosition(soundPoint);
		this.updateVolume(hearingPoint);
	}
	
}
