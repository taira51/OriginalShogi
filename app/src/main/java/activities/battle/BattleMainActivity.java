package activities.battle;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import shogi.play.Action;
import shogi.stage.Stage;
import taira.japaneseChess.japanesechess.R;

public class BattleMainActivity extends Activity {

    //フィールド　ビュー
    TableLayout boardTableLayout;   //盤面のテーブルレイアウト
    ViewGroup vg;
    TableRow[] boardTableRow = new TableRow[9];         //テーブルレイアウトに含まれるTableRow
    ImageButton[][] boardElement = new ImageButton[9][9];   //盤面の81マスを配列で準備
    View lastTouchView = null;      //盤面がアクティブな時に最後にタッチしたViewが格納される
    View lastMoveView = null;       //最後に移動した駒が存在する座標のViewが格納される

    //フィールド　対局情報
    Stage stage = new Stage();      //実際の盤面情報
    boolean player = true;          //先手・後手（trueで先手）
    boolean turn = true;            //現在のターン（trueで自分のターン）
    ArrayList<String> moveIndexList = new ArrayList<String>();

    //boardElementと座標のHashMap
    private static final HashMap<String,String> indexKey = new HashMap<String,String>();

    //対局進行
    String beforeIndex = null;      //駒の移動前の座標
    String afterIndex = null;       //駒の移動先の座標
    String action = null;           //指し手（投了）

    //Serverコネクション
    Socket socket = null;               //ソケット
    BufferedReader inMessage = null;    //入力
    PrintWriter outMessage = null;      //出力

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //xmlと盤面を関連付ける
        setContentView(R.layout.activity_battle_main);

        //TableLayoutの関連付け
        boardTableLayout = (TableLayout) findViewById(R.id.boardTableLayout);
        vg = (ViewGroup)findViewById(R.id.boardTableLayout);

        //TableRowの関連付け
        for(int i=0; i<9; i++){
            boardTableRow[i] = (TableRow)vg.getChildAt(i);
        }

        //マス要素であるImageButtonの関連付け
        Integer tagCounter = 0;
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                boardElement[i][j] = (ImageButton)boardTableRow[i].getChildAt(j);
                boardElement[i][j].setTag(tagCounter++);

                //ボードのマスをタッチした時のリスナー処理
                boardElement[i][j].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //タッチされたマスのImageButtonのViewを返す
                        boardElementListener(v);
                    }
                });

            }
        }

        //HashMap Stageクラスとレイアウトの座標の関連付け
        indexKey.put("0","9一");
        indexKey.put("1","8一");
        indexKey.put("2","7一");
        indexKey.put("3","6一");
        indexKey.put("4","5一");
        indexKey.put("5","4一");
        indexKey.put("6","3一");
        indexKey.put("7","2一");
        indexKey.put("8","1一");
        indexKey.put("9","9二");
        indexKey.put("10","8二");
        indexKey.put("11","7二");
        indexKey.put("12","6二");
        indexKey.put("13","5二");
        indexKey.put("14","4二");
        indexKey.put("15","3二");
        indexKey.put("16","2二");
        indexKey.put("17","1二");
        indexKey.put("18","9三");
        indexKey.put("19","8三");
        indexKey.put("20","7三");
        indexKey.put("21","6三");
        indexKey.put("22","5三");
        indexKey.put("23","4三");
        indexKey.put("24","3三");
        indexKey.put("25","2三");
        indexKey.put("26","1三");
        indexKey.put("27","9四");
        indexKey.put("28","8四");
        indexKey.put("29","7四");
        indexKey.put("30","6四");
        indexKey.put("31","5四");
        indexKey.put("32","4四");
        indexKey.put("33","3四");
        indexKey.put("34","2四");
        indexKey.put("35","1四");
        indexKey.put("36","9五");
        indexKey.put("37","8五");
        indexKey.put("38","7五");
        indexKey.put("39","6五");
        indexKey.put("40","5五");
        indexKey.put("41","4五");
        indexKey.put("42","3五");
        indexKey.put("43","2五");
        indexKey.put("44","1五");
        indexKey.put("45","9六");
        indexKey.put("46","8六");
        indexKey.put("47","7六");
        indexKey.put("48","6六");
        indexKey.put("49","5六");
        indexKey.put("50","4六");
        indexKey.put("51","3六");
        indexKey.put("52","2六");
        indexKey.put("53","1六");
        indexKey.put("54","9七");
        indexKey.put("55","8七");
        indexKey.put("56","7七");
        indexKey.put("57","6七");
        indexKey.put("58","5七");
        indexKey.put("59","4七");
        indexKey.put("60","3七");
        indexKey.put("61","2七");
        indexKey.put("62","1七");
        indexKey.put("63","9八");
        indexKey.put("64","8八");
        indexKey.put("65","7八");
        indexKey.put("66","6八");
        indexKey.put("67","5八");
        indexKey.put("68","4八");
        indexKey.put("69","3八");
        indexKey.put("70","2八");
        indexKey.put("71","1八");
        indexKey.put("72","9九");
        indexKey.put("73","8九");
        indexKey.put("74","7九");
        indexKey.put("75","6九");
        indexKey.put("76","5九");
        indexKey.put("77","4九");
        indexKey.put("78","3九");
        indexKey.put("79","2九");
        indexKey.put("80","1九");


    }

    //メイン処理
    @Override
    public void onStart() {
        super.onStart();
        updateView();   //盤面のViewの更新
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    //Viewファンクション
    //Stageインスタンスを元に盤面を更新するメソッド
    private void updateView(){
        String imageResource;

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                //パスの初期化
                imageResource = "空";

                //パスの生成
                if(stage.getBoard().getBoardElement()[i+1][j+1].getKoma() != null){
                    imageResource = stage.getBoard().getBoardElement()[i+1][j+1].getKoma().getPictName();

                    //視点
                    if(player == true){
                        //先手視点 - 自分の駒か敵の駒か
                        if(player == stage.getBoard().getBoardElement()[i+1][j+1].getKoma().isPlayer()){
                            imageResource = ("b_" + imageResource);
                        }else{
                            imageResource = ("w_" + imageResource);
                        }
                    }else{
                        //後手視点 - 自分の駒か敵の駒か
                        if(player == stage.getBoard().getBoardElement()[i+1][j+1].getKoma().isPlayer()){
                            imageResource = ("w_" + imageResource);
                        }else{
                            imageResource = ("b_" + imageResource);
                        }
                    }

                }

                //ImageButtonに画像をセット
                boardElement[i][j].setImageResource(getResources().getIdentifier(imageResource, "drawable", getPackageName()));
            }
        }
    }

    //アクティブな盤面をタッチされた時のリスナー処理
    private void boardElementListener(View v) {
        //座標を数値型に変換する時の保管用変数
        int indexInteger[] = new int[2];

        //すでに色のついたマスがある場合は通常の状態に戻す（Viewの初期化）
        if (lastTouchView != null) {
            //前にタッチした座標の色を通常に戻す
            lastTouchView.setBackgroundResource(R.drawable.board_focus_normal);

            //前にタッチした駒の移動可能座標のマスの色を通常に戻す
            for (int i = 0; i < moveIndexList.size(); i++) {
                indexInteger = stage.getBoard().convertIndexInteger(moveIndexList.get(i));
                boardElement[indexInteger[0] - 1][indexInteger[1] - 1].setBackgroundResource(R.drawable.board_focus_normal);
            }

            //最後にタッチされたマスと同じマスをタッチした場合は、盤面の初期化だけで処理を終える
            if(lastTouchView == v){
                lastTouchView = null;
                return;
            }

            //駒の移動処理(駒をHOLDした状態で移動可能範囲をタッチした場合、処理される)
            for(int i=0; i<moveIndexList.size(); i++){
                if(String.valueOf(indexKey.get(v.getTag().toString())).equals(moveIndexList.get(i))) {

                    //makeActionの引数作成:beforeIndex
                    indexInteger = stage.getBoard().convertIndexInteger(beforeIndex);

                    //makeActionの引数作成:afterIndex
                    afterIndex = moveIndexList.get(i);

                    //makeActionの引数作成:移動対象の駒の名前
                    String Name = stage.getBoard().getBoardElement()[indexInteger[0]][indexInteger[1]].getKoma().getKomaName();

                    //makeActionの引数作成
                    String naru = null;

                    //駒の移動処理
                    action = makeActionMove(beforeIndex, afterIndex, Name, naru);
                    System.out.println("デバッグ:指し手:" + action);
                    Action.actionMove(makeActionMove(beforeIndex, afterIndex, Name, naru), stage);

                    //盤面の更新
                    updateView();

                    //最後に移動した駒の存在する座標の背景に色をつける
                    if(lastMoveView != null){
                        lastMoveView.setBackgroundResource(R.drawable.board_focus_normal);
                    }
                    lastMoveView = v;
                    lastMoveView.setBackgroundResource(R.drawable.board_focus_lastmove);
                    lastTouchView = null;

                    //指し手送信
                    new BattleMainActivityServlet().execute(BattleMainActivity.this);

                    //ターンチェンジ
//                    changeTurn();

                    return;
                }
            }

        }

        //タッチされた座標を数値型に変換する
        indexInteger = stage.getBoard().convertIndexInteger(String.valueOf(indexKey.get(v.getTag().toString())));
        beforeIndex = String.valueOf(indexKey.get(v.getTag().toString()));
//        System.out.println("デバッグ:タッチされた座標:" + beforeIndex +"(" + indexInteger[0] + indexInteger[1] + ")");

        //タッチしたマスの色付け
        if (stage.getBoard().getBoardElement()[indexInteger[0]][indexInteger[1]].getKoma() != null) {
            if (player == stage.getBoard().getBoardElement()[indexInteger[0]][indexInteger[1]].getKoma().isPlayer()) {
                v.setBackgroundResource(R.drawable.board_focus_touch_my);   //自分の駒をタッチ
            } else {
                v.setBackgroundResource(R.drawable.board_focus_touch);      //相手の駒をタッチ
            }
        } else {
            v.setBackgroundResource(R.drawable.board_focus_touch);          //駒の存在しないマスをタッチ
        }

        //タッチした駒の移動可能マスに色をつける
        if (stage.getBoard().getBoardElement()[indexInteger[0]][indexInteger[1]].getKoma() != null) {
            if (player == stage.getBoard().getBoardElement()[indexInteger[0]][indexInteger[1]].getKoma().isPlayer()) {
                indexInteger = null;

                //移動可能座標の取得
                moveIndexList = null;
                moveIndexList = Action.getPossibleMoveIndex(String.valueOf(indexKey.get(v.getTag().toString())), stage.getBoard());

                //移動可能座標の色付け
                for (int i = 0; i < moveIndexList.size(); i++) {
                    indexInteger = stage.getBoard().convertIndexInteger(moveIndexList.get(i));
                    boardElement[indexInteger[0] - 1][indexInteger[1] - 1].setBackgroundResource(R.drawable.board_focus_moveable);
//                    System.out.println("デバッグ:BattleMainActivity:移動可能範囲" + (indexInteger[0]) + "" + (indexInteger[1]) + "");
                }

            }
        }

        //最後にタッチされたImageButtonを格納する
        lastTouchView = v;

    }

    //ターンの入れ替えメソッド
    private void changeTurn (){
        //turnを反転させる
        if(this.turn){ this.turn = false; } else { this.turn = true; }
        //盤面のアクティブ状態の変化処理
        for(int i=0; i<9; i++){for(int j=0; j<9; j++){boardElement[i][j].setEnabled(this.turn);}}
    }

    //1手分の指し手を棋譜形式で生成する
    private String makeActionMove(String beforeIndex, String afterIndex, String moveKoma, String powerUp){
        //移動後座標,移動駒,成・不成り,移動前座標
        String action = afterIndex +","+ moveKoma +",";
        if(powerUp != null){ action = action + powerUp +","; } else { action = action + "--,"; }
        action = action +beforeIndex;
        return action;
    }

    private String makeActionPut(){
        String action = null;

        return action;
    }

    //ゲッター・セッター
    public Socket getSocket(){ return socket; }

    public void setSocket(Socket socket){ this.socket = socket; }

    public BufferedReader getInMessage() { return inMessage; }

    public void setInMessage(BufferedReader inMessage) { this.inMessage = inMessage; }

    public PrintWriter getOutMessage() { return outMessage; }

    public void setOutMessage(PrintWriter outMessage) { this.outMessage = outMessage; }

    public String getAction(){ return action; }

}