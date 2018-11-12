/**.
 * seam carver class
 */
class SeamCarver {
    /**.
     * edgeTo variable
     */
    private int[][] edgeTo;
    /**.
     * distTo variable
     */
    private Double[][] distTo;
    /**.
     * picture variable
     */
    private Picture picture;
    /**.
     * height variable
     */
    private int picHeight;
    /**.
     * width of the pic
     */
    private int picWidth;
    /**.
     * constructor
     *
     * @param      picture  The picture
     */
    public SeamCarver(final Picture picture) {
        this.picture = picture;
        this.picWidth = picture.width();
        this.picHeight = picture.height();

    }
    /**.
     * method to get the picture obj
     *
     * @return     { Picture }
     */
    public Picture picture() {
        return picture;
    }
    /**.
     * width method to return the width of the pic
     *
     * @return     { int }
     */
    public int width() {
        return picWidth;
    }
    /**.
     * getter method for height of the pic
     *
     * @return     { int }
     */
    public int height() {
        return picHeight;
    }
    /**.
     * method to find the energy
     *
     * @param      x     { x cordinate }
     * @param      y     { y cordinate }
     *
     * @return     { Energy in Double type }
     */
    public double energy(final int x, final int y) {
        if (x == 0 || x == picWidth - 1 || y == 0 || y == picHeight - 1) {
            final double thou = 1000.0;
            return thou;
        }

        double xRed = Math.abs(picture.get(x - 1, y).
            getRed() - picture.get(x + 1, y).getRed());
        double xBlue = Math.abs(picture.get(x - 1, y).
            getBlue() - picture.get(x + 1, y).getBlue());
        double xGreen = Math.abs(picture.get(x - 1, y).
            getGreen() - picture.get(x + 1, y).getGreen());
        double yRed = Math.abs(picture.get(x, y - 1).
            getRed() - picture.get(x, y + 1).getRed());
        double yBlue = Math.abs(picture.get(x, y - 1).
            getBlue() - picture.get(x, y + 1).getBlue());
        double yGreen = Math.abs(picture.get(x, y - 1).
            getGreen() - picture.get(x, y + 1).getGreen());
        double total = xRed * xRed + xGreen * xGreen
        + xBlue * xBlue + yRed * yRed + yGreen * yGreen + yBlue * yBlue;
        return Math.sqrt(total);
    }
    /**.
     *sequence of indices for horizontal seam
     *
     *time complexity is O(w*h)
     *w is the width and h is the height
     * @return  sequence of indices of horizontal seam
     */
    public int[] findHorizontalSeam() {
        int[][] edgeTo = new int[picHeight][picWidth];
        double[][] distTo = new double[picHeight][picWidth];
        initialise(distTo);
        final double thou = 1000.0;
        for (int row = 0; row < picHeight; row++) {
            distTo[row][0] = thou;
        }
        for (int col = 0; col < picWidth - 1; col++) {
            for (int row = 0; row < picHeight; row++) {
                relaxH(row, col);
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
    /**.
     * private method for relax method
     *
     * @param      row     The row
     * @param      col     The col
     * @param      edgeTo  The edge to
     * @param      distTo  The distance to
     */
    private void relaxH(final int row, final int col) {
        int nextCol = col + 1;
        for (int i = -1; i <= 1; i++) {
            int nextRow = row + i;
            if (nextRow < 0 || nextRow >= picHeight) continue;
            if (i == 0) {
                if (distTo[nextRow][nextCol] >= distTo[row][col] 
                    + energy(nextCol, nextRow)) {
                    distTo[nextRow][nextCol] = distTo[row][col] 
                + energy(nextCol, nextRow);
                    edgeTo[nextRow][nextCol] = i;
                }
            }
            if (distTo[nextRow][nextCol] > distTo[row][col] 
                + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[row][col] 
            + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
        }
    }
    /**.
     * Method to find the vertical seam
     * Complexity O( width * height)
     *
     * @return sequence of indices for vertical seam.
     */
    public int[] findVerticalSeam() {
        double[][] energy = new double[picHeight][picWidth];
        int[][] edgeTo = new int[picHeight][picWidth];
        double[][] distTo = new double[picHeight][picWidth];
        initialise(distTo);
        int[] indices = new int[picHeight];
        if (picWidth == 1 || picHeight == 1) {
            return indices;
        }
        final double thou = 1000.0;
        for (int i = 0; i < picWidth; i++) {
            distTo[0][i] = thou;
        }
        // this is for relaxation.
        for (int i = 0; i < picHeight - 1; i++) {
            for (int j = 0; j < picWidth; j++) {
                relaxV(i, j);
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
        for (int row = picHeight - 1, col = minCol; row >= 0; row--) {
            indices[row] = col;
            col -= edgeTo[row][col];
        }
        indices[0] = indices[1];
        return indices;
    }
    /**.
     * method to initialise the disto array
     * complexity o(width * height )
     *
     * @param      distTo  The distance to
     */
    private void initialise(final double[][] distTo) {
        for (int i = 0; i < distTo.length; i++) {
            for (int j = 0; j < distTo[i].length; j++) {
                distTo[i][j] = Double.MAX_VALUE;
            }
        }
    }
    /**.
     * ralx method for vertical seam
     *
     * @param      row     The row of type int
     * @param      col     The col of type int
     * @param      edgeTo  The edge to of type int array
     * @param      distTo  The distance to of type double array
     */
    private void relaxV(final int row, final int col) {
        int nextRow = row + 1;
        for (int i = -1; i <= 1; i++) {
            int nextCol = col + i;
            if (nextCol < 0 || nextCol >= picWidth) {
                continue;
            }
            if (i == 0) {
                if (distTo[nextRow][nextCol] >= distTo[row][col]
                    + energy(nextCol, nextRow)) {
                    distTo[nextRow][nextCol] = distTo[row][col]
                + energy(nextCol, nextRow);
                    edgeTo[nextRow][nextCol] = i;
                }
            }
            if (distTo[nextRow][nextCol] > distTo[row][col]
                + energy(nextCol, nextRow)) {
                distTo[nextRow][nextCol] = distTo[row][col]
            + energy(nextCol, nextRow);
                edgeTo[nextRow][nextCol] = i;
            }
        }
    }
    /**.
     * method to remove the Horizontal seam
     * complexity o(width * height )
     *
     * @param      seam  The seam
     */
    public void removeHorizontalSeam(final int[] seam) {
        //handle exceptions
        for (int col = 0; col < picWidth; col++) {
            for (int row = seam[col]; row < picHeight - 1; row++) {
                this.picture.set(col, row, this.picture.get(col, row + 1));
            }
        }
        picHeight--;
    }
    /**.
     * methdo to remove the vertical seam
     * complexity o(width * height )
     *
     * @param      seam  The seam
     */
    public void removeVerticalSeam(final int[] seam) {
        for (int row = 0; row < picHeight; row++) {
            for (int col = seam[row]; col < picWidth - 1; col++) {
                this.picture.set(col, row, this.picture.get(col + 1, row));
            }
        }
        picWidth--;
    }
}