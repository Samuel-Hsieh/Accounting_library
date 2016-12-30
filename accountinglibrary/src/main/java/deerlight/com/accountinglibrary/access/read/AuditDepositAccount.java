package deerlight.com.accountinglibrary.access.read;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import deerlight.com.accountinglibrary.access.bean.AuditAccountBean;
import deerlight.com.accountinglibrary.access.write.DBcontroller;
import deerlight.com.accountinglibrary.database.AccountingDB;

/**
 * Created by taaze on 2016/12/29.
 */

public class AuditDepositAccount implements DBcontroller {

    Context context;
    Cursor cursor;
    AccountingDB DB;
    SQLiteDatabase sqLiteDatabase;

    public AuditDepositAccount(Context context) {
        this.context = context;
    }

    //取得帳戶
    public ArrayList<AuditAccountBean> getAccount(String POSTS_SELECT_QUERY) {
        OpenDB();
        ArrayList<AuditAccountBean> ListAccount = new ArrayList<>();
        sqLiteDatabase = DB.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery(POSTS_SELECT_QUERY, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                ListAccount.add(new AuditAccountBean(cursor.getString(0)
                        , cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        CloseDB();
        return ListAccount;
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
