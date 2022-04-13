import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameObject 
{//GameObject
   public int xCtr, yCtr;
   public Color c;
   
   public GameObject()
   {
      /*x=12;
      y=12;
      */
      c=Color.WHITE;
      
   }
   public GameObject(int x,int y,Color c)
   {
      //this.x=x-12; //If user wants to put a square at a particular coordinate, then...hmm
      //this.y=y-12;
      if(x<12)
      xCtr=12;
      else if(x>787)
      xCtr=787;
      else
      xCtr=x+12;
      
      if(y<12)
      yCtr=12;
      else if(y>787)
      yCtr=787;
      else
      yCtr=y+12;
      this.c=c;
   }

   public int getX()
   {
      return xCtr;
   }
   public int getY()
   {
      return yCtr;
   }
   public Color getColor()
   {
      return c;
   }
   public void setX(int n)
   {
      if(n<12)
      xCtr=12;
      else if(n>787)
      xCtr=786;
      else
      xCtr=n+12;

      
   }
   public void setY(int n)
   {
      if(n<12)
      yCtr=12;
      else if(n>787)
      yCtr=786;
      else
      yCtr=n+12;
  }
  
   public void setColor(Color c)
   {
      this.c=c;
   }
   
   public void setTop(int newTop)
   {yCtr=newTop+12;}
   public void setBottom(int newBottom)
   {yCtr=newBottom-13;}
   public void setLeft(int newLeft)
   {xCtr=newLeft+12;}
   public void setRight(int newRight)
   {xCtr=newRight-13;}
   
   public int getTop()
   {return yCtr-12;}
   public int getBottom()
   {return yCtr+13;}
   public int getLeft()
   {return xCtr-12;}
   public int getRight()
   {return xCtr+13;}
   ////////////////////////////////////////TWO METHODS
   
   public boolean collides(GameObject go)
   {//collides
      int topthis=this.getY()-12;
      int bottomthis=this.getY()+13;
      int leftthis=this.getX()-12;
      int rightthis=this.getX()+13;
      int topother=go.getY()-12;
      int bottomother=go.getY()+13;
      int leftother=go.getX()-12;
      int rightother=go.getY()+13;
      
      if(Math.abs(this.getX()-go.getX())>2 || Math.abs(this.getY()-go.getY())>2) //Instructed to include this line but don't know why. 4/9/2022 11:07a
      {
         return false;
      }
      else
      {                                                        
         return true;// (!(bottomthis<topother)||(topthis>bottomother)||(leftthis>rightother)||(rightthis<leftother));
      }
   }//collides
   
   public boolean collidesOnTop(GameObject go)
   {//collidesOnTop
      boolean tempii=false;
      int topthis=this.getY()-12;
      int bottomthis=this.getY()+13;
      int leftthis=this.getX()-12;
      int rightthis=this.getX()+13;
      if(go!=null)
      {//if(go!=null)
         int topother=go.getY()-12;
         int bottomother=go.getY()+13;
         int leftother=go.getX()-12;
         int rightother=go.getY()+13;
         
         if(this.getX()==go.getX()&&this.getY()==go.getY()) //Instructed to include this line but don't know why. 4/9/2022 11:07a
         {
            tempii=false;
         }
         else
         {                                                        
            tempii=((topthis<bottomother)||(leftthis<rightother)||(rightthis>leftother));
         }
         
      }//if(go!=null)
      
      return tempii;
   }//collidesOnTop

   
   public void draw(Graphics g)
   {//draw
      g.setColor(this.getColor());
      g.drawRect(this.getX()-12,this.getY()-12,25,25);
      g.fillRect(this.getX()-12,this.getY()-12,25,25);
   }//draw
   
   public String toString()
   {
      String temp=new String("");
      temp=(" "+this.getX()+" "+this.getY()+" ");
      return temp;
   }
   
 
   
}//GameObject