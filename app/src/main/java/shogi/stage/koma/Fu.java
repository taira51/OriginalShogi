package shogi.stage.koma;

public class Fu extends Koma{

	public Fu(boolean player) {
		super(player);
		
		//駒の名前の初期化
		normalName = "歩";
		superName = "と金";
		setKomaName(normalName);
		
		//駒の画像の名前の初期化
		pictNormalName = "fu";
		pictSuperName = "n_tokin";
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
