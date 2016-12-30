package deerlight.com.accountinglibrary.access.read;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import deerlight.com.accountinglibrary.access.write.DBcontroller;
import deerlight.com.accountinglibrary.database.AccountingDB;
import deerlight.com.accountinglibrary.access.bean.AuditBean;

/**
 * Created by taaze on 2016/12/27.
 */

public class Audit implements DBcontroller {

    String POSTS_SELECT_QUERY;
    Context context;
    Cursor cursor;
    AccountingDB DB;
    SQLiteDatabase sqLiteDatabase;

    public Audit(Context context, String sql) {
        this.context = context;
        POSTS_SELECT_QUERY = sql;
    }

    /**
     * 計算total
     */
    public Double getTotal() {
        OpenDB();
        sqLiteDatabase = DB.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery(POSTS_SELECT_QUERY, null);
        Double payoutTotal = 0D;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                payoutTotal = payoutTotal + Double.valueOf(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        CloseDB();
        return payoutTotal;
    }

    /**
     * 抓資料
     */
    public ArrayList<AuditBean> getAudit() {
        OpenDB();
        sqLiteDatabase = DB.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery(POSTS_SELECT_QUERY, null);
        ArrayList<AuditBean> ArrayAudit = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                ArrayAudit.add(new AuditBean(cursor.getInt(0)
                        , cursor.getString(1)
                        , cursor.getString(2)
                        , cursor.getString(3)
                        , cursor.getString(4)
                        , cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        CloseDB();
        return ArrayAudit;
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
