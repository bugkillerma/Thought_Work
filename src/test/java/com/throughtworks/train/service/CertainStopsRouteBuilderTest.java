package com.throughtworks.train.service;

import com.throughtworks.train.graph.Railroad;
import com.throughtworks.train.graph.RouteMap;
import com.throughtworks.train.graph.TrainStation;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class CertainStopsRouteBuilderTest {

    RouteMap map = null;
    CertainStopsRouteBuilder builder = new CertainStopsRouteBuilder();
    TrainStation a;
    TrainStation b;
    TrainStation c;
    TrainStation d;
    TrainStation e;
    Railroad road1;
    Railroad road2;
    Railroad road3;
    Railroad road4;
    Railroad road5;
    Railroad road6;
    Railroad road7;
    Railroad road8;
    Railroad road9;

    Collection<Railroad> suitableRoad = new ArrayList<>();

    @Before
    public void initialRouteMap(){

        map = new RouteMap();
        a = new TrainStation("A");
        b = new TrainStation("B");
        c = new TrainStation("C");
        d = new TrainStation("D");
        e = new TrainStation("E");
        map.getStations().addAll(new ArrayList<TrainStation>(Arrays.asList(a,b,c,d,e)));

        road1 = new Railroad(a,b,5);
        road2 = new Railroad(b,c,4);
        road3 = new Railroad(c,d,8);
        road4 = new Railroad(d,c,8);
        road5 = new Railroad(a,d,5);
        road6 = new Railroad(a,e,7);
        road7 = new Railroad(d,e,6);
        road8 = new Railroad(e,b,3);
        road9 = new Railroad(c,e,2);

        map.getRoads().addAll(new ArrayList<Railroad>(Arrays.asList(road1,road2,road3,road4,road5,road6,road7,road8,road9)));
        map.getRoads().addAll(new ArrayList<Railroad>(Arrays.asList(road1,road2,road3,road4,road5,road6,road7,road8,road9)));
        map.getRoutingTable().put(a, new HashSet<>(Arrays.asList(road1,road5,road6)));
        map.getRoutingTable().put(b, new HashSet<>(Arrays.asList(road2)));
        map.getRoutingTable().put(c, new HashSet<>(Arrays.asList(road3,road9)));
        map.getRoutingTable().put(e, new HashSet<>(Arrays.asList(road8)));
        map.getRoutingTable().put(d, new HashSet<>(Arrays.asList(road4,road7)));
    }

    @Test
    public void testFindSuitableRoute(){
        List<TrainStation> stations = new ArrayList<TrainStation>(Arrays.asList(a,c));
        int result = builder.getNumRoutes(stations,map,4);
        assertEquals(3,result);
    }
}
