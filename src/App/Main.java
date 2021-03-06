package App;
import scene.Camera;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.LinkedBlockingQueue;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import helicopter.Helicopter;
import objects.*;

/**
 * Main class for the helicopter application
 * 
 * @author Duc Nguyen
 *
 */
public class Main implements GLEventListener, KeyListener {
	// Key pressed events queue
	private final LinkedBlockingQueue<KeyEvent> keyPressedEventsQ = new LinkedBlockingQueue<>();
	// Key released events queue
	private final LinkedBlockingQueue<KeyEvent> keyReleasedEventsQ = new LinkedBlockingQueue<>();
	private final GLU glu = new GLU();
	
	public static Camera camera;
	private Ground ground;
	private Origin origin;
	private Helicopter helicopter;

	public static void main(String[] args) {
		Frame frame = new Frame("Assignment 2 - Helicopter");
		GLCanvas canvas = new GLCanvas();
		Main app = new Main();
		canvas.addGLEventListener(app);
		canvas.addKeyListener(app);

		frame.add(canvas);
		frame.setSize(1280, 960);
		final FPSAnimator animator = new FPSAnimator(canvas, 60);
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// Run this on another thread than the AWT event queue to
				// make sure the call to Animator.stop() completes before
				// exiting
				new Thread(new Runnable() {

					@Override
					public void run() {
						animator.stop();
						System.exit(0);
					}
				}).start();
			}
		});
		// Center frame
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		animator.start();
        canvas.requestFocus();
		
		System.out.println("Key mapping:\n"
				+ "--------------------------------------------\n" 
				+ "UP/DOWN ARROWS: Increase or decrease altitude\n"
				+ "LEFT/RIGHT ARROWS: Turn left or right\n"
				+ "W/S: Move forward or backward\n"
				+ "A/D: Strafe left or right\n"
				+ "L: Change ground draw mode\n"
				+ "K: Toggle camera follow helicopter\n");
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		// Enable VSync
		gl.setSwapInterval(1);
		// Setup the drawing area and shading mode
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_COLOR_MATERIAL);
		gl.glClearColor(0.95f, 0.95f, 0.95f, 1.f);
		
		float ambientLight[] = { 0.2f, 0.2f, 0.2f, 1 }; // no ambient
		float diffuseLight[] = { 1, 1, 1, 1 }; // white light for diffuse
		float specularLight[] = { 1, 1, 1, 1 }; // white light for specular
		
        float position[] = {30.0f, 20.0f, 10.0f, 1.0f };
		
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambientLight, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuseLight, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, specularLight, 0);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position, 0);
        
        float globalAmbientLight[] = { 0.4f, 0.4f, 0.4f, 1 };

		// set the global ambient light level
		gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, globalAmbientLight, 0);
        
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_NORMALIZE);

		camera = new Camera();
		origin = new Origin();
		helicopter = new Helicopter(glu);
		ground = new Ground();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		camera.newWindowSize(width, height);
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		// Handle key pressed events in queue
		while (keyPressedEventsQ.size() > 0)
		{
			handlePressedKeyEvents();
		}
		
		// Handle key released events in queue
		while (keyReleasedEventsQ.size() > 0)
		{
			handleReleasedKeyEvents();
		}

		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		camera.draw(gl);
		ground.draw(gl);
		origin.draw(gl);
		helicopter.draw(gl);

		// Flush all drawing operations to the graphics card
		gl.glFlush();
	}
	
	private void handlePressedKeyEvents()
	{
		try
		{
			KeyEvent event = keyPressedEventsQ.take();
			
			switch(event.getKeyCode())
			{
				case KeyEvent.VK_L:
					// Change ground draw mode
					ground.toggleDrawMode();
					break;
				case KeyEvent.VK_W:
					helicopter.isForward = true;
					break;
				case KeyEvent.VK_S:
					helicopter.isBackward = true;
					break;
				case KeyEvent.VK_A:
					helicopter.isLeft = true;
					break;
				case KeyEvent.VK_D:
					helicopter.isRight = true;
					break;
				case KeyEvent.VK_UP:
					helicopter.isUp = true;
					break;
				case KeyEvent.VK_DOWN:
					helicopter.isDown = true;
					break;
				case KeyEvent.VK_LEFT:
					helicopter.isLookLeft = true;
					break;
				case KeyEvent.VK_RIGHT:
					helicopter.isLookRight = true;
					break;
				case KeyEvent.VK_K:
					helicopter.lockCamera = !helicopter.lockCamera;
					break;
				default:
					break;
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	private void handleReleasedKeyEvents()
	{
		try
		{
			KeyEvent event = keyReleasedEventsQ.take();
			
			switch(event.getKeyCode())
			{
				case KeyEvent.VK_W:
					helicopter.isForward = false;
					break;
				case KeyEvent.VK_S:
					helicopter.isBackward = false;
					break;
				case KeyEvent.VK_A:
					helicopter.isLeft = false;
					break;
				case KeyEvent.VK_D:
					helicopter.isRight = false;
					break;
				case KeyEvent.VK_UP:
					helicopter.isUp = false;
					break;
				case KeyEvent.VK_DOWN:
					helicopter.isDown = false;
					break;
				case KeyEvent.VK_LEFT:
					helicopter.isLookLeft = false;
					break;
				case KeyEvent.VK_RIGHT:
					helicopter.isLookRight = false;
					break;
				default:
					break;
			}
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		try {
			keyPressedEventsQ.put(arg0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		try {
			keyReleasedEventsQ.put(arg0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
