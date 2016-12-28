package deerlight.com.accountinglibrary;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by taaze on 2016/12/28.
 */

public class StoreUserData {

    private static final String FAILED = "不明原因失敗";
    private static final String SUCCESS = "新增成功";
    private static final String ITEM_EXIST = "項目已存在";
    private static final String ITEM_NO_EXIST = "項目不存在";
    private static final String ACCOUNT_EXIST = "帳戶已存在";
    private static final String ACCOUNT_NO_EXIST = "帳戶不存在";
    private static final String REMOVE_ITEM = "已刪除項目";
    private static final String REMOVE_ACCOUNT = "已刪除帳戶";
    private static final String UPDATE_SUCCESS = "編輯成功";
    private final static String _Item = "item"; //金額
    private final static String _Account = "account"; //金額

    Context context;
    AccountingDB DB;

    public StoreUserData(Context context) {
        this.context = context;
    }

    //新增項目
    public void AddItem(String mItem) {
        OpenDB();
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_item", mItem);
        long check = sqLiteDatabase.insert(_Item, null, contentValues);
        if (check > 0) {
            showToast(SUCCESS);
        } else {
            showToast(ITEM_EXIST);
        }
        CloseDB();
    }

    //新增帳戶
    public void AddAccount(String mAccount) {
        OpenDB();
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_account", mAccount);
        long check = sqLiteDatabase.insert(_Account, null, contentValues);
        if (check > 0) {
            showToast(SUCCESS);
        } else {
            showToast(ACCOUNT_EXIST);
        }
        CloseDB();
    }

    //取得項目
    public ArrayList<String> getItem(String POSTS_SELECT_QUERY) {
        OpenDB();
        ArrayList<String> ListItem = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(POSTS_SELECT_QUERY, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                ListItem.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        CloseDB();
        return ListItem;
    }

    //取得帳戶
    public ArrayList<String> getAccount(String POSTS_SELECT_QUERY) {
        OpenDB();
        ArrayList<String> ListAccount = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(POSTS_SELECT_QUERY, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                ListAccount.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        CloseDB();
        return ListAccount;
    }

    //編輯項目
    public void UpdateItem(String OldItem, String NewItem) {
        OpenDB();
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_item", NewItem);
        long check = sqLiteDatabase.update(_Item, contentValues
                , "_item = '" + OldItem + "'", null);
        if (check > 0) {
            showToast(UPDATE_SUCCESS);
        } else {
            showToast(ITEM_NO_EXIST);
        }
        CloseDB();
    }

    //編輯帳戶
    public void UpdateAccount(String OldAccount, String NewAccount) {
        OpenDB();
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_account", NewAccount);
        long check = sqLiteDatabase.update(_Account, contentValues
                , "_account = '" + OldAccount + "'", null);
        if (check > 0) {
            showToast(UPDATE_SUCCESS);
        } else {
            showToast(ACCOUNT_NO_EXIST);
        }
        CloseDB();
    }

    //刪除項目
    public void removeItem(String item) {
        OpenDB();
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        long check = sqLiteDatabase.delete(_Item
                , "_item = '" + item + "'", null);
        if (check > 0) {
            showToast(REMOVE_ITEM);
        } else {
            showToast(ITEM_NO_EXIST);
        }
        CloseDB();
    }

    //刪除帳戶
    public void removeAccount(String account) {
        OpenDB();
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        long check = sqLiteDatabase.delete(_Account
                , "_account = '" + account + "'", null);
        if (check > 0) {
            showToast(REMOVE_ACCOUNT);
        } else {
            showToast(ACCOUNT_NO_EXIST);
        }
        CloseDB();
    }

    private void OpenDB() {
        DB = new AccountingDB(context);
    }

    private void CloseDB() {
        if (DB != null) {
            DB.close();
            DB = null;
        }
    }

    //顯示Toast
    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
