package deerlight.com.accountinglibrary.access.read;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import deerlight.com.accountinglibrary.access.write.DBcontroller;
import deerlight.com.accountinglibrary.database.AccountingDB;

/**
 * Created by taaze on 2016/12/29.
 */

public class AuditAccountingItem implements DBcontroller {

    Context context;
    Cursor cursor;
    AccountingDB DB;
    SQLiteDatabase sqLiteDatabase;

    public AuditAccountingItem(Context context) {
        this.context = context;
    }

    //取得項目
    public ArrayList<String> getItem(String POSTS_SELECT_QUERY) {
        OpenDB();
        ArrayList<String> ListItem = new ArrayList<>();
        sqLiteDatabase = DB.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery(POSTS_SELECT_QUERY, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                ListItem.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        CloseDB();
        return ListItem;
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
        if (cursor != null) {
            cursor.close();
            cursor = null;
        }
    }
}
