package oneway.NovelViewer.IndexTree;

import java.util.*;
import oneway.NovelViewer.Interface.IWordInfo;
import oneway.NovelViewer.Tail.TailManager;
import oneway.NovelViewer.WordBase.WordInfo;
import oneway.NovelViewer.WordChange.*;

public class WIBase
{
    public static String nodeType="normal";
    public static int nodeCount=0;
    String nodeName;
    protected HashMap<String,WINode> nodes=null;
    protected LinkedList<IWordInfo> words=null;
    public Object parrent=null;
    boolean IsTailChecked=false;

    public static String[] changedWordType=new String[] {"一类动词","二类动词","三类动词","一类形容词","二类形容词","助动词"};

    public WIBase() {
        nodeCount++;
    }
    public WIBase(String wordName) {
        nodeName =wordName;
        nodeCount++;
    }

    public int GetWordsCount()
    {
        if(words==null)
            return 0;
        return words.size();
    }

    WINode GetNode(WordInfo wi)
    {
        //HashMap<String,WINode> node=nodes.get(location)
        String firstName=wi.GetChar(0);
        WINode node=nodes.get(firstName);
        if(node==null)
        {
            node=new WINode(firstName);
            AddNode(firstName, node);
        }
        return node;
    }

    void AddNode(String key,WINode node)
    {
        nodes.put(key, node);
        node.parrent=this;
    }

    //WINode GetNode(NameInfo ni)
    //{
        //return GetNode(ni.name,ni.id);
    //}

    WINode GetNode(NameInfo ni)//建树，建立一个节点
    {
        if(nodes==null)
            nodes=new HashMap<String,WINode>();

        String key=ni.get();
        WINode node=nodes.get(key);
        if(node==null)
        {
            node=new WINode(key);
            AddNode(key, node);
        }
        key=null;
        return node;
    }


    public WINode GetNode(String key)//切词
    {
        if(mode!=0) {
            if (nodes == null) {
                nodes = new HashMap<String, WINode>();
                addSubNodes();
            }
        }

        if(nodeType.equals("normal")&&!IsTailChecked)
        {
            AddTailNode();
        }

        if(mode==0)
        {
            if(nodes==null)
            {
                return null;
            }
        }

        WINode node=nodes.get(key);
        return node;
    }
    void AddTailNode()
    {
        if(words!=null&&words.size()!=0)//直接加在词尾
        {
            for(IWordInfo wi : words)
            {
                AddTailNode2(wi);
            }
        }
        if(nodes==null)
            return;
        LinkedList<IWordInfo> nextWords=GetNextWords();
        for(IWordInfo wi : nextWords)//替换词尾
        {
            AddTailNode1(wi);
        }
        IsTailChecked=true;
    }

    LinkedList<IWordInfo> GetNextWords()
    {
        LinkedList<IWordInfo> nextWords=new LinkedList<IWordInfo>();
        for(WINode subNode : nodes.values())
        {
            if(subNode.words!=null)
            {
                nextWords.addAll(subNode.words);
            }
        }
        return nextWords;
    }

    //boolean tailHadAdded=false;


    TailManager tt;
    void AddTailNode(String key)
    {
        if(tt==null)
            tt=new TailManager();
        //LinkedList<WINode> subNodes = (LinkedList<WINode>) nodes.values();
        for(WINode subNode : nodes.values())
        {
            for(IWordInfo wi : subNode.words)
            {
                WINode node=tt.GetNode(wi,key);
                node.nodeType="tail";
                if(node!=null)
                    AddNode(key, node);
            }
        }

        //tailHadAdded=true;
    }

    public LinkedList<IWordInfo> GetWords(String key) {
        WINode node=nodes.get(key);
        if(node!=null)
        {
            return node.words;
        }
        else
            return new LinkedList<IWordInfo>();
    }

    private void AddTailNode1(IWordInfo wi) //替换词尾
    {
        if(wi.getType().equals("一类动词"))
        {
            //String t=key.substring(id,id+1);
            String t=wi.GetTail();
            String key=wi.GetKey();
            String[] tails=Verb1.GetTails(key,t);
            addNoTails(tails,wi);
            tails=null;
        }
        else if(wi.getType().equals("二类动词"))
        {
            String[] tails=Verb2.GetTails();
            addNoTails(tails,wi);
            tails=null;
        }
        else if(wi.getType().equals("一类形容词"))
        {
            String[] tails=ADJ1.GetTails();
            addNoTails(tails,wi);
            tails=null;
        }
    }
    private void AddTailNode2(IWordInfo wi) //直接加在词尾
    {
        String wordType=wi.getType();
        if(wordType.equals("二类形容词"))
        {
            String[] tails=ADJ2.GetTails();
            addNoTails(tails,wi);
            tails=null;
        }
        else if(wordType.equals("三类动词"))
        {
            String[] tails=Verb3.GetTails();
            addNoTails(tails,wi);
            tails=null;
        }
        else if(wordType.equals("名词")||wordType.equals("代词")||wordType.equals("专有名词"))
        {
            String[] tails=new String[]{"は"};
            addNoTails(tails,wi);
            tails=null;
        }
    }

    protected void addNoTails(String[] tails,IWordInfo wi)
    {
        for(String tail : tails)
        {
            if(tail==null)return;
            NameInfo ni=new NameInfo(tail,wi);
            AddNoTail(ni);
        }
    }

    LinkedList<NameInfo> nis=new LinkedList<NameInfo>();

    public void addNameInfo(NameInfo ni)
    {
        nis.add(ni);
    }

    private void addSubNodes()
    {
        if(nis==null)return;
        while (nis.size()>0)
        {
            NameInfo ni=nis.poll();
            addSubNode(ni);
        }
        nis=null;
    }

    //建立子节点的地方
    //protected void Add(String name, int id, IWordInfo wi)
    protected void addSubNode(NameInfo ni)
    {
        int length=ni.length;
        if(2<=ni.lengthToLast())//最后
        {
            addNextNode(ni);//往后移动一位 建立一个节点 递归
        }
        else if(1==ni.lengthToLast())
        {
            addNextNode(ni);//往后移动一位 建立一个节点 递归
            AddTailNode1(ni.wi);//替换词尾
        }
        else  //相当于 ni.id==length //递归直到移动到单词最后
        {
            AddWord(ni.wi);
            AddTailNode2(ni.wi);//直接加在词尾
        }
    }

    //往后移动一位 建立一个节点 递归
    protected void addNextNode(NameInfo ni)
    {
        WINode node=GetNode(ni);//建立一个节点
        ni.next();//往后移动一位

        if(mode==0)
        {
            node.addSubNode(ni);//递归
        }
        else
        {
            node.addNameInfo(ni);
        }
    }

    public final int mode=1;//0,一次全部添加

    //public void AddNoTail(String key, int id, IWordInfo wi)
    public void AddNoTail(NameInfo ni)
    {
        if(1<=ni.lengthToLast())
        {
            WINode node=GetNode(ni);
            ni.next();
            node.AddNoTail(ni);
        }
        else
        {
            AddWord(ni.wi);
        }
    }

    //给TailManager的接口
    public void Add(NameInfo ni)
    {
        if(0<ni.lengthToLast())
        {
            WINode node=GetNode(ni);
            ni.next();
            node.Add(ni);
        }
        else
        {
            WordInfo wi=new WordInfo(ni.name);
            AddWord(wi);
        }
    }

    void AddWord(IWordInfo wi)
    {
        if(words==null)
            words=new LinkedList<IWordInfo>();
        if(!words.contains(wi))
            words.add(wi);
    }

    public Object GetParrent() {
        return parrent;
    }
}
