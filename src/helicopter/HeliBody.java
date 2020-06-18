package helicopter;

import com.jogamp.opengl.GL2;

/**
 * This class contains draw function for the body of the helicopter
 * @author Duc Nguyen
 *
 */
public class HeliBody {

	public HeliBody() {
	}
	
	public void draw(GL2 gl) {
		gl.glPushMatrix();
		
		gl.glColor3d(0.2, 0.5, 0);

		gl.glBegin(GL2.GL_QUADS);
		
		// Top
		gl.glVertex3d(-2, 2, -2);
		gl.glVertex3d(-2, 2, 2);
		gl.glVertex3d(2, 2, 2);
		gl.glVertex3d(2, 2, -2);

		// Front
		gl.glColor4d(1, 1, 1, 0.75);
		
		gl.glVertex3d(2, 2, 2);
		gl.glVertex3d(-2, 2, 2);
		gl.glVertex3d(-1, 0.8, 4.5);
		gl.glVertex3d(1, 0.8, 4.5);

		gl.glColor4d(0.2, 0.5, 0, 1);
		// Head
		gl.glVertex3d(-1, 0.8, 4.5);
		gl.glVertex3d(1, 0.8, 4.5);
		gl.glVertex3d(1, 0, 5);
		gl.glVertex3d(-1, 0, 5);

		// Right-front side
		gl.glVertex3d(-2, 2, 2);
		gl.glVertex3d(-1, 0.8, 4.5);
		gl.glVertex3d(-1, 0, 5);
		gl.glVertex3d(-2, 0, 2);
		
		// Right-back side
		gl.glVertex3d(-2, 2, -2);
		gl.glVertex3d(-2, 2, 2);
		gl.glVertex3d(-2, 0, 2);
		gl.glVertex3d(-2, 0, -2);
		
		// Back side
		gl.glVertex3d(-2, 2, -2);
		gl.glVertex3d(2, 2, -2);
		gl.glVertex3d(2, 0, -2);
		gl.glVertex3d(-2, 0, -2);
		
		// Left-front side
		gl.glVertex3d(2, 2, 2);
		gl.glVertex3d(1, 0.8, 4.5);
		gl.glVertex3d(1, 0, 5);
		gl.glVertex3d(2, 0, 2);
		
		// Left-back side
		gl.glVertex3d(2, 2, -2);
		gl.glVertex3d(2, 2, 2);
		gl.glVertex3d(2, 0, 2);
		gl.glVertex3d(2, 0, -2);
		
		gl.glEnd();

		//Bottom side
		gl.glBegin(GL2.GL_POLYGON);
		
		gl.glVertex3d(-2, 0, -2);
		gl.glVertex3d(-2, 0, 2);
		gl.glVertex3d(-1, 0, 5);
		gl.glVertex3d(1, 0, 5);
		gl.glVertex3d(2, 0, 2);
		gl.glVertex3d(2, 0, -2);
		
		gl.glEnd();

        gl.glPopMatrix();
	}

}
