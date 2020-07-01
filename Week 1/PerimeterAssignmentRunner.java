import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int stat = 0;
        for (Point CurrPt : s.getPoints())
        {
        	stat++;
        }
        return stat;
    }

    public double getAverageLength(Shape s) {
        return getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0;
        Point currPt = s.getLastPoint();
        for(Point prevPt : s.getPoints())
        {
        	double currDist = prevPt.distance(currPt);
        	largestSide = currDist >= largestSide ? currDist : largestSide;
        	prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = 0;
        for(Point currPt : s.getPoints())
        {
        	double currx = currPt.getX();
        	largestX = currx >= largestX ? currx : largestX;
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
    	DirectoryResource dr = new DirectoryResource();
    	double largestPerimeter = 0;
    	for (File f : dr.selectedFiles()) {
    		FileResource fr = new FileResource(f);
    		Shape s = new Shape(fr);
    	    double length = getPerimeter(s);
    	    largestPerimeter = length >= largestPerimeter ? length : largestPerimeter;
    	}
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
    	double largestPerimeter = 0;
    	for (File f : dr.selectedFiles()) {
    		FileResource fr = new FileResource(f);
    		Shape s = new Shape(fr);
    	    double length = getPerimeter(s);
    	    if (length > largestPerimeter) {
                temp = f;
                largestPerimeter = length;
            }
    	}
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("Perimeter = " + length);
        System.out.println("Number of points = " + getNumPoints(s));
        System.out.println("Average length = " + getAverageLength(s));
        System.out.println("Longest side = " + getLargestSide(s));
        System.out.println("Largest X = " + getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println("Largest perimeter among multiple files: " + getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("File with largest perimeter among multiple files: " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
    	PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
    	
    	System.out.println("Question 1, please select one file:");
        System.out.println("\n Result:");
        pr.testPerimeter();
        
        System.out.println("\n Question 2, please select one file:");
        System.out.println("\n Result:");
        pr.testPerimeter();
        
        System.out.println("\n Question 3, please select one file:");
        System.out.println("\n Result:");
        pr.testPerimeter();
        
        System.out.println("\n Question 4, please select multiple files:");
        System.out.println("\n Result:");
        pr.testPerimeterMultipleFiles();
        
        System.out.println("\n Question 5, please select multiple files:");
        System.out.println("\n Result:");
        pr.testFileWithLargestPerimeter();
    }
}
