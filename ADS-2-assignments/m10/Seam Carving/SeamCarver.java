
public class SeamCarver {
	// create a seam carver object based on the given picture
	int[][] edgeTo;
	Double[][] distTo;
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

		double xRed = Math.abs(picture.get(x - 1, y).getRed() - picture.get(x + 1, y).getRed());
		double xBlue = Math.abs(picture.get(x - 1, y).getBlue() - picture.get(x + 1, y).getBlue());
		double xGreen = Math.abs(picture.get(x - 1, y).getGreen() - picture.get(x + 1, y).getGreen());
		double yRed = Math.abs(picture.get(x, y - 1).getRed() - picture.get(x, y + 1).getRed());
		double yBlue = Math.abs(picture.get(x, y - 1).getBlue() - picture.get(x, y + 1).getBlue());
		double yGreen = Math.abs(picture.get(x, y - 1).getGreen() - picture.get(x, y + 1).getGreen());
		double total = xRed * xRed + xGreen * xGreen + xBlue * xBlue + yRed * yRed + yGreen * yGreen + yBlue * yBlue;
		return Math.sqrt(total);
	}
	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
        int[][] edgeTo = new int[picHeight][picWidth];
        double[][] distTo = new double[picHeight][picWidth];
        reset(distTo);
        for (int row = 0; row < picHeight; row++) {
            distTo[row][0] = 1000;
        }
        for (int col = 0; col < picWidth - 1; col++) {
            for (int row = 0; row < picHeight; row++) {
                relaxH(row, col, edgeTo, distTo);
            }
        }
        double minDist = Double.MAX_VALUE;
        int minRow = 0;
        for (int row = 0; row < picHeight; row++) {
            if (minDist > distTo[row][picWidth - 1]) {
                minDist = distTo[row][picWidth - 1];
                minRow = row;
            } 
        }
        int[] indices = new int[picWidth];
        for (int col = picWidth - 1, row = minRow; col >= 0; col--) {
            indices[col] = row;
            row -= edgeTo[row][col];
        }
        return indices;
    }
    private void relaxH(int row, int col, int[][] edgeTo, double[][] distTo) {
        int nextCol = col + 1;
        for (int i = -1; i <= 1; i++) {
            int nextRow = row + i;
            if (nextRow < 0 || nextRow >= picHeight) continue;
            if(i == 0) {
            	if(distTo[nextRow][nextCol] >= distTo[row][col]  + energy(nextCol, nextRow)) {
	                distTo[nextRow][nextCol] = distTo[row][col]  + energy(nextCol, nextRow);
	                edgeTo[nextRow][nextCol] = i;
            	}
            }
            if (distTo[nextRow][nextCol] > distTo[row][col]  + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[row][col]  + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
        }
    }
	/**
	 *this method is to find the vertical seam.
	 *first of all find the shortest path from top to.
	 *bottom.
	 * .
	 *
	 * @return sequence of indices for vertical seam.
	 */
	public int[] findVerticalSeam() {
		double[][] energy = new double[picHeight][picWidth];
		int[][] edgeTo = new int[picHeight][picWidth];
		double[][] distTo = new double[picHeight][picWidth];
		reset(distTo);
		int[] indices = new int[picHeight];
		if(picWidth == 1 || picHeight == 1) {
			return indices;
		}
		for(int i = 0; i < picWidth; i++) {
			distTo[0][i] = 1000.0;
		}
		// this is for relaxation.
		for (int i = 0; i < picHeight - 1; i++) {
			for(int j = 0; j < picWidth; j++) {
				relaxV(i, j, edgeTo, distTo);
			}
		}
		// calculating from last row's coloumn
        double minDist = Double.MAX_VALUE;
        int minCol = 0;
        for (int col = 0; col < picWidth; col++) {
            if (minDist > distTo[picHeight - 1][col]) {
                minDist = distTo[picHeight - 1][col];
                minCol = col;
            }
        }
        //indices values of shortest path.
        for (int row = picHeight -1, col = minCol; row >= 0; row--) {
            indices[row] = col;
            col -= edgeTo[row][col];
        }
        indices[0] = indices[1];
        return indices;
    }
	private void reset(double[][] distTo) {
		for(int i = 0; i < distTo.length; i++) {
			for(int j = 0; j < distTo[i].length; j++) {
				distTo[i][j] = Double.MAX_VALUE;
			}
		}
	}
	private void relaxV(int row, int col, int[][] edgeTo, double[][] distTo) {
		int nextRow = row + 1;
        for (int i = -1; i <= 1; i++) {
            int nextCol = col + i;
            if (nextCol < 0 || nextCol >= picWidth) {
            	continue;
            }
            if(i == 0) {
            	if(distTo[nextRow][nextCol] >= distTo[row][col] + energy(nextCol, nextRow)) {
            	distTo[nextRow][nextCol] = distTo[row][col] + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            	}
            }
            if (distTo[nextRow][nextCol] > distTo[row][col] + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[row][col] + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
    	}
	}
	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {
		//handle exceptions
	for(int col = 0; col < picWidth; col++) {
		for(int row = seam[col]; row < picHeight - 1; row++) {
			this.picture.set(col, row, this.picture.get(col, row + 1));
		}
	}
	picHeight--;
	}
	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {
	for(int row = 0; row < picHeight; row++) {
		for(int col = seam[row]; col < picWidth - 1; col++) {
		this.picture.set(col, row, this.picture.get(col + 1, row));
		}
	}
	picWidth--;
	}
}