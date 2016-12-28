package deerlight.com.accountinglibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by taaze on 2016/12/28.
 */

public class StoreUserData {

    private static final String FAILED = "不明原因失敗";
    private static final String SUCCESS = "新增成功";
    private static final String ITEM_EXIST = "項目已存在";
    private static final String ITEM_NO_EXIST = "項目不存在";
    private static final String ACCOUNT_EXIST = "帳戶已存在";
    private static final String ACCOUNT_NO_EXIST = "帳戶不存在";
    private static final String REMOVE_ITEM = "已刪除項目";
    private static final String REMOVE_ACCOUNT = "已刪除帳戶";

    Context context;

    public StoreUserData(Context context) {
        this.context = context;
    }

    //新增項目
    public void AddItem(String mItem) {
        SharedPreferences sharedPref = context.getSharedPreferences("Item", Context.MODE_PRIVATE);
        if (!sharedPref.contains(mItem)) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(mItem, mItem);
            if (editor.commit()) {
                showToast(SUCCESS);
            } else {
                showToast(FAILED);
            }
        } else {
            showToast(ITEM_EXIST);
        }
    }

    //新增帳戶
    public void AddAccount(String mAccount) {
        SharedPreferences sharedPref = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
        if (!sharedPref.contains(mAccount)) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(mAccount, mAccount);
            if (editor.commit()) {
                showToast(SUCCESS);
            } else {
                showToast(FAILED);
            }
        } else {
            showToast(ACCOUNT_EXIST);
        }
    }

    //取得項目
    public ArrayList<String> getItem() {
        SharedPreferences sharedPref = context.getSharedPreferences("Item", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPref.getAll();
        ArrayList<String> ItemList = new ArrayList<>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            ItemList.add(entry.getValue().toString());
        }
        return ItemList;
    }

    //取得帳戶
    public ArrayList<String> getAccount() {
        SharedPreferences sharedPref = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPref.getAll();
        ArrayList<String> AccountList = new ArrayList<>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            AccountList.add(entry.getValue().toString());
        }
        return AccountList;
    }

    //刪除項目
    public void removeItem(String item) {
        SharedPreferences sharedPref = context.getSharedPreferences("Item", Context.MODE_PRIVATE);
        if (sharedPref.contains(item)) {
            SharedPreferences.Editor editor = sharedPref.edit().remove(item);
            if (editor.commit()) {
                showToast(REMOVE_ITEM);
            } else {
                showToast(FAILED);
            }
        } else {
            showToast(ITEM_NO_EXIST);
        }
    }

    //刪除帳戶
    public void removeAccount(String account) {
        SharedPreferences sharedPref = context.getSharedPreferences("Account", Context.MODE_PRIVATE);
        if (sharedPref.contains(account)) {
            SharedPreferences.Editor editor = sharedPref.edit().remove(account);
            if (editor.commit()) {
                showToast(REMOVE_ACCOUNT);
            } else {
                showToast(FAILED);
            }
        } else {
            showToast(ACCOUNT_NO_EXIST);
        }
    }

    //顯示Toast
    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
