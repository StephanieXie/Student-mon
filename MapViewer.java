import java.awt.*;
import java.io.File;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 *  Class that draws the map
 *
 *  @author Stephanie Xie, Tinli Yarrington, Rachel Utz, Sabrina Sayasith
 *  @version 07 February 2016 (Hackathon 2016)
 */

public class MapViewer extends JComponent {
	/** Map */
	private MapGrid mapGrid;
	
	/** Player */
	private Player player;
	
	/** Magnification */
	private int magnify;
	
	/** Offset of map */
	private int x, y;
	
	/** Window size */
	private static int s;
	
	/** Constructor: creates a map of specified size with default color green. 
	 * @throws IOException */
	public MapViewer(MapGrid mGrid) throws IOException {
		super();
		mapGrid = mGrid;
		player = new Player(30, 0.25, new Point(200, 200));
		x = 0;
		y = 0;
		s = 800;
	}
	
	/** Gets magnification */
	public int getMagnify() {
		return magnify;
	}
	
	/** Sets magnification */
	public void setMagnify(int mag) {
		magnify = mag;
		repaint();
	}
	
	/** Gets player */
	public Player getPlayer() {
		return player;
	}
	
    /**
     *  Draws the magnified map in the graphics window
     *
     *  @param g The graphics object to draw into
     */
    public void paintComponent(Graphics g) {
    	// Set background color of map to beige and fill map
    	Color beige = new Color(245, 245, 220);
    	g.setColor(beige);
    	g.fillRect(0, 0, s, s);
    	
    	// Fill in individual pixels with a different color you want
    	for (int i = 0; i < mapGrid.getWidth(); i++) {
    		for (int j = 0; j < mapGrid.getHeight(); j++) {	
    			
    			// gets the MapGrid color to fill in the pixel i, j that's being drawn
    			g.setColor(mapGrid.getColor(i, j)); 
    			g.fillRect(x + i*magnify, y + j*magnify, magnify, magnify);
    		}
    	}
    	
    	// Create map - Science Quad, Smith College
    	try {
    	    //Background
    	    g.drawImage((ImageIO.read(new File("Asphalt.jpg"))),0,0,null);

    	    // Burton Lawn
    	    g.drawImage((ImageIO.read(new File("Grass.png"))),270,270,null);
            
    	     // Wright Hall
    	    g.drawImage((ImageIO.read(new File("Wright.jpg"))),400,100,null);

    	    // Burton
    	    g.drawImage((ImageIO.read(new File("Burton.jpg"))),120,300,null);

    	    // Sabin-reed
    	    g.drawImage((ImageIO.read(new File("Sabin-Reed.jpg"))),30,230,null);

    	    // McConnell
    	    g.drawImage((ImageIO.read(new File("McConnell.jpg"))),30,580,null);

    	    // Tyler
    	    g.drawImage((ImageIO.read(new File("Tyler.jpg"))),30,700,null);

    	    // Bass
    	    g.drawImage((ImageIO.read(new File("Bass.jpg"))),260,580,null);

    	    // Young library
    	    g.drawImage((ImageIO.read(new File("Young.jpeg"))),260,670,null);

    	    // Neilson Library
    	    g.drawImage((ImageIO.read(new File("Neilson.jpg"))),600,230,null);

    	    // Corner Store
    	    g.drawImage((ImageIO.read(new File("CornerStore.jpg"))),600,700,null);
    	    
    	    // Pond
    	    g.drawImage((ImageIO.read(new File("Paradise-Pond.jpg"))),0,0,null);
    	} catch(IOException e){
    	    System.out.println("You don't have the picture file");
    	}
            
	    // Import character   
    	try {
    	    g.drawImage(ImageIO.read(new File(player.getFile())), player.getLocX(), player.getLocY(), this);
    	} catch (IOException e) {
    	}
    }    
 
    /**
     *  @returns The minimum dimension
     */
    public Dimension getMinimumSize() {
	return new Dimension(s, s);
    }

    /**
     *  @returns The preferred dimension
     */
    public Dimension getPreferredSize() {
	return new Dimension(s, s);
    }
	
}