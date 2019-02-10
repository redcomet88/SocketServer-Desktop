package com.jzj.socket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Description: TCP Server UI
 * @Author: redcomet
 * @Date: 2019-01-18-12:19
 */

public class TcpServerUI extends  JFrame{


    private JButton jb1;
    private JButton jb2;
    private JButton jb3;
    private JTextField jText1;
    private TcpServer server;

    public static void main(String[] args) {
        TcpServerUI frame = new TcpServerUI();
    }

    public TcpServerUI(){
        setTitle("TCP-Server"); //建立一个窗口
        FlowLayout fl = new FlowLayout(); //使用流布局
        setLayout(fl);//修改布局管理
        jb1 = new JButton("启动服务"); //创建一个按钮
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startService(e);
            }
        });
        add(jb1); //把按钮jb1放入窗口
        jb2 = new JButton("停止服务");//创建一个按钮
        jb2.setEnabled(false);
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopService(e);
            }
        });
        add(jb2);//把按钮jb2放入窗口
        jText1 = new JTextField();
        jText1.setFont(new Font("宋体", Font.PLAIN, 20));
        jText1.setColumns(20);
        add(jText1);
        jb3 = new JButton("发送");//创建一个按钮
        add(jb3);//把按钮jb2放入窗口
        setSize(600, 300); //设置窗口的大小
        setLocation(300,200);//设置窗口的初始位置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口
        setVisible(true); //显示窗口
    }

    private void startService(ActionEvent evt) {
        jb1.setEnabled(false);
        jb2.setEnabled(true);
        server = new MyTcpServer(1234);
        System.out.println("--------Server Started--------");
        server.start();
    }

    private void stopService(ActionEvent evt) {
        jb1.setEnabled(true);
        jb2.setEnabled(false);
        System.out.println("--------Server Stoped--------");
        server.stop();
    }
}
