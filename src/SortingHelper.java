import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortingHelper {

	public void doQuickSort(String[] tempStringArr, int startIndex, int endIndex, int start, int end){
		
		if (startIndex < endIndex){
			int index = MakePartition(tempStringArr,startIndex,endIndex,start,end);
			doQuickSort(tempStringArr,startIndex,index-1,start,end);
			doQuickSort(tempStringArr,index+1,endIndex,start,end);			
		}
	}

	private int MakePartition(String[] tempStringArr, int startIndex,
			int endIndex,int start, int end) {
		// TODO Auto-generated method stub
		String line = tempStringArr[startIndex];
		int custId = Integer.parseInt(line.substring(start, end));
		while (startIndex < endIndex) {
			while (startIndex < endIndex && custId <= Integer.parseInt(tempStringArr[endIndex].substring(start,end))) {
				endIndex--;
			}
			tempStringArr[startIndex] = tempStringArr[endIndex];
			
			while (startIndex < endIndex && custId >= Integer.parseInt(tempStringArr[startIndex].substring(start, end))) {
				startIndex++;
			}
			tempStringArr[endIndex] = tempStringArr[startIndex];
		}
		tempStringArr[startIndex] = line;
		return startIndex;
	}

	public static ArrayList<String> Sort(ArrayList<String> listToSort,final int startIndex, final int endIndex){
		Collections.sort(listToSort, new Comparator<String>() {

		    public int compare(String a, String b) {
		    	int joiningAttribute_a = Integer.parseInt(a.substring(startIndex, endIndex));
		    	int joiningAttribute_b = Integer.parseInt(b.substring(startIndex, endIndex));
		    	if (joiningAttribute_a == joiningAttribute_b) {
		    		return 0;
				}else if (joiningAttribute_a > joiningAttribute_b) {
					return 1;
				}else{
					return -1;
				}
		    }

		});
		
		return listToSort;
	}
}
