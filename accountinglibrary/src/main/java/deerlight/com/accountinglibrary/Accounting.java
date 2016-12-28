package deerlight.com.accountinglibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
    long Money;

    public Accounting(Context context, String date, String StringMoney, String comments, String InputType, String mAccount) {
        this.context = context;
        Date = date;
        this.StringMoney = StringMoney;
        this.comments = comments;
        this.InputType = InputType;
        this.mAccount = mAccount;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void SaveDataToDB() {
        AccountingDB DB = new AccountingDB(context);
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Money = Long.valueOf(StringMoney);
        contentValues.put("_date", Date);
        contentValues.put("_money", selectedItem);
        contentValues.put("_note", comments);
        contentValues.put("_item", Money);
        contentValues.put("_account", mAccount);
        sqLiteDatabase.insert(InputType, null, contentValues);
        DB.close();
    }
}
