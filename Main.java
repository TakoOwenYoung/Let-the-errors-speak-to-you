
import java.net.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import javafx.scene.image.*;

import java.io.*;

import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.net.*;
import javafx.geometry.*;

/*import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;*/


public class Main extends Application{
   FlowPane fp;
   FlowPane fpTwo;
   
   Canvas theCanvas = new Canvas(600,600);
   static int score=0;
   static int highscore=0;
   boolean collision=false;
   MineObject activeMine;                    //Declaration of members
   PlayerObject thePlayer;
   MineObject m;
   AnimationTimer ta;
   TestObject station;
   int cgridx;
   int cgridy;
   ArrayList<MineObject> mineArray;    //Array to hold the mines
   protected static int cycle=0;    //Cycle is used to make the player and mine disappear on collision
   Random rand=new Random();
   public void start(Stage stage)
   {
      
      thePlayer=new PlayerObject(300,300);
      station=new TestObject(300, 300);
      m=new MineObject(thePlayer.getX(),thePlayer.getY());
      mineArray=new ArrayList<MineObject>();
      ta = new AnimationHandler();
      Label pointsDisplay=new Label();
      HBox window=new HBox();
      window.setAlignment(Pos.TOP_CENTER);
      window.setBackground(new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, Insets.EMPTY)));
      window.getChildren().add(pointsDisplay);
      pointsDisplay.setTextFill(Color.WHITE);
      
      
      /*////////////////////////////////////////////////////////////
      String musicFile = "brakes.mp3";     // For example

      Media sound = new Media(new File(musicFile).toURI().toString());        //I tried to follow the instructions for adding a sound to my program but something did not get imported properly. 
      MediaPlayer mediaPlayer = new MediaPlayer(sound);
      mediaPlayer.play();
      ////////////////////////////////////////////////////////////*/
   
      fp = new FlowPane();
      fp.getChildren().add(window);
      fp.getChildren().add(theCanvas);
      
      gc = theCanvas.getGraphicsContext2D();             //Standard root pane
      drawBackground(300,300,gc);
     
      
      Scene scene = new Scene(fp, 600, 600);
      stage.setScene(scene);                             //Commands to show the stage
      stage.setTitle("Project :)");
      stage.show();
      
      //////////////////////////////////////////////////////////////
      fp.setOnKeyPressed(new KeyListenerDown());     //Add the keylistener to fp, and request focus. 
      fp.setOnKeyReleased(new KeyListenerReleased()); //Add keylistener for when a key is released. 
      fp.requestFocus();
      
      ta.start();    //Start the animation handler.
      
      

      //////////////////////////////////////////////////////////////
      
      
      
   }
   
   GraphicsContext gc;
   
   
   
   Image background = new Image("stars.png");
   Image overlay = new Image("starsoverlay.png");     //Similar to Gradius III <3 
   Random backgroundRand = new Random();
   
   
   //this piece of code doesn't need to be modified
   public void drawBackground(float playerx, float playery, GraphicsContext gc)
   {
	  //re-scale player position to make the background move slower. 
      playerx*=.1;
      playery*=.1;
   
	//figuring out the tile's position.
      float x = (playerx) / 400;
      float y = (playery) / 400;
      
      int xi = (int) x;
      int yi = (int) y;
      
	  //draw a certain amount of the tiled images
      for(int i=xi-3;i<xi+3;i++)
      {
         for(int j=yi-3;j<yi+3;j++)
         {
            gc.drawImage(background,-playerx+i*400,-playery+j*400);
         }
      }
      
	  //below repeats with an overlay image
      playerx*=2f;
      playery*=2f;
   
      x = (playerx) / 400;
      y = (playery) / 400;
      
      xi = (int) x;
      yi = (int) y;
      
      for(int i=xi-3;i<xi+3;i++)
      {
         for(int j=yi-3;j<yi+3;j++)
         {
            gc.drawImage(overlay,-playerx+i*400,-playery+j*400);
         }
      }
   }
   
   
   
   
   

   
   public class AnimationHandler extends AnimationTimer
   {
      public void handle(long currentTimeInNanoSeconds) 
      {
         gc.clearRect(0,0,600,600);  
         
         //USE THIS CALL ONCE YOU HAVE A PLAYER
         drawBackground(thePlayer.getX(),thePlayer.getY(),gc); 
         
	      //example calls of draw - this should be the player's call for draw
         if(!collision){
         thePlayer.draw(300,300,gc,true); //all other objects will use false in the parameter.
         }
         thePlayer.act();   //This method in drawable object is where all of the objects derive their functionality from. 
         
         score=(int)thePlayer.distance(station);               //How far the ship is from the space station determines the points awarded. 
         if(score>highscore){
            highscore=score;
         }
   
   
            for(int i=0; i<mineArray.size(); i++){
               if(!mineArray.get(i).equals(activeMine)){
            mineArray.get(i).advanceColor();    //If the mine in the mineArray is not the one that has collided with the ship,... 
            mineArray.get(i).draw(thePlayer.getX(), thePlayer.getY(), gc, false);      //...advance color is called and the color is interpolated. 
            }
         }
         for(int i=0; i<mineArray.size(); i++){
            if(mineArray.get(i).distance(thePlayer)<=15){
               activeMine=mineArray.get(i);
               collision=true;                        //If the ship collides with a mine, then cycle allows the animation handler to cycle through again,
               cycle++;                            //drawing the environment without the ship/mine pair. 
                  if(cycle>2){
                     ta.stop();
                     }
            }
         }

      
      
      if(thePlayer.inMotion()==true){   //This prevents mines from spawning when the player is at rest. 
      
      
      for(int i=0; i<4; i++){
         cgridx = ((int)thePlayer.getX())/100;
         cgridy = ((int)thePlayer.getY())/100;
         
         if(i==0){                //Determines the starting distance from the player and adds the mine using the add mine method
               cgridx-=4;
               cgridy-=5;
               addMine(cgridx, cgridy, true);   //The boolean represents the axis that the mines will be associated with
            }
            if(i==1){
               cgridx-=4;
               cgridy+=5;
               addMine(cgridx, cgridy, false);
              
            }
            if(i==2){
               cgridx-=4;
               cgridy+=5;
               addMine(cgridx, cgridy, true);
            }
            if(i==3){
               cgridx+=4;
               cgridy+=5;
               addMine(cgridx, cgridy, false);
            }
            }//end for loop
         }//end if(inMotion)
         try{
         String skip=new String("");
         Scanner scoreCheck=new Scanner(new File("ProjectFile.txt"));      //Get the current high score
         skip=scoreCheck.next();
         skip=scoreCheck.next();
         highscore=scoreCheck.nextInt();
         
         
         
         }
         catch(Exception fnfe){
               System.out.println("error");
         }
         gc.setFill(Color.TEAL);
         gc.fillText("Score is: "+score+"\nHighscore is: "+highscore, 15, 30);
         
      }
   }

//////////////////////////////////////////////////////////////////////////////

 public class KeyListenerDown implements EventHandler<KeyEvent>{
 
         public void handle(KeyEvent e){
         if(e.getCode()==KeyCode.W){            
            thePlayer.setUp(true);
            }
         if(e.getCode()==KeyCode.A){
            thePlayer.setLeft(true);
            }
         if(e.getCode()==KeyCode.S){                  //The keys will move the player.
            thePlayer.setDown(true);
            }
         if(e.getCode()==KeyCode.D){
            thePlayer.setRight(true);
            }
            
        }//end handle
 
        }//End keyListenerDown
   
   public class KeyListenerReleased implements EventHandler<KeyEvent>{
 
         public void handle(KeyEvent e){
         if(e.getCode()==KeyCode.W){            
            thePlayer.setUp(false);
            }
         if(e.getCode()==KeyCode.A){
            thePlayer.setLeft(false);
            }
         if(e.getCode()==KeyCode.S){                  //The keys will move the player.
            thePlayer.setDown(false);
            }
         if(e.getCode()==KeyCode.D){
            thePlayer.setRight(false);
            }            
        }//end handle
 
        }//End keyListenerDown

   public void addMine(int x, int y, boolean axis){
      for(int j=0; j<9; j++){                                        //This method adds the mines
         
         int randN=rand.nextInt(99)+1;
         
         if(randN<=2){
            if(axis){
               mineArray.add(new MineObject((x+j)*100+rand.nextInt(50), y*100+rand.nextInt(50)));             //Here the mines are randomly distributed through each j increment...
            }
            else{                                                                                           //...of the grid, on the x and y axes, respectively
               mineArray.add(new MineObject((x)*100+rand.nextInt(50), (y-j)*100+rand.nextInt(50)));
            }
         
         }
      }
   }//End addMine()
   public static void main(String[] args){
      launch(args);
      String skip=new String("");
      boolean exists=true;
      Scanner scan;
      Scanner read;                                         //Declare members, one scanner checks for a file, the other reads from it if it exists.
      File fileName=new File("ProjectFile.txt");;
      FileOutputStream fos;
      PrintWriter pw;
      
      //Checks to see if there is an existing project file
      
      try{
         scan=new Scanner(fileName);
         }
         catch(FileNotFoundException fnfe){
            System.out.println("There is no file to begin with");
            exists=false;
            }
      
      ///////////////////////Finish check
      
      
      try{
         if(exists==false){
         fos=new FileOutputStream("ProjectFile.txt", false);
         pw=new PrintWriter(fos);
            if(score>highscore){
               highscore=score;                                   //If there is no existing file, a new one is made.
               pw.println("Highscore is "+highscore);
            }
            pw.close();
            
               }//end if exists=false
               
         if(exists==true){                 //If there is a file already, then the scanner 'read' is used to obtain the existing high score value
            read=new Scanner(fileName);
            skip=read.next();
            skip=read.next();
            highscore=read.nextInt();
         
         
            if(score>highscore){
            fos=new FileOutputStream("ProjectFile.txt", false);      //If a new high score is attained, the file is made new, reflecting the current high score
            pw=new PrintWriter(fos);
            
               highscore=score;
               pw.println("Highscore is "+highscore);                            
            
            pw.close();
            
            }



         }//end if exists==true
               
            }//end try
        
         catch(FileNotFoundException fnfe){
               System.out.println("Can't find the file.");        //Just in case something happens to the file. 
         }
         
      }//end main method
   }