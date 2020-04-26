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
parent.zk.listen(document, "keydown", onDocKeydown);
// -->
</script>
</head>
<body onload="init()">
	<form id="form" enctype="multipart/form-data" method="POST" onsubmit="submitUpload()" class="upload-form">
	<%-- We have to encode dtid and uuid in action rather than hidden fields,
		because 1) dtid must be ready before parsing multi-part requests.
		2) parsing multi-part might fail
	--%>
	<%-- change the following if you want the return URI to be different from the default
	<input type="hidden" name="nextURI" value="~./zul/html/fileupload-done.html.dsp"/>
	--%>
	<input type="hidden" name="native" value="${param.native}"/>

<c:set var="unlimited" value="${param.max < 0 ? true: false}"/>
<c:set var="maxcnt" value="${empty param.max ? 1 : unlimited ? (param.max/-1) : param.max}"/>
		<input class="inputfile" type="file" id="file" size="28" name="file" class="inputfile" onchange="parent.zk.progress(); form.submit();"/>
		<label for="file">${c:l('fileupload.selectFile')}</label>
		<input class="button" type="submit" value="${c:l('mesg:org.zkoss.zul.mesg.MZul:UPLOAD_SUBMIT')}" onclick="parent.zk.progress()"/>
		<input class="button" type="button" value="${c:l('mesg:org.zkoss.zul.mesg.MZul:UPLOAD_CANCEL')}" onclick="cancelUpload()"/>
	</form>
</body>
</html>
