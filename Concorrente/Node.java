/****************************************************************
* Autor: Paulo Rodrigues Camacan    
* Matricula: 201810829
* Inicio: 25/08/2019
* Ultima alteracao: 30/08/2019
* Nome: Node
* Funcao: Node é a representação do nos
****************************************************************/
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.lang.Runnable;
import java.lang.Thread;
import java.util.Random;

public class Node extends JPanel{
  private Node parent;              //Parent do node
  private ArrayList<Node> children; //Filhos do nodes
  private int nodeID;               //Id do node, so para saber a order
  private int posX;                 //Posicao X do node
  private int posY;                 //Posicao Y do node
     
  //O drawer, responsavel para desenhar a arvore
  private static int countIndex;  //Contador para nodeID
  private int status = 0;         //Status da imagem do node
  private NodeRunnable runnable;  //Evento quando eh chama do run do thread
  private Container container;    //Container no qual o node pertence

  /****************************************************************
  * Metodo: Node
  * Funcao: Instancia a classe
  *************************************************************** */
  public Node(){
    this(null);
  }

  /****************************************************************
  * Metodo: Node
  * Funcao: Instancia a classe
  *************************************************************** */
  public Node(Node parent){
    this.parent = parent;                   //Set parent
    this.children = new ArrayList<Node>();  //Instancia o nova lista
    this.nodeID = countIndex;               //seta o numero do node ID
    countIndex++;                           //counta mais um
    this.setSize(50, 50);
  }

  /****************************************************************
  * Metodo: run
  * Funcao: Funcao que vai rodar quando a thread iniciar
  *************************************************************** */
  public void run(){
    Node root = this; //Como não é possivel pegar this no thread, salvei para pegar

    if(this.runnable != null){
      Thread thread = new Thread(){ //Chama a thread quando for chamado o metodo run
        @Override
        public void run(){
            root.runnable.run(root); //chama o runnable do root
        }
      };
      thread.start(); //Inicia a thread
    }
  }
  
  /****************************************************************
  * Metodo: setParent
  * Funcao: Setter do parent
  *************************************************************** */
  public void setParent(Node parent){
    this.parent = parent; //set o parent
  }
  
  /****************************************************************
  * Metodo: getParent
  * Funcao: Getter do parent
  *************************************************************** */
  public Node getParent(){
    return this.parent;
  }
  
  /****************************************************************
  * Metodo: addChild
  * Funcao: cria um filho para esse node
  *************************************************************** */
  public Node addChild(){
    return this.addChild(null);
  }

  /****************************************************************
  * Metodo: addChild
  * Funcao: Instancia a classe
  * Parametro: set o evento do node 
  *************************************************************** */
  public Node addChild(NodeRunnable runnable){
    Node child = new Node(this); //Criar um novo Node
    child.setContainer(this.container); //Set o container
    if(runnable != null)
      child.setRunnable(runnable);

    this.children.add(child); //Adicionar o novo node para o Children desse node
    return child;
  }
  
  /****************************************************************
  * Metodo: removeChild
  * Funcao: Instancia a classe
  *************************************************************** */
  public boolean removeChild(Node child){
    return this.children.remove(child);
  }

  /****************************************************************
  * Metodo: die
  * Funcao: Instancia a classe
  ****************************************************************/
  public void die(){
    this.status = 7; //Coloca o Morty no status 7
  }

  /****************************************************************
  * Metodo: setRunnable
  * Funcao: Instancia a classe
  ****************************************************************/
  public void setRunnable(NodeRunnable runnable){
    this.runnable = runnable;  
  }

  public void setContainer(Container container){
    this.container = container;
    this.container.add(this);
  }

  /****************************************************************
  * Metodo: count
  * Funcao: pega a quantidade de filhos
  ****************************************************************/
  public int count(){
    return this.children.size();
  }
  
  /****************************************************************
  * Metodo: Node
  * Funcao: Instancia a classe
  ****************************************************************/
  public Node get(int index){
    return this.children.get(index);
  } 

  /****************************************************************
  * Metodo: Node
  * Funcao: Instancia a classe
  ****************************************************************/
  public void setStatus(int status){
    this.status = status;
  }

  /****************************************************************
  * Metodo: revalidate
  * Funcao: Funcao recursiva que vai desenha os filhos e o pai
  * Parametro:
  * x: posicao X
  * y: posicao Y
  * size: tamanho
  * spacing: distancia entre os filhos
  *************************************************************** */
  public void revalidate(int x, int y, int size, int spacing){
    revalidate(0, x, y, size, spacing, 0);
  }

  /****************************************************************
  * Metodo: revalidate
  * Funcao: Funcao recursiva que vai desenha os filhos e o pai
  * Parametro:
  * x: posicao X
  * y: posicao Y
  * size: tamanho
  * spacing: distancia entre os filhos
  * pass: o layer de cada filho, a linha
  *************************************************************** */
  private void revalidate(int index, int x, int y, int size, int spacing, int pass){
    int bound = size + spacing; //tamanho do bloco mais o espaco
    int total = 0; //distancia total em x andado
    
    if(this.parent != null){
      int pc = this.parent.count();
      int offset = this.parent.count() * bound;
      if(pc > 1)
        total = (int)(offset * ((float)index / pc)) - bound;
    }

    int dx = x - total - bound / 2; // faz com que cada pai vai para o meio entre os filhos 
    int dy = y + bound * pass - bound / 2; // adiciona a distancia entre os filhos

    this.setBounds(dx, dy, bound, bound);
    for(int i = 0; i < this.count(); i++){ //desenha os filhos
      //Pass - 1 para pular a layer
      children.get(i).revalidate(i, x - total, y, size, spacing, pass - 1); //chama a recursiva para desenhar os filhos
    }
  }

  /****************************************************************
  * Metodo: draw
  * Funcao: desenha o node
  * Parametro:
  * x: posicao X
  * y: posicao Y
  * size: tamanho
  * spacing: distancia entre os filhos
  * pass: o layer de cada filho, a linha
  ****************************************************************/
  public void draw(Graphics g, int x, int y, int size, int spacing){
    draw(g, x, y, size, spacing, 0);
  }

  /****************************************************************
  * Metodo: Node
  * Funcao: Funcao recursiva que vai desenha os filhos e o pai
  * Parametro:
  * x: posicao X
  * y: posicao Y
  * size: tamanho
  * spacing: distancia entre os filhos
  * pass: o layer de cada filho, a linha
  *************************************************************** */
  private int draw(Graphics g, int x, int y, int size, int spacing, int pass){
    int total = 0; //distancia total em x andado
    int bound = size + spacing; //tamanho do bloco mais o espaco

    for(int i = 0; i < this.count(); i++){ //desenha os filhos
      //Pass - 1 para pular a layer
      total += children.get(i).draw(g, x + total, y, size, spacing, pass - 1); //chama a recursiva para desenhar os filhos
    }

    int dx = x + (total / 2) -  bound / 2 ; // faz com que cada pai vai para o meio entre os filhos 
    int dy = y + bound * pass - bound / 2; // adiciona a distancia entre os filhos

    if(this.count() > 0) //se houver filhos
      dx -= bound / 2; //diminui bound/2 ou centro

    //g.drawImage(Icons.getMorty(this.status), dx, dy, null); //Pega a imagem atual do morty e mostra na posicao no node
    this.setLocation(dx, dy);
    return total + (count() == 0 ? bound : 0);
  }

  /****************************************************************
  * Metodo: paintComponent
  * Funcao: Faz o overide do metodo de pintar do elemento
  ****************************************************************/
  public void paintComponent(Graphics g){
    g.drawImage(Icons.getMorty(this.status), 0, 0, 100, 100, this); //Desnha o morty na posicao em que o Node foi colocado
  }
}