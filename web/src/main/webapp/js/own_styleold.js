/**
* bj es el acronimo de "$" o "jQuery"
**/
bj(document).ready(function() {		
	
	bj('.startmenu img').attr("src","/icons/down.png");
	
	/*Click Menu Perfil*/
	bj(".codiusuari").click(function() {
		bj('.menuperfil').toggle();
	});
	
	/*Cuando es un movil*/
	if (bj(window).width() <= 480){
		if(!(bj('#logo').length > 0)){
			bj('.startmenu').before('<img alt="Logo" id="logo" src="/icons/logo-30x30.png" />');
		}
				
	} else {
		//Muestra menu pricipal
		bj('.startmenu').mouseover(function () {
			if(bj('.menuprincipal [style*="display:none"]')){
				bj('.menuprincipal').fadeIn();
			}
		});
		//Oculta menu principal
		bj('.menuprincipal').mouseleave(function () {
			bj(this).fadeOut();
		});
		//Muestra menu perfil
		bj('.codiusuari').mouseover(function () {
			if(bj('.menuperfil [style*="display:none"]')){
				bj('.menuperfil').fadeIn();
			}
		});
		//Oculta menu perfil
		bj('.menuperfil').mouseleave(function () {
			bj(this).fadeOut();
		});
	}
	
	bj(window).scroll(function(){
		if (bj(window).width() < 768){
			if (bj(window).scrollTop() > 0) {
				bj('.own-ip').fadeOut();
			} else {
				bj('.own-ip').fadeIn();
			}
		}	
	});
	
	
	
});
