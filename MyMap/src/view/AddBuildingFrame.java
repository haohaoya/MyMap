package view;

import domain.Building;
import domain.MyMap;
import domain.Road;
import javafx.scene.control.RadioButton;
import service.MyMapService;
import util.BaseFrame;
import util.MapUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class AddBuildingFrame extends BaseFrame {

    //创建一个面板
    private JPanel mainPanel = new JPanel();

    //地图面板，介绍面板
    private JPanel mapPanel = new JPanel();
    private JPanel messagePanel = new JPanel();

    private ImageIcon o = new ImageIcon(MapUtil.getO());
    private JLabel oLabel = new JLabel(o);
    private ImageIcon map = new ImageIcon(MapUtil.getMapImage());
    private JLabel mapLabel = new JLabel(map);
    private JLabel mapbg = new JLabel(new ImageIcon("src/image/mapbg.jpg"));

    private JLabel mess = new JLabel("请在地图中点击选择位置");

    private JLabel type = new JLabel("是否为中转类型:");
    private int isTransferOrNot = 0;
    private int posX=-1;
    private int posY=-1;
    private JLabel typeLabel = new JLabel(new ImageIcon("src/image/no.jpg"));

    private JLabel name = new JLabel("建筑名称:");
    private JTextField nameField = new JTextField();

    private JLabel imagepath = new JLabel("图片路径:");
    private JTextField imagepathField = new JTextField();

    private JLabel informationLabel = new JLabel("建筑信息:");
    private JTextArea information = new JTextArea();
    private JScrollPane scroll = new JScrollPane(information);

    private JButton loadButton = new JButton("保 存 建 筑");
    private JButton backToMenuButton = new JButton("返 回 菜 单");

    //主画板背景
    private ImageIcon imageIcon = new ImageIcon("src/image/bg.jpg");
    private JLabel background = new JLabel(imageIcon);

    public AddBuildingFrame(){}
    public AddBuildingFrame(String title){
        super(title);
        this.init();
    }

    protected void setFontAndSoOn() {
        //设置布局管理方式
        mainPanel.setLayout(null);
        mapPanel.setLayout(null);
        messagePanel.setLayout(null);
        //设置背景图片位置和大小
        background.setBounds(0, 0, 1100,790);
        mapPanel.setLayout(null);
        mapPanel.setBounds(60,60,604,604);
        mapPanel.setBorder(BorderFactory.createLineBorder(new Color(0x5BAB95),3));

        oLabel.setBounds(50,50,30,30);
        oLabel.setVisible(false);
        mapbg.setBounds(2,2,600,600);
        mapLabel.setBounds(2,2,600,600);

        mess.setBounds(20,28,300,20);
        mess.setFont(new Font("楷体",Font.PLAIN,19));
        mess.setForeground(new Color(0x6C5892));

        name.setBounds(25,60,100,20);
        name.setFont(new Font("楷体",Font.PLAIN,19));
        nameField.setBounds(25,80,200,30);
        nameField.setFont(new Font("楷体",Font.BOLD,20));

        imagepath.setBounds(25,140,100,20);
        imagepath.setFont(new Font("楷体",Font.PLAIN,19));
        imagepathField.setBounds(25,160,200,30);
        imagepathField.setFont(new Font("楷体",Font.BOLD,20));

        type.setBounds(25,225,200,20);
        type.setFont(new Font("楷体",Font.PLAIN,19));
        typeLabel.setBounds(170,220,25,25);

        informationLabel.setBounds(25,270,100,20);
        informationLabel.setFont(new Font("楷体",Font.PLAIN,19));
        information.setFont(new Font("楷体",Font.BOLD,30));
        information.setOpaque(false);
        information.setLineWrap(true);
        information.setWrapStyleWord(true);
        scroll.setBounds(25,290,200,125);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        loadButton.setBounds(25,440,200,50);
        loadButton.setFont(new Font("宋体",Font.BOLD,22));
        loadButton.setFocusPainted(false);
        loadButton.setBackground(new Color(0xE77FBF7D));
        loadButton.setForeground(new Color(0x004D2C));
        backToMenuButton.setBounds(25,515,200,50);
        backToMenuButton.setFont(new Font("宋体",Font.BOLD,22));
        backToMenuButton.setFocusPainted(false);
        backToMenuButton.setBackground(new Color(0xE77FBF7D));
        backToMenuButton.setForeground(new Color(0x004D2C));
        messagePanel.setBounds(750,60,250,604);
        messagePanel.setBorder(BorderFactory.createLineBorder(new Color(0x5BAB95)));
        messagePanel.setBackground(new Color(0x49FFC2FC, true));

    }

    protected void addElement() {
        mapPanel.add(oLabel);
        mapPanel.add(mapLabel);
        mapPanel.add(mapbg);
        mainPanel.add(mapPanel);
        messagePanel.add(mess);
        messagePanel.add(name);
        messagePanel.add(nameField);
        messagePanel.add(type);
        messagePanel.add(typeLabel);
        messagePanel.add(imagepath);
        messagePanel.add(imagepathField);
        messagePanel.add(informationLabel);
        messagePanel.add(scroll);
        messagePanel.add(loadButton);
        messagePanel.add(backToMenuButton);
        mainPanel.add(messagePanel);
        //添加背景
        mainPanel.add(background);
        this.add(mainPanel);
    }

    protected void addListener() {
        ActionListener add = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyMapService service = new MyMapService();
                MyMap map = service.getMyMap();
                HashMap<String,Integer> nameToBuildingIndex = map.getNameToBuildingIndex();
                ArrayList<Building> buildings = map.getBuildings();
                ArrayList<Road> roads = map.getRoads();
                String newname = AddBuildingFrame.this.nameField.getText();
                newname = newname.replace(" ","");
                String p = imagepathField.getText();
                String infor = information.getText();
                infor = infor.replace(" ","");
                File f;
                if(posX==-1||posY==-1){
                    JOptionPane.showMessageDialog(AddBuildingFrame.this, "请选择地点！");
                }else if(nameToBuildingIndex.get(newname)==null){
                    if(isTransferOrNot==0) {
                        if ("".equals(newname)) {
                            JOptionPane.showMessageDialog(AddBuildingFrame.this, "建筑名称必填！");
                        }else {
                            f = new File(p);
                            if (!f.isFile() || !f.canRead()) {
                                p = "src/image/default.jpg";
                            }
                            if("".equals(infor)){
                                infor = "暂无介绍";
                            }
                            Building b = new Building(isTransferOrNot,posX,posY,newname,infor,p);
                            buildings.add(b);
                            service.upDataMap(buildings,roads);
                            JOptionPane.showMessageDialog(new AddBuildingFrame("MyMap地图导航系统-更改地图-添加建筑"), "添加成功！");
                            AddBuildingFrame.this.dispose();
                        }
                    }else{
                        newname = String.valueOf(System.currentTimeMillis());
                        p = "src/image/default.jpg";
                        infor = "暂无介绍";
                        Building b = new Building(isTransferOrNot,posX,posY,newname,infor,p);
                        buildings.add(b);
                        service.upDataMap(buildings,roads);
                        JOptionPane.showMessageDialog(new AddBuildingFrame("MyMap地图导航系统-更改地图-添加建筑"), "添加成功！");
                        AddBuildingFrame.this.dispose();
                    }
                }else{
                    JOptionPane.showMessageDialog(AddBuildingFrame.this,"建筑名字重复！");
                }


            }
        };
        ActionListener backToMenuActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuFrame("MyMap地图导航系统");
                AddBuildingFrame.this.dispose();
            }
        };
        MouseListener mouseListener = new MouseListener() {
            MyMapService service = new MyMapService();
            MyMap map = service.getMyMap();
            ArrayList<Building> buildings = map.getBuildings();
            @Override
            public void mouseClicked(MouseEvent e) {
                int posX = e.getX();
                int posY = e.getY();
                AddBuildingFrame.this.oLabel.setBounds(posX-13,posY-18,30,30);
                AddBuildingFrame.this.oLabel.setVisible(true);
                AddBuildingFrame.this.posX = posX;
                AddBuildingFrame.this.posY = posY;
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        MouseListener type = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(AddBuildingFrame.this.isTransferOrNot == 0){
                    AddBuildingFrame.this.isTransferOrNot = 1;
                    AddBuildingFrame.this.nameField.setEnabled(false);
                    AddBuildingFrame.this.nameField.setText("");
                    AddBuildingFrame.this.imagepathField.setEnabled(false);
                    AddBuildingFrame.this.imagepathField.setText("");
                    AddBuildingFrame.this.information.setEnabled(false);
                    AddBuildingFrame.this.information.setText("");
                    AddBuildingFrame.this.typeLabel.setIcon(new ImageIcon("src/image/yes.jpg"));
                }else{
                    AddBuildingFrame.this.isTransferOrNot = 0;
                    AddBuildingFrame.this.imagepathField.setEnabled(true);
                    AddBuildingFrame.this.nameField.setEnabled(true);
                    AddBuildingFrame.this.information.setEnabled(true);
                    AddBuildingFrame.this.typeLabel.setIcon(new ImageIcon("src/image/no.jpg"));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        backToMenuButton.addActionListener(backToMenuActionListener);
        loadButton.addActionListener(add);
        typeLabel.addMouseListener(type);
        mapLabel.addMouseListener(mouseListener);
    }

    protected void setFrameSelf() {
        //设置窗口位置和大小
        this.setBounds(380,110,1100,790);
        //设置点击关闭退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗体大小不可拖拽
        this.setResizable(false);
        //设置窗体显示状态
        this.setVisible(true);
    }
}
