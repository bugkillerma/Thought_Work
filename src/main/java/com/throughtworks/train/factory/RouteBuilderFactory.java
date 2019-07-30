package com.throughtworks.train.factory;

import com.throughtworks.train.service.*;

public class RouteBuilderFactory {
  public static RouteBuilder getRouteBuilder(BuilderType type) {

    RouteBuilder builder = null;

    switch (type) {
      case DIRECTLY:
        {
          builder = new DirectlyRouteBuilder();
          break;
        }
      case SHORTEST:
        {
          builder = new ShortestRouteBuilder();
          break;
        }
      case CONDITION:
        {
          builder = new ConditionedRouteBuilder();
          break;
        }
      case BELOECERAIN:
        {
          builder = new BelowCertainStopsRouteBuilder();
          break;
        }
      case CERTAIN:
        {
          builder = new CertainStopsRouteBuilder();
          break;
        }
    }
    return builder;
  }
}
