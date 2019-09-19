import processing.core.PApplet;
import processing.core.PConstants;

public class AnalogClock {
	PApplet processingTest;

	int cx, cy;
	float secondsRadius;
	float minutesRadius;
	float hoursRadius;
	float clockDiameter;
	AnalogClock(PApplet pt){
		processingTest = pt;
	}
	public void settings() {
		  processingTest.size(640, 360);
		}
	public void setupClock(int width, int height) {
		processingTest.stroke(255);
	  
	  int radius = PApplet.min(width, height) / 2;
	  secondsRadius = (float) (radius * 0.72);
	  minutesRadius = (float) (radius * 0.60);
	  hoursRadius = (float) (radius * 0.50);
	  clockDiameter = (float) (radius * 1.8);
	  
	  cx = width / 2 + 40;
	  cy = height / 2 + 25;
	}

	public void drawClock() {
		//processingTest.background(0);
	  
	  // Draw the clock background
		processingTest.fill(225, 225, 236);
		processingTest.noStroke();
		processingTest.ellipse(cx, cy, clockDiameter, clockDiameter);
	  
	  // Angles for sin() and cos() start at 3 o'clock;
	  // subtract HALF_PI to make them start at the top
	  float s = PApplet.map(PApplet.second(), 0, 60, 0, PConstants.TWO_PI) - PConstants.HALF_PI;
	  float m = PApplet.map(PApplet.minute() + PApplet.norm(PApplet.second(), 0, 60), 0, 60, 0, PConstants.TWO_PI) - PConstants.HALF_PI; 
	  float h = PApplet.map(PApplet.hour() + PApplet.norm(PApplet.minute(), 0, 60), 0, 24, 0, PConstants.TWO_PI * 2) - PConstants.HALF_PI;
	  
	  // Draw the hands of the clock
	  processingTest.stroke(0);
	  processingTest.strokeWeight(1);
	  processingTest.line(cx, cy, cx + PApplet.cos(s) * secondsRadius, cy + PApplet.sin(s) * secondsRadius);
	  processingTest.strokeWeight(2);
	  processingTest.line(cx, cy, cx + PApplet.cos(m) * minutesRadius, cy + PApplet.sin(m) * minutesRadius);
	  processingTest.strokeWeight(4);
	  processingTest.line(cx, cy, cx + PApplet.cos(h) * hoursRadius, cy + PApplet.sin(h) * hoursRadius);
	  
	  // Draw the minute ticks
	  processingTest.strokeWeight(1);
	  processingTest.beginShape(PConstants.POINTS);
	  for (int a = 0; a < 360; a+=6) {
	    float angle = PApplet.radians(a);
	    float x = cx + PApplet.cos(angle) * secondsRadius;
	    float y = cy + PApplet.sin(angle) * secondsRadius;
	    processingTest.vertex(x, y);
	  }
	  processingTest.endShape();
	}

}
