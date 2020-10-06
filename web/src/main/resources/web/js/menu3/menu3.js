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

zkMenu3.open=function(ed, parent, options, first) {
	var w = document.createElement("div");
	w.master = ed;
	if ( first )
	{
		var pos = parent.previousElementSibling.getClientRects()[0];
		w.style.left = ""+(pos.x) + "px";
		w.style.top = ""+(pos.y + pos.height) + "px";
	} else {
		var pos = parent.getClientRects()[0];
		w.style.left = ""+(pos.x + pos.width) + "px";
		w.style.top = ""+(pos.y + pos.height - 24) + "px";
	}
	ed.modal.appendChild(w);
	w.setAttribute("class", "menu3");
	w.style.position="fixed";
	var t = document.createElement("table");
	w.appendChild(t);
	for (var i = 0; i < options.length; i++) {
		var option = options[i];
		var tr = document.createElement("tr");
		tr.option = option;
		t.appendChild(tr);
		var od = document.createElement("td");
		var img = document.createElement("img");
		img.setAttribute("src", option.img);
		od.appendChild(img);
		tr.appendChild(od);
		var td = document.createElement("td");
		tr.appendChild(td);
		if (option.options) {
			od.setAttribute("class", "menu3menu");
			od.appendChild(document.createTextNode(option.label));
			zk.listen(tr, "mouseover", zkMenu3.onMouseOver);
			td.innerHTML = ("&#x279c;");
		} else {
			od.setAttribute("class", "menu3item");
			var a = document.createElement("a");
			a.href = option.full_url;
			a.appendChild(document.createTextNode(option.label));
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
	menu.openOption = zkMenu3.open(menu.master, option, option.option.options, false);
} 

zkMenu3.onCloseMenu=function(ev) {
	var modal = ev.currentTarget;
	modal.master.modal = null;
	modal.remove();
} 

zkMenu3.prevent=function(ev) {
	ev.preventDefault();
}


