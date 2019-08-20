public class Array{
  private Object[] data;
  private int base;
  
  public Array(){
    this(0, 0);
  }

  public Array(int size){
    this(size, 0);
  }

  public Array(int size, int lim){
    this.data = new Object[size];
    this.base = lim;
  }

  public void assign(Array arr){
    if(this != arr){
      if(arr.getLength() != this.getLength())
        this.data = new Object[arr.getLength()];

      for(int i = 0; i < arr.getLength(); i++){
        this.data[i] = arr.data[i];
      }
      this.base = arr.base;
    }
  }

  public int getLength(){
    return this.data.length;
  }

  public void setLength(int size){
    if(this.getLength() != size){
      Object[] tmp = new Object[size];
      int min = this.getLength() < size ? this.getLength() : size;
      for(int i = 0; i < min; i++)
        tmp[i] = this.data[i];
      this.data = tmp;
    }
  }

  public Object get(int index){
    return this.data[index-this.base];
  }

  public void set(int index, Object obj){
    this.data[index-this.base] = obj;
  }
}
