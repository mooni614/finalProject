


$(document).ready(function () {


// 가격 높은 순 정렬을 위한 컨트롤러 연결
    $('#high_price_list_btn').click(function () {

        $.ajax({
            type: "GET",
            url: "/highPriceList", // 높은 가격순으로 정렬하는 컨트롤러 엔드포인트 URL
            dataType: "json", // 반환되는 데이터 형식
            success: function(data) {
                // 서버에서 반환된 데이터를 사용하여 화면 업데이트
                updateCarList(data);
            },
            error: function() {
                alert("데이터를 불러오는 중에 오류가 발생했습니다.");
            }
        });
    });

    //가격 낮은 순 정렬
    $('#low_price_list_btn').click(function () {

        $.ajax({
            type: "GET",
            url: "/lowPriceList", // 높은 가격순으로 정렬하는 컨트롤러 엔드포인트 URL
            dataType: "json", // 반환되는 데이터 형식
            success: function(data) {
                // 서버에서 반환된 데이터를 사용하여 화면 업데이트
                updateCarList(data);
            },
            error: function() {
                alert("데이터를 불러오는 중에 오류가 발생했습니다.");
            }
        });
    });


    //차종순 정렬
    $('#car_type_list_btn').click(function () {

        $.ajax({
            type: "GET",
            url: "/carTypeList", // 높은 가격순으로 정렬하는 컨트롤러 엔드포인트 URL
            dataType: "json", // 반환되는 데이터 형식
            success: function(data) {
                // 서버에서 반환된 데이터를 사용하여 화면 업데이트
                updateCarList(data);
            },
            error: function() {
                alert("데이터를 불러오는 중에 오류가 발생했습니다.");
            }
        });
    });


// 왼쪽 사이드바에서 키워드 검색 이벤트
    $('#reserve_search_btn').click(function () {
        var searchKeyword = $('#searchKeyword').val();

        $.ajax({
            type: "GET",
            url: "/rentcarReserve", // 컨트롤러 엔드포인트 URL
            dataType: "json", // 반환되는 데이터 형식
            data: {
                searchKeyword: searchKeyword // 검색어를 쿼리 매개변수로 전달
            },
            success: function(data) {
                // 서버에서 반환된 데이터를 사용하여 화면 업데이트
                updateCarList(data);
            },
            error: function() {
                alert("데이터를 불러오는 중에 오류가 발생했습니다.");
            }
        });
    });




});


//결과 상자의 호버효과




//정렬조건에 따라 결과 상자 보여주기
function updateCarList(data) {
    var carListContainer = $(".reserv_result_gird");
    carListContainer.empty(); // 기존 목록 삭제

    // 반환된 데이터를 반복하여 목록에 추가
    for (var i = 0; i < data.length; i++) {
        var carInfo = data[i];
        var carItemHtml = `
                <!-- 차량 안내 카드1 -->
                <div class="result_item" id="result_item" onclick="location.href='/store/rentcar/rentcarChoice?car_name=${carInfo.car_name}&cartype=${carInfo.car_type}&caryear=${carInfo.car_year}&carprice=${carInfo.car_discount}'">
                    <div><span class="result_item_year">${carInfo.car_year}</span></div>
                    <p class="result_item_name" id="car_name">${carInfo.car_name}</p>
                    <p class="result_item_type">${carInfo.car_type}</p>
                    <img src="${carInfo.car_img}" style="width: 100%; max-width: 163px;">
                    <div class="price_box">
                        <p class="lowest_label">최저</p>
                        <p class="PriceText-iWKXRR result_item_price">${carInfo.car_discount}</p>
                        <p class="result_item_price">원</p>
                    </div>
                </div>
            `;
        carListContainer.append(carItemHtml);
    }
}



