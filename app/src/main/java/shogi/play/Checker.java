package shogi.play;

import java.util.ArrayList;

import shogi.stage.*;
import shogi.stage.koma.*;

public class Checker {
	
	//引数の玉インスタンスが王手かどうかを判定する（王手ならtrueを返す）
	public static boolean outeCheck(Stage stage, Koma gyokuInstance){
		
		//引数の玉インスタンスが存在する座標を格納する
		String gyokuIndex = stage.getBoard().searchKomaInstance(gyokuInstance);
		
		//王手の判定に使用する変数の宣言
		String indexName;	//効きを調べる敵の駒の座標が格納される
		ArrayList<String> attack = new ArrayList<String>();	//効きを調べる敵の駒の効いてる座標が格納される
		
		//全てのマス分繰り返す（81マス）
		for(int i=Board.getMapRow().get("一"); i<=Board.getMapRow().get("九"); i++){		//-----for文1
			for(int j=Board.getMapColumn().get("9"); j<=Board.getMapColumn().get("1"); j++){	//-----for文2
	
				//該当座標に駒が存在する場合分岐させる
				if(stage.getBoard().getBoardElement()[i][j].getKoma() != null){	//-----if文1
					
					//該当の駒の所有者が引数の玉の所有者と違う場合分岐させる
					if(stage.getBoard().getBoardElement()[i][j].getKoma().isPlayer() != gyokuInstance.isPlayer()){		//-----if文2

						//対象の駒が存在する座標と、その駒の効いている座標のリストを各変数に格納する
//						indexName = 
						attack = Action.getPossibleMoveIndex(Board.convertIndexName(i, j), stage.getBoard());
						
						//該当の駒の移動可能座標に引数の玉インスタンスが存在する場合trueを返す
						for(int k=0; k<attack.size(); k++){
							if(gyokuIndex == attack.get(k)){
								return true;		//相手の駒の効きに自分の玉が存在する → 王手
							}
						}
							
					}	//-----if文2終了
				}	//-----if文1終了
			}	//-----for文2終了
		}	//-----for文1終了
		
		System.out.println("デバッグ:outeCheck:引数の玉インスタンスに王手はかかっていません。");
		return false;	//引数の玉に王手はかかってないのでfalseを返す
	}
}
