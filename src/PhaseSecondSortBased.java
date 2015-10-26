import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class PhaseSecondSortBased {

	public void performTwoWay(int no_of_sublists_R,int no_of_sublists_S, int tupleSize1, int startIndex1, int endIndex1,
			int tupleSize2, int startIndex2, int endIndex2) throws IOException {
		BufferedReader[] br_R = new BufferedReader[no_of_sublists_R];
		BufferedReader[] br_S = new BufferedReader[no_of_sublists_S];

		//getting buffered reader for all sublists of R
		for (int i = 0; i < br_R.length; i++) {
			DataInputStream in = new DataInputStream(new FileInputStream("data_R_"
					+ (i + 1) + ".txt"));
			br_R[i] = new BufferedReader(new InputStreamReader(in));
		}

		//getting buffered reader for all sublists of S
		for (int i = 0; i < br_S.length; i++) {
			DataInputStream in = new DataInputStream(new FileInputStream("data_S_"
					+ (i + 1) + ".txt"));
			br_S[i] = new BufferedReader(new InputStreamReader(in));
		}
		//to store blocks of sublists of R
		ArrayList<String> strR = new ArrayList<String>();
		//to store blocks of sublists of S
		ArrayList<String> strS = new ArrayList<String>();
		
		PhaseSecondSortBased two = new PhaseSecondSortBased();
		strR = two.putIntoArray(strR, br_R, no_of_sublists_R,tupleSize1, startIndex1, endIndex1);
		strS = two.putIntoArray(strS, br_S, no_of_sublists_S,tupleSize2, startIndex2, endIndex2);
		
		int counterR = 0, counterS = 0;
		boolean shouldContinue = true;
		
		PrintWriter print = new PrintWriter("output_sortbased.txt", "UTF-8");;
		
		while(shouldContinue){ // for R
			
			int joinValueOfR = Integer.parseInt(strR.get(counterR).substring(startIndex1, endIndex1));
			int joinValueOfS = Integer.parseInt(strS.get(counterS).substring(startIndex2, endIndex2));
			
			while(joinValueOfR >= joinValueOfS){
				if (joinValueOfR == joinValueOfS){
					print.println(strR.get(counterR) +"\t"+strS.get(counterS));
				}
				counterS++;
				//if first blocks are traversed,replace them with 2nd blocks & so on
				if (counterS == strS.size()){
					counterS=0;
					strS = two.putIntoArray(strS, br_S, no_of_sublists_S,tupleSize2, startIndex2, endIndex2);
					if (strS.size() == 0){
						shouldContinue = false;
						break;
					}
					continue;
				}else{
					joinValueOfS = Integer.parseInt(strS.get(counterS).substring(startIndex2, endIndex2));					
				}
			}
			counterS = 0;
			counterR++;
			//if first blocks are traversed,replace them with 2nd blocks & so on
			if (counterR == strR.size()){
				counterR=0;
				strR = two.putIntoArray(strR, br_R, no_of_sublists_R,tupleSize1, startIndex1, endIndex1);
				if (strR.size() == 0){
					shouldContinue = false;
					break;
				}
				
				continue;
			}
		}
		
		print.close();
	}

	

	public ArrayList<String> putIntoArray(ArrayList<String> arr, BufferedReader[] br,int lengthSublist, int tupple_size, int start, int end) throws IOException{
		arr = new ArrayList<String>();
		int BLOCK_SIZE = 4096;
		int no_of_tuples_per_block = (int) Math.ceil((double)BLOCK_SIZE / tupple_size);
		for (int i = 0; i < lengthSublist; i++) {

			for (int j = 0; j < no_of_tuples_per_block; j++) {

				String strline;
				if ((strline = br[i].readLine()) != null) { //add into arraylist
					arr.add(strline);
				}else{ // if null then break
					break;
				}

			}

		}
		return SortingHelper.Sort(arr, start, end);
		
	}
}
