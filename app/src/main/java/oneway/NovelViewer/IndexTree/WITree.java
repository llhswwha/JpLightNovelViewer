package oneway.NovelViewer.IndexTree;

import android.util.Log;

import java.util.LinkedList;

import oneway.NovelViewer.Helpers.AppContext;
import oneway.NovelViewer.Helpers.TimeHelper;
import oneway.NovelViewer.Interface.IWordInfo;
import oneway.NovelViewer.Tail.TailManager;
import oneway.NovelViewer.WordChange.HelpingVerb;
import oneway.NovelViewer.WordChange.Verb3;
import oneway.NovelViewer.WordInfos.WordHeadManager;

public class WITree extends WIBase
{
    LinkedList<IWordInfo> allWords;
    public WITree(String name)
    {
        super(name);
    }

    public WITree(LinkedList<IWordInfo> allWords)
    {
        if(tt==null)
            tt=new TailManager();
        this.allWords=allWords;
        //LoadWords();
    }

    /*
    //一次全部导入所有单词
    private void LoadWords()
    {
        LoadWords(allWords);
    }*/

    /*public void LoadWords(LinkedList<IWordInfo> words)
    {
        for(IWordInfo wi : words)
        {
            AddWordInfo(wi);//如果存在两个空格（及以上）会出错
        }
    }*/

    public void LoadWords(LinkedList<NameInfo> nameInfos)
    {
        for(NameInfo nameInfo : nameInfos)
        {
            loadNameInfo(nameInfo);//如果存在两个空格（及以上）会出错
        }
    }

    public void loadNameInfo(NameInfo nameInfo) {
        try {
            addRootNode(nameInfo);
        }
        catch (Exception ex)
        {
            Log.e("addRootNode",ex.getMessage());
        }
    }

    public void AddWordInfo(IWordInfo wi) {
        try {
            if(wi.getName().equals("谷口 たにぐち"))
            {
                Log.e("AddWordInfo debug","谷口 たにぐち");
            }
            if(wi==null)return;
            if (AddSpecialWord1(wi)) return;
            String[] names = wi.getNames();
            for (String name : names) {
                if(name==null || name.trim().length()==0) continue;//如果存在两个空格（及以上）会出错
                if (AddSpecialWord2(wi)) continue;
                NameInfo nameInfo = new NameInfo(name, wi);
                loadNameInfo(nameInfo);
            }
        }
        catch (Exception ex)
        {
            Log.e("AddWordInfo",ex.getMessage());
        }
    }

    //这里是建树的入口
    protected void addRootNode(NameInfo ni)//这里id==0
    {
        WINode node = GetNode(ni);
        /*if(ni.length==1)//只有
            AddWord(ni.wi);
        ni.next();
        node.addSubNode(ni);//建立子节点*/
        ni.next();

        if(mode==0)
        {
            node.addSubNode(ni);//此时的 ni.length==1 就相当于addSubNode中的ni.id==length
        }
        else
        {
            node.addNameInfo(ni);
        }
    }

    //添加特殊词:助动词です
    private boolean AddSpecialWord1(IWordInfo wi)  {
        if (wi.getType().equals("助动词") && wi.getName().equals("です")) {
            String[] tails = HelpingVerb.GetTails();
            addNoTails(tails,wi);
            return true;
        }
        return false;
    }

    //添加特殊词:三类动词する
    private boolean AddSpecialWord2(IWordInfo wi)
    {
        if(IsSpecialWord2(wi))
        {
            String name=wi.getName();
            String head=name.substring(0, name.length()-2);
            String[] tails=Verb3.GetTails();
            addNoTails(tails,wi);
            return true;
        }
        return false;
    }

    //三类动词中的部分特殊词汇
    public boolean IsSpecialWord2(IWordInfo wi) {
        if(wi.getType().equals("三类动词") && wi.getName().contains("する"))
        {
            String[] names=  wi.getNames();
            return names[0].length()<=3;
        }
        return false;
    }

    static TailManager tt;

    private LinkedList<String> addedWords=new LinkedList<String>();

    /*
      动态添加当前句子中的词汇进入切词树
     */
    private void dynamicLoadWords(char[] cs)
    {
        for (int i=0;i<cs.length;i++)
        {
            String c=cs[i]+"";
            if(!addedWords.contains(c))
            {
                addedWords.add(c);
                LinkedList<NameInfo> words= WordHeadManager.GetWords(c);
                LoadWords(words);
            }
        }
    }

    public LinkedList<DivideWord> divideSentence(String sentence)
    {
        char[] cs=sentence.toCharArray();

        //long beforeTime=System.currentTimeMillis();
        //dynamicLoadWords(cs);
        //AppContext.dynamicLoadWordsTime= TimeHelper.getTime(beforeTime);

        long beforeTime2=System.currentTimeMillis();
        LinkedList<DivideWord> result=divideSentence(sentence,cs);
        AppContext.divideSentenceTime= TimeHelper.getTime(beforeTime2);

        return result;
    }

    private LinkedList<DivideWord> divideSentence(String sentence,char[] cs)
    {
        DivideResult result=new DivideResult();
        int length=sentence.length();
        for(int id1=0;id1<length;id1++)
        {
            Character c=cs[id1];
            String key=c.toString();

            if("、。！".contains(key))//如果是标点符号自己返回
            {
                result.add(key);
                continue;
            }

            if(!addedWords.contains(key))
            {
                addedWords.add(key);
                LinkedList<NameInfo> words= WordHeadManager.GetWords(key);
                LoadWords(words);//第一次添加单词到树中
            }

            if(nodes==null)
            {
                result.add(key);
                continue;
            }

            WINode node=nodes.get(key);

            if(key.equals("囲"))
            {
                Log.e("debug","debug");
            }

            if(node==null)
            {
                result.add(key);
                continue;
            }
            int id2=id1+1;
            for(;id2<length;id2++)
            {
                Character c2=cs[id2];
                String key2=c2.toString();
                 WINode node2=node.GetNode(key2);
                if(node2==null)
                {
                    if(id2==id1+1)
                    {
                        String w=sentence.substring(id1,id2);
                        DivideWord dw=new DivideWord(w,node.words);
                        result.add(dw);
                        //isBreak=true;
                        break;
                    }
                    else
                    {
                        if(node.GetWordsCount()!=0)
                        {
                            String w=sentence.substring(id1,id2);
                            DivideWord dw=new DivideWord(w,node.words);
                            result.add(dw);
                            id1=id2-1;
                        }
                        else
                        {
                            while(node.GetWordsCount()==0)
                            {
                                id2--;
                                if(id2==id1+1)
                                    break;
                                Object parrent=node.GetParrent();
                                if(parrent==null)
                                    break;
                                else
                                    node=(WINode)parrent;
                            }

                            if(id2==id1+1)
                            {

                                String w=sentence.substring(id1,id1+1);
                                DivideWord dw=new DivideWord(w,node.words);
                                result.add(dw);
                            }
                            else
                            {
                                String w=sentence.substring(id1,id2);
                                DivideWord dw=new DivideWord(w,node.words);
                                result.add(dw);
                                id1=id2-1;
                            }
                        }
                        //isBreak=true;
                        break;
                    }
                }
                else
                {
                    node=node2;
                }
            }

            if(id2==length)
            {
                String w=sentence.substring(id1,id2);
                DivideWord dw=new DivideWord(w,node.words);
                result.add(dw);
                id1=id2;
            }
        }
        return result;
    }
}
