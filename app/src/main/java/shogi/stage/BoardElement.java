package shogi.stage;

import shogi.stage.koma.*;

public class BoardElement {
	private Koma koma = null;			//駒
	private boolean inside = true;		//true→盤面　false→盤外
	private String index = null;		//マスの座標
	
	BoardElement(Koma koma, boolean inside, String index){
		this.koma = koma;
		this.inside = inside;
		this.index = index;
	}
	
	//ゲッター・セッター
	public Koma getKoma() {
		return koma;
	}
	public void setKoma(Koma koma) {
		this.koma = koma;
	}
	public boolean isInside() {
		return inside;
	}
	public void setInside(boolean inside) {
		this.inside = inside;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
}
