package com.throughtworks.train.graph;

import com.throughtworks.train.exception.NoSuchStationException;

import java.util.*;

/**
 * It just provides the basic methods to solve
 * the given problem. A class stores the necessary information
 * for the problem
 *
 * @author "chenyang ma"
 *
 */
public class RouteMap {

    private Set<TrainStation> stations = new HashSet<>();
    private Set<Railroad> roads = new HashSet<>();
    private Hashtable<TrainStation, Set<Railroad>> routingTable = new Hashtable<TrainStation, Set<Railroad>>();

    private void assertStation(final TrainStation station) {
        if(!stations.contains(station)){
            throw new NoSuchStationException();
        }
    }

    public Set<Railroad> getRoads() {
        return roads;
    }

    public void setStations(Set<TrainStation> stations) {
        this.stations = stations;
    }

    public void setRoads(Set<Railroad> roads) {
        this.roads = roads;
    }

    public Set<TrainStation> getStations() {
        return stations;
    }

    public Hashtable<TrainStation, Set<Railroad>> getRoutingTable() {
        return routingTable;
    }

    public void setRoutingTable(Hashtable<TrainStation, Set<Railroad>> routingTable) {
        this.routingTable = routingTable;
    }
}
