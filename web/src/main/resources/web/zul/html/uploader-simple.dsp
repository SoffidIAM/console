<%--
attributesearchbox.dsp

Copyright (C) 2017 Soffid IAM. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
--%><%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %>
<c:set var="self" value="${requestScope.arg.self}"/>
<div id="${self.uuid}"${self.outerAttrs}${self.innerAttrs} z.type="uploader.uploader.Uploader">
	<input class="inputfile" id="${self.uuid}!input" type="file" id="file" size="28" name="file" class="inputfile" ${self.multipleTag}/>
	<label for="${self.uuid}!input">${c:l('fileupload.selectFile')}</label>
</div>
