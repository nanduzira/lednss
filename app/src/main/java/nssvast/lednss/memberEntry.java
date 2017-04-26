package nssvast.lednss;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/*
 *
 * Created by anand on 14-Apr-17.
 */

public class memberEntry extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private static final String TAG = "WARD TAG";

//    private ZXingScannerView scannerView;
    private IntentIntegrator qrScan;

    public static final String NAME_INTENT = "nssvast.lednss.NAME";
    public static final String AGE_INTENT = "nssvast.lednss.AGE";
    public static final String SEX_INTENT = "nssvast.lednss.SEX";
    public static final String EDU_QUALIFICATIONS_INTENT = "nssvast.lednss.EDU_QUALIFICATIONS";
    public static final String JOB_INTENT = "nssvast.lednss.JOB";
    public static final String UID_INTENT = "nssvast.lednss.UID";
    public static final String ELECTION_ID_INTENT = "nssvast.lednss.ELECTION_ID";
    public static final String GOVT_AIDS_INTENT = "nssvast.lednss.GOVT_AIDS";
    public static final String MOB_NO_INTENT = "nssvast.lednss.MOB_NO";
    public static final String ANY_TRAITS_INTENT = "nssvast.lednss.ANY_TRAITS";

    public String sex;

    public EditText name, age, eduQualifications, job, uidNo, electionID, govtAids, mobNo, anyTraits;
    public RadioGroup sexRg;
    public Button submitMember,qrEntry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_entry);

        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_member_entry);
        setSupportActionBar(toolbar);

        name = (EditText) findViewById(R.id.name);
        age  = (EditText) findViewById(R.id.age);
        eduQualifications = (EditText) findViewById(R.id.edu_qualifications);
        job = (EditText) findViewById(R.id.job);
        uidNo = (EditText) findViewById(R.id.uid_no);
        electionID = (EditText) findViewById(R.id.election_id_no);
        govtAids = (EditText) findViewById(R.id.govt_aids);
        mobNo = (EditText) findViewById(R.id.mob_no);
        anyTraits = (EditText) findViewById(R.id.any_traits);

        sexRg = (RadioGroup) findViewById(R.id.sex);
        sexRg.setOnCheckedChangeListener(this);

        qrEntry = (Button) findViewById(R.id.qr);
        qrEntry.setOnClickListener(this);
        submitMember = (Button) findViewById(R.id.submit_member);
        submitMember.setOnClickListener(this);

        qrScan = new IntentIntegrator(this);
        qrScan.setPrompt("Scan UID");
        qrScan.setOrientationLocked(false);
        qrScan.setBeepEnabled(false);
        qrScan.setBarcodeImageEnabled(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
        if(null != radioButton && checkedId > -1) {
            switch (checkedId) {
                case R.id.male:
                    sex = "MALE";
                    break;
                case R.id.female:
                    sex = "FEMALE";
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == submitMember) {
            Intent i = new Intent();
            i.putExtra(NAME_INTENT, name.getText().toString());
            i.putExtra(AGE_INTENT, age.getText().toString());
            i.putExtra(SEX_INTENT, sex);
            i.putExtra(EDU_QUALIFICATIONS_INTENT, eduQualifications.getText().toString());
            i.putExtra(JOB_INTENT, job.getText().toString());
            i.putExtra(UID_INTENT, uidNo.getText().toString());
            i.putExtra(ELECTION_ID_INTENT, electionID.getText().toString());
            i.putExtra(GOVT_AIDS_INTENT, govtAids.getText().toString());
            i.putExtra(MOB_NO_INTENT, mobNo.getText().toString());
            i.putExtra(ANY_TRAITS_INTENT, anyTraits.getText().toString());
            setResult(RESULT_OK, i);
            finish();
        } else if (v == qrEntry) {
            qrScan.initiateScan();
//            scannerView = new ZXingScannerView(this);
//            setContentView(scannerView);
//            scannerView.setResultHandler(this);
//            scannerView.startCamera();
//            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
//            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
//            Log.d(TAG,"qr");
//            startActivityForResult(intent,0);
        }
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        scannerView.stopCamera();
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        if (requestCode == 0) {
//            if (resultCode == RESULT_OK) {
//                Log.d(TAG, "onActivityResult: in true");
//                name.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
//                age.setText(intent.getStringExtra("SCAN_RESULT"));
//            } else if (resultCode == RESULT_CANCELED) {
//
//            }
//        }
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            if (result.getContents() == null)
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            else {
                Log.d(TAG, "onActivityResult: "+result.getContents());
                String contents = result.getContents();
                String[] c = contents.split("\"");
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                int currentAge = currentYear-Integer.parseInt(c[11]);
                age.setText(String.valueOf(currentAge));
                sexRg.check((c[9].compareTo("MALE")==0)?R.id.male:R.id.female);
                name.setText(c[7]);
                uidNo.setText(c[5]);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, intent);
        }
    }

//    @Override
//    public void handleResult(Result result) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Scanned UID Data");
//
//        String[] c = result.getText().split("\"");
//        int a = Calendar.YEAR-(Integer.parseInt(c[11]));
//
//        builder.setMessage(c[5]+c[7]+c[9]+a);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        sexRg.check((c[9].equals("MALE"))?R.id.male:R.id.female);
//        uidNo.setText(c[5]);
//        name.setText(c[7]);
//    }
}
