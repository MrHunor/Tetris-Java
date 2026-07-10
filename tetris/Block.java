import greenfoot.*;
import java.util.*;

public class Block extends Actor {
    private int fallDelay = 30; 
    private int timer = 0; 
    private boolean isLocked = false; 
    private ArrayList<int[]> blockDimensions = new ArrayList<>();

    public Block() {
        int shapeType = Greenfoot.getRandomNumber(3); 
        
        switch(shapeType) {
            //the pushbacks are the offsets of the original square of the actor 0x0
            case 0: // Square Shape
                blockDimensions.add(new int[]{0, 0});
                blockDimensions.add(new int[]{1, 0});
                blockDimensions.add(new int[]{0, 1});
                blockDimensions.add(new int[]{1, 1});
                break;
            case 1: // Straight Line
                blockDimensions.add(new int[]{0, 0});
                blockDimensions.add(new int[]{0, 1});
                blockDimensions.add(new int[]{0, 2});
                blockDimensions.add(new int[]{0, 3});
                break;
            case 2: // L-Shape
                blockDimensions.add(new int[]{0, 0});
                blockDimensions.add(new int[]{0, 1});
                blockDimensions.add(new int[]{0, 2});
                blockDimensions.add(new int[]{1, 2});
                break;
        }
        createPlaceholderImage();
    }

    public void act() {//def game loop
        if (isLocked) return; 
        System.out.println("Block at X:"+getX()+"\nY:"+getY());
        handleMovement();
        applyGravity();
             TetrisWorld world = (TetrisWorld) getWorld();
        for(int i = 0; i<20;i++)
        {
            if(world.checkRow(i)==true)
            {
                world.deleteRow(i);
            }
        }
        //DO NOT CHANGE THESE VALUES OTHERSWISE ACESS OUT OF BOUNDS
        for(int i=0; i<world.grid.length; i++)
        {
           for(int z =0; z <world.grid[i].length; z++)
           {
            if(world.grid[i][z]==false)System.out.print("0");
            else System.out.print("1");
          }
          System.out.println();
        }
    }

    private void handleMovement() {
        if (Greenfoot.isKeyDown("left") && timer % 5 == 0) {
            if (isValidPosition(getX() - 1, getY())) {
                setLocation(getX() - 1, getY());
            }
        }
        if (Greenfoot.isKeyDown("right") && timer % 5 == 0) {
            if (isValidPosition(getX() + 1, getY())) {
                setLocation(getX() + 1, getY());
            }
        }
    }

    private void applyGravity() {
        timer++;
        if (timer >= fallDelay) {
            if (isValidPosition(getX(), getY() + 1)) {
                setLocation(getX(), getY() + 1);
            } else {
                lockInPlace();
            }
            timer = 0;
        }
    }
    
    //
    private boolean isValidPosition(int newX, int newY) {
        TetrisWorld world = (TetrisWorld) getWorld();
        
        for (int[] offset : blockDimensions) {
            int targetX = newX + offset[0];
            int targetY = newY + offset[1];
            
            // Check wall boundaries
            if (targetX < 0 || targetX >= TetrisWorld.WIDTH || targetY >= TetrisWorld.HEIGHT) {
                return false;
            }
            
            // Check grid matrix collision
            if (world.isCellOccupied(targetX, targetY)) {
                return false;
            }
        }
        return true;
    }

    private void lockInPlace() {
        isLocked = true;
        TetrisWorld world = (TetrisWorld) getWorld();
        
        // Save each sub-block into the world's layout
        for (int[] offset : blockDimensions) {
            world.lockCell(getX() + offset[0], getY() + offset[1]);
        }
        
        world.spawnPiece(); 
    }

    private void createPlaceholderImage() {
        GreenfootImage img = new GreenfootImage(120, 120);
        img.setColor(Color.GREEN);
        for(int[] offset : blockDimensions) {
            int px = offset[0] * 30;
            int py = offset[1] * 30;
            img.fillRect(px, py, 28, 28);
        }
        setImage(img);
    }
}