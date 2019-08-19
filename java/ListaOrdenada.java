public class ListaOrdenada{
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
  private Element head;
  private Element tail;
 
  public void pushBack(Object data){
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

  public void pushFront(Object data){
    //Cria um novo element com a data e o head como proximo
    Element neo = new Element(data, this.head);
    //Se for a primeira insercao
    if (this.head == null){
      this.tail = neo;
    }
    this.head = neo;
  }

  public void pop(Object data) throws Exception{
    Element ptr = this.head;
    Element pre = null;
   
    for(;ptr != null && ptr.getData() != data; ptr = ptr.getNext()){
      pre = ptr;
    }
    //Acerta um error
    if (ptr == null){
      throw new Exception("Falha no engano");
    } 

    if (ptr == this.head){
      this.head = ptr.getNext();
    }else if (ptr == this.tail){
      this.tail = pre;
      pre.setNext(null);
    }else{
      pre.setNext(ptr.getNext());
    }
  }
  
  public Element getHead(){
    return this.head;
  }

  public Element getTail(){
    return this.tail;
  }
  
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
      list.pop(0);
      System.out.println("Poof 0");
      System.out.println(list);
      list.pop(5);
      System.out.println("Poof 5");
      System.out.println(list);
      System.out.println("Error test");
      list.pop(10);
    }catch(Exception e){
      System.out.println("Java é coisa de demente mongoloide");
      System.out.println("Olha esse sistema de errors");
      System.out.println(e);
    }
  }
}
