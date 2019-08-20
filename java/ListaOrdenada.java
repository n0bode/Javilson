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
		
		@Override
		public String toString(){
			return "" + this.data;
		}
  }
  private Element head;
  private Element tail;

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

  public void pushBack(Object data){
    //Cria um novo element com a data e o head como proximo
    Element neo = new Element(data, this.head);
    //Se for a primeira insercao
    if (this.head == null){
      this.tail = neo;
    }
    this.head = neo;
  }

	public void insertFront(Object ref, Object data){
		Element ptr = this.get(ref);
		Element neo = new Element(data, ptr.getNext());

		ptr.setNext(neo);

		if(ptr == this.tail){
			this.tail = neo;
		}
	}

	public void insertBack(Object ref, Object data){
		Element ptr = this.get(ref);
		Element neo = new Element(data, ptr);
		Element prv = head;
			
		if (ptr == this.head){
			this.head = neo;
			return;
		}

		for(;prv != null && prv.getNext() != ptr; prv = prv.getNext()){}
		prv.setNext(neo);
	}

	public Element get(Object data){
		Element ptr = head;
		for(;ptr != null && ptr.getData() != data; ptr = ptr.getNext()){}
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
	
	public int indexOf(Object data){
		Element ptr = this.head;
  	int index = 0;
   
    for(;ptr != null && ptr.getData() != data; ptr = ptr.getNext()){
      index++;
    }
		return index;
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
			list.insertFront(5, 6);
			list.insertFront(9, 10);
			System.out.println(list);
			System.out.println(list.indexOf(4));
    }catch(Exception e){
      System.out.println("Java é coisa de demente mongoloide");
      System.out.println("Olha esse sistema de errors");
      System.out.println(e);
    }
  }
}
