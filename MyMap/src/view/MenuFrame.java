package view;

import util.BaseFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuFrame extends BaseFrame {
    //创建一个面板
    private JPanel mainPanel = new JPanel();

    private ImageIcon imageIcon = new ImageIcon("src/image/bg.jpg");
    private JLabel background = new JLabel(imageIcon);

    //创建title显示标题
    private JLabel titleLabel = new JLabel("MyMap 地 图 导 航 系 统");

    //创建四个按钮
    private JButton findRoadButton = new JButton("查 找 路 线");
    private JButton findBuildingButton = new JButton("查 找 建 筑");
    private JButton updateMapButton = new JButton("更 改 地 图");
    private JButton seeMapButton = new JButton("查 看 地 图");
    private JButton exitButton = new JButton("退 出 程 序");

    public MenuFrame(){}
    public MenuFrame(String title){
        super(title);
        this.init();
    }

    protected void setFontAndSoOn() {
        //设置布局管理方式
        mainPanel.setLayout(null);
        //设置背景图片位置和大小
        background.setBounds(0, 0, 1100,790);
        //设置标题组件的位置和大小
        titleLabel.setBounds(195,120,1100,61);
        titleLabel.setFont(new Font("楷体",Font.BOLD,60));
        titleLabel.setForeground(new Color(0x5BAB95));

        //设置按钮位置和大小
        findRoadButton.setBounds(430,230,200,50);
        findRoadButton.setFont(new Font("宋体",Font.BOLD,22));
        findRoadButton.setFocusPainted(false);
        findRoadButton.setBackground(new Color(0xE77FBF7D, true));
        findRoadButton.setForeground(new Color(0x004D2C));
        findBuildingButton.setBounds(430,330,200,50);
        findBuildingButton.setFont(new Font("宋体",Font.BOLD,22));
        findBuildingButton.setFocusPainted(false);
        findBuildingButton.setBackground(new Color(0xE77FBF7D, true));
        findBuildingButton.setForeground(new Color(0x004D2C));
        seeMapButton.setBounds(430,430,200,50);
        seeMapButton.setFont(new Font("宋体",Font.BOLD,22));
        seeMapButton.setFocusPainted(false);
        seeMapButton.setBackground(new Color(0xE77FBF7D, true));
        seeMapButton.setForeground(new Color(0x004D2C));
        updateMapButton.setBounds(430,530,200,50);
        updateMapButton.setFont(new Font("宋体",Font.BOLD,22));
        updateMapButton.setFocusPainted(false);
        updateMapButton.setBackground(new Color(0xE77FBF7D, true));
        updateMapButton.setForeground(new Color(0x004D2C));
        exitButton.setBounds(430,630,200,50);
        exitButton.setFont(new Font("宋体",Font.BOLD,22));
        exitButton.setFocusPainted(false);
        exitButton.setBackground(new Color(0xE77FBF7D, true));
        exitButton.setForeground(new Color(0x004D2C));
    }

    protected void addElement() {
        mainPanel.add(titleLabel);
        mainPanel.add(findRoadButton);
        mainPanel.add(findBuildingButton);
        mainPanel.add(seeMapButton);
        mainPanel.add(updateMapButton);
        mainPanel.add(exitButton);
        //添加背景
        mainPanel.add(background);
        this.add(mainPanel);
    }

    protected void addListener() {
        ActionListener exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        ActionListener updateMapListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateMapFrame("MyMap地图导航系统-更改地图");
                MenuFrame.this.dispose();
            }
        };
        ActionListener seeMapListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SeeMapFrame("MyMap地图导航系统-查看地图");
                MenuFrame.this.dispose();
            }
        };
        ActionListener findBuildingListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindBuildingFrame("MyMap地图导航系统-查找建筑");
                MenuFrame.this.dispose();
            }
        };
        ActionListener findRoadListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindRoadFrame("MyMap地图导航系统-查找路线");
                MenuFrame.this.dispose();
            }
        };

        findRoadButton.addActionListener(findRoadListener);
        findBuildingButton.addActionListener(findBuildingListener);
        seeMapButton.addActionListener(seeMapListener);
        updateMapButton.addActionListener(updateMapListener);
        exitButton.addActionListener(exitListener);


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
