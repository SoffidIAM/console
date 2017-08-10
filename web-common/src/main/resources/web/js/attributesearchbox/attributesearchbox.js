zkAttributesearchbox={};
zkAttributesearchbox.init=function(ed) {
	var button = document.getElementById(ed.id+"!button");
	zk.listen(button, "click", zkau.onclick);
	var closebutton = document.getElementById(ed.id+"!close");
	zk.listen(closebutton, "click", function () {
		var req = {uuid: ed.id, cmd: "onRemove", data : []};
		zkau.send (req, 5);
	});
};
zkAttributesearchbox.cleanup=function(_2){
};


zkSearchbox={};
zkSearchbox.init=function(ed) {
	zk.listen(document.getElementById(ed.id+"!mode"), 
			"click", 
			function () {
		var req = {uuid: ed.id, cmd: "onChangeMode", data : []};
		zkau.send (req, 5);
	});
	zk.listen(document.getElementById(ed.id+"!search"), 
			"click", 
			function () {
		var req = {uuid: ed.id, cmd: "onSearch", data : []};
		zkau.send (req, 5);
	});
};
zkSearchbox.cleanup=function(_2){
};

