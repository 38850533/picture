import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void onlyBlue()  // removes all red and green 
  //from every pixel, and replaces keeps the blue values
  {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels)
	  {
		  for (Pixel pixelObj : rowArray)
		  {
			  pixelObj.setRed(0);
			  pixelObj.setGreen(0);
		  }
	  }
  }
  
  public void negate(){ // subtracts the current value of each
	  //pixel from 255 and this creates an inverse color effect
	  Pixel[][] pixels = this.getPixels2D();
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int column = 0; column < pixels[0].length; column++)
		  {
			  pixels[row][column].setRed(255-pixels[row][column].getRed());
			  pixels[row][column].setBlue(255-pixels[row][column].getBlue());
			  pixels[row][column].setGreen(255-pixels[row][column].getGreen());
		  }
	  }
  }
  
  public void grayscale(){ // sets the red,green and blue to be equal at that pixel
	  //making a grayscale effect
	  Pixel[][] pixels = this.getPixels2D();
	  for (int row = 0; row < pixels.length; row++){
		  for (int column = 0; column < pixels[0].length; column++){
			  int average = (pixels[row][column].getRed()+pixels[row][column].getGreen()+pixels[row][column].getBlue())/3;
			  pixels[row][column].setRed(average);
			  pixels[row][column].setGreen(average);
			  pixels[row][column].setBlue(average);
		  }
	  } 
  }
  
  public void FixUnderwater(){
	  Pixel[][] pixels = this.getPixels2D();
	  for (int row = 0; row < pixels.length; row++){
		  for (int column = 0; column < pixels[0].length; column++){
			  if(pixels[row][column].getGreen() > pixels[row][column].getBlue()){
				  pixels[row][column].setBlue(pixels[row][column].getBlue()/3);
			  }
		  }
	  } 
  }
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
    } }
  }
  
  
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void mirrorHorizontal(){
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel;
	  Pixel bottomPixel;
	  for (int row = 0; row < pixels.length/2; row++){
		  for (int column = 0; column < pixels[0].length; column++){
			  topPixel = pixels[row][column];
			  bottomPixel = pixels[pixels.length-1-row][column];
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
  }
  
  public void mirrorHorizontalBottomToTop(){ // same as the other mirrors but started
	  // from the bottom now we're here
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel;
	  Pixel bottomPixel;
	  for (int row = 0; row < pixels.length/2; row++){
		  for (int column = 0; column < pixels[0].length; column++){
			  topPixel = pixels[row][column];
			  bottomPixel = pixels[pixels.length-1-row][column];
			  topPixel.setColor(bottomPixel.getColor());
		  }
	  }
  }
  
  public void mirrorDiagonal(){
	  Pixel[][] pixels = this.getPixels2D();
	  int height = pixels.length;
	  for (int i = 0; i < height; i++){
		  for (int j = 0; j < i; j++){
			  pixels[j][i].setColor(pixels[i][j].getColor());
		  }
	  }
  }
  
  public void mirrorArms(){
	  Pixel[][] pixels = this.getPixels2D();
	  for (int row = 163; row <= 194; row++){
		  for (int column = 0; column < pixels[0].length; column++){
			  pixels[224-(row-163)][column].setColor(pixels[row][column].getColor());
		  }
	  }
  }
  
  public void mirrorGull(){
	  Pixel[][] pixels = this.getPixels2D();
	  for (int row = 238; row <= 328; row++){
		  for (int column = 230; column <= 350; column++){
			  pixels[row][460 - column].setColor(pixels[row][column].getColor());
		  }
	  }
  }
  
  
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  
  public void copy(Picture fromPic, 
          int startRow, int startCol, int fromStartRow, 
          int fromEndRow, int fromStartCol, int fromEndColumn) // second copy method
{
Pixel fromPixel = null;
Pixel toPixel = null;
Pixel[][] toPixels = this.getPixels2D();
Pixel[][] fromPixels = fromPic.getPixels2D();
for (int fromRow = fromStartRow, toRow = startRow; 
  fromRow <= fromEndRow &&
  toRow <= (fromEndRow-fromStartRow) + startRow; 
  fromRow++, toRow++)
{
for (int fromCol = fromStartCol, toCol = startCol; 
    fromCol <= fromEndColumn &&
    toCol <= (fromEndColumn - fromStartCol) + startCol;  
    fromCol++, toCol++)
{
 fromPixel = fromPixels[fromRow][fromCol];
 toPixel = toPixels[toRow][toCol];
 toPixel.setColor(fromPixel.getColor());
}
}   
}

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  public void createNewCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0, 20, 50, 30, 40);
    this.copy(flower1, 70,0, 30, 35, 22, 29);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  public void myCollage(){
	  Picture beach = new Picture("beach.jpg");
	  Picture kitten = new Picture("kitten2.jpg");
	  Picture lion = new Picture("flower1.jpg");
	  kitten.onlyBlue();
	  beach.negate();
	  lion.mirrorDiagonal();
	  this.copy(beach, 500, 0);
	  this.copy(kitten, 100, 150);
	  this.copy(lion, 0, 0);
	  this.mirrorVertical();
  }
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
	  Pixel leftPixel = null;
	    Pixel rightPixel = null;
	    Pixel topPixel = null;
	    Pixel bottomPixel = null;
	    Pixel[][] pixels = this.getPixels2D();
	    Color rightColor = null;
	    Color bottomColor = null;
	    for (int row = 0; row < pixels.length-1; row++)
	    {
	      for (int col = 0; 
	           col < pixels[0].length-1; col++)
	      {
	        leftPixel = pixels[row][col];
	        rightPixel = pixels[row][col+1];
	        rightColor = rightPixel.getColor();
	        topPixel = pixels[row][col];
	        bottomPixel = pixels[row+1][col];
	        bottomColor = bottomPixel.getColor();        
	        if (leftPixel.colorDistance(rightColor) > edgeDist)
	          leftPixel.setColor(Color.BLACK);
	        else if (topPixel.colorDistance(bottomColor) > edgeDist)
	        	bottomPixel.setColor(Color.BLACK);
	        else
	          leftPixel.setColor(Color.WHITE);
	      }
	    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
