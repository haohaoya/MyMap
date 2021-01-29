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

public class BestFrame extends BaseFrame {



    //创建一个面板
    private JPanel mainPanel = new JPanel();

    //地图面板，介绍面板
    private JPanel mapPanel = new JPanel();
    private JPanel messagePanel = new JPanel();
    private ArrayList<Road> newRoads = new ArrayList<>();
    private ImageIcon map = new ImageIcon(MapUtil.getBestMapImage(newRoads));
    private JLabel mapLabel = new JLabel(map);
    private JLabel mapbg = new JLabel(new ImageIcon("src/image/mapbg.jpg"));



    private JButton loadButton = new JButton("一 键 修 改");
    private JButton backToMenuButton = new JButton("返 回 菜 单");

    //主画板背景
    private ImageIcon imageIcon = new ImageIcon("src/image/bg.jpg");
    private JLabel background = new JLabel(imageIcon);

    public BestFrame(){}
    public BestFrame(String title){
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

        loadButton.setBounds(25,220,200,50);
        loadButton.setFont(new Font("宋体",Font.BOLD,22));
        loadButton.setFocusPainted(false);
        loadButton.setBackground(new Color(0xE77FBF7D));
        loadButton.setForeground(new Color(0x004D2C));
        backToMenuButton.setBounds(25,360,200,50);
        backToMenuButton.setFont(new Font("宋体",Font.BOLD,22));
        backToMenuButton.setFocusPainted(false);
        backToMenuButton.setBackground(new Color(0xE77FBF7D));
        backToMenuButton.setForeground(new Color(0x004D2C));
        messagePanel.setBounds(750,60,250,604);
        messagePanel.setBorder(BorderFactory.createLineBorder(new Color(0x5BAB95)));
        messagePanel.setBackground(new Color(0x49FFC2FC, true));

    }

    protected void addElement() {
        mapPanel.add(mapLabel);
        mapPanel.add(mapbg);
        mainPanel.add(mapPanel);
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
                ArrayList<Building> buildings = map.getBuildings();
                service.upDataMap(buildings,newRoads);
                JOptionPane.showMessageDialog(new UpdateMapFrame("MyMap地图导航系统-更改地图"), "更改成功！");
                BestFrame.this.dispose();

            }
        };
        ActionListener backToMenuActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuFrame("MyMap地图导航系统");
                BestFrame.this.dispose();
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
