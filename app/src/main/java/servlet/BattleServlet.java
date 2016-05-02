package servlet;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import activities.MainActivity;
import common.Config;

public class BattleServlet extends AsyncTask<MainActivity, Void, String>{
    private MainActivity mainActivity;
    private String message = null;          //通信間のメッセージを格納する

    @Override
    protected String doInBackground(MainActivity ... params) {
        try {
            this.mainActivity = params[0];

            //Serverへ接続
            if(mainActivity.getSocket() == null){
                connectionServer();
            }

            //メッセージの送信
            mainActivity.getOutMessage().println(mainActivity.getCommand());

            //サーバーからの応答処理
            message = mainActivity.getInMessage().readLine();
            System.out.println(message);
            if(message.equals("ResponseServerExit")){
                disConnectionServer();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            message = null;
            disConnectionServer();
        }

        return message;
    }

    public void onPostExecute(String message) {
        //Viewの更新
        mainActivity.setShowInNumber(message);
    }

    //Serverと接続を開始するメソッド
    private void connectionServer(){
        try {
            mainActivity.setSocket(new Socket(Config.ADDRESS_BATTLE, Config.PORT_BATTLE));
            System.out.println("サーバーに接続しました。(接続したアドレス:" + mainActivity.getSocket().getRemoteSocketAddress()+")");

            //データ受け取り用変数
            mainActivity.setInMessage(new BufferedReader(new InputStreamReader(mainActivity.getSocket().getInputStream())));
            //送信データ格納用変数
            mainActivity.setOutMessage(new PrintWriter(mainActivity.getSocket().getOutputStream(), true));

        }catch (IOException e) {
            System.out.println("エラー:connectionServer():サーバーの接続に失敗しました。");
        }
    }

    //Serverとの接続を切断するメソッド
    private void disConnectionServer(){
        System.out.println(mainActivity.getSocket().getRemoteSocketAddress()+"との接続を切断します。");
        mainActivity.setSocket(null);
        mainActivity.setInMessage(null);
        mainActivity.setOutMessage(null);
        System.out.println("Serverとの接続を切断しました。");
    }

}
