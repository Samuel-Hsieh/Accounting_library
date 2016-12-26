package deerlight.com.accountinglibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by samuel_hsieh on 2016/12/26.
 */

public class Accounting {

    private static final String EXPENSES = "_expenses_money";
    private static final String INCOME = "_income_money";
    String InputType = null;
    Context context;
    String StringMoney;
    String comments;
    String Date;
    String selectedItem;
    long Money;

    public Accounting(Context context, String date, String InputType, String StringMoney, String comments) {
        this.context = context;
        Date = date;
        this.StringMoney = StringMoney;
        this.comments = comments;
        this.InputType = InputType;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void SaveDataToDB(){
        AccountingDB DB = new AccountingDB(context);
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Money = Long.valueOf(StringMoney);
        contentValues.put("_date", Date);
        contentValues.put("_item", selectedItem);
        contentValues.put("_note", comments);
        switch (InputType){
            case "expenses":
                contentValues.put(EXPENSES, Money);
                break;
            case "income":
                contentValues.put(INCOME, Money);
                break;
            default:
                break;
        }
        sqLiteDatabase.insert(InputType, null, contentValues);
        DB.close();
    }
}
