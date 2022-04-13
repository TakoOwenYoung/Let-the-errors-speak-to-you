import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Player extends GameObject
{//Player Class
   static boolean onGround=false;
   public Player(int x,int y,Color c)
   {//Player()
      super(); //HOW DO I SIMPLIFY THIS? 4/9/2022 11:41a
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
   
   public void left()
   {
       xCtr--;
   }
   
      public void right()
   {
      xCtr++;
   }
   
      public void up()
   {
      yCtr--;
   }
   
      public void down()
   {
      yCtr++;
   }
   
      public void jump()
   {
      yCtr-=7;   
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
           
      boolean temp=false;
      GameObject current;
      
      yCtr++;
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
     // System.out.println("Outer loop");
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current =goaY.get(j).get(i);
            System.out.println("this.getBottom() "+this.getBottom()+"=="+current.getTop()+"current.getTop()+");
            //System.out.println();
            //System.out.println();
            System.out.println("this.getRight() "+this.getRight()+">"+current.getLeft()+"+current.getLeft()");
            System.out.println(" this.getLeft() "+ this.getLeft()+"<"+current.getRight()+"current.getRight()");
            System.out.println("this.getTop() "+this.getTop()+"<"+current.getBottom()+"current.getBottom()");

             
            if(current!=null)
            {
               if(this.getBottom() == current.getTop() &&
                  this.getRight() > current.getLeft() &&
                  this.getLeft() < current.getRight() &&
                  this.getTop() < current.getBottom() )
               {
                  temp=true;
               }
               //else
               //{temp=false;}
               //onGround=temp;
            }
           
         }
      }//Outer J loop
      yCtr--;
      return temp;
      
   }//isOnGround
   
   public boolean move(ArrayList<ArrayList<GameObject>> goaY)
   {//move
      boolean canMove=true;
      GameObject current;
      
      //To the left
      
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current =goaY.get(j).get(i);
            if(current!=null)
            {
               if(this.getBottom()>=current.getTop() &&
               this.getTop()<current.getBottom() &&
               this.getLeft()==current.getRight())
      
               {
                  canMove=false;
               }
               
          
            }
         }
      }//Outer J loop
      
      
      
      //Is there anything to the right?
      
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
                  canMove=false;
               }
              
            }
         }
      }//Outer J loop
      return canMove;
   }//move
   
      

   public boolean collides(ArrayList<ArrayList<GameObject>> goaY) //Backup Parameter phrase--> //ArrayList<? extends ArrayList<?>>goaX 
   {//collides
      boolean temp=false;
      GameObject current;
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         for(int i=0; i<goaY.get(i).size(); i++)
         {
            current=goaY.get(j).get(i);
            if(goaY.get(j).get(i)!=null)
            {
               if(this.collides(goaY.get(j).get(i)))
               {
                  temp=true;
                  System.out.println("Collided");
               }
            }
         }
      }//Outer J loop
      
      return temp;
      
   }//collides
   
   

   
}//Player Class 



           


