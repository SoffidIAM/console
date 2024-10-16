var ports = [];
var timeout = 0;
var closeAt = 0;
var notify = true;
onconnect = (e) => {
	const port = e.ports[0];
	ports.push(port);
	port.addEventListener("message", (e) => {
		var msg = e.data;
		if (msg.msg == 'keep-alive') {
			closeAt = Date.now() + timeout * 60 * 1000;
			notify = true;
		}		
		if (msg.msg == 'configure') {
			timeout = msg.timeout;
			closeAt = Date.now() + timeout * 60 * 1000;
			notify = true;
		}
	});
	port.start();
}

setInterval(() => {
	if (closeAt > 0 && closeAt < Date.now() && notify) {
		notify = false;
		for (var i = 0 ; i < ports.length; i++) {
			var port = ports[i];
			try {
				port.postMessage({msg:"timeout"});
			} catch (e) {}
		}
	}
}, 1000);