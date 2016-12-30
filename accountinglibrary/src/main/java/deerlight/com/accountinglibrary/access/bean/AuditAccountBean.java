package deerlight.com.accountinglibrary.access.bean;

/**
 * Created by taaze on 2016/12/30.
 */

public class AuditAccountBean {

    String account;
    String money;

    public AuditAccountBean(String account, String money) {
        this.account = account;
        this.money = money;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
