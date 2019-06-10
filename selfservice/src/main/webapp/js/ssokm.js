function launchSsoUrl (secrets) {
	if ( document.firstElementChild.getAttribute("soffidAraleExtensionPresent") == "true")
	{
		window.postMessage({type:"soffid",action:"prepare",url:secrets.url,secrets:secrets});
	} else {
		var isChromium = window.chrome;
		var winNav = window.navigator;
		var vendorName = winNav.vendor;
		var isOpera = typeof window.opr !== "undefined";
		var isIEedge = winNav.userAgent.indexOf("Edge") > -1;
		var isIOSChrome = winNav.userAgent.match("CriOS");

		if (isIOSChrome) {
		   // is Google Chrome on IOS
		} else if(
		  isChromium !== null &&
		  typeof isChromium !== "undefined" &&
		  vendorName === "Google Inc." &&
		  isOpera === false &&
		  isIEedge === false
		) {
			if (confirm ("A Google chrome extension must be installed to get single sign-on. Please, confirm to install it"))
			{
				location.href="https://chrome.google.com/webstore/detail/phjdhfhnbedpkmplaegoejildnieofcf";
				return;
			}
		}
	}
	window.setTimeout(function(){window.open(secrets.url, "_blank")}, 100);
}