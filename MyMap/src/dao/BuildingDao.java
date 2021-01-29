package dao;

import domain.Building;

import java.io.*;
import java.util.ArrayList;

public class BuildingDao {
    public ArrayList<Building> getBuildings (){
        ArrayList<Building> buildings = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/dbfile/buildings.txt")));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try {
            if (reader != null) {
                while (true) {
                    String line = reader.readLine();
                    if(line==null){
                        reader.close();
                        break;
                    }
                    String[] strings = line.split(" ");
                    Building building = new Building(new Integer(strings[0]),new Integer(strings[1]),new Integer(strings[2]),
                            strings[3],strings[4],strings[5]);
                    buildings.add(building);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return buildings;
    }
}
