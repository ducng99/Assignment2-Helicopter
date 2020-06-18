package objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

public class Origin {
	private GLU glu = new GLU();

	public Origin() {
	}

	public void draw(GL2 gl)
	{
		gl.glColor3d(1, 1, 1);
		GLUquadric quadric = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(quadric, GLU.GLU_FILL);
		glu.gluSphere(quadric, 0.1, 10, 15);
		glu.gluDeleteQuadric(quadric);
		
		gl.glBegin(GL2.GL_LINES);
		// shows x-axis
		gl.glColor3d(1, 0, 0);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(1, 0, 0);
		
		// show y-axis
		gl.glColor3d(0, 1, 0);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(0, 1, 0);
		
		// show z-axis
		gl.glColor3d(0, 0, 1);
		gl.glVertex3d(0, 0, 0);
		gl.glVertex3d(0, 0, 1);
		gl.glEnd();
	}
}
