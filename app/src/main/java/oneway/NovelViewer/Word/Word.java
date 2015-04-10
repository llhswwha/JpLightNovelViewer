package oneway.NovelViewer.Word;

import oneway.NovelViewer.Data.FileNames;
import oneway.NovelViewer.Data.MyFileIO;
import oneway.NovelViewer.WordBase.WordInfo;

public class Word {

	public String type,path;
	public String name,content;
	String path2;
	public Word(String wordType, String path) 
	{
		this.type=wordType;
		this.path=path;
	}
	public Word(String wordType, String wordName,String wordContent) 
	{
		type=wordType;
		name=wordName;
		content=wordContent;
		//path=type+"/"+name;
		try
		{
		path=FileNames.dirPathWordBase+type+"//"+name+".txt";
		String dir=FileNames.dirPathNewWords+type+"//";
		MyFileIO.CreateDirPath(dir);
		path2=dir+name+".txt";
		}
		catch(Exception ex)
		{
			String message=ex.getMessage();
			String b=message;
		}
	}
//	public static Word Create(String wordType, String path) {
//		// TODO Auto-generated method stub
//		
//		
//		return null;
//	}
	public WordInfo GetInfo() {
		// TODO Auto-generated method stub
		WordInfo wi=new WordInfo(type,name,path);
		return wi;
	}
	public void Save() {
		// TODO Auto-generated method stub
		MyFileIO.WriteAllText(path, content);
		MyFileIO.WriteAllText(path2, content);
	}

}
