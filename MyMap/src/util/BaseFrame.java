package util;

import javax.swing.*;

public abstract class BaseFrame extends JFrame {
    //基础窗口
    public BaseFrame(){}

    public BaseFrame(String title){
        super(title);
    }

    protected void init(){
        this.setFontAndSoOn();
        this.addElement();
        this.addListener();
        this.setFrameSelf();
    }

    //设置字体 颜色 背景 布局 等
    protected abstract void setFontAndSoOn();

    //将属性添加到窗体
    protected abstract void addElement();

    //添加事件监听
    protected abstract void addListener();

    //设置窗体自身
    protected abstract void setFrameSelf();
}
