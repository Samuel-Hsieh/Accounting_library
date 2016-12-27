package deerlight.com.accounting_library;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import deerlight.com.accountinglibrary.Accounting;
import deerlight.com.accountinglibrary.AddItems;
import deerlight.com.accountinglibrary.Audit;
import deerlight.com.accountinglibrary.AuditListItems;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void test() {

//        Accounting accounting = new Accounting(this,"2016/12/27","expenses","1000","ggfg");
//        accounting.setSelectedItem("早餐");
//        accounting.SaveDataToDB();

//        AddItems addItems = new AddItems(this,"爽");
//        addItems.SaveItemsToDB();

//        String selectQuery = "SELECT _date , _item , _note , _expenses_money FROM 'expenses'";
//        Audit audit = new Audit(this,selectQuery);
//        ArrayList<AuditListItems> a = audit.getAudit();
//        for(AuditListItems b : a){
//            Log.v("jgj","gjghj: "+b.getData());
//            Log.v("jgj","gjghj: "+b.getItem());
//            Log.v("jgj","gjghj: "+b.getComment());
//            Log.v("jgj","gjghj: "+b.getMoney());
//        }
    }
}
