$(function () {
    // tab 기능
    var $tablink = $('.second-box-tabs div').click(function (e) {
        var idx = $tablink.index(this);
        $('.second-box-tabs div').css('color', 'rgb(170, 170, 170)');
        $('.second-box-tabs div').css('font-weight', '400');
        $(this).css('color', '#00ce7c');
        $(this).css('font-weight', '600');

        var marginLeftValue = idx * 50;
        $('.second-box-tabs-clicked-bar').animate({
            'margin-left': marginLeftValue + '%'
        }, 200)
        $('.sections-con').removeClass('show');
        $('.third-box section:first > div').eq(idx).addClass('show')
    });


    // // 대여 가능 여부 생년 검사
    // $('#input_birth').keyup(() => {
    //
    //     var input_birth = document.getElementById('input_birth');
    //     var warningDiv = document.getElementById('warning');
    //     var inputYear = input_birth.value.substring(0, 2);
    //     var currentYear = new Date().getFullYear() % 100;
    //     var btnKakaoPay = document.getElementById('btnKakaoPay');
    //
    //     if (input_birth.value >= 2) {
    //         var inputValue = parseInt(input_birth.value.substring(0, 2));
    //     }
    //
    //     if (input_birth.value.length >= 6) {
    //
    //         result = currentYear - inputYear;
    //         absolute = Math.abs(result);
    //         console.log(absolute);
    //
    //         if (absolute >= 20) {
    //             console.log('20이상');
    //             // 20세 이상
    //             warningDiv.textContent = "만 20세, 해당 차량 대여 가능합니다.";
    //             warningDiv.style.color = "rgb(89, 178, 106)"; // 기본 색상으로 변경
    //             btnKakaoPay.disabled = false;
    //         } else if (absolute < 20) {
    //             console.log('20이하');
    //             // 20세 미만
    //             warningDiv.textContent = "20세 미만입니다.";
    //             warningDiv.style.color = "red"; // 빨간색으로 변경
    //             btnKakaoPay.disabled = true;
    //         }
    //
    //     } else {
    //         warningDiv.textContent = "'-' 을 제외한 생년월일 6자리를 입력하세요"; // 경고 메시지 초기화
    //         btnKakaoPay.disabled = true;
    //     }
    // });

// 핸드폰 인증
    var auth_num = '';
    var auth_check = false;
    var id_check = false;

    // 인증번호 요청, 재요청 클릭시
    $('#verifyBtn').click(function () {
        const phoneNumberPattern = /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/;
        const user_phone = $("#input_phone").val();

        if (phoneNumberPattern.test(user_phone)) {

            $('#verify').removeClass('auth');
            $('#verifyBtn').text('재요청');

        } else {
            alert("잘못된 번호입니다")
            $("#input_phone").focus();
        }

        if (!auth_check) { // 인증완료가 아직 안됐을 경우.

            $.ajax({
                url: '/member/joinAuth',
                method: 'POST',
                data: {user_phone: user_phone},
                success: function (data) {


                    console.log(data); // controller에서 넘긴 data를 받아온다.
                    auth_num = data;

                    // 인증번호 칸 열기

                },
                error: function () {

                }

            });
        } else {

            alert("이미 인증이 완료됐습니다.");

        }
    })

    $('#verifyConfirmBtn').click(function () {

        if (auth_num === $('#input_auth').val() || auth_check) {

            alert('인증이 완료됐습니다.');
            auth_check = true;
            $('#verify').addClass('auth');
            $('#verifyBtn').addClass('auth');
            $('#input_phone').val("인증완료");
            $('#input_phone').css('color', '#0064de');
            $('#input_phone').css('border-color', '#0064de');
            $('#input_phone').prop('disabled', true);
            $('#btnKakaoPay').prop('disabled', false);

        } else {
            alert('잘못 입력했습니다. 인증번호를 확인하세요.');
            $('#input_auth').focus();
            $('#btnKakaoPay').disabled = ture;

        }
    })
// 카카오결제
    $("#btnKakaoPay").click(function () {

        var query = {
            input_name: $("#input_name").val(),
            input_phone: $("#input_phone").val(),
            // input_birth: $("#input_birth").val(),
            // monthDay: $("#input_birth").val().substring(2),
            checkVal: $("input[formcontrolname=gender]:checked"),
            selectBox: $("select[name='selectBox']").val()
        }
        const phoneNumberPattern = /^01[0-9]-\d{3,4}-\d{4}$/; // 휴대폰 형식검사 정규 표현식
        const birthpattern = /^(\d\d)(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/;
        var btnKakaoPay = document.getElementById('btnKakaoPay');
        var warningDiv = document.getElementById('warning');

        if (query.input_name === '') {
            /*######이름 입력 검사######*/
            alert('이름을 입력하세요.');
            $("#input_name").focus();
            btnKakaoPay.disabled = true;
        } else if (query.input_phone === '') {
            /*######핸드폰 입력 검사######*/
            alert('핸드폰 번호를 입력하세요.');
            $("#input_phone").focus();
            btnKakaoPay.disabled = true;
        } else if (!auth_check) {
            /*######핸드폰 인증 검사######*/
            alert('핸드폰 인증을 완료하세요.')
            $("#input_phone").focus();
        } else if (!query.checkVal) {
            /*######성별 선택 검사######*/
            alert("성별을 선택해 주세요.");
            btnKakaoPay.disabled = true;
            $("input[formcontrolname='gender']:checked").focus();
        } else if (query.selectBox === "") {
            alert("국적을 선택해 주세요.");
            $("select[name='selectBox']").focus();
        } else {
            // 결제 진입

            // 결제 정보를 form에 저장한다.
            var ticketInfo = {
                ticTicketId: $("#ticketId").val(),
                ticFlightDepartureDate: $("#departureDate").val(),
                ticFlightArrivalDate: $("#arrivalDate").val(),
                ticSeatGrade: $("#seatGrade").val(),
                ticAirlineName: $("#airlineName").val(),
                ticFee: $("#totalPrice").val(),
                ticFromLocation: $("#fromLocation").val(),
                ticToLocation: $("#toLocation").val(),
                ticVihicleId: $("#vehicleId").val(),
                // userId: $("#userId").val(),
                // userName: $("#userName").val()
            };
            // 카카오페이 결제전송
            $.ajax({
                type: 'post'
                , url: '/kakaoPay',
                data: JSON.stringify(ticketInfo), // JSON 데이터 전송
                contentType: 'application/json' // JSON 데이터임을 명시
                , success: function (response) {
                    // 화면 중앙에 위치시키기 위한 x, y 좌표 계산
                    var screenWidth = window.screen.width;
                    var screenHeight = window.screen.height;
                    var popupWidth = 500; // 팝업 창 가로 크기
                    var popupHeight = 600; // 팝업 창 세로 크기
                    var left = (window.screen.width / 2) - (popupWidth / 2);
                    var top = (window.screen.height / 2) - (popupHeight / 2);

                    // 팝업 창 열기
                    var popup = window.open(response.next_redirect_pc_url, "_blank", "width=500,height=600,left=" + left + ",top=" + top);

                }
            })
        }
    })

// 모달 열기
    $('.shuttle-button').click(function () {
        $('.modal').css('display', 'block');
        $("body").css('overflow', 'hidden');
        // $("body").css('margin-left', '1px');  // 스크롤로 인한 화면 꿀렁거림 제거
        // 이유 모르겠는데 위 현상 없어짐
    })
// 모달 닫기
    $('.modal-btn').click(function () {
        $('.modal').css('display', 'none');
        $("body").css('overflow', 'auto');
        // $("body").css('margin-left', '0px');  // 스크롤로 인한 화면 꿀렁거림 제거
        // 이유 모르겠는데 위 현상 없어짐
    })

// 텍스트 숨기기
    $('.arrow').click(function () {
        if ($(this).hasClass('false')) {
            $('.arrow').removeClass('false');
            $('.arrow').addClass('onArrow');
            $('.policy-detail').css('max-height', '989px');
            $('.policy-detail').css('margin-bottom', '20px');
        } else {
            $('.arrow').removeClass('onArrow');
            $('.arrow').addClass('false');
            $('.policy-detail').css('max-height', '0px');
            $('.policy-detail').css('margin-bottom', '0px');
        }
    })

// 내륙, 제주 지역 선택텝
    var $landLocation = $('.insurance-locations div').click(function () {
        var idx = $landLocation.index(this);
        $('.insurance-locations div').removeClass('locationClick');
        $(this).addClass('locationClick');

        $('.insurance-info').removeClass('showTale');
        $('.insurance-info').eq(idx).addClass('showTale'); // sections-con.show > section:nth-child(3) > table
    });


});