package deerlight.com.accountinglibrary.access.write;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import deerlight.com.accountinglibrary.R;
import deerlight.com.accountinglibrary.database.AccountingDB;

/**
 * Created by samuel_hsieh on 2016/12/26.
 */

public class Accounting {

    String InputType = null;
    Context context;
    String StringMoney;
    String comments;
    String Date;
    String selectedItem;
    String mAccount;
    AccountingDB DB;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;

    public Accounting(Context context, String date, String StringMoney, String comments, String InputType, String mAccount) {
        this.context = context;
        Date = date;
        this.StringMoney = StringMoney.trim();
        this.comments = comments;
        this.InputType = InputType;
        this.mAccount = mAccount;
    }

    public Accounting(Context context, String inputType) {
        this.context = context;
        InputType = inputType;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void SaveDataToDB() {
        OpenDB();
        sqLiteDatabase = DB.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("_date", Date);
        contentValues.put("_money", StringMoney);
        contentValues.put("_note", comments);
        contentValues.put("_item", selectedItem);
        contentValues.put("_account", mAccount);
        long check = sqLiteDatabase.insert(InputType, null, contentValues);
        if (check > 0) {
            showToast(getString(R.string.INSERT_SUCCESS));
        } else {
            showToast(getString(R.string.FAILED));
        }
        CloseDB();
    }

    public void UpdateDataToDB(int Id) {
        OpenDB();
        sqLiteDatabase = DB.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("_date", Date);
        contentValues.put("_money", StringMoney);
        contentValues.put("_note", comments);
        contentValues.put("_item", selectedItem);
        contentValues.put("_account", mAccount);
        long check = sqLiteDatabase.update(InputType, contentValues
                , "_id = '" + Id + "'", null);
        if (check > 0) {
            showToast(getString(R.string.UPDATE_SUCCESS));
        } else {
            showToast(getString(R.string.FAILED));
        }
        CloseDB();
    }

    public void RemoveDataToDB(int Id) {
        OpenDB();
        sqLiteDatabase = DB.getWritableDatabase();
        long check = sqLiteDatabase.delete(InputType
                , "_id = '" + Id + "'", null);
        if (check > 0) {
            showToast(getString(R.string.REMOVE_SUCCESS));
        } else {
            showToast(getString(R.string.FAILED));
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
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
            sqLiteDatabase = null;
        }
        if (contentValues != null) {
            contentValues.clear();
            contentValues = null;
        }
    }

    //顯示Toast
    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private String getString(int id){
        return  context.getResources().getString(id);
    }
}
