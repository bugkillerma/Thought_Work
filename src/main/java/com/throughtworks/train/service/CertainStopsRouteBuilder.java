package com.throughtworks.train.service;

import com.throughtworks.train.exception.NoSuchStationException;
import com.throughtworks.train.graph.Railroad;
import com.throughtworks.train.graph.RouteMap;
import com.throughtworks.train.graph.TrainStation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * RouteBuilder implementation. It just provides the basic methods to solve the given problem(7).
 *
 * @author "chenyang ma"
 */
public class CertainStopsRouteBuilder implements RouteBuilder {

  private int stops;
  private RouteMap map;
  private int result = 0;

  private void findSuitableRoute(List<TrainStation> stations, RouteMap map) {

    TrainStation dep = stations.stream().findFirst().get();
    TrainStation dest = stations.stream().skip(stations.size() - 1).findFirst().get();
    this.map = map;
    result = findRoutes(dep, dest);
  }

  @Override
  public int getNumRoutes(List<TrainStation> stations, RouteMap map, int stops) {
    this.stops = stops;
    findSuitableRoute(stations, map);
    return result;
  }

  /*
   * Finds number of stops from start to end,
   * with a maximum of maxStops and the depth
   * limit.
   */
  private int findRoutes(TrainStation start, TrainStation end) {

    int routes;
    Hashtable<TrainStation, Set<Railroad>> routingTable = this.map.getRoutingTable();
    // Check if start and end nodes exists in route table
    if (routingTable.containsKey(start) && routingTable.containsKey(end)) {
      List<TrainStation> temp;
      List<TrainStation> level;
      HashMap<Integer, List<TrainStation>> levelStations = new HashMap<>();
      Set<Railroad> tempRoads;
      levelStations.put(0, Arrays.asList(start));
      for (int i = 0; i < stops; i++) {
        level = new ArrayList<>();
        for (TrainStation dep : levelStations.get(i)) {
          temp = new ArrayList<>();
          tempRoads = routingTable.get(dep);
          temp = tempRoads.stream().map(p -> p.getDestination()).collect(Collectors.toList());
          level.addAll(temp);
        }
        levelStations.put(i + 1, level);
      }
      List<TrainStation> result = levelStations.get(stops);
      routes = (int) result.stream().filter(t -> t.equals(end)).count();
    } else throw new NoSuchStationException();

    return routes;
  }

  @Override
  public int getDistance(List<TrainStation> stations, RouteMap map) {
    return 0;
  }
}
