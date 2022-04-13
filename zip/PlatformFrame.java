import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class PlatformFrame extends JFrame
{                                                                                                                        //PlatformFrame class

   public static ArrayList<ArrayList<GameObject>> staticGoaY;                                                                               
   Container contents;
   PlatformPanel pp;
   Player mushi;
   ArrayList<ArrayList<GameObject>> goaY=new ArrayList<ArrayList<GameObject>>();
   public PlatformFrame()
   {
      super("Actraiser");
      contents=getContentPane();
      pp=new PlatformPanel();
      pp.setPreferredSize(new Dimension(800,600));

      contents.add(pp);
      setBackground(Color.BLACK);
      setSize(800,650);
      setVisible(true);
   }

//////////////////////////////////////////PLATFORM FRAME(PLATFORM PANEL)///////////////////////////////////////////////////////////////////////////////////////      

public class PlatformPanel extends JPanel
   {                                                                                               //PlatformPanel class
      Scanner read;
      int startBlockX;
      int startBlockY;
      int numRows;
      int numColumns;
      ArrayList<ArrayList<GameObject>> goaY=new ArrayList<ArrayList<GameObject>>();


      public PlatformPanel()
      {                                                                                            //PlatformPanel()
         super();
         try
         {
         Timer t=new Timer(10, new TimeListenerB());
         t.start();                                                                                           //try
         Color currClr=new Color(0,0,0);
         
         addKeyListener(new KeyEventDemo());
         
         read=new Scanner(new File("tako.txt"));
         
         
         
         startBlockX=read.nextInt();
         // System.out.println("startBlockX= "+startBlockX);
         startBlockY=read.nextInt();
         // System.out.println("startBlockY= "+startBlockY);
         numRows=read.nextInt();
        //  System.out.println("numRows= "+numRows);
         numColumns=read.nextInt();
         // System.out.println("numColumns= "+numColumns);
         
         mushi=new Player(startBlockX,startBlockY,new Color(0,153,0));
         
                  //System.out.println("\ngoaY elements from Platform Frame:");
         for(int j=0; j<numRows; j++)
         {
               ArrayList<GameObject> goaX=new ArrayList<GameObject>();

               for(int i=0; i<numColumns; i++)
               {
                  
                  int num=read.nextInt();
                  if(num==1)
                  goaX.add(new GameObject((i*25),(j*25),Color.BLACK));
                  else if(num==2)
                  goaX.add(new GameObject((i*25),(j*25),Color.GREEN));
                  else if(num==0)
                  goaX.add(null);
               }               
                  goaY.add(goaX);    
            }
            System.out.println("goaY size up here: "+goaY.size());
            staticGoaY=goaY;
            for(int j=0; j<numRows; j++)
            {
               for(int i=0; i<numColumns; i++)
               {
                  //if(staticGoaY.get(i).get(j)!=null)
                  //System.out.print(staticGoaY.get(i).get(j));
               }
               System.out.println();
            }
         }                                                                                         //try
         catch(IndexOutOfBoundsException ioobe)
         {
            System.out.println(ioobe.getMessage());
         }
         catch(IOException ioe)
         {
            System.out.println("ioe thrown");
         }
         catch(NullPointerException npe)
         {
            System.out.println("Line 105 "+npe.getMessage());
         }
          /*catch(NoSuchElementException nse)
         {
            System.out.println(nse.getMessage());
         }
         */

         setBackground(Color.RED);
         setVisible(true);
         setSize(800,600);
         setFocusable(true);

      }                                                                                            //PlatformPanel()
////////////////////////////////////////END   PLATFORM FRAME(PLATFORM PANEL)//////////////////////////////////////////////////////////////



////////////////////////Begin TimeListenerB////////////////////////////////////////////////////////////////////////////////////////////////
   boolean up;
   boolean down;
   boolean left;
   boolean right;
   int xDirection=0;
   int yDirection=0;
   int grav=1;
   double acc=0.05;
   int count=0;
   public class TimeListenerB implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {  
         count++;
         
         try
         {
            if(up)
            {  
               if(count%20==0)
               mushi.jump();
            }
            if(down)
            {
               
            }
            if(left)
            {
               mushi.left();
               if(mushi.move(goaY)==false)
               mushi.right();
            }
            if(right)
            {
               mushi.right();
               if(mushi.move(goaY)==false)
               mushi.left();
            }
            
            
            //int deltaX= mushi.getX() + xDirection;
            //int deltaY= mushi.getX()+ yDirection;
            //mushi.setX(deltaX);
            //mushi.setY(deltaY);
            /*
            if(mushi.getY() > 587)
            {
               yDirection = -1;
            }
            if(mushi.getX() > 787)
            {
               xDirection = -1;
            }
            
            if(mushi.getY() < 12)
            {
               yDirection = 1;
            }
            if(mushi.getX() < 12)
            {
               xDirection = 1;
            }
            */
            //System.out.println("goaY size: "+goaY.size());
            for(int j=0; j<goaY.size(); j++)
            {
               for(int i=0; i<goaY.get(j).size(); i++)
               {
                  
                  //System.out.print("goaY ("+i+") ("+j+"): "+goaY.get(j).get(i)+" ");
               }
               
            }
            //System.out.println("isOnGround "+mushi.isOnGround(goaY));
            if(mushi.isOnGround(goaY)==false)
            mushi.setY(mushi.getY()+1);
            
            //if(mushi.isOnGround(goaY))
            //{
              // int n=(mushi.getY()+grav);
              // mushi.setY(n);
            //}

           repaint(); //calls the paint component
         }//try end
         catch(NullPointerException npe)
         {
            System.out.println("Mushi object is null");
         }
         
      }
      
   }
//}
////////////////////////End TimeListenerB////////////////////////////////////////////////////////////////////////////////////////////////
      
//******************************************************************PLATFORM FRAME(PAINT COMPONENT)******************************************************
      public void paintComponent(Graphics g)
      {                                                                                            //paintComponent
         super.paintComponent(g);
         //System.out.print(
         for(int j=0; j<numColumns; j++)
            {
               for(int i=0; i<numRows; i++)
               {
                  if(goaY.get(i).get(j)!=null)
                  goaY.get(i).get(j).draw(g);
               }
            }


         mushi.draw(g);
         
         //System.out.println("Mushi (Left side, Right side)=("+mushi.getLeft()+", "+mushi.getRight()+")");                 
      }                                                                                            //paintComponent
      //***********************************************************END  PLATFORM FRAME(PAINT COMPONENT)******************************************************
      
      
      //******************************************************************PLATFORM FRAME(KEY EVENT DEMO)******************************************************
      public class KeyEventDemo  implements KeyListener 
  {
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) 
    {
      if(e.getKeyCode() == KeyEvent.VK_W)
      {
         up=false;
      }
      
      if(e.getKeyCode() == KeyEvent.VK_S)
      {
         down=false;
      }
      if(e.getKeyCode() == KeyEvent.VK_A)
      {
         left=false;
      }
      if(e.getKeyCode() == KeyEvent.VK_D)
      {
         right=false;  
      }

    }
    public void keyPressed(KeyEvent e) 
    {
    
      if(e.getKeyCode() == KeyEvent.VK_W)
      {
         up=true;
      }
      if(e.getKeyCode() == KeyEvent.VK_S)
      {
         down=true;
      }
      if(e.getKeyCode() == KeyEvent.VK_A)
      { 
         left=true;  
      }
      if(e.getKeyCode() == KeyEvent.VK_D)
      {
         right=true;  
      }
      
      repaint(); //calls the paint component
    }
   }                                                                                               //KEY EVENT DEMO
//*********************************************************   END    PLATFORM FRAME(KEY EVENT DEMO)  ******************************************************
       

        
   }                                                                                               //PlatformPanel class
   
            public static void main(String[] args)
   {//Begin main
      PlatformFrame pf=new PlatformFrame();
      pf.setDefaultCloseOperation(EXIT_ON_CLOSE);
     /* 
            java.util.Timer time;
      time=new java.util.Timer();
      
         TimerTask task=new TimerTask() {
         @Override
         public void run() {

            if(pf.mushi.isOnGround(staticGoaY)==false)
            {
               int n=(pf.mushi.getY()+grav);
               pf.mushi.setY(n);
            }
            if(pf.mushi.isOnGround(staticGoaY)==true)
            {grav=1;}
            pf.repaint();
            }
               
               
            };
         

              
         TimerTask task2=new TimerTask() {
         @Override
         public void run() {
            while(grav<=7&&pf.mushi.isOnGround(staticGoaY))
            {
               //grav+=acc;
            }
               if(pf.mushi.isOnGround(staticGoaY)==true)
               //{grav=1;}
            }
               
               
            };
         
     
     time.scheduleAtFixedRate(task, 0, 10);
         time.scheduleAtFixedRate(task2, 10, 10);
         

    
     TimerTask task3=new TimerTask() {
         @Override
         public void run() {
            System.out.println("Mushi move= "+mushi.move(staticGoaY));  //REMEMBER STATICGOAY AND GOAY
            }
               
               
            };

          time.scheduleAtFixedRate(task3, 0, 1000);
      */
     }//end Main 
   
     
}                                                                                                  //PlatformFrame class









         /*if(tako.collides(mushi))
         {
         System.out.println("Collides");
         }
         tako.draw(g);
         System.out.println("startBlockX ="+startBlockX);
         System.out.println("startBlockY ="+startBlockY);
         System.out.println("Mushi color ="+mushi.getColor());
         System.out.println("Mushi x ="+mushi.getX());
         System.out.println("Mushi y ="+mushi.getY());
         */