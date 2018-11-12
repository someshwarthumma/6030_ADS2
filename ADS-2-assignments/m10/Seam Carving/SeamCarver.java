
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
        for(int i = 0; i < distTo.length; i++) {
			for(int j = 0; j < distTo[i].length; j++) {
				distTo[i][j] = Double.MAX_VALUE;
			}
		}
        for (int row = 0; row < picHeight; row++) {
            distTo[row][0] = 1000;
        }
        for (int col = 0; col < picWidth - 1; col++) {
            for (int row = 0; row < picHeight; row++) {
                relaxHorizontal(row, col);
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

		private void relaxHorizontal(int row, int col) {
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

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		int[] indices = new int[picHeight];
		edgeTo = new int[picHeight][picWidth];
		distTo = new Double[picHeight][picWidth];
		if(picHeight <2 || picWidth < 2){
			return indices;
		}
		for ( int height = 0; height < distTo.length; height++) {
			for (int width = 0; width < distTo[height].length; width++) {
				distTo[height][width] = Double.MAX_VALUE;
			}
		}

		for (int width = 0; width < picWidth ; width++) {
			distTo[0][width] = 1000.0;
		}


		for (int height = 0; height < picHeight-1 ; height++) {
			for (int width = 0 ; width < picWidth ; width++) {
				relaxVertical(height , width);
			}
		}

		Double minDist = Double.MAX_VALUE;
		int minWidth = 0;
		for(int width = 0; width < picWidth ; width++){
			if(minDist > distTo[picHeight - 1][width]) {
				minDist = distTo[picHeight-1][width];
				minWidth = width;
			}
		}
		int trace = minWidth;
		for(int height = picHeight -1; height >= 0; height-- ) {
			indices[height] = trace;
			trace -= edgeTo[height][trace];
		}
		indices[0] = indices[1];
		return indices;
	}

	private void relaxVertical(int row, int col) {
		int height = row;
		int width = col;
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

		/*int nextHeight = height + 1;
		int prevWidth = width - 1;
		int nextWidth = width + 1;
		Double distEnergy = distTo[height][width];
		Double e1 = energy(nextHeight , prevWidth);
		Double e2 = energy(nextHeight , width);
		Double e3 = energy(nextHeight, nextWidth);
		if (prevWidth >= 0 && nextWidth < picWidth && distTo[nextHeight][prevWidth] > distEnergy + e1) {
			distTo[nextHeight][prevWidth] = e1;
			edgeTo[nextHeight][nextWidth] = width;
		} else if ((prevWidth >= 0) && (nextWidth < picWidth) && (distTo[nextHeight][nextWidth] > distEnergy + e3)) {
			distTo[nextHeight][nextWidth] = e3;
			edgeTo[nextHeight][nextWidth] = width;
		} else if ((prevWidth >= 0 && nextWidth < picWidth && distTo[nextHeight][width] >= distEnergy + e2)) {
		distTo[nextHeight][width] = e2;
			edgeTo[nextHeight][width] = width;
		*/
	}
}
	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {
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