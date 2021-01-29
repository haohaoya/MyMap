package view;

import domain.Building;
import domain.MyMap;
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

public class FindBuildingFrame extends BaseFrame {
    //创建一个面板
    private JPanel mainPanel = new JPanel();

    //地图面板，介绍面板
    private JPanel mapPanel = new JPanel();
    private JPanel messagePanel = new JPanel();

    private int state = 0;
    private int OX=0;
    private int OY=0;

    private ImageIcon o = new ImageIcon(MapUtil.getO());
    private JLabel oLabel = new JLabel(o);
    private ImageIcon map = new ImageIcon(MapUtil.getMapImage());
    private JLabel mapbg = new JLabel(new ImageIcon("src/image/mapbg.jpg"));
    private JLabel mapLabel = new JLabel(map);


    private ImageIcon image = new ImageIcon("src/image/default.jpg");
    private JLabel imageLabel = new JLabel(image);
    private JLabel tipsLabel = new JLabel("建筑名称:");
    private JTextField textField = new JTextField();
    private JButton searchButton = new JButton("查 找 建 筑");
    private JButton backToMenuButton = new JButton("返 回 菜 单");

    //主画板背景
    private ImageIcon imageIcon = new ImageIcon("src/image/bg.jpg");
    private JLabel background = new JLabel(imageIcon);

    private ShowThread showThread = new ShowThread();
    public FindBuildingFrame(){}
    public FindBuildingFrame(String title){
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

        oLabel.setBounds(OX,OY,30,30);
        oLabel.setVisible(false);
        mapbg.setBounds(2,2,600,600);
        mapLabel.setBounds(2,2,600,600);

        imageLabel.setBounds(50,50,150,150);
        tipsLabel.setBounds(20,260,200,50);
        tipsLabel.setFont(new Font("宋体",Font.BOLD,35));
        tipsLabel.setForeground(new Color(0x9DAEC4));
        textField.setBounds(30,330,190,40);
        textField.setFont(new Font("宋体",Font.PLAIN,30));
        searchButton.setBounds(25,415,200,50);
        searchButton.setFont(new Font("宋体",Font.BOLD,22));
        searchButton.setFocusPainted(false);
        searchButton.setBackground(new Color(0xE77FBF7D));
        searchButton.setForeground(new Color(0x004D2C));
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
        messagePanel.add(imageLabel);
        messagePanel.add(tipsLabel);
        messagePanel.add(textField);
        messagePanel.add(searchButton);
        messagePanel.add(backToMenuButton);
        mainPanel.add(messagePanel);
        //添加背景
        mainPanel.add(background);
        this.add(mainPanel);
        showThread.start();
    }

    protected void addListener() {
        ActionListener backToMenuActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuFrame("MyMap地图导航系统");
                state = -1;
                FindBuildingFrame.this.dispose();
            }
        };
        ActionListener searchActionListener = new ActionListener() {
            MyMapService service = new MyMapService();
            MyMap map = service.getMyMap();
            ArrayList<Building> buildings = map.getBuildings();
            HashMap<String,Integer> nameToBuildingIndex = map.getNameToBuildingIndex();
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                Integer index = nameToBuildingIndex.get(name);
                if(index == null){
                    ImageIcon image = new ImageIcon("src/image/default.jpg");
                    FindBuildingFrame.this.imageLabel.setIcon(image);
                    state = 0;
                    JOptionPane.showMessageDialog(FindBuildingFrame.this,"没有找到该建筑！");
                }else{
                    Building building = buildings.get(index);
                    ImageIcon image = new ImageIcon(building.getImagePath());
                    FindBuildingFrame.this.imageLabel.setIcon(image);
                    OX = building.getPosX()-13;
                    OY = building.getPosY()-18;
                    state = 1;
                }
            }
        };
        searchButton.addActionListener(searchActionListener);
        backToMenuButton.addActionListener(backToMenuActionListener);
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

    private class ShowThread extends Thread{
        @Override
        public void run() {
            while(true){
                if(state==0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                if(state==-1){
                    break;
                }
                if(state==1){
                    oLabel.setBounds(OX,OY,30,30);
                    oLabel.setVisible(true);
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    oLabel.setVisible(false);
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
