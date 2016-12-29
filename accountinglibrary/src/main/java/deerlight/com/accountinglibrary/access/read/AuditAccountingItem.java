package deerlight.com.accountinglibrary.access.read;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import deerlight.com.accountinglibrary.database.AccountingDB;

/**
 * Created by taaze on 2016/12/29.
 */

public class AuditAccountingItem {

    Context context;
    AccountingDB DB;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;

    public AuditAccountingItem(Context context) {
        this.context = context;
    }

    //取得項目
    public ArrayList<String> getItem(String POSTS_SELECT_QUERY) {
        OpenDB();
        ArrayList<String> ListItem = new ArrayList<>();
        sqLiteDatabase = DB.getReadableDatabase();
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
}
