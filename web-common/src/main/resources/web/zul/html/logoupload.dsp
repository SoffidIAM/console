<%--
fileupload.dsp

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Thu May 10 16:06:58     2007, Created by tomyeh
}}IS_NOTE

{{IS_RIGHT
}}IS_RIGHT
--%><%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<c:set var="self" value="${requestScope.arg.self}"/>
<div id="${self.uuid}"${self.outerAttrs}${self.innerAttrs}>
<iframe style="width:100%;" frameborder="0" src="${c:encodeURL('~./zul/html/logoupload.html.dsp')}?dtid=${self.desktop.id}&amp;uuid=${self.uuid}&amp;max=${self.number}&amp;native=${self.native}">
</iframe>
</div>
