package com.throughtworks.train.service;

import com.throughtworks.train.exception.NoSuchRouteException;
import com.throughtworks.train.graph.Railroad;
import com.throughtworks.train.graph.RouteMap;
import com.throughtworks.train.graph.TrainStation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * RouteBuilder implementation. It just provides the basic methods to solve the given problem(1-5).
 *
 * @author "chenyang ma"
 */
public class DirectlyRouteBuilder implements RouteBuilder {

  private Collection<Railroad> suitableRoad;

  /*
   * Finds the route between two nodes
   */
  private void findSuitableRoute(List<TrainStation> stations, RouteMap map)
      throws NoSuchRouteException {

    Collection<Railroad> suitableRoad = new ArrayList<>();

    if (stations.size() < 2) {
      throw new NoSuchRouteException();
    }

    Set<Railroad> roadList = map.getRoads();

    for (int index = 0; index < stations.size() - 1; index++) {

      boolean noSuchRoad = true;
      for (Railroad road : roadList) {
        if (road.getDepartureStation().equals(stations.get(index))
            && road.getDestination().equals(stations.get(index + 1))) {
          suitableRoad.add(road);
          noSuchRoad = false;
        }
      }

      if (noSuchRoad) throw new NoSuchRouteException();
    }
    this.suitableRoad = suitableRoad;
  }

  @Override
  public int getDistance(List<TrainStation> stations, RouteMap map) {
    findSuitableRoute(stations, map);
    return suitableRoad.stream().mapToInt(t -> t.getDistance()).sum();
  }

  @Override
  public int getNumRoutes(List<TrainStation> stations, RouteMap map, int limitation) {
    return 1;
  }
}
