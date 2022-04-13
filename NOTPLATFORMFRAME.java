import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class NOTPLATFORMFRAME  extends JFrame
{                                                                                                  //NOTPLATFORMFRAME class
   Container contents;
   PlatformPanel pp;
   Player mushi;
   
   public NOTPLATFORMFRAME()
   {
      super("NOTPLATFORMFRAME");
      contents=getContentPane();
      pp=new PlatformPanel();
      pp.setPreferredSize(new Dimension(800,600));

      contents.add(pp);
      setBackground(Color.BLACK);
      setSize(800,600);
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
      java.util.Timer time;

      ArrayList<ArrayList<GameObject>> goaY;
      //KeyEventDemo ked=new KeyEventDemo();
      
      
      
      public PlatformPanel()
      {                                                                                            //PlatformPanel()
         super();
         try
         {
                                                                                                           //try
         Color currClr=new Color(0,0,0);
         goaY=new ArrayList<ArrayList<GameObject>>();
         //System.out.println(""+startBlockY);
         
         addKeyListener(new KeyEventDemo());
         
         read=new Scanner(new File("Stage1.txt"));
         
         
         
         startBlockX=read.nextInt();
         // System.out.println("startBlockX= "+startBlockX);
         startBlockY=read.nextInt();
         // System.out.println("startBlockY= "+startBlockY);
         numRows=read.nextInt();
        //  System.out.println("numRows= "+numRows);
         numColumns=read.nextInt();
         // System.out.println("numColumns= "+numColumns);
         
         mushi=new Player(startBlockX,startBlockY,new Color(0,153,0));
         
         time=new java.util.Timer();
         TimerTask task=new TimerTask() {
         @Override
         public void run() {
            if(mushi.isOnGround(goaY)==false)
            {mushi.down();}
               
               
            }
         };

         time.schedule(task, 10);
         
         for(int j=0; j<numRows; j++)
         {
               ArrayList<GameObject> goaX=new ArrayList<GameObject>();

               for(int i=0; i<numColumns; i++)
               {
                  int num=read.nextInt();
                  if(num==1)
                  goaX.add(new GameObject((i*25),(j*25),Color.GREEN));
                  else if(num==2)
                  goaX.add(new GameObject((i*25),(j*25),Color.RED));
                  else if(num==0)
                  goaX.add(null);               }
                  goaY.add(goaX);
                  System.out.print("\n");               
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

         setBackground(Color.BLUE);
         setVisible(true);
         setSize(800,600);
         setFocusable(true);

      }                                                                                            //PlatformPanel()
////////////////////////////////////////END   PLATFORM FRAME(PLATFORM PANEL)//////////////////////////////////////////////////////////////
      public int getgoaYSize()
      {
         return goaY.size();
      }
      public int getgoaXSize()
      {
         return goaY.get(0).size();
      }
      
//******************************************************************PLATFORM FRAME(PAINT COMPONENT)******************************************************
      public void paintComponent(Graphics g)
      {                                                                                            //paintComponent
         super.paintComponent(g);
         
         for(int j=0; j<numColumns; j++)
            {
               for(int i=0; i<numRows; i++)
               {
                  if(goaY.get(i).get(j)!=null)
                  goaY.get(i).get(j).draw(g);
               }
            }
         /*if(tako.collides(mushi))
         {
         System.out.println("Collides");
         }
         tako.draw(g);*/
         System.out.println("startBlockX ="+startBlockX);
         System.out.println("startBlockY ="+startBlockY);
         System.out.println("Mushi color ="+mushi.getColor());
         System.out.println("Mushi x ="+mushi.getX());
         System.out.println("Mushi y ="+mushi.getY());
         mushi.draw(g);
                          
      }                                                                                            //paintComponent
      //***********************************************************END  PLATFORM FRAME(PAINT COMPONENT)******************************************************
      
      
      //******************************************************************PLATFORM FRAME(KEY EVENT DEMO)******************************************************
      public class KeyEventDemo  implements KeyListener 
  {
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e) 
    {
      if(e.getKeyCode() == KeyEvent.VK_W)
      {
         mushi.up();
         
      }
      
      if(e.getKeyCode() == KeyEvent.VK_S)
      {
         mushi.down();
         
      }
      if(e.getKeyCode() == KeyEvent.VK_A)
      {
         mushi.left();
         
      }
      if(e.getKeyCode() == KeyEvent.VK_D)
      {
         mushi.right();
         
      }
      
      repaint(); //calls the paint component
    }
   }                                                                                               //KEY EVENT DEMO
//*********************************************************   END    PLATFORM FRAME(KEY EVENT DEMO)  ******************************************************
        
   }                                                                                               //PlatformPanel class
   
      public static void main(String[] args)
   {
      NOTPLATFORMFRAME pf=new NOTPLATFORMFRAME();
      pf.setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

}                                                                                                  //NOTPLATFORMFRAME class