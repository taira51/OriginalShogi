package shogi.stage;

import java.util.ArrayList;
import shogi.stage.koma.*;

public class MochiGomaElement {
	private ArrayList<Koma> komaStack = new ArrayList<Koma>();		//駒インスタンスを格納する
	private int num	= 0;			//所持数
	
	//komaStackに駒インスタンスを追加する
	public void addKoma(Koma koma){
		if(num < 18){
			this.komaStack.add(koma);
			this.num = this.komaStack.size();
		}else{
			System.out.println("デバッグ：MochiGoma.java：所持数が18のMochiGomaをaddKomaしました。");
		}
	}
	
	//komaStackから駒インスタンスを削除する
	public void subKoma(){
		if(num > 0){
			this.komaStack.remove(komaStack.size());
			this.num = komaStack.size();
		}else{
			System.out.println("デバッグ：MochiGoma.java：所持数が0のMochiGomaをsubKomaしました。");
		}
	}
	
	//komaStackに一番新しく格納されている駒インスタンスを返す
	public Koma getKoma(){
		if(num > 0){
			return komaStack.get(komaStack.size());
		}else{
			System.out.println("デバッグ:MochiGomaElement.java:所持数0の駒をgetKomaしました。nullを返します。");
			return null;
		}
	}
	
	//格納されている駒の所持数を返す
	public int getNum(){
		return num;
	}
	

}
