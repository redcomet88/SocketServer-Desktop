package com.jzj.socket;

/**
 * @Description:
 * @Author: redcomet
 * @Date: 2019-01-18-11:41
 */

public class MyTcpServer extends TcpServer {
    /**
     * 实例化
     *
     * @param port 监听的端口
     */
    public MyTcpServer(int port) {
        super(port);
    }

    @Override
    public void onConnect(SocketTransceiver client) {
        printInfo(client, "Connect");
    }

    @Override
    public void onConnectFailed() {
        System.out.println("Client Connect Failed");
    }

    @Override
    public void onReceive(SocketTransceiver client, String s) {
        printInfo(client, "Send Data: " + s);
        client.send(s);
    }

    @Override
    public void onDisconnect(SocketTransceiver client) {
        printInfo(client, "Disconnect");
    }

    @Override
    public void onServerStop() {
        System.out.println("--------Server Stopped--------");
    }

    static void printInfo(SocketTransceiver st, String msg) {
        System.out.println("Client " + st.getInetAddress().getHostAddress());
        System.out.println("  " + msg);
    }
}
