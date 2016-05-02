package activities.battle;

import java.io.Serializable;

/*
 * 各対局者の情報、対局の先手番後手番等の情報を管理するクラス
 */
public class BattleInformation implements Serializable {
    //先手ユーザー情報
    private String senteUserNum;
    private String senteUserName;

    //後手ユーザー情報
    private String goteUserNum;
    private String goteUserName;

    public void BattleInformation(){
        /*  ユーザー情報を格納する */
    }


    public String getSenteUserNum() {
        return senteUserNum;
    }

    public void setSenteUserNum(String senteUserNum) {
        this.senteUserNum = senteUserNum;
    }

    public String getSenteUserName() {
        return senteUserName;
    }

    public void setSenteUserName(String senteUserName) {
        this.senteUserName = senteUserName;
    }

    public String getGoteUserNum() {
        return goteUserNum;
    }

    public void setGoteUserNum(String goteUserNum) {
        this.goteUserNum = goteUserNum;
    }

    public String getGoteUserName() {
        return goteUserName;
    }

    public void setGoteUserName(String goteUserName) {
        this.goteUserName = goteUserName;
    }
}
