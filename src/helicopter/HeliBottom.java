package helicopter;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

/**
 * This class contains draw function for the bottom of the helicopter
 * @author Duc Nguyen
 *
 */
public class HeliBottom {
	private GLU glu;

	public HeliBottom(GLU glu) {
		this.glu = glu;
	}

	public void draw(GL2 gl)
	{
		GLUquadric quadric = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(quadric, GLU.GLU_FILL);
		
		gl.glPushMatrix();
		
		gl.glColor3d(.1, .1, .1);
		gl.glTranslated(-1.5, 0, 3);
		gl.glRotated(90, 1, 0, 0);
		glu.gluCylinder(quadric, .1, .1, .3, 10, 5);
		
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		
		gl.glColor3d(.1, .1, .1);
		gl.glTranslated(-1.5, 0, -1);
		gl.glRotated(90, 1, 0, 0);
		glu.gluCylinder(quadric, .1, .1, .3, 10, 5);
		
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		
		gl.glColor3d(.1, .1, .1);
		gl.glTranslated(1.5, 0, 3);
		gl.glRotated(90, 1, 0, 0);
		glu.gluCylinder(quadric, .1, .1, .3, 10, 5);
		
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		
		gl.glColor3d(.1, .1, .1);
		gl.glTranslated(1.5, 0, -1);
		gl.glRotated(90, 1, 0, 0);
		glu.gluCylinder(quadric, .1, .1, .3, 10, 5);
		
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		
		gl.glColor3d(.1, .1, .1);
		gl.glTranslated(1.5, -.3, -2.5);
		glu.gluCylinder(quadric, .12, .12, 7, 10, 5);
		
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		
		gl.glColor3d(.1, .1, .1);
		gl.glTranslated(-1.5, -.3, -2.5);
		glu.gluCylinder(quadric, .12, .12, 7, 10, 5);
		
		gl.glPopMatrix();
		
		glu.gluDeleteQuadric(quadric);
	}
}
