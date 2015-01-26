package songNameEditor;

import java.io.File;

/**
 * This program was meant to delete the numbers in the beginning of the pink floyd songs i borrowed
 * @author Nando
 *
 */
public class SongNameEditor {
	
	public static void main1(String[] args) {
		File folder = new File("C:/Documents and Settings/Nando/Music/downloaded/12_01_12/Pink Floyd/");
		
		File[] albums = folder.listFiles();
		
		for(File album : albums)	{
			
			System.out.println("Album: "+album.getName());
			
			File[] songs = album.listFiles();
			
			for(File song : songs) {
				
				String s = song.getName();
				
				System.out.print("\tSong: "+s);
				
				String newName = s;
				
				//System.out.println("\t\t"+checkName(s));
				
				
				if(checkName(s)) {
					newName = editName(s);
					
					System.out.print("\tRename to: "+newName);
					
					File newf = new File("C:/Documents and Settings/Nando/Music/downloaded/12_01_12/Pink Floyd/"+newName);
					
					
					song.renameTo(newf);
					
				}
				
				System.out.println("");
			}		
			
			System.out.println("");
		}
	}
	
	public static void main2(String[] args) {
		File folder = new File("C:/Users/Nando/Workspaces/Java/Workspace/Small Projects/");
		
		File[] songs = folder.listFiles();
		
		for(File song : songs) {
			if(song.getName().substring(song.getName().indexOf(".")+1).equals("mp3")) {
				String alb ="";
				alb = makeAlbum(song.getAbsolutePath());
				
				File newf = new File("C:/Documents and Settings/Nando/Music/downloaded/12_01_12/Pink Floyd/"+alb+"/"+song.getName());
				
				song.renameTo(newf);
			}
		}
			
	}
	
	private static String makeAlbum(String s) {
		
		String temp = s.substring(s.indexOf("\\")+1);
		
		//System.err.println(temp);
		
		while(temp.indexOf("\\") >= 0) {
			if(temp.substring(temp.indexOf("\\")+1).indexOf("\\") >= 0)
				temp = temp.substring(temp.indexOf("\\")+1);	
			
			//System.err.println(temp);
		}
		
		String alb = temp.substring(0, temp.indexOf("\\")-1);
		File ab = new File("C:/Documents and Settings/Nando/Music/downloaded/12_01_12/Pink Floyd/"+alb);
		if(!ab.exists())
			ab.mkdir();
		
		return alb;
		
		
	}
	
	private static boolean checkName(String s) {
		
		return (s.charAt(0) < 'a' || s.charAt(0) > 'z') && (s.charAt(0) < 'A' || s.charAt(0) > 'Z');
	}
	
	
	private static String editName(String s) {
		int nameStart = -1;
		for(int x = 0; x < s.length(); x++)
			if(((s.charAt(x) >= 'a' && s.charAt(x) <= 'z') || (s.charAt(x) >= 'A' && s.charAt(x) <= 'Z')) && nameStart == -1)
				nameStart = x;
		
		return s.substring(nameStart);
	}
}
