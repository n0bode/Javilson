import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.lang.Thread;
import java.util.Random;

public class Node extends Thread{
  private Node parent; //Parent do node
  private ArrayList<Node> children; //Filhos do nodes
  private int nodeID; //Id do node, so para saber a order
  private int posX;   //Posicao X do node
  private int posY;   //Posicao Y do node

  private Component drawer; //O drawer, responsavel para desenhar a arvore
  private static int countIndex; //Contador para nodeID
  private Color color; //Cor do node
  
  public Node(){
    this(null);
  }
 
  public Node(Node parent){
    Random rand = new Random();
    this.parent = parent;
    this.children = new ArrayList<Node>();
    this.nodeID = countIndex;
    this.color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    countIndex++;
  }

  @Override
  public void run(){
    try{
      System.out.println("create");
      Thread.sleep((30 - this.nodeID) * 1000);
      System.out.println("destred " + this.nodeID);
      
      if(this.getParent() != null){
        if(this.count() > 0){
        }
        this.getParent().removeChild(this);
      }
      this.drawer.repaint();
    }catch (Exception e){

    }
  }

  public void setParent(Node parent){
    this.parent = parent;
  }
  
  public Node getParent(){
    return this.parent;
  }

  public Node addChild(){
    Node child = new Node(this);
    child.setDrawer(this.drawer);
    this.children.add(child);
    return child;
  }
  
  public boolean removeChild(Node child){
    child.interrupt();
    return this.children.remove(child);
  }

  public boolean removeChild(int index){
    return this.children.remove(this.children.get(index));
  }

  public void clearChild(){
  }

  public int count(){
    return this.children.size();
  }
  
  public Node get(int index){
    return this.children.get(index);
  } 

  public void draw(Graphics g, int x, int y, int size, int spacing){
    draw(g, x, y, size, spacing, 0);
  }

  private int draw(Graphics g, int x, int y, int size, int spacing, int pass){
    int total = 0;
    int bound = size + spacing;

    for(int i = 0; i < this.count(); i++){
      total += children.get(i).draw(g, x + total, y, size, spacing, pass + 1);
    }

    int dx = x + (total / 2);
    int dy = y + bound * pass;

    if(this.count() > 0){
      dx -= bound / 2;
    }

    //System.out.println("node:" + nodeID + "; x:" + dx + "; total:" + total);
    g.setColor(this.color);
    g.fillRect(dx, dy, size, size); 
    g.setColor(Color.BLACK);
    for(Node child : this.children){
      drawLine(g, dx + size/2, dy + size, child.posX + size/2, child.posY);
    }
    g.drawRect(dx, dy, size, size); 

    this.posX = dx;   
    this.posY = dy;
    
    return total + (count() == 0 ? bound : 0);
  }

  public void drawLine(Graphics g, int x, int y, int dx, int dy){
    int my = (y + dy) / 2;
    int cy = (dy - y) / 2;
    
    Graphics2D g2 = (Graphics2D)g;

    g2.drawLine(x, my, dx, my);
    g2.drawLine(x, y, x, y+cy);
    g2.drawLine(dx, dy-cy, dx, dy);
  }
  
  public void setDrawer(Component drawer){
    this.drawer = drawer;
  }

  public void init(){
    for(Node child : this.children){
      child.init();
    }
    this.start();
  }
}
