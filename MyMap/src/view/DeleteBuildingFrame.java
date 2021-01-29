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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

public class DeleteBuildingFrame extends BaseFrame {

    //创建一个面板
    private JPanel mainPanel = new JPanel();

    //地图面板，介绍面板
    private JPanel mapPanel = new JPanel();
    private JPanel messagePanel = new JPanel();

    private ImageIcon map = new ImageIcon(MapUtil.getMapImage());
    private JLabel mapLabel = new JLabel(map);
    private JLabel mapbg = new JLabel(new ImageIcon("src/image/mapbg.jpg"));
    private JLabel tLabel = new JLabel(new ImageIcon(MapUtil.getTImage()));

    private JLabel mess = new JLabel("请输入要删除的建筑名称");
    private JLabel mess2 = new JLabel("或中转建筑的代号:");
    private JLabel mess3 = new JLabel("温馨提示:");
    private JLabel mess4 = new JLabel(" 删除有路的建筑将删除");
    private JLabel mess5 = new JLabel("与该建筑相连的所有路！");
    private JTextField nameField = new JTextField();


    private JButton loadButton = new JButton("删 除 建 筑");
    private JButton backToMenuButton = new JButton("返 回 菜 单");

    //主画板背景
    private ImageIcon imageIcon = new ImageIcon("src/image/bg.jpg");
    private JLabel background = new JLabel(imageIcon);

    public DeleteBuildingFrame(){}
    public DeleteBuildingFrame(String title){
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

        mess.setBounds(20,110,300,30);
        mess.setFont(new Font("楷体",Font.PLAIN,19));
        mess.setForeground(new Color(0x6C5892));
        mess2.setBounds(20,140,300,30);
        mess2.setFont(new Font("楷体",Font.PLAIN,19));
        mess2.setForeground(new Color(0x6C5892));


        nameField.setBounds(30, 220, 190, 40);
        nameField.setFont(new Font("宋体", Font.PLAIN, 30));


        mess3.setBounds(20,320,300,30);
        mess3.setFont(new Font("楷体",Font.PLAIN,19));
        mess3.setForeground(new Color(0x6C5892));

        mess4.setBounds(20,350,300,30);
        mess4.setFont(new Font("楷体",Font.PLAIN,19));
        mess4.setForeground(new Color(0x6C5892));

        mess5.setBounds(20,380,300,30);
        mess5.setFont(new Font("楷体",Font.PLAIN,19));
        mess5.setForeground(new Color(0x6C5892));



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
        messagePanel.add(nameField);
        messagePanel.add(mess3);
        messagePanel.add(mess4);
        messagePanel.add(mess5);
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
                String name = DeleteBuildingFrame.this.nameField.getText();
                MyMapService service = new MyMapService();
                MyMap map = service.getMyMap();
                HashMap<String,Integer> nameToBuildingIndex = map.getNameToBuildingIndex();
                ArrayList<Road> roads = map.getRoads();
                ArrayList<Building> buildings = map.getBuildings();
                if(nameToBuildingIndex.get(name)==null){
                    JOptionPane.showMessageDialog(DeleteBuildingFrame.this, "建筑名称错误");
                }else{
                    for(int i = 0;i <roads.size();i++){
                        if(roads.get(i).getBuildingName1().equals(name)||roads.get(i).getBuildingName2().equals(name)){
                            roads.remove(i);
                            i--;
                        }
                    }

                    buildings.remove((int)nameToBuildingIndex.get(name));
                    service.upDataMap(buildings,roads);
                    JOptionPane.showMessageDialog(new DeleteBuildingFrame("MyMap地图导航系统-更改地图-删除建筑"), "删除成功！");
                    DeleteBuildingFrame.this.dispose();
                }
            }
        };
        ActionListener backToMenuActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuFrame("MyMap地图导航系统");
                DeleteBuildingFrame.this.dispose();
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
