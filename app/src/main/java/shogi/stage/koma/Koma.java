package shogi.stage.koma;

public abstract class Koma {
	//駒の種類・状態を表すフィールド
	private boolean player = true;							//所有者 先手→true 後手→false
	private boolean status = false;							//成・不成りの状態　成→true 不成→false
	private String komaName = null;							//駒の名前(statusで変化)
	protected String normalName = null,superName = null;	//通常時と成状態時の名前
	private String pictName;								//駒の画像の名前(statusとplayerで変化)
	protected String pictNormalName = null,pictSuperName = null;	//通常時と成状態時の駒の画像の名前
	
	//移動可能範囲に関するフィールド・抽象メソッド
	protected int front = 0,frontRight = 0,right = 0,backRight = 0,back = 0,backLeft = 0,left = 0,frontLeft = 0,keimaRight = 0,keimaLeft = 0;
	protected int[] moveRength = new int[10];			//現在のstatusの駒の移動範囲を格納する
	public abstract void changeMoveRength();			//statusを元に、移動範囲を修正する
	
	//コンストラクタ （駒の所有者、駒の名前)
	public Koma(boolean player){
		//所有者
		this.player = player;
	}

	//駒の所有者を反転させる
	public void changePlayer(){
		if(player){
			status = false;
			komaName = normalName;
		}else{
			status = true;
			komaName = normalName;
		}
	}
	
	//成・不成りを反転させる
	public void changeStatus(){
		if(status){
			status = false;
			komaName = normalName;
		}else{
			status = true;
			komaName = superName;
		}
	}
	
	//画像の名前を変更する
	public void changePictName(){
		if(status){
			this.pictName = pictSuperName;
		}else{
			this.pictName = pictNormalName;
		}
	}
	
	//moveRength[]を最新の状態にして返す
	public int[] getMoveRength(){
		changeMoveRength();
		moveRength[0] = front;
		moveRength[1] = frontRight;
		moveRength[2] = right;
		moveRength[3] = backRight;
		moveRength[4] = back;
		moveRength[5] = backLeft;
		moveRength[6] = left;
		moveRength[7] = frontLeft;
		moveRength[8] = keimaRight;
		moveRength[9] = keimaLeft;
		return moveRength;
	}
	
	//現在の所有者を取得 先手→true
	public boolean isPlayer() {
		return player;
	}

	//現在の成状態を取得　不成→false
	public boolean isStatus() {
		return status;
	}
	
	//現在の駒の名前を取得
	public String getKomaName(){
		return komaName;
	}

	//駒の画像ファイル名を返す
	public String getPictName() { return pictName; }
	
	//駒の名前を設定する
	protected void setKomaName(String komaName){
		this.komaName = komaName;
	}
	
	//駒の画像の名前を設定する
	protected void setPictName(String pictName){
		this.pictName = pictName;
	}
}
