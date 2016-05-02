package shogi.stage.koma;

public class Kyosya extends Koma{

	public Kyosya(boolean player) {
		super(player);
		
		//駒の名前の初期化
		normalName = "香車";
		superName = "成香";
		setKomaName(normalName);

		//駒の画像の名前の初期化
		pictNormalName = "kyosya";
		pictSuperName = "n_kyosya";
		setPictName(pictNormalName);
		
		//移動可能範囲の初期化
		changeMoveRength();
	}

	@Override
	public void changeMoveRength() {
		if(isStatus()){	//true　→　成状態
			front = 1;
			frontRight = 1;
			right = 1;
			backRight = 0;
			back = 1;
			backLeft = 0;
			left = 1;
			frontLeft = 1;
			keimaRight = 0;
			keimaLeft = 0;
		}else{
			front = 8;
			frontRight = 0;
			right = 0;
			backRight = 0;
			back = 0;
			backLeft = 0;
			left = 0;
			frontLeft = 0;
			keimaRight = 0;
			keimaLeft = 0;
		}
	}
}
