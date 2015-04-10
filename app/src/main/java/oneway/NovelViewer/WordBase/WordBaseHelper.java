package oneway.NovelViewer.WordBase;

import android.os.Handler;
import android.os.Message;

import oneway.NovelViewer.Helpers.AppContext;
import oneway.NovelViewer.Helpers.HandlerMsg;
import oneway.NovelViewer.Helpers.TimeHelper;
import oneway.NovelViewer.Interface.IWordBase;
import oneway.NovelViewer.SQLite.SQLiteWordBase;

/**
 * Created by Administrator on 2015/4/3.
 */
public class WordBaseHelper {

    public static Handler handler;
    public static void reportProgress(int id,int max)
    {
        if(handler!=null)
        {
            Message msg=new Message();
            msg.what= HandlerMsg.TYPE_PROGRESS;
            msg.arg1=id;
            msg.arg2=max;
            handler.sendMessage(msg);
        }
    }

    public static void Load()
    {
        getInstance();
    }

    private static IWordBase instance;
    public static IWordBase getInstance()
    {
        if(instance==null)
        {
            long beforeTime=System.currentTimeMillis();
            getWordBase();
            AppContext.getWordBaseTime = TimeHelper.getTime(beforeTime);
        }
        return instance;
    }

    private static IWordBase getWordBase()
    {
        //instance=  new WordBase();
        instance= new SQLiteWordBase();
        return instance;
    }
}
