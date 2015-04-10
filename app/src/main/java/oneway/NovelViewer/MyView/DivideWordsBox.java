package oneway.NovelViewer.MyView;

import java.util.LinkedList;

import oneway.NovelViewer.NovelViewerWordsActivity;
import oneway.NovelViewer.IndexTree.DivideWord;
import oneway.NovelViewer.WordBase.WordBase;
import android.graphics.Color;
import android.text.ClipboardManager;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DivideWordsBox {
    static WordBase wb;
    LinkedList<DivideWord> words;
    //public LinkedList<LinearLayout> lls=new LinkedList<LinearLayout>();
    public LinearLayout llAllWords;
    public LinearLayout llPosibleWords;
    NovelViewerWordsActivity novelViewerWordsActivity;
    public DivideWordsBox(String text,int width,NovelViewerWordsActivity novelViewerWordsActivity)
    {
        llAllWords=new LinearLayout(novelViewerWordsActivity);
        llAllWords.setOrientation(1);


        this.novelViewerWordsActivity=novelViewerWordsActivity;
        if(wb==null)
            wb=new WordBase();
        words=wb.getDivideWords(text);

        double x=0;int y=0;
        LinearLayout ll=new LinearLayout(novelViewerWordsActivity);
        int width1=50;
        int count=width/width1-1;
        //int wordMaxCount=width/31;//720/32=22.5 720/36=20 720/40=16
        int wordMaxCount=StaticArgs.WordCount;
        String line="";
        LinkedList<String> wordsStr=new LinkedList<String>();
        for(int i=0;i<words.size();i++)
        {

            DivideWord word=words.get(i);
            wordsStr.add(word.realWord);

            if(word.realWord.equals(""))
            {
                x++;
                continue;
            }
            Button btn=CreateButton(novelViewerWordsActivity,word);


            double btnWidth=word.GetLength();
            x+=btnWidth*1;
            line+=word.realWord;
//			if(word.realWord.equals("。"))
//			{
//				ll.addView(btn);
//				x=0;
//				lls.add(ll);
//				ll=new LinearLayout(novelViewerWordsActivity);
//			}
//			else
            {

                if(x>wordMaxCount||word.realWord.equals("\n"))
                {
                    x=btnWidth*1;
                    line=word.realWord;
                    //lls.add(ll);

                    Button btnSpace=CreateButton(novelViewerWordsActivity);
                    ll.addView(btnSpace);

                    llAllWords.addView(ll);
                    ll=new LinearLayout(novelViewerWordsActivity);
                }
                if(!word.realWord.equals("\n"))
                    ll.addView(btn);
            }
        }
        //lls.add(ll);
        llAllWords.addView(ll);

        llPosibleWords=new LinearLayout(novelViewerWordsActivity);
        llPosibleWords.setOrientation(1);
        llAllWords.addView(llPosibleWords);

        tvWordContent=new TextView(novelViewerWordsActivity);
        tvWordContent.setScrollBarStyle(0);
        tvWordContent.setSaveEnabled(true);
        tvWordContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        llAllWords.addView(tvWordContent);
    }
    TextView tvWordContent;
    public LinkedList<Button> btns=new LinkedList<Button>();
    Button CreateButton(NovelViewerWordsActivity novelViewerWordsActivity,DivideWord word)
    {
        Button btn=new Button(novelViewerWordsActivity);
        btns.add(btn);
        if(word.GetWordCount()==0)
            btn.setTextColor(Color.BLACK);
        else if(word.GetWordCount()==1)
            btn.setTextColor(Color.BLUE);
        else
        {
            boolean only=word.CanBeOnly();
            if(only)
                btn.setTextColor(Color.BLUE);
            else
                btn.setTextColor(Color.RED);
        }
        btn.setPadding(10, 2, 12, 2);//字与控件边缘的距离

        btn.setText(word.realWord);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(-9, -10, -9, -10);//按钮之间的距离
        btn.setLayoutParams(lp);
        btn.getLayoutParams().height=StaticArgs.ButtonHeight;
        btn.setTextSize(StaticArgs.ButtonSize);
        btn.setOnClickListener(new BtnOnClickListenser());

        return btn;
    }
    Button CreateButton(NovelViewerWordsActivity novelViewerWordsActivity,String text)
    {
        Button btn=CreateButton(novelViewerWordsActivity);
        btn.setText(text);
        return btn;
    }
    Button CreateButton(NovelViewerWordsActivity novelViewerWordsActivity)
    {
        Button btn=new Button(novelViewerWordsActivity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(-9, -10, -9, -10);//按钮之间的距离
        btn.setLayoutParams(lp);
        btn.getLayoutParams().height=StaticArgs.ButtonHeight;
        btn.setTextSize(StaticArgs.ButtonSize);
        return btn;
    }
    Button CreateButton2(NovelViewerWordsActivity novelViewerWordsActivity)
    {
        Button btn=new Button(novelViewerWordsActivity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(-9, -10, -9, -10);//按钮之间的距离
        btn.setLayoutParams(lp);
        btn.getLayoutParams().height=StaticArgs.ButtonHeight;
        btn.setTextSize(StaticArgs.ButtonSize);
        return btn;
    }
    public class BtnOnClickListenser implements OnClickListener
    {
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            llPosibleWords.removeAllViews();
            btnPosibleWords.clear();
            tvWordContent.setText("");

            Button btn=(Button)arg0;
            int id=btns.indexOf(btn);
            DivideWord word=words.get(id);

            ClipboardManager clip = (ClipboardManager)novelViewerWordsActivity.getSystemService(novelViewerWordsActivity.CLIPBOARD_SERVICE);
            clip.setText(word.realWord);  // 粘贴
            //Toast.makeText(novelViewerWordsActivity,word.realWord+" 复制到粘贴板", Toast.LENGTH_SHORT).show();

            if(word.GetWordCount()==0)
            {
                //btn.setTextColor(Color.RED);
            }
            else if(word.GetWordCount()==1)
            {
                String text=word.GetWordText(0);
                //Toast.makeText(novelViewerWordsActivity, text, Toast.LENGTH_SHORT).show();
                tvWordContent.setText(text);
            }
            else
            {
                boolean only=word.CanBeOnly();
                String text;
                if(only)
                {
                    text=word.GetMostPossibleWordText();
                    //Toast.makeText(novelViewerWordsActivity, text, Toast.LENGTH_SHORT).show();
                    tvWordContent.setText(text);
                }
                else
                {
                    text=word.GetAllWords();
                    PosibleWords=word;
                    ShowPosibleWords(text);
                }
            }
        }
    }

    private void ShowPosibleWords(String text)
    {
        String[] words=text.split("\n");

        LinearLayout ll=new LinearLayout(novelViewerWordsActivity);

        for(int i=0;i<words.length;i++)
        {
            String word=words[i];
            //Button btn=new Button(novelViewerWordsActivity);
            Button btn=CreateButton2(novelViewerWordsActivity);

            btn.setText(word);
//			if(words.length<=3)
//				llPosibleWords.addView(btn);
//			else
//			{
            if(i==3)
            {
                llPosibleWords.addView(ll);
                ll=new LinearLayout(novelViewerWordsActivity);
                ll.addView(btn);
            }
            else
            {
                ll.addView(btn);
            }
            if(i==words.length-1)
                llPosibleWords.addView(ll);
//			}
            btnPosibleWords.add(btn);
            btn.setOnClickListener(new BtnPosibleWordsOnClickListenser());
        }
    }
    DivideWord PosibleWords;
    LinkedList<Button> btnPosibleWords=new LinkedList<Button>();
    public class BtnPosibleWordsOnClickListenser implements OnClickListener
    {
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Button btn=(Button)arg0;
            int id=btnPosibleWords.indexOf(btn);
            String text=PosibleWords.GetWordText(id);
            //Toast.makeText(novelViewerWordsActivity, text, Toast.LENGTH_SHORT).show();
            tvWordContent.setText(text);
        }
    }
}
