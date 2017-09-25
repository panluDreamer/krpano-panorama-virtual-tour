$(function() {
	/* ËÑË÷ */
	$(".search_pic").click(function() {
		$(".headernav").fadeOut(200);
		$(".search_main").fadeIn(1000);
	});
	$(".search_main .close_btn").click(function() {
		$(".search_main").fadeOut(500);
		$(".headernav").fadeIn(1000);
	});
	/* µÇÂ¼ */
	$(".list a").click(function() {
		$(".member").slideToggle(500);
	});
});