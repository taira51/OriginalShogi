package shogi.stage.koma;

public class Gin extends Koma{

	public Gin(boolean player) {
		super(player);
		
		//駒の名前の初期化
		normalName = "銀";
		superName = "成銀";
		setKomaName(normalName);

		//駒の画像の名前の初期化
		pictNormalName = "gin";
		pictSuperName = "n_gin";
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
			front = 1;
			frontRight = 1;
			right = 0;
			backRight = 1;
			back = 0;
			backLeft = 1;
			left = 0;
			frontLeft = 1;
			keimaRight = 0;
			keimaLeft = 0;
		}
	}
}
