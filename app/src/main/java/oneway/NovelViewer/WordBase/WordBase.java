package oneway.NovelViewer.WordBase;

import java.util.LinkedList;
import oneway.NovelViewer.BaseClasses.BaseWordBase;
import oneway.NovelViewer.Data.*;
import oneway.NovelViewer.IndexTree.*;
import oneway.NovelViewer.Interface.*;
import oneway.NovelViewer.Word.Word;

public class WordBase extends BaseWordBase {
    /*public static String[] wordTypes = new String[] { "一类动词", "二类动词", "三类动词", "一类形容词", "二类形容词", "副词", "名词", "数量词", "代词", "叹词", "连词",
            "专有名词", "外来语", "助词", "格助词", "系助词", "修助词", "副助词", "接续助词", "终助词", "复合助词", "助动词", "接头词", "连体词", "叹词", "接", "语法", "词组", "造词"
            ,"自定义"};*/
    public String root;
    public WordBase()
    {
        new FileNames();
        root=FileNames.dirPathWordBase;
        //WITree.LoadTree();
        LoadWords();
    }
    //读取词库文件夹中的所有单词
    //public LinkedList<WordCollection> wcs = new LinkedList<WordCollection>();
    public int GetWordCount()
    {
        return allWords.size();
    }

    public static String current;

    public void LoadWords()//通过读取词库信息文件得到所有单词
    {
        wordTypes=new String[100];
        int typeId=0;

        String[] lines=MyFileIO.ReadAllLines(FileNames.filePathWordBaseInfo);
        WordCollection wc=null;
        for(int j=0;j<lines.length;j++)
        {
            String line=lines[j];
            current=line;

            WordBaseHelper.reportProgress(j,lines.length);
            if(line.contains("("))
            {
                if(wc!=null)
                {
                    this.add(wc);
                    wordTypes[typeId++]=wc.getWordType();
                    //allWords.addAll(wc.getWords());
                    addWords(wc.getWords());
                }
                wc=new WordCollection(line);
            }
            else
            {
                wc.Add(line);
            }
        }
        addWords(wc.getWords());
        //allWords.addAll(wc.getWords());
    }

    public void AddWord(Word word)
    {
        IWordInfo wi=word.GetInfo();
        allWords.add(wi);
        IWordCollection wc=get(word.type);
        wc.add(wi);
        word.Save();
        if(wiTree!=null)
            wiTree.AddWordInfo(wi);
    }

    public boolean saveWords()
    {
        String text="";
        for(IWordCollection wc : this)
        {
            text+=wc+"\n";
        }
        text=text.trim();
        MyFileIO.WriteAllText(FileNames.filePathWordBaseInfo, text);
        return true;
    }

    public String GetTypeName(int id) {
        IWordCollection wc=get(id);
        return wc.getWordType();
    }
}
