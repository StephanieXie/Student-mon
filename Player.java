import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

/** 
 *  Main character
 * 
 *  @author Stephanie Xie, Tinli Yarrington, Rachel Utz, Sabrina Sayasith
 *  @version 07 February 2016 (Hackathon 2016)
 */
public class Player extends Character{
	/** X location for the player */
	private int xLoc;
	
	/** Y location for the player */
	private int yLoc; 
	
	/** Array holding foods the player is holding */
	private ArrayList food = new ArrayList<String>();
	
	/** Array that matches in index to the foods above, holds the amount of health the food at same index it restores */
	private ArrayList healthFood = new ArrayList<Double>(); 
	
	/** Procrastination bar - determines how many times the player can procrastinate */
    private int procrastionationBar;
    
    /** Direction player is facing */
    private String dir;
	
    /** Constructor */
	public Player(double h, double d, Point loc) throws IOException{
		super(h, d, loc);
		xLoc = (int) loc.getX(); 
		yLoc = (int) loc.getY();
		this.procrastionationBar = 5; 
		this.food.add("Chocolate bar"); 
		this.food.add("Coffee");
		this.food.add("Apple");
		this.healthFood.add(this.getHealth()/4);
		this.healthFood.add(this.getHealth()/2);
		this.healthFood.add(this.getHealth()*3/4);
		// sprite is initially facing down
				dir = "DOWN";
	}
	
	/** Returns the image of the new direction the player is facing */
	public String getFile() {
		if (dir.equals("UP")) {
			return "StephanieBack.png";
		} else if (dir.equals("DOWN")) {
			return "StephanieFront.png";
		} else if (dir.equals("LEFT")) {
			return "StephanieLeft.png";
		} else if (dir.equals("RIGHT")) {
			return "StephanieRight.png";
		} else {
			return null;
		}
	}
	
	/** Sets the direction and movement of the player */
	public void setDirection(String direction) {
		this.dir = direction;
	}
	
	/** Move player to new locationX */
	public int moveX(int x) {
		return xLoc += x;
	}
	
	/** Move player to new locationY */
	public int moveY(int y) {
		return yLoc += y;
	}
	
	/** Get x of current player position */
	public int getLocX() {
		return xLoc;
	}
	
	/** Get y of current player position */
	public int getLocY() {
		return yLoc;
	}
	
	/** Generates all the interactions between the homework and player */
	public void battle(Homework home) {
		Scanner input = new Scanner(System.in); // scanner for responses from user
		Scanner blank = new Scanner(System.in); // scanner for user to just hit ENTER
		String response = ""; // holds response for user
		String fill = ""; // just for blank enters
		
		System.out.println("You ended up with " + home.getName() + " at " + this.findLocation(new Point(this.xLoc, this.yLoc)) + "!");
		while((!response.equalsIgnoreCase("a") || !response.equalsIgnoreCase("b") || !response.equalsIgnoreCase("c") || !response.equalsIgnoreCase("d")) && this.getHealth() >0 && home.getHealth()>0) {
			System.out.println("What are you going to do?");
			System.out.println("a) Review your notes");
			System.out.println("b) Complete problem sets");
			System.out.println("c) Eat food");
			System.out.println("d) Procrastinate");
			response = input.nextLine();
			
				if (response.equalsIgnoreCase("a")) {
					this.setDamage(0.25);
					System.out.println("You did " + this.getDamageDone(home.getMaxHealth()) + " damage to the " + home.getName());
					System.out.println("<<<Hit Enter>>>");
					fill = blank.nextLine();
					home.setHealth(home.getHealth()-this.getDamageDone(home.getMaxHealth()));
					System.out.println("The " + home.getName() + " has " + home.getHealth() + " out of " + home.getMaxHealth() + " energy left.");
					System.out.println("<<<Hit Enter>>>");
					fill = blank.nextLine();
					
				} else if (response.equalsIgnoreCase("b")) {
					this.setDamage(0.35);
					System.out.println("You did " + this.getDamageDone(home.getMaxHealth()) + " damage to the " + home.getName());
					System.out.println("<<<Hit Enter>>>");
					fill = blank.nextLine();
					home.setHealth(home.getHealth()-this.getDamageDone(home.getMaxHealth()));
					System.out.println("The " + home.getName() + " has " + home.getHealth() + " out of " + home.getMaxHealth() + " energy left.");
					System.out.println("<<<Hit Enter>>>");
					fill = blank.nextLine();
					
				} else if (response.equalsIgnoreCase("c")) {
					System.out.println("Which food would you like to eat?");
					for(int i=0;i<food.size();i++) {
					    System.out.println(i + (") ") + food.get(i));
					}
					
					int number = 100; // to represent index of food to be consumed
					while (number>food.size() || number<0) {
						Scanner reader= new Scanner(System.in);
						System.out.println("Enter the number of the food you'd like to eat: ");
						number = reader.nextInt();
					}
					
					System.out.println("You selected " + food.get(number));
					System.out.println("<<<Hit Enter>>>");
					fill = blank.nextLine();
					food.remove(number);
					healthFood.remove(number);
					double newHealth = this.getHealth() + (double)(healthFood.get(number));
					
					if (newHealth >= this.getMaxHealth()) {
						this.returnHealthToNormal();
					} else {
						this.setHealth(newHealth);
					}
					
					System.out.println("Your energy is now up to " + this.getHealth() + " out of " + this.getMaxHealth() + ".");
					System.out.println("<<<Hit Enter>>>");
					fill = blank.nextLine();
					
				} else if (response.equalsIgnoreCase("d")) {
					if (this.procrastionationBar == 0) {
						System.out.println("You can't run away anymore!");
					} else {
						this.setProcrastionationBar(this.procrastionationBar-1);
						System.out.println("You can procrastionate " + procrastionationBar + " more times.");
					}
					break;  
				}

				if (home.getHealth()>0 && !response.equals("d")) {
					System.out.println("The " + home.getName() + " throws more work at you!");
					this.setHealth(this.getHealth()-home.getDamageDone(this.getMaxHealth()));
					System.out.println("You now have " + this.getHealth() + " out of " + this.getMaxHealth() + " energy left.");
					System.out.println("<<<Hit Enter>>>");
					fill = blank.nextLine();
					System.out.println();
				}
		}
		
		if (!response.equals("d")) {
			if (this.getHealth() <= 0) {
				System.out.println("You were beaten by the " + home.getName() + " homework!");
				this.setProcrastionationBar(0);
				System.out.println("You can't procrastionate anymore because you couldn't get your homework done!");
			} else {
				System.out.println("You successfully completed your " + home.getName() + " homework!");
				if (this.procrastionationBar == 5) {
					System.out.println("Great job!");
				} else {
					this.setProcrastionationBar(this.procrastionationBar + 1);
					System.out.println("You can now procrastionate an extra time!");
				}
			}
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("THIS IS END OF THE BATTLE");
		home.returnHealthToNormal();
	}
	
	/** Changes procrastination bar */
	public void setProcrastionationBar(int newP) {
		this.procrastionationBar = newP;
	}
}
