import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


/**
 * BusService encapsulate a bus service with a String id.  It supports
 * querying for the list of bus stops served by this service.
 *
 * @author Ooi Wei Tsang
 * @author Jennifer (B03)
 * @version CS2030 AY20/21 Special Term 1, Lab 8
 */
class BusService {
  private final String serviceId;

  /**
   * Construct a BusService object with a given id.  An empty
   * Set of bus stops is initialized.
   * @param id The id of this bus service.
   */
  public BusService(String id) {
    this.serviceId = id;
  }

  /**
   * Get the current set of bus stops as a Completable Future of a set.  
   * Query the web server if bus stops are not retrieved before.
   * @return A Completable Future of set of bus stops that 
   *     this bus services serves.
   */
  public CompletableFuture<Set<BusStop>> getBusStops() {
    return BusAPI.getBusStopsServedBy(serviceId).thenApply(bs -> {
      Scanner sc = new Scanner(bs);
      Set<BusStop> stops = sc.useDelimiter("\n").tokens()
            .map(line -> line.split(","))
            .map(fields -> new BusStop(fields[0], fields[1]))
            .collect(Collectors.toSet());
      sc.close();
      return stops; });
  }


  /**
   * Return the bus stops matching a given name as a 
   * Completable Future of a set.
   * @param  name Name (possibly partial) of a bus stop.
   * @return A Completable Future of set of bus stops 
   *     matching the given name.
   */
  public CompletableFuture<Set<BusStop>> findStopsWith(String name) {
    return getBusStops().thenApply(bs -> bs.stream()
       .filter(stop -> stop.matchName(name))
       .collect(Collectors.toSet()));
  }

  /**
   * Return the hash code of this bus service.
   * @return The hash code.
   */
  @Override
  public int hashCode() {
    return serviceId.hashCode();
  }

  /**
   * Return true if this bus service is equals to another bus service.
   * Two bus services are equal if they have the same id.
   * @param  busService another bus service to check for equality.
   * @return true if the bus servives are equal.
   */
  @Override
  public boolean equals(Object busService) {
    if (busService instanceof BusService) {
      return this.serviceId.equals(((BusService) busService).serviceId);
    } else {
      return false;
    }
  }

  /**
   * Convert this bus service to a string.
   * @return A string containing the id of this bus service.
   */
  @Override
  public String toString() {
    return serviceId;
  }
}
