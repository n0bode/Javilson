import javax.swing.*;
import java.awt.*;

public class Principal extends JFrame{
  private Node root;

  public Principal(){
    super();
    this.build();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.root.init();
  }
  
  private void build(){
    this.root = new Node();
    root.setDrawer(this);
    root.addChild();
    root.addChild();
    root.addChild();

    root.get(0).addChild();

    root.get(0).addChild();
    root.get(0).addChild();
    root.get(0).addChild(); 
    root.get(0).addChild();

    root.get(0).get(3).addChild();
    root.get(0).get(3).addChild();
    root.get(0).get(3).addChild();
    root.get(0).get(3).addChild();
    
    root.get(1).addChild();
    root.get(1).addChild();

    root.get(1).get(0).addChild();
    root.get(1).get(0).addChild();
    
    root.get(1).get(1).addChild();
    root.get(1).get(1).addChild();
    
    root.get(2).addChild().addChild();
    root.get(2).get(0).addChild();
    root.get(2).get(0).addChild();

    root.get(2).addChild().addChild();
    root.get(2).get(0).addChild().addChild();
    root.get(1).addChild();

    root.get(1).get(0).get(0).addChild();
    root.get(1).get(0).get(0).addChild();
    root.get(1).get(0).get(0).addChild();
  }
  
  public void paint(Graphics g){
    super.paint(g);
    root.draw(g, 20, 50, 40, 25);
  }

  public static void main(String[] args){
    Principal frame  = new Principal();
    frame.setSize(800, 800);
    frame.setVisible(true);
  }
}
