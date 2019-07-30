package com.throughtworks.train.service;

import com.throughtworks.train.exception.NoSuchStationException;
import com.throughtworks.train.graph.Railroad;
import com.throughtworks.train.graph.RouteMap;
import com.throughtworks.train.graph.TrainStation;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * RouteBuilder implementation. It just provides the basic methods to solve the given problem(10).
 *
 * @author "chenyang ma"
 */
public class ConditionedRouteBuilder implements RouteBuilder {

  private RouteMap map;
  private int maxDistance = 0;

  private int findNumRoutesWithin(
      TrainStation start, TrainStation end, int distance, int maxDistance) {
    int routes = 0;
    // Check if start and end nodes exists in route table
    Hashtable<TrainStation, Set<Railroad>> routingTable = this.map.getRoutingTable();
    // Check if start and end nodes exists in route table
    if (routingTable.containsKey(start) && routingTable.containsKey(end)) {
      /*
       * If start node exists then traverse all possible
       * routes and for each, check if it is destination
       */
      Set<Railroad> roads = routingTable.get(start);
      for (Railroad road : roads) {
        distance += road.getDistance();
        /* If distance is under max, keep traversing
         * even if match is found until distance is > max
         */
        if (distance <= maxDistance) {
          if (road.getDestination().equals(end)) {
            routes++;
            routes += findNumRoutesWithin(road.getDestination(), end, distance, maxDistance);

          } else {
            routes += findNumRoutesWithin(road.getDestination(), end, distance, maxDistance);
            distance -= road.getDistance(); // Decrement weight as we backtrack
          }
        } else distance -= road.getDistance();
      }
    } else throw new NoSuchStationException();

    return routes;
  }

  @Override
  public int getNumRoutes(List<TrainStation> stations, RouteMap map, int limitation) {
    TrainStation dep = stations.stream().findFirst().get();
    TrainStation dest = stations.stream().skip(stations.size() - 1).findFirst().get();
    this.maxDistance = limitation;
    this.map = map;
    return findNumRoutesWithin(dep, dest, 0, maxDistance);
  }

  @Override
  public int getDistance(List<TrainStation> stations, RouteMap map) {
    return 0;
  }
}
