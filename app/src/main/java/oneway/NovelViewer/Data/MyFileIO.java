package oneway.NovelViewer.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.http.util.EncodingUtils;

public class MyFileIO {
//	public static String ReadAllText(String fileName)
//	{
//		File file=new File(fileName);
//		String fileContent="";
//		if(file.exists())
//		{
//			try
//			{
//				FileReader fr=new FileReader(file);
//				char[] buffer=new char[(int)file.length()];
//				fr.read(buffer);
//				
//				for(char c:buffer)
//				{
//					fileContent+=c;
//				}
//			}
//			catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		else
//		{
//			WriteAllText(fileName,fileContent);
//		}
//		return fileContent;
//	}

    public static String ReadAllText(String fileName)
    {
        String fileContent="";
        try{
            FileInputStream fin = new FileInputStream(fileName);
            //FileInputStream fin = openFileInput(fileName);
            //用这个就不行了，必须用FileInputStream
            int length = fin.available();
            byte [] buffer = new byte[length];
            fin.read(buffer);
            fileContent = EncodingUtils.getString(buffer, "UTF-8");
            fin.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return fileContent;
    }

    public static String[] ReadAllLines(String fileName)
    {

        String fileContent=ReadAllText(fileName);
        fileContent=fileContent.trim();
        return fileContent.split("\n");
    }
    public static void CreateDirPath(String fileName)
    {
        File rootFolder=new File(fileName);
        boolean r=rootFolder.mkdirs();
    }
    public static void CreateDirPath(File dir)
    {
        if(dir.exists())
            return;
        dir.mkdirs();
    }
    public static void WriteAllText(String fileName,String fileContent)
    {
        File file=new File(fileName);
        if(file.exists())file.delete();

        CreateDirPath(file.getParentFile());
        try {

            file.createNewFile();
            FileWriter fw=new FileWriter(file);
            fw.write(fileContent);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void WriteAllText(String fileName,String[] fileLines)
    {
        String fileContent="";
        for(String line : fileLines)
            fileContent+=line+"\n";
        WriteAllText(fileName,fileContent);
    }
    public static void WriteAllText(String fileName,LinkedList<String> fileLines)
    {
        String fileContent="";
        for(String line : fileLines)
            fileContent+=line+"\n";
        WriteAllText(fileName,fileContent);
    }
    public static LinkedList<File> GetSubDirs(String rootDirName)
    {
        LinkedList<File> subDirs=new LinkedList<File>();
        try
        {

            File rootFolder=new File(rootDirName);
            if(rootFolder.isDirectory())
            {
                File[] subFiles=rootFolder.listFiles();//listFiles
                for(File file : subFiles)
                {
                    if(file.isDirectory())
                    {
                        subDirs.add(file);
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error： " + e);
        }
        return subDirs;
    }
    public static LinkedList<File> GetSubDirs(File rootFolder)
    {
        LinkedList<File> subDirs=new LinkedList<File>();
        try
        {
            if(rootFolder.isDirectory())
            {
                File[] subFiles=rootFolder.listFiles();//listFiles
                for(File file : subFiles)
                {
                    if(file.isDirectory())
                    {
                        subDirs.add(file);
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error： " + e);
        }
        return subDirs;
    }
    public static LinkedList<File> GetSubFiles(File rootFolder)
    {
        LinkedList<File> subDirs=new LinkedList<File>();
        try
        {
            if(rootFolder.isDirectory())
            {
                File[] subFiles=rootFolder.listFiles();//listFiles
                for(File file : subFiles)
                {
                    if(file.isFile())//就是这里的判断不一样了，就从子目录变为子文件了
                    {
                        subDirs.add(file);
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error： " + e);
        }
        return subDirs;
    }

    public static LinkedList<File> GetSubFiles(String rootDirName)
    {
        LinkedList<File> subDirs=new LinkedList<File>();
        try
        {

            File rootFolder=new File(rootDirName);
            if(rootFolder.isDirectory())
            {
                File[] subFiles=rootFolder.listFiles();//listFiles
                for(File file : subFiles)
                {
                    if(file.isFile())
                    {
                        subDirs.add(file);
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error： " + e);
        }
        return subDirs;
    }

    static void getDir(String strPath) throws Exception//参考用
    {
        try
        {
            File f=new File(strPath);
            if(f.isDirectory())
            {
                File[] fList=f.listFiles();//listFiles
                for(int j=0;j<fList.length;j++)
                {
                    if(fList[j].isDirectory())
                    {
                        System.out.println(fList[j].getPath());
                        getDir(fList[j].getPath()); //在getDir函数里面又调用了getDir函数本身
                    }
                }

                for(int j=0;j<fList.length;j++)
                {
                    if(fList[j].isFile())
                    {
                        System.out.println(fList[j].getPath());
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error： " + e);
        }
    }


}
