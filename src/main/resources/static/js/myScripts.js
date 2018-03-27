function sendMessage(url, formId, replacementId){
    asyncRequest(url, formId, replacementId, function(data){
    	$(replacementId).replaceWith(data);
    	$(formId)[0].reset();
    });
}

function asyncRequest(url, formId, replacementId,
		onSuccess = function(data){$(replacementId).replaceWith(data);}){
	$.ajax({
        type: "POST",
        url: url,
        data: $(formId).serialize(),
        success : onSuccess,
        error: function(result) {
            alert('error: '+result);
        }
    });
}