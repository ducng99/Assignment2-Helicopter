package helicopter;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

/**
 * This class contains draw function for the top blades of the helicopter
 * @author Tom
 *
 */
public class HeliTop {
	private GLU glu;
	public final Engine engine = new Engine();

	public HeliTop(GLU glu) {
		this.glu = glu;
	}

	public void draw(GL2 gl)
	{
		gl.glPushMatrix();

		gl.glColor3d(0.1, 0.1, 0.1);
		gl.glTranslated(0, 2, 0);
		gl.glRotated(-90, 1, 0, 0);
		
		// Blades holder thingy
		GLUquadric quadric = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(quadric, GLU.GLU_FILL);
		glu.gluCylinder(quadric, .4, .3, .6, 10, 15);
		glu.gluDeleteQuadric(quadric);
		
		gl.glPopMatrix();
		gl.glPushMatrix();
		
		// Blades
		
		gl.glTranslated(0, 2.3, 0);
		engine.spinBlades(gl);
		
		gl.glBegin(GL2.GL_QUADS);
		
		// 1st blade - bottom
		gl.glVertex3d(7, 0, 0.2);
		gl.glVertex3d(-7, 0, 0.2);
		gl.glVertex3d(-7, 0, -0.2);
		gl.glVertex3d(7, 0, -0.2);
		
		// 1st blade - top
		gl.glVertex3d(7, 0.1, 0.2);
		gl.glVertex3d(-7, 0.1, 0.2);
		gl.glVertex3d(-7, 0.1, -0.2);
		gl.glVertex3d(7, 0.1, -0.2);
		
		// 1st blade - long side 1
		gl.glVertex3d(7, 0, 0.2);
		gl.glVertex3d(-7, 0, 0.2);
		gl.glVertex3d(-7, 0.1, 0.2);
		gl.glVertex3d(7, 0.1, 0.2);
		
		// 1st blade - long side 2
		gl.glVertex3d(7, 0, -0.2);
		gl.glVertex3d(-7, 0, -0.2);
		gl.glVertex3d(-7, 0.1, -0.2);
		gl.glVertex3d(7, 0.1, -0.2);
		
		// 1st blade - short side 1
		gl.glVertex3d(7, 0, 0.2);
		gl.glVertex3d(7, 0.1, 0.2);
		gl.glVertex3d(7, 0.1, -0.2);
		gl.glVertex3d(7, 0, -0.2);
		
		// 1st blade - short side 2
		gl.glVertex3d(-7, 0, 0.2);
		gl.glVertex3d(-7, 0.1, 0.2);
		gl.glVertex3d(-7, 0.1, -0.2);
		gl.glVertex3d(-7, 0, -0.2);
		
		// 2nd blade - bottom
		gl.glVertex3d(0.2, 0, 7);
		gl.glVertex3d(-0.2, 0, 7);
		gl.glVertex3d(-0.2, 0, -7);
		gl.glVertex3d(0.2, 0, -7);

		// 2nd blade - top
		gl.glVertex3d(0.2, 0.1, 7);
		gl.glVertex3d(-0.2, 0.1, 7);
		gl.glVertex3d(-0.2, 0.1, -7);
		gl.glVertex3d(0.2, 0.1, -7);
		
		// 2nd blade - long side 1
		gl.glVertex3d(0.2, 0, 7);
		gl.glVertex3d(0.2, 0.1, 7);
		gl.glVertex3d(0.2, 0.1, -7);
		gl.glVertex3d(0.2, 0, -7);
		
		// 2nd blade - long side 2
		gl.glVertex3d(-0.2, 0, 7);
		gl.glVertex3d(-0.2, 0.1, 7);
		gl.glVertex3d(-0.2, 0.1, -7);
		gl.glVertex3d(-0.2, 0, -7);
		
		// 2nd blade - short side 1
		gl.glVertex3d(0.2, 0, 7);
		gl.glVertex3d(-0.2, 0, 7);
		gl.glVertex3d(-0.2, 0.1, 7);
		gl.glVertex3d(0.2, 0.1, 7);
		
		// 2nd blade - short side 2
		gl.glVertex3d(0.2, 0, -7);
		gl.glVertex3d(-0.2, 0, -7);
		gl.glVertex3d(-0.2, 0.1, -7);
		gl.glVertex3d(0.2, 0.1, -7);
		
		gl.glEnd();
		
		gl.glPopMatrix();
	}
}
