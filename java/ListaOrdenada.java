public class ListaOrdenada{
  //Exceptions
  public class EmptyListException extends Exception{}
  public class ElementNotFound extends Exception{}

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
   */
  public void insertFront(Object ref, Object data) throws ElementNotFound{
    Element ptr = this.getElement(ref);
    Element neo = new Element(data, ptr.getNext());

    ptr.setNext(neo);
    if(ptr == this.tail){
      this.tail = neo;
    }
  }

  /*
   * Insere atras do elemento selecionado
   */
  public void insertBack(Object ref, Object data) throws ElementNotFound{
    Element ptr = this.getElement(ref);
    Element neo = new Element(data, ptr);
    Element prv = head;
      
    if (ptr == this.head){
      this.head = neo;
      return;
    }

    for(;prv != null && prv.getNext() != ptr; prv = prv.getNext()){}
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
      throw new ElementNotFound();
    }
    return ptr;
  }

  public void pop(Object data) throws Exception{
    Element ptr = this.head;
    Element prv = null;
   
    for(;ptr != null && ptr.getData() != data; ptr = ptr.getNext()){
      prv = ptr;
    }
    //Acerta um error
    if (ptr == null){
      throw new Exception("Falha no engano");
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
    ListaOrdenada list = new ListaOrdenada();

    for(int i = 0; i < 10; i++){
      if (i % 2 == 0)
        list.pushBack(i);
      else
        list.pushFront(i); 
    }
    System.out.println(list);

    try{
      list.insertFront(15, 6);
      list.insertFront(9, 10);
      System.out.println(list);
    }catch(Exception e){
      System.out.println("Java é coisa de demente mongoloide");
      System.out.println("Olha esse sistema de errors");
      System.out.println(e);
    }
  }
}
