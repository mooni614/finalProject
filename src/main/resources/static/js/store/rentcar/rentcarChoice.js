$(document).ready(function () {
/*
    $('#choiceCompany').click(function () {

        if ($('#sessionUser').val() === '') {
            Swal.fire('로그인후 이용하세요.', '', 'error').then(function () {

                location.href = '/member/login';
            });
        } else {



            var carInfoId = $('#choiceCompany').data('car-info-id'); // 이 부분을 추가하여 car_info_id 값을 가져오기

            console.log(carInfoId);
            location.href = '/pay/rentcarPaymentPage?Car_info_id='+ carInfoId; // car_info_id를 사용하여 URL을 생성하고 이동


        }
*/


    });

function moveToPay(e) {

    if ($('#sessionUser').val() === '') {
        Swal.fire('로그인후 이용하세요.', '', 'error').then(function () {

            location.href = '/member/login';
        });
    } else {


        var carInfoId = e.dataset.carInfoId; // 이 부분을 추가하여 car_info_id 값을 가져오기

        console.log(carInfoId);


        location.href = '/pay/rentcarPaymentPage?Car_info_id=' + carInfoId; // car_info_id를 사용하여 URL을 생성하고 이동


    }

}

