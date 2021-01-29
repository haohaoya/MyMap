package dao;

import domain.Building;
import domain.MyMap;
import domain.Road;

import java.io.*;
import java.util.ArrayList;

public class MapDao {
    public MyMap getMyMap(){
        ArrayList<Building> buildings = new BuildingDao().getBuildings();
        ArrayList<Road> roads = new RoadDao().getRoads();
        MyMap map = new MyMap(buildings,roads);
        return map;
    }
    public void upDataMyMap(ArrayList<Building> buildings, ArrayList<Road> roads){

        BufferedWriter bbw = null;
        BufferedWriter rbw = null;

        try {
            bbw = new BufferedWriter(new FileWriter("src/dbfile/buildings.txt"));
            rbw = new BufferedWriter(new FileWriter("src/dbfile/roads.txt"));
            for(Building building : buildings){
                bbw.write(building.getIsTransferOrNot()+" "+building.getPosX()+" "+building.getPosY()+" "+
                        building.getName()+" "+building.getInformation()+" "+building.getImagePath()+"\r\n");
            }
            for(Road road : roads){
                rbw.write(road.getBuildingName1()+" "+road.getBuildingName2()+" "+road.getLength()+"\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bbw != null) {
                    bbw.close();
                }
                if (rbw != null) {
                    rbw.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
