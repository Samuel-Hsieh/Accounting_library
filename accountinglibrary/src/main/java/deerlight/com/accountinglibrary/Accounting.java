package deerlight.com.accountinglibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by samuel_hsieh on 2016/12/26.
 */

public class Accounting {

    private static final String FAILED = "不明原因失敗";
    private static final String INSERT_SUCCESS = "新增成功";
    private static final String UPDATE_SUCCESS = "編輯成功";
    private static final String REMOVE_SUCCESS = "刪除成功";
    String InputType = null;
    Context context;
    String StringMoney;
    String comments;
    String Date;
    String selectedItem;
    String mAccount;
    AccountingDB DB;

    public Accounting(Context context, String date, String StringMoney, String comments, String InputType, String mAccount) {
        this.context = context;
        Date = date;
        this.StringMoney = StringMoney.trim();
        this.comments = comments;
        this.InputType = InputType;
        this.mAccount = mAccount;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void SaveDataToDB() {
        OpenDB();
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_date", Date);
        contentValues.put("_money", StringMoney);
        contentValues.put("_note", comments);
        contentValues.put("_item", selectedItem);
        contentValues.put("_account", mAccount);
        long check = sqLiteDatabase.insert(InputType, null, contentValues);
        if (check > 0) {
            showToast(INSERT_SUCCESS);
        } else {
            showToast(FAILED);
        }
        CloseDB();
    }

    public void UpdateDataToDB(int Id) {
        OpenDB();
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_date", Date);
        contentValues.put("_money", StringMoney);
        contentValues.put("_note", comments);
        contentValues.put("_item", selectedItem);
        contentValues.put("_account", mAccount);
        long check = sqLiteDatabase.update(InputType, contentValues
                , "_id = '" + Id + "'", null);
        if (check > 0) {
            showToast(UPDATE_SUCCESS);
        } else {
            showToast(FAILED);
        }
        CloseDB();
    }

    public void RemoveDataToDB(int Id) {
        OpenDB();
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        long check = sqLiteDatabase.delete(InputType
                , "_id = '" + Id + "'", null);
        if (check > 0) {
            showToast(REMOVE_SUCCESS);
        } else {
            showToast(FAILED);
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
