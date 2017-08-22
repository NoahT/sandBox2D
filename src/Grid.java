import processing.core.PVector;

public abstract class Grid {
	public static final int CELLS_PER_UNIT = 50;
	
	public static PVector cellsToGrid(PVector pVector) {
		return Grid.cellsToGrid(pVector.x, pVector.y);
	}
	
	public static PVector cellsToGrid(float cellX, float cellY) {
		return new PVector(cellsToGrid(cellX), cellsToGrid(cellY));
	}
	
	public static float cellsToGrid(float cells) {
		return cells * CELLS_PER_UNIT;
	}
}
