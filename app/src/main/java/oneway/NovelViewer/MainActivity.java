package oneway.NovelViewer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.*;
import android.widget.*;

import java.util.LinkedList;

import oneway.NovelViewer.IndexTree.DivideWord;
import oneway.NovelViewer.Interface.IWordInfo;
import oneway.NovelViewer.MyView.DivideWordsLayout;
import oneway.NovelViewer.MyView.MyProgressBar;
import oneway.NovelViewer.MyViewGroup.*;
import oneway.NovelViewer.SQLite.SQLiteWordBase;
import oneway.NovelViewer.Tests.TestWordInfo;


public class MainActivity
        extends Activity
        implements Runnable
    {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinkedList<IWordInfo> wordInfos=new LinkedList<IWordInfo>();
        wordInfos.add(new TestWordInfo("a1 b","A1:123"));
        wordInfos.add(new TestWordInfo("a2 c","A2:123"));

        LinkedList<DivideWord> words=new LinkedList<DivideWord>();
        DivideWord divideWord=new DivideWord("a",wordInfos);
        words.add(divideWord);

        DivideWordsLayout layout=new DivideWordsLayout(this);
        layout.loadWords(words);
        setContentView(layout);
    }

        private void dialogTest()
        {
             /*AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("123");
        builder.setMessage("123243");
        LinearLayout linearLayout=new LinearLayout(this);
        TextView btn=new TextView(this);
        btn.setText("Button1");
        linearLayout.addView(btn);
        builder.setView(linearLayout);
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MainActivity.this.finish();
            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("取消",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"123143",Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
        */

        /*new AlertDialog.Builder(this)
                .setTitle("请输入").
                setIcon(android.R.drawable.ic_dialog_info).
                setView(new EditText(this)).
                setPositiveButton("确定", null)
                .setNegativeButton("取消", null).show();

        new AlertDialog.Builder(this)
                .setTitle("复选框")
                .setMultiChoiceItems(new String[] { "Item1", "Item2" }, null, null)
                .setPositiveButton("确定", null)
                .setNegativeButton("取消", null).show();
        */

        /*new AlertDialog.Builder(this)
                .setTitle("单选框")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setSingleChoiceItems(new String[] { "Item1", "Item2" }, 0, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", null).show();*/

            //new AlertDialog.Builder(this).setTitle("列表框").setItems(new String[] { "Item1", "Item2" }, null).setNegativeButton("确定", null).show();

            //LayoutInflater inflater = getLayoutInflater();
            //View layout = inflater.inflate(R.layout.dialog,(ViewGroup) findViewById(R.id.dialog));
            //new AlertDialog.Builder(this).setTitle("自定义布局").setView(layout).setPositiveButton("确定", null).setNegativeButton("取消", null).show();

        /*LayoutInflater inflater=getLayoutInflater();
        View layout=inflater.inflate(R.layout.dialog,(ViewGroup)findViewById(R.id.dialog));
        new AlertDialog.Builder(this).setTitle("自定义布局").setView(layout).setPositiveButton("确定",null).setNegativeButton("取消",null).show();*/

        /*mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("读取ing");
        mProgressDialog.setMessage("正在读取中请稍候");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();*/

        /*mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setIcon(R.drawable.ic_launcher);
        mProgressDialog.setTitle("进度条窗口");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMax(MAX_PROGRESS);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mProgressDialog.dismiss();
            }
        });*/
        /*mProgressDialog.setButton2("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //这里添加点击后的逻辑
            }
        });*/
            mProgressDialog.show();

        /*mProgressDialog=new ProgressDialog(MainActivity.this);
        mProgressDialog.setIcon(R.drawable.ic_launcher);
        mProgressDialog.setTitle("进度");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMax(MAX_PROGRESS);
        mProgressDialog.show();*/

            new Thread(this).start();
        }

    ProgressDialog mProgressDialog;
    final int MAX_PROGRESS=100;
    public void run() {
        int Progress = 0;
        while (Progress < MAX_PROGRESS) {
            try {
                Thread.sleep(100);
                Progress++;
                mProgressDialog.incrementProgressBy(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void ScrollViewTest2()
    {
        ScrollView scrollView=new ScrollView(this);
        scrollView.setBackgroundColor(Color.GREEN);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(500, 300);
        lp2.setMargins(20,20,20,20);
        scrollView.setLayoutParams(lp2);

        DivideWordsLayout layout=new DivideWordsLayout(this);
        layout.setBackgroundColor(Color.BLUE);

        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        String text="";
        for (int i=0;i<15;i++)
        {
            TextView t=new TextView(this);
            t.setText("Text"+i);
            //linearLayout.addView(t);
            //layout.addView(t);

            text+="Text"+i+"\n";
        }

        layout.loadWords(text);

        scrollView.addView(layout);

        //scrollView.addView(linearLayout);

        //setContentView(scrollView);

        LinearLayout linearLayout0=new LinearLayout(this);
        linearLayout0.addView(scrollView);
        setContentView(linearLayout0);
    }
    private void ScrollViewTest()
    {
        SimpleFlowLayout layout=new SimpleFlowLayout(this);
        layout.setBackgroundColor(Color.BLUE);

        ViewGroup.MarginLayoutParams layoutParams=new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20,20,20,20);
        layout.setLayoutParams(layoutParams);

        //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(300, 400);
        //lp.setMargins(20,20,20,20);
        //layout.setLayoutParams(lp);

        for (int i=0;i<10;i++)
        {
            TextView t=new TextView(this);
            t.setText("Text"+i);
            layout.addView(t);
        }

        LinearLayout linearLayout=new LinearLayout(this);
        //linearLayout.addView(layout);

        ScrollView scrollView=new ScrollView(this);
        scrollView.setBackgroundColor(Color.GREEN);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(300, 200);
        lp2.setMargins(20,20,20,20);
        scrollView.setLayoutParams(lp2);

        TextView textView=new TextView(this);
        String text="";
        for (int i=0;i<100;i++)
        {
            text+=i;
        }
        textView.setText(text);
        //textView.setText("123");

        //scrollView.addView(layout);
        scrollView.addView(textView);
        linearLayout.addView(scrollView);

        ViewGroup1 viewGroup1=new ViewGroup1(this);
        setContentView(viewGroup1);

        //setContentView(linearLayout);
    }




    private void LayoutTest()
    {

        ViewGroup layout=null;
        //layout=new ViewGroup2(this);
        layout=new SimpleFlowLayout(this);
        //layout.setBackgroundColor(Color.BLUE);
        //layout.layout(10,10,150,150);
        //layout=new FlowLayout(this);
        //layout=new LinearLayout(this);
        for(int i=0;i<20;i++)
        {
            TextView child=new TextView(this);

            child.setBackgroundColor(Color.parseColor("#7690A5"));

            ViewGroup.MarginLayoutParams layoutParams=new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(15,15,5,5);
            child.setLayoutParams(layoutParams);
            child.setText("child"+i);
            layout.addView(child);
            //ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
            //int childWidth = child.getMeasuredWidth();
            //int childHeight = child.getMeasuredHeight();
        }
        setContentView(layout);

        //setContentView(R.layout.flowlayout_test2);
        //FlowLayout layout=(FlowLayout)findViewById(R.id.flowlayout);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
