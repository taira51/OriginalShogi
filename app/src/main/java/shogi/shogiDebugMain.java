package shogi;

import java.io.IOException;
import java.net.Socket;

import common.Config;
import shogi.stage.*;

public class shogiDebugMain {
	public static void main(String[] args) {
		Stage stage = new Stage();

		try {
			Socket socket = new Socket(Config.ADDRESS_BATTLE, Config.PORT_BATTLE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//----------getPossibleMoveメソッドのデバッグ開始
//		String indexName = "2八";	//←座標を指定すると移動可能範囲を返す
//		Board boardInstance = stage.getBoardElement();
//		System.out.println("移動可能座標"+Action.getPossibleMoveIndex(indexName, boardInstance));
		//----------getPossibleMoveメソッドのデバッグ終了
		
		//----------王手チェックのデバッグ開始
//		Checker.outeCheck(stage,stage.getAllKomaInstance().get("Gyoku1"));
		
		//----------王手チェックのデバッグ終了
		
		
		
		
		
		//以下デバッグが完了したデバッグ作業
		//----------convertIndexIntegerのデバッグ開始
//		String indexName = stage.getBoardElement().searchKomaInstance(stage.getAllKomaInstance().get("Gyoku1"));
//		int[] indexInteger = stage.getBoardElement().convertIndexInteger(indexName);
//		System.out.println("convertIndexIntegerのデバッグ:" + indexName + indexInteger[0] + indexInteger[1]);
		//----------convertIndexIntegerのデバッグ終了
		
		//----------convertIndexNameのデバッグ開始
//		String indexName = stage.getBoardElement().searchKomaInstance(stage.getAllKomaInstance().get("Hisya1"));
//		int[] indexInteger = stage.getBoardElement().convertIndexInteger(indexName);
//		String indexName2 = stage.getBoardElement().convertIndexName(indexInteger[0], indexInteger[1]);
//		System.out.println("convertIndexNameのデバッグ:" + indexName +"→"+ indexInteger[0] + indexInteger[1] +"→" +indexName2);
		//----------convertIndexNameのデバッグ終了
		
		//----------searchKomaInstanceメソッドのデバッグ開始
//		Koma koma = stage.getAllKomaInstance().get("Gyoku1");
//		System.out.println(stage.getBoardElement().searchKomaInstance(koma));
		//----------searchKomaInstanceメソッドのデバッグ終了
		
		//----------各メソッドのデバッグ開始
//		stage.getBoardElement().dispBoardKoma();		//ボードに配置されている駒の名前を表示
//		stage.getBoardElement().dispBoardIndex();		//ボードの座標を表示
//		stage.getMochiGoma().dispMochiGoma();	//持ち駒の情報を表示
		//----------各メソッドのデバッグ終了
	
		
	}

}
