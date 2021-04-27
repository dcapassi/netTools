package netTools;

public class Run {

	public static void main(String[] args) {
		
		String ipAddress;
		NetTools.verbose = true;
		ipAddress = "192.168.1.1";
		System.out.println(NetTools.isIpv4(ipAddress));

		
		String mask;
		NetTools.verbose = true;
		mask = "255.255.255.192";
		System.out.println(NetTools.isAValidMask(mask));
		
		String binary1 = NetTools.binary32bits("172.16.1.193");
		String binary2 = NetTools.binary32bits("255.255.255.224");
		System.out.println(binary1);
		System.out.println(binary2);
		String result = NetTools.binary32bitsFormated(NetTools.binaryAnd(binary1, binary2),true);
		
		System.out.println(result);
		
	}

}
