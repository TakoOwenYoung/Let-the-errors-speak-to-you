import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class PlatformFrame extends JFrame
{                                                                                                                        //PlatformFrame class
int count=0;
int gravity=0; if(c%20==0)
        {
            gravity++;
            yCtr+=gravity;
        }
         
         System.out.println("Gravity is "+gravity);
      //}
      if(gravity>7)
      {
         gravity=7;
      }
 if(c%20==0)
        {
            gravity++;
            yCtr+=gravity;
        }
         
         System.out.println("Gravity is "+gravity);
      //}
      if(gravity>7)
      {
         gravity=7;
      }
 if(c%20==0)
        {
            gravity++;
            yCtr+=gravity;
        }
         
         System.out.println("Gravity is "+gravity);
      //}
      if(gravity>7)
      {
         gravity=7;
      }

   public static ArrayList<ArrayList<GameObject>> staticGoaY;                                                                               
   Container contents;
   PlatformPanel pp;
   Player mushi;
   int jump=0;
   int grav=1;

   boolean canJump=false;
   int jumpNow;
   VictoryBlock target;
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
         javax.swing.Timer t=new javax.swing.Timer(10, new TimeListenerB());
                                                                                                    //try 
         Color currClr=new Color(0,0,0);
         addKeyListener(new KeyEventDemo());
         read=new Scanner(new File("tako.txt"));
         startBlockX=read.nextInt();
         startBlockY=read.nextInt();
         numRows=read.nextInt();
         numColumns=read.nextInt();
         for(int j=0; j<numRows; j++)
         {
               ArrayList<GameObject> goaX=new ArrayList<GameObject>();

               for(int i=0; i<numColumns; i++)
               {
                  
                  int num=read.nextInt();
                  if(num==1)
                  {
                     goaX.add(new GameObject((i*25),(j*25),Color.BLACK));
                  }
                  else if(num==2)   
                   { 
                     target =new VictoryBlock((i*25),(j*25),Color.GREEN);
                     goaX.add(target);
                   }
                  
                  else if(num==0)
                  goaX.add(null);
               }               
                  goaY.add(goaX);    
            }
            
            staticGoaY=goaY;
            mushi=new Player(startBlockX,startBlockY,new Color(0,153,0));
            
            t.start();
            
            
            
            }
                                                                                                  //try
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
   
 
  
   
     
   
   public class TimeListenerB implements ActionListener
   {  
      
            int n;
      
        
      public void actionPerformed(ActionEvent e)
      {  
       count++;
                             

         //try
         //{
            if(canJump)
            {              
                  if(count%10==0)
                   {
                     if(jump<7)
                     jumpNow++;
                   }
                  for(int i=0; i<jump; i++)
                  {
                  if(mushi.canMove(goaY)[2])
                     {
                     mushi.up();
                     jump=7-jumpNow;
                     }
                     else
                     {
                        jump=0;
                        
                     }
                     if(jump==0)
                     {canJump=false;}
                  }
                  if(jump==0)
                  {up=false;}
                  

                     
            }
            
            if(mushi.canMove(goaY)[3])
            {
              
            
            
            
            
            // if(count%20==0&&grav<7)
                 //{grav+=1;}
                 //for(int i=0; i<grav; i++)
                  //{
                     //if(mushi.canMove(goaY)[3])
                     //mushi.dowwn();
                    //mushi.down(count);
                  //}
            }
            
            if(left&&mushi.canMove(goaY)[0] )
            {
              mushi.left();
            }
            if(right&&mushi.canMove(goaY)[1])
            {
               mushi.right();
            }

           repaint(); //calls the paint component
            if(mushi.collidesVictory(goaY,target))
            {
            System.out.println("Mushi collided withh target");
            JOptionPane.showMessageDialog(null, "Congradulations, you've won!", "Victory message", JOptionPane.PLAIN_MESSAGE);
            System.exit(1);
            }
            }
      
   }
//}
///////////////////////End TimeListenerB////////////////////////////////////////////////////////////////////////////////////////////////
///*****************************************************************PLATFORM FRAME(PAINT COMPONENT)******************************************************
      
      public void paintComponent(Graphics g)
      {   
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

         target.draw(g);
         mushi.draw(g);
         
         //do
         //{
            
         //}while(false);              
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
         //down=false;
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
         if(!mushi.canMove(goaY)[3])
         {
         up=true;
         canJump = true;
         jump=7;
         jumpNow=0;
         }
      }
      if(e.getKeyCode() == KeyEvent.VK_S)
      {
         //down=true;
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
   }//end Main 
   
     
}                                                                                                  //PlatformFrame class
