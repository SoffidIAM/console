zkMenu3={};
zkMenu3.init=function(ed) {
	if (ed.getAttribute("options"))
		ed.options = JSON.parse (ed.getAttribute("options"));
};

/** Called by the server to set the attribute. */
zkMenu3.setAttr = function (ed, name, value) {
	switch (name) {
	case "options":
		ed.options = JSON.parse (value);
		return true;
	}
	return false;
};

zkMenu3.cleanup=function(_2){
};


zkMenu3.openRef=function(ed) {
	var options = ed.options;
	ed.modal = document.createElement("div");
	ed.modal.setAttribute("class", "menu3modal");
	ed.modal.master = ed;
	zk.listen(ed.modal, "click", zkMenu3.onCloseMenu);
	document.body.appendChild(ed.modal);
	zkMenu3.open(ed, ed, options, true);
}

zkMenu3.populate=function(ed, pos, data) {
	var v = pos.split(" ");
	var option = ed.options [v[0]];
	for (var i = 1; i < v.length; i++) {
		option = option.options[v[i]];
	}
	option.dynamic = false;
	option.options = JSON.parse(data);
	if (ed.currentPosition == pos) {
		var menu = ed.currentTR.parentElement/*table*/.parentElement;
		menu.openOption = zkMenu3.open(ed, ed.currentTR, option.options, false);
	}
}

zkMenu3.open=function(ed, parent, options, first) {
	var w = document.createElement("div");
	w.master = ed;
	var position;
	if ( first )
	{
		var pos = parent.previousElementSibling.getClientRects()[0];
		w.style.left = ""+(pos.x) + "px";
		w.style.top = ""+(pos.y + pos.height) + "px";
		position = "";
	} else {
		var pos = parent.getClientRects()[0];
		if (pos.x + pos.width + 200 > window.innerWidth) {
			w.style.left = ""+(pos.x + 64) + "px";
			w.style.top = ""+(pos.y + pos.height - 10) + "px";
		} else {
			w.style.left = ""+(pos.x + pos.width) + "px";
			w.style.top = ""+(pos.y + pos.height - 24) + "px";
		}
		position = parent.position + " ";
	}
	ed.modal.appendChild(w);
	w.position = position;
	w.setAttribute("class", "menu3");
	w.style.position="fixed";
	var t = document.createElement("table");
	w.appendChild(t);
	for (var i = 0; i < options.length; i++) {
		var option = options[i];
		var tr = document.createElement("tr");
		tr.option = option;
		tr.position = position + i;
		t.appendChild(tr);
		var od = document.createElement("td");
		var img = document.createElement("img");
		img.setAttribute("src", option.img);
		od.appendChild(img);
		tr.appendChild(od);
		var td = document.createElement("td");
		tr.appendChild(td);
		if (option.options || option.dynamic) {
			od.setAttribute("class", "menu3menu");
			zk.listen(tr, "mouseover", zkMenu3.onMouseOver);
			td.innerHTML = ("&#x279c;");
			if (option.full_url) {
				od.setAttribute("class", "menu3item");
				var a = document.createElement("a");
				a.href = option.full_url;
				a.appendChild(document.createTextNode(option.label));				
				zk.listen(a, "click", zkMenu3.prevent);
				od.appendChild(a);
				zk.listen(tr, "click", zkMenu3.onClick);
			} else {
				od.appendChild(document.createTextNode(option.label));				
			}
		} else {
			od.setAttribute("class", "menu3item");
			var a = document.createElement("a");
			a.href = option.full_url;
			a.appendChild(document.createTextNode(option.label));
			zk.listen(tr, "mouseover", zkMenu3.onMouseOver2);
			zk.listen(a, "click", zkMenu3.prevent);
			od.appendChild(a);
			zk.listen(tr, "click", zkMenu3.onClick);
		}
	}
	return w;
} 

zkMenu3.closeMenu=function(menu) {
	if (menu.openOption) {
		zkMenu3.closeMenu(menu.openOption);
		menu.openOption.remove();
		menu.openOption = null;
	}
}

zkMenu3.onClick=function(ev) {
	var option = ev.currentTarget;
	var menu = option.parentElement/*table*/.parentElement;
	zkau.send ({uuid: menu.master.id, cmd: "onSelect", data : [option.option.url, ev.ctrlKey]}, 5);		
} 

zkMenu3.onMouseOver=function(ev) {
	var option = ev.currentTarget;
	var menu = option.parentElement/*table*/.parentElement;
	zkMenu3.closeMenu(menu);
	menu.master.currentTR = option;
	menu.master.currentPosition = option.position;
	if (option.option.dynamic) {
		if (!option.option.inProgress) {
			option.option.inProgress = true; // Prevent new refresh
			zkau.send ({uuid: menu.master.id, cmd: "onPopulate", data : [option.position]}, 5);		
		}
	} else {		
		menu.openOption = zkMenu3.open(menu.master, option, option.option.options, false);
	}
} 

zkMenu3.onMouseOver2=function(ev) {
	var option = ev.currentTarget;
	var menu = option.parentElement/*table*/.parentElement;
	zkMenu3.closeMenu(menu);
	menu.master.currentTR = option;
	menu.master.currentPosition = option.position;
} 

zkMenu3.onCloseMenu=function(ev) {
	var modal = ev.currentTarget;
	modal.master.modal = null;
	modal.remove();
} 

zkMenu3.prevent=function(ev) {
	ev.preventDefault();
}


