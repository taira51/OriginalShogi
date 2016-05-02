package shogi.stage.koma;

public class Kin extends Koma{

	public Kin(boolean player) {
		super(player);
		
		//駒の名前の初期化
		normalName = "金";
		superName = "なし";
		setKomaName(normalName);

		//駒の画像の名前の初期化
		pictNormalName = "kin";
		pictSuperName = "PictNameError";
		setPictName(pictNormalName);
		
		//移動可能範囲の初期化
		changeMoveRength();
	}

	@Override
	public void changeMoveRength() {
		if(isStatus()){	//true　→　成状態
			System.out.println("デバッグ:Kin.java:金のsutatusがtrueになっています。");
		}else{
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
		}
	}
}
