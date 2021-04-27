package netTools;

public class NetTools {
	
	public static boolean verbose = false;
	
	public static boolean isIpv4(String ipAddress) {
				
		if (verbose) {
			System.out.println("Checking if the string: "+ ipAddress +" has the IPv4 format.");
		}
		
		String[] ipAddressOctets = ipAddress.split("\\.");
		int numberOfOctects = ipAddressOctets.length;
		
		if (verbose) {
			System.out.println("Number of Octects: "+ numberOfOctects);
		}
		
		//Check if the string has four octects
		if (numberOfOctects != 4) {
			if (verbose) {
				System.out.println("IPv4 check failed: Does not have four octects");
			}
			return false;
		}
		
		//Convert the octect to Int
		//Check if it is the range between 0 and 255
		
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
				System.out.println(number);
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
	}
}
