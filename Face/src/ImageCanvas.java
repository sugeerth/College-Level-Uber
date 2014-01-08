import java.awt.*;
import java.awt.image.*;

/**
 * Canvas used to display any type of image. As long as its submitted
 * in byte[], int[] or double[] format with the appropiate
 * width and height. Also the image is converted into grayscale.
 *
 */
class ImageCanvas extends Canvas  {


    private Image memImage=null;      // image constructed from PPM data

    /**
     * Image displayed from the given array.
     *
     * @param bytes the byte array with the each element being an 8bit RGB
     * tuple
     * @param width The width of the iamge
     * @param height The height of the image
     */
    public void readImage(byte[] bytes, int width, int height) {

	int pix[] = new int[width * height];
	int index = 0;
        int ofs = 0;

        for (index = 0; index < pix.length-2; index++) {
          pix[index] = 255 << 24 /*alpha*/ |
                    (int)(bytes[ofs] & 0xFF) << 16 /*R*/ |
                    (int)(bytes[ofs] & 0xFF) << 8 /*G*/ |
                    (int)(bytes[ofs] & 0xFF) /*B*/;
          ofs += 1;
        }

        memImage = createImage(new MemoryImageSource(width, height, pix, 0, width));
	repaint();
    }

    /**
     * Image displayed from the given array.
     *
     * @param doubles the double array with the each element being an 64bit RGB
     * tuple. The alpha color is reset to FF and only the 24bits (from left to
     * right of each element are displayed).
     *
     * @param width The width of the iamge
     * @param height The height of the image
     */

    public void readImage(double[] doubles,  int width, int height) {


	// construct Image from binary PPM color data.


        int w = width;
	int h = height;

	int pix[] = new int[w * h];
	int index = 0;
        int avg = 0;
        for (index = 0; index < pix.length-2; index++) {
          //avg = (int) ((doubles[index] + doubles[index+1] + doubles[index+2]) / 3);
          avg = (int)doubles[index];
          pix[index] = 255 << 24 |/* avg << 16 | avg << 8 |*/ avg;


        }

        memImage = createImage(new MemoryImageSource(width, height, pix, 0, width));
	repaint();
    }

    /**
     * Image displayed from the given array.
     *
     * @param int the int array with the each element being an 32bit RGB
     * tuple. No conversion done.
     *
     * @param width The width of the iamge
     * @param height The height of the image
     */

    public void readImage(int[] ints, int width, int height) {

        memImage = createImage(new MemoryImageSource(width, height, ints, 0, width));
	repaint();
    }

    /**
     * Paint on our given object the given image.
     *
     */
    public void paint(Graphics g) {
	Dimension d = getSize();      // get size of drawing area
	g.setColor(getBackground());  // clear drawing area
	g.fillRect(0, 0, d.width, d.height);
	g.setColor(getForeground());

	if (memImage != null) {
	    g.drawImage(memImage, 0, 0, this);
	}
    }
}
