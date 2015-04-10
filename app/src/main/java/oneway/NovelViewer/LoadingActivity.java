package oneway.NovelViewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import oneway.NovelViewer.Helpers.HandlerMsg;
import oneway.NovelViewer.Novel.NovelManager;
import oneway.NovelViewer.R;
import oneway.NovelViewer.WordBase.WordBaseHelper;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);	//无title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);	//全屏
        setContentView(R.layout.activity_loading);


        textViewProcessInfo=(TextView)findViewById(R.id.textViewProcessInfo);
        loadProgressBar=(ProgressBar)findViewById(R.id.progressBar3);
         imgIDs=new int[count];
        imgIDs[0]=R.id.radioButton;
        imgIDs[1]=R.id.radioButton2;
        imgIDs[2]=R.id.radioButton3;
        imgIDs[3]=R.id.radioButton4;
        imgIDs[4]=R.id.radioButton5;

        new InitialThread().start();
        new IndexThread().start();
    }

    private ProgressBar loadProgressBar;
    private TextView textViewProcessInfo;

    private int time=60000;
    private boolean isStop;
    private int count=5;
    private int[] imgIDs;

    public Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what)
            {
                case HandlerMsg.TYPE_STOP:
                    Intent intent = new Intent(LoadingActivity.this, NovelIndexActivity.class);
                    //Intent intent = new Intent(LoadingActivity.this, WordBaseActivity.class);
                    startActivity(intent);
                    isStop = true;
                    finish();
                    break;
                case HandlerMsg.TYPE_SELECTED:
                    ((RadioButton)findViewById(msg.arg1)).setChecked(true);
                    break;
                case HandlerMsg.TYPE_NO_SELECTED:
                    ((RadioButton)findViewById(msg.arg1)).setChecked(false);
                    break;
                case HandlerMsg.TYPE_PROGRESS:
                    int max=msg.arg2;
                    int id=msg.arg1;
                    textViewProcessInfo.setText(id+"/"+max);

                    loadProgressBar.setMax(max);
                    loadProgressBar.setProgress(id);

                    break;
            }
        }
    };

    public class InitialThread extends Thread{
        @Override
        public void run()
        {
            try {
                //Thread.sleep(time);//替换为初始化代码....
                //NovelManager.load();
                WordBaseHelper.handler=myHandler;
                WordBaseHelper.Load();
                Message msg = new Message();
                msg.what = HandlerMsg.TYPE_STOP;
                myHandler.sendMessage(msg);

            } catch (Exception e) {
                e.printStackTrace();
                Message msg;
                msg = new Message();
                msg.what = HandlerMsg.TYPE_STOP;
                myHandler.sendMessage(msg);
            }
        }
    }

    public class IndexThread extends Thread
    {
        @Override
        public void run()
        {
            Message msg;
            while(!isStop)
            {
                for(int i= 0 ; i < count ; i++)
                {
                    msg = new Message();
                    msg.what = HandlerMsg.TYPE_SELECTED;
                    msg.arg1 = imgIDs[i];
                    myHandler.sendMessage(msg);
                    msg = new Message();
                    if(i==0)
                    {
                        msg.what = HandlerMsg.TYPE_NO_SELECTED;
                        msg.arg1 = imgIDs[count-1];
                        myHandler.sendMessage(msg);
                    }
                    else
                    {
                        msg.what = HandlerMsg.TYPE_NO_SELECTED;
                        msg.arg1 = imgIDs[i-1];
                        myHandler.sendMessage(msg);
                    }
                    SystemClock.sleep(500);
                }
            }
        }
    }
}
