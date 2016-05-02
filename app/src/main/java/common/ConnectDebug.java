package common;

import java.net.Socket;

public class ConnectDebug {

    public static void isConnecting(Socket socket){
        if(socket != null){
            System.out.println("デバッグ:ConnectDebug.java:isConnecting():" + socket.getRemoteSocketAddress());
        }else{
            System.out.println("デバッグ:ConnectDebug.java:isConnecting():ソケットはnullです。");
        }
    }
}
