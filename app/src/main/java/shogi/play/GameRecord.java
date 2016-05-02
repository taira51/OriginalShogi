package shogi.play;

import java.util.ArrayList;
import shogi.stage.*;
import shogi.stage.koma.*;

public class GameRecord {
	ArrayList<GameRecordElement> gameRecordList = new ArrayList<GameRecordElement>();
	int counter = 0;
	
	//最新の一手をstageRecordListに追加する
	void addGameRecordList(String index, Koma koma, Stage stage){
		counter++;
		gameRecordList.add(new GameRecordElement(counter,index,koma,stage));
	}
	
	//gameRecordListを返す
	ArrayList<GameRecordElement> getGameRecordList(){
		return gameRecordList;
	}
	
	//引数に対応するGameRecordElementを返す
	GameRecordElement getGameRecordElement(int turnNum){
		return gameRecordList.get(turnNum);
	}
}
