// efeito infinit scroll
var pageNumber = 0;

//some com o load e botao
$(document).ready(function(){
	$("#loader-img").hide();
	$("#fim-btn").hide();
});

$(window).scroll(function() {
	
	var scrollTop = $(this).scrollTop();
	var conteudo = $(document).height() - $(window).height();
	
	//console.log("scrollTop : " + scrollTop + " | " + "Conteudo " + conteudo );
	if (scrollTop >= conteudo) {
		pageNumber++;
		setTimeout(function(){
			loadByScrollBar(pageNumber);	
		}, 200);
		
	}
	
});

function loadByScrollBar(pageNumber){
	
	$.ajax({
	
		method: "GET",
		url: "/promocao/list/ajax",
		data: {
			page: pageNumber
		},
		beforeSend: function(){
			$("#loader-img").show();
		},
		success: function(response){
		//	console.log("Resposta" , response);
			console.log("lista > " , response.length);
			if (response.length > 150){
				$(".row").fadeIn(250, function(){
					$(this).append(response);
				});
			} else {
				$("#fim-btn").show();
				$("#loader-img").removeClass("loader");
			}
				
			
		},
		error: function(xhr){
			alert("Ops. Ocorreu um erro: "  + xhr.status + " - " + xhr.statusText );
		},
		complete: function(){
			$("#loader-img").hide();
		}
		
	})
	
	
}