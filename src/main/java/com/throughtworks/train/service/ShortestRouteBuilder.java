package com.throughtworks.train.service;

import com.throughtworks.train.exception.NoSuchStationException;
import com.throughtworks.train.graph.Railroad;
import com.throughtworks.train.graph.RouteMap;
import com.throughtworks.train.graph.TrainStation;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * RouteBuilder implementation. It just provides the basic methods to solve the given problem(8,9).
 *
 * @author "chenyang ma"
 */
public class ShortestRouteBuilder implements RouteBuilder {

  private RouteMap map;

  @Override
  public int getDistance(List<TrainStation> stations, RouteMap map) {
    TrainStation dep = stations.stream().findFirst().get();
    TrainStation dest = stations.stream().skip(stations.size() - 1).findFirst().get();
    this.map = map;
    return shortestRoute(dep, dest);
  }

  /*
   * Shortest route;
   * Wrapper for recursive function
   */
  public int shortestRoute(TrainStation start, TrainStation end) {

    return findShortestRoute(start, end, 0, 0);
  }

  /*
   * Finds the shortest route between two nodes
   */
  private int findShortestRoute(
      TrainStation start, TrainStation end, int distance, int shortestRoute) {

    // Check if start and end nodes exists in route table
    Hashtable<TrainStation, Set<Railroad>> routingTable = this.map.getRoutingTable();
    // Check if start and end nodes exists in route table
    if (routingTable.containsKey(start) && routingTable.containsKey(end)) {
      /*
       * If start node exists then traverse all possible
       * routes and for each, check if it is destination
       */
      start.setVisited(true);
      Set<Railroad> roads = routingTable.get(start);
      for (Railroad road : roads) {
        // If node not already visited, or is the destination, increment weight
        if (road.getDestination() == end || !road.getDestination().isVisited())
          distance += road.getDistance();

        /* If destination matches, we compare
         * weight of this route to shortest route
         * so far, and make appropriate switch
         */
        if (road.getDestination().equals(end)) {
          if (shortestRoute == 0 || distance < shortestRoute) shortestRoute = distance;
          start.setVisited(false);
          return shortestRoute; // Unvisit node and return shortest route
        }
        /* If destination does not match, and
         * destination node has not yet been visited,
         * we recursively traverse destination node
         */
        else if (!road.getDestination().isVisited()) {
          shortestRoute = findShortestRoute(road.getDestination(), end, distance, shortestRoute);
          // Decrement weight as we backtrack
          distance -= road.getDistance();
        }
        continue;
      }
    } else throw new NoSuchStationException();

    /*
     * Before exiting this recursive stack level,
     * we mark the start node as visited.
     */
    start.setVisited(false);
    return shortestRoute;
  }

  @Override
  public int getNumRoutes(List<TrainStation> stations, RouteMap map, int limitation) {
    return 1;
  }
}
