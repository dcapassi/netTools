package netTools;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NetToolsTest {

	@Test
	void testIsIpv4() {
		assertFalse(NetTools.isIpv4("192.168.1.256."));
		assertFalse(NetTools.isIpv4("192.168.1"));
		assertFalse(NetTools.isIpv4("192.168.1.1.1"));
		assertFalse(NetTools.isIpv4(""));
		assertTrue(NetTools.isIpv4("0.0.0.0"));
		assertTrue(NetTools.isIpv4("192.168.1.1"));
		assertTrue(NetTools.isIpv4("1.1.1.1"));
		assertTrue(NetTools.isIpv4("255.255.255.255"));		
}
	@Test
	void testIsAValidMask() {
		assertFalse(NetTools.isAValidMask("255.0.255.0"));
		assertFalse(NetTools.isAValidMask("0.0.255.255"));
		assertFalse(NetTools.isAValidMask("0.255.255.0"));
		assertFalse(NetTools.isAValidMask("255.0.0.1"));
		assertTrue(NetTools.isAValidMask("255.255.255.0"));
		assertTrue(NetTools.isAValidMask("255.255.0.0"));
		assertTrue(NetTools.isAValidMask("255.0.0.0"));
		assertTrue(NetTools.isAValidMask("255.255.255.255"));
}
	
	
	
}