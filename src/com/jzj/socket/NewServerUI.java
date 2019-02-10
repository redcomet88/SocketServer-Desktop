package com.jzj.socket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @Description: TCP Server UI
 * @Author: redcomet
 * @Date: 2019-01-18-12:19
 */

public class NewServerUI extends  JFrame{


    private JButton jb1;
    private JButton jb2;
    private JButton jb3;
    private JTextField jtv1;
    private JTextArea textArea;
    private TcpServer server;

    public static void main(String[] args) {
        NewServerUI frame = new NewServerUI();
    }

    public NewServerUI(){

        setSize(450, 300); //设置窗口的大小
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setResizable(false);

        setTitle("TCP-Server"); //窗口标题

        GridBagLayout gridBagLayout=new GridBagLayout(); //实例化布局对象
        setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints=new GridBagConstraints();//实例化这个对象用来对组件进行管理
        //该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        gridBagConstraints.fill=GridBagConstraints.BOTH;
        // NONE：不调整组件大小。
        // HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        // VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        // BOTH：使组件完全填满其显示区域。
        // 组件1(gridx,gridy)组件的左上角坐标，
        // gridwidth，gridheight：组件占用的网格行数和列数
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        jb1 = new JButton("启动服务"); //创建一个按钮
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startService(e);
            }
        });
        gridBagLayout.setConstraints(jb1, gridBagConstraints);

        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        jb2 = new JButton("停止服务");//创建一个按钮
        jb2.setEnabled(false);
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopService(e);
            }
        });
        gridBagLayout.setConstraints(jb2, gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=3;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.gridheight=2;
        textArea = new JTextArea(3, 20); // 设置自动换行
        textArea.setLineWrap(true);//添加到内容面板
        gridBagLayout.setConstraints(textArea, gridBagConstraints);

        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy=3;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=2;
        jb3 = new JButton("发送");//创建一个按钮
        jb3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage(e);
            }
        });
        gridBagLayout.setConstraints(jb3, gridBagConstraints);

        add(jb1);
        add(jb2);
        add(textArea);
        add(jb3);
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

    private void sendMessage(ActionEvent evt) {
        System.out.println("--------Send Message--------");
        ArrayList<SocketTransceiver> clients = (ArrayList<SocketTransceiver>) server.getClients();
        String msg = textArea.getText();
        clients.get(0).send(msg);
    }
}
