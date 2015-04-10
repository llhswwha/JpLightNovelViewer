package oneway.NovelViewer.ActivityParrent;

import java.util.LinkedList;

import oneway.NovelViewer.R;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class SpinnerActivity extends Activity 
{
	protected void SetSpinnerItems(Spinner sp,LinkedList<String> items)
    {
    	//ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_item_text_view);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
    	for(String item : items)
    		adapter.add(item);
    	sp.setAdapter(adapter);
    }
    protected void SetSpinnerItems(Spinner sp,String[] items)
    {
    	ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
    	for(String item : items)
    		adapter.add(item);
    	
    	sp.setAdapter(adapter);
    }
}
