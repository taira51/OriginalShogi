package activities.battle;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import common.Config;

public class BattleMainActivityServlet extends AsyncTask<BattleMainActivity, Void, String>{
    private BattleMainActivity battleMainActivity;
    private String message = null;          //通信間のメッセージを格納する

    @Override
    protected String doInBackground(BattleMainActivity ... params) {
        try {
            this.battleMainActivity = params[0];

            //Serverへ接続
            if(battleMainActivity.getSocket() == null){
                connectionServer();
            }

            //メッセージの送信
            battleMainActivity.getOutMessage().println(battleMainActivity.getAction());

            //サーバーからの応答処理
            message = battleMainActivity.getInMessage().readLine();
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
    }

    //Serverと接続を開始するメソッド
    private void connectionServer(){
        try {
            battleMainActivity.setSocket(new Socket(Config.ADDRESS_BATTLE, Config.PORT_BATTLE));
            System.out.println("サーバーに接続しました。(接続したアドレス:" + battleMainActivity.getSocket().getRemoteSocketAddress()+")");

            //データ受け取り用変数
            battleMainActivity.setInMessage(new BufferedReader(new InputStreamReader(battleMainActivity.getSocket().getInputStream())));
            //送信データ格納用変数
            battleMainActivity.setOutMessage(new PrintWriter(battleMainActivity.getSocket().getOutputStream(), true));

        }catch (IOException e) {
            System.out.println("エラー:connectionServer():サーバーの接続に失敗しました。");
        }
    }

    //Serverとの接続を切断するメソッド
    private void disConnectionServer(){
        System.out.println(battleMainActivity.getSocket().getRemoteSocketAddress()+"との接続を切断します。");
        battleMainActivity.setSocket(null);
        battleMainActivity.setInMessage(null);
        battleMainActivity.setOutMessage(null);
        System.out.println("Serverとの接続を切断しました。");
    }

}
