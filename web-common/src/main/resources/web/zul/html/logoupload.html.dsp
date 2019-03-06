<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %><%--
fileupload.html.dsp

{{IS_NOTE
	Purpose:
		The content of the inline frame of the fileupload modal dialog
		(fileupload.html.zul)
	Description:
		
	History:
		Thu Jul 21 11:37:28     2005, Created by tomyeh
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
--%><%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<title>Upload</title>
${z:outDeviceStyleSheets('ajax')}
<%-- We cannot use ${z:outLangStyleSheets()} since Executions.getCurrent()
	is not available for this page.
 --%>
<script type="text/javascript">
<!--
function submitUpload() {
	var wndid = '${param.uuid}';
	var img = parent.$e(wndid + '!img');
	if (img) img.parentNode.removeChild(img);
		<%-- Bug 1578549: we have to remove the closable button first, since
			it might mis-behave if user clicks it after submitting
		--%>

	parent.zkau.beginUpload(wndid);
}
function cancelUpload() {
	parent.setTimeout("zkau.endUpload();zkau.sendOnClose('${param.uuid}');", 100);
}
function init() {
	var el = document.getElementById("form");
	el.action = parent.zk.getUpdateURI(
		"/upload?dtid=${param.dtid}&uuid=${param.uuid}");
	if (parent.zk.ie) {
		var cave = parent.$e("${param.uuid}!cave");
		if (cave)
			document.body.style.backgroundColor = parent.Element.getStyle(cave, "background-color") || "";
	}
	parent.zk.focus(document.getElementById("file"));
}
function onDocKeydown(evt) {
	if (!evt) evt = window.event;
	if (parent.Event.keyCode(evt) == 27)
		cancelUpload();
}
function addUpload(img) {
	img.src = parent.zk.rename(img.src, "delete");
	img.onclick = function () {deleteUpload(img)};
	
	// due to the runtime error of IE, we cannot use the tr.innerHTML method.  
	var table = parent.$parentByTag(img, "TABLE"), 
		tr = table.insertRow(table.rows.length),
		td = tr.insertCell(0);
	td.innerHTML = table.rows.length;
	td.align = "right";
	tr.insertCell(1).innerHTML = '<input class="file" type="file" id="file" name="file"/>' +
		'<img src="${c:encodeURL('~./zul/img/add.gif')}" onclick="addUpload(this);" />';
	adjustHgh(table);
}
function deleteUpload(img) {
	var table = parent.$parentByTag(img, "TABLE");
	table.deleteRow(img.parentNode.parentNode.rowIndex);
	for (var i = 0, j = table.rows.length; i < j; ++i)
		table.rows[i].cells[0].innerHTML = i+1;
	adjustHgh(table);
}
function adjustHgh(table) {
	table.parentNode.style.height = table.rows.length > 3 ? "100px" : "";
	if (parent.zk.opera) table.parentNode.style.overflow = "auto";
}
parent.zk.listen(document, "keydown", onDocKeydown);
// -->
</script> 
</head>
<body onload="init()">
	<form id="form" enctype="multipart/form-data" method="POST" onsubmit="submitUpload()">
	<%-- We have to encode dtid and uuid in action rather than hidden fields,
		because 1) dtid must be ready before parsing multi-part requests.
		2) parsing multi-part might fail
	--%>
	<%-- change the following if you want the return URI to be different from the default
	<input type="hidden" name="nextURI" value="~./zul/html/fileupload-done.html.dsp"/>
	--%>
	<input type="hidden" name="native" value="${param.native}"/>

	<input class="file" type="file" id="file" size="28" name="file"/>
	<input class="button" type="submit" value="${c:l('mesg:org.zkoss.zul.mesg.MZul:UPLOAD_SUBMIT')}" onclick="parent.zk.progress()"/>
	</form>
</body>
</html>
