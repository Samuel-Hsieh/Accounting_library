package deerlight.com.accountinglibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by samuel_hsieh on 2016/12/26.
 */

public class AddItems {

    String items;
    Context context;
    private final static String _Items = "items";

    public AddItems(Context context, String items) {
        this.items = items;
        this.context = context;
    }

    public void SaveItemsToDB(){
        AccountingDB DB = new AccountingDB(context);
        SQLiteDatabase sqLiteDatabase = DB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_item", items);
        sqLiteDatabase.insert(_Items, null, contentValues);
        DB.close();
    }
}
