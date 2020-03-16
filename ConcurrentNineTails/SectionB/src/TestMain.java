import java.util.Arrays;

public class TestMain {

  public static void main(String[] args) {

    System.out.println(new Integer(2).compareTo(new Integer(4)));
    Integer[] items = new Integer[7];
    items[0] = new Integer(0);    items[1] = new Integer(1);    items[2] = new Integer(2);    items[3] = new Integer(3);    items[4] = new Integer(5);    items[5] = new Integer(6);    items[6] = new Integer(99);
    int i = 7 - 1;
    while (i > 0 && items[i].compareTo(items[(i - 1) / 2]) < 0) {
      Integer itemsI = items[i];
      items[i] = items[(i - 1) / 2];
      items[(i - 1) / 2] = itemsI;
      i--;
    }
  }
}
