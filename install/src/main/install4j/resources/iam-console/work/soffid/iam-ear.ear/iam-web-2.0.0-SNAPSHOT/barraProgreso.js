function Boot_progressbox(id, msg, x, y) {
	var myWidth = 0, myHeight = 0;
	if (typeof (window.innerWidth) == 'number') {
		// Non-IE
		myWidth = window.innerWidth;
		myHeight = window.innerHeight;
	} else if (document.documentElement
			&& (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
		// IE 6+ in 'standards compliant mode'
		myWidth = document.documentElement.clientWidth;
		myHeight = document.documentElement.clientHeight;
	} else if (document.body
			&& (document.body.clientWidth || document.body.clientHeight)) {
		// IE 4 compatible
		myWidth = document.body.clientWidth;
		myHeight = document.body.clientHeight;
	}

	x = (myWidth - 180) / 2;
	y = (myHeight - 70) / 2;
	
	var _path = location.pathname;
	var ruta = _path.substr(0,_path.lastIndexOf("/"));

	var html = '<div id="'+ id + '" style="left:'+ x+ 'px;top:'+ y+ 'px;'
			+ 'position:absolute;z-index:79000;background-color:#FFFFFF;color:#102B6D;'
			+ 'white-space:nowrap;border:5px solid #83B5F7;padding:8px 10px;">'
			+ '<img src="'+ruta+'/zkau/web/img/spacer.gif" alt="..." class="z-loading-icon"/>'
			+ " S'està processant..." + '</div>';
	document.body.insertAdjacentHTML("afterbegin", html);
	return $e(id);
}