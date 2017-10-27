var ACTIVATE_PATH = 'api/v1/activate';
var TERMINATE_PATH = 'api/v1/terminate';

function activateRobots() {
	$.ajax({
		type : 'GET',
		url : ACTIVATE_PATH,
		dataType : 'json',
		async : true,
		success : function(result) {

		},
		error : function(jqXHR, textStatus, errorThrown) {

		}
	});
}
function terminateRobots() {
	$.ajax({
		type : 'GET',
		url : TERMINATE_PATH,
		dataType : 'json',
		async : true,
		success : function(result) {

		},
		error : function(jqXHR, textStatus, errorThrown) {

		}
	});
}