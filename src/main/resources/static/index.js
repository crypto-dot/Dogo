$(document).ready(
	function() {
		$("button").click(
			function() {	
				let x = $("#x").val();
				let y = $("#y").val();
				let url = "api/math/add/" + x + "/" + y;
				$.get(url, function(data) {
					$("#result").text(data);
				})
			}
		)

	}

)