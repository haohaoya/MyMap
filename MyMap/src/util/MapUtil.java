package util;

import domain.Building;
import domain.MyMap;
import domain.Road;
import service.MyMapService;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MapUtil {
    public static BufferedImage getO(){
        BufferedImage image = new BufferedImage(24,24,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2 = image.createGraphics();
        image = g2.getDeviceConfiguration().createCompatibleImage(24,24,Transparency.TRANSLUCENT);
        Graphics g = image.getGraphics();
        g.setColor(new Color(0x4F81CB));
        g.fillArc(0,0,24,24,0,360);
        return image;
    }

    public static BufferedImage getTImage(){
        BufferedImage image = new BufferedImage(600,600,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2 = image.createGraphics();
        image = g2.getDeviceConfiguration().createCompatibleImage(600,600,Transparency.TRANSLUCENT);
        Graphics g = image.getGraphics();
        MyMapService service = new MyMapService();
        MyMap map = service.getMyMap();
        ArrayList<Building> buildings = map.getBuildings();
        for(Building building : buildings){
            int posX = building.getPosX()-10;
            int posY = building.getPosY()-10;
            String name = building.getName();
            if(building.getIsTransferOrNot()==1) {
                g.setColor(new Color(0xCA385E));
                g.setFont(new Font("楷体", Font.PLAIN, 13));
                g.drawString(name, posX - 30 - name.length() / 2, posY + 29);
            }
        }


        return image;
    }

    public static BufferedImage getBestMapImage(ArrayList<Road> roads){
        BufferedImage image = new BufferedImage(600,600,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2 = image.createGraphics();
        image = g2.getDeviceConfiguration().createCompatibleImage(600,600,Transparency.TRANSLUCENT);
        Graphics g = image.getGraphics();
        MyMapService service = new MyMapService();
        MyMap map = service.getMyMap();
        ArrayList<Building> buildings = map.getBuildings();
        HashMap<String,Integer> nameToBuildingIndex = map.getNameToBuildingIndex();
        int[][] m = new int[buildings.size()][buildings.size()];
        //补全为完全图
        for(int i=0;i<buildings.size();i++){
            for(int j = 0;j<buildings.size();j++){
                if(i==j){
                    //自己到自己，最远
                    m[i][j] = 2147483647;
                }else{
                    Building building1 = buildings.get(i);
                    Building building2 = buildings.get(j);
                    m[i][j] =(int)Math.sqrt(Math.pow(building1.getPosX()-building2.getPosX(),2)+Math.pow(building1.getPosY()-building2.getPosY(),2));
                }
            }
        }

        int[] adjvex = new int[buildings.size()];
        int[] lowcost = new int[buildings.size()];
        //生成最小生成树
        lowcost[0] = 0;
        for(int i = 1;i<buildings.size();i++){
            adjvex[i] = 0;
            lowcost[i] = m[0][i];
        }
        for(int i = 0;i<buildings.size()-1;i++){
            int min = 2147483647;
            int mindex = 0;
            for(int k = 0;k<buildings.size();k++){
                if(lowcost[k]!=0 && lowcost[k]<min){
                    mindex=k;
                    min = lowcost[k];
                }
            }

            lowcost[mindex] = 0;

            for(int k = 0;k<buildings.size();k++){
                if(k!=mindex && m[mindex][k]<lowcost[k]){
                    lowcost[k] = m[mindex][k];
                    adjvex[k] = mindex;
                }
            }

        }

        for(int i = 1;i<buildings.size();i++){
            int u = adjvex[i];

            Road road = new Road(buildings.get(i).getName(),buildings.get(u).getName(),m[i][u]);
            roads.add(road);
        }


        //画
        g.setColor(new Color(0x99FFF8));
        ((Graphics2D) g).setStroke(new BasicStroke(11f));
        for(Road road : roads){
            int index1 = nameToBuildingIndex.get(road.getBuildingName1());
            int index2 = nameToBuildingIndex.get(road.getBuildingName2());
            Building building1 = buildings.get(index1);
            Building building2 = buildings.get(index2);
            int posX1 = building1.getPosX();
            int posY1 = building1.getPosY();
            int posX2 = building2.getPosX();
            int posY2 = building2.getPosY();
            g.drawLine(posX1,posY1,posX2,posY2);

        }
        for(Building building : buildings){
            int posX = building.getPosX()-10;
            int posY = building.getPosY()-10;
            if(building.getIsTransferOrNot()==0){
                g.setColor(new Color(0x5BAB95));
                g.fillArc(posX,posY,20,20,0,360);
            }else{
                g.setColor(new Color(0x99FFF8));
                g.fillArc(posX,posY,18,18,0,360);

            }
            String name = building.getName();
            if(building.getIsTransferOrNot()==0) {
                g.setColor(new Color(0x004D2C));
                g.setFont(new Font("楷体", Font.PLAIN, 20));
                g.drawString(name, posX - 20 - name.length() / 2, posY + 32);
            }
        }
        return image;
    }

    public static BufferedImage getMapImage(){
        BufferedImage image = new BufferedImage(600,600,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2 = image.createGraphics();
        image = g2.getDeviceConfiguration().createCompatibleImage(600,600,Transparency.TRANSLUCENT);
        Graphics g = image.getGraphics();
        //g.setColor(new Color(0x527FBF7D, true));
        //g.fillRect(0,0,600,600);
        MyMapService service = new MyMapService();
        MyMap map = service.getMyMap();
        ArrayList<Road> roads = map.getRoads();
        ArrayList<Building> buildings = map.getBuildings();
        HashMap<String,Integer> nameToBuildingIndex = map.getNameToBuildingIndex();
        g.setColor(new Color(0x99FFF8));
        ((Graphics2D) g).setStroke(new BasicStroke(11f));
        for(Road road : roads){
            int index1 = nameToBuildingIndex.get(road.getBuildingName1());
            int index2 = nameToBuildingIndex.get(road.getBuildingName2());
            Building building1 = buildings.get(index1);
            Building building2 = buildings.get(index2);
            int posX1 = building1.getPosX();
            int posY1 = building1.getPosY();
            int posX2 = building2.getPosX();
            int posY2 = building2.getPosY();
            g.drawLine(posX1,posY1,posX2,posY2);

        }
        for(Building building : buildings){
            int posX = building.getPosX()-10;
            int posY = building.getPosY()-10;
            if(building.getIsTransferOrNot()==0){
                g.setColor(new Color(0x5BAB95));
                g.fillArc(posX,posY,20,20,0,360);
            }else{
                g.setColor(new Color(0x99FFF8));
                g.fillArc(posX,posY,18,18,0,360);

            }
            String name = building.getName();
            if(building.getIsTransferOrNot()==0) {
                g.setColor(new Color(0x004D2C));
                g.setFont(new Font("楷体", Font.PLAIN, 20));
                g.drawString(name, posX - 20 - name.length() / 2, posY + 32);
            }
        }
        return image;
    }

    public static BufferedImage getRoad(MyMap map,String startName,String endName){
        BufferedImage image = new BufferedImage(600,600,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2 = image.createGraphics();
        image = g2.getDeviceConfiguration().createCompatibleImage(600,600,Transparency.TRANSLUCENT);
        Graphics g = image.getGraphics();
        ArrayList<Building> buildings = map.getBuildings();
        HashMap<String,Integer> nameToBuildingIndex = map.getNameToBuildingIndex();
        Queue<Integer> queue = new LinkedList<Integer>();
        int buildingnum = 0;
        int roadlength = 0;
        int[][] m = map.getMap();
        int[] pre = new int[buildings.size()];
        for(int i = 0;i<pre.length;i++){
            pre[i] = -1;
        }
        queue.add(nameToBuildingIndex.get(startName));
        pre[nameToBuildingIndex.get(startName)] = -2;

        while(!queue.isEmpty()){
            int tmp = queue.poll();
            if(intoQueue(queue,m,pre,tmp,buildings,nameToBuildingIndex.get(endName))==1){
                break;
            }
        }

        if(pre[nameToBuildingIndex.get(endName)]==-1){
            return null;
        }

        g.setColor(new Color(0x5CE775));
        ((Graphics2D) g).setStroke(new BasicStroke(11f));
        int tmp = nameToBuildingIndex.get(endName);
        while(true){
            int index1 = tmp;
            int index2 = pre[tmp];
            tmp = index2;
            if(tmp == -2){
                index2 = nameToBuildingIndex.get(startName);
            }
            Building building1 = buildings.get(index1);
            Building building2 = buildings.get(index2);
            g.drawLine(building1.getPosX(),building1.getPosY(),building2.getPosX(),building2.getPosY());
            roadlength += Math.sqrt(Math.pow(building1.getPosX()-building2.getPosX(),2)+Math.pow(building1.getPosY()-building2.getPosY(),2));
            if(tmp == -2){
                break;
            }
        }
        tmp = nameToBuildingIndex.get(endName);
        while(true){
            int index = tmp;
            tmp = pre[tmp];
            Building building = buildings.get(index);
            if(building.getIsTransferOrNot()==0){
                g.setColor(new Color(0x5CCB9C));
                g.fillArc(building.getPosX()-10,building.getPosY()-10,20,20,0,360);
                buildingnum++;
            }else{
                g.setColor(new Color(0x5CE775));
                g.fillArc(building.getPosX()-10,building.getPosY()-10,18,18,0,360);
            }
            String name = building.getName();
            if(building.getIsTransferOrNot()==0) {
                g.setColor(new Color(0xD473B3));
                g.setFont(new Font("楷体", Font.PLAIN, 20));
                g.drawString(name, building.getPosX() - 30 - name.length() / 2, building.getPosY() + 22);
            }
            if(tmp == -2){
                break;
            }
        }
        g.setFont(new Font("楷体",Font.BOLD,20));
        g.setColor(new Color(0x4B9680));
        g.drawString("中转最少路程：",10,575);
        g.drawString("总长度约:"+roadlength+"   中转次数:"+(buildingnum-2),5,595);
        return image;
    }

    //找到所有与该点相连的点，标记pre,加入队列
    //如果是中转点继续往下找
    private static int intoQueue(Queue<Integer> queue,int[][] m,int[] pre,int tmp,ArrayList<Building> buildings,int end){
        for(int i = 0;i<pre.length;i++){
            if(m[tmp][i]!=0 && pre[i]==-1){
                if(buildings.get(i).getIsTransferOrNot()==1){
                    //是中专点
                    pre[i] = tmp;
                    intoQueue(queue,m,pre,i,buildings,end);
                }else{
                    //不是中专点
                    pre[i] = tmp;
                    if(i==end){
                        return 1;
                    }
                    queue.add(i);
                }
            }
        }
        return 0;
    }

    public static BufferedImage getShortRoad(MyMap map,String startName,String endName){
        BufferedImage image = new BufferedImage(600,600,BufferedImage.TYPE_INT_BGR);
        Graphics2D g2 = image.createGraphics();
        image = g2.getDeviceConfiguration().createCompatibleImage(600,600,Transparency.TRANSLUCENT);
        Graphics g = image.getGraphics();
        ArrayList<Road> roads = map.getRoads();
        ArrayList<Building> buildings = map.getBuildings();
        HashMap<String,Integer> nameToBuildingIndex = map.getNameToBuildingIndex();
        int buildingnum = 0;
        int roadlength = 0;
        int[][] m = map.getMap();
        int[] pre = new int[buildings.size()];
        int[] dist = new int[buildings.size()];
        int[] flag = new int[buildings.size()];
        for(int i = 0;i<pre.length;i++){
            pre[i] = nameToBuildingIndex.get(startName);
            dist[i] = m[nameToBuildingIndex.get(startName)][i];
            if(dist[i]==0){
                dist[i] = 2147483647;
            }
            flag[i] = 0;
        }

        dist[nameToBuildingIndex.get(startName)] = 0;
        pre[nameToBuildingIndex.get(startName)] = -2;
        flag[nameToBuildingIndex.get(startName)] = 1;


        int min;
        int k = 0;
        for(int i = 0;i<pre.length-1;i++){
            min = 2147483647;
            for(int j = 0; j < pre.length; j++){
                if(flag[j]==0 && dist[j] < min){
                    min = dist[j];
                    k = j;
                }
            }

            dist[k] = min;
            flag[k] = 1;

            //更改最短距离
            for(int j=0; j < pre.length; j++){
                int temp = dist[k] + m[k][j];
                if(m[k][j]==0){
                    temp = 2147483647;
                }
                if(flag[j] == 0 && temp < dist[j]){
                    dist[j] = temp;
                    pre[j] = k;
                }
            }
        }

        if(dist[nameToBuildingIndex.get(endName)]==0){
            return null;
        }

        g.setColor(new Color(0x5CE775));
        ((Graphics2D) g).setStroke(new BasicStroke(11f));
        int tmp = nameToBuildingIndex.get(endName);
        while(true){
            int index1 = tmp;
            int index2 = pre[tmp];
            tmp = index2;
            if(tmp == -2){
                index2 = nameToBuildingIndex.get(startName);
            }
            Building building1 = buildings.get(index1);
            Building building2 = buildings.get(index2);
            g.drawLine(building1.getPosX(),building1.getPosY(),building2.getPosX(),building2.getPosY());
            roadlength += Math.sqrt(Math.pow(building1.getPosX()-building2.getPosX(),2)+Math.pow(building1.getPosY()-building2.getPosY(),2));
            if(tmp == -2){
                break;
            }
        }
        tmp = nameToBuildingIndex.get(endName);
        while(true){
            int index = tmp;
            tmp = pre[tmp];
            Building building = buildings.get(index);
            if(building.getIsTransferOrNot()==0){
                g.setColor(new Color(0x5CCB9C));
                g.fillArc(building.getPosX()-10,building.getPosY()-10,20,20,0,360);
                buildingnum++;
            }else{
                g.setColor(new Color(0x5CE775));
                g.fillArc(building.getPosX()-10,building.getPosY()-10,18,18,0,360);
            }
            String name = building.getName();
            if(building.getIsTransferOrNot()==0) {
                g.setColor(new Color(0xD473B3));
                g.setFont(new Font("楷体", Font.PLAIN, 20));
                g.drawString(name, building.getPosX() - 30 - name.length() / 2, building.getPosY() + 22);
            }
            if(tmp == -2){
                break;
            }
        }
        g.setFont(new Font("楷体",Font.BOLD,20));
        g.setColor(new Color(0x4B9680));
        g.drawString("长度最短路程：",10,575);
        g.drawString("总长度约:"+roadlength+"   中转次数:"+(buildingnum-2),5,595);
        return image;
    }



}
