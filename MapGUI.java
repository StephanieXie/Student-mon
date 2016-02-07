import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*; 
import java.util.*;

/**
 *  Java Swing application
 *
 *  @author Stephanie Xie, Tinli Yarrington, Rachel Utz, Sabrina Sayasith
 *  @version 07 February 2016 (Hackathon 2016)
 */
public class MapGUI{
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
	
	/** Vap to be displayed */
	private static MapGrid map;
	
	/** Viewport of the map */
	private static MapViewer view;
	
	/** Array of homework types */
	public Homework[] homeworks = {new Homework("Statistics homework", 10, 0.15, new Point(0,0)), 
			new Homework("Physics homework", 20, 0.36, new Point(0,0)), 
			new Homework("Mathematics homework", 10, 0.15, new Point(0,0)), 
			new Homework("Geology homework", 15, 0.20, new Point(0,0)), 
			new Homework("Literature homework", 18, 0.25, new Point(0,0)), 
			new Homework("Foreign language homework", 15, 0.20, new Point(0,0)), 
			new Homework("Engineering homework", 20, 0.36, new Point(0,0)), 
			new Homework("Computer science homework", 18, 0.25, new Point(0,0)), 
			new Homework("Chemistry homework", 18, 0.30, new Point(0,0)), 
			new Homework("Biology homework", 18, 0.30, new Point(0,0)), 
			new Homework("Neuroscience homework", 18, 0.30, new Point(0,0))};;
	
	/** Array of food types */
	public String[] cornerStore = {"Orange Juice", "Peanut Butter and Jelly Sandwich", "Potato Chips", "French fries", "Chicken nuggets", "Mac and cheese", "Ice cream", "Hamburger", "Tacos" ,"Twizzlers"};
	
	/** Array of food values */
	public double[] foodValues = {15, 15, 8, 8, 15, 10, 8, 30, 10, 5};
	
	/** Constructor */
	public MapGUI() throws IOException {
		map = new MapGrid(400, 400);
        view = new MapViewer(map); 
	}
	
    /** This method is called by the application version. */
    public void createAndShowGUI() {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the windows.
        JFrame frame = new JFrame("Map");
        try { frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (Exception e) {}

        // Create and add the viewers:
        view.setMagnify(10); /** smallest = 1*/
        frame.getContentPane().add(view);
        
        // Add components
        createComponents(frame.getContentPane());

        // Display the window:
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     *  Application calls this to set up the GUI contents.
     *
     *  @param pane  The pane of the JFrame
     */
    public void createComponents(Container pane) {
        pane.setLayout(new FlowLayout());

        view.addKeyListener(new Keyboard());
        view.setFocusable(true);
       
        pane.add(view);
  
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(200,100));
    }
    
    /** Event handler for KeyLister */
    class Keyboard implements KeyListener {
    	boolean[] keys = new boolean[255];

    	@Override
    	public void keyPressed(KeyEvent e) {
    		keys[e.getKeyCode()] = true;
    		this.updatePlayer();
    	}

    	@Override
    	public void keyReleased(KeyEvent e) {
    		keys[e.getKeyCode()] = false;
    	}

    	@Override
    	public void keyTyped(KeyEvent e) {
    	}
    	
    	public void updatePlayer() {
    		// move up
    		Player player = view.getPlayer();
    		
    		if (keys[KeyEvent.VK_W]) {
    			view.getPlayer().moveY(-20);
    			player.setDirection("UP");
    		}
    		// move down
    		if (keys[KeyEvent.VK_S]) {
    			view.getPlayer().moveY(20);
    			player.setDirection("DOWN");
    		}
    		// move left
    		if (keys[KeyEvent.VK_A]) {
    			view.getPlayer().moveX(-20);
    			player.setDirection("LEFT");
    		}
    		// move right
    		if (keys[KeyEvent.VK_D]) {
    			view.getPlayer().moveX(20);
    			player.setDirection("RIGHT");
    		}
    		
    		view.repaint();
    		fight();
    	}
    }
    
    /** Checks if there will be a fight */
    public void fight() {
        Scanner scan = new Scanner(System.in);
        String response = "";
        //int answer = 100;
        
        Random rand = new Random();
        int value = rand.nextInt(homeworks.length); // random number between 0 and length of homeworks

        Player play = view.getPlayer();
        Homework home = homeworks[value];
        
	    Point here = new Point(play.getLocX(), play.getLocY());
	   	String currentLoc = play.findLocation(here);
	   	
	   	if ((currentLoc.equals("Young Science Library") || currentLoc.equals("Neilson Library")) && !response.equals("no") && !response.equals("yes") && home.hasAppeared()){
	   		System.out.println("Would you like to take a break here?");
	   		System.out.println("yes");
	   		System.out.println("no");
	   		response = scan.nextLine();
	   		if (response.equalsIgnoreCase("yes")){
	   			System.out.println("Your health has been restored!");
	   			play.returnHealthToNormal();
	   		}
	   	} else if (play.isAtLocation(currentLoc) && home.hasAppeared()){
	   		play.battle(home);
	   	}
    }

    /** 
     *  This is the entry point for the application version
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        final MapGUI GUI = new MapGUI();
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    GUI.createAndShowGUI();
		}
	    });
        
    }
} 