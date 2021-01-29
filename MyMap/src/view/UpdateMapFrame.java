package view;

import util.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateMapFrame extends BaseFrame {

    //创建一个面板
    private JPanel mainPanel = new JPanel();

    private ImageIcon imageIcon = new ImageIcon("src/image/bg.jpg");
    private JLabel background = new JLabel(imageIcon);

    //创建title显示标题
    private JLabel titleLabel = new JLabel("MyMap 地 图 导 航 系 统");

    //创建四个按钮
    private JButton addBuildingButton = new JButton("添 加 建 筑");
    private JButton addRoadButton = new JButton("添 加 路 线");
    private JButton deleteBuildingButton = new JButton("删 除 建 筑");
    private JButton deleteRoadButton = new JButton("删 除 路 线");
    private JButton backToMenuButton = new JButton("返 回 菜 单");
    private JButton bestButton = new JButton("最佳布网方案");

    public UpdateMapFrame(){}
    public UpdateMapFrame(String title){
        super(title);
        this.init();
    }

    protected void setFontAndSoOn() {
        //设置布局管理方式
        mainPanel.setLayout(null);
        //设置背景图片位置和大小
        background.setBounds(0, 0, 1100,790);
        titleLabel.setBounds(195,120,1100,61);
        titleLabel.setFont(new Font("楷体",Font.BOLD,60));
        titleLabel.setForeground(new Color(0x5BAB95));

        //设置按钮位置和大小
        addBuildingButton.setBounds(430,230,200,50);
        addBuildingButton.setFont(new Font("宋体",Font.BOLD,22));
        addBuildingButton.setFocusPainted(false);
        addBuildingButton.setBackground(new Color(0xE77FBF7D));
        addBuildingButton.setForeground(new Color(0x004D2C));
        addRoadButton.setBounds(430,330,200,50);
        addRoadButton.setFont(new Font("宋体",Font.BOLD,22));
        addRoadButton.setFocusPainted(false);
        addRoadButton.setBackground(new Color(0xE77FBF7D));
        addRoadButton.setForeground(new Color(0x004D2C));
        deleteBuildingButton.setBounds(430,430,200,50);
        deleteBuildingButton.setFont(new Font("宋体",Font.BOLD,22));
        deleteBuildingButton.setFocusPainted(false);
        deleteBuildingButton.setBackground(new Color(0xE77FBF7D));
        deleteBuildingButton.setForeground(new Color(0x004D2C));
        deleteRoadButton.setBounds(430,530,200,50);
        deleteRoadButton.setFont(new Font("宋体",Font.BOLD,22));
        deleteRoadButton.setFocusPainted(false);
        deleteRoadButton.setBackground(new Color(0xE77FBF7D));
        deleteRoadButton.setForeground(new Color(0x004D2C));
        backToMenuButton.setBounds(430,630,200,50);
        backToMenuButton.setFont(new Font("宋体",Font.BOLD,22));
        backToMenuButton.setFocusPainted(false);
        backToMenuButton.setBackground(new Color(0xE77FBF7D));
        backToMenuButton.setForeground(new Color(0x004D2C));
        bestButton.setBounds(40,670,180,50);
        bestButton.setFont(new Font("宋体",Font.BOLD,22));
        bestButton.setFocusPainted(false);
        bestButton.setBackground(new Color(0xB1B0C2));
        bestButton.setForeground(new Color(0x004D2C));
    }

    protected void addElement() {
        mainPanel.add(titleLabel);
        mainPanel.add(addBuildingButton);
        mainPanel.add(addRoadButton);
        mainPanel.add(deleteBuildingButton);
        mainPanel.add(deleteRoadButton);
        mainPanel.add(backToMenuButton);
        mainPanel.add(bestButton);
        //添加背景
        mainPanel.add(background);
        this.add(mainPanel);
    }

    protected void addListener() {
        ActionListener backToMenuActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuFrame("MyMap地图导航系统");
                UpdateMapFrame.this.dispose();
            }
        };

        ActionListener addBuildingActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddBuildingFrame("MyMap地图导航系统-更改地图-添加建筑");
                UpdateMapFrame.this.dispose();
            }
        };

        ActionListener deleteBuildingActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteBuildingFrame("MyMap地图导航系统-更改地图-删除建筑");
                UpdateMapFrame.this.dispose();
            }
        };
        ActionListener addRoadActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddRoadFrame("MyMap地图导航系统-更改地图-添加路线");
                UpdateMapFrame.this.dispose();
            }
        };
        ActionListener deleteRoadActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteRoadFrame("MyMap地图导航系统-更改地图-删除路线");
                UpdateMapFrame.this.dispose();
            }
        };
        ActionListener bestActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BestFrame("MyMap地图导航系统-更改地图-最佳布网方案");
                UpdateMapFrame.this.dispose();
            }
        };
        bestButton.addActionListener(bestActionListener);
        addBuildingButton.addActionListener(addBuildingActionListener);
        backToMenuButton.addActionListener(backToMenuActionListener);
        deleteBuildingButton.addActionListener(deleteBuildingActionListener);
        addRoadButton.addActionListener(addRoadActionListener);
        deleteRoadButton.addActionListener(deleteRoadActionListener);
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
