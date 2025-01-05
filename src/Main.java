public class Main {

  public static Node<Integer> buildCircularList(int n) {
      if (n <= 0) return null;

      Node<Integer> head = new Node<>(1);
      Node<Integer> current = head;

      for (int i = 2; i <= n; i++) {
          Node<Integer> newNode = new Node<>(i);
          current.setNext(newNode);
          current = newNode;
      }

      current.setNext(head);
      return head;
  }

  public static void josephus(Node<Integer> head, int n, int m, int k) {
      if (head == null || n <= 0 || m <= 0 || k <= 0 || k > n) return;

      Node<Integer> current = head;
      Node<Integer> prev = null;

      while (current.getNext() != head) {
          current = current.getNext();
      }
      prev = current;

      current = head;
      System.out.print("Current position: ");

      int remaining = n;
      while (remaining > k) {
          for (int step = 1; step < m; step++) {
              prev = current;
              current = current.getNext();
          }

          System.out.print(current.getValue() + " ");
          prev.setNext(current.getNext());
          current = current.getNext();
          remaining--;
      }

      System.out.println();
      System.out.print("Remaining: ");
      for (int i = 0; i < k; i++) {
          System.out.print(current.getValue() + " ");
          current = current.getNext();
      }
      System.out.println();
  }

  public static void main(String[] args) {
      int n = 41;
      int m = 2;
      int k = 1;

      Node<Integer> circularList = buildCircularList(n);
      josephus(circularList, n, m, k);

      if (k == 1) {
          System.out.println("Josephus should stand at position: " + findSurvivor(n, m));
      }
  }

  public static int findSurvivor(int n, int m) {
      int survivor = 0;
      for (int i = 1; i <= n; i++) {
          survivor = (survivor + m) % i;
      }
      return survivor + 1;
  }
}
