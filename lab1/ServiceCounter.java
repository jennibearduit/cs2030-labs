public class ServiceCounter {
  private boolean isAvailable;
  private static int numberOfServiceCounters;

  public ServiceCounter() {
    this.isAvailable = true;
    ServiceCounter.numberOfServiceCounters++;
  }

  public void assignCustomer(int n) {
    this.isAvailable = false;
  }
}
