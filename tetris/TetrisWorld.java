import greenfoot.*;

public class TetrisWorld extends World {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;
    public static final int CELL_SIZE = 30;

    public TetrisWorld() {    
        // Create a 10x20 grid where each cell is 30x30 pixels
        super(WIDTH, HEIGHT, CELL_SIZE);
        
        // Draw a black background
        GreenfootImage bg = new GreenfootImage(WIDTH * CELL_SIZE, HEIGHT * CELL_SIZE);
        bg.setColor(Color.BLACK);
        bg.fill();
        setBackground(bg);
        
        // Spawn the first piece at the top center
        spawnPiece();
    }
    
    public void spawnPiece() {
        addObject(new Block(), WIDTH / 2, 0);
    }
}