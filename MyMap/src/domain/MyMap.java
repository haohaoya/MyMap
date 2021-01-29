package domain;

import java.util.ArrayList;
import java.util.HashMap;

public class MyMap {
    private ArrayList<Building> buildings;
    private ArrayList<Road> roads;
    private int[][] map;
    private HashMap<String,Integer> nameToBuildingIndex;

    public MyMap(ArrayList<Building> buildings, ArrayList<Road> roads){
        this.buildings = buildings;
        this.roads = roads;
        this.nameToBuildingIndex = new HashMap<>();
        for(int i = 0;i<buildings.size();i++){
            nameToBuildingIndex.put(buildings.get(i).getName(),i);
        }
        this.map = new int[buildings.size()][buildings.size()];
        for(int i = 0;i<buildings.size();i++){
            for(int j=0;j<buildings.size();j++){
                map[i][j] = 0;
            }
        }
        for(int i = 0;i<roads.size();i++){
            int index1 = nameToBuildingIndex.get(roads.get(i).getBuildingName1());
            int index2 = nameToBuildingIndex.get(roads.get(i).getBuildingName2());
            map[index1][index2] = roads.get(i).getLength();
            map[index2][index1] = roads.get(i).getLength();
        }
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public HashMap<String, Integer> getNameToBuildingIndex() {
        return nameToBuildingIndex;
    }

    public void setNameToBuildingIndex(HashMap<String, Integer> nameToBuildingIndex) {
        this.nameToBuildingIndex = nameToBuildingIndex;
    }
}
