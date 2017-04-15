package nssvast.lednss;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by anand on 14-Apr-17.
 */

public class memberEntry extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_entry);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_member_entry);
        setSupportActionBar(toolbar);

        int ward = getIntent().getIntExtra(houseEntry.WARD_INTENT, 0);
        int house = getIntent().getIntExtra(houseEntry.HOUSE_INTENT, 0);
    }
}
