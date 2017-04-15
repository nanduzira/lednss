package nssvast.lednss;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * Created by anand on 09-Apr-17.
 */

public class houseEntry extends AppCompatActivity {

    private static final String TAG = "ward tag";
    public static final String WARD_INTENT = "nssvast.lednss.WARD";
    public static final String HOUSE_INTENT = "nssvast.lednss.HOUSE";

    public int wardNbr;
    public String houseNbr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_entry);

        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_house_entry);
        setSupportActionBar(toolbar);

        wardNbr = getIntent().getIntExtra(wardFragment.WARD_INTENT, 0);

        final EditText wardNo = (EditText) findViewById(R.id.ward_no);
        wardNo.append(""+wardNbr);
        final EditText houseNo = (EditText) findViewById(R.id.house_no);
        final EditText head = (EditText) findViewById(R.id.head);
        final EditText address = (EditText) findViewById(R.id.address);
        final EditText rationNo = (EditText) findViewById(R.id.ration_no);

        final RadioGroup caste = (RadioGroup) findViewById(R.id.caste);
        final RadioGroup ownLand = (RadioGroup) findViewById(R.id.own_land);

        final EditText land = (EditText) findViewById(R.id.land);
        final EditText income = (EditText) findViewById(R.id.income);
        final EditText electricityNo = (EditText) findViewById(R.id.electricity_no);
        final EditText landlineNo = (EditText) findViewById(R.id.landline_no);

        final RadioGroup gasConnection = (RadioGroup) findViewById(R.id.gas_connection);

        final EditText gasAgency = (EditText) findViewById(R.id.gas_agency);
        final EditText drinkingWater = (EditText) findViewById(R.id.drinking_water);

        final RadioGroup laterineFacility = (RadioGroup) findViewById(R.id.laterine_facility);
        final RadioGroup ownVehicle = (RadioGroup) findViewById(R.id.own_vehicle);

        final CheckBox twoWheeler = (CheckBox) findViewById(R.id.two_wheeler);
        final CheckBox threeWheeler = (CheckBox) findViewById(R.id.three_wheeler);
        final CheckBox fourWheeler = (CheckBox) findViewById(R.id.four_wheeler);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_member);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(view.getContext(), memberEntry.class);
                i.putExtra(WARD_INTENT, wardNbr);
                i.putExtra(HOUSE_INTENT, houseNbr);
                startActivity(i);
            }
        });

    }

}
