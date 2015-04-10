package oneway.NovelViewer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import oneway.NovelViewer.Helpers.Setting;
import oneway.NovelViewer.R;

public class SettingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        EditText etTextSize=(EditText)findViewById(R.id.editTextTextSize);
        etTextSize.setText(Setting.textSize+"");
        EditText etMinCount=(EditText)findViewById(R.id.editTextMinCount);
        etMinCount.setText(Setting.minCount+"");
        EditText etMaxCount=(EditText)findViewById(R.id.editTextMaxCount);
        etMaxCount.setText(Setting.maxCount+"");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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
}
