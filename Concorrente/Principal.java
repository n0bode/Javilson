import javax.swing.*;
import java.awt.*;
import java.lang.Thread;

public class Principal extends JFrame{
  private Node root;

  public Principal(){
    super();
    this.build();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.root.init();
  }
  
  private void build(){
    System.out.println("Nasce o Pai");
    this.root = new Node();
    root.setDrawer(this);
    this.root.addEvent(new NodeEvent(){
       @Override
       public void run(Node node){
         try{
           Thread.sleep(2000);
           System.out.println("Nasce o primeiro filho");
           node.addChild();
           node.getDrawer().repaint();

           Thread.sleep(3000);
           System.out.println("Nasce o segundo filho");
           node.getDrawer().repaint();

           node.addChild();
           Thread.sleep(7000);
         }catch(Exception e){
           System.out.println(e.getMessage());
         }
       }
    });
    this.repaint();
  }
  
  @Override 
  public void paint(Graphics g){
    super.paint(g);
    if (root != null){
      root.draw(g, 20, 50, 40, 25);
    }
  }

  public static void main(String[] args){
    Principal frame  = new Principal();
    frame.setSize(800, 800);
    frame.setVisible(true);
  }
}
