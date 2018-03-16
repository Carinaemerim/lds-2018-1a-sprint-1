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