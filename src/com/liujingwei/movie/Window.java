package com.liujingwei.movie;

import java.awt.*;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Window extends JFrame {

    Date now = new Date();
    Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int date = c.get(Calendar.DATE);
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);
    int second = c.get(Calendar.SECOND);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
    JTextField room;
    JTextField row;
    JTextField col;
    JButton hold;
    JButton relese;

    JFrame frame =new JFrame();

    JPanel panel_up=new JPanel();
    JPanel panel_bottom=new JPanel();
    JPanel panel_center=new JPanel();

    private JTextArea area = new JTextArea("现在时间是："+ df.format(now)+
            "\r\n现在还有 5 场电影:"+
            "\r\n请输入放映厅号（1-5)，行号（1-10），列号（1-10）"+
            "\r\n点击售票座位锁定，点击清空座位释放！");


    public Window(){

        GUIinit();
        area.setEditable(false);

    }
    JTextField fd_room=new JTextField(10);
    JTextField fd_row=new JTextField(10);
    JTextField fd_col=new JTextField(10);
    JButton btn_hold=new JButton("售出座位");
    JButton btn_relese=new JButton("清空座位");

    void GUIinit(){
        frame.setTitle("电影院座位管理系统设计");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        frame.setLocation(750, 250);
        frame.setSize(300, 400);
        frame.add(panel_up,BorderLayout.NORTH);
        frame.add(panel_bottom,BorderLayout.SOUTH);
        frame.add(panel_center,BorderLayout.CENTER);

        panel_up.setLayout(new GridLayout(3,2));
        JLabel N_room =new JLabel("输入放映厅号:");
        JLabel N_row=new JLabel("输入行号:");
        JLabel N_col=new JLabel("输入列号:");
		/*小面板1 2 别分用来存放 用户名的输入框以及密码的输入框
		小面板1 2 内部的布局使用FlowLayout布局*/
        JPanel panel_small_1 =new JPanel();
        JPanel panel_small_2 =new JPanel();
        JPanel panel_small_3 =new JPanel();

        panel_up.add(N_room);
        panel_up.add(panel_small_1);
        panel_up.add(N_row);
        panel_up.add(panel_small_2);
        panel_up.add(N_col);
        panel_up.add(panel_small_3);



        panel_small_1.setLayout(new FlowLayout());
        panel_small_1.add(fd_room);
        panel_small_2.setLayout(new FlowLayout());
        panel_small_2.add(fd_row);
        panel_small_3.setLayout(new FlowLayout());
        panel_small_3.add(fd_col);

        panel_center.setLayout(new FlowLayout());
        panel_center.add(area);

        panel_bottom.setLayout(new FlowLayout());

        //Does cancle mean "clear the textfile" or "close the frame"?
        panel_bottom.add(btn_hold);
        panel_bottom.add(btn_relese);

        frame.setVisible(true);
    }

    /**
     * 方法名：getFieldText()
     * @param
     * @return string
     * 功能：获取窗口的TextField中的字符串
     * */
    public String getField1Text()
    {
        return fd_room.getText().toString();
    }

    public String getField2Text()
    {
        return fd_row.getText().toString();
    }

    public String getField3Text()
    {
        return fd_col.getText().toString();
    }

    /**
     * 方法名：getButton()
     * @param
     * @return JButton
     * 功能：获得该窗口中的按钮
     * */
    public JButton getButtonHold()
    {
        return btn_hold;
    }

    public JButton getButtonRelese()
    {
        return btn_relese;
    }

    /**
     * 方法名：getJTextArea()
     * @param
     * @return JTextArea
     * 功能：返回窗口中的JTextArea
     * */
    public JTextArea getJTextArea()
    {
        return area;
    }
}

