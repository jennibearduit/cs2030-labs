import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

/**
 * The BusAPI class interface with the Web API running at 
 * cs2030-bus-api.herokuapp.com to retrieve:
 * - bus services that serve a given bus stop 
 * - bus stop visited by a bus service.
 *
 * @author Ooi Wei Tsang
 * @author Jennifer (B03)
 * @version CS2030 AY20/21 Semester 2, Lab 8
 **/
class BusAPI {
  /** 
   * URL to query for bus stops. 
   * An alternative is "https://www.comp.nus.edu.sg/~ooiwt/bus_services/"
   */
  private static final String BUS_SERVICE_API = 
      "https://cs2030-bus-api.herokuapp.com/bus_services/";

  /** 
   * URL to query for bus services. 
   * An alternative is "https://www.comp.nus.edu.sg/~ooiwt/bus_stops/"
   **/
  private static final String BUS_STOP_API = 
      "https://cs2030-bus-api.herokuapp.com/bus_stops/";

  /**
   * Given a URL, asynchronously obtained the HTTP response string
   * from the URL.  It returns a Completable Future of string.
   * @param url The URL to query
   * @return The HTTP resposne body wrapped by Completable Future.
   */
  private static CompletableFuture<String> httpGet(String url) {
    HttpClient client = HttpClient.newBuilder()
        .build();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();

    return client.sendAsync(request, BodyHandlers.ofString()).thenApply(response -> {
      if (response.statusCode() != 200) {
        System.out.println(response + " " + response.statusCode());
        return "";
      }
      return response.body(); 
    });
  }

  /**
   * Return a Completable Future of string from CS2030 BUS API given a bus service ID.
   * @param serviceId Bus service id
   * @return The Completable Future of string returned by the CS2030 BUS API service listing
   *     the bus stops that are serviced at this bus.  Return an empty
   *     string if something go wrong.
   */ 
  public static CompletableFuture<String> getBusStopsServedBy(String serviceId) {
    return httpGet(BUS_SERVICE_API + serviceId);
  }

  /**
   * Return a Completable Future of string from CS2030 BUS API given a bus stop ID.
   * @param stopId Bus stop id
   * @return The Completable Future of string returned by the CS2030 BUS API service listing
   *     the bus services that stopped at this bus stop; an empty 
   *     string if the API query failed.
   */ 
  public static CompletableFuture<String> getBusServicesAt(String stopId) {
    return httpGet(BUS_STOP_API + stopId);
  }
}
