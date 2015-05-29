import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;


public class FeedBackAnalysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> listInput = new ArrayList<String>();
		Set<String> setPos = new HashSet<String>();
		Set<String> setNeg = new HashSet<String>();
		
		try{
			listInput = readInputs(listInput,"HackathonInput.txt");
			//listInput = readInputs(listInput,"test1.txt");
			setPos = readStrings(setPos,"positive-words.txt");
			setNeg = readStrings(setNeg,"negative-words.txt");
			FileWriter fw = new FileWriter(new File("output.txt"));
			//System.out.println(listInput.size()); 
			//System.out.println(setPos.size()); 
			//System.out.println(setNeg.size());
			
			
			if(listInput!=null && listInput.size()>0){
				//ListIterator<String> lt = listInput.listIterator();
				for (ListIterator<String> iter = listInput.listIterator(); iter.hasNext(); ) {
					String s = iter.next();
					//String delims = "but"+"\\,";
					int innerNum = 0;
					String[] conjSplit = s.split("\\.");
					if(conjSplit!=null && conjSplit.length>0){
						//int innerNum = 0;
						for(int j=0;j<conjSplit.length;j++){
							//innerNum=0;
							if(conjSplit[j].contains(",")){
								
								String[] commaArr = conjSplit[j].split("\\,");
								for(int k=0;k<commaArr.length;k++){
									//innerNum=0;
									innerNum = innerNum+getMark(commaArr[k].split("\\s+"),setPos,setNeg);
									//innerNum = ( innerNum==0 ) ? 0 : ( (innerNum < 0 ) ? -1 : 1 );
									//System.out.println(commaArr[k]+ "::" + getResult(innerNum));
									//System.out.println(s+ "::" + getResult(innerNum));
								}
								
							}else{
								innerNum = innerNum + getMark(conjSplit[j].split("\\s+"),setPos,setNeg);
								//innerNum = ( innerNum==0 ) ? 0 : ( (innerNum < 0 ) ? -1 : 1 );
								//System.out.println(conjSplit[j]+"::"+innerNum);
								//System.out.println(s+ "::" + getResult(innerNum));
							}					
							
						}
						
						
					}else{					 
			            String[] str = s.split("\\s+");
			            //System.out.println();
			            innerNum = getMark(str,setPos,setNeg);
			            //innerNum = ( innerNum==0 ) ? 0 : ( (innerNum < 0 ) ? -1 : 1 );
			            //System.out.println(s+ "::" + getResult(innerNum));
					}
					
					 System.out.println(s+ ":Final -->>>>>>>>>>>:" + getResult(innerNum));
					 writeResult(fw,getResult(innerNum));
					 
		        }// for end loop
				
					fw.close();
					
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}//end of static
	
	
	
	static String getResult(int score){
		if(score==0)
			return "Neutral";
		else if(score>0)
			return "Positive";
		else
			return "Negative";
	}
	
	static int getMark(String[] str,Set<String>setPos,Set<String>setNeg){
		int mark = 0;
		for(int i=0;i<str.length;i++){
			int val = 0,temp;String key;
			 key = str[i].toLowerCase();
			if(setPos.contains(key)){
					val = val + 1;temp=1;
			}
			else if(setNeg.contains(key)){
					val = val -1;temp=-1;
			}
			else{
				val = val + 0;	temp=0;
				
			}
			
			System.out.println("key - "+ key +", val "+temp);
			mark = mark + val;
			
		}
		mark = ( mark==0 ) ? 0 : ( (mark < 0 ) ? -1 : 1 );
		
		if(notCheck(str))
			mark = ( mark==0 ) ? 0 : ( (mark < 0 ) ? 1 : -1 );
		
		return mark;
	}
	
static boolean notCheck(String[] arr){
		
		if(arr!=null&&arr.length>0){
			//System.out.println(arr.length);
			for(int i=0;i<arr.length;i++){
				//System.out.println(arr[i]+"---");
				if( ("not".equalsIgnoreCase(arr[i].toLowerCase())))		{
					//System.out.println("not is true");
					return true;
				}
						
			}
			return false;
		}else		
			return false;
	}
	
	static void writeResult(FileWriter fw,String result){

		try {
			fw.write(result);
			fw.write(System.lineSeparator()); //new line 
		} catch (IOException e) {			
			e.printStackTrace();
		}
		/*finally{
			try {
				//fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/

	}
	
	static Set<String> readStrings(Set<String> set,String filename){
		try {
			//System.out.println(System.getenv());
			//FileReader fr = new FileReader("C:\\HackathonWS\\myText\\src\\inputsample.txt"); 
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr); 
			String s; 
			while((s = br.readLine()) != null) { 
				set.add(s);
			} 
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return set;
	}
	
	static List<String> readInputs(List<String> list,String filename){
		try {
			//System.out.println(System.getenv());
			//FileReader fr = new FileReader("C:\\HackathonWS\\myText\\src\\inputsample.txt"); 
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr); 
			String s; 
			while((s = br.readLine()) != null) { 
				list.add(s);
			} 
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}


