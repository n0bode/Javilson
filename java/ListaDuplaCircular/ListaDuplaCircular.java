public class ListaDuplaCircular{
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
    private Element prev;
    private Object data;

    public Element(Object data){
      this(data, null, null);
    }

    public Element(Object data, Element prev, Element next){
      this.prev = prev;
      this.next = next;
      this.data = data;
    }

    public Element getNext(){
      return this.next;
    }

    public void setNext(Element next){
      this.next = next;
    }

    public Element getPrev(){
      return this.prev;
    }

    public void setPrev(Element prev){
      this.prev = prev;
    }

    public Object getData(){
      return this.data;
    }

    public void setData(Object data){
      this.data = data;
    }   

    @Override
    public String toString(){
      return "" + this.data;
    }
  }

  private Element tail; //Ultimo elemento da lista 
  /*
   * Adiciona no final da lista
   */
  public void pushEnd(Object data){
    //Cria um novo elemento com a data, mas sem next
    Element neo = new Element(data);
    //Se for a primeira insercao
    if (this.tail == null){
      neo.setPrev(neo);
    }else{
      neo.setPrev(this.tail);
      neo.setNext(this.tail.getNext());
    }
    this.tail.setNext(neo);
    this.tail = neo;
  }
  
  /*
   * Adiciona no inicio da lista
   */
  public void pushBegin(Object data){
    //Cria um novo element com a data e o head como proximo
    //Se for a primeira insercao
    if (this.tail == null){
      Element neo = new Element(data);
      neo.setNext(neo);
      neo.setPrev(neo);
      this.tail = neo;
    }else{
      Element neo = new Element(data, this.getTail(), this.getHead());
      tail.setNext(neo);
      this.getHead().setPrev(neo);
    }
  }
  
  /*
   * Insire na frente do elemento selecionado
   * Ref é o elemento que esta na lista 
   */
  public void insertFront(Object ref, Object data) throws ElementNotFound, EmptyListException{
    Element ptr = this.getElement(ref); //Pega a posicao de ref na lista ou elemento
    if (ptr == null)
      throw new ElementNotFound(""+data);

    Element neo = new Element(data, ptr, ptr.getNext()); //Cria o novo elemento antes do next do ptr
    ptr.getNext().setPrev(neo);
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
  public void insertBack(Object ref, Object data) throws ElementNotFound, EmptyListException{
    Element ptr = this.getElement(ref); //Pega o elemento da lista que tenha o ref
    if (ptr == null)
      throw new ElementNotFound(""+data);
    
    Element neo = new Element(data, ptr.getPrev(), ptr); //O novo elemento
    ptr.setPrev(neo);
    ptr.getPrev().setNext(neo);
    ptr.setPrev(neo);
  }
  
  /*
   * Pega o elemento da lista com a data
  */
  private Element getElement(Object data) throws ElementNotFound, EmptyListException{
    if(tail == null)
      throw new EmptyListException();

    Element ptr = this.getHead();
    for(;ptr.getData() != data && ptr != this.getTail(); ptr = ptr.getNext()){}

    //Aqui ele atira um error quando nao existir o elemento com essa data
    if(ptr == null)
      throw new ElementNotFound(""+data);
    return ptr;
  }

  /*
   * Metodo para apagar um elemento da lista
   */
  public void pop(Object data) throws ElementNotFound, EmptyListException{
    Element ptr = this.getElement(data); //Para comecar a rodar a lista
    ptr.getNext().setPrev(ptr.getPrev());
    ptr.getPrev().setNext(ptr.getNext());
  }  
    
  public void assign(ListaDuplaCircular list){
   if(this != list){
     this.clear();
     for(Element ptr=list.getHead(); ptr != list.getTail(); ptr=ptr.getNext()){
        this.pushBegin(ptr.data);
     }
     this.pushEnd(list.getTail().getData());
   } 
  }

  /*
   * Limpa a lista
   */
  public void clear(){
    this.tail = null;
  }

  public Object getFirst() throws EmptyListException{
    if(this.tail == null)
      throw new EmptyListException();
    return tail.getNext().getData();
  }
  
  public Object getLast() throws EmptyListException{
    if(this.tail == null)
      throw new EmptyListException();
    return this.tail.getData();
  }
  
  private Element getTail(){
    return this.tail;
  }

  private Element getHead(){
    return this.tail.getNext();
  }

  public boolean isEmpty(){
    return (this.tail == null);
  }

  /*
   * Para quando chamar o Print da lista
   * ele ser mostrada formatada
   */
  @Override
  public String toString(){
    if(this.isEmpty())
      return "[]";

    String str = "[ ";
    for(Element ptr = this.getHead(); ptr != this.getTail(); ptr = ptr.getNext()){

      str += ptr.getData() + ", ";
    }
    str += this.getTail().getData() + " ]";
    return str;
  }
}
