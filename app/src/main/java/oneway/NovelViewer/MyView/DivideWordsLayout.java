package oneway.NovelViewer.MyView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;

import oneway.NovelViewer.Helpers.AppContext;
import oneway.NovelViewer.Helpers.TimeHelper;
import oneway.NovelViewer.IndexTree.DivideWord;
import oneway.NovelViewer.Interface.IWordBase;
import oneway.NovelViewer.MyViewGroup.FlowLayout;
import oneway.NovelViewer.MyViewGroup.SimpleFlowLayout;
import oneway.NovelViewer.WordBase.WordBaseHelper;

/**
 * Created by Administrator on 2014/7/19.
 */
public class DivideWordsLayout
        //extends SimpleFlowLayout
        extends FlowLayout
    {
    public DivideWordsLayout(Context context) {
        super(context);
        //InitTestButtons();
    }

    public DivideWordsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //InitTestButtons();
    }

    public DivideWordsLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //InitTestButtons();
    }

    public void loadWords(String text)
    {
        removeAllViews();
        LinkedList<DivideWord> words=WordBaseHelper.getInstance().getDivideWords(text);
        loadWords(words);
    }

    public void loadWords(LinkedList<DivideWord> words)
    {
        for(int i=0;i<words.size();i++) {
            DivideWord word = words.get(i);
            View wordBox=new WordView(getContext(),word);
            this.addView(wordBox);
        }
    }

    protected boolean isWrap(View childView)
    {
        if(childView instanceof TextView)
        {
            TextView textView=(TextView)childView;
            if(textView.getText().equals("\n"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
}
