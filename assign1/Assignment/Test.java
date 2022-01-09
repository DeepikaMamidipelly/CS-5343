package Assignment;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[]= {41,65,14,80,20,10,55,58,24,56,28,86,96,10,3,84,4,41,13,32,42,43,83,78,82,70,15,-41};
	        int product=1;
	        for(int i:nums){
	            product=product*i;
	        }
	        System.out.println(product);
	        if(product>0){
	            System.out.print(">0"+product);
	        }else if(product==0){
	            System.out.print("=0"+product);
	        }else{
	            System.out.print("<0"+product);
	        }
	  
	}

}
