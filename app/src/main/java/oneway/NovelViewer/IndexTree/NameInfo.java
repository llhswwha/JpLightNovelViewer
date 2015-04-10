package oneway.NovelViewer.IndexTree;

import oneway.NovelViewer.Interface.IWordInfo;

//切词的最基本单位，单词一个名称
public class NameInfo {
    int length;
    String name;
    private int id;//当前位置
    IWordInfo wi;//所属单词
    public NameInfo(String n,IWordInfo w)
    {
        name=n;
        length=name.length();
        wi=w;
    }

    public void next()
    {
        id++;
    }

    public String toString()
    {
        return name+"("+id+")";
    }
    //获取当前id上的字符
    public String get()
    {
        if(name==null||name.length()==0)
        {
            return null;
        }
       return name.substring(id,id+1);
    }

    //距离最后还有多少距离
    public int lengthToLast()
    {
        return length-id;
    }
}
