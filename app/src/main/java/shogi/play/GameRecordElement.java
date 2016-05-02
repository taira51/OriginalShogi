package shogi.play;

import shogi.stage.*;
import shogi.stage.koma.*;

public class GameRecordElement {
	int turnNum = 0;
	String player = "先手";
	String lastMoveIndex = "";
	Koma lastMoveKoma;
	String strKifu = "";
	Stage gameRecord;
	
	//コンストラクタ:最新の一手を更新する
	GameRecordElement(int turnNum, String lastMoveIndex, Koma lastMoveKoma, Stage gameRecord){
		this.turnNum = turnNum;
		this.player = changePlayer();		
		this.lastMoveIndex = lastMoveIndex;
		this.lastMoveKoma = lastMoveKoma;
		strKifu = String.valueOf(turnNum) + ":" + lastMoveIndex + lastMoveKoma.getKomaName();
		this.gameRecord = gameRecord;
	}
	
	//指し手が先手か後手かを返す
	private String changePlayer(){
		if(turnNum % 2 == 1){
			return "先手";
		}else{
			return "後手";
		}
	}
	


}
