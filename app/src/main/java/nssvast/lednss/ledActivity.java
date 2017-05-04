package nssvast.lednss;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class ledActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public ProgressDialog progressDialog;
    public DBController controller;

    public ledActivity () {
        controller = new DBController(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Synching SQLite Data with Remote MySQL DB, Please wait...");
        progressDialog.setCancelable(false);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.led, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sync) {
            syncSQLiteMySQLDB();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void syncSQLiteMySQLDB() {
        AsyncHttpClient client= new AsyncHttpClient();
        RequestParams params = new RequestParams();
//        if (k == 1) {
            ArrayList<HashMap<String, String>> houseValues = controller.getAllUsers(1);
            if (houseValues.size() != 0)
                if (controller.dbSyncCount(1) != 0) {
                    progressDialog.show();
                    params.put("housesJSON", controller.composeJSONfromSQLite(1));
                    client.post("http://zira/lednss/inserthouse.php", params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String response) {
                            System.out.println(response);
                            progressDialog.hide();
                            try {
                                JSONArray array = new JSONArray(response);
                                System.out.println(array.length());
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = (JSONObject) array.get(i);
                                    System.out.println(object.get("ward"));
                                    System.out.println(object.get("house_no"));
                                    System.out.println("update_status");
                                    controller.updateSyncStatus(object.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WARD).toString(), object.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HOUSE).toString(), object.get(FeedReaderContract.UPDATE_STATUS).toString(), 1);
                                }
                                Toast.makeText(getApplicationContext(), "DB Syncing Houses Completed!", Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), "Error Occured Houses:[Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Throwable error, String content) {
                            progressDialog.hide();
                            if (statusCode == 404)
                                Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                            else if (statusCode == 500)
                                Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(getApplicationContext(), "Unexpected Error occured {Houses}! [Most Common Error: Device might not be connected to Internet]", Toast.LENGTH_LONG).show();
                        }
                    });
                } else
                    Toast.makeText(getApplicationContext(), "Houses: SQLite & Remote MySQL DBs are in Sync", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "No Data in SQLite DB --> HOUSES, please do enter Details to perform Sync Action", Toast.LENGTH_LONG).show();
//        } else if (k == 2) {
            ArrayList<HashMap<String, String>> memberValues = controller.getAllUsers(2);
            if (memberValues.size() != 0)
                if (controller.dbSyncCount(2) != 0) {
                    progressDialog.show();
                    params.put("membersJSON", controller.composeJSONfromSQLite(2));
                    client.post("http://zira/lednss/insertmember.php", params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String response) {
                            System.out.println(response);
                            progressDialog.hide();
                            try {
                                JSONArray array = new JSONArray(response);
                                System.out.println(array.length());
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = (JSONObject) array.get(i);
                                    System.out.println(object.get("ward"));
                                    System.out.println(object.get("house_no"));
                                    System.out.println("update_status");
                                    controller.updateSyncStatus(object.get(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_WARD).toString(), object.get(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_HOUSE).toString(), object.get(FeedReaderContract.UPDATE_STATUS).toString(), 2);
                                }
                                Toast.makeText(getApplicationContext(), "DB Syncing Members Completed!", Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), "Error Occured Members:[Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Throwable error, String content) {
                            progressDialog.hide();
                            if (statusCode == 404)
                                Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                            else if (statusCode == 500)
                                Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(getApplicationContext(), "Unexpected Error occured {Members}! [Most Common Error: Device might not be connected to Internet]", Toast.LENGTH_LONG).show();
                        }
                    });
                } else
                    Toast.makeText(getApplicationContext(), "Members: SQLite & Remote MySQL DBs are in Sync", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "No Data in SQLite DB --> MEMBERS, please do enter Details to perform Sync Action", Toast.LENGTH_LONG).show();
//        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action

        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
