
class VirtualThread {
  public static void main(String[] args) {

    Thread vThreadUnstarted = Thread.ofVirtual().unstarted(runnable);
    Runnable runnable = () -> {
      for(int i=0; i<10; i++) {
          System.out.println("Index: " + i);
      }
    };
    
    Thread vThread = Thread.ofVirtual().start(runnable);
  }
}
