package domain;

public class Road {
    private String buildingName1;
    private String buildingName2;
    private int length;

    public Road(String buildingName1,String buildingName2,int length){
        this.buildingName1 = buildingName1;
        this.buildingName2 = buildingName2;
        this.length = length;
    }

    public void setBuildingName1(String buildingName1) {
        this.buildingName1 = buildingName1;
    }

    public String getBuildingName1() {
        return buildingName1;
    }

    public void setBuildingName2(String buildingName2) {
        this.buildingName2 = buildingName2;
    }

    public String getBuildingName2() {
        return buildingName2;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
