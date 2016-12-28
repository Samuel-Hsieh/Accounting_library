package deerlight.com.accountinglibrary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by taaze on 2016/12/27.
 */

public class Audit {

    Context context;
    ArrayList<AuditListItems> ArrayAudit = new ArrayList<>();
    Cursor cursor;

    public Audit(Context context, String POSTS_SELECT_QUERY) {
        this.context = context;
        AccountingDB DB = new AccountingDB(context);
        SQLiteDatabase sqLiteDatabase = DB.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery(POSTS_SELECT_QUERY, null);
    }

    /**
     * 計算total
     */
    public Double getTotal() {
        Double payoutTotal = 0D;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                payoutTotal = payoutTotal + Double.valueOf(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        return payoutTotal;
    }

    /**
     * 抓資料
     */
    public ArrayList<AuditListItems> getAudit() {
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                ArrayAudit.add(new AuditListItems(cursor.getString(0)
                        , cursor.getString(1)
                        , cursor.getString(2)
                        , cursor.getString(3)
                        , cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return ArrayAudit;
    }
}
