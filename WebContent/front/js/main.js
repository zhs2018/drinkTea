//get device size
/*document.write('<style>@media all and (orientation : landscape) {\
		.container{min-height:'+document.documentElement.clientWidth+'px;}\
	} \
	@media all and (orientation : portrait){\
		.container{min-height:'+document.documentElement.clientHeight+'px;}\
	} </style>');*/


function confirmReceipt(){
	$(".container").addClass("blur");
	$(".popup").show();
	$("body").addClass("closescroll");
}
function cancel(){
	$(".container").removeClass("blur");
	$(".popup").hide();
	$("body").removeClass("closescroll");
}

function sure(){
	window.location.href="receivingEvaluation.html";
}


(function(d,g){d["_alert"]=d.alert;d["_confirm"]=window.confirm;var f={zIndex:1000,getZindex:function(){return this.zIndex+=100},dialog:{}},e=function(j,k){return k&&j.replace(/{(.*?)}/gi,function(l,m){return k[m]||m})};function b(){var k=this;var j=arguments[0];k.arg=(j&&(typeof j=="object"))?j:{"str":j};k._mask=[];k.TPL='<div class="widget_wrap" style="z-index:{zIndex2};">{dialog by Anjey_wu wkf39988@gmail.com}</div>';k.TPL_MASK='<div class="widget_mask" style="z-index:{zIndex};"></div>';k._body=k.TPL;k.type="dialog";k.zIndex=f.getZindex();k.id="widget_"+k.type+"_"+k.zIndex;k.classes="";return k}b.prototype={constructor:b,config:function(k){var l=this;if(k&&("object"==typeof k)){for(var j in k){l[j]=k[j]}}l.arg.zIndex=l.zIndex;l.arg.zIndex2=l.zIndex+50;l._body=e(l.TPL_MASK+l.TPL,l.arg);return l},init:function(){},handleEvent:function(j){var k=this;k.clickFn(j);return k},open:function(){var j=this;if(!g.body){d.addEventListener("DOMContentLoaded",function(){j.open()},false);return j}if(!(f.dialog[j.id])){j.widget=document.createElement("div");j.widget.setAttribute("data-role","widget");j.widget.setAttribute("data-widget","widget_"+j.type);j.widget.setAttribute("id",j.id);j.widget.style.zIndex=j.zIndex;j.widget.setAttribute("class",j.classes);j.widget.innerHTML=j._body;j.widget.addEventListener("click",j,false);g.body.appendChild(j.widget);f.dialog[j.id]=j;j._mask.push(j.id)}j.widget.classList.add("on");return j},close:function(){var j=this;j.widget.classList.remove("on");return j},destroy:function(){var j=this;j.widget.parentNode.removeChild(j.widget);delete f.dialog[j.id];j=undefined;return j},clickFn:function(j){var k=this;if(k.callBack&&k.hasOwnProperty("callBack")){k._callBack=k.callBack;delete k.callBack;k.clickFn=function(l){k._callBack.call(k,l)&&k.callBack.call(k,l)}}else{k.clickFn=function(l){k.callBack.call(k,l)}}k.clickFn(j);return k},callBack:function(j){var k=this,l=null;if(j&&(l=j.target)&&("BUTTON"==l.tagName)){k.destroy()}return k}};function c(k){var j=this;b.call(j);j.TPL='<div class="widget_wrap" style="z-index:{zIndex2};"><div><span></span></div></div>';j.type="loading";j.classes="";j.zIndex=f.getZindex();j.id="widget_"+j.type+"_"+j.zIndex;return j}c.prototype=new b();c.prototype.constructor=c;function h(l){var k=this;b.call(k);var j=arguments[0];k.arg=(j&&(typeof j=="object"))?j:{"str":j};k.TPL='<div class="widget_wrap" style="z-index:{zIndex2};">						<div class="widget_header"></div>						<div class="widget_body">{str}</div>						<div class="widget_footer">							<ul>								<li><button type="button">确定</button></li>							</ul>						</div>					</div>';k.type="alert";k.classes="alert";k.zIndex=f.getZindex();k.id="widget_"+k.type+"_"+k.zIndex;return k}h.prototype=new b();h.prototype.constructor=h;function i(l){var k=this;b.call(k);var j=arguments[0];k.arg=(j&&(typeof j=="object"))?j:{"str":j};k.TPL='<div class="widget_wrap" style="z-index:{zIndex2};" >			<div class="widget_header"></div>			<div class="widget_body">{str}</div>			<div class="widget_footer">				<ul>					<li><button type="button">取消</button></li>					<li><button type="button">确定</button></li>				</ul>			</div>		</div>';k.type="confirm";k.classes="";k.zIndex=f.getZindex();k.id="widget_"+k.type+"_"+k.zIndex;return k}i.prototype=new b();i.prototype.constructor=i;function a(l){var k=this;b.call(k);var j=arguments[0];k.arg=(j&&(typeof j=="object"))?j:{"str":j};k.TPL='<div class="widget_wrap" style="z-index:{zIndex2};"><div class="widget_body">{str}</div></div>';k.type="tip";k.classes="";k.zIndex=f.getZindex();k.id="widget_"+k.type+"_"+k.zIndex;k.t=1500;return k}a.prototype=new b();a.prototype.delay=function(){var j=this;if(j.delay&&!j.hasOwnProperty("delay")){j.delay=function(){j.destroy()}}setTimeout(function(){console.log("delay");j.delay()},j.t);return j};a.prototype.constructor=a;d.dialog=function(k,j){return new b(j).config(j)};d.alert=function(k,j){return new h(k,j).config(j).open()};d.confirm=function(k,j){return new i(k,j).config(j).open()};d.loading=function(k,j){return new c(k,j).config(j).open()};d.tip=function(k,j){return new a(k,j).config(j).open().delay()}})(window,document);