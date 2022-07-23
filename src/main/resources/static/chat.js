$(document).ready(function() {
	const chatURL = "/api/chat";
	let count = 0;
	$.get(chatURL, function(chatData) {
		chatData.forEach(chatLog => {
			if (count === 0) {
				$(".data").html("");
				$(".data")
				.append($("<h1>" + chatLog.id + "</h1>"))
				.append($("<h2>" + chatLog.name + "</h2>"))
				.append($("<p>" + chatLog.content + "</p>"));
				count++;
			}
			else {
				let newDiv = $("<div></div>");
				newDiv.addClass("data");
				$("body").append(newDiv);
				newDiv
				.append($("<h1>" + chatLog.id + "</h1>"))
				.append($("<h2>" + chatLog.name + "</h2>"))
				.append($("<p>" + chatLog.content + "</p>"));
			}
		});
	});
});