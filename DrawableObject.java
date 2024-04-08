import javafx.scene.paint.*;
import javafx.scene.canvas.*;

public abstract class DrawableObject{
   
   public DrawableObject(float x, float y){
      this.x = x;
      this.y = y;
   }

   //positions
   private float x;
   private float y;
   private boolean moving=false;
   private float xForce=0;
   private float yForce=0;                   //Declaration of members 
   protected boolean up=false;
   protected boolean down=false;
   protected boolean left=false;
   protected boolean right=false;
   private boolean noKeyPressed;   
   public void setUp(boolean u){
      up=u;
   }
   
   public void setDown(boolean d){
      down=d;
   }
   
   public void setLeft(boolean l){
      left=l;
   }
   
   public void setRight(boolean r){    //Accessor and mutator methods
      right=r;
   }
   
   public boolean getUp(){
      return up;
   }
   
   public boolean getDown(){
      return down;
   }
   public boolean getLeft(){
      return left;
   }
   public boolean getRight(){
      return right;
   }
      
   public boolean getNoKeyPressed(){
      return noKeyPressed;
   }
   public void setNoKeyPressed(boolean press){
      noKeyPressed=press;
   }
   
   //takes the position of the player and calls draw me with appropriate positions
   public void draw(float playerx, float playery, GraphicsContext gc, boolean isPlayer)
   {
      //the 300,300 places the player at 300,300, if you want to change it you will have to modify it here
      
      if(isPlayer)
         drawMe(playerx,playery,gc);
      else                                                                                            //Draws objects relative to the player
         drawMe(-playerx+300+x,-playery+300+y,gc);                                           
   }
   
   //this method you implement for each object you want to draw. Act as if the thing you want to draw is at x,y.
   //NOTE: DO NOT CALL DRAWME YOURSELF. Let the the "draw" method do it for you. I take care of the math in that method for a reason.
   public abstract void drawMe(float x, float y, GraphicsContext gc);
   public void act(){
      
         //Updates player position
         x+=xForce;
         y+=yForce;
  
         ///////////////////////Up
         if(getUp()==true){
            yForceUp();        //If I press up the upward y force increases by 0.1 each tick
            }
         else if(getUp()==false){      //If I am not pressing up....
               if(noKeyPressed&&yForce<0.25&&yForce>-0.25){    //and I am not pressing anything else while -0.25<yForce<0.25, yForce becomes 0.
                  yForce=0;
               }
               else{
                  if(yForce<0)            //if the above criteria is not met, then the force is reduced accordingly
                  yForce+=0.025;
               }
            }//End else if(getUp()==false)
            
            
         ///////////////////////Down
         if(getDown()==true){
            yForceDown();
         }
         else if(getDown()==false){      //If I am not pressing down....
               if(noKeyPressed&&yForce<0.25&&yForce>-0.25){    //and I am not pressing anything else while -0.25<yForce<0.25, yForce becomes 0.
                  yForce=0;
               }
               else{
                  if(yForce>0)
                  yForce-=0.025;          //if the above criteria is not met, then the force is reduced accordingly
               }
            }
         ////////////////////////Left
         if(getLeft()==true){
            xForceDown();
         }
         else if(getLeft()==false&&xForce<0){      //If I am not pressing down....
                  xForce+=0.025;
               }
            

         ////////////////////////Right
         if(getRight()==true){
            xForceUp();
         }
         else if(getRight()==false&&xForce>0){      //If I am not pressing down....
                  xForce-=0.025;
               }
         
         
         if(getUp()==false&&getDown()==false&&getLeft()==false&&getRight()==false){
            noKeyPressed=true;
         }                                                                 //This determines if no key is pressed.
         else{
            noKeyPressed=false;
         }

         
         //Stops player if going slowly and no key is pressed
         if((Math.abs(xForce)<0.25&&noKeyPressed)){
              xForce=0;
         }
               
         if((Math.abs(yForce)<0.25&&noKeyPressed)){
               //System.out.println("Stops player if going slowly Y: "+(noKeyPressed&&yForce<0.25&&yForce>-0.25));
               yForce=0;
         }//end slow player stop
            
                
               //setXF(getXF()-(float)0.025);
               //setXF(getXF()+(float)0.025);
               //setYF(getYF()-(float)0.025);
               //setYF(getYF()+(float)0.025);
     
      }//end act
   
   public boolean inMotion(){
      if(xForce==0&&yForce==0){
         return false;
      }
      else{                      //This is here so mines do not spawn while player is still
         return true;
      }
   }

   
   public float getX(){return x;}
   public float getY(){return y;}
   public void setX(float x_){x = x_;}          //more accessor and mutator methods. 
   public void setY(float y_){y = y_;}
   
    public float getXF(){
      return xForce;
   }
   
   public float getYF(){
      return yForce;
   }  
   
   public void xForceUp(){
      if(!(xForce>5)){
         xForce+=(float)0.1;
      }
      
   }//end xForceUp
   public void xForceDown(){
      if(!(xForce<-5)){
         xForce-=(float)0.1;
      }                                            //These methods increment the x and y forces, but keep them from going out of bounds. 
   }//end xForceDown
   
   
   
   public void yForceUp(){
      if(!(yForce<-5)){
         yForce-=(float)0.1;
      }//end yForceUp
      
   }
   public void yForceDown(){
      if(!(yForce>5)){
         yForce+=(float)0.1;
         }
   }//end yForceDown
   
   
   
   
   
   public void setXF(float f){
      //if(!(Math.abs(xForce)>5))
       if((xForce<-5.1)){
         xForce=-5;
       }
       
       else if(xForce>5.1){
         xForce=5;
       }
       
       else{
       xForce=f;}
                                    //These are auxiliary methods for x and y force modifications. 
   }
   
   public void setYF(float f){
      //if(!(Math.abs(yForce)>5))
       if((yForce<-5.1)){
         yForce=-5;
       }
       
       else if(yForce>5.1){
         yForce=5;
       }
       
       else{
       yForce=f;}
       
   }
   
   
   public double distance(DrawableObject other)
   {
      return (Math.sqrt((other.x-x)*(other.x-x) +  (other.y-y)*(other.y-y)   ));       //This is the distance method. Thank you for including it. 
   }
}