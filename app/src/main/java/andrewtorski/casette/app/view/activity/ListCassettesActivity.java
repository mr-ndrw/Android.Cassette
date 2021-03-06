package andrewtorski.casette.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import andrewtorski.casette.R;
import andrewtorski.casette.app.di.HasComponent;
import andrewtorski.casette.app.di.components.CassetteComponent;
import andrewtorski.casette.app.di.components.DaggerCassetteComponent;
import andrewtorski.casette.app.model.CassetteModel;
import andrewtorski.casette.app.view.fragment.ListCassettesFragment;

public class ListCassettesActivity extends BaseActivity implements HasComponent<CassetteComponent>,
        ListCassettesFragment.CassetteListListener {

    //region Private fields

    private static final String TAG = "LI_CAS_ACT";

    private CassetteComponent cassetteComponent;

    //endregion Private fields

    //region Methods

    private void initializeInjector() {
        this.cassetteComponent = DaggerCassetteComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    //endregion Methods

    //region Static Methods

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ListCassettesActivity.class);
    }

    //endregion Static Methods

    //region Activity overridden methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_list_cassettes);

        this.initializeInjector();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_cassettes, menu);
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

    //endregion Activity overridden methods

    //region HasComponent<CassetteComponent> implemented methods

    @Override
    public CassetteComponent getComponent() {
        return this.cassetteComponent;
    }

    //endregion HasComponent<CassetteComponent> implemented methods

    //region ListCassettesFragment.CassetteListListener Methods

    @Override
    public void onCassetteClicked(CassetteModel cassetteModel) {
        Log.d(TAG, "Cassette clicked, navigating.");
        this.navigator.navigateToCassetteDetails(this, cassetteModel.getId());
    }

    //endregion ListCassettesFragment.CassetteListListener Methods

}
