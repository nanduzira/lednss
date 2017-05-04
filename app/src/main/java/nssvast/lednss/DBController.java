package nssvast.lednss;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Created by anand on 25-Apr-17.
 */

public class DBController extends SQLiteOpenHelper {

    public String SQL_CREATE_HOUSE_ENTRIES;
    public String SQL_CREATE_MEMBER_ENTRIES;
    public String SQL_VIEW_HOUSE_ENTRIES;
    public String SQL_VIEW_MEMBER_ENTRIES;
    public String SQL_UPDATE_HOUSE_ENTRIES;
    public String SQL_UPDATE_MEMBER_ENTRIES;

    public static final String TAG = "DB_TAG";

    public DBController(Context context) {
        super(context, "ledKerala", null, 1);
        SQL_CREATE_HOUSE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + FeedReaderContract.FeedEntryHouses.TABLE_NAME + "(" +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WARD + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HOUSE + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HEAD + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ADDRESS + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_RATION + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_CASTE + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_OWN_LAND + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LAND + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_INCOME + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ELECTRICITY + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LANDLINE + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_GAS + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WATER + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LATERINE + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_VEHICLE_TYPE + " VARCHAR," +
                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LITERACY + " VARCHAR," +
                        FeedReaderContract.UPDATE_STATUS + " VARCHAR);";
        SQL_CREATE_MEMBER_ENTRIES =
                "CREATE TABLE IF NOT EXISTS " + FeedReaderContract.FeedEntryMembers.TABLE_NAME + "(" +
                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_WARD + " VARCHAR," +
                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_HOUSE + " VARCHAR," +
                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_NAME + " VARCHAR," +
                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_AGE + " VARCHAR," +
                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_SEX + " VARCHAR," +
                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_EDU_QUALIFICATIONS + " VARCHAR," +
                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_JOB + " VARCHAR," +
                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_UID + " VARCHAR," +
                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_ELECTION_ID + " VARCHAR," +
                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_GOVT_AIDS + " VARCHAR," +
                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_MOB_NO + " VARCHAR," +
                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_ANY_TRAITS + " VARCHAR," +
                        FeedReaderContract.UPDATE_STATUS + " VARCHAR);";

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_HOUSE_ENTRIES);
        db.execSQL(SQL_CREATE_MEMBER_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int ver_old, int cur_ver) {
        onCreate(db);
    }

    public void insertData(HashMap<String, String > houseValues, List<List<String>> memberList, int n) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WARD, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WARD));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HOUSE, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HOUSE));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HEAD, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HEAD));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ADDRESS, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ADDRESS));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_RATION, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_RATION));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_CASTE, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_CASTE));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_OWN_LAND, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_OWN_LAND));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LAND, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LAND));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_INCOME, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_INCOME));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ELECTRICITY, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ELECTRICITY));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LANDLINE, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LANDLINE));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_GAS, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_GAS));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WATER, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WATER));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LATERINE, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LATERINE));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_VEHICLE_TYPE, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_VEHICLE_TYPE));
        values.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LITERACY, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LITERACY));
        values.put(FeedReaderContract.UPDATE_STATUS, "no");
        db.insert(FeedReaderContract.FeedEntryHouses.TABLE_NAME, null, values);
        values.clear();

        for (int i =0; i<n; i++){
            values.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_WARD, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WARD));
            values.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_HOUSE, houseValues.get(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HOUSE));
            values.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_NAME, memberList.get(i).get(0));
            values.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_AGE, memberList.get(i).get(1));
            values.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_SEX, memberList.get(i).get(2));
            values.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_EDU_QUALIFICATIONS, memberList.get(i).get(3));
            values.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_JOB, memberList.get(i).get(4));
            values.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_UID, memberList.get(i).get(5));
            values.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_ELECTION_ID, memberList.get(i).get(6));
            values.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_GOVT_AIDS, memberList.get(i).get(7));
            values.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_MOB_NO, memberList.get(i).get(8));
            values.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_ANY_TRAITS, memberList.get(i).get(9));
            values.put(FeedReaderContract.UPDATE_STATUS, "no");
            db.insert(FeedReaderContract.FeedEntryMembers.TABLE_NAME, null, values);
            values.clear();
        }
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAllUsers(int i) {
        ArrayList<HashMap<String, String>> allValues = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        if (i == 1) {
            SQL_VIEW_HOUSE_ENTRIES = "SELECT * FROM " + FeedReaderContract.FeedEntryHouses.TABLE_NAME + ";";
            cursor = db.rawQuery(SQL_VIEW_HOUSE_ENTRIES, null);
            if (cursor.moveToFirst())
                do {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WARD, cursor.getString(0));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HOUSE, cursor.getString(1));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HEAD, cursor.getString(2));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ADDRESS, cursor.getString(3));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_RATION, cursor.getString(4));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_CASTE, cursor.getString(5));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_OWN_LAND, cursor.getString(6));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LAND, cursor.getString(7));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_INCOME, cursor.getString(8));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ELECTRICITY, cursor.getString(9));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LANDLINE, cursor.getString(10));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_GAS, cursor.getString(11));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WATER, cursor.getString(12));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LATERINE, cursor.getString(13));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_VEHICLE_TYPE, cursor.getString(14));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LITERACY, cursor.getString(15));
                    allValues.add(map);
                } while (cursor.moveToNext());
            cursor.close();
        } else if (i == 2) {
            SQL_VIEW_MEMBER_ENTRIES = "SELECT * FROM " + FeedReaderContract.FeedEntryMembers.TABLE_NAME + ";";
            cursor = db.rawQuery(SQL_VIEW_MEMBER_ENTRIES, null);
            if (cursor.moveToFirst())
                do {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_WARD, cursor.getString(0));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_HOUSE, cursor.getString(1));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_NAME, cursor.getString(2));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_AGE, cursor.getString(3));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_SEX, cursor.getString(4));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_EDU_QUALIFICATIONS, cursor.getString(5));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_JOB, cursor.getString(6));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_UID, cursor.getString(7));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_ELECTION_ID, cursor.getString(8));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_GOVT_AIDS, cursor.getString(9));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_MOB_NO, cursor.getString(10));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_ANY_TRAITS, cursor.getString(11));
                    allValues.add(map);
                } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return allValues;
    }
    public String composeJSONfromSQLite(int i) {
        ArrayList<HashMap<String, String>> allValues = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        if (i == 1) {
            SQL_VIEW_HOUSE_ENTRIES = "SELECT * FROM " + FeedReaderContract.FeedEntryHouses.TABLE_NAME + " WHERE " + FeedReaderContract.UPDATE_STATUS + " = 'no';";
            cursor = db.rawQuery(SQL_VIEW_HOUSE_ENTRIES, null);
            if (cursor.moveToFirst())
                do {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WARD, cursor.getString(0));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HOUSE, cursor.getString(1));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HEAD, cursor.getString(2));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ADDRESS, cursor.getString(3));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_RATION, cursor.getString(4));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_CASTE, cursor.getString(5));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_OWN_LAND, cursor.getString(6));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LAND, cursor.getString(7));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_INCOME, cursor.getString(8));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ELECTRICITY, cursor.getString(9));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LANDLINE, cursor.getString(10));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_GAS, cursor.getString(11));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WATER, cursor.getString(12));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LATERINE, cursor.getString(13));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_VEHICLE_TYPE, cursor.getString(14));
                    map.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LITERACY, cursor.getString(15));
                    allValues.add(map);
                } while (cursor.moveToNext());
            cursor.close();
        } else if (i == 2) {
            SQL_VIEW_MEMBER_ENTRIES = "SELECT * FROM " + FeedReaderContract.FeedEntryMembers.TABLE_NAME + " WHERE " + FeedReaderContract.UPDATE_STATUS + " = 'no';";
            cursor = db.rawQuery(SQL_VIEW_MEMBER_ENTRIES, null);
            if (cursor.moveToFirst())
                do {
                    HashMap<String, String> map = new HashMap<>();
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_WARD, cursor.getString(0));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_HOUSE, cursor.getString(1));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_NAME, cursor.getString(2));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_AGE, cursor.getString(3));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_SEX, cursor.getString(4));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_EDU_QUALIFICATIONS, cursor.getString(5));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_JOB, cursor.getString(6));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_UID, cursor.getString(7));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_ELECTION_ID, cursor.getString(8));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_GOVT_AIDS, cursor.getString(9));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_MOB_NO, cursor.getString(10));
                    map.put(FeedReaderContract.FeedEntryMembers.COLUMN_NAME_ANY_TRAITS, cursor.getString(11));
                    allValues.add(map);
                } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        Gson gson = new GsonBuilder().create();
        String str = gson.toJson(allValues);
        Log.d(TAG, "composeJSONfromSQLite: " + str);
        return str;
    }

    public String getSyncStatus() {
        String msg = null;
        if (this.dbSyncCount(1)+this.dbSyncCount(2) == 0)
            msg = "SQLite & Remote MySQL DBs are in Sync!";
        else
            msg = "DB Sync needed";
        return msg;
    }

    public int dbSyncCount(int i) {
        int count = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        if (i == 1) {
            SQL_VIEW_HOUSE_ENTRIES = "SELECT * FROM " + FeedReaderContract.FeedEntryHouses.TABLE_NAME + " WHERE " + FeedReaderContract.UPDATE_STATUS + " = 'no';";
            cursor = db.rawQuery(SQL_VIEW_HOUSE_ENTRIES, null);
            count = cursor.getCount();
            cursor.close();
        } else if (i == 2) {
            SQL_VIEW_MEMBER_ENTRIES = "SELECT * FROM " + FeedReaderContract.FeedEntryMembers.TABLE_NAME + " WHERE " + FeedReaderContract.UPDATE_STATUS + " = 'no';";
            cursor = db.rawQuery(SQL_VIEW_MEMBER_ENTRIES, null);
            count = cursor.getCount();
            cursor.close();
        }
        db.close();
        return count;
    }

    public void updateSyncStatus(String wardNo, String houseNo, String status, int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(i == 1) {
            SQL_UPDATE_HOUSE_ENTRIES = "UPDATE " + FeedReaderContract.FeedEntryHouses.TABLE_NAME + " SET update_status = '" + status + "' WHERE " + FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WARD + " = '" + wardNo + "' AND " + FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HOUSE + " = '" + houseNo + "';";
            db.execSQL(SQL_UPDATE_HOUSE_ENTRIES);
        } else if (i == 2) {
            SQL_UPDATE_MEMBER_ENTRIES = "UPDATE " + FeedReaderContract.FeedEntryMembers.TABLE_NAME + " SET update_status = '" + status + "' WHERE " + FeedReaderContract.FeedEntryMembers.COLUMN_NAME_WARD + " = '" + wardNo + "' AND " + FeedReaderContract.FeedEntryMembers.COLUMN_NAME_HOUSE + " = '" + houseNo + "';";
            db.execSQL(SQL_UPDATE_MEMBER_ENTRIES);
        }
        db.close();
    }
}
