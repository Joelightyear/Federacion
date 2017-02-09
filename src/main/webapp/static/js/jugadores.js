	$(document).ready(function(){
		
		aplicarListeners();
		limparModal();
		
	});
		var limparModal = fuction(){
			$('#id').val('');
			$('#nombre').val('');
			$('#edad').val('');
			$('#goles').val('');
			$('#posicion').val('');
			$('#equipo').val('');
					
		};
		
		var aplicarListeners = fuction (){
			
		
		$('.btn-editar').on('click', function(){
				var id = $(this).parents('tr').data('id');
				var url = 'jugadores/'+id;
				
				$.get(url)
					.done(function(jugador){
						$('#id').val(jugador.id);
						$('#nombre').val(jugador.nombre);
						$('#edad').val(jugador.edad);
						$('#goles').val(jugador.goles);
						$('#posicion').val(jugador.posicion);
						$('#form-jugador .modal-title').text("Editando jugadores...")
						
						$('#modal-jugador').modal('show');
					});
		});
		
		$('.btn-borrar').on('click', function(){
			var id = $(this).parents('tr').data('id');
			
			$.ajax({
				url : "jugadores/"+id,
				type: 'DELETE',
			    success: function(result) {
			    	$('tr[data-id="'+id+'"]').remove();
					var ingredientes = parseInt( $('#cantidad-jugadores').text() );
			    	$('#cantidad-jugadores').text(ingredientes - 1);
			    }
			});
			
		});
		
	});
	}