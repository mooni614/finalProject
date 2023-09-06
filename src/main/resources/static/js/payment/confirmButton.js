$(function () {
    $('#confirm').click(function () {
        window.opener.location.href = '/community/home';
        window.self.close();
    })
})