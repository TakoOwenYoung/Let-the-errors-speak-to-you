import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VictoryBlock extends GameObject
{
   
   public VictoryBlock(int x,int y,Color c)
   {//Player()
      super(); 
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
}
