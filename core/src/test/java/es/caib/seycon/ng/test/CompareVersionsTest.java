package es.caib.seycon.ng.test;

import com.soffid.iam.service.ServerPluginServiceImpl;
import com.soffid.iam.utils.Security;

public class CompareVersionsTest extends AbstractTest {

	public void testCompareVersion() throws Exception
	{
		try {
			Security.nestedLogin("Test", Security.ALL_PERMISSIONS);

			ServerPluginServiceImpl s = new ServerPluginServiceImpl();

			if (0!=s.compareVersions("1", "1")) throw new Exception("CompareVersionsTest result not 0");
			if (0!=s.compareVersions("1.1", "1.1")) throw new Exception("CompareVersionsTest result not 0");
			if (0!=s.compareVersions("1.1.1", "1.1.1")) throw new Exception("CompareVersionsTest result not 0");
			if (0!=s.compareVersions("3.0.1-2024.04.12.16.10", "3.0.1-2024.04.12.16.10")) throw new Exception("CompareVersionsTest result not 0");
			if (0!=s.compareVersions("3.0.1-2024.04.12.16.10-aaaa", "3.0.1-2024.04.12.16.10-aaaa")) throw new Exception("CompareVersionsTest result not 0");
			if (0!=s.compareVersions("aaaa", "aaaa")) throw new Exception("CompareVersionsTest result not 0");

			if (-1!=s.compareVersions("1", "2")) throw new Exception("CompareVersionsTest result not -1");
			if (-1!=s.compareVersions("1", "9")) throw new Exception("CompareVersionsTest result not -1");
			if (-1!=s.compareVersions("1", "10")) throw new Exception("CompareVersionsTest result not -1");
			if (-1!=s.compareVersions("3", "27")) throw new Exception("CompareVersionsTest result not -1");
			if (-1!=s.compareVersions("56", "765")) throw new Exception("CompareVersionsTest result not -1");
			if (-1!=s.compareVersions("1.1", "1.2")) throw new Exception("CompareVersionsTest result not -1");
			if (-1!=s.compareVersions("1.2", "1.10")) throw new Exception("CompareVersionsTest result not -1");
			if (-1!=s.compareVersions("3.27", "3.102")) throw new Exception("CompareVersionsTest result not -1");
			if (-1!=s.compareVersions("3.27", "3.102.4.6")) throw new Exception("CompareVersionsTest result not -1");
			if (-1!=s.compareVersions("3.0.1-2024.04.12.16.10", "3.0.1-2024.04.14.16.10")) throw new Exception("CompareVersionsTest result not -1");
			if (-1!=s.compareVersions("2024.04.12.16.10", "2024.04.14.16.10")) throw new Exception("CompareVersionsTest result not -1");
			if (-1!=s.compareVersions("3.0.1", "3.0.1.1")) throw new Exception("CompareVersionsTest result not -1");
			if (-1!=s.compareVersions("3.0.1", "3.0.1.1-2024.04.12.16.10")) throw new Exception("CompareVersionsTest result not -1"); // -1
			if (-1!=s.compareVersions("3.0.1-2024.04.12.16.10", "3.0.1")) throw new Exception("CompareVersionsTest result not -1");   // -1

			if (1!=s.compareVersions("2", "1")) throw new Exception("CompareVersionsTest result not 1");
			if (1!=s.compareVersions("1.2", "1.1")) throw new Exception("CompareVersionsTest result not 1");
			if (1!=s.compareVersions("10", "5.17")) throw new Exception("CompareVersionsTest result not 1");
			if (1!=s.compareVersions("3.0.1", "3.0.1-2024.04.12.16.10")) throw new Exception("CompareVersionsTest result not 1");
			if (1!=s.compareVersions("3.0.1.1", "3.0.1-2024.04.12.16.10")) throw new Exception("CompareVersionsTest result not 1");

		} finally {
			Security.nestedLogoff();
		}
	}
}
