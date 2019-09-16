import java.lang.*;
import java.io.*;

class ReadAfile {
	public static void main(String[] args){
		try{
			String s1 = "Mytext.txt";
			File dir = new File(s1);
			String s2 = dir.getAbsolutePath();
			System.out.println(s2);
			// File myFile = new File("D:\\Java\\HeadFirstJava\\HFJ14\\card\\hellp");
			// FileReader fileReader = new FileReader(myFile);
			String s3 = dir.getAbsolutePath().substring(0,(dir.getAbsolutePath().length()-"Mytext.txt".length()-1));
			// s4 = s2.subString
			FileReader fileReader = new FileReader( s3 + "\\card\\2");
			BufferedReader reader = new BufferedReader(fileReader);

			File test = new File(s3);
			if(test.isDirectory()){
				String[] dirContents = test.list();
				for(int i= 0; i < dirContents.length; i++){
					System.out.println(dirContents[i]);
				}
			}

			String line = null;
			while((line = reader.readLine()) != null){
				System.out.println(line);
			}
			reader.close();
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
}