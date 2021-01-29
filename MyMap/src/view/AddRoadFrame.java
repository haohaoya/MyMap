package view;

import domain.Building;
import domain.MyMap;
import domain.Road;
import service.MyMapService;
import util.BaseFrame;
import util.MapUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class AddRoadFrame extends BaseFrame {


    //创建一个面板
    private JPanel mainPanel = new JPanel();

    //地图面板，介绍面板
    private JPanel mapPanel = new JPanel();
    private JPanel messagePanel = new JPanel();

    private ImageIcon map = new ImageIcon(MapUtil.getMapImage());
    private JLabel mapLabel = new JLabel(map);
    private JLabel mapbg = new JLabel(new ImageIcon("src/image/mapbg.jpg"));
    private JLabel tLabel = new JLabel(new ImageIcon(MapUtil.getTImage()));

    private JLabel mess = new JLabel("请输入要添加路俩点名");
    private JLabel mess2 = new JLabel("称或中转建筑的代号:");
    private JLabel mess3 = new JLabel("温馨提示:");
    private JLabel mess4 = new JLabel(" 由于不相连的俩点确定");
    private JLabel mess5 = new JLabel("的路可能有多条 所以只");
    private JLabel mess6 = new JLabel("能添加俩点间的路！");
    private JTextField nameField1 = new JTextField();
    private JTextField nameField2 = new JTextField();

    private JButton loadButton = new JButton("添 加 路 线");
    private JButton backToMenuButton = new JButton("返 回 菜 单");

    //主画板背景
    private ImageIcon imageIcon = new ImageIcon("src/image/bg.jpg");
    private JLabel background = new JLabel(imageIcon);

    public AddRoadFrame(){}
    public AddRoadFrame(String title){
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

        mapbg.setBounds(2,2,600,600);
        mapLabel.setBounds(2,2,600,600);
        tLabel.setBounds(2,2,600,600);

        mess.setBounds(20,60,300,30);
        mess.setFont(new Font("楷体",Font.PLAIN,19));
        mess.setForeground(new Color(0x6C5892));
        mess2.setBounds(20,90,300,30);
        mess2.setFont(new Font("楷体",Font.PLAIN,19));
        mess2.setForeground(new Color(0x6C5892));


        nameField1.setBounds(30, 150, 190, 40);
        nameField1.setFont(new Font("宋体", Font.PLAIN, 30));
        nameField2.setBounds(30, 220, 190, 40);
        nameField2.setFont(new Font("宋体", Font.PLAIN, 30));


        mess3.setBounds(20,295,300,30);
        mess3.setFont(new Font("楷体",Font.PLAIN,19));
        mess3.setForeground(new Color(0x6C5892));

        mess4.setBounds(20,330,300,30);
        mess4.setFont(new Font("楷体",Font.PLAIN,19));
        mess4.setForeground(new Color(0x6C5892));

        mess5.setBounds(20,360,300,30);
        mess5.setFont(new Font("楷体",Font.PLAIN,19));
        mess5.setForeground(new Color(0x6C5892));

        mess6.setBounds(20,390,300,30);
        mess6.setFont(new Font("楷体",Font.PLAIN,19));
        mess6.setForeground(new Color(0x6C5892));

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
        mapPanel.add(tLabel);
        mapPanel.add(mapLabel);
        mapPanel.add(mapbg);
        mainPanel.add(mapPanel);
        messagePanel.add(mess);
        messagePanel.add(mess2);
        messagePanel.add(nameField1);
        messagePanel.add(nameField2);
        messagePanel.add(mess3);
        messagePanel.add(mess4);
        messagePanel.add(mess5);
        messagePanel.add(mess6);
        messagePanel.add(loadButton);
        messagePanel.add(backToMenuButton);
        mainPanel.add(messagePanel);
        //添加背景
        mainPanel.add(background);
        this.add(mainPanel);
    }

    protected void addListener() {
        ActionListener delete = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyMapService service = new MyMapService();
                MyMap map = service.getMyMap();
                HashMap<String,Integer> nameToBuildingIndex = map.getNameToBuildingIndex();
                ArrayList<Road> roads = map.getRoads();
                ArrayList<Building> buildings = map.getBuildings();
                String name1 = nameField1.getText();
                String name2 = nameField2.getText();
                Road newroad = null;
                int flag = 1;
                if(nameToBuildingIndex.get(name1)==null||nameToBuildingIndex.get(name2)==null){
                    JOptionPane.showMessageDialog(AddRoadFrame.this, "建筑名称错误");
                }else{
                    for(Road road : roads){
                        if(road.getBuildingName1().equals(name1)&&road.getBuildingName2().equals(name2)){
                            flag = 0;
                            JOptionPane.showMessageDialog(AddRoadFrame.this, "路线重复！");
                        }else if(road.getBuildingName1().equals(name2)&&road.getBuildingName2().equals(name1)){
                            flag = 0;
                            JOptionPane.showMessageDialog(AddRoadFrame.this, "路线重复！");
                        }
                    }
                    if(flag==1){
                        int index1 = nameToBuildingIndex.get(name1);
                        int index2 = nameToBuildingIndex.get(name2);
                        Building building1 = buildings.get(index1);
                        Building building2 = buildings.get(index2);
                        int length = (int)Math.sqrt(Math.pow(building1.getPosX()-building2.getPosX(),2)+Math.pow(building1.getPosY()-building2.getPosY(),2));
                        newroad = new Road(name1,name2,length);
                        roads.add(newroad);
                        service.upDataMap(buildings,roads);
                        JOptionPane.showMessageDialog(new AddRoadFrame("MyMap地图导航系统-更改地图-添加路线"), "添加成功！");
                        AddRoadFrame.this.dispose();
                    }
                }

            }
        };
        ActionListener backToMenuActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuFrame("MyMap地图导航系统");
                AddRoadFrame.this.dispose();
            }
        };

        backToMenuButton.addActionListener(backToMenuActionListener);
        loadButton.addActionListener(delete);
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
