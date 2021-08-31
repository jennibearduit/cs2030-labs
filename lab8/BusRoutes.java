import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Encapsulates the result of a query: for a bus stop and a search string,
 * it stores a map of bus services that servce stops with matching name.
 * e.g., stop 12345, name "MRT", with map contains:
 *    96: 11223 "Clementi MRT"
 *    95:  22334 "Beuno Vista MRT"
 *
 * @author Ooi Wei Tsang
 * @author Jennifer (B03)
 * @version CS2030 AY20/21 Special Term 1, Lab 8
 */
class BusRoutes {
  final BusStop stop;
  final String name;
  final Map<BusService, CompletableFuture<Set<BusStop>>> services;

  /**
   * Constructor for creating a bus route.
   * @param stop The first bus stop.
   * @param name The second bus stop.
   * @param services The Completable Future of set of bus services between the two stops.
   */
  BusRoutes(BusStop stop, String name, Map<BusService, CompletableFuture<Set<BusStop>>> services) {
    this.stop = stop;
    this.name = name;
    this.services = services;
  }

  /**
   * Return a Completable Future of string describing the bus route.
   * @return A completable Future of string describing the bus route. 
   *     The first line contains the query information:
   *     bus stop id and search string.  The remaining line contains 
   *     the bus services and matching stops served.
   */
  public CompletableFuture<String> description() {
    String result = "Search for: " + this.stop + " <-> " + name + ":\n";
    result += "From " +  this.stop + "\n";

    CompletableFuture<String> finalResult = CompletableFuture.completedFuture(result);

    for (BusService service : services.keySet()) {
      CompletableFuture<String> serviceDescription = services.get(service)
          .thenApply(stops -> describeService(service, stops));
      finalResult = finalResult.thenCombine(serviceDescription, (fr, sd) -> fr + sd);
    }
    return finalResult;
  }

  /**
   * Return a string representation a bus service and its matching stops.
   * @param service The bus service. 
   * @param stops The set of bus stops.
   * @return The first line contains the query information:
   *     bus stop id and search string.  The remaining line contains 
   *     the bus services and matching stops served.
   */
  public String describeService(BusService service, Set<BusStop> stops) {
    if (stops.isEmpty()) {
      return "";
    } 
    return stops.stream()
        .filter(stop -> stop != this.stop) 
        .reduce("- Can take " + service + " to:\n",
            (str, stop) -> str += "  - " + stop + "\n",
            (str1, str2) -> str1 + str2);
  }
}
