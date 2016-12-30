package deerlight.com.accounting_library;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import deerlight.com.accountinglibrary.access.bean.AuditAccountBean;
import deerlight.com.accountinglibrary.access.read.AuditAccountingItem;
import deerlight.com.accountinglibrary.access.read.AuditDepositAccount;
import deerlight.com.accountinglibrary.access.write.Accounting;
import deerlight.com.accountinglibrary.access.read.Audit;
import deerlight.com.accountinglibrary.access.bean.AuditBean;
import deerlight.com.accountinglibrary.access.write.AccountingItem;
import deerlight.com.accountinglibrary.access.write.DepositAccount;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                test_Remove("expenses");
//                test_UpdateExpenses("expenses");
//                test_expenses();
//                test_income();
//                test_audit("income");
//                test_audit("expenses");
//                test_additem("早餐");
//                test_AuditItems();
//                test_AddAccount("郵局","1000");
//                test_AuditAccount();
//                test_UpdateItem("早餐", "午餐");
//                test_UpdateAccount("郵局","中國信託","2000");
//                test_RemoveItem("午餐");
//                test_RemoveAccount("中國信託");
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 測試支出
     */
    private void test_expenses() {
        Accounting accounting = new Accounting(this, "2016/12/29", "10000", "嗚嗚嗚", "expenses", "現金");
        accounting.setSelectedItem("買禮物");
        accounting.SaveDataToDB();
    }

    /**
     * 測試收入
     */
    private void test_income() {
        Accounting accounting = new Accounting(this, "2016/12/22", "30000", "TAAZE", "income", "永豐銀行");
        accounting.setSelectedItem("薪水");
        accounting.SaveDataToDB();
    }

    /**
     * 測試查帳
     */
    private void test_audit(String table) {
//        String sql = "SELECT _date , _money , _note , _item , _account FROM '" + table + "'"+"WHERE _account = '現金'";
        String sql = "SELECT _id , _date , _money , _note , _item , _account FROM '" + table + "'";
        Audit audit = new Audit(this, sql);
        ArrayList<AuditBean> a = audit.getAudit();
        Log.v("AuditData", "total: " + audit.getTotal());
        for (AuditBean b : a) {
            Log.v("AuditData", "ID: " + b.getId());
            Log.v("AuditData", "Data: " + b.getData());
            Log.v("AuditData", "item: " + b.getItem());
            Log.v("AuditData", "Comment: " + b.getComment());
            Log.v("AuditData", "Money: " + b.getMoney());
            Log.v("AuditData", "Account: " + b.getAccount());
        }
    }

    /**
     * 測試新增項目
     */
    private void test_additem(String item) {
        AccountingItem accountingItem = new AccountingItem(this);
        accountingItem.Insert(item);
    }

    /**
     * 測試新增帳戶
     */
    private void test_AddAccount(String account, String money) {
        DepositAccount depositAccount = new DepositAccount(this);
        depositAccount.Insert(account, money);
    }

    /**
     * 測試查詢項目
     */
    private void test_AuditItems() {
        ArrayList<String> ItemList;
        String sql = "SELECT _item FROM item";
        AuditAccountingItem auditAccountingItem = new AuditAccountingItem(this);
        ItemList = auditAccountingItem.getItem(sql);
        for (String item : ItemList) {
            Log.v("test_AuditItems", "項目: " + item);
        }
    }

    /**
     * 測試查詢帳戶
     */
    private void test_AuditAccount() {
        ArrayList<AuditAccountBean> AccountList;
        String sql = "SELECT _account , _money FROM account";
        AuditDepositAccount auditDepositAccount = new AuditDepositAccount(this);
        AccountList = auditDepositAccount.getAccount(sql);
        for (AuditAccountBean accountList : AccountList) {
            Log.v("test_AuditAccount", "帳戶: " + accountList.getAccount());
            Log.v("test_AuditAccount", "金額: " + accountList.getMoney());
        }
    }

    /**
     * 測試編輯項目
     */
    private void test_UpdateItem(String OldItem, String NewItem) {
        AccountingItem accountingItem = new AccountingItem(this);
        accountingItem.Update(OldItem, NewItem);
    }

    /**
     * 測試編輯帳戶
     */
    private void test_UpdateAccount(String OldAccount, String NewAccount, String NewMoney) {
        DepositAccount depositAccount = new DepositAccount(this);
        depositAccount.Update(OldAccount, NewAccount, NewMoney); //Old , New
    }

    /**
     * 測試刪除項目
     */
    private void test_RemoveItem(String item) {
        AccountingItem accountingItem = new AccountingItem(this);
        accountingItem.Remove(item);
    }

    /**
     * 測試刪除帳戶
     */
    private void test_RemoveAccount(String Account) {
        DepositAccount depositAccount = new DepositAccount(this);
        depositAccount.Remove(Account);
    }

    /**
     * 編輯記帳
     */
    private void test_UpdateExpenses(String table) {
        Accounting accounting = new Accounting(this, "2016/12/29", "10000", "嗚嗚嗚", table, "現金"); //需要更改成
        accounting.setSelectedItem("禮物");
        accounting.UpdateDataToDB(5); //需要更改的id
    }

    /**
     * 刪除記帳
     */
    private void test_Remove(String table) {
        Accounting accounting = new Accounting(this, table); //需要刪除的資料
        accounting.RemoveDataToDB(4);
    }
}
