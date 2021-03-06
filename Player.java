import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Player extends GameObject
{//Player Class
   public boolean floor=false;
   public Player(int x,int y,Color c)
   {//Player()
      super(); //Ensure the player block is instantiated within the right range       
      if(x<12)
      xCtr=12;
      else
      xCtr=x+12;
      if(y<12)
      yCtr=12;
      else
      yCtr=y+12;
      this.c=c;

      
   }//Player()
   
   //Moves player object left
   public void left()
   {
       xCtr--;
   }
   //Moves player object right
      public void right()
   {
      xCtr++;
   }
   //Moves player object up
      public void up()
   {
      yCtr--;
   }
   
   //public void down()
   //{
     // yCtr++;
  // }   

      
      public void down(int n, ArrayList<ArrayList<GameObject>> goaY)
   {
      if(!this.collides(goaY))
      yCtr=yCtr+n;
   }   

   /*Gravity method
      public void gravity( int c)
   {
      
      int gravity=0;
     
         
         if(c%20==0)
        {
            gravity++;
        }
         yCtr+=gravity;
         System.out.println("Gravity is "+gravity);
      //}
      if(gravity>7)
      {
         gravity=7;
      }
   }   
   */
   
      public void jump()
   {
      yCtr-=7;   
   }
   
   public boolean getFloor()
   {
      return floor;
   }
  /*@Override
   public void draw(Graphics g)
   {//draw
      g.setColor(this.getColor());
      g.drawRect(this.getX()-12,this.getY()-12,25,25);
      g.fillRect(this.getX()-12,this.getY()-12,25,25);
   }//draw
   */
   
   public boolean isOnGround(ArrayList<ArrayList<GameObject>> goaY)  
   {//isOnGround
         //System.out.println("In the method on ground");
           
      floor=false;
      GameObject current;
      
      //yCtr++;
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
     // System.out.println("Outer loop");
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current =goaY.get(j).get(i);
            
             
            if(current!=null)
            {
               if(this.getBottom() == current.getTop() &&
                  this.getRight() > current.getLeft() &&
                  this.getLeft() < current.getRight() &&
                  this.getTop() < current.getBottom() )
               {
                  floor=true;
               }
               //else
               //{temp=false;}
               //onGround=temp;
            }
           
         }
      }//Outer J loop
      //yCtr--;
      return floor;
      
   }//isOnGround
   
   public boolean[] canMove(ArrayList<ArrayList<GameObject>> goaY)
   {//move
      // System.out.println("goaY.size()= "+goaY.size());
      
      boolean canMoveLeft=true;
      boolean canMoveRight=true;
      boolean canMoveUp=true;
      boolean canMoveDown =true;
      
      GameObject current;
      
      //To the left
      // xCtr++;
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current =goaY.get(j).get(i);
            if(current!=null)
            {
               if(this.getBottom()>current.getTop() &&
               this.getTop()<current.getBottom() &&
               this.getLeft()==current.getRight())
      
               {
                  canMoveLeft=false;
                  //System.out.println("Can'T MOVE LEfft");
               }
               
          
            }
         }
      }//Outer J loop
     //  xCtr++;
      
      
      //Is there anything to the right?
    // xCtr++;
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current =goaY.get(j).get(i);
            if(current!=null)
            {
               if(this.getBottom()>current.getTop() &&
               this.getTop()<current.getBottom() &&
               this.getRight()==current.getLeft())
      
               {
                  canMoveRight=false;
                  //System.out.println("can move right is "+canMoveRight);

               }
              
            }
         }
      }//Outer J loop
 
      
      
      //Is there anything above?
     
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current=goaY.get(j).get(i);
            if(current!=null)
            {
               if(this.getBottom()>current.getTop() &&
               this.getTop()==current.getBottom() &&
               this.getRight()>current.getLeft()&&
               this.getLeft()<current.getRight())
               {
               canMoveUp=false;
               }
              
            }
         }
      }//Outer J loop
    
       for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current =goaY.get(j).get(i);
            if(current!=null)
            {
               if(this.getBottom()==current.getTop() &&
               this.getLeft()<current.getRight() &&
               this.getRight()>current.getLeft())
      
               {
                  canMoveDown=false;
                  floor=true;
                             //System.out.println("can move down is  "+this.canMove(goaY)[3]);

               }
              
            }
         }
      }//Outer J loop
    
    
    //canMoveDown=actionSquare.isOnGround(goaY);
      return new boolean[]  {canMoveLeft,canMoveRight, canMoveUp,canMoveDown};
   
   
   }//move
   
      

   /*public boolean collides(ArrayList<ArrayList<GameObject>> goaY)
   {//collides
      boolean temp=false;
      GameObject current;
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current=goaY.get(j).get(i);
            if(goaY.get(j).get(i)!=null)
            {
               if(this.collides(current))
               {
                  temp=true;
                  System.out.println("Collided");
               }
            }
         }
      }//Outer J loop
      
return temp;
      
   }//collides
   */
   //This method determines if the player block collides with a victory block object 
   public boolean collidesVictory(ArrayList<ArrayList<GameObject>> goaY,VictoryBlock vb) //Backup Parameter phrase--> //ArrayList<? extends ArrayList<?>>goaX 
   {//collidesV
      
            
      boolean collidesOnLeft=false;
      boolean collidesOnRight=false;
      boolean collidesOnUp=false;
      boolean collidesOnDown =false;
      GameObject current;
      
      
      //To the left
      // xCtr++;
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current =goaY.get(j).get(i);
            if(current!=null&&current instanceof VictoryBlock)
            {
               if(this.getBottom()>current.getTop() &&
               this.getTop()<current.getBottom() &&
               this.getLeft()==current.getRight())
      
               {
                  collidesOnLeft=true;
                  System.out.println("Collides on left");
               }
               
          
            }
         }
      }//Outer J loop
     //  xCtr++;
      
      
      //Is there anything to the right?
    // xCtr++;
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current =goaY.get(j).get(i);
            if(current!=null&&current instanceof VictoryBlock)//Check to see if the current game object is not NULL and see if it's a victory block  
            {
               if(this.getBottom()>current.getTop() && //If the bottom of the player is greater , meaning lower, than the top of a game object then return true 
               this.getTop()<current.getBottom() && //If the top of the player object is higher then the bottom of the gameobject return true 
               this.getRight()==current.getLeft()) //If the right side of my player is equal to the left side of the current game object block then return true 
      
               {
                  collidesOnRight=true;
                  System.out.println("can move right is "+collidesOnRight);

               }
              
            }
         }
      }//Outer J loop
 
      
      
      //Is there anything above?
     
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current=goaY.get(j).get(i);
            if(current!=null&&current instanceof VictoryBlock)
            {
               if(this.getBottom()>current.getTop() &&
               this.getTop()==current.getBottom() &&
               this.getRight()>current.getLeft()&&
               this.getLeft()<current.getRight())
               {
               collidesOnUp=true;
               //System.out.println("can move up is  "+this.collidesVictory(goaY,vb));

               }
              
            }
         }
      }//Outer J loop
    
       for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current =goaY.get(j).get(i);
            if(current!=null&&current instanceof VictoryBlock)
            {
               if(Math.abs(this.getBottom()-current.getTop())<2 &&
               this.getLeft()<current.getRight() &&
               this.getRight()>current.getLeft())
      
               {
                  collidesOnDown=true;
                             //System.out.println("can move down is  "+this.collidesVictory(goaY, vb));

               }
              
            }
         }
      }//Outer J loop
    
    
    //collidesOnDown=actionSquare.isOnGround(goaY);
      return  (collidesOnLeft||collidesOnRight||collidesOnUp||collidesOnDown);
   
      
   }//collidesV
   
   

   
}//Player Class 



           


