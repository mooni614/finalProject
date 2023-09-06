

    // 일별 가입자 수 현황
    const ctx = document.getElementById('myChart');
    var user_number = "";
    $.ajax({

        method: 'POST',
        data: "",
        url: '/test/getUserChart',
        success: function (data) {


            var daycountList = data.countList;
            var dayList = getdayList(daycountList.length);
            var ageMap = data.ageMap;// circle chart에 보여질값.


            //
            showChart(daycountList, dayList);
            showCircleChart(ageMap);

        }, error() {

            console.log("bye")
        }
    })

    function getdayList(daylength) { // 오늘부터 이전 10일까지의 날짜값은 직접 포매팅해야한다.

        var dayList = [];
        const date = new Date();

        date.setDate(date.getDate() - daylength);

        for (var i = 0; i < daylength; i++) {

            date.setDate(date.getDate() + 1);

            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0');
            const day = String(date.getDate()).padStart(2, '0');
            const formattedDate = `${month}-${day}`; // "2023-08-31"

            dayList.push(formattedDate);

        }
        return dayList;
    }

    function showChart(countList, dayList) {

        console.log(countList);
        new Chart(ctx, {

            type: 'line',
            data: {
                labels: dayList,
                datasets: [{
                    label: '일별 가입자 수 현황',
                    data: countList,
                    borderWidth: 2
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: false
                    }
                }
            }
        });

    }


    function showCircleChart(ageMap){


        anychart.onDocumentReady(function () {
            // create pie chart with passed data
            var chart = anychart.pie([
                ['10대', ageMap.ten],
                ['20대', ageMap.two],
                ['30대', ageMap.three],
                ['40대', ageMap.four],
                ['50대', ageMap.five],
                ['60대', ageMap.six],
                ['70대', ageMap.seven]
            ]);


            // set chart labels position to outside
            // set legend title settings
            chart.title('');
            // set chart labels position to outside
            chart.labels().position('outside');
            // set legend title settings
            chart
                .legend()
                .title()
                .enabled(true)
                .text('회원 연령대 비율')
                .padding([0, 0, 10, 0]);

            // set legend position and items layout
            chart
                .legend()
                .position('center-bottom')
                .itemsLayout('horizontal')
                .align('center');
            chart.innerRadius('30%');

            // set container id for the chart
            chart.container('container');
            // initiate chart drawing
            chart.draw();
        });



    }



    // 삭제하기 버튼 클릭시
    function deluser(element) {

        console.log('deluser메서드실행');
        user_number = element.id;


        // Swal.fire('비밀번호가 변경됐습니다', '', 'success').then((result) => {
        //
        //     window.location.href = '/';
        //
        // })

        Swal.fire({
            title: '<strong>HTML <u>example</u></strong>',
            icon: 'warning',
            title: '정말 회원 정보를 삭제하시겠습니까?',
            showCloseButton: false,
            showCancelButton: true,
            focusConfirm: false,
            confirmButtonText:
                '확인',
            confirmButtonAriaLabel: 'great!',
            cancelButtonText:
                '취소',
            cancelButtonAriaLabel: 'Thumbs down'
        }).then((result) => {

            if (result.isConfirmed) {

                var query = {
                    user_number: user_number
                }

                $.ajax({

                    url: '/manager/deleteUser',
                    method: 'POST',
                    data: query,
                    success: function (data) {

                        // var responseList = data;
                        // $('.userinfo-box').remove();


                        var userList = "";
                        // for (var i = 0; i < responseList.length; i++) {
                        //
                        //     var userDate = new Date(responseList[i].userDate);
                        //     var userRegdate = new Date(responseList[i].userRegdate);
                        //
                        //
                        //     var date_year = userDate.getFullYear();
                        //     var date_month = String(userDate.getMonth() + 1).padStart(2, '0');
                        //     var date_day = String(userDate.getDate()).padStart(2, '0');
                        //     var formattedDate = `${date_year}년 ${date_month}월 ${date_day}일`; // "2023-08-31"
                        //
                        //     var regdate_year = userRegdate.getFullYear();
                        //     var regdate_month = String(userRegdate.getMonth() + 1).padStart(2, '0');
                        //     var regdate_day = String(userRegdate.getDate()).padStart(2, '0');
                        //     var formattedRegDate = `${regdate_year}년 ${regdate_month}월 ${regdate_day}일`; // "2023-08-31"
                        //
                        //     userList += "<div class=userinfo-box>";
                        //     userList += `<div>${responseList[i].userId}</div>`
                        //     userList += `<div>${responseList[i].userName}</div>`
                        //     userList += `<div>${responseList[i].userGender}</div>`
                        //     userList += `<div>${formattedDate}</div>`
                        //     userList += `<div>${formattedRegDate}</div>`
                        //     userList += `<div class=user-del-btn onclick=deluser(this) id =${responseList[i].userNumber}>
                        //     <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 384 512">
                        //                                  <style>svg {
                        //                                      fill: #ff0000
                        //                                  }</style>
                        //                                  <path d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z"/>
                        //                              </svg>
                        //
                        //                                  </div>`
                        //
                        //     userList += "</div>"; // userinfo-box닫는 태그
                        //
                        //
                        // }
                        // $('.usertable-inner').append(userList);

                        // 추가한 버튼의 이벤트 핸들러를 추가한다.
                        Swal.fire('삭제가 완료됐습니다.', '', 'success').then((result) => {

                            location.href = '/pageTest';

                        })
                    }, error: function () {

                    }
                });

            }

        })


        $('.swal2-confirm').click(function () {


            console.log('모달확인버튼.')
        })
    }






