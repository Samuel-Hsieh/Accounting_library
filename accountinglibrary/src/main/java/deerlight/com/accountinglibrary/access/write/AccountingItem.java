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

public class AccountingItem implements DataWrite {

    private final static String _Item = "item"; //金額
    Context context;
    AccountingDB DB;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;

    public AccountingItem(Context context) {
        this.context = context;
    }

    @Override
    public void Insert(String mItem) {
        OpenDB();
        sqLiteDatabase = DB.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("_item", mItem);
        long check = sqLiteDatabase.insert(_Item, null, contentValues);
        if (check > 0) {
            showToast(getString(R.string.INSERT_SUCCESS));
        } else {
            showToast(getString(R.string.ITEM_EXIST));
        }
        CloseDB();
    }

    @Override
    public void Update(String OldItem, String NewItem) {
        OpenDB();
        sqLiteDatabase = DB.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("_item", NewItem);
        long check = sqLiteDatabase.update(_Item, contentValues
                , "_item = '" + OldItem + "'", null);
        if (check > 0) {
            showToast(getString(R.string.UPDATE_SUCCESS));
        } else {
            showToast(getString(R.string.ITEM_NO_EXIST));
        }
        CloseDB();
    }

    @Override
    public void Remove(String item) {
        OpenDB();
        sqLiteDatabase = DB.getWritableDatabase();
        long check = sqLiteDatabase.delete(_Item
                , "_item = '" + item + "'", null);
        if (check > 0) {
            showToast(getString(R.string.REMOVE_ITEM));
        } else {
            showToast(getString(R.string.ITEM_NO_EXIST));
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

    @Override
    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private String getString(int id){
        return  context.getResources().getString(id);
    }
}