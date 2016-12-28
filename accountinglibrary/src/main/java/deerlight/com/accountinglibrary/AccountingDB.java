package deerlight.com.accountinglibrary;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.ActionMode;

/**
 * Created by samuel_hsieh on 2016/12/26.
 */

public class AccountingDB extends SQLiteOpenHelper {

    private final static int _DBVersion = 1; //版本
    private final static String _DBName = "accounting.db";  //資料庫名稱
    private final static String _Expenses = "expenses"; //支出
    private final static String _Income = "income"; //收入
    private final static String _Moneys = "moneys"; //金額

    public AccountingDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public AccountingDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public AccountingDB(Context context) {
        this(context, _DBName, null, _DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String ExpensesSQL = "CREATE TABLE IF NOT EXISTS " + _Expenses + "( " +
                "_date DATE NULL, " +
                "_money VARCHAR NULL, " +
                "_note VARCHAR NULL, " +
                "_item VARCHAR NULL, " +
                "_account VARCHAR NULL " +
                ");";
        db.execSQL(ExpensesSQL);
        final String IncomeSQL = "CREATE TABLE IF NOT EXISTS " + _Income + "( " +
                "_date DATE NULL, " +
                "_money VARCHAR NULL, " +
                "_note VARCHAR NULL, " +
                "_item VARCHAR NULL, " +
                "_account VARCHAR NULL " +
                ");";
        db.execSQL(IncomeSQL);
        final String MoneySQL = "CREATE TABLE IF NOT EXISTS " + _Moneys + "( " +
                "_account VARCHAR NULL, " +
                "_money VARCHAR NULL " +
                ");";
        db.execSQL(MoneySQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        final String Expense = "DROP TABLE " + _Expenses;
        db.execSQL(Expense);
        final String Income = "DROP TABLE " + _Income;
        db.execSQL(Income);
        final String Moneys = "DROP TABLE " + _Moneys;
        db.execSQL(Moneys);
    }
}
