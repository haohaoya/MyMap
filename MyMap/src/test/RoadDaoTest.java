package test;

import dao.RoadDao;
import domain.Road;

import java.util.ArrayList;

public class RoadDaoTest {

    public static void main(String args[]){
        ArrayList<Road> roads = new RoadDao().getRoads();
        for(Road road : roads){
            System.out.println(road.getBuildingName1()+"--"+road.getBuildingName2()+"--"+road.getLength());
        }
    }
}
