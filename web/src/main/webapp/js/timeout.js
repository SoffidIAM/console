if (sessionTimeout && sessionTimeout > 0) {
	
	var closeAt = 	Date.now() + sessionTimeout * 60 * 1000;
	setInterval (() => {
		if (Date.now() > closeAt) {
			zkau.send ({uuid: mainWindowUuid, cmd: "onTimeout", data : []}, 5);		
		}
	}, 10000); // Every ten seconds
	window.addEventListener("keypress", (e) => {
		closeAt = Date.now() + sessionTimeout * 60 * 1000;
	});
	window.addEventListener("click", (e) => {
		closeAt = Date.now() + sessionTimeout * 60 * 1000;
	});
}