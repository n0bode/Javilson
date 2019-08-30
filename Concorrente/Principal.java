/****************************************************************
* Autor: Paulo Rodrigues Camacan    
* Matricula: 201810829
* Inicio: 25/08/2019
* Ultima alteracao: 30/08/2019
* Nome: Principal
* Funcao: Classe que vai rodar tudo
****************************************************************/
import javax.swing.*;
import java.lang.Thread;
import java.lang.Runnable;
import java.time.Duration;
import java.time.Instant;

public class Principal extends JFrame implements Runnable{
  private Node root; //Node pai
  private int SECOND = 1000; //Variavel para mudar a velocidade do tempo
  private JLabel txtTimer; //Component para mostrar o timer
  private Instant start; //Tempo para quando iniciar o thread pai
  private boolean running; //Se estiver rodando
  private Thread timer; //Thread do timer

  public Principal(){
    super();
    this.build();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);
    this.root.run();
  }

  /****************************************************************
  * Metodo: sleep
  * Funcao: como java eh muito verboroso, isso nao e um bom metodo, mas reduzir para so sleep
  *************************************************************** */
  private void sleep(int milli){
    try{
      Thread.sleep(milli);
    }catch(Exception e){
      //println(e.getMessage());
    }
  }
  
  /****************************************************************
  * Metodo: mainEvent
  * Funcao: evento principal, ou a thread do pai
  *************************************************************** */
  private void mainEvent(Node root){
    //Callback do pai
    this.repaint(); //re-limpa a tela
    start = Instant.now(); //Pega o timer de inicio

    sleep(SECOND * 10); //10s
    root.setStatus(1);  //Para o morty com 10 anos  
    sleep(SECOND * 12); //22s
    root.setStatus(2); // Para o morty com 20 anos
    //println("First child");
    root.addChild((first) -> { //Adicionando o filho ao pai e criando o runnable dele
      sleep(SECOND * 10); //32s
      first.setStatus(1); //Para o morty com 10 anos
      sleep(SECOND * 6); //38 s

      //Adicionar o neto
      first.addChild((grandson) ->{ //Adicionando o neto ao filho e criando o runnable dele
        //Callback do primero neto
        sleep(SECOND * 10); //48s
        grandson.setStatus(1); //Para o morty com 10 anos
        sleep(SECOND * 10); //58s
        grandson.setStatus(2); // Para o morty com 20 anos //Para o morty com 20 anos
        //println("add bison");
        grandson.addChild((bison) ->{ //Adicionando oa neto o bisneto e criando o runnable dele
          sleep(SECOND * 10); //80 segs
          bison.setStatus(1); //Para o morty com 10 anos
          sleep(SECOND * 2);
          //println("bison dead");
          bison.die(); //mata o bisneto
        }).run(); //Roda a thread

        sleep(SECOND * 5); //73
        //println("first grandson dead");
        grandson.die(); //mata o primeiro neto
      }).run();
      sleep(SECOND * 9); //83
      first.setStatus(2); // Para o morty com 20 anos
      sleep(SECOND * 10); //83
      first.setStatus(3); //Para o Morty de 30 anos
      sleep(SECOND * 6); //83
      first.setStatus(4); //Para o Morty de 40 anos
      sleep(SECOND * 10);
      first.setStatus(5); //Para o Morty de 50 anos
      sleep(SECOND * 10);
      //println("Firts child dead");
      first.die(); //o primeiro filho morre
    }).run();

    sleep(SECOND * 3); //25 Segs
    //println("Second child");
    //Cria o segundo filho e criar o callback da thread dele
    root.addChild((second) ->{
      //Callback da thread do segundo filho
      sleep(SECOND * 10);
      second.setStatus(1); //Para o morty com 10 anos
      sleep(SECOND * 10); //45 segs
      second.setStatus(2); // Para o morty com 20 anos
      //println("Second grandson");
      //Cria o segundo neto e o adiciona para o segundo filho
      second.addChild((grandson) ->{
        sleep(SECOND * 33); //78s
        //println("Second grandson dead");
        grandson.die(); //Morre o segundo neto
      }).run();     
      sleep(SECOND * 15); //80
      second.setStatus(3); //Para o Morty de 30 anos //Para o Morty de 30 anos
      sleep(SECOND * 5);
      second.setStatus(4); //Para o Morty de 40 anos
      sleep(SECOND * 10);
      second.setStatus(5); //Para o Morty de 50 anos
      sleep(SECOND * 5);
      //println("Second Child dead");
      second.die(); //Morre o segundo filho
    }).run();

    sleep(SECOND * 7); //32 Sgs
    root.setStatus(3); //Para o Morty de 30 anos
    //Cria o terceiro filho e criar o callback da thread dele
    root.addChild((third) ->{
      //Callback do terceiro filho
      sleep(SECOND * 10); //42
      third.setStatus(1); //Para o morty com 10 anos
      sleep(SECOND * 10); //52
      third.setStatus(2); // Para o morty com 20 anos
      sleep(SECOND * 10); //62
      third.setStatus(3); //Para o Morty de 30 anos
      sleep(SECOND * 10); //72
      third.setStatus(4); //Para o Morty de 40 anos
      sleep(SECOND * 10); //82
      third.setStatus(5); //Para o Morty de 50 anos 
      sleep(SECOND * 5); //87
      //println("Third child is dead");
      third.die(); //mata  
    }).run(); 
    sleep(SECOND * 8);
    root.setStatus(4); //Para o Morty de 40 anos
    sleep(SECOND * 10);
    root.setStatus(5); //Para o Morty de 50 anos
    sleep(SECOND * 20);
    root.setStatus(5); //Para o Morty de 60 anos
    sleep(SECOND * 20); //90 secs

    running = false; //Para o thread
    this.timer.interrupt(); //Para o thread
    //println("Father dead");
    root.die(); //mata
    this.updateTimer(); //Atualiza o tempo para arrumar a hora
    repaint(); //re-limpa a tela
  }

  /****************************************************************
  * Metodo: build
  * Funcao: funcao para iniciar a arvore 
  *************************************************************** */
  private void build(){
    this.root = new Node(); //Cria o no pai
    this.root.setRunnable(this::mainEvent); //Seta o callback da thread principal
    this.root.setContainer(this); //Set o layout pai do no pai
    this.repaint(); //re-limpa a tela

    this.txtTimer = new JLabel(""); //Cria a Jabel para mostrar o timer na tela
    this.txtTimer.setBounds(250, 0, 100, 100); //Move e redimenciona o timer da tela
    this.add(txtTimer); //adiciona a frame principal

    running = true; //Para o programar rodar
    this.timer = new Thread(this); //Criar a thread do timer
    this.timer.start(); //Inciar a thread do timer
  }
  
  /****************************************************************
  * Metodo: updateTimer
  * Funcao: atualiza o timer na tela
  *************************************************************** */
  private void updateTimer(){
    if(start != null){ //Caso ainda o no pai nao foi iniciado
      Instant end = Instant.now(); //Pega o tempo agora
      long diff = Duration.between(start, end).toMillis(); //Converte a diferenca do tempo quando iniciou para o agora
      double secs = (double)diff / SECOND; //Converte para segundos
      this.txtTimer.setText(String.format("%d anos %d meses", (int)secs, (int)((secs % 1) * 12))); //Format o timer para ser mostrado
    }
  }

  /****************************************************************
  * Metodo: run
  * Funcao: Metedo para quando o timer for iniciado
  *************************************************************** */
  public void run(){
    try{
      while(running){ //Quando o programar estiver rodando
        updateTimer(); //Atualiza o timer na tela
        this.root.revalidate(300, 550, 125, 10); //recalcula a posicao os nos na arvore
        this.repaint(); //re-limpa a tela
        Thread.sleep(50);
      }
    }catch(Exception e){
      
    } 
  }

  public static void main(String[] args){
    Principal frame = new Principal(); //Cria a frame principal
    frame.setSize(600, 650); //Seta o tamanho da janela 600x600
    frame.setVisible(true); //Mostra a janela
  }
}