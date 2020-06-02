

function scrollFunction() {
    var scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;

    if (scrollTop > 120) {
        $("#toTop").fadeIn("slow");
    } else {
        $("#toTop").css("display","none");
        $("#toTop").fadeOut("slow");
    }

}

function toTop() {
    $("#navbar").css("display", "block");
    $("html,body").animate({scrollTop: 0}, 500);
}

function changeWidth() {
    // 默认输入框宽度
    // alert($("#search").css("width"));
    $("#search").focus(function () {
        $(this).animate({width: '300px'}, 'slow');
    }).blur(function () {
        $(this).animate({width: '170px'}, 'slow');
    });
}

function activeNavbar() {
    $(".navbar-nav li a").hover(function () {
        $(this).css({"color": "white","background-color": "#3c763d"});
    }, function () {
        $(this).css({"color": "#777","background-color": "transparent" });
    });

}

function loading() {
    $(".progress-bar").animate({width: "100%"}, function () {
        $(".progress").css("display", "none");
    });
}


function showOrHide() {
    var agent = navigator.userAgent;
    var scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
    if (/.*Firefox.*/.test(agent)) {
        document.addEventListener("DOMMouseScroll", function (e) {
            e = e || window.event;
            var detail = e.detail;
            // console.log("火狐上滚还是下滚: " + e.detail);
            if (detail > 0 && scrollTop > 100) {
                $("#navbar").css("display", "none");
            } else {
                $("#navbar").css("display", "block");
            }
        });
    } else {
        document.onmousewheel = function (e) {
            e = e || window.event;
            var wheelDelta = e.wheelDelta;
            if (wheelDelta > 0 || scrollTop < 100) {
                $("#navbar").css("display", "block");
            } else {
                $("#navbar").css("display", "none");
            }
        }
    }

}


window.onscroll = function () {
    scrollFunction();
    showOrHide();
};


$().ready(function () {
    changeWidth();
    activeNavbar();
    loading();
    $('[data-toggle="tooltip"]').tooltip();
});


// 加载粒子效果
function loadParticles() {

    Particles.init({
        selector: '.background',
        color: "#3c763d",
        connectParticles: true,
        speed: 1.5,
    });
}

window.onload = function () {
    loadParticles();
};

/**
 * 更新文章浏览数
 * @param aid
 */
function addView(aid){
    $.ajax({
        url: "/article/addView",
        type: "POST",
        data: JSON.stringify({
            aid: aid
        }),
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        success: function () {

        }
    })
}
