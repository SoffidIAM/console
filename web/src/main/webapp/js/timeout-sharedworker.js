console.log("Initiialized message from worker");

var ports = [];
var timeout = 0;
var closeAt = 0;
onconnect = (e) => {
	const port = e.ports[0];
	ports.push(port);
	port.addEventListener("message", (e) => {
		var msg = e.data;
		if (msg.msg == 'keep-alive') {
			closeAt = Date.now() + timeout * 60 * 1000;
		}		
		if (msg.msg == 'configure') {
			timeout = msg.timeout;
			closeAt = Date.now() + timeout * 60 * 1000;
		}
	});
	port.start();
}

setInterval(() => {
	if (closeAt > 0 && closeAt < Date.now()) {
		for (var i = 0 ; i < ports.length; i++) {
			var port = ports[i];
			try {
				port.postMessage({msg:"timeout"});
			} catch (e) {}
		}
	}
}, 1000);