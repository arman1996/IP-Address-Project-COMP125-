package ipAddressProcessor;

public class IPAddress {
	private int[] parts;

	/**
	 * instantiates the instance variable array parts to an array of size 4 
	 */
	public void reset() {
		parts = new int[4];
	}
	
	/**
	 * 
	 * @param token
	 * @return true if parameter is the String representation of an integer
	 * between 0 and 255 (including 0 and 255), false otherwise.
	 * Strings "0", "1", "2" .... "254", "255" are valid.
	 * Padded Strings (such as "00000000153") are also valid 
	 * 
	 * Note that isValidElement is a static method. It can be called from
	 * other methods in the class, whether static or non-static.
	 */
	public static boolean isValidElement(String token) {
		for(int i = 0; i<token.length(); i++){
			char a = token.charAt(i);
			//Compares ASCII values of letters a-z and A-Z
			if((a>=65 && a<=90) || (a>=97 && a<=122)){
				return false;
			}
		}
		int castString = Integer.parseInt(token);
		if(castString>=0 && castString<=255){
			return true;
		}
		return false;
	}

	/**
	 * If the ip address from the String passed is valid,
	 * sets the instance variable parts to store it as 4 integer values.
	 * For example, if ip = "192.000168.0.0000001", parts should become {192,168,0,1}.
	 * If the ip address passed is invalid, parts should become {0,0,0,0}
	 * 
	 * remember to reset the instance array parts before you do anything else
	 * @param ip
	 */
	public void setParts(String ip) {
		reset();
		// Used "\" to escape the special characters "\." and "."
		String[] ipSplit = ip.split("\\.");
		if(ipSplit.length != 4) {
			parts[0] = 0;
			parts[1] = 0;
			parts[2] = 0;
			parts[3] = 0;
			return;
		}
		for(int i = 0; i<ipSplit.length; i++){
			if(isValidElement(ipSplit[i]) == true){
				parts[i] = Integer.parseInt(ipSplit[i]);
			}
			else{
				parts[0] = 0;
				parts[1] = 0;
				parts[2] = 0;
				parts[3] = 0;
				return;
			}
		}
	}

	/**
	 * If the ip address from the array passed (data) is valid,
	 * makes a deep copy of the array passed in the instance variable parts.
	 * For example, if data = {192,168,0,1}, parts should become {192,168,0,1}
	 * by copying each item of data into corresponding item in parts.
	 * If the ip address passed is invalid (for example {500,4,60,216}
	 * or {192,16,01}, or {13,13,13,13,13}, parts should become {0,0,0,0}
	 * 
	 * remember to reset the instance array parts before you do anything else
	 * @param data
	 */
	public void setParts(int[] data) {
		reset();
		if(data.length != 4) {
			parts[0] = 0;
			parts[1] = 0;
			parts[2] = 0;
			parts[3] = 0;
			return;
		}
		for(int i = 0; i<data.length; i++){
			if(data[i]>=0 && data[i]<=255){
				parts[i] = data[i];
			}
			else{
				parts[0] = 0;
				parts[1] = 0;
				parts[2] = 0;
				parts[3] = 0;
				return;
			}
		}
		
	}

	/**
	 * DO NOT MODIFY
	 * construct an address using ip address passed as String
	 * @param address
	 */
	public IPAddress(String address) {
		setParts(address);
	}	

	/**
	 * DO NOT MODIFY
	 * construct an address using tokens of ip address passed as an array
	 * @param parts
	 */
	public IPAddress(int[] parts) {
		setParts(parts);
	}

	/**
	 * 
	 * @return a deep copy of instance variable array parts
	 */
	public int[] getParts() {
		int[] partsCopy = new int[4];
		for(int i = 0; i<partsCopy.length; i++){
			partsCopy[i] = parts[i];
		}
		return  partsCopy;
	}

	/**
	 * compare calling object and parameter object on each item of the instance 
	 * variable array parts, one by one. 
	 * 
	 * So, for example, if the first item of parts of calling object is more than
	 * the first item of parts of parameter object, return 1. if the first item of 
	 * parts of calling object is less than the first item of parts of parameter 
	 * object, return -1.
	 * 
	 * if the first item of parts of calling object and the first item of parts of 
	 * parameter object are the same, continue with comparing the second items 
	 * and so on.
	 * 
	 * If each of the four items are the same, return 0 
	 * @param other
	 * @return 
	 */
	public int compareTo(IPAddress other) {
		int [] thisArray = this.parts;
		int [] otherArray = other.parts;
		int i = 0;
		
		while(i <= 3){
			if(thisArray[i] > otherArray[i]){
				return 1;
			}
			else if(thisArray[i] < otherArray[i]){
				return -1;
			}
			else if(thisArray[i] == otherArray[i]){
				i = i + 1;
			}
		}
		return 0;
	}

	/**
	 * 
	 * @param other
	 * @return true if calling object and parameter object represent 
	 * the same ip address, and false otherwise
	 */
	public boolean equals(IPAddress other) {
		// Using the compareTo method from before
		if(this.compareTo(other) == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * ip addresses from 0.0.0.0 to 127.255.255.255 are class A addresses.
	 * ip addresses from 128.0.0.0 to 191.255.255.255 are class B addresses.
	 * ip addresses from 192.0.0.0 to 223.255.255.255 are class C addresses.
	 * ip addresses from 224.0.0.0 to 255.255.255.255 are class D addresses.
	 * @return the classification of the ip address (as 'A' or 'B' or 'C' or 'D')
	 */
	public char getClassification() {
		// Using the ASCII code of '@' to initialize the variable. As the integer value of '@' is right before 'A'
		int classification = '@';
		if(parts[0] <= 127){
			classification = classification + 1;
		}
		else if(parts[0] >= 128 && parts[0] <= 191){
			classification = classification + 2;
		}
		else if(parts[0] >= 192 && parts[0] <= 223){
			classification = classification + 3;
		}
		else if(parts[0] >= 224 && parts[0] <= 255){
			classification = classification + 4;
		}
		return (char) classification;
	}

	/**
	 * 
	 * DO NOT MODIFY
	 * 
	 * subnet mask provides a way to obtain a network address from
	 * and ip address by performing a bit-by-bit AND operation (bitwise AND)
	 * on the mask and the ip address. Java provides the bitwise operator 
	 * in '&'. For example, the expression 13 & 14 evaluates to 12 
	 * (as 13 = 1101 and 14 = 1110).
	 *   1101 (13)
	 * & 1110 (14)
	 * ------ 
	 *   1100 (12)
	 *   
	 *   IP:   1101 1000 . 0000 0011 . 1000 0000 . 0000 1100  (216.3.128.12)
	 *   Mask: 1111 1111 . 1111 1111 . 1111 1111 . 0000 0000  (255.255.255.0)
     * ---------------------------------------------
     *         1101 1000 . 0000 0011 . 1000 0000 . 0000 0000  (216.3.128.0)
	 * @param mask
	 * @return the network address given by applying the 
	 * subnet mask on given ip address
	 * 
	 */
	public IPAddress applyMask(IPAddress mask) {
		int[] result = new int[4];
		for(int i=0; i < parts.length; i++)  {
			result[i] = parts[i] & mask.parts[i];
		}
		return new IPAddress(result);
	}
	
	/**
	 * 
	 * @param other
	 * @param mask
	 * @return true if the calling object and parameter object other are in the same
	 * subnet when the parameter mask is applied, false otherwise.
	 * 
	 * that is, both calling object and parameter object return the same subnet address
	 * after the mask is applied.
	 * 
	 * for example,
	 * 
	 * if this = {192,144,48,64}, other = {192,144,96,64}, mask = {255,255,0,0}
	 * then 
	 * the ip address returned upon apply mask on this is {192,144,0,0}
	 * and 
	 * the ip address returned upon apply mask on other is {192,144,0,0} as well
	 * 
	 * therefore 
	 * {192,144,48,64} and {192,144,96,64} are in same subnet for mask {255,255,0,0}
	 * 
	 * but if this = {192,144,48,64}, other = {192,144,96,64}, mask = {255,255,255,0}
	 * then 
	 * the ip address returned upon apply mask on this is {192,144,48,0}
	 * while 
	 * the ip address returned upon apply mask on other is {192,144,64,0}
	 * 
	 * therefore 
	 * {192,144,48,64} and {192,144,96,64} are NOT in same subnet for mask {255,255,255,0}
	 * 
	 * You can use this website to check if two ip addresses are in the same
	 * subnet for a given mask - 
	 * http://www.meridianoutpost.com/resources/etools/network/two-ips-on-same-network.php
	 */
	public boolean sameSubnet(IPAddress other, IPAddress mask) {
		IPAddress thisMasked = this.applyMask(mask);
		IPAddress otherMasked = other.applyMask(mask);
		if(thisMasked.compareTo(otherMasked) == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * DO NOT MODIFY
	 */
	public String toString() {
		String result = "";
		for(int i=0; i < parts.length; i++)
			result+=parts[i]+".";
		return result.substring(0, result.length() - 1);
	}
}
