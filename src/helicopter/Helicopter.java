package helicopter;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

import App.Main;
import utils.Vector;

/**
 * Helicopter main class
 * @author Duc Nguyen
 *
 */
public class Helicopter {	
	private final HeliBody body;
	private final HeliTop top;
	private final HeliTail tail;
	private final HeliBottom bottom;
	
	public Vector Position;
	public double direction;
	
	public boolean isLeft = false, isRight = false, isForward = false, isBackward = false, isUp = false, isDown = false, isLookLeft = false, isLookRight = false;
	public boolean lockCamera = true;
    
    public Helicopter(GLU glu) {
        Position = new Vector();
        direction = 0.0;
        
        body = new HeliBody();
    	top = new HeliTop(glu);
    	tail = new HeliTail(glu);
    	bottom = new HeliBottom(glu);
    }
    
    public void draw(GL2 gl) {
    	if (isLeft)
    		strafeLeft();
    	if (isRight)
    		strafeRight();
    	if (isForward)
    		goForward();
    	if (isBackward)
    		goBackward();
    	if (isUp)
    		goUp();
    	if (isDown)
    		goDown();
    	if (isLookLeft)
    		lookLeft();
    	if (isLookRight)
    		lookRight();
    	
    	gl.glTranslated(0, .42, 0);
        gl.glTranslated(Position.x, Position.y, Position.z);
        
        // -direction because I love clockwise rotation
        gl.glRotated(-direction, 0, 1, 0);

        body.draw(gl);
        top.draw(gl);
        tail.draw(gl);
        bottom.draw(gl);
        
        if (lockCamera)
    	{
        	Main.camera.setEyePos(getCameraPos());
        	Main.camera.setLookAt(Position);
    	}
    }

    /**
     * Calculate offsets on x-axis and z-axis based on direction and move left
     */
    private void strafeLeft()
    {
    	if (top.engine.engineStarted() && tail.engine.engineStarted())
    	{
    		double rad = Math.toRadians(direction);

	    	double zOffset = 0.1 * Math.sin(rad);
	    	double xOffset = 0.1 * Math.cos(rad);
	    	
	    	Position = Position.Offset(xOffset, 0, zOffset);
    	}
    }

    /**
     * Calculate offsets on x-axis and z-axis based on direction and move right
     */
    private void strafeRight()
    {
    	if (top.engine.engineStarted() && tail.engine.engineStarted())
    	{
	    	double rad = Math.toRadians(direction);
	
	    	double zOffset = -0.1 * Math.sin(rad);
	    	double xOffset = -0.1 * Math.cos(rad);
	    	
	    	Position = Position.Offset(xOffset, 0, zOffset);
    	}
    }
    
    /**
     * Calculate offsets on x-axis and z-axis based on direction and move forward
     */
    private void goForward()
    {
    	if (top.engine.engineStarted() && tail.engine.engineStarted())
    	{
	    	double rad = Math.toRadians(direction);
	
	    	double zOffset = 0.1 * Math.cos(rad);
	    	double xOffset = -0.1 * Math.sin(rad);
	    	
	    	Position = Position.Offset(xOffset, 0, zOffset);
    	}
    }
    
    /**
     * Calculate offsets on x-axis and z-axis based on direction and move backward
     */
    private void goBackward()
    {
    	if (top.engine.engineStarted() && tail.engine.engineStarted())
    	{
	    	double rad = Math.toRadians(direction);
	
	    	double zOffset = -0.1 * Math.cos(rad);
	    	double xOffset = 0.1 * Math.sin(rad);
	    	
	    	Position = Position.Offset(xOffset, 0, zOffset);
    	}
    }
    
    /**
     * Check whether blades spin speed have reached maxed and take off
     */
    private void goUp()
    {
    	if (!top.engine.engineStarted())
    	{
    		top.engine.startEngine();
    	}
    	
    	if (!tail.engine.engineStarted())
    	{
    		tail.engine.startEngine();
    	}
    	
    	if (top.engine.engineStarted() && tail.engine.engineStarted())
    	{
    		Position = Position.Offset(0, 0.1);
    	}
    }
    
    private void goDown()
    {
    	if (Position.y <= 0)
    	{
    		if (!top.engine.engineStopped())
    			top.engine.stopEngine();
    		
    		if (!tail.engine.engineStopped())
    			tail.engine.stopEngine();
    	}
    	
    	if (Position.y > 0)
    	{
    		Position = Position.Offset(0, -0.1);
    	}
    }
    
    private void lookLeft()
    {
    	if (top.engine.engineStarted() && tail.engine.engineStarted())
    	{
	    	direction--;
	    	
	    	if (direction < 0)
	    		direction += 360;
    	}
    }
    
    private void lookRight()
    {
    	if (top.engine.engineStarted() && tail.engine.engineStarted())
    	{
	    	direction++;
	    	
	    	if (direction >= 360)
	    		direction -= 360;
    	}
    }
    
    /**
     * Calculate camera position
     * @return {@link Vector} position
     */
    private Vector getCameraPos()
    {
    	double rad = Math.toRadians(direction);

    	double zOffset = -20 * Math.cos(rad);
    	double xOffset = 20 * Math.sin(rad);
    	
    	return Position.Offset(xOffset, 8, zOffset);
    }
}
