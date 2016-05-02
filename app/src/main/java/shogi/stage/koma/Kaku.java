package shogi.stage.koma;

public class Kaku extends Koma{

	public Kaku(boolean player) {
		super(player);
		
		//駒の名前の初期化
		normalName = "角";
		superName = "馬";
		setKomaName(normalName);

		//駒の画像の名前の初期化
		pictNormalName = "kaku";
		pictSuperName = "n_uma";
		setPictName(pictNormalName);
		
		//移動可能範囲の初期化
		changeMoveRength();
	}

	@Override
	public void changeMoveRength() {
		if(isStatus()){	//true　→　成状態
			front = 1;
			frontRight = 8;
			right = 1;
			backRight = 8;
			back = 1;
			backLeft = 8;
			left = 1;
			frontLeft = 8;
			keimaRight = 0;
			keimaLeft = 0;
		}else{
			front = 0;
			frontRight = 8;
			right = 0;
			backRight = 8;
			back = 0;
			backLeft = 8;
			left = 0;
			frontLeft = 8;
			keimaRight = 0;
			keimaLeft = 0;
		}
	}
}
