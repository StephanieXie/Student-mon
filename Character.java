import java.awt.*;
import java.io.*;
import javax.swing.*;

/**
 * Character class
 * 
 * @author Stephanie Xie, Tinli Yarrington, Rachel Utz, Sabrina Sayasith
 * @version 07 February 2016 (Hackathon 2016)
 */
public class Character extends JComponent {
	/** Maximum character health */
	private final double maxHealth;
	
	/** Character health */
	private double health;
	
	/** Damage done */
	private double damage;
	
	/** Character location */
	private Point location;
	
	/** Constructor */
	public Character(double h, double d, Point loc) throws IOException {
		this.maxHealth = h;
		this.damage = d;
		this.location = loc;
		this.health = h;
	}
	
	/** Get character health */
	public double getHealth() {
		return this.health;
	}
	
	/** Get maximum character health */
	public double getMaxHealth() {
		return this.maxHealth;
	}
	
	/** Get damage done */
	public double getDamageDone(double opposingHealth) {
		return (opposingHealth)*(this.damage);
	}
	
	/** Find location of character */
	public String findLocation(Point loc) {
		double x = loc.getX();
		double y = loc.getY();
		
		if (x >=400 && y >=100 && x<=680 && y<=180) {
			return "Wright Hall";
		} else if(x >=120 && y >=300 && x<=210 && y<=490) {
			return "Burton Hall";
		} else if(x >=30 && y >=230 && x<=120 && y<=490) {
			return "Sabin-Reed";
		} else if(x >=30 && y >=580 && x<=210 && y<=660) {
			return "McConnell";
		} else if(x >=30 && y >=700 && x<=210 && y<=770) {
			return "Tyler House";
		} else if(x >=260 && y >=580 && x<=520 && y<=670) {
			return "Bass";
		} else if(x >=260 && y >=670 && x<=390 && y<=750) {
			return "Young Science Library";
		} else if(x >=600 && y >=230 && x<=750 && y<=630) {
			return "Neilson Library";
		} else if (x>=600 && y>=700 && x<=780 && y<=790) {
			return "Corner Store";
		} else {
			return "";
		}
	}
	
	/** Check if character is at the location */
	public boolean isAtLocation(String str) {
		if (str == "") {
			return false;
		} else {
			return true;
		}
	}
	
	/** Set character location */
	public void setLocation(Point newLoc) {
		this.location = newLoc;
	}
	
	/** Set character health */
	public void setHealth(double newHealth) {
		this.health = newHealth;
	}
	
	/** Set damage dealt */
	public void setDamage(double newDamage) {
		this.damage = newDamage;
	}
	
	/** Restore full health */
	public void returnHealthToNormal() {
		this.health = this.maxHealth;
	}
}
