zkAttributesearchbox={};
zkAttributesearchbox.init=function(ed) {
	var button = document.getElementById(ed.id+"!button");
	if (button) zk.listen(button, "click", zkau.onclick);
	var closebutton = document.getElementById(ed.id+"!close");
	if (closebutton) zk.listen(closebutton, "click", function () {
		var req = {uuid: ed.id, cmd: "onRemove", data : []};
		zkau.send (req, 5);
	});
};
zkAttributesearchbox.cleanup=function(_2){
};


zkSearchbox={};
zkSearchbox.init=function(ed) {
	var e = document.getElementById(ed.id+"!modeText");
	if (e) zk.listen(e, 
			"click", 
			function () {
		var req = {uuid: ed.id, cmd: "onChangeMode", data : [0]};
		zkau.send (req, 5);
	});
	e = document.getElementById(ed.id+"!modeBasic");
	if (e) zk.listen(e, 
			"click", 
			function () {
		var req = {uuid: ed.id, cmd: "onChangeMode", data : [1]};
		zkau.send (req, 5);
	});
	e=document.getElementById(ed.id+"!modeAdvanced");
	if (e) zk.listen(e, 
			"click", 
			function () {
		var req = {uuid: ed.id, cmd: "onChangeMode", data : [2]};
		zkau.send (req, 5);
	});
	e = document.getElementById(ed.id+"!search");
	if (e) zk.listen(e, 
			"click", 
			function () {
		var req = {uuid: ed.id, cmd: "onSearch", data : []};
		zkau.send (req, 5);
	});
};
zkSearchbox.cleanup=function(_2){
};

