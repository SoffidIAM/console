function dolaunchSsoUrl (d, secrets, direct) {
	var ev = new CustomEvent("soffidMessage", {detail: 
		{type:"soffid",action:"prepare",url:secrets.url,secrets:secrets,direct:direct}});
	d.dispatchEvent(ev);
}


function launchSsoUrl (secrets, direct) {
	var d = document.getElementById("soffidAraleExtensionDetector");
	if ( d && d.getAttribute("soffidAraleExtensionPresent") == "true")
	{
		dolaunchSsoUrl(d, secrets, direct);
	} else {
		installSSOExtension();
	}
}

function doRegisterVault() {
	var d = document.getElementById("soffidAraleExtensionDetector");
	if ( d && d.getAttribute("soffidAraleExtensionPresent") == "true") {
		var evt  = document.createEvent ("HTMLEvents");
		evt.initEvent ("registerVault", true, true);
		d.dispatchEvent(evt);
	} else {
		installSSOExtension();
	}
}


function installSSOExtension() {
	window.setTimeout( function () {
		var d = document.getElementById("soffidAraleExtensionDetector");
		if ( d && d.getAttribute("soffidAraleExtensionPresent") == "true")
		{
			dolaunchSsoUrl(d, secrets, direct);
		
		} else  if (d) {
			var isChromium = window.chrome;
			var winNav = window.navigator;
			var vendorName = winNav.vendor;
			var isOpera = typeof window.opr !== "undefined";
			var isIEedge = winNav.userAgent.indexOf("Edge") > -1;
			var isIOSChrome = winNav.userAgent.match("CriOS");
			var isFirefox = winNav.userAgent.match("Firefox/");
			
			if ( isFirefox) {
				if (confirm ("A Firefox extension must be installed to get single sign-on. Please, confirm to install it. After installing the addon, you can come backup to Soffid self service portal"))
				{
					location.href="https://addons.mozilla.org/es/firefox/addon/soffid-self-service/";
					return;
				}
			} else {
				if (confirm ("A Google chrome extension must be installed to get single sign-on. Please, confirm to install it. After installing the addon, you can come backup to Soffid self service portal"))
				{
					location.href="https://chrome.google.com/webstore/detail/phjdhfhnbedpkmplaegoejildnieofcf";
					return;
				}
			}
			window.setTimeout(function(){window.open(secrets.url, "_blank")}, 100);
		}
	}, 300);
}