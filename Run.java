package netTools;

public class Run {

	public static void main(String[] args) {
		
		NetTools.verbose = true;
		String ipAddress = "192.168.1.1";
		System.out.println(NetTools.isIpv4(ipAddress));
		
		

	}

}
