package shogi.stage.koma;

public class Hisya extends Koma{

	public Hisya(boolean player) {
		super(player);
		
		//駒の名前の初期化
		normalName = "飛車";
		superName = "龍";
		setKomaName(normalName);

		//駒の画像の名前の初期化
		pictNormalName = "hisya";
		pictSuperName = "n_hisya";
		setPictName(pictNormalName);
		
		//移動可能範囲の初期化
		changeMoveRength();
	}

	@Override
	public void changeMoveRength() {
		if(isStatus()){	//true　→　成状態
			front = 8;
			frontRight = 1;
			right = 8;
			backRight = 1;
			back = 8;
			backLeft = 1;
			left = 8;
			frontLeft = 1;
			keimaRight = 0;
			keimaLeft = 0;
		}else{
			front = 8;
			frontRight = 0;
			right = 8;
			backRight = 0;
			back = 8;
			backLeft = 0;
			left = 8;
			frontLeft = 0;
			keimaRight = 0;
			keimaLeft = 0;
		}
	}
}
