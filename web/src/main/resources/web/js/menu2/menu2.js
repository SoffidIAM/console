zkMenu2={};
zkMenu2.init=function(ed) {
	var button = document.getElementById(ed.id+"!starter");
	if (button) {
		button.master = ed;
		zk.listen(button, "click", zkMenu2.onOpenMenu);
	}
	var modal = document.getElementById(ed.id+"!modal");
	if (modal) {
		modal.master = ed;
		zk.listen(modal, "click", zkMenu2.onCloseMenu);
	}
};

zkMenu2.cleanup=function(_2){
};


zkMenu2.onOpenMenu=function(ev) {
	var top = ev.currentTarget.master;
	var table = document.getElementById(top.id+"!modal");
	document.body.appendChild(table);
	if (table.classList.contains("open")) {
		top.appendChild(table);
		table.classList.remove("open");
	}
	else
	{
		table.classList.add("open");
		var dropdown = document.getElementById(top.id+"!dropdown");
		var button = document.getElementById(top.id+"!starter");
		var crdd = dropdown.getClientRects();
		if (crdd.length > 0 && crdd[0].width > 10) {
			var crdd0 = crdd[0];
			var crbtn = button.getClientRects()[0];
			dropdown.style.top = String(crbtn.bottom)+"px";
			dropdown.style.left = String(crbtn.right-crdd0.width)+"px";
		} else {
			top.appendChild(table);
			table.classList.remove("open");
		}
	}
} 

zkMenu2.onCloseMenu=function(ev) {
	var top = ev.currentTarget.master;
	var table = document.getElementById(top.id+"!modal");
	table.classList.remove("open");
	top.appendChild(table);
} 


zkMenu2item={};

zkMenu2item.init=function(ed) {
};

zkMenu2item.cleanup=function(_2){
};

