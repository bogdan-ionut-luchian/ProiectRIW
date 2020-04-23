package laborator1;
//import java.io.File;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element; 
import org.jsoup.select.Elements;
//import java.io.IOException;


import java.awt.List;
import java.awt.RenderingHints.Key;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.net.UnknownHostException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class lab1 {

	public static void main(String[] args) throws IOException {
		
		File input=new File("D:/Facultate/AN 4 SEM 2/emag.html");
		Document doc = Jsoup.parse(input,"utf-8");
		
		String title = doc.title();
		System.out.println("Title: "+title);
		
		String keywords = doc.select("meta[name=keywords]").first().attr("content");  
        System.out.println("Primul Meta keyword : " + keywords);  
        
        String description = doc.select("meta[name=description]").first().attr("content");  
        System.out.println("Primul Meta description : " + description);  
        
        String robot = doc.select("meta[name=robots]").first().attr("content");
        System.out.println("Primul Robots: "+robot);
        
        Element link = doc.select("a[href^=https:]").first();
        System.out.println("Primul link: "+link.attr("href"));
        
        //Elements links=doc.select("a[href]");
        /*for(Element l:links)
        {
        	System.out.println("Link: "+l.attr("abs:href"));
        	//System.out.println("text: "+l.text());
        }*/
        
        
        
       System.out.println("Text:");
       String text = doc.body().text();
       System.out.println(text);
       
       //laborator2 - 1
       Queue<File> fisiere=new LinkedList<File>();
       File p = new File("D:\\\\Facultate\\\\AN 4 SEM 2\\\\laborator1\\\\WD");
       fisiere=getFile(p);
       //File file = new File("D:\\\\Facultate\\\\AN 4 SEM 2\\\\laborator1\\\\Dir1");
      // File file=new File("D:\\\\Facultate\\\\AN 4 SEM 2\\\\laborator1\\\\src\\\\laborator1\\\\text1.txt");
       //TextSplit(file);
       
       //lab2 - 2
       //System.out.println("Problema 2:");
		
		//TextP(file);
       	System.out.println();
		System.out.println("Laborator2:");
		//printFiles();
		//TextSplit(file);
		for(File f : fisiere)
			
		{
			System.out.println(f);
			TextSplit(f);

		}
		
		//lab 3
		System.out.println();
		System.out.println("Laborator 3:");
		MapareInversa(p);
		
		//lab 4
		
		System.out.println("Laborator 4:");
		
		System.out.println("introduceti primul cuvant:");
		Scanner sc1=new Scanner(System.in);
		String cuv1=sc1.nextLine();
		
		System.out.println("introduceti al doilea cuvant:");
		Scanner sc2=new Scanner(System.in);
		String cuv2=sc2.nextLine();
		
		if((VerificareString(cuv1)&&VerificareString(cuv2))==true)
		{
			System.out.println("Cuvintele cautate se afla in fisier");
		}
		else
		{
			System.out.println("Cuvantul cautat nu se afla in fisier");
		}
		
		System.out.println("Reuniune:");
		LinkedList<String>Lista=new LinkedList<>();
		Lista=ORoperator(cuv1, cuv2);
		System.out.println(Lista);
		
		
		System.out.println("Intersectie:");
		LinkedList<String>Lista2=new LinkedList<>();
		Lista2=ANDoperator(cuv1, cuv2);
		System.out.println(Lista2);
				
		System.out.println("NOT:");	
		LinkedList<String> Lista3 = new LinkedList<String>();
		Lista3=NOTOperator(cuv1,cuv2);
		System.out.println(Lista3);
	}

	//Laborator 1 Ex 2
	   
    public static Scanner in;
    public static Map<String, Integer> map = new HashMap<String,Integer>();
    Set<String> keySet=map.keySet();
   
    public static Map<String, Integer> TextSplitLab1(File file)
    {
        try {
            in=new Scanner(file);
            while(in.hasNextLine())
            {
                String line=in.nextLine();
                String str=line.toLowerCase();
                StringBuilder parts=new StringBuilder();
                for(int i=0;i<str.length();++i)
                {
                    if(Character.isLetter(str.charAt(i)))
                    {
                        parts.append(str.charAt(i));
                    }
                    else
                    {
                        if(parts.length()>2)
                        {
                            if(!map.containsKey(parts.toString())) {
                                map.put(parts.toString(), 1);
                            }
                            else
                            {
                                map.put(parts.toString(), map.get(parts.toString())+1);
                            }
                           
                        }
                        parts.setLength(0);
                    }
                }
            }
            System.out.println(map);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
       
        return map;
    }
	
	//Laborator 2
	public static boolean CheckIfText(File file)
	{
		boolean exist = false;
		if(file == null)
		{
			return false;
		}
		String nume = file.getName();
		int i = nume.lastIndexOf('.');
		String ext = i>0?nume.substring(i+1) : " ";
		if(ext.equals("txt"))
		{
			exist = true;
		}
		else
		{
			exist = false;
		}
		return exist;
	}
	
	public static boolean CheckIfException(String words)
	{
		String[] exceptii= {"it", "been", "but","are","below" , "have"};
		for(String i:exceptii)
		{
			if(words.equals(i))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static Queue<File> getFile(File path)
	{
		Queue<File> fisiere =  new LinkedList<File>();
		Queue<File> directoare = new LinkedList<>();
		directoare.add(path);
		
		while(!directoare.isEmpty())
		{
			File current = directoare.poll();
			File[] DirList = current.listFiles();
			
			if(DirList != null)
			{
				for(File file : DirList)
				{
					if(file.isDirectory())
					{
						directoare.add(file);
						 //System.out.println("director");
					}
					else if(CheckIfText(file))
					{
						fisiere.add(file);
						//System.out.println("fisier");
					}
				}
			}
			
		}
		return fisiere;
			
	}
	
	public static boolean StopWords(String words)
	{
		ArrayList<String> lista = new ArrayList<String>();
		try{
			FileReader intrare = new FileReader("stopwords.txt");
			BufferedReader br = new BufferedReader(intrare);
			String linie = br.readLine();
			while(linie!=null)
			{
				lista.add(linie);
				linie = br.readLine();
			}
			intrare.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	public static Map<String, Integer> TextSplit(File filePath) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		FileInputStream inputStream = null;
		Porter porter=new Porter();
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(filePath);
			sc = new Scanner(inputStream, "UTF-8");
			StringBuilder word = new StringBuilder();

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				for (int i = 0; i < line.length(); ++i) {
					if (Character.isLetter(line.charAt(i))) {
						word.append(line.charAt(i));
						

					} else {
						if (((word.length() > 2))&&(StopWords(word.toString()))) {
							if (!map.containsKey(porter.stripAffixes(word.toString()))) {
								map.put(porter.stripAffixes(word.toString()), 1);
							} else {
								map.put(porter.stripAffixes(word.toString()), map.get(porter.stripAffixes(word.toString())) + 1);
							}
						}
						word.setLength(0);
					}
					
					if (i == (line.length() - 1) && word.length() > 0) {
						
						if (!map.containsKey(word.toString())) {
							map.put(porter.stripAffixes(word.toString()), 1);
						} else {
							map.put(porter.stripAffixes(word.toString()), map.get(porter.stripAffixes(word.toString())) + 1);
						}
						word.setLength(0);
					}
				}
			}
			System.out.println(map);
			inputStream.close();
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	//Laborator 3
	public static Map<String, Map<String,Integer>> inverseMap = new HashMap<>();
	public static Map<String, Map<String,Integer>> directMap = new HashMap<>();
	public static MongoSetup mongoClient=new MongoSetup();
	public static void MapareInversa(File folder) throws IOException
	{
		

		 HashSet<String>hset=new HashSet<String>();
		 Queue<File> fisiere=new LinkedList<File>();
		 FileWriter out;
		 FileWriter allFileMap=new FileWriter("allFileMap.txt");
		 FileWriter indexInvers=new FileWriter("inversIndex.txt");
		 fisiere=getFile(folder);
		 
		 mongoClient.setDatabaseName("RIW");	 
		// MongoClient mongoClient = new MongoClient();
		// DB database = mongoClient.getDB("RIW");
		 DBCollection collection1 = mongoClient.getCollection("directIndex");
		 collection1.drop();
	
		 for (File f : fisiere)
		 {
			 String dir = f.getParent();
			 File path = new File(dir + "\\index.html"); 
			 if(hset.contains(dir) == true)
			 {
				 out = new FileWriter(path, true);
			 }
			 else
			 {
				 out = new FileWriter(path);
				 hset.add(dir);
			 }
			 
			 HashMap<String, Integer>print = (HashMap<String, Integer>)TextSplit(f);//TextSplit cu porter sau fara
			 directMap.put(f.getAbsolutePath(), print);
			 
			 
			//MONGODB directIndex						
			java.util.List<BasicDBObject> temps = new ArrayList<>();
			for(Entry<String, Integer> entry:print.entrySet()) {					  
				temps.add(new BasicDBObject("t",entry.getKey()).append("c", entry.getValue()));
			}
			DBObject directIndex = new BasicDBObject("docs",f.getAbsolutePath()).append("temps",temps);
            collection1.insert(directIndex);
			 
			 			 			 			 
			 out.write(directMap.toString());
			 out.write(System.lineSeparator());
			 allFileMap.write(f.getAbsolutePath()+",");
			 allFileMap.write(path.getAbsolutePath());
			 allFileMap.write(System.lineSeparator());
			 out.close();
			 
			 
			 //inverseMap
			 for(String key1 : directMap.keySet())
			 {
				 for(String key2 : directMap.get(key1).keySet())
				 {
					 int c = directMap.get(key1).get(key2);
					 if(inverseMap.containsKey(key2))
					 {
						 inverseMap.get(key2).put(key1, c);
					 }
					 else
					 {
						 HashMap<String, Integer> a = new HashMap<>();
						 a.put(key1, c);
						 inverseMap.put(key2, a);
					
					 }
				 }
			 }
			 directMap.clear();
		 }
		 
		//java.util.List<BasicDBObject> docs = new ArrayList<>();
		 
		 for(Entry<String, Map<String, Integer>> entry:inverseMap.entrySet()) 
		 {				
				indexInvers.write((entry.getKey()+" = "+entry.getValue()));
				indexInvers.write(System.lineSeparator());	
		 }
		 
		 //mongo inversIndex
		 DBCollection collection = mongoClient.getCollection("inversIndex");			
		 collection.drop();
		 for(Entry<String, Map<String, Integer>> entry:inverseMap.entrySet()) {
			//Invers_Index(entry.getValue(),entry.getKey());
			java.util.List<BasicDBObject> inversIndex = new ArrayList<BasicDBObject>();
			for(Entry<String, Integer> entry1 : entry.getValue().entrySet())
			{
				inversIndex.add(new BasicDBObject("d",entry1.getKey()).append("c", entry1.getValue()));
			}
			
			DBObject invers = new BasicDBObject("term",entry.getKey()).append("docs", inversIndex);
			collection.insert(invers);
		}
		 
		 indexInvers.close();
		 allFileMap.close();
	}
	
	//Laborator 4
	public static boolean VerificareString(String values)
	{
		for(Entry<String, Map<String, Integer>> entry : inverseMap.entrySet())
		{
			if(values.equals(entry.getKey()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static LinkedList<String> ORoperator(String cuv1, String cuv2)
	{
		LinkedList<String> list1= new LinkedList<>();
		
		
		if((cuv1 !=null)&&(cuv2!=null))
		{
			if(VerificareString(cuv1)&&VerificareString(cuv2))
			{
				for(String key1 : inverseMap.keySet())
				{
					for(String key2 : inverseMap.keySet())
					{
						if(key1.equals(cuv1) && key2.equals(cuv2))
						{
							System.out.println(inverseMap.get(key1).keySet());
                            System.out.println(inverseMap.get(key2).keySet());
                            System.out.println("Rezultatul reuniunii: ");
                            
                            for(String key3 : inverseMap.get(key1).keySet())
                            {
                            	                           
                            	for(String key4 : inverseMap.get(key2).keySet())
                            	{    
                            		list1.add(key3.toString());
                            		list1.add(key4.toString());
                            		/*if(key3 == key4)
                            		{
                            			list1.add(key4.toString());
                            		}
                            		else if(key3!=key4)
                            		{
                            			list1.add(key3.toString());
                            		}
                            		else if(key4!=key3)
                            		{
                            			list1.add(key4.toString());
                            		}
   		*/
                            	}  
                            }
                             							
						}
					}
				}																			
			}
		}
				
		return list1;
	}
	
	/*public static LinkedList<String> ORoperator1(String cuv1, String cuv2)
	{
		LinkedList<String> list1= new LinkedList<>();
		
		
		if((cuv1 !=null)&&(cuv2!=null))
		{
			if(VerificareString(cuv1)&&VerificareString(cuv2))
			{
				for(String key1 : inverseMap.keySet())
				{
					for(String key2 : inverseMap.keySet())
					{
						if(key1.equals(cuv1) && key2.equals(cuv2))
						{
							System.out.println(inverseMap.get(key1).keySet());
                            System.out.println(inverseMap.get(key2).keySet());
                            System.out.println("Rezultatul reuniunii: ");
                            
                            for(String key3 : inverseMap.get(key1).keySet())
                            {
                            	list1.add(key3.toString());
                            }
                            for(String key4 : inverseMap.get(key2).keySet())
                            {             
                            		list1.add(key4.toString());
                           	}                                                           							
						}
					}
				}																			
			}
		}
				
		return list1;
	}*/
	
	public static LinkedList<String> ANDoperator(String cuv1,String cuv2)
	{
		LinkedList<String> mylist=new LinkedList<>();
		if((cuv1 !=null)&&(cuv2!=null))
		{
			if(VerificareString(cuv1)&&VerificareString(cuv2))
			{	
					for(String key1 : inverseMap.keySet())
					{
						for(String key2 : inverseMap.keySet())
						{			
							if(key1.equals(cuv1)&&key2.equals(cuv2))
							{		
								System.out.println(inverseMap.get(key1).keySet());
								System.out.println(inverseMap.get(key2).keySet());
								System.out.println("Rezultatul intersectiei: ");
								
								for(String key3 : inverseMap.get(key1).keySet())
								{
									for(String key4 : inverseMap.get(key2).keySet())
									{
										if(key3==key4)
											//System.out.println(key3);
											mylist.add(key3.toString());
									}	
								}	
							}
						}
					}
				}
		}
		return mylist;	
		
	}
		
	
	public static LinkedList<String> NOTOperator(String cuv1,String cuv2)
	{
		LinkedList<String> mylist=new LinkedList<>();
		if((cuv1 !=null)&&(cuv2!=null))
		{
			if(VerificareString(cuv1)&&VerificareString(cuv2))
			{	
					for(String key1 : inverseMap.keySet())
					{
						for(String key2 : inverseMap.keySet())
						{			
							if(key1.equals(cuv1)&&key2.equals(cuv2))
							{		
								System.out.println(inverseMap.get(key1).keySet());
								System.out.println(inverseMap.get(key2).keySet());
								System.out.println("Rezultatul operatorului not: ");
								
								for(String key3 : inverseMap.get(key1).keySet())
								{
									for(String key4 : inverseMap.get(key2).keySet())
									{
										if(key3!=key4)
											//System.out.println(key3);
											mylist.add(key3.toString());
									}	
								}	
							}
						}
					}
				}
		}
		return mylist;	
	}
	
	public static boolean verifyIfTermenExists(ArrayList<String>documentsList,String values)
	{
		for(int i=0;i<documentsList.size();i++)
		{
			if(documentsList.get(i).equals(values))
			{
				return true;
			}
		}
		return false;
	}
	
}
