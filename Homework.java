import java.io.*;
import java.awt.*;
import java.util.Random;

/**
 * Creates homework class, which fights the Player class
 * 
 *  @author Stephanie Xie, Tinli Yarrington, Rachel Utz, Sabrina Sayasith
 *  @version 07 February 2016 (Hackathon 2016)
 */
public class Homework extends Character{
	/** Holds the name of the homework (ie. Statistics) */
	private String name;
	
	/** Constructor: calls to parent class, takes four parameters */
	public Homework(String name, double h, double d, Point loc) throws IOException{
		super(h, d, loc); 
		this.name = name;
	}
	
	/** Generates random number between 1 and 10, and returns true if value is 1,2,3, false if others */
	public boolean hasAppeared() {
		Random rand = new Random();
        int value = rand.nextInt(10) + 1; 
        
        if (value <= 3) {
        	return true;
        } else {
        	return false;
        }
	}
	
	/** Returns name of the homework */
	public String getName() {
		return name;
	}
	
	/** Sets the name of the homework */
	public void setName(String name) {
		this.name = name;
	}
}
	