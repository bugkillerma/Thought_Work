package com.throughtworks.train.service;

import com.throughtworks.train.exception.NoSuchStationException;
import com.throughtworks.train.graph.Railroad;
import com.throughtworks.train.graph.RouteMap;
import com.throughtworks.train.graph.TrainStation;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * RouteBuilder implementation. It just provides the basic methods to solve the given problem(6).
 *
 * @author "chenyang ma"
 */
public class BelowCertainStopsRouteBuilder implements RouteBuilder {

  private int limitation;
  private RouteMap map;
  private int result = 0;

  private void findSuitableRoute(List<TrainStation> stations, RouteMap map) {

    TrainStation dep = stations.stream().findFirst().get();
    TrainStation dest = stations.stream().skip(stations.size() - 1).findFirst().get();
    this.map = map;
    result = findRoutes(dep, dest, 0, limitation);
  }

  /*
   * Finds number of stops from start to end,
   * with a maximum of maxStops and the depth
   * limit.
   */
  private int findRoutes(TrainStation start, TrainStation end, int depth, int maxStops) {

    int routes = 0;
    Hashtable<TrainStation, Set<Railroad>> routingTable = this.map.getRoutingTable();
    // Check if start and end nodes exists in route table
    if (routingTable.containsKey(start) && routingTable.containsKey(end)) {
      /*
       * If start node exists then traverse all possible
       * routes and for each, check if it is destination
       * If destination, and number of stops within
       * allowed limits, count it as possible route.
       */
      depth++;
      if (depth > maxStops) // Check if depth level is within limits
      return 0;
      start.setVisited(true); // Mark start node as visited
      Set<Railroad> roads = routingTable.get(start);
      for (Railroad road : roads) {
        /* If destination matches, we increment route
         * count, then continue to next node at same depth
         */
        if (road.getDestination().equals(end)) {
          routes++;
        }
        /* If destination does not match, and
         * destination node has not yet been visited,
         * we recursively traverse destination node
         */
        else if (!road.getDestination().isVisited()) {
          routes += findRoutes(road.getDestination(), end, depth, maxStops);
          depth--;
        }
      }
    } else throw new NoSuchStationException();

    /*
     * Before exiting this recursive stack level,
     * mark the start node as visited.
     */
    start.setVisited(false);
    return routes;
  }

  @Override
  public int getDistance(List<TrainStation> stations, RouteMap map) {
    return 0;
  }

  @Override
  public int getNumRoutes(List<TrainStation> stations, RouteMap map, int limitation) {
    this.limitation = limitation;
    findSuitableRoute(stations, map);
    return result;
  }
}
