package com.throughtworks.train.graph;

/**
 * as node in Graph
 * @author "chenyang ma"
 *
 */
public class TrainStation {

    private String stationName;
    private boolean visited;

    public TrainStation(String name) {
        this.stationName = name;
        this.visited = false;
    }

    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (obj.getClass() != getClass() || obj == null ) {
            return false;
        }

        TrainStation rhsTown = (TrainStation)obj;
        return this.stationName.equals(rhsTown.stationName);
    }

    public int hashCode() {
        if(this.stationName == null) return 0;
        return this.stationName.hashCode();
    }

    public String toString() {
        return this.stationName.toString();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}

