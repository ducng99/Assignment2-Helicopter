package helicopter;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

/**
 * This class contains draw function for the tail and tail blades of the helicopter
 * @author Duc Nguyen
 *
 */
public class HeliTail {
	private GLU glu;
	public final Engine engine = new Engine();

	public HeliTail(GLU glu) {
		this.glu = glu;
	}

	public void draw(GL2 gl)
	{
		GLUquadric quadric = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(quadric, GLU.GLU_FILL);
		
		gl.glPushMatrix();

		gl.glColor3d(0.2, 0.5, 0);
		gl.glTranslated(0, 1.5, -2);
		gl.glRotated(180, 0, 1, 0);
		
		// Tail
		glu.gluCylinder(quadric, .5, .2, 7, 20, 15);
		
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		
		gl.glColor3d(0.1, 0.1, 0.1);
		gl.glTranslated(0.2, 1.5, -8);
		gl.glRotated(90, 0, 1, 0);
		
		// Blade holder thing
		glu.gluCylinder(quadric, .15, .1, 0.3, 10, 15);
		
		gl.glPopMatrix();
		
		gl.glPushMatrix();
		gl.glTranslated(0.35, 1.5, -8);
		gl.glRotated(90, 0, 0, 1);
		
		engine.spinBlades(gl);
		
		gl.glBegin(GL2.GL_QUADS);
		
		// Blade - bottom
		gl.glVertex3d(0.8, 0, 0.04);
		gl.glVertex3d(-0.8, 0, 0.04);
		gl.glVertex3d(-0.8, 0, -0.04);
		gl.glVertex3d(0.8, 0, -0.04);
		
		// Blade - top
		gl.glVertex3d(0.8, 0.05, 0.04);
		gl.glVertex3d(-0.8, 0.05, 0.04);
		gl.glVertex3d(-0.8, 0.05, -0.04);
		gl.glVertex3d(0.8, 0.05, -0.04);
		
		// Blade - long side 1
		gl.glVertex3d(0.8, 0, 0.04);
		gl.glVertex3d(-0.8, 0, 0.04);
		gl.glVertex3d(-0.8, 0.05, 0.04);
		gl.glVertex3d(0.8, 0.05, 0.04);
		
		// Blade - long side 2
		gl.glVertex3d(0.8, 0, -0.04);
		gl.glVertex3d(-0.8, 0, -0.04);
		gl.glVertex3d(-0.8, 0.05, -0.04);
		gl.glVertex3d(0.8, 0.05, -0.04);
		
		// Blade - short side 1
		gl.glVertex3d(0.8, 0, 0.04);
		gl.glVertex3d(0.8, 0.05, 0.04);
		gl.glVertex3d(0.8, 0.05, -0.04);
		gl.glVertex3d(0.8, 0, -0.04);
		
		// Blade - short side 2
		gl.glVertex3d(-0.8, 0, 0.04);
		gl.glVertex3d(-0.8, 0.05, 0.04);
		gl.glVertex3d(-0.8, 0.05, -0.04);
		gl.glVertex3d(-0.8, 0, -0.04);
		
		gl.glEnd();
		
		glu.gluDeleteQuadric(quadric);
		
		gl.glPopMatrix();
	}
}
