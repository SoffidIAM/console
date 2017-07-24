zkAttributesearchbox={};
zkAttributesearchbox.init=function(ed) {
	var button = document.getElementById(ed.id+"!button");
	console.log("button="+button);
	zk.listen(button, "click", zkau.onclick);
	var closebutton = document.getElementById(ed.id+"!close");
	zk.listen(closebutton, "click", function () {
		var req = {uuid: ed.id, cmd: "onRemove", data : []};
		zkau.send (req, 5);
	});
	console.log("closebutton="+closebutton);
};
zkAttributesearchbox.cleanup=function(_2){
};

