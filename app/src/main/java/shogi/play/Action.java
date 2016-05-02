package shogi.play;

import java.util.ArrayList;

import shogi.stage.Board;
import shogi.stage.Stage;
import shogi.stage.koma.*;

public class Action {
	
	//盤面の駒を移動させる
	public static void actionMove(String actionMove, Stage stage){
		//棋譜形式を読み込みできる状態に配列にする
		String[] action = actionMove.split(",");

		//盤面の駒を移動する処理に必要な変数の生成
		int[] indexInteger = new int[2];
		String beforeIndex = action[3];
		String afterIndex = action[0];

		indexInteger = stage.getBoard().convertIndexInteger(beforeIndex);
		Koma targetKoma = stage.getBoard().getBoardElement()[indexInteger[0]][indexInteger[1]].getKoma();

		//beforeIndexに存在する駒を削除する
		stage.getBoard().getBoardElement()[indexInteger[0]][indexInteger[1]].setKoma(null);

		//afterIndexに駒をコピーする
		indexInteger = stage.getBoard().convertIndexInteger(afterIndex);
		stage.getBoard().getBoardElement()[indexInteger[0]][indexInteger[1]].setKoma(targetKoma);


	}
	
//	//持ち駒台の駒を盤面に打つ
//	void actionPut(){
//		
//	}
	
	//盤面の駒の移動可能座標を返す	引数のdoOuteCheckは、返り値の更新の際に王手チェックを行うか行わいかを指定する
	public static ArrayList<String> getPossibleMoveIndex(String indexName, Board boardInstance){
		/* 1.駒の移動可能座標を返すメソッド
		 * 2.getPossibleMoveIndexからのみ呼び出されるサブルーチンupdatePossibleMoveIndexListメソッドが存在する
		 * 3.サブルーチンは処理する方向ごとに呼び出され、王手のチェックは行わずにあくまで盤面の状況に沿った駒の移動可能な座標を返すメソッド
		 */
		
		//初期設定
		ArrayList<String> possibleMoveIndexList = new ArrayList<String>();				//return用変数の宣言
		int indexRow = Board.convertIndexInteger(indexName)[0];							//引数の行座標をint型に変換
		int indexColumn = Board.convertIndexInteger(indexName)[1];				//引数の列座標をint型に変換
		Koma targetKoma = boardInstance.getBoardElement()[indexRow][indexColumn].getKoma();	//引数の座標に存在する駒インスタンスをtargetKomaに格納
		int[][] direction;						//方向を表す配列
		if(targetKoma.isPlayer()){				//targetKomaが先手の場合true
			direction = Board.getDirectionSente();
		}else{
			direction = Board.getDirectionGote();
		}
		int moveRength = 0;				//対応する方向への移動距離が格納される
		int[] nextIndex = new int[2];	//方向に対応した次の座標を示す値が格納される（要素0に行座標）
		
		//全ての方向に対応する値を盤面の状態に合わせて修正していく
		for(int i=0; i<10; i++){
			updatePossibleMoveIndexList(i,indexRow,indexColumn,moveRength,targetKoma,nextIndex,direction,boardInstance,possibleMoveIndexList);
		}
		
		return possibleMoveIndexList;
	}
	
	//getPossibleMoveIndexのサブルーチン	返り値possibleMoveIndexListを更新する処理を行う
	private static ArrayList<String> updatePossibleMoveIndexList(int i,int indexRow, int indexColumn, int moveRength, Koma targetKoma, int[] nextIndex, int[][] direction, Board boardInstance, ArrayList<String> possibleMoveIndexList){
		/* このサブルーチンは指定された駒の情報を引数に移動可能先の方向が変わる度に呼び出される */
		
		//現在の更新する方向に関する情報を変数を格納する
		moveRength = targetKoma.getMoveRength()[i];
		nextIndex[0] = direction[i][0];		//行座標の方向
		nextIndex[1] = direction[i][1];		//列座標の方向

		//移動方向に対する移動距離の分だけ繰り返す
		for(int j=0; j<moveRength; j++){
			if( boardInstance.getBoardElement()[indexRow + nextIndex[0]][indexColumn + nextIndex[1]].isInside() ){		//分岐1:盤面か盤外かを判定（盤面ならtrue）
				if( boardInstance.getBoardElement()[indexRow + nextIndex[0]][indexColumn + nextIndex[1]].getKoma() == null ){	//分岐2:別の駒が存在するか（存在しなければtrue）

					//移動できる座標をpossibleMoveIndexListに追加する
					//System.out.println("デバッグ:"+boardInstance.convertIndexName(indexRow + nextIndex[0], indexColumn + nextIndex[1])+"を格納します");
					possibleMoveIndexList.add(boardInstance.convertIndexName(indexRow + nextIndex[0], indexColumn + nextIndex[1]));
					
					//1つ方向に対して移動距離が2マス以上の場合は、格納された移動先座標を基準に処理を繰り返す
					indexRow += nextIndex[0];
					indexColumn += nextIndex[1];
					
				}else{	//-----if(分岐2)の終了
					//System.out.println("デバッグ:「"+boardInstance.getBoardElement()[indexRow + nextIndex[0]][indexColumn + nextIndex[1]].getKoma().getKomaName()+"」が存在します");
					if( boardInstance.getBoardElement()[indexRow + nextIndex[0]][indexColumn + nextIndex[1]].getKoma().isPlayer() == targetKoma.isPlayer() ){		//分岐3:座標に存在する駒の所有者がtargetKomaと同じかを判定(同じならtrue)
						break;		//参照した座標に存在する駒が自分の駒と同じ場合は現在の方向の処理を終了する
					}else{
						//移動できる座標をpossibleMoveIndexListに追加する
						//System.out.println("デバッグ:"+boardInstance.convertIndexName(indexRow + nextIndex[0], indexColumn + nextIndex[1])+"を格納します");
						possibleMoveIndexList.add(boardInstance.convertIndexName(indexRow + nextIndex[0], indexColumn + nextIndex[1]));
						break;
					}
				}
			}else{
				//System.out.println("デバッグ:盤外です");
				break;	//盤面の外を参照した場合は現在の方向の処理を終了する
			}	//-----if文(分岐1)の終了
				

		}//-----for文の終了
		
		return possibleMoveIndexList;
	}

//	//持ち駒台の駒の打てる座標を返す
//	String[] getPossiblePutIndex(){
//		
//	}
	
	//プレイヤーの指せる手を全て返す（駒移動）　先手の指し手を返す場合、引数にtrueを指定する
	ArrayList<String> getPossiblecActionMove(Stage stage, boolean player){
		ArrayList<String> possibleActionMoveList = new ArrayList<String>();
		String possibleActionMove = null;		//指し手が格納される
		
		//全てのマス分繰り返す（81マス）
		for(int i=Board.getMapRow().get("一"); i<=Board.getMapRow().get("九"); i++){
			for(int j=Board.getMapColumn().get("9"); j<=Board.getMapColumn().get("1"); j++){
				
				//引数のplayerと同じownerの駒の場合、駒の移動可能先を返り値に格納する
				if(stage.getBoard().getBoardElement()[i][j].getKoma().isPlayer() == player){
					
//					possibleActionMove = Board.
					
				}
				
			}
		}
		
		return possibleActionMoveList;
	}
	
	//プレイヤーの指せる手を全て返す（駒打ち）　先手の指し手を返す場合、引数にtrueを指定する
//	ArrayList<String> getPossibleActionPut(boolean sente){
//		
//	}
	
}
