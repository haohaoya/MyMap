package domain;


public class Building {
    //是中转对象为1   不是为0
    private int isTransferOrNot;
    private int posX;
    private int posY;
    private String name;
    private String information;
    private String imagePath;


    public Building(int isTransferOrNot, int posX, int posY,String name,String information,String imagePath){
        this.isTransferOrNot = isTransferOrNot;
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        this.information = information;
        this.imagePath = imagePath;
    }

    public void setIsTransferOrNot(int isTransferOrNot) {
        this.isTransferOrNot = isTransferOrNot;
    }

    public int getIsTransferOrNot() {
        return isTransferOrNot;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosY() {
        return posY;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setInformation(String information) {
        this.information = information;
    }

    public String getInformation() {
        return information;
    }


    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
