package oneway.NovelViewer.Tail;

import java.util.HashMap;
import java.util.LinkedList;

public class TailNodeBase 
{
	String name;
	HashMap<String,TailNode> nodes=new HashMap<String,TailNode>();
	LinkedList<String> words=new LinkedList<String>();
	public void Add(String word,int id)
	{
		if(id<word.length())
		{
			String key=word.substring(id,id+1);
			TailNode node=GetNode(key);
			node.Add(word,id+1);
		}
		else
		{
			words.add(word);
		}
	}
	public TailNode GetNode(String key)
	{
		TailNode node=nodes.get(key);
		if(node==null)
		{
			node=new TailNode(key);
			nodes.put(key, node);
		}
		return node;
	}
}
