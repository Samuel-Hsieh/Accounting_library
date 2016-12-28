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

import deerlight.com.accountinglibrary.Accounting;
import deerlight.com.accountinglibrary.Audit;
import deerlight.com.accountinglibrary.AuditListItems;
import deerlight.com.accountinglibrary.StoreUserData;

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
//                test_expenses();
//                test_income();
//                test_audit("income");
//                test_audit("expenses");
//                test_additem("午餐");
//                test_AuditItems();
//                test_AddAccount("晚餐");
//                test_AuditAccount();
//                test_RemoveItem();
//                test_RemoveAccount();
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
        Accounting accounting = new Accounting(this, "2016/12/28", "1200", "借錢嗚嗚", "expenses", "台灣銀行");
        accounting.setSelectedItem("借錢");
        accounting.SaveDataToDB();
    }

    /**
     * 測試收入
     */
    private void test_income() {
        Accounting accounting = new Accounting(this, "2016/12/28", "1000", "曝鹿工作室", "income", "中華郵政");
        accounting.setSelectedItem("薪水");
        accounting.SaveDataToDB();
    }

    /**
     * 測試查帳
     */
    private void test_audit(String table) {
//        String sql = "SELECT _date , _money , _note , _item , _account FROM '" + table + "'"+"WHERE _account = '現金'";
        String sql = "SELECT _date , _money , _note , _item , _account FROM '" + table + "'";
        Audit audit = new Audit(this, sql);
        ArrayList<AuditListItems> a = audit.getAudit();
        Log.v("AuditData", "total: " + audit.getTotal());
        for (AuditListItems b : a) {
            Log.v("AuditData", "Data: " + b.getData());
            Log.v("AuditData", "Item: " + b.getItem());
            Log.v("AuditData", "Comment: " + b.getComment());
            Log.v("AuditData", "Money: " + b.getMoney());
            Log.v("AuditData", "Account: " + b.getAccount());
        }
    }

    /**
     * 測試新增項目
     */
    private void test_additem(String item) {
        StoreUserData storeUserData = new StoreUserData(this);
        storeUserData.AddItem(item);
    }

    /**
     * 測試新增帳戶
     */
    private void test_AddAccount(String item) {
        StoreUserData storeUserData = new StoreUserData(this);
        storeUserData.AddAccount(item);
    }

    /**
     * 測試查詢項目
     */
    private void test_AuditItems() {
        ArrayList<String> ItemList;
        StoreUserData storeUserData = new StoreUserData(this);
        ItemList = storeUserData.getItem();
        for (String item : ItemList) {
            Log.v("test_AuditItems", "項目: " + item);
        }
    }

    /**
     * 測試查詢帳戶
     */
    private void test_AuditAccount() {
        ArrayList<String> AccountList;
        StoreUserData storeUserData = new StoreUserData(this);
        AccountList = storeUserData.getAccount();
        for (String account : AccountList) {
            Log.v("test_AuditAccount", "帳戶: " + account);
        }
    }

    /**
     * 測式刪除項目
     */
    private void test_RemoveItem() {
        StoreUserData storeUserData = new StoreUserData(this);
        storeUserData.removeItem("早餐");
    }

    /**
     * 測式刪除帳戶
     */
    private void test_RemoveAccount() {
        StoreUserData storeUserData = new StoreUserData(this);
        storeUserData.removeAccount("早餐");
    }
}
