package deerlight.com.accountinglibrary.access.write;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import deerlight.com.accountinglibrary.R;
import deerlight.com.accountinglibrary.database.AccountingDB;

/**
 * Created by taaze on 2016/12/29.
 */

public class DepositAccount implements DBcontroller {


    private final static String _Account = "account"; //金額
    Context context;
    AccountingDB DB;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;

    public DepositAccount(Context context) {
        this.context = context;
    }

    public void Insert(String mAccount, String money) {
        OpenDB();
        sqLiteDatabase = DB.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("_account", mAccount);
        contentValues.put("_money", money);
        long check = sqLiteDatabase.insert(_Account, null, contentValues);
        if (check > 0) {
            showToast(getString(R.string.INSERT_SUCCESS));
        } else {
            showToast(getString(R.string.ACCOUNT_EXIST));
        }
        CloseDB();
    }

    public void Update(String OldAccount, String NewAccount, String NewMoney) {
        OpenDB();
        sqLiteDatabase = DB.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("_account", NewAccount);
        contentValues.put("_money", NewMoney);
        long check = sqLiteDatabase.update(_Account, contentValues
                , "_account = '" + OldAccount + "'", null);
        if (check > 0) {
            showToast(getString(R.string.UPDATE_SUCCESS));
        } else {
            showToast(getString(R.string.ACCOUNT_NO_EXIST));
        }
        CloseDB();
    }

    public void Remove(String account) {
        OpenDB();
        sqLiteDatabase = DB.getWritableDatabase();
        long check = sqLiteDatabase.delete(_Account
                , "_account = '" + account + "'", null);
        if (check > 0) {
            showToast(getString(R.string.REMOVE_ACCOUNT));
        } else {
            showToast(getString(R.string.ACCOUNT_NO_EXIST));
        }
        CloseDB();
    }

    @Override
    public void OpenDB() {
        DB = new AccountingDB(context);
    }

    @Override
    public void CloseDB() {
        if (DB != null) {
            DB.close();
            DB = null;
        }
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
            sqLiteDatabase = null;
        }
        if (contentValues != null) {
            contentValues.clear();
            contentValues = null;
        }
    }

    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private String getString(int id) {
        return context.getResources().getString(id);
    }
}
