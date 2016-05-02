package activities;

import activities.battle.BattleMainActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import servlet.BattleServlet;
import taira.japaneseChess.japanesechess.R;
import taira.japaneseChess.japanesechess.R.id;

@SuppressLint("NewApi")
public class MainActivity extends Activity{

	/* Server終了ボタンを押さないと、Serverが終了せず、接続したままの状態となるので要修正 */

	//Serverコネクション
	private Socket socket = null;
	private BufferedReader inMessage;		//データ受け取り用変数
	private PrintWriter outMessage;			//送信データ格納変数
	private String command = null;			//コマンド

	//コンポーネント
	private Button battleStartButton;
	private Button connectionServerButton;
	private Button disConnectionServerButton;
	private Button outNumberButton1;
	private Button outNumberButton2;
	private TextView showInNumber;

	private BattleServlet battleServlet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		battleStartButton = (Button) findViewById(id.battleStartButton);
		connectionServerButton = (Button) findViewById(id.connectionServerButton);
		disConnectionServerButton = (Button) findViewById(id.disConnectionServerButton);
		outNumberButton1 = (Button) findViewById(id.outNumberButton1);
		outNumberButton2 = (Button) findViewById(id.outNumberButton2);
		showInNumber = (TextView) findViewById(id.showInNumber);

		//バトルアクティビティへインテント
		battleStartButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent i = new Intent(getApplicationContext(),BattleMainActivity.class);
            	startActivity(i);
            }
        });

	}

	@Override
	public void onStart() {
		super.onStart();

		//Serverと接続開始
		connectionServerButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				command = "ConnectionServer";
				new BattleServlet().execute(MainActivity.this);
			}
		});

		//Serverと接続終了
		disConnectionServerButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				command = "ServerExit";
				new BattleServlet().execute(MainActivity.this);
			}
		});

		//メッセージ1を送信する
		outNumberButton1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				command = "メッセージ1";
				new BattleServlet().execute(MainActivity.this);
			}
		});

		//メッセージ2を送信する
		outNumberButton2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				command = "メッセージ2";
				new BattleServlet().execute(MainActivity.this);
			}
		});

	}

	//ゲッター・セッター
	public String getCommand(){
		return command;
	}

	public void setShowInNumber(String message){
		showInNumber.setText(message);
	}

	public Socket getSocket(){
		return socket;
	}

	public void setSocket(Socket socket){
		this.socket = socket;
	}

	public BufferedReader getInMessage() {
		return inMessage;
	}

	public void setInMessage(BufferedReader inMessage) {
		this.inMessage = inMessage;
	}

	public PrintWriter getOutMessage() {
		return outMessage;
	}

	public void setOutMessage(PrintWriter outMessage) {
		this.outMessage = outMessage;
	}
}
