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

public class SeeMapFrame extends BaseFrame {
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

    private ImageIcon image = new ImageIcon("src/image/default.jpg");
    private JLabel imageLabel = new JLabel(image);
    private JTextArea information = new JTextArea("  请点击地图中的圆点，查看对应地点的相关信息。");
    private JScrollPane scroll = new JScrollPane(information);
    private JButton backToMenuButton = new JButton("返 回 菜 单");

    //主画板背景
    private ImageIcon imageIcon = new ImageIcon("src/image/bg.jpg");
    private JLabel background = new JLabel(imageIcon);

    public SeeMapFrame(){}
    public SeeMapFrame(String title){
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

        imageLabel.setBounds(50,50,150,150);
        information.setFont(new Font("楷体",Font.BOLD,30));
        information.setOpaque(false);
        information.setDisabledTextColor(Color.PINK);
        information.setLineWrap(true);
        information.setWrapStyleWord(true);
        information.setEnabled(false);
        scroll.setBounds(25,230,200,250);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
        messagePanel.add(scroll);
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
                SeeMapFrame.this.dispose();
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
                for(Building building : buildings){
                    if(building.getIsTransferOrNot()==0){
                        if(posX >= building.getPosX()-10 && posX <= building.getPosX()+10){
                            if(posY >= building.getPosY()-10 && posY <= building.getPosY()+10){
                                ImageIcon image = new ImageIcon(building.getImagePath());
                                SeeMapFrame.this.imageLabel.setIcon(image);
                                SeeMapFrame.this.oLabel.setBounds(building.getPosX()-13,building.getPosY()-18,30,30);
                                SeeMapFrame.this.oLabel.setVisible(true);
                                SeeMapFrame.this.information.setText(building.getName()+"：\n  "+building.getInformation());
                                break;
                            }
                        }
                    }
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
