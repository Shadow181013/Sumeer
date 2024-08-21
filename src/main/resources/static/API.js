$(document).ready(function() {
    $('.btn-primary').click(function() {
        var zone = $(this).siblings('.card-title').text();
        $.ajax({
            url: '/SightAPI',
            type: 'GET',
            data: { zone: zone },
            success: function(data) {
                localStorage.setItem('sightData', JSON.stringify(data));
                window.location.href = '/results.html';
            },
            error: function(error) {
                console.error('Error:', error);
            }
        });
    });
});