package dao;

import domain.Building;
import domain.Road;

import java.io.*;
import java.util.ArrayList;

public class RoadDao {

    public ArrayList<Road> getRoads (){
        ArrayList<Road> roads = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/dbfile/roads.txt")));
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
                    Road road = new Road(strings[0],strings[1],new Integer(strings[2]));
                    roads.add(road);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return roads;
    }
}
