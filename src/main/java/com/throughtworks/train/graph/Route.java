package com.throughtworks.train.graph;

import java.util.*;

/**
 * In this context, a path
 * is defined as a series of consecutive edges.
 *
 * @author "chenyang ma"
 *
 */
public class Route {

    private List<Railroad> roadList;
    private int distance = 0;



    public void addRailroad(final Railroad road) {

        if (!edgeIsConsecutive(road)) {
            throw new IllegalArgumentException("The road: " + road + " is not consecutive to the existing path");
        }
        roadList.add(road);
        distance += road.getDistance();
    }

    private boolean edgeIsConsecutive(final Railroad edge) {
        final TrainStation lastNode = getDestination();
        if (lastNode != null && !lastNode.equals(edge.getDepartureStation())) {
            return false;
        }
        return true;
    }

    public int getPathDistance() {
        return distance;
    }

    public TrainStation getDestination() {
        TrainStation destination = null;
        if (!roadList.isEmpty()) {
            destination = roadList.get(roadList.size() - 1).getDestination();
        }
        return destination;
    }

    public List<Railroad> getRoadList() {
        return roadList;
    }

    public String toString() {
        return "Route distance is " + distance + ", roadList is" + roadList;
    }

    public int compareTo(final Route otherRoute) {
        return this.distance - otherRoute.distance;
    }

    public void setRoadList(List<Railroad> roadList) {
        this.roadList = roadList;
    }
}

