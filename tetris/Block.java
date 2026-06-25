import greenfoot.*;
import java.util.*;
public class Block extends Actor {
    private int fallDelay = 30; // How many frames between each drop
    private int timer = 0; //used for gravity
    private boolean isLocked = false; //to check if Block has landed
    private ArrayList<Integer> blockdimensions = new ArrayList<>();
    
    public Block() {
        // Create a simple green square for the block
        switch(Greenfoot.getRandomNumber(4))
         {
        case 0://standart block
        for(int i = 0; i<30;i++)
        {
        for(int z = 0; z<30;z++)
        {
        blockdimensions.add(1);
        }
        blockdimensions.add(2);
        }
        GreenfootImage img = new GreenfootImage(30, 30);
        img.setColor(Color.GREEN);
        img.fill();
        img.setColor(Color.BLACK); // Outline
        img.drawRect(0, 0, 29, 29);
        setImage(img);
        break;
    }
    
    }

    public void act() {//Act is the engine that is called every frame @ 60fps
        if (isLocked) return; // Stop executing if the block has landed
        
        handleMovement();
        applyGravity();
    }

    private void handleMovement() {

        if (Greenfoot.isKeyDown("left") && timer % 5 == 0) {
            if (getX() > 0 && !isTouchingBlock(-1, 0)) {
                setLocation(getX() - 1, getY());
            }
        }
        if (Greenfoot.isKeyDown("right") && timer % 5 == 0) {
            if (getX() < TetrisWorld.WIDTH - 1 && !isTouchingBlock(1, 0)) {
                setLocation(getX() + 1, getY());
            }
        }
    }

    private void applyGravity() {//make the block actually fall
        timer++;
        if (timer >= fallDelay) {
            // Check if we hit the floor or another block
            if (getY() < TetrisWorld.HEIGHT - 1 && !isTouchingBlock(0, 1)) {
                setLocation(getX(), getY() + 1);
            } else {
                lockInPlace();
            }
            timer = 0;
        }
    }
    
    // Checks if there is another block in the direction the player wants to move
    private boolean isTouchingBlock(int xOffset, int yOffset) {
        Actor below = getOneObjectAtOffset(xOffset, yOffset, Block.class);
        return below != null;
    }

    private void lockInPlace() {
        isLocked = true;
        TetrisWorld world = (TetrisWorld) getWorld();
        world.spawnPiece(); // Spawn the next piece
    }
}

