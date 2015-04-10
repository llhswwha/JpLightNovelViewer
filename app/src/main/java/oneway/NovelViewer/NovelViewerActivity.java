package oneway.NovelViewer;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import oneway.NovelViewer.ActivityParrent.SpinnerActivity;
import oneway.NovelViewer.MyView.DivideWordsBox;
import oneway.NovelViewer.Novel.Novel;
import oneway.NovelViewer.Novel.NovelPart;

public class NovelViewerActivity extends SpinnerActivity 
{
	Novel novel;
    TextView tvNovel;
    Spinner spIndex;
    LinearLayout llWords;
    
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novel_viewer);
        LoadNovel();

        spIndex=(Spinner)findViewById(R.id.spNovelParts);
        tvNovel=(TextView)findViewById(R.id.tvNovel);
        tvNovel.setMovementMethod(ScrollingMovementMethod.getInstance());
        spIndex.setOnItemSelectedListener((OnItemSelectedListener)new SpItemSelectedListener());
        SetSpinnerItems(spIndex,novel.getIndex());
    }
    
    void LoadNovel()
    {
    	Intent intent=getIntent();
        String fileName=intent.getStringExtra("fileName");
        String dir=intent.getStringExtra("dir");
        novel=new Novel(fileName,dir);
    }
    
    public class SpItemSelectedListener implements OnItemSelectedListener
    {
		public void onItemSelected(AdapterView<?> parrent,View view,int position,long id)
		{
			ShowContent((int) id);
		}
		public void onNothingSelected(AdapterView<?> parrent)
		{
			
		}
    }
    void ShowContent(int id)
    {
    	//etContent.setText("  ");
    	NovelPart novelPart=novel.get(id);
    	String text=novelPart.getText();
    	//etContent.setText(text);
    	tvNovel.scrollTo(0, 0);
    	tvNovel.setText(text);
    	
    	//ShowDivideWords(text);
    }
//    void ShowDivideWords(String text)
//    {
//    	String[] lines=text.split("\n");
//    	String line1=lines[0];
//    	DivideWordsBox dwb=new DivideWordsBox(line1,llWords.getWidth(),this);
//    	llWords.removeAllViews();
//    	for(LinearLayout ll:dwb.lls)
//    	{
//    		llWords.addView(ll);
//    	}
//    }
}
