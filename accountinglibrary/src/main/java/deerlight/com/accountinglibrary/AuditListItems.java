package deerlight.com.accountinglibrary;

/**
 * Created by taaze on 2016/12/27.
 */

public class AuditListItems {

    String data;
    String item;
    String comment;
    String money;

    public AuditListItems(String data, String item, String comment, String money) {
        this.data = data;
        this.item = item;
        this.comment = comment;
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
