package test;

import dao.BuildingDao;
import domain.Building;

import java.util.ArrayList;

public class BuildingDaoTest {
    public static void main(String args[]){
        ArrayList<Building> buildings = new BuildingDao().getBuildings();
        for(Building building : buildings){
            System.out.println(building.getName()+"--"+building.getPosX()+"--"+building.getInformation()+"--"+building.getImagePath());
        }
    }
}
