package es.caib.seycon.ng.test;

import java.net.UnknownHostException;

import com.soffid.iam.utils.IPAddress;
import com.soffid.iam.utils.InvalidIPException;

public class IPV6Test {
	public static void main (String args[]) throws UnknownHostException, InvalidIPException {
		IPAddress ip = new IPAddress("fe80::0:0:0:0","64");
		for (int i = 0; i < ip.ip.length; i++)
		{
			System.out.println("IP["+ i+ "]="+ ip.ip[i] + " / " +ip.mask[i]);
		}
		IPAddress ip2 = new IPAddress("fe80:0:0:0:8999:fb80:c2dc:d1bd%5");
		for (int i = 0; i < ip2.ip.length; i++)
		{
			System.out.println("IP["+ i+ "]="+ ip2.ip[i]);
		}
		
		System.out.println(ip.conte(ip2));
	}

}
