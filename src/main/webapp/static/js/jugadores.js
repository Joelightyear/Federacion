$(document).ready(function(){
        
        $('.btn-editar').on('click', function(){
                var id = $(this).parents('tr').data('id');
                var url = 'pizzas/'+id;
                
                $.get(url)
                    .done(function(pizza){
                        $('#id').val(pizza.id);
                        $('#nombre').val(pizza.nombre);
                        $('#precio').val(pizza.precio);
                        $('#categoria').val(pizza.categoria);
                        
                        $('#modal-pizza').modal('show');
                    });
        });
        

        $('.btn-borrar').on('click', function(){
            var id = $(this).parents('tr').data('id');
            
            $.ajax({
                url : "pizzas/"+id,
                type: 'DELETE',
                success: function(result) {
                    $('tr[data-id="'+id+'"]').remove();
                    var ingredientes = parseInt( $('#quantidade-pizzas').text() );
                    $('#quantidade-pizzas').text(pizzas - 1);
                }
            });
            
        });
        
    });