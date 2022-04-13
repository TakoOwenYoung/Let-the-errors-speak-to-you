if(Math.abs(this.getX()-goaY.get(j).get(i).getX())<25&&Math.abs(this.getY()-goaY.get(j).get(i).getX())<25)

public boolean move(ArrayList<ArrayList<GameObject>> goaY)
   {//move
      boolean tempi=true;
      GameObject current;
      
      //Is something on the left?
      
      xCtr--;
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         
         for(int i=0; i<goaY.get(j).size(); i++)        
         {//i loop
         if(goaY.get(j).get(i)!=null&&(Math.abs(this.getX()-goaY.get(j).get(i).getX())<25&&Math.abs(this.getY()-goaY.get(j).get(i).getX())<25))
         {
         //if(Math.abs(this.getX()-goaY.get(j).get(i).getX())<25)
         //current=goaY.get(j).get(i);
           // if(this.getLeft() < goaY.get(j).get(i).getRight())

           //{
               //if(Math.abs(current.getX()-this.getX())<25&&Math.abs(current.getY()-this.getY())<25)
                  //{
                     //if(this.collidesOnTop(current)&&this.getY()==current.getY()&&this.getX()-current.getX()<25)
                     //{
                        tempi=false;
                        System.out.println("Player xCtr: "+xCtr+", Player yCtr: "+yCtr);
                        System.out.println("Current xCtr: "+goaY.get(j).get(i).getX()+", Current yCtr: "+goaY.get(j).get(i).getY());
                        System.out.println("Can move= "+tempi );
                     //} 
                  //}
           //}
          }   
         }//i loop
      }//Outer J loop   
         xCtr++;
      
            
      /*Is something on the right?

      xCtr+=2;
      
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current=goaY.get(j).get(i);
         
            if(current!=null)
            {
   
               if(this.collides(goaY.get(j).get(i)))
               {
                  temp=false;
               }
            
            }
         }
      }//Outer J loop
      xCtr--; //xCtr back to normal
      
      /*
      
      yCtr--;
      
      for(int j=0; j<goaY.size(); j++)
      {//Outer J loop
         for(int i=0; i<goaY.get(j).size(); i++)
         {
            current=goaY.get(j).get(i);
         
              if(current!=null)
            {

               if(this.collides(goaY.get(j).get(i)))
               {
                  temp=false;
               }
            
            }
         }
      }//Outer J loop
      
      yCtr++;
      */
      return tempi;
      
   }//move

if(this.getLeft()==current.getRight() &&
               this.getY()==current.getY() &&
               Math.abs(this.getX()-current.getX())==25)






   
      
      
      




  