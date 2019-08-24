
public class Main{
  public static void main(String[] args){
    ListaDuplaCircular list0 = new ListaDuplaCircular();
    ListaDuplaCircular list1 = new ListaDuplaCircular();

    //Aqui gera os elementos da lista
    for(int i = 0; i < 9; i++){
      list1.pushBegin(i);
      list0.pushBegin(9 -i);
    }
    System.out.println("Lista 1: " + list1);
    System.out.println("Lista 0: " + list0);
    try{
      list1.insertFront(5, -1);
      System.out.println("Adiciona -1 na frente do 5");
      list1.insertBack(5, -1);
      System.out.println("Adiciona -1 atras do 5");
      System.out.println("Lista 1: " + list1);
      list1.pop(-1);
      System.out.println("Remove -1");
      System.out.println("Lista 1: " + list1);
      System.out.println("Atribute Lista1 a lista0");
      list0.assign(list1);
      System.out.println("Lista 0: " + list0);
    }catch(Exception e){
      System.out.println(e);
    }
  }
}
