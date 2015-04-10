package oneway.NovelViewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import oneway.NovelViewer.Data.MyFileIO;
import oneway.NovelViewer.R;

public class NovelTextViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_text_view);
        textView=(TextView)findViewById(R.id.novelContentTextView);
        Intent intent=getIntent();
        String fileName=intent.getStringExtra("fileName");
        String text= MyFileIO.ReadAllText(fileName);
        textView.setText(text);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
    TextView textView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_novel_text_view, menu);
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
