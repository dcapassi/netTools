package netTools;

public class NetTools {


	public static boolean isIpv4(String ipAddress) {

		if (!ipAddressHasThreeDots(ipAddress))
			return false;

		if (!checkIfIpHasFourOctects(ipAddress))
			return false;

		if (!checkIfIsIntegerAndHasValidRange(ipAddress))
			return false;

		return true;
	}

	public static boolean ipAddressHasThreeDots(String ipAddress) {

		int countSeparators = 0;
		for (int i = 0; i < ipAddress.length(); i++) {
			if (ipAddress.charAt(i) == '.') {
				countSeparators++;
			}
		}
		if (countSeparators != 3) {
			return false;
		}
		return true;

	}

	public static boolean ipAddressHasFourOctects(String[] ipAddressOctets) {

		int numberOfOctects = ipAddressOctets.length;

		if (numberOfOctects != 4)
			return false;
		
		return true;
	}

	public static String[] getIpAddressOctects(String ipAddress) {
		String[] ipAddressOctets = ipAddress.split("\\.");
		return ipAddressOctets;
	}

	public static boolean checkIfIpHasFourOctects(String ipAddress) {
		String[] ipAddressOctets = getIpAddressOctects(ipAddress);
		return ipAddressHasFourOctects(ipAddressOctets);
	}

	public static boolean checkIfIsIntegerAndHasValidRange(String ipAddress) {

		String[] ipAddressOctets = getIpAddressOctects(ipAddress);

		int octectValue = 0;
		int count = 0;
		while (count < 4) {
			try {
				octectValue = Integer.parseInt(ipAddressOctets[count]);
			} catch (NumberFormatException ex) {
				return false;
			} finally {
				count++;
				if (octectValue < 0 | octectValue > 255) 
					return false;
				}
			}
		return true;
	}

	public static boolean isAValidMask(String mask) {

		boolean isIpv4 = isIpv4(mask);
		if (!isIpv4) {
			return false;
		}

		String maskBinary32bits = binary32bits(mask);
		boolean transition = false;

		if (maskBinary32bits.charAt(0) == '0') {
			return false;
		}

		if (maskBinary32bits.charAt(0) == '0') {
			return false;
		}

		for (int i = 0; i < maskBinary32bits.length() - 1; i++) {
			if (maskBinary32bits.charAt(i) != maskBinary32bits.charAt(i + 1)) {
				if (transition == true) {
					return false;
				} else {
					transition = true;
				}
			}
		}
		return true;
	}

	public static String binaryByteWithLeadingZeroes(int decimalOctect) {

		if (decimalOctect < 0 | decimalOctect > 255) {
			return null;
		}

		String binaryString = Integer.toBinaryString(decimalOctect);
		String leadingZeros = "";

		int count = 8 - binaryString.length();

		while (count > 0) {
			leadingZeros = leadingZeros + "0";
			count--;
		}

		binaryString = leadingZeros + binaryString;

		return binaryString;

	}

	public static String binary32bits(String fourOctectsDecimal) {
		String[] decimalOctects = fourOctectsDecimal.split("\\.");
		String binary32bits = "";
		for (int i = 0; i < 4; i++) {
			binary32bits = binary32bits + binaryByteWithLeadingZeroes(Integer.parseInt(decimalOctects[i]));
		}
		return binary32bits;
	}

	public static String binaryAnd(String firstBinary32bits, String secondBinary32bits) {
		// check if both entries are 32 bits long
		if (firstBinary32bits.length() != 32 | secondBinary32bits.length() != 32) {
			return null;
		}
		String and = "";
		int bitOfTheFirstInput, bitOfTheSecondInput;
		for (int i = 0; i < 32; i++) {
			bitOfTheFirstInput = (Character.getNumericValue(firstBinary32bits.charAt(i)));
			bitOfTheSecondInput = (Character.getNumericValue(secondBinary32bits.charAt(i)));
			and = and + Integer.toString(bitOfTheFirstInput & bitOfTheSecondInput);
		}
		return and;
	}

	public static String binary32bitsFormated(String binary32bits, boolean formatDecimal) {
		if (binary32bits.length() != 32) {
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
					format = Integer.toString((Integer.parseInt(octect, 2)));
				} else {
					format = octect;
				}
				ipFormated = ipFormated + "." + format;
				octect = "";
				count = 1;
			} else {
				count++;
			}
		}
		return ipFormated.substring(1);
	}

	public static String ipv6Expansion(String ipAddressV6) {

		int countSeparators = 0;
		int countDoubleSepators = 0;
		int posDoubleSepator = 0;
		String expandedIpv6Address = ipAddressV6;

		for (int i = 0; i < ipAddressV6.length() - 1; i++) {
			if (ipAddressV6.charAt(i) == ':') {
				countSeparators++;

				if (ipAddressV6.charAt(i + 1) == ':') {
					countDoubleSepators++;
					posDoubleSepator = i;
				}
			}
		}

		if (countDoubleSepators > 1 | (countSeparators != 7 & countDoubleSepators == 0)) {
			System.out.println("Error in the Ipv6 format");
			return null;
		}

		String expandendSepator = "";
		if (countDoubleSepators == 1) {
			expandendSepator = expandendSepator + ":";
			for (int i = 0; i < 8 - countSeparators; i++) {
				expandendSepator = expandendSepator + "0:";
			}
			expandedIpv6Address = ipAddressV6.substring(0, posDoubleSepator) + expandendSepator
					+ ipAddressV6.substring(posDoubleSepator + 2);

		}

		if (expandedIpv6Address.charAt(0) == ':') {
			expandedIpv6Address = "0" + expandedIpv6Address;
		}
		if (expandedIpv6Address.charAt(expandedIpv6Address.length() - 1) == ':') {
			expandedIpv6Address = expandedIpv6Address + "0";
		}

		return expandedIpv6Address;

	}

	public static boolean isIpv6(String ipAddress) {

		ipAddress = ipv6Expansion(ipAddress);

		if (ipAddress == null)
			return false;

		String[] ipAddressOctets = ipAddress.split("\\:");
		int numberOfHextects = ipAddressOctets.length;

		if (numberOfHextects != 8) 
			return false;

		int number = 0;
		int count = 0;

		while (count < 8) {
			try {
				number = Integer.parseInt(ipAddressOctets[count], 16);
			} catch (NumberFormatException ex) {
				return false;
			} finally {
				count++;
				if (number < 0 | number > 65535)
					return false;
			}
		}

		return true;
	}
}
