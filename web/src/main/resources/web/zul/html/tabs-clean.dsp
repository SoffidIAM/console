<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<%@ taglib uri="http://www.zkoss.org/dsp/zk/core" prefix="z" %>
<c:set var="self" value="${requestScope.arg.self}"/>
<div id="${self.uuid}" z.type="zul.tab.Tabs"${self.outerAttrs}${self.innerAttrs}>
<table border="0" cellpadding="0" cellspacing="0">
<tr valign="bottom">
	<c:forEach var="child" items="${self.children}">
	${z:redraw(child, null)}
	</c:forEach>

<td style="display:none" id="${self.uuid}!child"></td><%-- bookmark for adding children --%>

<%-- postfix column  --%>
<td></td>
	</tr>
</table>
</div>
