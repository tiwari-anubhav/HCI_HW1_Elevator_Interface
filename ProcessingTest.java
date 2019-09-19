import guru.ttslib.TTS;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import controlP5.*;


public class ProcessingTest extends PApplet{
	ControlP5 cp5;
	AnalogClock ac;
	Textfield floorLabel;
	TTS tts = new TTS();
	public static String currFloor = "0";
	public static String firstButton = "Ground Floor";
	public static String firstButtonUp = "Reception";
	public static String secondButton = "Customer Care";
	public static String secondButtonUp = "Services";
	public static String thirdButton = "Marketing and Sales";
	public static String thirdButtonUp = "Administration";
	public static String fourthButton = "Acquistions";
	public static String fourthButtonUp = "Products";
	public static List<Integer> floorsSelected = new ArrayList<Integer>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("ProcessingTest");		
	}


    public void settings(){
        size(500,500);
    }

    public void setup(){
        //fill(120,50,240);
    	background(0x8989A5);
    	int buttonWidth = 40;
    	int buttonHeight = 40;
    	int buttonPosX;
    	int buttonPosY;
    	String buttonName;
    	String buttonName1;
    	cp5 = new ControlP5(this);
    	ac = new AnalogClock(this);
    	
    	PFont buttonFont = createFont("Segoe UI SemiLight Italic",18);
    	PFont buttonFont1 = createFont("Segoe UI SemiLight Italic",12);
    	PImage[] imgs = {loadImage("/resources/images/door-open.png"),loadImage("/resources/images/bell.png"),loadImage("/resources/images/door-close.png")};
    	PImage[] floorImgs = {loadImage("/resources/images/zero.jpg"),loadImage("/resources/images/one-1.png"),
    			loadImage("/resources/images/two.jpg")
    					};
    	PImage[] icons ={loadImage("/resources/images/arrow-up.png"),loadImage("/resources/images/arrow-down.png")};    	
    	//image(imgs[0],0,0);
    	buttonPosX = 80;
    	for (int i= 0; i<imgs.length;i++) {
    		imgs[i].resize(40,40);    		
    		buttonPosY = 450;
    		buttonName = "button" + Integer.toString(i);
    		createNewButton(cp5,buttonName,buttonPosX ,buttonPosY, 20, 20, buttonFont, imgs[i]);
      	 	buttonPosX = buttonPosX + 150;
    	}
    	buttonPosY = 350;
    	for (int i= 0; i<= floorImgs.length;i++) {
    		buttonName = Integer.toString(i);
    		buttonName1 = Integer.toString(i + 4);
    		buttonPosX = 250;
    		//floorImgs[i].resize(60,60);
    		newButton(cp5,buttonName,buttonPosX ,buttonPosY, buttonWidth, buttonHeight, buttonFont, floorImgs[0],icons);
    		newButton(cp5,buttonName1,buttonPosX ,buttonPosY, buttonWidth, buttonHeight, buttonFont, floorImgs[0],icons);
    		cp5.get(buttonName1).hide();
    		buttonPosY = buttonPosY - 70;
    	} 
    	
    	next(buttonFont1, "next", 60, 25, 350, 410);
    	next(buttonFont1, "previous", 80, 25, 100, 410);
    	cp5.get("previous").hide();


    }

    public void draw(){
    	background(0x8989A5);
    	ac.setupClock(60, 60);
    	ac.drawClock();
    	
    	PFont font1 = createFont("Segoe UI SemiLight Italic",15);
    	PFont font2 = createFont("Segoe UI SemiLight",12);
    	PFont font3 = createFont("Segoe UI SemiBold",15);
    	textFont(font1);
    	textAlign(CENTER);
    	text("1000 Kg",250,50);
    	text("13 Persons",250,70);
    	PFont font = createFont("/resources/fonts/digital-7 (italic).ttf",40);
    	textFont(font);
    	fill(255,255,255);
    	textSize(70);
    	text(ProcessingTest.currFloor,430,30,50,50);
    	
    	textFont(font2);
    	textAlign(CENTER);
    	text(ProcessingTest.firstButton,120,385);
    	stroke(255);
    	strokeWeight(1/2);
    	line(50,370,200,370);
    	text(ProcessingTest.firstButtonUp,120,365);
    	
    	textFont(font2);
    	textAlign(CENTER);
    	text(ProcessingTest.secondButton,120,315);
    	stroke(255);
    	strokeWeight(1/2);
    	line(50,300,200,300);
    	text(ProcessingTest.secondButtonUp,120,295);
    	
    	textFont(font2);
    	textAlign(CENTER);
    	text(ProcessingTest.thirdButton,120,245);
    	stroke(255);
    	strokeWeight(1/2);
    	line(50,230,200,230);
    	text(ProcessingTest.thirdButtonUp,120,225);
    	
    	textFont(font2);
    	textAlign(CENTER);
    	text(ProcessingTest.fourthButton,120,175);
    	stroke(255);
    	strokeWeight(1/2);
    	line(50,160,200,160);
    	text(ProcessingTest.fourthButtonUp,120,155);
    	
    	textFont(font3);
    	textAlign(CENTER);
    	text("Floor Queue",400,150);
    	stroke(225);
    	strokeWeight(1/2);
    	line(340,155,480,155);
    	
    	Collections.sort(ProcessingTest.floorsSelected);
    	PFont font4 = createFont("Segoe UI SemiBold",12);
    	textFont(font4);
    	int yPos = 200;
    	for(int i=0;i<ProcessingTest.floorsSelected.size();i++) {
    		pushMatrix();
    		text(Integer.toString(ProcessingTest.floorsSelected.get(i)),400,yPos);
    		yPos = yPos + 20;
    		popMatrix();
    	}
    	
    }
    public void newButton(ControlP5 cp,String name,int posX, int posY, int width, int height,PFont buttonFont, PImage image,PImage[] icons) {
    	cp.addButton(name).setPosition(posX,posY).setSize(width,height)
    	   .setFont(buttonFont).setCaptionLabel(name).setColorActive(221).setColorValue(0x8989A5).setColorForeground(color(137, 137, 165 ,200))
    	   .setColorBackground(color(91, 91, 95))
    	   .onClick(new CallbackListener() { // a callback function that will be called onPress
    		      public void controlEvent(CallbackEvent theEvent) {
    		    	  background(0x8989A5);
    		    	  theEvent.getController().isActive();
    		          String n = theEvent.getController().getCaptionLabel().getText();
    		          PFont font = createFont("arial",26);
    		      	  textFont(font);
    		      	  try {
    		      	  //floorLabel.remove();
    		      	  int nextFloor = Integer.parseInt(n);
    		      	  int currentFloor = Integer.parseInt(currFloor);
    		      	  //floorLabel.setText(Integer.toString(nextFloor));
    		      	  ProcessingTest.currFloor = Integer.toString(nextFloor);
    		      	  makeFloorsQueue(currentFloor,nextFloor);
    		      	  if (nextFloor != currentFloor) {
    		      	  tts.speak("You've Selected Floor " + Integer.toString(nextFloor) + ".Press again to cancel");
    		      		//java.awt.Toolkit.getDefaultToolkit().beep();
    		      	  //ProcessingTest.floorsSelected[0] =
    		      	  }
    		      	  setDirection(currentFloor,nextFloor,icons);
    		      	  //displayQueue(200);    		      	  
    		      	  }
    		      	  catch(Exception e) {
    		      		 System.out.println(e);
    		      	  }
    		        }
    		      });
    }
	/*
	 * public void mousePressed() { TTS tts = new TTS();
	 * //tts.speak("Please enter a number"); }
	 */
    public void createNewButton(ControlP5 cp,String name,int posX, int posY, int width, int height,PFont buttonFont, PImage image) {
    	cp.addButton(name).setPosition(posX,posY).setSize(width,height)
    	   .setFont(buttonFont).setImage(image).setColorActive(221).setColorValue(0x8989A5)
    	   .onClick(new CallbackListener() { // a callback function that will be called onPress
    		      public void controlEvent(CallbackEvent theEvent) {
    		    	  background(0x8989A5);
    		    	  //String n = theEvent.getController().getName();
    		          PFont font = createFont("arial",26);
    		      	  textFont(font);
    		      	  if(name.contains("button0")) {
    		      		  System.out.println(name);
    		      		  tts.speak("Opening Doors");
    		      	  }
    		      	  else if (name.contains("button1")) {
    		      		tts.speak("Please stay tuned while we send in help.");
    		      	  }
    		      	  else if (name.contains("button2")) {
    		      		tts.speak("Doors are closing");  
    		      	  }
    		      	  
    		        }
    		      });
    }
    public void setDirection(int cf, int nf,PImage[] icons) {
    	int posX = 480;
    	int posY = 40;
    	int width=10,height = 50;
    	try {
    	cp5.getController("arrow_down").remove();
    	}
    	catch(NullPointerException e) {
    		//System.out.println(ne)
    	}
    	try 
    	{
    		cp5.getController("arrow_up").remove();
    		
    	}
    	catch(NullPointerException ne) {
    			//System.out.println(ne)
    	}
    	if (cf > nf) {
    		icons[1].resize(20, 20);
    		cp5.addButton("arrow_down").setPosition(posX,posY).setSize(width,height).setImage(icons[1]);
    		cp5.getController(Integer.toString(nf)).setColorBackground(color(178, 200, 211 ));
        	
    	}
    	else if (cf < nf) {
    		icons[0].resize(20, 20);
    		cp5.addButton("arrow_up").setPosition(posX,posY).setSize(width,height).setImage(icons[0]);
    		cp5.getController(Integer.toString(nf)).setColorBackground(color(178, 200, 211 ));
        	
    	}
    	else if(cf == nf) {
    		cp5.getController(Integer.toString(nf)).setColorBackground(color(91, 91, 95));
    	}
    }
    
    public void next(PFont buttonFont,String label, int width, int height,int xPos, int yPos) {
    	cp5.addButton(label).setPosition(xPos,yPos).setSize(width,height)
  	   .setFont(buttonFont).setCaptionLabel(label).setColorActive(221).setColorValue(0x8989A5).setColorForeground(color(137, 137, 165 ,200)).setColorBackground(color(91, 91, 95))
  	   .onClick(new CallbackListener() {
  		    public void controlEvent(CallbackEvent next) {
  		    	PFont buttonFont = createFont("arial",18);
  			    	if (next.getController().getName() == "next") {
  			    		ProcessingTest.firstButton = "Fourth Floor";
  			    		ProcessingTest.firstButtonUp = "Laboratory";
  			    		ProcessingTest.secondButton = "Fifth Floor";
  			    		ProcessingTest.secondButtonUp = "R & D";
  			    		ProcessingTest.thirdButton = "Sixth Floor";
  			    		ProcessingTest.thirdButtonUp = "Accounting";
  			    		ProcessingTest.fourthButton = "Seventh Floor";
  			    		ProcessingTest.fourthButtonUp = "Human Resources";
  			    		toggleControllers("next",buttonFont);
  			    				    		
  			    	}
  			    	if (next.getController().getName() == "previous") {

  			    		cp5.get("0").show();
  			    		cp5.get("1").show();
  			    		cp5.get("2").show();
  			    		cp5.get("3").show();
  			    		ProcessingTest.firstButton = "Ground Floor";
  			    		ProcessingTest.firstButtonUp = "Reception";
  			    		ProcessingTest.secondButton = "Customer Care";
  			    		ProcessingTest.secondButtonUp = "Services";
  			    		ProcessingTest.thirdButton = "Marketing and Sales";
  			    		ProcessingTest.thirdButtonUp = "Administration";
  			    		ProcessingTest.fourthButton = "Acquistions";
  			    		ProcessingTest.fourthButtonUp = "Products";
  			    		toggleControllers("previous",buttonFont);
  			    		
  			    	}
  			    	
  		}
  		   
  	   });
    }
    public void toggleControllers(String controllerName,PFont buttonFont) {
    	if(controllerName == "next") {
    		
    		cp5.get("next").hide();
    		cp5.get("previous").show();
    		for(int i = 0; i<=3;i ++) {
    			cp5.get(Integer.toString(i)).hide();
    			cp5.get(Integer.toString(i+4)).show();
    		}
    		
	    		
    		redraw();
    	}
    	else if(controllerName == "previous") {
    		cp5.get("previous").hide();
    		cp5.get("next").show();
    		for(int i = 0; i<=3;i ++) {
    			cp5.get(Integer.toString(i+4)).hide();
    			cp5.get(Integer.toString(i)).show();
    		}
    		redraw();
		}
    }

    public void makeFloorsQueue(int cf, int nf) {
    	if (ProcessingTest.floorsSelected.contains(nf)) {
    		ProcessingTest.floorsSelected.remove(Integer.valueOf(nf));
    	}
    	else {
    		ProcessingTest.floorsSelected.add(nf);
    	}
    }
    
	/*
	 * public void displayQueue(int yPos) {
	 * Collections.sort(ProcessingTest.floorsSelected, Collections.reverseOrder());
	 * PFont font4 = createFont("Segoe UI SemiBold",12); textFont(font4); for(int
	 * i=0;i<ProcessingTest.floorsSelected.size();i++) {
	 * text(Integer.toString(ProcessingTest.floorsSelected.get(i)),400,yPos); yPos =
	 * yPos + 220; } redraw(); }
	 */
}
