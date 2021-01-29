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
import java.util.ArrayList;
import java.util.HashMap;

public class FindRoadFrame extends BaseFrame {

    //创建一个面板
    private JPanel mainPanel = new JPanel();

    //地图面板，介绍面板
    private JPanel mapPanel = new JPanel();
    private JPanel messagePanel = new JPanel();

    private ImageIcon map = new ImageIcon(MapUtil.getMapImage());
    private JLabel mapLabel = new JLabel(map);
    private JLabel roadLabel = new JLabel();
    private JLabel mapbg = new JLabel(new ImageIcon("src/image/mapbg.jpg"));


    private JLabel startLabel = new JLabel("起点名称:");
    private JTextField startTextField = new JTextField();
    private JLabel endLabel = new JLabel("终点名称:");
    private JTextField endTextField = new JTextField();

    private JButton searchShortButton = new JButton("最 短 路 线");
    private JButton searchButton = new JButton("最 少 路 线");
    private JButton backToMenuButton = new JButton("返 回 菜 单");

    //主画板背景
    private ImageIcon imageIcon = new ImageIcon("src/image/bg.jpg");
    private JLabel background = new JLabel(imageIcon);

    public FindRoadFrame() {
    }

    public FindRoadFrame(String title) {
        super(title);
        this.init();
    }

    protected void setFontAndSoOn() {
        //设置布局管理方式
        mainPanel.setLayout(null);
        mapPanel.setLayout(null);
        messagePanel.setLayout(null);
        //设置背景图片位置和大小
        background.setBounds(0, 0, 1100, 790);

        mapPanel.setLayout(null);
        mapPanel.setBounds(60, 60, 604, 604);
        mapPanel.setBorder(BorderFactory.createLineBorder(new Color(0x5BAB95), 3));

        mapbg.setBounds(2,2,600,600);
        mapLabel.setBounds(2, 2, 600, 600);
        roadLabel.setBounds(2,2,600,600);

        startLabel.setBounds(20, 70, 200, 50);
        startLabel.setFont(new Font("宋体", Font.BOLD, 35));
        startLabel.setForeground(new Color(0x9DAEC4));
        startTextField.setBounds(30, 140, 190, 40);
        startTextField.setFont(new Font("宋体", Font.PLAIN, 30));

        endLabel.setBounds(20, 230, 200, 50);
        endLabel.setFont(new Font("宋体", Font.BOLD, 35));
        endLabel.setForeground(new Color(0x9DAEC4));
        endTextField.setBounds(30, 290, 190, 40);
        endTextField.setFont(new Font("宋体", Font.PLAIN, 30));

        searchShortButton.setBounds(25, 365, 200, 50);
        searchShortButton.setFont(new Font("宋体", Font.BOLD, 22));
        searchShortButton.setFocusPainted(false);
        searchShortButton.setBackground(new Color(0xE77FBF7D));
        searchShortButton.setForeground(new Color(0x004D2C));
        searchButton.setBounds(25, 440, 200, 50);
        searchButton.setFont(new Font("宋体", Font.BOLD, 22));
        searchButton.setFocusPainted(false);
        searchButton.setBackground(new Color(0xE77FBF7D));
        searchButton.setForeground(new Color(0x004D2C));
        backToMenuButton.setBounds(25, 515, 200, 50);
        backToMenuButton.setFont(new Font("宋体", Font.BOLD, 22));
        backToMenuButton.setFocusPainted(false);
        backToMenuButton.setBackground(new Color(0xE77FBF7D));
        backToMenuButton.setForeground(new Color(0x004D2C));
        messagePanel.setBounds(750, 60, 250, 604);
        messagePanel.setBorder(BorderFactory.createLineBorder(new Color(0x5BAB95)));
        messagePanel.setBackground(new Color(0x49FFC2FC, true));

    }

    protected void addElement() {
        mapPanel.add(roadLabel);
        mapPanel.add(mapLabel);
        mapPanel.add(mapbg);
        mainPanel.add(mapPanel);
        messagePanel.add(startLabel);
        messagePanel.add(startTextField);
        messagePanel.add(endLabel);
        messagePanel.add(endTextField);
        messagePanel.add(searchShortButton);
        messagePanel.add(searchButton);
        messagePanel.add(backToMenuButton);
        mainPanel.add(messagePanel);
        //添加背景
        mainPanel.add(background);
        this.add(mainPanel);
    }

    protected void addListener() {
        ActionListener backToMenuActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuFrame("MyMap地图导航系统");
                FindRoadFrame.this.dispose();
            }
        };
        ActionListener searchActionListener = new ActionListener() {
            MyMapService service = new MyMapService();
            MyMap map = service.getMyMap();
            ArrayList<Building> buildings = map.getBuildings();
            HashMap<String,Integer> nameToBuildingIndex = map.getNameToBuildingIndex();
            @Override
            public void actionPerformed(ActionEvent e) {
                roadLabel.setVisible(false);
                String startName = startTextField.getText();
                String endName = endTextField.getText();
                if(nameToBuildingIndex.get(startName)==null){
                    JOptionPane.showMessageDialog(FindRoadFrame.this,"起点输入错误！");
                }else if(nameToBuildingIndex.get(startName)==null){
                    JOptionPane.showMessageDialog(FindRoadFrame.this,"终点输入错误！");
                }else if(startName.equals(endName)) {
                    JOptionPane.showMessageDialog(FindRoadFrame.this,"目的地已经在你脚下了！");
                }else{
                    Image i = MapUtil.getRoad(map,startName,endName);
                    if(i!=null) {
                        ImageIcon road = new ImageIcon(i);
                        roadLabel.setIcon(road);
                        roadLabel.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(FindRoadFrame.this,"没有一条路能到达！");
                    }
                }
            }
        };
        ActionListener searchShortActionListener = new ActionListener() {
            MyMapService service = new MyMapService();
            MyMap map = service.getMyMap();
            ArrayList<Building> buildings = map.getBuildings();
            HashMap<String,Integer> nameToBuildingIndex = map.getNameToBuildingIndex();
            @Override
            public void actionPerformed(ActionEvent e) {
                roadLabel.setVisible(false);
                String startName = startTextField.getText();
                String endName = endTextField.getText();
                if(nameToBuildingIndex.get(startName)==null){
                    JOptionPane.showMessageDialog(FindRoadFrame.this,"起点输入错误！");
                }else if(nameToBuildingIndex.get(startName)==null){
                    JOptionPane.showMessageDialog(FindRoadFrame.this,"终点输入错误！");
                }else if(startName.equals(endName)) {
                    JOptionPane.showMessageDialog(FindRoadFrame.this,"目的地已经在你脚下了！");
                }else{
                    Image i = MapUtil.getShortRoad(map,startName,endName);
                    if(i!=null) {
                        ImageIcon road = new ImageIcon(i);
                        roadLabel.setIcon(road);
                        roadLabel.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(FindRoadFrame.this,"没有一条路能到达！");
                    }
                }
            }
        };
        backToMenuButton.addActionListener(backToMenuActionListener);
        searchButton.addActionListener(searchActionListener);
        searchShortButton.addActionListener(searchShortActionListener);

    }

    protected void setFrameSelf() {
        //设置窗口位置和大小
        this.setBounds(380, 110, 1100, 790);
        //设置点击关闭退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗体大小不可拖拽
        this.setResizable(false);
        //设置窗体显示状态
        this.setVisible(true);
    }

}
