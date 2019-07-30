package com.throughtworks.train.graph;

/**
 * The edge between two connected stations
 *
 * @author "chenyang ma"
 *
 */
public class Railroad {

    private TrainStation departureStation;
    private TrainStation destination;
    private int distance;

    public Railroad(final TrainStation departureStation, final TrainStation destination, final int distance) {
        this.departureStation = departureStation;
        this.destination = destination;
        this.distance = distance;
    }

    public TrainStation getDepartureStation() {
        return departureStation;
    }

    public TrainStation getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Railroad road = (Railroad) obj;
        if (destination == null) {
            if (road.destination != null) {
                return false;
            }
        } else if(!destination.equals(road.destination)) {
            return false;
        }
        if (departureStation == null) {
            if (road.departureStation != null) {
                return false;
            }
        } else if(!departureStation.equals(road.departureStation)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return departureStation.toString() + ";" + destination.toString();
    }
}
