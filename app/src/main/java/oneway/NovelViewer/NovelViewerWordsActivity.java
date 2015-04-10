package oneway.NovelViewer;

import java.io.File;
import java.util.LinkedList;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SeekBar.OnSeekBarChangeListener;
import oneway.NovelViewer.ActivityParrent.SpinnerActivity;
import oneway.NovelViewer.Helpers.AppContext;
import oneway.NovelViewer.Helpers.DialogHelper;
import oneway.NovelViewer.Helpers.TimeHelper;
import oneway.NovelViewer.MyView.DivideWordsBox;
import oneway.NovelViewer.MyView.DivideWordsLayout;
import oneway.NovelViewer.Novel.Novel;
import oneway.NovelViewer.Novel.NovelPart;
import oneway.NovelViewer.Novel.NovelPartHelper;

public class NovelViewerWordsActivity extends SpinnerActivity
{
    Novel novel;

    Spinner spIndex;
    DivideWordsLayout llWords;
    TextView tvUsedTime;
    SeekBar sbReadProgress;
    TextView tvPercent;
    EditText etCurrentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novel_viewer_words);
        GetViews();
        LoadNovel();
        SetSpinnerItems(spIndex,novel.getIndex());
    }

    void GetViews()
    {
        spIndex=(Spinner)findViewById(R.id.spNovelIndex);
        spIndex.setOnItemSelectedListener(new SpItemSelectedListener());
        llWords=(DivideWordsLayout)findViewById(R.id.llDivideWords);
        tvUsedTime=(TextView)findViewById(R.id.tvUsedTime);
        tvPercent=(TextView)findViewById(R.id.tvPercent);
        etCurrentId=(EditText)findViewById(R.id.etCurrentIndex);
        sbReadProgress=(SeekBar)findViewById(R.id.sbReadProgress);
        sbReadProgress.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                if(progress<=0)
                    return;
                if(currentLineCount!=0)
                    currentLineCount=progress-1;
                SetProgress();
                ShowDivideWords();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
    LinkedList<String> lines=new LinkedList<String>();
    int currentLineCount;
    public void BtnGoToIndex_OnClick(View view)
    {
        GetToIndex();
    }
    public void BtnPreLine_OnClick(View view)
    {
        ShowPreLine();
    }
    public void BtnNextLine_OnClick(View view)
    {
        ShowNextLine();
    }
    void GetToIndex()
    {
        int id=Integer.parseInt(etCurrentId.getText().toString());
        currentLineCount=id-1;
        SetProgress();
    }
    void ShowContent(int id)//显示内容
    {
        NovelPart novelPart=novel.get(id);
        lines=novelPart.GetLines();//得到所有行，最小显示单位。
        currentLineCount=0;
        int max=lines.size();
        try
        {
            sbReadProgress.setMax(max);
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        SetProgress();
        ShowDivideWords();//在这切词并以按钮的形式把单词排列出来

    }
    DivideWordsBox dwb;
    void SetProgress()
    {
        try
        {
            int max=sbReadProgress.getMax();
            //sbReadProgress.setProgress(max-1);
            if(currentLineCount>=0&&currentLineCount<max)
            {
                double percent=(currentLineCount+1.0)/max;
                int percent2=(int)(percent*100);
                etCurrentId.setText((currentLineCount+1)+"");
                tvPercent.setText("/"+max+" "+percent2+"%");
                sbReadProgress.setProgress(currentLineCount+1);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    String currentLine="";
    void ShowDivideWords()
    {
        if(currentLineCount<0||currentLineCount==lines.size())
            return;
        String line=lines.get(currentLineCount);
        if(line!=currentLine)
            currentLine=line;
        else
            return;
        if(currentLine.equals(""))
        {
            //Toast.makeText(this, "该行为空", Toast.LENGTH_SHORT).show();
            currentLineCount++;
            ShowDivideWords();
            return;
        }

        if(NovelPartHelper.IsImage(currentLine))
        {
            ShowImage(currentLine);
            return;
        }

        long beforeTime=System.currentTimeMillis();
        llWords.loadWords(currentLine);
        AppContext.allDivideTime = TimeHelper.getTime(beforeTime);
        String message=String.format("用时  %.2f s", AppContext.allDivideTime);
        tvUsedTime.setText(message);
    }

    private String GetImagePath(String line)
    {
        line=line.trim();
        String filePath="";
        if(line.contains("※［＃挿絵画像 "))//※［＃挿絵画像 01_011］｜挿絵《P.11》
        {
            int id1=9;
            int id2=line.indexOf('］');
            String fileName=line.substring(id1,id2);
            filePath=novel.GetDirectory()+"/img/"+fileName+".jpg";

        }
        else if(line.contains("［＃挿絵"))//［＃挿絵（img/01_000.jpg）入る］
        {
            int id1=6;
            int id2=line.indexOf('）');
            String fileName=line.substring(id1,id2);
            filePath=novel.GetDirectory()+"/"+fileName;
        }
        else if(line.contains("<img src="))//<img src="img/02_000a.jpg">
        {
            int id1=line.indexOf("img/");
            int id2=line.length()-2;
            String fileName=line.substring(id1,id2);
            filePath=novel.GetDirectory()+"/"+fileName;
        }
        return filePath;
    }
    private void ShowImage(String line)
    {
        String imagePath=GetImagePath(line);
        File file=new File(imagePath);
        if(file.exists())
        {
            ImageView iv=new ImageView(this);
            Bitmap bm=BitmapFactory.decodeFile(imagePath);
            iv.setImageBitmap(bm);
            llWords.removeAllViews();
            llWords.addView(iv);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                ShowNextLine();
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                ShowPreLine();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    void ShowNextLine()
    {
        currentLineCount++;
        if(currentLineCount<lines.size())
        {
            SetProgress();
        }
    }

    void ShowPreLine()
    {
        currentLineCount--;
        if(currentLineCount>=0)
        {
            SetProgress();
        }
    }

    void LoadNovel()
    {
        Intent intent=getIntent();
        String fileName=intent.getStringExtra("fileName");
        String dir=intent.getStringExtra("dir");
        novel=new Novel(fileName,dir);
        this.setTitle(novel.name);
    }

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
    public void BtnReturn_OnClick(View view)
    {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu.add("Ajust");
        menu.add(R.string.menuAddWord);
        menu.add(R.string.menuShowDic).setIcon(android.R.drawable.ic_menu_help);
        menu.add(R.string.menuPrevPage);
        menu.add(R.string.menuNextPage);
        menu.add(R.string.menuShowWordsInfo);
        menu.add(R.string.menuOpenFile);
        menu.add(R.string.menuSetting);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        String itemText=item.toString();
        if(itemText.equals(getString(R.string.menuPrevPage)))
            ShowPreLine();
        else if(itemText.equals(getString(R.string.menuNextPage)))
            ShowNextLine();
        /*else if(itemText.equals("Ajust"))
            AjustButtons();*/
        else if(itemText.equals(getString(R.string.menuAddWord)))
            OpenAddActivity();
        else if(itemText.equals(getString(R.string.menuShowDic)))
            OpenDicActivity();
        else if(itemText.equals(getString(R.string.menuShowWordsInfo)))
            showWordsInfo();
        else if(itemText.equals(getString(R.string.menuOpenFile)))
            openNovelTextViewActivity();
        else if(itemText.equals(getString(R.string.menuSetting)))
            openSettingActivity();
        return true;
    }

    private void openSettingActivity()
    {
        Intent intent=new Intent();
        intent.setClass(this,SettingActivity.class);
        startActivity(intent);
    }

    private void openNovelTextViewActivity()
    {
        Intent intent=new Intent();
        intent.putExtra("fileName",novel.path);
        intent.setClass(this,NovelTextViewActivity.class);
        startActivity(intent);
    }

    private void showWordsInfo()
    {
        String text=AppContext.getInfo();
        DialogHelper.show(this,"信息",text);
    }

    void OpenAddActivity()
    {
        Intent intent=new Intent();
        intent.setClass(this, AddWordActivity.class);
        this.startActivity(intent);
    }
    void OpenDicActivity()
    {
        Intent intent=new Intent();
        intent.setClass(this, WordBaseActivity.class);
        this.startActivity(intent);
    }

    /*
    boolean AjustButtons()//调整按钮的位置，主要是根据按钮的实际宽度产生一组新的布局
    {
        //GetButtonWidths();
        if(dwb.btns.size()==0)	return false;
        Button btnTest=dwb.btns.get(1);
        if(btnTest.getWidth()==0)
            return false;
        DivideWordsBoxEx dwbEx=new DivideWordsBoxEx(llWords.getWidth(),dwb.btns,this);
        llWords.removeAllViews();
        llWords.addView(dwbEx.llAllWords);
        return true;
    }*/

    /*
    //定时器，用来显示当前单词所用的时间
    Handler handler=new Handler();
    Runnable runnable;
    void InitialSelfTimer()
    {
        runnable=new Runnable(){
            public void run() {
                if(!AjustButtons())//失败的话继续延迟
                    handler.postDelayed(runnable, 1);
            }
        };
        //handler.postDelayed(runnable, 1);//显示后再进行调整
    }
    */
}
