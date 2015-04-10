package oneway.NovelViewer.MyView;

import java.util.LinkedList;

import oneway.NovelViewer.NovelViewerWordsActivity;
import oneway.NovelViewer.IndexTree.DivideWord;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class DivideWordsBoxEx {
    public LinearLayout llAllWords;
    public LinearLayout llPosibleWords;
    NovelViewerWordsActivity novelViewerWordsActivity;
    public DivideWordsBoxEx()
    {

    }
    public DivideWordsBoxEx(int width,LinkedList<Button> btns,NovelViewerWordsActivity novelViewerWordsActivity)
    {
        llAllWords=new LinearLayout(novelViewerWordsActivity);
        llAllWords.setOrientation(1);
        this.novelViewerWordsActivity=novelViewerWordsActivity;

        width=StaticArgs.MaxWidth;

        double x=0;//当前按钮的起始位置，即上一个按钮的
        LinearLayout ll=new LinearLayout(novelViewerWordsActivity);
        for(Button btn : btns)
        {
            double btnWidth=btn.getWidth();//当前按钮的宽度
            double btnLeft=btn.getLeft();
            double btnRight=btn.getRight();
            double btnWidth2=btn.getMeasuredWidth();
            //x+=btnWidth*1+1;
            {
                Button newBtn=CreateButton(novelViewerWordsActivity,btn);
                if((x+btnWidth+10)>=width)
                {
                    Button btnSpace=CreateButton(novelViewerWordsActivity);
                    ll.addView(btnSpace);

                    x=0;
                    llAllWords.addView(ll);
                    ll=new LinearLayout(novelViewerWordsActivity);
                }
                x+=btnWidth+1;
                ll.addView(newBtn);
            }
        }
        //lls.add(ll);
        llAllWords.addView(ll);

        llPosibleWords=new LinearLayout(novelViewerWordsActivity);
        llPosibleWords.setOrientation(0);
        llAllWords.addView(llPosibleWords);
    }

    Button CreateButton(NovelViewerWordsActivity novelViewerWordsActivity,Button originalBtn)
    {
        Button btn=new Button(novelViewerWordsActivity);
        btns.add(btn);
        btn.setPadding(10, 9, 12, 9);//字与控件边缘的距离

        btn.setText(originalBtn.getText());
        btn.setTextColor(originalBtn.getTextColors());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        lp.setMargins(-8, -9, -8, -9);//按钮之间的距离
        btn.setLayoutParams(lp);

        btn.getLayoutParams().height=StaticArgs.ButtonHeight;
        btn.setTextSize(StaticArgs.ButtonSize);

        btn.setOnClickListener(new BtnOnClickListenser());

        return btn;
    }
    Button CreateButton(NovelViewerWordsActivity novelViewerWordsActivity)
    {
        Button btn=new Button(novelViewerWordsActivity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(-8, -9, -8, -9);//按钮之间的距离
        btn.setLayoutParams(lp);
        btn.getLayoutParams().height=StaticArgs.ButtonHeight;
        btn.setTextSize(StaticArgs.ButtonSize);
        return btn;
    }

    LinkedList<DivideWord> words;
    public LinkedList<Button> btns=new LinkedList<Button>();

    String interpunction=";:.,!?\"—'|";
    public class BtnOnClickListenser implements OnClickListener
    {
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            Button btn=(Button)arg0;
            int id=btns.indexOf(btn);
            DivideWord word=words.get(id);
            String name=word.realWord;
            if(!interpunction.contains(name))
                SearchWord_Iciba(name);
        }
    }
    void SearchWord_Iciba(String name)
    {
        name=name.toLowerCase();
        String uriStr="http://wap.iciba.com/cword/"+name;
        Uri uri=Uri.parse(uriStr);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        novelViewerWordsActivity.startActivity(intent);
    }
}
