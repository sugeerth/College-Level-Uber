
/**
 *
 * Copyright (c) 2002 by Konrad Rzeszutek
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * Interface for various extension image files.
 *
 * @author Konrad Rzeszutek
 * @version 1.0
 */

public interface xxxFile {
   /**
     * Get the height of the PPM image.
     *
     * @return the height of the image.
     */
  public int getHeight();

     /**
     * Get the width of the PPM image.
     *
     * @return  the width of the image.
     */
    public int getWidth();
      /**
     * Get the data as byte array. Data is of any type that
     * has been read from the file (usually 8bit RGB)
     *
     * @return  The data of the image.
     */
    public byte[] getBytes();
      /**
     * Get the data as double array. Data is of any type that
     * has been read from the file (usually 8bit RGB put into an 64bit double)
     *
     * @return  The data of the image.
     */
    public double[] getDouble();
}
