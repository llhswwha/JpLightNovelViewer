package oneway.NovelViewer.SQLite;

import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;

import oneway.NovelViewer.BaseClasses.BaseWordBase;
import oneway.NovelViewer.Data.FileNames;
import oneway.NovelViewer.Helpers.AppContext;
import oneway.NovelViewer.Helpers.TimeHelper;
import oneway.NovelViewer.Interface.IWordCollection;
import oneway.NovelViewer.Interface.IWordInfo;
import oneway.NovelViewer.Helpers.SQLiteHelper;
import oneway.NovelViewer.WordBase.WordBaseHelper;

/**
 * Created by Administrator on 2014/7/20.
 */
public class SQLiteWordBase extends BaseWordBase {
    private String TAG="SQLiteWordBase";
    private SQLiteDatabase db;
    public SQLiteWordBase()
    {
        new FileNames();
        String path=FileNames.dirDb+"Jp.ldx";
        db=SQLiteDatabase.openOrCreateDatabase(path,null);
        loadWordTypes();
        loadWordCollections();
    }

    private void loadWordTypes()
    {
        wordTypes=SQLiteHelper.getTableNames(db);
        wordTypes[0]=null;
        wordTypes[wordTypes.length-1]=null;
        //wordTypes.removeLast();
        //wordTypes.removeFirst();
    }

    private void loadWordCollections()
    {
        String info="";
        for(int i=0;i<wordTypes.length;i++)
        //for (String type : wordTypes)
        {
            String type=wordTypes[i];
            if(type==null)continue;

            WordBaseHelper.reportProgress(i,wordTypes.length);

            long time1=System.currentTimeMillis();

            IWordCollection wc=new SQLiteWordCollection(type);
            String[] items=SQLiteHelper.getItems(db,type,"Name","");
            for(String item : items)
            {
                IWordInfo word= new SQLiteWordInfo(item,type,db);
                wc.add(word);
                addWord(word);
            }
            this.add(wc);

            float time= TimeHelper.getTime(time1);
            if(time>0.01) {
                info += String.format("%s(%d):%.2f\n", type, items.length, time);
            }
        }

        AppContext.getWordBaseDetails=info;
    }
}
