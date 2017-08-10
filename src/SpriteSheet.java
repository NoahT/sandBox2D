import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteSheet { //TODO: import spritebatch and test for later
	private BufferedImage spriteSheet;
	private ArrayList<BufferedImage> subSprites;
	private int xOffset,
	yOffset,
	width,
	height;

	public SpriteSheet(String filePath) throws IOException {
		this(filePath, -1, -1, 0, 0); //negative to prevent adding buffered images to arraylist
	}

	public SpriteSheet(String filePath, int xOffset, int yOffset, int width, int height) throws IOException {
		this.spriteSheet = ImageIO.read(new File(filePath));
		this.subSprites = new ArrayList<BufferedImage>();

		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.width = width;
		this.height = height;

		this.init();
	}

	public void init() {
		int xPos = 0,
				yPos = 0,
				rows = this.spriteSheet.getHeight() / (this.width + this.xOffset), //number of rows
				columns = this.spriteSheet.getHeight() / (this.height + this.yOffset); //number of columns

		this.subSprites.clear();
		
		for(int index = 0; index < columns; index++) { //set for y
			yPos = (this.height + this.yOffset) * index;
			for(int index2 = 0; index2 < rows; index2++) { //set for x
				yPos = (this.width + this.xOffset) * index2;
				this.subSprites.add(this.spriteSheet.getSubimage(xPos, yPos, this.width, this.height));
			}
		}
	}
}





