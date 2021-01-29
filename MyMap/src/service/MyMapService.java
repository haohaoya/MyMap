package service;

import dao.MapDao;
import domain.Building;
import domain.MyMap;
import domain.Road;

import java.util.ArrayList;
import java.util.HashMap;

public class MyMapService {
    private MapDao mapDao = new MapDao();

    public MyMap getMyMap(){
        MyMap map = mapDao.getMyMap();
        return map;
    }

    public void upDataMap(ArrayList<Building> buildings, ArrayList<Road> roads){
        mapDao.upDataMyMap(buildings,roads);
    }
}
