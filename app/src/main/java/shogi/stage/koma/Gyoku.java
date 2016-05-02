package shogi.stage.koma;

public class Gyoku extends Koma{

	public Gyoku(boolean player) {
		super(player);
		
		//駒の名前の初期化
		normalName = "玉";
		superName = "なし";
		setKomaName(normalName);

		//駒の画像の名前の初期化
		pictNormalName = "gyoku";
		pictSuperName = "PictNameError";
		setPictName(pictNormalName);
		
		//移動可能範囲の初期化
		changeMoveRength();
	}

	@Override
	public void changeMoveRength() {
		if(isStatus()){	//true　→　成状態
			System.out.println("デバッグ:Gyoku.java:玉のsutatusがtrueになっています。");
		}else{
			front = 1;
			frontRight = 1;
			right = 1;
			backRight = 1;
			back = 1;
			backLeft = 1;
			left = 1;
			frontLeft = 1;
			keimaRight = 0;
			keimaLeft = 0;
		}
	}
}
