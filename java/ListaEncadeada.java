public class ListaEncadeada{
  //Exceptions
  public class EmptyListException extends Exception{}

  public class ElementNotFound extends Exception{
    public ElementNotFound(String message){
      super(message);
    }
  }

  //Class Element
  public class Element{
    private Element next;
    private Object data;

    public Element(Object data){
      this.data = data;
    }

    public Element(Object data, Element next){
      this.next = next;
      this.data = data;
    }

    public Element getNext(){
      return this.next;
    }

    public void setNext(Element next){
      this.next = next;
    }

    public Object getData(){
      return this.data;
    }

    public void setData(Object data){
      this.data = data;
    }   
  }

  private Element head; //Primeiro elemento da lista
  private Element tail; //Ultimo elemento da lista
  
  /*
   * Adiciona no inicio da lista
   */
  public void pushFront(Object data){
    //Cria um novo elemento com a data, mas sem next
    Element neo = new Element(data);
    //Se for a primeira insercao
    if (this.tail == null){
      this.head = neo;
    }else{
      this.tail.setNext(neo);
    }
    this.tail = neo;
  }
  
  /*
   * Adiciona no final da lista
   */
  public void pushBack(Object data){
    //Cria um novo element com a data e o head como proximo
    Element neo = new Element(data, this.head);
    //Se for a primeira insercao
    if (this.head == null){
      this.tail = neo;
    }
    this.head = neo;
  }
  
  /*
   * Insire na frente do elemento selecionado
   * Ref é o elemento que esta na lista 
   */
  public void insertFront(Object ref, Object data) throws ElementNotFound{
    Element ptr = this.getElement(ref); //Pega a posicao de ref na lista ou elemento
    Element neo = new Element(data, ptr.getNext()); //Cria o novo elemento antes do next do pptr

    ptr.setNext(neo);
    
    //Se o ptr for o tail, o neo sera o novo tail
    if(ptr == this.tail){
      this.tail = neo;
    }
  }

  /*
   * Insere atras do elemento selecionado
   * Ref é a data do elemento
   */
  public void insertBack(Object ref, Object data) throws ElementNotFound{
    Element ptr = this.getElement(ref); //Pega o elemento da lista que tenha o ref
    Element neo = new Element(data, ptr); //O novo elemento
    Element prv = head; //Comeca a vasculhar o antecessor em head
    
    //Se o ptr for o head, o neo sera o novo head
    if (ptr == this.head){
      this.head = neo;
      return;
    }
    
    //Comeca a vasculhar a lista a procura do antecessor
    for(;prv != null && prv.getNext() != ptr; prv = prv.getNext()){}
    //Seta o proximo do antecessor como o novo elemento
    prv.setNext(neo);
  }
  
  /*
   * Pega o elemento da lista com a data
  */
  private Element getElement(Object data) throws ElementNotFound{
    Element ptr = head;
    for(;ptr != null && ptr.getData() != data; ptr = ptr.getNext()){}

    //Aqui ele atira um error quando nao existir o elemento com essa data
    if(ptr == null){
      throw new ElementNotFound(""+data);
    }
    return ptr;
  }

  /*
   * Metodo para apagar um elemento da lista
   */
  public void pop(Object data) throws ElementNotFound{
    Element ptr = this.head; //Para comecar a rodar a lista
    Element prv = null; //O Prev é nulo, pois comeca a vaculhar em head
    
    /* Aqui vai procurar o elemento da lista
     * que tenha mesma data
     * aproveita e pega tambem o antecessor dele
     */ 
    for(;ptr != null && ptr.getData() != data; ptr = ptr.getNext()){
      prv = ptr; //Seta o antecessor
    }

    //Acerta um error
    if (ptr == null){
      throw new ElementNotFound(""+data);
    } 

    if (ptr == this.head){
      this.head = ptr.getNext();
    }else if (ptr == this.tail){
      this.tail = prv;
      prv.setNext(null);
    }else{
      prv.setNext(ptr.getNext());
    }
  }  
  
  /*
   * Metodo para pegar o index do elemento na lista
   * Nao serve pra nada, mas e bom ter como exemplo
   */
  public int indexOf(Object data){
    Element ptr = this.head;
    int index = 0;
   
    for(;ptr != null && ptr.getData() != data; ptr = ptr.getNext()){
      index++;
    }
    return index;
  }
  
  public void replace(ListaEncadeada list){
   if(this != list){
     this.clear();
     for(Element ptr=list.getHead(); ptr != null; ptr=ptr.getNext()){
        this.pushFront(ptr.data);
     }
   } 
  }

  /*
   * Limpa a lista
   */
  public void clear(){
    this.tail = this.head = null;
  }

  public Object getFirst() throws EmptyListException{
    if(this.head == null)
      throw new EmptyListException();
    return this.head.getData();
  }
  
  public Object getLast() throws EmptyListException{
    if(this.tail == null)
      throw new EmptyListException();
    return this.tail.getData();
  }

  public Element getHead(){
    return this.head;
  }

  public Element getTail(){
    return this.tail;
  }
  
  public boolean isEmpty(){
    return (this.head == null);
  }

  /*
   * Para quando chamar o Print da lista
   * ele ser mostrada formatada
   */
  @Override
  public String toString(){
    String str = "[ ";
    for(Element ptr = head; ptr != null; ptr = ptr.getNext()){
      str += ptr.getData() + (ptr.getNext() != null ? ", " : "");
    }
    str += "]";
    return str;
  }

  public static void main(String[] args){
    ListaEncadeada list0 = new ListaEncadeada();
    ListaEncadeada list1 = new ListaEncadeada();

    //Aqui gera os elementos da lista
    for(int i = 0; i < 10; i++){
      list0.pushFront(9-i);
      list1.pushFront(i);
    }

    System.out.println("Lista 0: " + list0);
    System.out.println("Lista 1: " + list1);
    try{
      list1.insertFront(5, -1);
      System.out.println("Adiciona -1 na frente do 5");
      System.out.println("Lista 1: " + list1);
      list1.pop(-1);
      System.out.println("Remove -1");
      System.out.println("Lista 1: " + list1);
      System.out.println("Atribute Lista1 a lista0");
      list0.replace(list1);
      System.out.println("Lista 0: " + list0);
    }catch(ElementNotFound e){
      System.out.println("Acho levemente que nao existe o elemento " + e.getMessage());
    }catch(Exception e){
      System.out.println(e);
    }
  }
}
