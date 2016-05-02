package shogi.stage;

import java.util.HashMap;
import shogi.stage.koma.*;

public class Stage {
	private static Board boardInstance;
	private static MochiGoma mochiGomaInstance;
	private static HashMap<String,Koma> allKomaInstance = new HashMap<String,Koma>();		//全ての駒インスタンスの保管場所
	
	//全ての初期化
	public Stage(){
		//全ての駒インスタンス生成
		allKomaInstance.put("Gyoku1", new Gyoku(true));
		allKomaInstance.put("Gyoku2", new Gyoku(false));
		allKomaInstance.put("Hisya1", new Hisya(true));
		allKomaInstance.put("Hisya2", new Hisya(false));
		allKomaInstance.put("Kaku1", new Kaku(true));
		allKomaInstance.put("Kaku2", new Kaku(false));
		
		allKomaInstance.put("Kin1", new Kin(true));
		allKomaInstance.put("Kin2", new Kin(true));
		allKomaInstance.put("Kin3", new Kin(false));
		allKomaInstance.put("Kin4", new Kin(false));
		allKomaInstance.put("Gin1", new Gin(true));
		allKomaInstance.put("Gin2", new Gin(true));
		allKomaInstance.put("Gin3", new Gin(false));
		allKomaInstance.put("Gin4", new Gin(false));
		allKomaInstance.put("Keima1", new Keima(true));
		allKomaInstance.put("Keima2", new Keima(true));
		allKomaInstance.put("Keima3", new Keima(false));
		allKomaInstance.put("Keima4", new Keima(false));
		allKomaInstance.put("Kyosya1", new Kyosya(true));
		allKomaInstance.put("Kyosya2", new Kyosya(true));
		allKomaInstance.put("Kyosya3", new Kyosya(false));
		allKomaInstance.put("Kyosya4", new Kyosya(false));
		
		allKomaInstance.put("Fu1", new Fu(true));
		allKomaInstance.put("Fu2", new Fu(true));
		allKomaInstance.put("Fu3", new Fu(true));
		allKomaInstance.put("Fu4", new Fu(true));
		allKomaInstance.put("Fu5", new Fu(true));
		allKomaInstance.put("Fu6", new Fu(true));
		allKomaInstance.put("Fu7", new Fu(true));
		allKomaInstance.put("Fu8", new Fu(true));
		allKomaInstance.put("Fu9", new Fu(true));
		allKomaInstance.put("Fu10", new Fu(false));
		allKomaInstance.put("Fu11", new Fu(false));
		allKomaInstance.put("Fu12", new Fu(false));
		allKomaInstance.put("Fu13", new Fu(false));
		allKomaInstance.put("Fu14", new Fu(false));
		allKomaInstance.put("Fu15", new Fu(false));
		allKomaInstance.put("Fu16", new Fu(false));
		allKomaInstance.put("Fu17", new Fu(false));
		allKomaInstance.put("Fu18", new Fu(false));
		
		//盤面・持ち駒台の初期化
		boardInstance = new Board(allKomaInstance);
		mochiGomaInstance = new MochiGoma();
		

	}
	
	public Board getBoard(){
		return boardInstance;
	}
	
	public MochiGoma getMochiGoma(){
		return mochiGomaInstance;
	}
	
	public static HashMap<String,Koma> getAllKomaInstance(){
		return allKomaInstance;
	}
}
