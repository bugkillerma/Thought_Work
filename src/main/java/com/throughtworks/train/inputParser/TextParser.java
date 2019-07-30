package com.throughtworks.train.inputParser;

import com.throughtworks.train.graph.Railroad;
import com.throughtworks.train.graph.RouteMap;
import com.throughtworks.train.graph.TrainStation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * An input parser which can use text as input to generate a route map
 * @author "chenyang ma"
 *
 */
public class TextParser {

    public TextParser(){}

    public List<String> parseInput(File input){

        List<String> list = new ArrayList<String>();
        try
        {
            String encoding = "GBK";
            if (input.isFile() && input.exists())
            {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(input), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println("can not fine the file");
            }
        }
        catch (Exception e)
        {
            System.out.println("reading error");
            e.printStackTrace();
        }

        return list;

    }

    private void buildRoutingTable(RouteMap map){
        Hashtable<TrainStation, Set<Railroad>> table = new Hashtable<TrainStation, Set<Railroad>>();
        for(TrainStation station : map.getStations()){
            Set<Railroad> relatedRoads = map.getRoads().stream().filter(
                    t -> t.getDepartureStation().equals(station)).collect(Collectors.toSet());
            table.put(station, relatedRoads);
        }
        map.setRoutingTable(table);
    }

    public void buildStaionsRoads(RouteMap map, String env){

        env = env.replaceAll("\\s+","");
        TrainStation dep;
        TrainStation dest;
        Railroad road;

        List<String> roads = new ArrayList<>(Arrays.asList(env.split(",")));
        for(String input : roads){
            char first = input.charAt(0);
            char second = input.charAt(1);
            int third = Character.getNumericValue(input.charAt(2));
            dep = new TrainStation(Character.toString(first));
            map.getStations().add(dep);
            dest = new TrainStation(Character.toString(second));
            map.getStations().add(dest);
            road = new Railroad(dep, dest, third);
            map.getRoads().add(road);
        }
        buildRoutingTable(map);
    }
}
