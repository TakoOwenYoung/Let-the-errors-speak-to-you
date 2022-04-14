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
       
         
         java.util.Timer time;
      time=new java.util.Timer();
      
         TimerTask task=new TimerTask() {
         @Override
         public void run() {

            if(pf.mushi.isOnGround(staticGoaY)==false)
            {
               int n=(pf.mushi.getY()+pf.pp.getGrav());
               pf.mushi.setY(n);
            }
            if(pf.mushi.isOnGround(staticGoaY)==true)
            {pf.pp.setGrav(1);}
            pf.repaint();
            }
               
               
            };
         

         /* 
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
      */  /* */



   
      
      
      




  