package com.throughtworks.train.service;

import com.throughtworks.train.graph.RouteMap;
import com.throughtworks.train.graph.TrainStation;

import java.util.List;

/**
 * General interface that provide a method to calculate the result
 *
 * @author "chenyang ma"
 *
 */
public interface RouteBuilder {

    int getDistance(List<TrainStation> stations, RouteMap map);
    int getNumRoutes(List<TrainStation> stations, RouteMap map, int limitation);
}
