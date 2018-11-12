
public class SeamCarver {
	// create a seam carver object based on the given picture
	Picture picture;
	int picHeight;
	int picWidth;
	public SeamCarver(Picture picture) {
		this.picture = picture;
		this.picWidth = picture.width();
		this.picHeight = picture.height();

	}
	// current picture
	public Picture picture() {
		return picture;
	}
	// width of current picture
	public int width() {
		return picWidth;
	}

	// height of current picture
	public int height() {
		return picHeight;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x == 0 || x == picWidth - 1 || y == 0 || y == picHeight - 1) {
			return 1000;
		}

		double xRed = Math.abs(picture.get(x-1,y).getRed() - picture.get(x+1, y).getRed());
		double xBlue = Math.abs(picture.get(x-1,y).getBlue() - picture.get(x+1, y).getBlue());
		double xGreen = Math.abs(picture.get(x-1,y).getGreen() - picture.get(x+1, y).getGreen());
		double yRed = Math.abs(picture.get(x,y -1).getRed() - picture.get(x, y + 1).getRed());
		double yBlue = Math.abs(picture.get(x,y -1).getBlue() - picture.get(x, y + 1).getBlue());
		double yGreen = Math.abs(picture.get(x,y -1).getGreen() - picture.get(x, y + 1).getGreen());
		double total = xRed*xRed + xGreen*xGreen + xBlue*xBlue + yRed*yRed + yGreen*yGreen + yBlue*yBlue;
		return Math.sqrt(total);
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}