package nssvast.lednss;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Created by anand on 09-Apr-17.
 */

public class houseEntry extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "WARD TAG";

    public static final String WARD_INTENT = "nssvast.lednss.WARD";

    public int wardNbr, vehicleType, memberCount, i;
    public String caste, ownLand, laterine;

    public EditText wardNo, houseNo, head, address, rationNo, land, income, electricityNo, landlineNo, gasAgency, drinkingWater, literacyCount;
    public RadioGroup casteRg, ownLandRg, gasConnectionRg, laterineFacilityRg, ownVehicleRg;
    public Button submitHouse, viewAll;
    public CheckBox twoWheeler, threeWheeler, fourWheeler;

    public List<List<String>> memberList;
    public HashMap<String, String> houseValues;

//    public SQLiteDatabase db;

//    public String SQL_CREATE_HOUSE_ENTRIES;
//    public String SQL_INSERT_HOUSE_ENTRIES;
//    public String SQL_VIEW_HOUSE_ENTRIES;
//    public String SQL_CREATE_MEMBER_ENTRIES;
//    public String SQL_INSERT_MEMBER_ENTRIES;
//    public String SQL_VIEW_MEMBER_ENTRIES;

    public DBController controller;

    public houseEntry() {
//        SQL_CREATE_HOUSE_ENTRIES =
//                "CREATE TABLE IF NOT EXISTS " + FeedReaderContract.FeedEntryHouses.TABLE_NAME + "(" +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WARD + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HOUSE + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HEAD + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ADDRESS + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_RATION + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_CASTE + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_OWN_LAND + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LAND + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_INCOME + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ELECTRICITY + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LANDLINE + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_GAS + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WATER + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LATERINE + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_VEHICLE_TYPE + " VARCHAR," +
//                        FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LITERACY + " VARCHAR);";
//
//        SQL_VIEW_HOUSE_ENTRIES =
//                "SELECT * FROM " + FeedReaderContract.FeedEntryHouses.TABLE_NAME + ";";
//        SQL_CREATE_MEMBER_ENTRIES =
//                "CREATE TABLE IF NOT EXISTS " + FeedReaderContract.FeedEntryMembers.TABLE_NAME + "(" +
//                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_WARD + " VARCHAR," +
//                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_HOUSE + " VARCHAR," +
//                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_NAME + " VARCHAR," +
//                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_AGE + " VARCHAR," +
//                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_SEX + " VARCHAR," +
//                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_EDU_QUALIFICATIONS + " VARCHAR," +
//                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_JOB + " VARCHAR," +
//                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_UID + " VARCHAR," +
//                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_ELECTION_ID + " VARCHAR," +
//                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_GOVT_AIDS + " VARCHAR," +
//                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_MOB_NO + " VARCHAR," +
//                        FeedReaderContract.FeedEntryMembers.COLUMN_NAME_ANY_TRAITS + " VARCHAR);";
//        SQL_VIEW_MEMBER_ENTRIES =
//                "SELECT * FROM " + FeedReaderContract.FeedEntryMembers.TABLE_NAME + ";";
        memberCount = i = 0;
        memberList = new ArrayList<>();
        houseValues = new HashMap<>();
        controller = new DBController(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_entry);

        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_house_entry);
        setSupportActionBar(toolbar);

        wardNbr = getIntent().getIntExtra(WARD_INTENT, 0);

        wardNo = (EditText) findViewById(R.id.ward_no);
        wardNo.append(""+wardNbr);
        wardNo.setEnabled(false);
        houseNo = (EditText) findViewById(R.id.house_no);
        houseNo.requestFocus();
        head = (EditText) findViewById(R.id.head);
        address = (EditText) findViewById(R.id.address);
        rationNo = (EditText) findViewById(R.id.ration_no);

        casteRg = (RadioGroup) findViewById(R.id.caste);
        casteRg.setOnCheckedChangeListener(this);
        ownLandRg = (RadioGroup) findViewById(R.id.own_land);
        ownLandRg.setOnCheckedChangeListener(this);

        land = (EditText) findViewById(R.id.land);
        income = (EditText) findViewById(R.id.income);
        electricityNo = (EditText) findViewById(R.id.electricity_no);
        landlineNo = (EditText) findViewById(R.id.landline_no);

        gasConnectionRg = (RadioGroup) findViewById(R.id.gas_connection);
        gasConnectionRg.setOnCheckedChangeListener(this);

        gasAgency = (EditText) findViewById(R.id.gas_agency);
        drinkingWater = (EditText) findViewById(R.id.drinking_water);

        laterineFacilityRg = (RadioGroup) findViewById(R.id.laterine_facility);
        laterineFacilityRg.setOnCheckedChangeListener(this);
        ownVehicleRg = (RadioGroup) findViewById(R.id.own_vehicle);
        ownVehicleRg.setOnCheckedChangeListener(this);

        vehicleType = 0;

        twoWheeler = (CheckBox) findViewById(R.id.two_wheeler);
        twoWheeler.setOnClickListener(this);
        threeWheeler = (CheckBox) findViewById(R.id.three_wheeler);
        threeWheeler.setOnClickListener(this);
        fourWheeler = (CheckBox) findViewById(R.id.four_wheeler);
        fourWheeler.setOnClickListener(this);

        literacyCount = (EditText) findViewById(R.id.literacy_count);

        submitHouse = (Button) findViewById(R.id.submit_house);
        submitHouse.setOnClickListener(this);
//        viewAll = (Button) findViewById(R.id.view_all);
//        viewAll.setOnClickListener(this);
//
//        db = openOrCreateDatabase(FeedReaderContract.DB_NAME, Context.MODE_PRIVATE, null);
//        db.execSQL(SQL_CREATE_HOUSE_ENTRIES);
//        db.execSQL(SQL_CREATE_MEMBER_ENTRIES);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_member);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), memberEntry.class);
                startActivityForResult(i, 100);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==submitHouse) {
//            SQL_INSERT_HOUSE_ENTRIES=
//                    "INSERT INTO " + FeedReaderContract.FeedEntryHouses.TABLE_NAME + " VALUES('" +
//                            wardNo.getText() + "','" + houseNo.getText() + "','" +
//                            head.getText() + "','" + address.getText() + "','" +
//                            rationNo.getText() + "','" + caste + "','" + ownLand + "','" +
//                            land.getText() + "','" + income.getText() + "','" +
//                            electricityNo.getText() + "','" + landlineNo.getText() + "','" +
//                            gasAgency.getText() + "','" + drinkingWater.getText() + "','" +
//                            laterine + "','" + vehicleType + "','" + literacyCount.getText() + "');";
//            db.execSQL(SQL_INSERT_HOUSE_ENTRIES);

            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WARD, wardNo.getText().toString());
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HOUSE, houseNo.getText().toString());
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_HEAD, head.getText().toString());
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ADDRESS, address.getText().toString());
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_RATION, rationNo.getText().toString());
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_CASTE, caste);
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_OWN_LAND, ownLand);
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LAND, land.getText().toString());
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_INCOME, income.getText().toString());
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_ELECTRICITY, electricityNo.getText().toString());
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LANDLINE, landlineNo.getText().toString());
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_GAS, gasAgency.getText().toString());
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_WATER, drinkingWater.getText().toString());
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LATERINE, laterine);
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_VEHICLE_TYPE, Integer.toString(vehicleType));
            houseValues.put(FeedReaderContract.FeedEntryHouses.COLUMN_NAME_LITERACY, literacyCount.getText().toString());

//            i = 0;
//            while (i < memberCount) {
//                SQL_INSERT_MEMBER_ENTRIES =
//                        "INSERT INTO " + FeedReaderContract.FeedEntryMembers.TABLE_NAME + " VALUES('" +
//                                wardNo.getText() + "','" + houseNo.getText() + "','" +
//                                memberList.get(i).get(0) + "','" + memberList.get(i).get(1) + "','" +
//                                memberList.get(i).get(2) + "','" + memberList.get(i).get(3) + "','" +
//                                memberList.get(i).get(4) + "','" + memberList.get(i).get(5) + "','" +
//                                memberList.get(i).get(6) + "','" + memberList.get(i).get(7) + "','" +
//                                memberList.get(i).get(8) + "','" + memberList.get(i).get(9) + "');";
//                db.execSQL(SQL_INSERT_MEMBER_ENTRIES);
//                i++;
//            }
//            showMessage("Success","House Added");
//            db.close();

            controller.insertData(houseValues, memberList, memberCount);
            Toast.makeText(getApplicationContext(), "House Added", Toast.LENGTH_SHORT).show();
            finish();
        }
//        else if(v==viewAll) {
//            Cursor c = db.rawQuery(SQL_VIEW_MEMBER_ENTRIES, null);
//            if(c.getCount()==0) {
//                showMessage("Error", "No records found");
//                return;
//            }
//            StringBuffer buffer = new StringBuffer();
//            while(c.moveToNext()) {
//                buffer.append("Ward No:"+ c.getString(0));
//                buffer.append("\nHouse No:"+ c.getString(1));
//                buffer.append("\nHead:"+ c.getString(2));
//                buffer.append("\nAddress:"+ c.getString(3));
//                buffer.append("\nRation:"+ c.getString(4));
//                buffer.append("\nCaste:"+ c.getString(5));
//                buffer.append("\nown_land:"+ c.getString(6));
//                buffer.append("\nland:"+ c.getString(7));
//                buffer.append("\nincom:"+ c.getString(8));
//                buffer.append("\nelectricity:"+ c.getString(9));
//                buffer.append("\nlandline:"+ c.getString(10));
//                buffer.append("\ngas_c:"+ c.getString(11));
//                buffer.append("\nwater:"+ c.getString(12));
//                buffer.append("\nlater:"+ c.getString(13));
//                buffer.append("\nv_type:"+ c.getString(14));
//                buffer.append("\nliteracy_count:"+ c.getString(15));
//            }
//            StringBuffer buff = new StringBuffer();
//            i = 0;
//            while (i < memberCount) {
//                buff.append("NAME:"+memberList.get(i++).get(0));
//            }
//            showMessage("House Details", buffer.toString());
//        }
        else if(v==twoWheeler) {
            if(twoWheeler.isChecked()) {
                vehicleType += 2;
            }
            else if (!twoWheeler.isChecked()) {
                vehicleType -= 2;
            }
        }
        else if(v==threeWheeler) {
            if (threeWheeler.isChecked()) {
                vehicleType += 3;
            }
            else  if (!threeWheeler.isChecked()) {
                vehicleType -= 3;
            }
        }
        else if(v==fourWheeler) {
            if (fourWheeler.isChecked()) {
                vehicleType += 4;
            }
            else if (!fourWheeler.isChecked()) {
                vehicleType -= 4;
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
        if(null != radioButton && checkedId > -1) {
            switch (checkedId) {
                case R.id.obc:
                    caste = getString(R.string.obc);
                    break;
                case R.id.sc:
                    caste = getString(R.string.sc);
                    break;
                case R.id.st:
                    caste = getString(R.string.st);
                    break;
                case R.id.others:
                    caste = getString(R.string.others);
                    break;
                case R.id.yes_land:
                    ownLand = getString(R.string.yes);
                    break;
                case R.id.no_land:
                    ownLand = getString(R.string.no);
                    break;
                case R.id.yes_gas:
                    gasAgency.setEnabled(true);
                    gasAgency.setText("");
                    break;
                case R.id.no_gas:
                    gasAgency.setEnabled(false);
                    gasAgency.setText(getString(R.string.no));
                    break;
                case R.id.yes_laterine:
                    laterine = getString(R.string.yes);
                    break;
                case R.id.no_laterine:
                    laterine = getString(R.string.no);
                    break;
                case R.id.yes_vehicle:
                    twoWheeler.setEnabled(true);
                    threeWheeler.setEnabled(true);
                    fourWheeler.setEnabled(true);
                    break;
                case R.id.no_vehicle:
                    twoWheeler.setEnabled(false);
                    threeWheeler.setEnabled(false);
                    fourWheeler.setEnabled(false);
                    break;
                case R.id.yes_computer:
                    literacyCount.setEnabled(true);
                    literacyCount.setText("");
                    break;
                case R.id.no_computer:
                    literacyCount.setEnabled(false);
                    literacyCount.setText("0");
                    break;
            }
        }
    }

//    public void showMessage(String title,String message)
//    {
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.show();
//    }

    public void onActivityResult(int requestCode, int resultCode, Intent i) {
        super.onActivityResult(requestCode, resultCode, i);
        if (requestCode == 100)
            if (resultCode == RESULT_OK) {
                memberList.add(new ArrayList<String>());
                memberList.get(memberCount).add(i.getStringExtra(memberEntry.NAME_INTENT));
                memberList.get(memberCount).add(i.getStringExtra(memberEntry.AGE_INTENT));
                memberList.get(memberCount).add(i.getStringExtra(memberEntry.SEX_INTENT));
                memberList.get(memberCount).add(i.getStringExtra(memberEntry.EDU_QUALIFICATIONS_INTENT));
                memberList.get(memberCount).add(i.getStringExtra(memberEntry.JOB_INTENT));
                memberList.get(memberCount).add(i.getStringExtra(memberEntry.UID_INTENT));
                memberList.get(memberCount).add(i.getStringExtra(memberEntry.ELECTION_ID_INTENT));
                memberList.get(memberCount).add(i.getStringExtra(memberEntry.GOVT_AIDS_INTENT));
                memberList.get(memberCount).add(i.getStringExtra(memberEntry.MOB_NO_INTENT));
                memberList.get(memberCount).add(i.getStringExtra(memberEntry.ANY_TRAITS_INTENT));
                memberCount++;
            }
    }

}