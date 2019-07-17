function launchSsoUrl (secrets) {
	var d = document.getElementById("soffidAraleExtensionDetector");
	if ( d && d.getAttribute("soffidAraleExtensionPresent") == "true")
	{
		var ev = new CustomEvent("soffidMessage", {detail: 
			{type:"soffid",action:"prepare",url:secrets.url,secrets:secrets}});
		d.dispatchEvent(ev);
	} else if (d) {
		var isChromium = window.chrome;
		var winNav = window.navigator;
		var vendorName = winNav.vendor;
		var isOpera = typeof window.opr !== "undefined";
		var isIEedge = winNav.userAgent.indexOf("Edge") > -1;
		var isIOSChrome = winNav.userAgent.match("CriOS");
		var isFirefox = winNav.userAgent.match("Firefox/");
		
		if (isIOSChrome) {
		   // is Google Chrome on IOS
		} else if(
		  isChromium !== null &&
		  typeof isChromium !== "undefined" &&
		  vendorName === "Google Inc." &&
		  isOpera === false &&
		  isIEedge === false
		) {
			if (confirm ("A Google chrome extension must be installed to get single sign-on. Please, confirm to install it. After installing the addon, you can come backup to Soffid self service portal"))
			{
				location.href="https://chrome.google.com/webstore/detail/phjdhfhnbedpkmplaegoejildnieofcf";
				return;
			}
		} else if ( isFirefox) {
			if (confirm ("A Firefox extension must be installed to get single sign-on. Please, confirm to install it. After installing the addon, you can come backup to Soffid self service portal"))
			{
				location.href="https://addons.mozilla.org/es/firefox/addon/soffid-self-service/";
				return;
			}
		}
		window.setTimeout(function(){window.open(secrets.url, "_blank")}, 100);
	}
}
