import greenfoot.*;

public class TetrisWorld extends World {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 20;
    public static final int CELL_SIZE = 30;

    // A grid tracking occupied cells: true = filled, false = empty
    public boolean[][] grid = new boolean[WIDTH][HEIGHT];

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
    
    
}
    
    public void spawnPiece() {
        addObject(new Block(), WIDTH / 2, 0);
    }

    // Call this to see if a specific absolute grid coordinate is already occupied
    public boolean isCellOccupied(int x, int y) {
        //quick fix to fix werid wall offset
        if (x < 3 || x >= WIDTH || y < 0 || y >= HEIGHT+1) {
           // System.out.println("Counted X:"+x+"& Y:"+y+" is OCCUPIED");
            return true; // Out of bounds counts as occupied (avoids memory misacess and out of sight problems)
        }
        return grid[x][y];
    }
    public boolean checkRow(int y)
    {
         System.out.println("Checking row:"+y);
        boolean check=true;
        for(int i = 0; i<WIDTH; i++)
        {
    if(isCellOccupied(i,y)==false) check = false;
        }
        return check;
    }
    public void deleteRow(int y)
    {
         System.out.println("Deleting row:"+y);
         for(int i = 0; i<WIDTH; i++)
        {
    unlockCell(i,y);
        }
    }
    public void unlockCell(int x, int y) {
        //avoid memory missacess
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            grid[x][y] = false;
        }
    }
    // Lock a specific coordinate into the background matrix
    public void lockCell(int x, int y) {
        //avoid memory missacess
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            grid[x][y] = true;
        }
    }
}