import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class PlatformFrameTwo extends JFrame
{                                                                                                                        //PlatformFrameTwo class
   int count = 0;
   public static ArrayList<ArrayList<GameObject>> staticGoaY;                                                                               
   Container contents;
   PlatformPanel pp;
   Player mushi;
   GameObject target;
   ArrayList<ArrayList<GameObject>> goaY=new ArrayList<ArrayList<GameObject>>();
   int ArrayListSizeX;
   int ArrayListSizeY;
   int dimX = 800;
   int dimY = 600;
   public PlatformFrameTwo()
   {
      super("Actraiser");
      contents=getContentPane();
      pp=new PlatformPanel();
      pp.setPreferredSize(new Dimension(dimX, dimY));

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
                                                                                                   //try
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
                 { goaX.add(new GameObject((i*25),(j*25),Color.GREEN));
                  target =new GameObject((i*25),(j*25),Color.GREEN);
                 }
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
            t.start(); 
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

         ArrayListSizeY = goaY.size();
         ArrayListSizeX = goaY.get(0).size();
         setBackground(Color.RED);
         setVisible(true);
         setSize(800,600);
         setFocusable(true);

      }                                                                                            //PlatformPanel()
////////////////////////////////////////END   PLATFORM FRAME(PLATFORM PANEL)//////////////////////////////////////////////////////////////



///////////////////////Begin TimeListenerB////////////////////////////////////////////////////////////////////////////////////////////////
   boolean up;
   boolean down;
   boolean left;
   boolean right;
   int grav=1;
   double acc=0.05;
   int  count=0;
   public class TimeListenerB implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {  
         count++;
                    //System.out.println("can move down is  "+mushi.canMove(goaY)[3]);

         //try
         //{
            if(up&&mushi.canMove(goaY)[2])
            {  
               mushi.up();
               
                  if(mushi.canMove(goaY)[3])
                  {
                     mushi.jump();
                  if(count%20==0)
                     mushi.down(1);
                  }
                     
            }
            if(down&&mushi.canMove(goaY)[3])
            {
             mushi.down(1);  
            }
            if(left&&mushi.canMove(goaY)[0] )
            {
              mushi.left();
              
            }
            if(right&&mushi.canMove(goaY)[1])
            {
               mushi.right();

            }
            
                                                           
          //System.out.println("can move down is  "+mushi.canMove(goaY)[3]);
           // while(mushi.isOnGround(goaY)==true)
           // {
          //  mushi.setY(mushi.getY()+1);
           // }
            
            //if(mushi.canMove(goaY)[3])
            //{
              // int n=(mushi.getY()+grav);
              // mushi.setY(n);
            //}

           repaint(); //calls the paint component
        // }//try end
         //catch(NullPointerException npe)
        // {
            //System.out.println(npe.getMessage());
        // }
         
      }
      
   }
//}
///////////////////////End TimeListenerB////////////////////////////////////////////////////////////////////////////////////////////////
///*****************************************************************PLATFORM FRAME(PAINT COMPONENT)******************************************************
      public void paintComponent(Graphics g)
      {   
      count++;
                                                                                                  //paintComponent
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
         if(mushi.collides(target))
         {
            System.out.println("Mushi collided withh target");
         }
         //else if (mushi.getY()<500 && random.nextDouble() >.8) mushi.setY(this.random.mushi.getY()+1);

         if(mushi.getY() < 400 & count%5 == 0) mushi.setY(mushi.getY()-1);
         
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
       

        
   } 

   // public Map<Integer, Map<Integer,String>> getBoundaries(){
   //    Map<Integer,Map<Integer,String>> bounds = new HashMap<Integer, HashMap<Integer,String>();
   //    for (int row = 1; row < goaY.size()-1; row++){
   //       Map<Integer, String> rowMap = new HashMap<Integer,String>();
   //       for (int col = 0; col < goaY.get(0).size(); col++){
   //          int val = goaY.get(row).get(col).;

   //          if (val == 2){
   //             rowMap.put(col,"target");
   //          }
   //          else {
   //                if (val ==0){
   //                   if goaY.get(row-1).get(col) == 
   //                }
   //          }
   //          else 
   //             bounds.set(row).set(col).set("target");

   //       }
   //    }
   //    return bounds;

   // }
                                                                                                 //PlatformPanel class
   
            public static void main(String[] args)
   {//Begin main
      PlatformFrameTwo pf=new PlatformFrameTwo();
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
            System.out.println("Mushi canMove= "+mushi.canMove(staticGoaY));  //REMEMBER STATICGOAY AND GOAY
            }
               
               
            };

          time.scheduleAtFixedRate(task3, 0, 1000);
      */
     }//end Main 
   
     
}                                                                                                  //PlatformFrameTwo class









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