if (sessionTimeout && sessionTimeout > 0) {
	if (typeof(SharedWorker) !== "undefined") {
		var worker = new SharedWorker("/soffid/js/timeout-sharedworker.js");
		worker.port.start();
		worker.port.postMessage({msg:"configure", timeout: sessionTimeout});
		worker.port.onmessage = (e) => {
			zkau.send ({uuid: mainWindowUuid, cmd: "onTimeout", data : []}, 5);					
		};
		window.addEventListener("keypress", (e) => {
			worker.port.postMessage({msg:"keep-alive"});
		});
		window.addEventListener("click", (e) => {
			worker.port.postMessage({msg:"keep-alive"});
		});
	} else {
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
}