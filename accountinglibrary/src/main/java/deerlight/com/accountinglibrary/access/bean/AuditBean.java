package deerlight.com.accountinglibrary.access.bean;

/**
 * Created by taaze on 2016/12/27.
 */

public class AuditBean {

    int id;
    String data;
    String money;
    String comment;
    String item;
    String account;

    public AuditBean(int id, String data, String money, String comment, String item, String account) {
        this.id = id;
        this.data = data;
        this.money = money;
        this.comment = comment;
        this.item = item;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
