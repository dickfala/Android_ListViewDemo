package com.example.jameschang.atm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final int RC_LOGIN = 100;
    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean logon = false;
    private FloatingActionButton fab;
    private String[] functions = getResources().getStringArray(R.array.functions);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String[] drinks = {"珍奶","綠茶","烏龍"};
        // setup list view
        setupListView();
        GridView grid = findViewById(R.id.grid);
        ArrayAdapter<String > adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,functions);
        grid.setAdapter(adapter);

//        getSharedPreferences("abc", MODE_PRIVATE)
//                .edit()
//                .putString("WORDS","hello")
//                .commit();
//
//        String s = getSharedPreferences("abc", MODE_PRIVATE)
//                .getString("WORDS", null);
//        Log.d(TAG, "onCreate: " + s);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        if (!logon) {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, RC_LOGIN);

        }

    }

    private void setupListView() {
        ListView list = findViewById(R.id.list);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        getResources().getStringArray(R.array.drinks));
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: " + position);
                String drink = getResources().getStringArray(R.array.drinks)[position];
                Log.d(TAG, "onItemClick: " + drink);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_LOGIN &&
                resultCode == RESULT_OK) {
            String userId = data.getStringExtra("USERID");
            String password = data.getStringExtra("PASSWORD");
            Log.d(TAG, "onActivityResult: " + userId);

            Snackbar.make(fab,"login success", Snackbar.LENGTH_LONG).setActionTextColor(Color.RED).show();

            getSharedPreferences(getString(R.string.pref_name), MODE_PRIVATE)
                    .edit()
                    .putString("USERID", userId)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class IconAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return functions.length;
        }

        @Override
        public Object getItem(int position) {
            return functions[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
