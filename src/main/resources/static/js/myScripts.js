function asyncSearch(url){
    $.ajax({
        type: "POST",
        url: url,
        data: $("#form-search").serialize(),
        success : function(data) {
            $('#search-results').replaceWith(data);
        },
        error: function(result) {
            alert('error');
        }
    });
}

function sendMessage(url){
    $.ajax({
        type: "POST",
        url: url,
        data: $("#chat-form").serialize(),
        success : function(data) {
        	$('#chat-input').text(null);
            $('#chat-results').replaceWith(data);
        },
        error: function(result) {
            alert('error');
        }
    });
}