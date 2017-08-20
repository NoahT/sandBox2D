import processing.core.PVector;

public abstract class Grid {
	public static final int CELLS_PER_UNIT = 500;
	
	public static PVector getGridPosition(PVector position) {
		return position.set(getGridPosition(position.x), getGridPosition(position.y));
	}
	
	public static PVector getGridPosition(float positionX, float positionY) {
		return new PVector(getGridPosition(positionX), getGridPosition(positionY));
	}
	
	public static float getGridPosition(float position) {
		return position * CELLS_PER_UNIT;
	}
}
