$(document).ready(() => {
  let arr = [];
  let cnt = 0;
  $(".btn").click(function () {
    let content = $(this).text();
    if (content == "=") {
      let ans = 0;
      $.ajax({
        url: "http://localhost:8080/work",
        data: JSON.stringify({ str: arr }),
        type: "POST",
        // dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (res) {
          $(".result").text(res);
          arr = [res];
          cnt = 0;
        },
        error: function (res) {
          $(".result").text(res);
          arr = [];
          cnt = 0;
        },
      });
      return;
    } else if (content == "CE") {
      if (arr[cnt] == "" || arr[cnt] == undefined) {
        cnt--;
      }
      arr[cnt] = "";
    } else if (content == "C") {
      arr = [];
      cnt = 0;
    } else if (content == "DEL") {
      if (arr[cnt] !== undefined && arr[cnt].length != 0) {
        arr[cnt] = arr[cnt].substr(0, arr[cnt].length - 1);
      }
    } else if ((content < "0" || content > "9") && content != ".") {
      cnt++;
      arr[cnt] = content;
      cnt++;
    } else {
      arr[cnt] = arr[cnt] == undefined ? content : arr[cnt] + content;
    }
    console.log(arr);
    $(".result").html(arr.join(""));
  });
});
