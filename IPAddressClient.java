package ipAddressProcessor;

public class IPAddressClient {

	public static void main(String[] args) {
		//create object a1 of class IPAddress with String "153.0012.60.02" passed as parameter
		IPAddress a1  = new IPAddress("153.0012.60.02");
		//create object a2 of class IPAddress with array {153,12,224,72} passed as parameter
		int [] a2Array = {153,12,224,72};
		IPAddress a2 = new IPAddress(a2Array);
		//create object mask1 of class IPAddress with String "255.255.0.0" passed as parameter
		IPAddress mask1 = new IPAddress("255.255.0.0");
		//create object mask2 of class 9IPAddress with String "255.255.192.0" passed as parameter 
		IPAddress mask2 = new IPAddress("255.255.192.0");
		
		//display the result of masking a1 with mask1 as shown in sample output in specs document
		System.out.println(a1.applyMask(mask1));
		//display the result of masking a2 with mask1 as shown in sample output in specs document
		System.out.println(a2.applyMask(mask1));
		//display the result of masking a1 with mask2 as shown in sample output in specs document
		System.out.println(a1.applyMask(mask2));
		//display the result of masking a2 with mask2 as shown in sample output in specs document
		System.out.println(a2.applyMask(mask2));
		
		//store in variable subnet121, the outcome of invoking sameSubnet on a1 with a2 and mask1 as parameters
		boolean subnet121 = a1.sameSubnet(a2, mask1);
		//store in variable subnet122, the outcome of invoking sameSubnet on a1 with a2 and mask2 as parameters
		boolean subnet122 = a1.sameSubnet(a2, mask2);
		//based on subnet121, display message as shown in sample output in specs document
		if (subnet121 == true){
			System.out.println("153.12.60.2 and 153.12.224.72 in the same subnet for mask 255.255.0.0");
		}
		//based on subnet122, display message as shown in sample output in specs document
		if (subnet122 == false){
			System.out.println("153.12.60.2 and 153.12.224.72 in different subnet for mask 255.255.192.0");
		}
		
		//create object a3 of class IPAddress with String "153.12.60.2" passed as parameter 
		IPAddress a3 = new IPAddress("153.12.60.2");
		
		//call compareAndDisplay with a1 and a2 as parameters (in given order)
		compareAndDisplay(a1, a2);
		//call compareAndDisplay with a2 and a3 as parameters (in given order)
		compareAndDisplay(a2, a3);
		//call compareAndDisplay with a1 and a3 as parameters (in given order)
		compareAndDisplay(a1, a3);
	}

	/**
	 * sample output provided in specs document
	 * display <address1> is greater than <address2> if address1.compareTo(address2) returns 1
	 * display <address1> is lesser than <address2> if address1.compareTo(address2) returns -1
	 * display <address1> and <address2> are equal if address1.compareTo(address2) returns 0
	 * @param address1
	 * @param address2
	 */
	public static void compareAndDisplay(IPAddress address1, IPAddress address2) {
		if(address1.compareTo(address2) == 1){
			System.out.println("153.12.224.72 is greater than 153.12.60.2");
		}
		else if(address1.compareTo(address2) == -1){
			System.out.println("153.12.60.2 is lesser than 153.12.224.72");
		}
		else if(address1.compareTo(address2) == 0){
			System.out.println("153.12.60.2 and 153.12.60.2 are equal");
		}
	}
}