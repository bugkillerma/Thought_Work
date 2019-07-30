package com.throughtworks.train;

import com.throughtworks.train.exception.NoSuchRouteException;
import com.throughtworks.train.factory.BuilderType;
import com.throughtworks.train.factory.RouteBuilderFactory;
import com.throughtworks.train.graph.RouteMap;
import com.throughtworks.train.graph.TrainStation;
import com.throughtworks.train.inputParser.TextParser;
import com.throughtworks.train.service.RouteBuilder;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** @author:chenyang.ma */
public class TrainRouteCalculator {
  public static void main(final String[] args) {

    TrainStation a = new TrainStation("A");
    TrainStation b = new TrainStation("B");
    TrainStation c = new TrainStation("C");
    TrainStation d = new TrainStation("D");
    TrainStation e = new TrainStation("E");

    File inputFile = null;
    try {
      inputFile = new File(TrainRouteCalculator.class.getResource("/input.txt").toURI());
    } catch (URISyntaxException ex) {
      ex.printStackTrace();
    }
    TextParser parser = new TextParser();
    List<String> info = parser.parseInput(inputFile);
    String env = info.get(0);
    RouteMap testMap = new RouteMap();

    parser.buildStaionsRoads(testMap, env);

    // use a simple factory pattern to generate RouteBuilder
    RouteBuilder builder = RouteBuilderFactory.getRouteBuilder(BuilderType.DIRECTLY);
    List<TrainStation> stations = new ArrayList<TrainStation>(Arrays.asList(a, b, c));
    int distance = builder.getDistance(stations, testMap);
    System.out.println("#1 output : " + distance);

    stations = new ArrayList<TrainStation>(Arrays.asList(a, d));
    distance = builder.getDistance(stations, testMap);
    System.out.println("#2 output : " + distance);

    stations = new ArrayList<TrainStation>(Arrays.asList(a, d, c));
    distance = builder.getDistance(stations, testMap);
    System.out.println("#3 output : " + distance);

    stations = new ArrayList<TrainStation>(Arrays.asList(a, e, b, c, d));
    distance = builder.getDistance(stations, testMap);
    System.out.println("#4 output : " + distance);

    try {
      stations = new ArrayList<TrainStation>(Arrays.asList(a, e, d));
      distance = builder.getDistance(stations, testMap);
      System.out.println("#5 output : " + distance);
    } catch (NoSuchRouteException exc) {
      System.out.println(exc.toString());
    }

    builder = RouteBuilderFactory.getRouteBuilder(BuilderType.BELOECERAIN);
    stations = new ArrayList<TrainStation>(Arrays.asList(c, c));
    int result;
    result = builder.getNumRoutes(stations, testMap, 3);
    System.out.println("#6 output : " + result);

    builder = RouteBuilderFactory.getRouteBuilder(BuilderType.CERTAIN);
    stations = new ArrayList<TrainStation>(Arrays.asList(a, c));
    result = builder.getNumRoutes(stations, testMap, 4);
    System.out.println("#7 output : " + result);

    builder = RouteBuilderFactory.getRouteBuilder(BuilderType.SHORTEST);
    stations = new ArrayList<TrainStation>(Arrays.asList(a, c));
    distance = builder.getDistance(stations, testMap);
    System.out.println("#8 output : " + distance);

    stations = new ArrayList<TrainStation>(Arrays.asList(b, b));
    distance = builder.getDistance(stations, testMap);
    System.out.println("#9 output : " + distance);

    builder = RouteBuilderFactory.getRouteBuilder(BuilderType.CONDITION);
    stations = new ArrayList<TrainStation>(Arrays.asList(c, c));
    result = builder.getNumRoutes(stations, testMap, 30);
    System.out.println("#10 output : " + result);
  }
}
