package shogi.play;

public interface Operation {
	abstract String[] inputHoldBoardKoma();		//盤面に存在する駒をHoldした時の処理
	abstract String[] inputHoldMochiGoma();		//駒台に存在する持ち駒をHoldした時の処理
	abstract void inputMoveIndex();				//盤面の駒をHoldした状態で盤面の座標を指定した時の処理
	abstract void inputPutIndex();				//駒台に存在する持ち駒をHoldした状態で盤面の座標を指定した時の処理
	abstract void inputResign();				//投了コマンドが送られてきた時の処理
}
