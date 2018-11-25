import java.util.Scanner;
class Solution{
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		//String type = s.nextLine();
		int num = Integer.parseInt(s.nextLine());
		int[] arr = new int[num];
		for(int i=0;i<num;i++){
			arr[i] = Integer.parseInt(s.nextLine());
		}
		BinarySearchST<Integer, Integer> b = new BinarySearchST<Integer, Integer>();
		for(int i=0;i<num;i++){
				b.put(arr[i], 0);
			}
		Double linearCount = 0.0;
		while(s.hasNext()){
			b.resetCounter();
			linearCount = 0.0;
			int temp = Integer.parseInt(s.nextLine());
			boolean binaryFlag = b.contains(temp, true);
			boolean linearFlag = false;
			//b.resetCounter();
			for(int i =0;i<num;i++){
				linearCount++;
				if(temp == arr[i]){
					//linearCount == 0;
					linearFlag = true;
					break;
				}
			}
			/*if(!binaryFlag&& !linearFlag){
				continue;
			}*/
			System.out.println();
			System.out.println("--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--");
			System.out.println("Size of symbole table: "+num);
			System.out.println("Search input: "+ temp);
			Double binary = b.getCounter();
			System.out.println("Iteration for BInarySearch: "+binary+"\nBinary search status: "+binaryFlag);
			System.out.println("Iteration for linear search: "+linearCount+"\nLinear search status: "+linearFlag);
			System.out.println("Linear / binary seach: "+(linearCount/binary));
			b.resetCounter();
			linearCount = 0.0;

		}

	}
}