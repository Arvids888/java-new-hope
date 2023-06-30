function setLang(langId) {
    $.get("http://localhost:8081/setLang/" langId, function(data) {
        location.reload();
    });
}