package oneway.NovelViewer;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import oneway.NovelViewer.ActivityParrent.SpinnerActivity;
import oneway.NovelViewer.Interface.*;
import oneway.NovelViewer.WordBase.*;

public class WordBaseActivity extends SpinnerActivity
{
	IWordBase wb;
	Spinner spWordType;
	Spinner spWordList;
	TextView tvWordContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.word_base);
        wb=WordBaseHelper.getInstance();
        this.setTitle(this.getTitle()+" "+wb.getCount());
		GetObject();
		InitialSpinnerType();
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	    if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
	     {
	    	//land
	     }
	     else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
	     {
	    	 //port
	     }

	}
	void GetObject()
	{
		spWordType=(Spinner)findViewById(R.id.spWordType);
		spWordList=(Spinner)findViewById(R.id.spWordList);
		tvWordContent=(TextView)findViewById(R.id.tvWordContent);
		tvWordContent.setMovementMethod(ScrollingMovementMethod.getInstance()); 
	}
	void InitialSpinnerType()
	{
		SetSpinnerItems(spWordType,wb.getTypes());
		//spWordType.setOnItemSelectedListener(new SpItemSelectedListener());
	}
    public class SpItemSelectedListener implements OnItemSelectedListener
    {
		public void onItemSelected(AdapterView<?> parrent,View view,int position,long id)
		{
//			Spinner sp=(Spinner)view;
//			if(sp==spWordType)
				InitialSpinnerWordList((int) id);
//			if(sp==spWordList)
//				ShowWord((int) id);
		}
		public void onNothingSelected(AdapterView<?> parrent)
		{
			
		}
    }
    
    public class Sp2ItemSelectedListener implements OnItemSelectedListener
    {
		public void onItemSelected(AdapterView<?> parrent,View view,int position,long id)
		{
				ShowWord((int) id);
		}
		public void onNothingSelected(AdapterView<?> parrent)
		{
			
		}
    }
    
    IWordCollection wc;
    int typeId;
    String typeName;
    void InitialSpinnerWordList(int id)
    {
    	wc=wb.get(id);
		SetSpinnerItems(spWordList,wc.getWordNames());
		spWordList.setOnItemSelectedListener(new Sp2ItemSelectedListener());
		
		typeId=id;
    	typeName=wc.getWordType();
    }
    void ShowWord(int id)
    {
    	tvWordContent.scrollTo(0,0);
        IWordInfo wi=wc.get(id);
    	String wordText=wi.getText();
    	tvWordContent.setText(wordText);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Add");
		menu.add("Save");
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
	 	   String itemText=item.toString();
	 	   if(itemText.equals("Add"))
	 		  OpenAddActivity();
	 	  if(itemText.equals("Save"))
	 	  {
	 		  wb.saveWords();
	 	  }
	 	   return true;
	}
	void OpenAddActivity()
	{
		Intent intent=new Intent();
		intent.putExtra("TypeId", typeId);
		intent.putExtra("TypeName", typeName);
		intent.setClass(this, AddWordActivity.class);
		this.startActivity(intent);
	}

}
