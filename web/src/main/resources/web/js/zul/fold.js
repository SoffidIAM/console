zkFold={};

zkFold.init=function(ed) {
	var header = document.getElementById(ed.id+"!header");
	header.addEventListener("click", zkFold.foldunfold);
	if (ed.getAttribute("_folded") == "false")
		ed.classList.add("unfold");
};

zkFold.foldunfold=function(ev) {
	var f = ev.currentTarget.parentElement;
	if (f.classList.contains("unfold"))
	{
		f.classList.remove("unfold");
		zkau.send ({uuid: f.id, cmd: "onFold", data : []}, 5);		
	}
	else
	{
		f.classList.add("unfold");
		zkau.send ({uuid: f.id, cmd: "onUnfold", data : []}, 5);		
	}
};

zkFold.cleanup=function(_2){
};

zkFold.setAttr = function (ed, name, value) {
	if (name == "label") {
		var header = document.getElementById(ed.id+"!header");
		var i = header.firstElementChild.nextElementSibling;
		i.innerText = value;
		return true;		
	}
	else if (name == "folded") {
		if (value == "true")
		{
			f.classList.remove("unfold");
		}
		else
		{
			f.classList.add("unfold");
		}
		return true;		
	}
	else
		return false;
}

