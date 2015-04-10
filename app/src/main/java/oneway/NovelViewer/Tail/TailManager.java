package oneway.NovelViewer.Tail;

import java.util.HashMap;
import java.util.LinkedList;

import oneway.NovelViewer.Data.FileNames;
import oneway.NovelViewer.Data.MyFileIO;
import oneway.NovelViewer.IndexTree.NameInfo;
import oneway.NovelViewer.IndexTree.WINode;
import oneway.NovelViewer.IndexTree.WITree;
import oneway.NovelViewer.Interface.IWordInfo;
import oneway.NovelViewer.WordBase.WordInfo;

public class TailManager 
{
	//HashMap<String,TailTree> nodes=new HashMap<String,TailTree>();
	HashMap<String,WITree> nodes=new HashMap<String,WITree>();
	//LinkedList<TailIndex> nodes=new LinkedList<TailIndex>();
	public TailManager()
	{
		String[] lines=MyFileIO.ReadAllLines(FileNames.filePathWordTailInfo);
		WITree node=null;
		//String type2="";
    	for(String line : lines)
    	{
    		if(line.contains("("))
    		{
    			String name=line.substring(1,line.length()-1);
    			if(name.contains("("))
    				name=name.substring(1,name.length());
    			String[] parts=name.split(" ");
    			String type1=parts[0];
//    			if(parts.length>1)
//    				type2=parts[1];
    			node=GetNode(type1);
    		}
    		else
    		{
                if(node!=null) {
                    NameInfo nameInfo = new NameInfo(line, null);
                    node.Add(nameInfo);
                }
    		}
    	}
	}
	public WITree GetNode(String key)
	{
		WITree node=nodes.get(key);
		if(node==null)
		{
			node=new WITree(key);
			nodes.put(key, node);
		}
		return node;
	}
//	public void AddTrailNode(HashMap<String, WINode> nodes2, WordInfo wi) {
//		String type=wi.wordType;
//		TailTree node =nodes.get(type);
//	}
	public WINode GetNode(IWordInfo wi, String key) {
		WITree node=nodes.get(wi.getType());
		if(node==null)
			return null;
		WINode node2=node.GetNode(key);
		return node2;
	}
}
