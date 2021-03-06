<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${titulo}</title>
<c:set var="path" value="${pageContext.request.contextPath}"
	scope="request" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</head>
<body>
	<h1>${titulo}</h1>

	<table
		class="table table-hover table-condensed table-striped table-bordered">
		<thead>
			<tr>
				<td style="width: 5%">#</td>
				<td style="width: 17%">Nombre</td>
				<td style="width: 17%">Estadio</td>
				<td style="width: 17%">Presupuesto</td>
				<td style="width: 17%">Division</td>
				<td style="width: 17%">Federacion</td>
				<sec:authorize access="hasRole('ADMIN')">
				<td style="width: 10%">Editar</td>
				<td style="width: 10%">Borrar</td>
				</sec:authorize>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${equipos}" var="equipo">
				<tr data-id="${equipo.id}">
					<td>${equipo.id}</td>
					<td><a href="<c:url value="/equipos/detalle/${equipo.id}"/>">${equipo.nombre}</a></td>	
					<td>${equipo.estadio}</td>
					<td>${equipo.presupuesto}</td>
					<td>${equipo.division}</td>
					<td>${equipo.federacion.nombre}</td>
					
					<sec:authorize access="hasRole('ADMIN')">
					<td>
					<button type="button" class="btn btn-warning btn-editar">Editar</button>
					</td>
					<td><button type="button" class="btn btn-danger btn-borrar">Borrar</button></td>
					</sec:authorize>
					
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">Equipos registrados: <span
					id="cantidad-equipos">${equipos.size()}</span></td>
			</tr>
			<tr>
				<td colspan="5">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#modal-equipo">Registrar Equipo</button>
				</td>
			</tr>
		</tfoot>
	</table>
<a href="${path}/index" type="button" class="btn btn-default" >Página de Inicio</a>
	<div class="modal fade" id="modal-equipo" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="form-equipo" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Información de equipos</h4>
					</div>
					<div class="modal-body">
                        <label for="nombre">Estadio: </label> <input id="estadio" name="estadio"
                            class="form-control"> 
                        <label for="nombre">Nombre: </label> <input id="nombre" name="nombre"
                            class="form-control"> 
                        <label for="nombre">Presupuesto: </label> <input id="presupuesto" name="presupuesto"
                            class="form-control">          
                        <label for="posicion">Division:
                        </label> <select id="division" name="division" 
                        class="form-control">
                            <c:forEach items="${divisiones}" var="division">
                                <option value="${division}">${division}</option>
                            </c:forEach></select>
                            
                        <label for="federacion">Federacion:
                        </label> <select id="federacion" name="federacion" class="form-control">
                            <c:forEach items="${federacion}" var="fede">
                                <option value="${fede.id}">${fede.nombre}</option>
                            </c:forEach>
                            
                        </select> <input id="id" name="id" type="hidden"> <input id="csrf"
                            name="_csrf" type="hidden" value="${_csrf.token}">
                    </div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
						<button id="btn-salvar" type="submit" class="btn btn-primary">Guardar
							Información</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		$('.btn-editar').on('click', function(){
			console.log("Entra en editar");
				var id = $(this).parents('tr').data('id');
				var url = 'equipos/'+id;
				
				$.get(url)
					.done(function(equipo){
						console.log (equipo);
						$('#id').val(equipo.id);
						$('#estadio').val(equipo.estadio);
						$('#nombre').val(equipo.nombre);
						$('#presupuesto').val(equipo.presupuesto);
						$('#division').val(equipo.division);
						$('#federacion').val(equipo.federacion);
						$('#form-equipo .modal-title').text("Editando equipos...");
						
						$('#modal-equipo').modal('show');
					});
		});
		
		
		
		$('.btn-borrar').on('click', function(){
			var id = $(this).parents('tr').data('id');
			
			$.ajax({
				url : "equipos/"+id,
				type: 'DELETE',
			    success: function(result) {
			    	$('tr[data-id="'+id+'"]').remove();
					var ingredientes = parseInt( $('#cantidad-equipos').text() );
			    	$('#cantidad-equipos').text(ingredientes - 1);
			    }
			});
			
		});
		
	});
	
	</script>

</body>
</html>