package netTools;

public class NetTools {
	
	public static boolean verbose = false;
	
	public static boolean isIpv4(String ipAddress) {
		
		if (verbose) {
			System.out.println("Checking if the string: "+ ipAddress +" has a valid IPv4 format.");
		}
		
		String[] ipAddressOctets = ipAddress.split("\\.");
		int numberOfOctects = ipAddressOctets.length;
		
		if (verbose) {
			System.out.println("Number of Octects: "+ numberOfOctects);
		}
		
		//	Check if the string has four octects
		if (numberOfOctects != 4) {
			if (verbose) {
				System.out.println("IPv4 check failed: Does not have four octects");
			}
			return false;
		}
		
		//	Convert the octect to Int
		//	Check if it is the range between 0 and 255
		
		int number = 0;
		int count = 0;
		
		while (count < 4) {
			try {
	            number = Integer.parseInt(ipAddressOctets[count]);
			}
	        catch (NumberFormatException ex){
	            ex.printStackTrace();
				if (verbose) {
					System.out.println("IPv4 check failed invalid entry");
					return false;
				}
				return false;     
	        }finally {
				count++;
				if (number<0 | number>255) {
					if (verbose) {
						System.out.println("IPv4 check failed: Octect #"+count+" out of range.");
						return false;
					}
				}
	        }
		}
		return true;
	} // End of the isIpv4 method
	
	public static boolean isIpv6(String ipAddress) {
		return true;
	}
	
	public static boolean isAValidMask(String mask) {

		//	Check if the Mask has a valid Ipv4 format
		//	4 Octects
		//	Numbers from 0 to 255
		
		boolean isIpv4 = isIpv4(mask);
		if (!isIpv4) {
			return false;
		}
		
		String maskBinary32bits = binary32bits(mask);
		boolean transition = false;
		
		for (int i = 0; i < maskBinary32bits.length()-1;i++) {
			if (maskBinary32bits.charAt(i) != maskBinary32bits.charAt(i+1)) {
				if (transition == true) {
					System.out.println("Invalid Mask!");
					return false;
				}else {
					transition = true;
				}
			}
		}

		return true;

		}
	
	public static String binaryByteWithLeadingZeroes(int decimalOctect) {
		
		if (decimalOctect < 0 | decimalOctect > 255 ) {
			return null;
		}
		
		String binaryString = Integer.toBinaryString(decimalOctect);
		String leadingZeros = "";
		
		int count = 8 - binaryString.length();
		
		while (count > 0) {
			leadingZeros = leadingZeros + "0";
			count--;
		}
		
		binaryString = leadingZeros  + binaryString;
		
		return binaryString;
		
	}
	
	public static String binary32bits(String fourOctectsDecimal) {
	String[] decimalOctects = fourOctectsDecimal.split("\\.");
	String binary32bits = "";
	for (int i = 0; i < 4; i++) {
		binary32bits = binary32bits + 
		binaryByteWithLeadingZeroes(Integer.parseInt(decimalOctects[i]));
	}
	return binary32bits;
}
	public static String binaryAnd(String binary32bits1, String binary32bits2) {
		//check if both entries are 32 bits long
		if (binary32bits1.length()!=32 | binary32bits2.length()!=32 ) {
			return null;
		}
		String and = "";
		int digit1, digit2;
		for (int i = 0; i < 32; i++) {
			digit1 = (Character.getNumericValue(binary32bits1.charAt(i)));
			digit2 = (Character.getNumericValue(binary32bits2.charAt(i)));
			and = and + Integer.toString(digit1&digit2);
		}
		return and;
	}
	
	public static String binary32bitsFormated(String binary32bits, boolean formatDecimal) {
		if (binary32bits.length()!=32) {
			return null;
		}
		int count = 1;
		String octect = "";
		String format = "";
		String ipFormated = "";
		for (int i = 0; i < 32; i++) {
			octect = octect + binary32bits.charAt(i);
			if (count == 8) {
				if (formatDecimal) {
					format = Integer.toString((Integer.parseInt(octect,2)));
				}else {
					format = octect;
				}
				ipFormated = ipFormated + "." + format;
				octect = "";
				count = 1;
			}else {
			count++;
			}
		}
		return ipFormated.substring(1);
	}
} 
