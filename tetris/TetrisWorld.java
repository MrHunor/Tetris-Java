import greenfoot.*;

public class TetrisWorld extends World {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;
    public static final int CELL_SIZE = 30;

    // A grid tracking occupied cells: true = filled, false = empty
    private boolean[][] grid = new boolean[WIDTH][HEIGHT];

    public TetrisWorld() {    
        super(WIDTH, HEIGHT, CELL_SIZE);
        
        GreenfootImage bg = new GreenfootImage(WIDTH * CELL_SIZE, HEIGHT * CELL_SIZE);
        bg.setColor(Color.BLACK);
        bg.fill();
        setBackground(bg);
        
        spawnPiece();
    }
    
    public void lockBorders()
    {
    for()
    
}
    
    public void spawnPiece() {
        addObject(new Block(), WIDTH / 2, 0);
    }

    // Call this to see if a specific absolute grid coordinate is already occupied
    public boolean isCellOccupied(int x, int y) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {
            return true; // Out of bounds counts as occupied (avoids memory misacess)
        }
        return grid[x][y];
    }

    // Lock a specific coordinate into the background matrix
    public void lockCell(int x, int y) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            grid[x][y] = true;
        }
    }
}