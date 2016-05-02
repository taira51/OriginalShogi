package shogi.stage;

import java.util.HashMap;

public class MochiGoma {
	private MochiGomaElement[] sente = new MochiGomaElement[7];		//7個のマス要素で持ち駒台を表現する - 先手
	private MochiGomaElement[] gote = new MochiGomaElement[7];		//7個のマス要素で持ち駒台を表現する - 後手
	private HashMap<String,Integer> mapIndex = new HashMap<String,Integer>();	//持ち駒台に配置する駒の位置情報を格納
	
	//コンストラクタ - 座標に対応するマップの生成・持ち駒台の初期化
	MochiGoma(){
		mapIndex.put("飛車", 0);
		mapIndex.put("角", 1);
		mapIndex.put("金", 2);
		mapIndex.put("銀", 3);
		mapIndex.put("桂馬", 4);
		mapIndex.put("香車", 5);
		mapIndex.put("歩", 6);
		
		//持ち駒台の初期化
		for(int i=0; i<7; i++){
			sente[i] = new MochiGomaElement();
			gote[i] = new MochiGomaElement();
		}	
	}
	
	//デバッグ:先手と後手の持駒台の情報を全て表示する
	public void dispMochiGoma(){
		System.out.println("先手");
		for(int i=0; i<7; i++){
			if(sente[i].getKoma() != null){
				System.out.println("sente["+i+"]:"+sente[i].getKoma().getKomaName()+"→"+sente[i].getNum());
			}else{
				System.out.println("sente["+i+"]:空→0");
			}
		}
		
		System.out.println("後手");
		for(int i=0; i<7; i++){
			if(gote[i].getKoma() != null){
				System.out.println("gote["+i+"]:"+gote[i].getKoma().getKomaName()+"→"+gote[i].getNum());
			}else{
				System.out.println("gote["+i+"]:空→0");
			}
		}
	}
	
}
