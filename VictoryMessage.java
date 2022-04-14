import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VictoryMessage extends JFrame
{
   private Container contents;
   private JLabel labelText;
   private JLabel labelImage;
   
   //Constructor
   public VictoryMessage()
   {
      super("Congraulations");
      
      contents=getContentPane();
      
      contents.setLayout(new BorderLayout()); //Set layout manager
      
      //Make label
      labelText=new JLabel("You've won!");
      //Set label properties
      //labelText.setForeground(Color.RED);
      //labelText.setBackground(Color.GREEN);
      labelText.setOpaque(true);
      
      //Use JLabel constructor with an ImageIcon arguement
      labelImage=new JLabel(new ImageIcon("chotto.jpg"));
      
      //Include tool tip text
      labelImage.setToolTipText("This is my friend, Chotto.");
      
      //Add the two labels to the content pane
      contents.add(labelText,BorderLayout.CENTER);
      //contents.add(labelImage,BorderLayout.CENTER);        
      
      setSize(350,150);
      setLocationRelativeTo(null);                        
      setVisible(true);
   }
   
      public static void main(String[] args)
      {
         VictoryMessage chotto=new VictoryMessage();
         chotto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
   
}