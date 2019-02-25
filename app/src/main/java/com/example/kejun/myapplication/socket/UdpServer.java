package com.example.kejun.myapplication.socket;

import android.util.Log;


import com.example.kejun.myapplication.interfaces.ProgressListener;
import com.example.kejun.myapplication.utils.Configuration;
import com.example.kejun.myapplication.utils.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * Created by kejun
 */
public class UdpServer {
    private ProgressListener progressListener;
    private int              port;
    private String           TAG;
    private  DatagramSocket serverSockent;

    public UdpServer(int port, ProgressListener progressListener) {
        this.port = port;
        this.TAG = UdpServer.class.getName();
        this.progressListener = progressListener;
    }

    /**
     *
     *注释描述:等待发送方，发送字段开udp服务端等待客户端的连接
     */
    public DatagramPacket waitClient() {
        DatagramPacket packet = null;
        byte[] recvBuf = new byte[Configuration.STRING_BUF_LEN];
        try {
            serverSockent = new DatagramSocket(port);//设置服务器端口, 监听广播信息
            serverSockent.setSoTimeout(Configuration.WAITING_TIME * 1000);//等待10秒  设置阻塞的时间
            DatagramPacket     message = new DatagramPacket(recvBuf, recvBuf.length); // 接收数据报文到datagramPacket中
            serverSockent.receive(message);//接收client的广播信息 /读取接收到得数据 包,如果客户端没有发送数据包，那么该线程就停滞不继续，这个同样也是阻塞式的
            String strmsg = Utils.getMessage(message.getData());
            message.setData((Configuration.currentTcpPort + Configuration.DELIMITER).getBytes("utf-8"));//将服务器的主机名发送给client
            serverSockent.send(message);//回复信息tcp要使用的Tcp端口给client
            message.setData(strmsg.getBytes("utf-8"));
             packet = message;
        } catch (SocketTimeoutException e) {
            progressListener.updateProgress(-3, 100, 100, 999);
            Log.e(TAG, "udp server 等待超时");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "编码解释错误" + e.getMessage());

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return packet;//将client发送的数据包返回给调用者, 里面包含client的地址, 端口, 主机名

    }

    /**
     * 关闭资源
     */
    public void close() {
        serverSockent.close();
    }
}
