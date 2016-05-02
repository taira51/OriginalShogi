package shogi.stage;

import java.util.HashMap;
import shogi.stage.koma.*;

public class Board {
	private BoardElement[][] board = new BoardElement[11][11];		//121個のマス要素で盤面を表現する
	private static final HashMap<String,Integer> mapRow = new HashMap<String,Integer>();			//マスの座標 - 行
	private static final HashMap<String,Integer> mapColumn = new HashMap<String,Integer>();		//マスの座標 - 列
	private static final int[][] directionSente = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-2,1},{-2,-1}};	//先手番から見た駒の移動方向が格納される
	private static final int[][] directionGote = {{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{2,-1},{2,1}};		//後手番絡みた駒の移動方向が格納される
	
	//コンストラクタ - 座標に対応するマップを生成・座標にマップを対応させる処理・駒インスタンスの初期配置を初期化
	Board(HashMap<String, Koma> allKomaInstance){
		mapColumn.put("1", 9);
		mapColumn.put("2", 8);
		mapColumn.put("3", 7);
		mapColumn.put("4", 6);
		mapColumn.put("5", 5);
		mapColumn.put("6", 4);
		mapColumn.put("7", 3);
		mapColumn.put("8", 2);
		mapColumn.put("9", 1);
		mapRow.put("一", 1);
		mapRow.put("二", 2);
		mapRow.put("三", 3);
		mapRow.put("四", 4);
		mapRow.put("五", 5);
		mapRow.put("六", 6);
		mapRow.put("七", 7);
		mapRow.put("八", 8);
		mapRow.put("九", 9);
		
		//盤外の生成
		for(int i=0; i<11; i++){
			board[0][i] = new BoardElement(null, false, "盤外");
			board[10][i] = new BoardElement(null, false, "盤外");
			board[i][0] = new BoardElement(null, false, "盤外");
			board[i][10] = new BoardElement(null, false, "盤外");
		}
		
		//盤面の初期化
		//後手一行
		board[mapRow.get("一")][mapColumn.get("9")] = new BoardElement((Koma) allKomaInstance.get("Kyosya3"),true,"9一");
		board[mapRow.get("一")][mapColumn.get("8")] = new BoardElement((Koma) allKomaInstance.get("Keima3"),true,"8一");
		board[mapRow.get("一")][mapColumn.get("7")] = new BoardElement((Koma) allKomaInstance.get("Gin3"),true,"7一");
		board[mapRow.get("一")][mapColumn.get("6")] = new BoardElement((Koma) allKomaInstance.get("Kin3"),true,"6一");
		board[mapRow.get("一")][mapColumn.get("5")] = new BoardElement((Koma) allKomaInstance.get("Gyoku2"),true,"5一");
		board[mapRow.get("一")][mapColumn.get("4")] = new BoardElement((Koma) allKomaInstance.get("Kin4"),true,"4一");
		board[mapRow.get("一")][mapColumn.get("3")] = new BoardElement((Koma) allKomaInstance.get("Gin4"),true,"3一");
		board[mapRow.get("一")][mapColumn.get("2")] = new BoardElement((Koma) allKomaInstance.get("Keima4"),true,"2一");
		board[mapRow.get("一")][mapColumn.get("1")] = new BoardElement((Koma) allKomaInstance.get("Kyosya4"),true,"1一");
		
		//後手二行
		board[mapRow.get("二")][mapColumn.get("9")] = new BoardElement(null,true,"9二");
		board[mapRow.get("二")][mapColumn.get("8")] = new BoardElement((Koma) allKomaInstance.get("Hisya2"),true,"8二");
		board[mapRow.get("二")][mapColumn.get("7")] = new BoardElement(null,true,"7二");
		board[mapRow.get("二")][mapColumn.get("6")] = new BoardElement(null,true,"6二");
		board[mapRow.get("二")][mapColumn.get("5")] = new BoardElement(null,true,"5二");
		board[mapRow.get("二")][mapColumn.get("4")] = new BoardElement(null,true,"4二");
		board[mapRow.get("二")][mapColumn.get("3")] = new BoardElement(null,true,"3二");
		board[mapRow.get("二")][mapColumn.get("2")] = new BoardElement((Koma) allKomaInstance.get("Kaku2"),true,"2二");
		board[mapRow.get("二")][mapColumn.get("1")] = new BoardElement(null,true,"1二");
		
		//後手三行
		board[mapRow.get("三")][mapColumn.get("9")] = new BoardElement((Koma) allKomaInstance.get("Fu10"),true,"9三");
		board[mapRow.get("三")][mapColumn.get("8")] = new BoardElement((Koma) allKomaInstance.get("Fu11"),true,"8三");
		board[mapRow.get("三")][mapColumn.get("7")] = new BoardElement((Koma) allKomaInstance.get("Fu12"),true,"7三");
		board[mapRow.get("三")][mapColumn.get("6")] = new BoardElement((Koma) allKomaInstance.get("Fu13"),true,"6三");
		board[mapRow.get("三")][mapColumn.get("5")] = new BoardElement((Koma) allKomaInstance.get("Fu14"),true,"5三");
		board[mapRow.get("三")][mapColumn.get("4")] = new BoardElement((Koma) allKomaInstance.get("Fu15"),true,"4三");
		board[mapRow.get("三")][mapColumn.get("3")] = new BoardElement((Koma) allKomaInstance.get("Fu16"),true,"3三");
		board[mapRow.get("三")][mapColumn.get("2")] = new BoardElement((Koma) allKomaInstance.get("Fu17"),true,"2三");
		board[mapRow.get("三")][mapColumn.get("1")] = new BoardElement((Koma) allKomaInstance.get("Fu18"),true,"1三");
		
		//四行
		board[mapRow.get("四")][mapColumn.get("9")] = new BoardElement(null,true,"9四");
		board[mapRow.get("四")][mapColumn.get("8")] = new BoardElement(null,true,"8四");
		board[mapRow.get("四")][mapColumn.get("7")] = new BoardElement(null,true,"7四");
		board[mapRow.get("四")][mapColumn.get("6")] = new BoardElement(null,true,"6四");
		board[mapRow.get("四")][mapColumn.get("5")] = new BoardElement(null,true,"5四");
		board[mapRow.get("四")][mapColumn.get("4")] = new BoardElement(null,true,"4四");
		board[mapRow.get("四")][mapColumn.get("3")] = new BoardElement(null,true,"3四");
		board[mapRow.get("四")][mapColumn.get("2")] = new BoardElement(null,true,"2四");
		board[mapRow.get("四")][mapColumn.get("1")] = new BoardElement(null,true,"1四");
		
		//五行
		board[mapRow.get("五")][mapColumn.get("9")] = new BoardElement(null,true,"9五");
		board[mapRow.get("五")][mapColumn.get("8")] = new BoardElement(null,true,"8五");
		board[mapRow.get("五")][mapColumn.get("7")] = new BoardElement(null,true,"7五");
		board[mapRow.get("五")][mapColumn.get("6")] = new BoardElement(null,true,"6五");
		board[mapRow.get("五")][mapColumn.get("5")] = new BoardElement(null,true,"5五");
		board[mapRow.get("五")][mapColumn.get("4")] = new BoardElement(null,true,"4五");
		board[mapRow.get("五")][mapColumn.get("3")] = new BoardElement(null,true,"3五");
		board[mapRow.get("五")][mapColumn.get("2")] = new BoardElement(null,true,"2五");
		board[mapRow.get("五")][mapColumn.get("1")] = new BoardElement(null,true,"1五");
		
		//六行
		board[mapRow.get("六")][mapColumn.get("9")] = new BoardElement(null,true,"9六");
		board[mapRow.get("六")][mapColumn.get("8")] = new BoardElement(null,true,"8六");
		board[mapRow.get("六")][mapColumn.get("7")] = new BoardElement(null,true,"7六");
		board[mapRow.get("六")][mapColumn.get("6")] = new BoardElement(null,true,"6六");
		board[mapRow.get("六")][mapColumn.get("5")] = new BoardElement(null,true,"5六");
		board[mapRow.get("六")][mapColumn.get("4")] = new BoardElement(null,true,"4六");
		board[mapRow.get("六")][mapColumn.get("3")] = new BoardElement(null,true,"3六");
		board[mapRow.get("六")][mapColumn.get("2")] = new BoardElement(null,true,"2六");
		board[mapRow.get("六")][mapColumn.get("1")] = new BoardElement(null,true,"1六");
		
		//先手七行		
		board[mapRow.get("七")][mapColumn.get("9")] = new BoardElement((Koma) allKomaInstance.get("Fu1"),true,"9七");
		board[mapRow.get("七")][mapColumn.get("8")] = new BoardElement((Koma) allKomaInstance.get("Fu2"),true,"8七");
		board[mapRow.get("七")][mapColumn.get("7")] = new BoardElement((Koma) allKomaInstance.get("Fu3"),true,"7七");
		board[mapRow.get("七")][mapColumn.get("6")] = new BoardElement((Koma) allKomaInstance.get("Fu4"),true,"6七");
		board[mapRow.get("七")][mapColumn.get("5")] = new BoardElement((Koma) allKomaInstance.get("Fu5"),true,"5七");
		board[mapRow.get("七")][mapColumn.get("4")] = new BoardElement((Koma) allKomaInstance.get("Fu6"),true,"4七");
		board[mapRow.get("七")][mapColumn.get("3")] = new BoardElement((Koma) allKomaInstance.get("Fu7"),true,"3七");
		board[mapRow.get("七")][mapColumn.get("2")] = new BoardElement((Koma) allKomaInstance.get("Fu8"),true,"2七");
		board[mapRow.get("七")][mapColumn.get("1")] = new BoardElement((Koma) allKomaInstance.get("Fu9"),true,"1七");
		
		//先手八行
		board[mapRow.get("八")][mapColumn.get("9")] = new BoardElement(null,true,"9八");
		board[mapRow.get("八")][mapColumn.get("8")] = new BoardElement((Koma) allKomaInstance.get("Kaku1"),true,"8八");
		board[mapRow.get("八")][mapColumn.get("7")] = new BoardElement(null,true,"7八");
		board[mapRow.get("八")][mapColumn.get("6")] = new BoardElement(null,true,"6八");
		board[mapRow.get("八")][mapColumn.get("5")] = new BoardElement(null,true,"5八");
		board[mapRow.get("八")][mapColumn.get("4")] = new BoardElement(null,true,"4八");
		board[mapRow.get("八")][mapColumn.get("3")] = new BoardElement(null,true,"3八");
		board[mapRow.get("八")][mapColumn.get("2")] = new BoardElement((Koma) allKomaInstance.get("Hisya1"),true,"2八");
		board[mapRow.get("八")][mapColumn.get("1")] = new BoardElement(null,true,"1八");
		
		//先手九行
		board[mapRow.get("九")][mapColumn.get("9")] = new BoardElement((Koma) allKomaInstance.get("Kyosya1"),true,"9九");
		board[mapRow.get("九")][mapColumn.get("8")] = new BoardElement((Koma) allKomaInstance.get("Keima1"),true,"8九");
		board[mapRow.get("九")][mapColumn.get("7")] = new BoardElement((Koma) allKomaInstance.get("Gin1"),true,"7九");
		board[mapRow.get("九")][mapColumn.get("6")] = new BoardElement((Koma) allKomaInstance.get("Kin1"),true,"6九");
		board[mapRow.get("九")][mapColumn.get("5")] = new BoardElement((Koma) allKomaInstance.get("Gyoku1"),true,"5九");
		board[mapRow.get("九")][mapColumn.get("4")] = new BoardElement((Koma) allKomaInstance.get("Kin2"),true,"4九");
		board[mapRow.get("九")][mapColumn.get("3")] = new BoardElement((Koma) allKomaInstance.get("Gin2"),true,"3九");
		board[mapRow.get("九")][mapColumn.get("2")] = new BoardElement((Koma) allKomaInstance.get("Keima2"),true,"2九");
		board[mapRow.get("九")][mapColumn.get("1")] = new BoardElement((Koma) allKomaInstance.get("Kyosya2"),true,"1九");
		
	}
	
	//int型の座標情報をString型の座標情報に変換する
	public static String convertIndexName(int row, int column){
		//行情報の変換
		String indexName = null;;
		
		switch(row){
		case 1:
			indexName = "一";
			break;
		case 2:
			indexName = "二";
			break;
		case 3:
			indexName = "三";
			break;
		case 4:
			indexName = "四";
			break;
		case 5:
			indexName = "五";
			break;
		case 6:
			indexName = "六";
			break;
		case 7:
			indexName = "七";
			break;
		case 8:
			indexName = "八";
			break;
		case 9:
			indexName = "九";
			break;
		}
		
		indexName = String.valueOf(mapColumn.get(String.valueOf(column))) +indexName;
		return  indexName; 
	}
	
	//座標名をint型の座標情報に変換する（要素0に列座標を格納）
	public static int[] convertIndexInteger(String indexName){
		int[] indexInteger = new int[2];
		
		//要素0に行座標、要素1に列情報を格納する
		indexInteger[0] = mapRow.get(indexName.substring(1,2));			//indexNameのセパレート　例:7六→6
		indexInteger[1] = mapColumn.get(indexName.substring(0,1));		//indexNameのセパレート　例:7六→7
		return indexInteger;
	}
	
	//引数の駒インスタンスをboardから探して、座標を返す
	public String searchKomaInstance(Koma searchKoma){
		for(int i=mapRow.get("一"); i<=mapRow.get("九"); i++){
			for(int j=mapColumn.get("9"); j<=mapColumn.get("1"); j++){
				if(board[i][j].getKoma() != null && board[i][j].getKoma() == searchKoma){
					return convertIndexName(i,j);
				}
			}
		}
		System.out.println("デバッグ:searchKomaInstance:引数の駒が盤面に存在しませんでした。nullを返します。");
		return null;	//引数の駒が盤面に存在しない場合はnullを返す
	}

	//
	
	//デバッグ:盤面の座標を全て表示する
	public void dispBoardIndex(){
		System.out.println("------------dispBoardIndex()の開始------------");
		for(int i=0; i<11; i++){
			for(int j=0; j<11; j++){
				System.out.print("|"+board[i][j].getIndex()+"|");
			}
			System.out.println();		//改行
		}
		System.out.println("------------dispBoardIndex()の終了------------");
	}
	
	//デバッグ:盤面に配置されている駒の位置情報を全て表示す
	public void dispBoardKoma(){
		System.out.println("------------dispBoardKoma()の開始------------");
		for(int i=0; i<11; i++){
			for(int j=0; j<11; j++){
				if(board[i][j] != null && board[i][j].getKoma() != null){
					System.out.print("|"+board[i][j].getKoma().getKomaName()+"|");
				}else{
					System.out.print("|空|");
				}
			}
			System.out.println();		//改行
		}
		System.out.println("------------dispBoardKoma()の終了------------");
	}

	//ゲッター・セッター
	public BoardElement[][] getBoardElement() {
		return board;
	}
	public static HashMap<String, Integer> getMapRow() {
		return mapRow;
	}

	public static HashMap<String, Integer> getMapColumn() {
		return mapColumn;
	}
	
	public static int[][] getDirectionSente(){
		return directionSente;
	}
	
	public static int[][] getDirectionGote(){
		return directionGote;
	}
	
}
