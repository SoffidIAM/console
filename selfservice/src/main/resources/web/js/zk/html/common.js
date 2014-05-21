if(!window.anima){String.prototype.startsWith=function(e){return this.substring(0,e.length)==e};String.prototype.endsWith=function(e){return this.substring(this.length-e.length)==e};String.prototype.trim=function(){var e=0,t=this.length,n=t-1;while(e<t&&this.charAt(e)<=" ")++e;while(n>=e&&this.charAt(n)<=" ")--n;return e>n?"":this.substring(e,n+1)};String.prototype.skipWhitespaces=function(e){for(var t=this.length;e<t;++e){var n=this.charAt(e);if(n!=" "&&n!="	"&&n!="\n"&&n!="\r")break}return e};String.prototype.nextWhitespace=function(e){for(var t=this.length;e<t;++e){var n=this.charAt(e);if(n==" "||n=="	"||n=="\n"||n=="\r")break}return e};Array.prototype.remove=function(e){for(var t=0,n=this.length;t<n;++t){if(e==this[t]){this.splice(t,1);return true}}return false};Array.prototype.contains=function(e){for(var t=0,n=this.length;t<n;++t){if(e==this[t])return true}return false};function z_fmsubm(e,t,n){var r=this._submfns;for(var i=0,s=r?r.length:0;i<s;++i)r[i].apply(this,arguments);return this._ogsubm(e,t,n)}if(zk.ie){zk.fixSubmit=function(e){e._ogsubm=e.submit;e.submit=z_fmsubm};zk._newElem=document.createElement;document.createElement=function(e){var t=zk._newElem(e);if(e.toUpperCase()=="FORM")zk.fixSubmit(t);return t}}else{HTMLFormElement.prototype._ogsubm=HTMLFormElement.prototype.submit;HTMLFormElement.prototype.submit=z_fmsubm}zk.override=function(e,t,n,r){n[t]=e[t];e[t]=r};zk.repaint=function(e,t){zk.addClass(e,"z-repaint");setTimeout(function(){zk.rmClass(e,"z-repaint")},t>0?t:1)};zk.fixOverflow=zk.ie?function(e){if(e){var t=e.style.overflow;e.style.overflow="hidden";if(e.offsetWidth){}e.style.overflow=t}}:zk.voidf;zk.redraw=function(e){if(e){e=$outer(e);zkau.cmd1.outer(e.id,e,zk.getOuterHTML(e))}};zk.getOuterHTML=function(e){if(e.outerHTML)return e.outerHTML;var t=document.createElement("DIV");var n=e.cloneNode(true);t.appendChild(n);return t.innerHTML};zk.alert=function(e){zk.alerting=true;try{alert(e)}finally{try{zk.alerting=false}catch(t){}}};zk.confirm=function(e){zk.alerting=true;try{return confirm(e)}finally{try{zk.alerting=false}catch(t){}}};zk.hasClass=function(e,t){var n=e.className;return n&&(" "+n+" ").indexOf(" "+t+" ")!=-1};zk.addClass=function(e,t,n){if(n==false){zk.rmClass(e,t);return}if(!zk.hasClass(e,t)){var r=e.className;if(r.length)r+=" ";e.className=r+t}};zk.rmClass=function(e,t,n){if(n==false){zk.addClass(e,t);return}if(zk.hasClass(e,t)){var r=new RegExp("(?:^|\\s+)"+t+"(?:\\s+|$)","g");e.className=e.className.replace(r," ")}};zk.setOffsetHeight=function(e,t){t=t-$int(Element.getStyle(e,"padding-top"))-$int(Element.getStyle(e,"padding-bottom"))-$int(Element.getStyle(e,"margin-top"))-$int(Element.getStyle(e,"margin-bottom"))-$int(Element.getStyle(e,"border-top-width"))-$int(Element.getStyle(e,"border-bottom-width"));e.style.height=(t>0?t:0)+"px"};zk.offsetWidth=function(e){if(!e)return 0;if(!zk.safari||$tag(e)!="TR")return e.offsetWidth;var t=0;for(var n=e.cells.length;--n>=0;)t+=e.cells[n].offsetWidth;return t};zk.offsetHeight=function(e){if(!e)return 0;if(!zk.safari||$tag(e)!="TR")return e.offsetHeight;var t=0;for(var n=e.cells.length;--n>=0;){var r=e.cells[n].offsetHeight;if(r>t)t=r}return t};zk.offsetTop=function(e){if(!e)return 0;if(zk.safari&&$tag(e)==="TR"&&e.cells.length)e=e.cells[0];return e.offsetTop};zk.offsetLeft=function(e){if(!e)return 0;if(zk.safari&&$tag(e)==="TR"&&e.cells.length)e=e.cells[0];return e.offsetLeft};zk.borders={l:"border-left-width",r:"border-right-width",t:"border-top-width",b:"border-bottom-width"};zk.paddings={l:"padding-left",r:"padding-right",t:"padding-top",b:"padding-bottom"};zk.sumStyles=function(e,t,n){var r=0;for(var i=0,s=t.length;i<s;i++){var o=$int(Element.getStyle(e,n[t.charAt(i)]));if(!isNaN(o))r+=o}return r};zk.revisedSize=function(e,t,n){var r="lr";if(n)r="tb";t-=zk.sumStyles(e,r,zk.borders)+zk.sumStyles(e,r,zk.paddings);if(t<0)t=0;return t};zk.revisedOffset=function(e,t){if(!t){if(e.getBoundingClientRect){var n=e.getBoundingClientRect();return[n.left+zk.innerX()-e.ownerDocument.documentElement.clientLeft,n.top+zk.innerY()-e.ownerDocument.documentElement.clientTop]}t=Position.cumulativeOffset(e)}var r=Position.realOffset(e);r[0]-=zk.innerX();r[1]-=zk.innerY();return[t[0]-r[0],t[1]-r[1]]};if(zk.safari){zk._oldposofs=Position.positionedOffset;Position.positionedOffset=function(e){if($tag(e)==="TR"&&e.cells.length)e=e.cells[0];return zk._oldposofs(e)}}if(zk.gecko||zk.safari){zk._oldcumofs=Position.cumulativeOffset;Position.cumulativeOffset=function(e){if(zk.safari&&$tag(e)==="TR"&&e.cells.length)e=e.cells[0];var t;if(!$visible(e)&&!zk.offsetWidth(e)){e.style.display="";t=zk._oldcumofs(e);e.style.display="none"}else{t=zk._oldcumofs(e)}return t}}zk.center=function(e,t){var n=zk.offsetWidth(e),r=zk.offsetHeight(e);if((!n||!r)&&!$visible(e)){e.style.top="-10000px";e.style.display="block";n=zk.offsetWidth(e);r=zk.offsetHeight(e),e.style.display="none"}var i=zk.innerX(),s=zk.innerY();var o,u,a,f;n=zk.innerWidth()-n;if(!t)o=i+n/2;else if(t.indexOf("left")>=0)o=i;else if(t.indexOf("right")>=0)o=i+n-1;else if(t.indexOf("center")>=0)o=i+n/2;else{o=0;a=true}r=zk.innerHeight()-r;if(!t)u=s+r/2;else if(t.indexOf("top")>=0)u=s;else if(t.indexOf("bottom")>=0)u=s+r-1;else if(t.indexOf("center")>=0)u=s+r/2;else{u=0;f=true}if(o<i)o=i;if(u<s)u=s;var l=zk.toStyleOffset(e,o,u);if(!a)e.style.left=l[0]+"px";if(!f)e.style.top=l[1]+"px"};zk.getDimension=function(e){var t=zk.offsetWidth(e),n;if(!$visible(e)&&!t){var r=e.style.left==""||e.style.left=="auto";if(r)e.style.left="0";var i=e.style.top==""||e.style.top=="auto";if(i)e.style.top="0";e.style.display="";t=zk.offsetWidth(e);n=zk.offsetHeight(e);e.style.display="none";if(r)e.style.left="";if(i)e.style.top=""}else{n=zk.offsetHeight(e)}return[t,n]};zk.position=function(e,t,n){var r=zk.getDimension(e);var i=r[0],s=r[1];r=zk.revisedOffset(t);var o,u;var a=zk.innerX(),f=zk.innerY(),l=a+zk.innerWidth(),c=f+zk.innerHeight();if(n=="end_before"){o=r[0]+zk.offsetWidth(t);u=r[1];if(zk.ie){var h=$int(Element.getStyle(t,"margin-top"));if(!isNaN(h))u+=h;h=$int(Element.getStyle(t,"margin-right"));if(!isNaN(h))o+=h}if(o+i>l)o=r[0]-i;if(u+s>c)u=c-s}else{o=r[0];u=r[1]+zk.offsetHeight(t);if(zk.ie){var h=$int(Element.getStyle(t,"margin-bottom"));if(!isNaN(h))u+=h;h=$int(Element.getStyle(t,"margin-left"));if(!isNaN(h))o+=h}if(u+s>c)u=r[1]-s;if(o+i>l)o=l-i}if(o<a)o=a;if(u<f)u=f;r=zk.toStyleOffset(e,o,u);e.style.left=r[0]+"px";e.style.top=r[1]+"px"};zk.getVflexHeight=function(e){var t=e.parentNode.clientHeight;if(zk.ie6Only){var n=e.parentNode;var r=n.style.height;if(r&&r.endsWith("px")){r=zk.revisedSize(n,$int(r),true);if(r&&r<t)t=r}}for(var i=e,s;s=i.previousSibling;){if(s.offsetHeight&&$visible(s))t-=s.offsetHeight;i=s}for(var i=e,s;s=i.nextSibling;){if(s.offsetHeight&&$visible(s))t-=s.offsetHeight;i=s}return t};zk.getStyleOffset=function(e){return[$int(e.style.left),$int(e.style.top)]};zk.toStyleOffset=function(e,t,n){var r=e.style.left,i=e.style.top;if(zk.opera||zk.air){e.style.left=e.style.top="0"}else{if(e.style.left==""||e.style.left=="auto")e.style.left="0";if(e.style.top==""||e.style.top=="auto")e.style.top="0"}var s=Position.cumulativeOffset(e);var o=zk.getStyleOffset(e);s=[t-s[0]+o[0],n-s[1]+o[1]];e.style.left=r;e.style.top=i;return s};zk.isOverlapped=function(e,t){return zk.isOffsetOverlapped(Position.cumulativeOffset(e),[e.offsetWidth,e.offsetHeight],Position.cumulativeOffset(t),[t.offsetWidth,t.offsetHeight])};zk.isOffsetOverlapped=function(e,t,n,r){var i=e[0],s=t[0]+i,o=e[1],u=t[1]+o;var a=n[0],f=r[0]+a,l=n[1],c=r[1]+l;return a<=s&&f>=i&&l<=u&&c>=o};zk.isRealVisible=function(e,t){if(!e)return false;do{if(!$visible(e,t))return false}while(e=$parent(e));return true};zk.isVisible=$visible;zk.focusDown=function(e){return zk._focusDown(e,["INPUT","SELECT","BUTTON"],true)||zk._focusDown(e,["A"],false)};zk._focusDown=function(e,t,n){if(!e)return false;if(e.focus){var r=$tag(e);if(t.contains(r)){zk.focus(e);return true}if(n&&r=="A"){for(var i=e;i=$parent(i);){if(getZKAttr(i,"type")){zk.focus(e);return true}}}}for(e=e.firstChild;e;e=e.nextSibling){if(zk._focusDown(e,t))return true}return false};zk.asyncFocusDown=function(e,t){++zk.inAsyncFocus;setTimeout("--zk.inAsyncFocus; if (!zk.focusDown($e('"+e+"'))) window.focus();",t>0?t:0)};zk.asyncFocus=function(e,t){++zk.inAsyncFocus;setTimeout("--zk.inAsyncFocus; zk.focus($e('"+e+"'));",t>0?t:0)};zk.inAsyncFocus=0;zk.focus=function(e){if(e&&e.focus)try{e.focus()}catch(t){setTimeout(function(){try{e.focus()}catch(t){setTimeout(function(){try{e.focus()}catch(t){}},100)}},0)}};zk.asyncSelect=function(e,t){++zk.inAsyncSelect;setTimeout("--zk.inAsyncSelect; zk.select($e('"+e+"'));",t>0?t:0)};zk.inAsyncSelect=0;zk.select=function(e){if(e&&e.select)try{e.select()}catch(t){setTimeout(function(){try{e.select()}catch(t){}},0)}};zk.getSelectionRange=function(e){try{if(document.selection!=null&&e.selectionStart==null){var t=document.selection.createRange();var n=e.createTextRange();var r="";if(e.type.toLowerCase()=="text"){r=n.duplicate()}else{r=t.duplicate();r.moveToElementText(e)}r.setEndPoint("EndToEnd",t);var i=r.text.length-t.text.length;return[i,i+t.text.length]}else{return[e.selectionStart,e.selectionEnd]}}catch(s){return[0,0]}};zk.insertAfter=function(e,t){var n=t.nextSibling;if(n)t.parentNode.insertBefore(e,n);else t.parentNode.appendChild(e)};zk.insertBefore=function(e,t){t.parentNode.insertBefore(e,t)};zk.insertHTMLBefore=function(e,t){if(zk.ie||zk.opera){switch($tag(e)){case"TD":case"TH":case"TR":case"CAPTION":case"COLGROUP":case"TBODY":case"THEAD":case"TFOOT":var n=zk._tblCreateElements(t);var r=e.parentNode;for(var i=0,s=n.length;i<s;++i)r.insertBefore(n[i],e);return}}e.insertAdjacentHTML("beforeBegin",t)};zk.insertHTMLBeforeEnd=function(e,t){if(zk.ie||zk.opera){var n=$tag(e);switch(n){case"TABLE":case"TR":case"TBODY":case"THEAD":case"TFOOT":case"COLGROUP":var r=zk._tblCreateElements(t);if(n=="TABLE"&&r.length&&$tag(r[0])=="TR"){var i=e.tBodies;if(!i||!i.length){i=document.createElement("TBODY");e.appendChild(i);e=i}else{e=i[i.length-1]}}for(var s=0,o=r.length;s<o;++s)e.appendChild(r[s]);return}}e.insertAdjacentHTML("beforeEnd",t)};zk.insertHTMLAfter=function(e,t){if(zk.ie||zk.opera){switch($tag(e)){case"TD":case"TH":case"TR":case"CAPTION":case"TBODY":case"THEAD":case"TFOOT":case"COLGROUP":case"COL":var n=zk._tblCreateElements(t);var r=e.nextSibling;var i=e.parentNode;for(var s=0,o=n.length;s<o;++s)if(r!=null)i.insertBefore(n[s],r);else i.appendChild(n[s]);return}}e.insertAdjacentHTML("afterEnd",t)};zk.setInnerHTML=function(e,t){if(zk.ie||zk.opera){var n=$tag(e);if(n=="TR"||n=="TABLE"||n=="TBODY"||n=="THEAD"||n=="TFOOT"||n=="COLGROUP"||n=="COL"){var r=zk._tblCreateElements(t);if(n=="TABLE"&&r.length&&$tag(r[0])=="TR"){var i=e.tBodies;if(!i||!i.length){i=document.createElement("TBODY");e.appendChild(i);e=i}else{e=i[0];while(e.nextSibling)e.parentNode.removeChild(e.nextSibling)}}while(e.firstChild)e.removeChild(e.firstChild);for(var s=0,o=r.length;s<o;++s)e.appendChild(r[s]);return}}e.innerHTML=t};zk.setOuterHTML=function(e,t){var n=e.parentNode;if(zk.ie||zk.opera){var r=$tag(e);if(r=="TD"||r=="TH"||r=="TABLE"||r=="TR"||r=="CAPTION"||r=="TBODY"||r=="THEAD"||r=="TFOOT"||r=="COLGROUP"||r=="COL"){var i=zk._tblCreateElements(t);var s=e.nextSibling;n.removeChild(e);for(var o=0,u=i.length;o<u;++o)if(s)n.insertBefore(i[o],s);else n.appendChild(i[o])}else{e.outerHTML=t}}else{var a=e.ownerDocument.createRange();a.setStartBefore(e);var f=a.createContextualFragment(t);n.replaceChild(f,e)}for(n=n.firstChild;n;n=n.nextSibling){if($tag(n)){if(!$visible(n))zk._hideExtr(n);else zk._showExtr(n);break}}};zk.nextSibling=function(e,t){while(e&&(e=e.nextSibling)!=null&&$tag(e)!=t);return e};zk.previousSibling=function(e,t){while(e&&(e=e.previousSibling)!=null&&$tag(e)!=t);return e};zk.parentNode=function(e,t){while(e&&(e=$parent(e))&&$tag(e)!=t);return e};zk.firstChild=function(e,t,n){for(var r=e.firstChild;r;r=r.nextSibling)if($tag(r)==t)return r;if(n){for(var r=e.firstChild;r;r=r.nextSibling){var i=zk.firstChild(r,t,n);if(i)return i}}return null};zk.isAncestor=function(e,t,n){if(n&&$uuid(e)==$uuid(t))return true;e=$e(e);t=$e(t);for(;t;t=$parent(t))if(e==t)return true;return false};zk.isAncestorX=function(e,t,n,r){for(var i=0,s=t.length;i<s;++i)if(zk.isAncestor(e,t[i],n))return true;if(r){var o=$e(getZKAttr(e,"owner"));return o&&zk.isAncestorX(o,t,n,r)}return false};zk.isAncestorX1=function(e,t,n,r){for(var i=0,s=e.length;i<s;++i)if(zk.isAncestor(e[i],t,n))return true;if(r){var o=$e(getZKAttr(t,"owner"));return o&&zk.isAncestorX1(e,o,n,r)}return false};zk.tagOfHtml=function(e){if(!e)return"";var t=e.indexOf(">"),n=e.lastIndexOf("<");if(t<0||n<0){zk.error("Unknown tag: "+e);return""}var r=e.substring(0,t);t=r.indexOf("<")+1;t=r.skipWhitespaces(t);n=r.nextWhitespace(t);return r.substring(t,n).toUpperCase()};if(zk.ie||zk.opera){zk._tblCreateElements=function(e){var t;e=e.trim();var n=zk.tagOfHtml(e);switch(n){case"TABLE":t=0;break;case"TR":t=2;e="<table>"+e+"</table>";break;case"TH":case"TD":t=3;e="<table><tr>"+e+"</tr></table>";break;case"COL":t=2;e="<table><colgroup>"+e+"</colgroup></table>";break;default:t=1;e="<table>"+e+"</table>";break}var r=document.createElement("DIV");r.innerHTML=e;while(--t>=0)r=r.firstChild;var i=[];for(var s;s=r.firstChild;){var o=$tag(s);if(o==n||o!="TBODY")i.push(s);r.removeChild(s)}return i}}zk.getElementValue=function(e){var t="";for(e=e.firstChild;e;e=e.nextSibling)if(e.data)t+=e.data;return t};if(!zk.ie&&!HTMLElement.prototype.insertAdjacentHTML){HTMLElement.prototype.insertAdjacentHTML=function(e,t){var n;var r=this.ownerDocument.createRange();switch(String(e).toLowerCase()){case"beforebegin":r.setStartBefore(this);n=r.createContextualFragment(t);this.parentNode.insertBefore(n,this);break;case"afterbegin":r.selectNodeContents(this);r.collapse(true);n=r.createContextualFragment(t);this.insertBefore(n,this.firstChild);break;case"beforeend":r.selectNodeContents(this);r.collapse(false);n=r.createContextualFragment(t);this.appendChild(n);break;case"afterend":r.setStartAfter(this);n=r.createContextualFragment(t);zk.insertAfter(n,this);break}}}zk.renType=function(e,t){var n=e.lastIndexOf(";");var r;if(n>=0){r=e.substring(n);e=e.substring(0,n)}else r="";n=e.lastIndexOf(".");if(n<0)n=e.length;var i=e.lastIndexOf("-"),s=e.lastIndexOf("/"),o=n<=s?"":e.substring(n),u=i<=s?n<=s?e:e.substring(0,n):e.substring(0,i);if(t)t="-"+t;else t="";return u+t+o+r};zk.rename=function(e,t){var n=e.lastIndexOf(";");var r;if(n>=0){r=e.substring(n);e=e.substring(0,n)}else r="";n=e.lastIndexOf(".");var i=e.lastIndexOf("/"),s=n<=i?"":e.substring(n);return e.substring(0,i+1)+t+s+r};if(!zk._actg1){zk._actg1=["IFRAME","EMBED","APPLET"];zk._actg2=["A","BUTTON","TEXTAREA","INPUT"];if(zk.ie6Only){zk._actg1.unshift("SELECT")}else zk._actg2.unshift("SELECT");zk.coveredTagnames=zk._actg1;zk._disTags=[];zk._hidCvred=[]}zk.disableAll=function(e){for(var t=0,n=zk._actg1.length;t<n;t++)zk._dsball(e,document.getElementsByTagName(zk._actg1[t]),true);if(zk.dbModal)for(var t=0,r=zk._actg2.length;t<r;t++)zk._dsball(e,document.getElementsByTagName(zk._actg2[t]))};zk._dsball=function(e,t,n){e:for(var r=0,i=t.length;r<i;r++){var s=t[r];if(zk.isAncestor(e,s))continue;for(var o=0,u=zk._disTags.length;o<u;++o){var a=zk._disTags[o];if(a.element==s)continue e}var f=$tag(s),l;if(n){if(!zk.shallHideDisabled(s))continue;l=s.style.visibility;s.style.visibility="hidden"}else if(zk.gecko&&f=="A"){l="h:"+zkau.getStamp(s,"tabIndex")+":"+(s.tabIndex?s.tabIndex:0);s.tabIndex=-1}else{l="d:"+zkau.getStamp(s,"disabled")+":"+s.disabled;s.disabled=true}zk._disTags.push({element:s,what:l})}};zk.shallHideDisabled=function(e){var t=$tag(e);return t!="IFRAME"&&t!="EMBED"&&t!="APPLET"||getZKAttr(e,"autohide")=="true"&&$visible(e,true)};zk.restoreDisabled=function(e){var t=[];for(var n=zk.ie,r=zk._disTags.length;r;--r){var i=zk._disTags.shift();var s=i.element;if(s&&s.tagName){if(e&&!zk.isAncestor(e,s)){t.push(i);continue}var o=i.what;if(o.startsWith("d:")){var u=o.indexOf(":",2);if(o.substring(2,u)==zkau.getStamp(s,"disabled"))s.disabled=o.substring(u+1)=="true"}else if(o.startsWith("h:")){var u=o.indexOf(":",2);if(o.substring(2,u)==zkau.getStamp(s,"href"))s.tabIndex=o.substring(u+1)}else s.style.visibility=o}}zk._disTags=t};zk.hideCovered=function(e){if(!e||e.length==0){var t=zk._hidCvred.length;while(t){var n=zk._hidCvred.shift();if(n.element.style)n.element.style.visibility=n.visibility;--t}return}var r=zk._actg1;for(var i=0,s=r.length;i<s;++i){var o=document.getElementsByTagName(r[i]);e:for(var u=0,a=o.length;u<a;u++){var f=o[u];if(!zk.isRealVisible(f))continue;for(var l=0,c=e.length;l<c;++l){if(zk.isAncestor(e[l],f))continue e}var h=false;if(zk.shallHideDisabled(f))for(var l=0,c=e.length;l<c;++l){if(zk.isOverlapped(e[l],f)){h=true;break}}if(h){for(var l=0,t=zk._hidCvred.length;l<t;++l){if(f==zk._hidCvred[l].element)continue e}zk._hidCvred.push({element:f,visibility:f.style.visibility});f.style.visibility="hidden"}else{for(var l=0,t=zk._hidCvred.length;l<t;++l){if(f==zk._hidCvred[l].element){f.style.visibility=zk._hidCvred[l].visibility;zk._hidCvred.splice(l,1);break}}}}}};zk.resolve=function(e){for(var t=0,n=window;;){var r=e.indexOf(".",t);var i=r>=0?e.substring(t,r):e.substring(t);n=n[i];if(r<0||!n)return n;t=r+1}};zk.setStyle=function(e,t){for(var n=0,r=0;r>=0;n=r+1){r=t.indexOf(";",n);var i=r>=0?t.substring(n,r):t.substring(n);var s=i.indexOf(":");var o,u;if(s<0){o=i.trim();u=""}else{o=i.substring(0,s).trim();u=i.substring(s+1).trim()}if(o)e.style[o.camelize()]=u}};zk.getTextStyle=function(e,t,n){var r="";for(var i=0,s=0;s>=0;i=s+1){s=e.indexOf(";",i);var o=s>=0?e.substring(i,s):e.substring(i);var u=o.indexOf(":");var a=u<0?o.trim():o.substring(0,u).trim();if(a.startsWith("font")||a.startsWith("text")||zk._txtstyles.contains(a)||t&&a=="width"||n&&a=="height")r+=o+";"}return r};if(!zk._txtstyles)zk._txtstyles=["color","background-color","background","white-space"];zk.backupStyle=function(e,t){var n="zk_bk"+t;if(!e.getAttribute(n))e.setAttribute(n,e.style[t]||"_zk_none_")};zk.restoreStyle=function(e,t){if(e&&e.getAttribute&&e.style){var n="zk_bk"+t;var r=e.getAttribute(n);if(r){e.removeAttribute(n);e.style[t]=r=="_zk_none_"?"":r}}};zk.scrollIntoView=function(e,t){if(e&&t){var n=$int(Element.getStyle(t,"padding-top"));var r=t.offsetTop-n;if(r<e.scrollTop){e.scrollTop=r}else{r=3+t.offsetTop+t.offsetHeight-e.scrollTop-e.clientHeight;if(r>0)e.scrollTop+=r}}};zk.go=function(e,t,n){var r=!zk.opera&&!zk.keepDesktop&&window.location.href.indexOf("#")<0;if(r&&e){r=e.indexOf("://")<0&&!e.startsWith("mailto:")&&!e.startsWith("javascript:")&&!e.startsWith("about:")}if(!e){if(r)zk.progress();window.location.reload()}else if(t){if(r)zk.progress();window.location.replace(e)}else if(n){var i=document.createElement("FORM");document.body.appendChild(i);var s=e.indexOf("?");if(s>0){var o=e.substring(s+1);e=e.substring(0,s);zk.queryToHiddens(i,o)}i.name="go";i.action=e;i.method="GET";i.target=n;if(e&&!zk.isNewWindow(e,n)&&r)zk.progress();i.submit()}else{if(r)zk.progress();window.location.href=e}if(r)zk.progressDone()};zk.isNewWindow=function(e,t){return e.startsWith("mailto:")||e.startsWith("javascript:")||t&&t!="_self"};zk.queryToHiddens=function(e,t){for(var n=0;;){var r=t.indexOf("=",n);var i=t.indexOf("&",n);var s,o;if(r<0||r>i&&i>=0){s=i>=0?t.substring(n,i):t.substring(n);o=""}else{s=t.substring(n,r);o=i>=0?t.substring(r+1,i):t.substring(r+1)}zk.newHidden(s,o,e);if(i<0)return;n=i+1}};zk.newFrame=function(e,t,n){var r=$e(e);if(r)return r;if(!t)t=zk.getUpdateURI("/web/img/spacer.gif");var i='<iframe id="'+e+'" name="'+e+'" src="'+t+'"';if(n)i+=' style="'+n+'"';i+="></iframe>";zk.insertHTMLBeforeEnd(document.body,i);return $e(e)};zk.formOf=function(e){for(;e;e=e.parentNode)if($tag(e)=="FORM")return e};zk.newHidden=function(e,t,n){var r=document.createElement("INPUT");r.type="hidden";r.name=e;r.value=t;if(n)n.appendChild(r);return r};zk.ncols=function(e){var t=0;if(e){for(var n=0,r=e.length;n<r;++n){var i=e[n].colSpan;if(i>=1)t+=i;else++t}}return t};zk.cellIndex=function(e){var t=0;if(zk.ie){var n=e.parentNode.cells;for(var r=0,i=n.length;r<i;r++){if(n[r]==e){t=r;break}}}else t=e.cellIndex;return t};zk.ncols=function(e){var t=0;if(e){for(var n=0;n<e.length;++n){var r=e[n].colSpan;if(r>=1)t+=r;else++t}}return t};zk.cpCellWidth=function(e,t,n){if(e==null||t==null||!t.length||!e.cells||!e.cells.length)return;var r=e.cells.length;var i,s=0,o=getZKAttr(n.element,"lastLoadIdx");for(var u=0,a=$int(o)||t.length;u<a;++u){var f=t[u];if(!zk.isVisible(f)||getZKAttr(f,"loaded")=="false")continue;var l=f.cells;var c=zk.ncols(l);var h=l.length==c&&$visible(f);if(h&&c>=r){s=r;i=f;break}if(c>s){i=h?f:null;s=c}else if(c==s&&!i&&h){i=f}}if(!s)return;var p=!i;if(p){i=document.createElement("TR");i.style.height="0px";for(var u=0;u<s;++u)i.appendChild(document.createElement("TD"));t[0].parentNode.appendChild(i)}for(var u=s;--u>=0;)e.cells[u].style.width="";var d=0;for(var u=s;--u>=0;){var v=e.cells[u],m=i.cells[u];if(zk.opera){d+=m.offsetWidth;v.style.width=zk.revisedSize(m,m.offsetWidth)}else{v.style.width=m.offsetWidth+"px";if(s>1){var g=m.offsetWidth-v.offsetWidth;if(g!=0){g+=m.offsetWidth;if(g<0)g=0;v.style.width=g+"px"}}}}if(zk.opera&&getZKAttr(n.element,"fixed")!="true")e.parentNode.parentNode.style.width=d+"px";if(p)i.parentNode.removeChild(i)};zk.formatFixed=function(e,t){var n=""+e;for(var r=t-n.length;--r>=0;)n="0"+n;return n};zk.parseDate=function(e,t,n){if(!t)t="yyyy/MM/dd";var r=new Date;var i=r.getFullYear(),s=r.getMonth(),o=r.getDate(),u=r.getHours(),a=r.getMinutes(),f=r.getSeconds();var l=e.split(/\W+/);for(var c=0,h=0,p=t.length;h<p;++h){var d=t.charAt(h);if(d>="a"&&d<="z"||d>="A"&&d<="Z"){var v=1;for(var m=h;++m<p;++v)if(t.charAt(m)!=d)break;var g;if(m<p){var y=t.charAt(m);g=y=="y"||y=="M"||y=="d"||y=="E"||y=="h"||y=="H"||y=="m"||y=="s"}var b=l[c++];switch(d){case"y":if(g){if(v<=3)v=2;if(b.length>v){l[--c]=b.substring(v);b=b.substring(0,v)}}i=$int(b);if(isNaN(i))return null;if(i<100)i+=i>29?1900:2e3;break;case"M":if(v<=2){if(g&&b.length>2){l[--c]=b.substring(2);b=b.substring(0,2)}s=$int(b)-1;if(isNaN(s))return null}else{for(var w=0;;++w){if(w==12)return null;if(v==3){if(zk.SMON[w].split(/\W+/)[0]==b){s=w;break}}else{if(zk.FMON[w].split(/\W+/)[0]==b){s=w;break}}}}break;case"d":if(g){if(v<2)v=2;if(b.length>v){l[--c]=b.substring(v);b=b.substring(0,v)}}o=$int(b);if(isNaN(o))return null;break;case"H":case"h":if(g){if(v<2)v=2;if(b.length>v){l[--c]=b.substring(v);b=b.substring(0,v)}}u=$int(b);if(isNaN(u))return null;break;case"m":if(g){if(v<2)v=2;if(b.length>v){l[--c]=b.substring(v);b=b.substring(0,v)}}a=$int(b);if(isNaN(a))return null;break;case"s":if(g){if(v<2)v=2;if(b.length>v){l[--c]=b.substring(v);b=b.substring(0,v)}}f=$int(b);if(isNaN(f))return null;break}h=m-1}}var E=new Date(i,s,o,u,a,f);if(n){if(E.getFullYear()!=i||E.getMonth()!=s||E.getDate()!=o)return null;e=e.trim();e=zk._ckDate(zk.SDOW,e);e=zk._ckDate(zk.S2DOW,e);e=zk._ckDate(zk.FDOW,e);e=zk._ckDate(zk.SMON,e);e=zk._ckDate(zk.S2MON,e);e=zk._ckDate(zk.FMON,e);e=zk._ckDate(zk.APM,e);for(var h=e.length;--h>=0;){var d=e.charAt(h);if((d>"9"||d<"0")&&t.indexOf(d)<0)return null}}return E};zk._ckDate=function(e,t){if(t.length)for(var n=e.length;--n>=0;){var r=t.indexOf(e[n]);if(r>=0)t=t.substring(0,r)+t.substring(r+e[n].length)}return t};zk.formatDate=function(e,t){if(!t)t="yyyy/MM/dd";var n="";for(var r=0,i=t.length;r<i;++r){var s=t.charAt(r);if(s>="a"&&s<="z"||s>="A"&&s<="Z"){var o=1;for(var u=r;++u<i;++o)if(t.charAt(u)!=s)break;switch(s){case"y":if(o<=3)n+=zk.formatFixed(e.getFullYear()%100,2);else n+=zk.formatFixed(e.getFullYear(),o);break;case"M":if(o<=2)n+=zk.formatFixed(e.getMonth()+1,o);else if(o==3)n+=zk.SMON[e.getMonth()];else n+=zk.FMON[e.getMonth()];break;case"d":n+=zk.formatFixed(e.getDate(),o);break;case"E":if(o<=3)n+=zk.SDOW[e.getDay()];else n+=zk.FDOW[e.getDay()];break;case"D":n+=zk.dayInYear(e);break;case"d":n+=zk.dayInMonth(e);break;case"w":n+=zk.weekInYear(e);break;case"W":n+=zk.weekInMonth(e);break;case"G":n+="AD";break;case"F":n+=zk.dayOfWeekInMonth(e);break;case"H":case"h":n+=e.getHours();break;case"m":n+=e.getMinutes();break;case"s":n+=e.getSeconds();break;default:n+="1"}r=u-1}else{n+=s}}return n};zk.ms2day=function(e){return Math.round(e/864e5)};zk.dayInYear=function(e,t){if(!t)t=new Date(e.getFullYear(),0,1);return 1+zk.ms2day(e-t)};zk.dayInMonth=function(e){return zk.dayInYear(e,new Date(e.getFullYear(),e.getMonth(),1))};zk.weekInYear=function(e,t){if(!t)t=new Date(e.getFullYear(),0,1);var n=t.getDay();if(n==7)n=0;return 1+Math.floor((zk.ms2day(e-t)+n)/7)};zk.weekInMonth=function(e){return zk.weekInYear(e,new Date(e.getFullYear(),e.getMonth(),1))};zk.dayOfWeekInMonth=function(e){return 1+Math.floor(zk.ms2day(e-new Date(e.getFullYear(),e.getMonth(),1))/7)};zk.getIntAttr=function(e,t){return $int(e.getAttribute(t))};zk.clearSelection=function(){try{if(window["getSelection"]){if(zk.safari){window.getSelection().collapse()}else{window.getSelection().removeAllRanges()}}else if(document.selection){if(document.selection.empty){document.selection.empty()}else if(document.selection.clear){document.selection.clear()}}return true}catch(e){return false}};zk.disableSelection=function(e){e=$e(e);if(e)if(zk.gecko)e.style.MozUserSelect="none";else if(zk.safari)e.style.KhtmlUserSelect="none";else if(zk.ie)e.onselectstart=function(e){if(!e)e=window.event;var t=Event.element(e),n=$tag(t);return n=="TEXTAREA"||n=="INPUT"&&(t.type=="text"||t.type=="password")}};zk.enableSelection=function(e){e=$e(e);if(e)if(zk.gecko)e.style.MozUserSelect="";else if(zk.safari)e.style.KhtmlUserSelect="";else if(zk.ie)e.onselectstart=null};zk.clearSelection=function(){try{if(window["getSelection"]){if(zk.safari)window.getSelection().collapse();else window.getSelection().removeAllRanges()}else if(document.selection){if(document.selection.empty)document.selection.empty();else if(document.selection.clear)document.selection.clear()}}catch(e){}};zk.Float=Class.create();zk.Float.prototype={initialize:function(){},empty:function(){return!this._ftid},close:function(e){if(this._ftid==e)this.closeFloats()},closeFloats:function(){return this._closeFloats(false,zkau._shallCloseBut,arguments)},closeFloatsOnFocus:function(){return this._closeFloats(true,zkau._shallCloseBut,arguments)},closeFloatsOf:function(){return this._closeFloats(false,zkau._shallCloseOf,arguments)},_closeFloats:function(e,t,n){if(this._ftid){var r=$e(this._ftid);if($visible(r)&&getZKAttr(r,"animating")!="hide"&&(!e||t(r,n))){this._close(r);this._ftid=null;return true}}return false},addHideCovered:function(e){if(this._ftid){var t=$e(this._ftid);if(t)e.push(t)}},setFloatId:function(e){this._ftid=e}};zk.Floats=Class.create();zk.Floats.prototype={initialize:function(){this._ftids=[];this._aspps={}},empty:function(){return!this._ftids.length},close:function(e){for(var t=this._ftids.length;t;)if(this._ftids[--t]==e){this.closeFloats();return true}return false},closeFloats:function(){return this._closeFloats(false,zkau._shallCloseBut,arguments)},closeFloatsOnFocus:function(){return this._closeFloats(true,zkau._shallCloseBut,arguments)},closeFloatsOf:function(){return this._closeFloats(false,zkau._shallCloseOf,arguments)},_closeFloats:function(e,t,n){var r;for(var i=this._ftids.length;--i>=0;){var s=this._ftids[i];var o=$e(s);if($visible(o)&&getZKAttr(o,"animating")!="hide"&&(!e&&!this._aspps[s]||t(o,n))){this._ftids.splice(i,1);this._close(o);r=true}}return r},addHideCovered:function(e){for(var t=0,n=this._ftids.length;t<n;++t){var r=$e(this._ftids[t]);if(r)e.push(r)}},getFloatIds:function(){return this._ftids},addFloatId:function(e,t){this._ftids.push(e);if(t)this._aspps[e]=true},removeFloatId:function(e){this._ftids.remove(e);delete this._aspps[e]}};zk.History=Class.create();zk.History.prototype={initialize:function(){this.curbk="";zk.addBeforeInit(function(){zkau.history.checkBookmark();setInterval("zkau.history.checkBookmark()",250)})},bookmark:function(e){if(this.curbk!=e){this.curbk=e;var t=encodeURIComponent(e);window.location.hash=zk.safari||!t?t:"#"+t;this.bkIframe(e)}},checkBookmark:function(){var e=this.getBookmark();if(e!=this.curbk){this.curbk=e;zkau.send({uuid:"",cmd:"onBookmarkChange",data:[e]},50)}},getBookmark:function(){var e=window.location.hash;var t=e.indexOf("#");return t>=0?decodeURIComponent(e.substring(t+1)):""},bkIframe:zk.ie?function(e){var t=zk.getUpdateURI("/web/js/zk/html/history.html",true),n=$e("zk_histy");if(!n)n=zk.newFrame("zk_histy",t,"display:none");if(e)t+="?"+encodeURIComponent(e);n.src=t}:zk.voidf,onHistoryLoaded:zk.ie?function(e){var t=e.indexOf("?");var n=t>=0?e.substring(t+1):"";window.location.hash=n?"#"+n:"";this.checkBookmark()}:zk.voidf};zk.remove=function(e){if(e)Element.remove(e)};zk.doEventStop=function(e){if(!e)e=window.event;Event.stop(e)};zk.setVisible=function(e,t,n){if(n||zk.isRealVisible(e.parentNode,true))zk.show(e,t);else if(t)action.show(e);else action.hide(e)};if(zk.opera)zk.cleanVisibility=function(e){e.style.visibility="visible"};else zk.cleanVisibility=function(e){e.style.visibility="inherit"};zk.show=function(id,bShow){if(bShow==false){zk.hide(id);return}var n=$e(id);if(n){var js=getZKAttr(n,"conshow");if(js){rmZKAttr(n,"conshow");try{eval(js)}finally{setZKAttr(n,"conshow",js)}}else{action.show(n)}}};zk.hide=function(id,bHide){if(bHide==false){zk.show(id);return}var n=$e(id);if(n){var js=getZKAttr(n,"conhide");if(js){rmZKAttr(n,"conhide");try{eval(js)}finally{setZKAttr(n,"conhide",js)}}else{action.hide(n)}}};zk._showExtr=function(e){if(!getZKAttr(e,"float")){var t=$e(e.id+"!chdextr");if(t&&"true"==getZKAttr(t,"coexist")){t.style.display="";t=$e(e.id+"!chdextr2");if(t&&t.style.width!="0"&&t.style.height!="0")t.style.display=""}}};zk._hideExtr=function(e){if(!getZKAttr(e,"float")){var t=$e(e.id+"!chdextr");if(t&&"true"==getZKAttr(t,"coexist")){t.style.display="none";t=$e(e.id+"!chdextr2");if(t)t.style.display="none"}}};comm={};comm.sendClick=function(e,t){e=$outer(e);if(e)zkau.send({uuid:e.id,cmd:"onClick",data:[t],ctl:true})};comm.sendUser=function(e){e=$outer(e);if(e){var t=arguments.length,n;if(t>1){n=[];for(var r=1;r<t;++r)n[r-1]=arguments[r]}zkau.send({uuid:e.id,cmd:"onUser",data:n,ctl:true})}};comm.sendEvent=function(e,t){e=$outer(e);if(e){var n=arguments.length,r;if(n>2){r=[];for(var i=2;i<n;++i)r[i-2]=arguments[i]}zkau.send({uuid:e.id,cmd:t,data:r,ctl:true})}};action={};action.show=function(e,t){var n=$e(e);if(n)if(getZKAttr(n,"animating")){zk._addAnique(n.id,"zk.show")}else{zk._showExtr(n);n.style.display="";if(!t&&zk.isRealVisible(n))zk.onVisiAt(n)}};action.hide=function(e,t){var n=$e(e);if(n)if(getZKAttr(n,"animating")){zk._addAnique(n.id,"zk.hide")}else{if(!t&&zk.isRealVisible(n))zk.onHideAt(n);n.style.display="none";zk._hideExtr(n)}};anima={};anima.count=0;anima.appear=function(e,t){var n=$e(e);if(n){if(getZKAttr(n,"animating")){zk._addAnique(n.id,"anima.appear")}else{++anima.count;setZKAttr(n,"animating","show");zk._showExtr(n);Effect.Appear(n,{duration:t?t/1e3:.6,afterFinish:anima._afterVisi})}}};anima.slideDown=function(e,t){var n=$e(e);if(n){if(getZKAttr(n,"animating")){zk._addAnique(n.id,"anima.slideDown")}else{++anima.count;setZKAttr(n,"animating","show");zk._showExtr(n);Effect.SlideDown(n,{duration:t?t/1e3:.4,afterFinish:anima._afterVisi,y:0})}}};anima.moveDown=function(e){anima.moveBy(e,"top")};anima.moveRight=function(e){anima.moveBy(e,"left")};anima.moveDiagonal=function(e){anima.moveBy(e)};anima.moveBy=function(e,t,n){var r=$e(e);if(r){if(getZKAttr(r,"animating")){zk._addAnique(r.id,"anima."+(t=="top"?"moveDown":t=="left"?"moveRight":"moveBy"))}else{++anima.count;setZKAttr(r,"animating","show");zk._showExtr(r);if(!t)t="topleft";Effect.MoveBy(r,0,0,{duration:n?n/1e3:.6,afterFinish:anima._afterHide,afterSetup:function(e){if(t.indexOf("left")>-1){e.options.x=e.originalLeft;e.originalLeft=0}if(t.indexOf("top")>-1){e.options.y=e.originalTop;e.originalTop=0}e.element.show()}})}}};anima.slideUp=function(e,t){var n=$e(e);if(n){if(getZKAttr(n,"animating")){zk._addAnique(n.id,"anima.slideUp")}else{++anima.count;setZKAttr(n,"animating","hide");zk.onHideAt(n);Effect.SlideUp(n,{duration:t?t/1e3:.4,afterFinish:anima._afterHide})}}};anima.fade=function(e,t){var n=$e(e);if(n){if(getZKAttr(n,"animating")){zk._addAnique(n.id,"anima.fade")}else{++anima.count;setZKAttr(n,"animating","hide");zk.onHideAt(n);Effect.Fade(n,{duration:t?t/1e3:.55,afterFinish:anima._afterHide})}}};anima.puff=function(e,t){var n=$e(e);if(n){if(getZKAttr(n,"animating")){zk._addAnique(n.id,"anima.puff")}else{++anima.count;setZKAttr(n,"animating","hide");zk.onHideAt(n);Effect.Puff(n,{duration:t?t/1e3:.6,afterFinish:anima._afterHide0})}}};anima.dropOut=function(e,t){var n=$e(e);if(n){if(getZKAttr(n,"animating")){zk._addAnique(n.id,"anima.dropOut")}else{++anima.count;setZKAttr(n,"animating","hide");zk.onHideAt(n);Effect.DropOut(n,{duration:t?t/1e3:.6,afterFinish:anima._afterHide0})}}};anima._afterVisi=function(e){var t=e.element;if(t){--anima.count;rmZKAttr(t,"animating");zk.onVisiAt(t);zk._doAnique(t.id)}};anima._afterHide=function(e){var t=e.element;if(t){zk._hideExtr(t);--anima.count;rmZKAttr(t,"animating");zk._doAnique(t.id)}};anima._afterHide0=function(e){var t=e.effects[0].element;if(t){zk._hideExtr(t);--anima.count;rmZKAttr(t,"animating");zk._doAnique(t.id)}};zk._anique={};zk._addAnique=function(e,t){var n=zk._anique[e];if(!n)n=zk._anique[e]=[];n.push(t)};zk._doAnique=function(id){var ary=zk._anique[id];if(ary){var n=$e(id),al=ary.length;while(al){if(getZKAttr(n,"animating"))break;var js=ary.shift();eval(js+"('"+id+"')");al--}if(!al)delete zk._anique[id]}}}