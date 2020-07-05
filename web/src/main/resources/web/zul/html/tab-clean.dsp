<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>
<c:set var="self" value="${requestScope.arg.self}"/>
<c:set var="suffix" value="-sel" if="${self.selected}"/>
<c:set var="suffix" value="-uns" unless="${self.selected}"/>
<c:set var="look" value="${self.tabbox.tabLook}-"/>
<c:set var="hghStyle" value="height:${self.height}" unless="${empty self.height}"/>
<td id="${self.uuid}" z.type="Tab"${self.outerAttrs}${self.innerAttrs} z.sel="${self.selected}" z.box="${self.tabbox.uuid}" z.panel="${self.linkedPanel.uuid}" z.disabled="${self.disabled}">
<a href="javascript:;" id="${self.uuid}!a">${self.imgTag}<c:out value="${self.label}"/></a>
</td>
