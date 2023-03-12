$(function(){
	$('#myinfo').click(function(){
		$('.member-detail').css('display', 'block');
		$('.mpList, .mrList, .mpartyList, .fpList, .frList').css('display', 'none');
	});
	
	$('#in_party').click(function(){
		$('.mpList').css('display', 'block');
		$('.member-detail, .mrList, .mpartyList, .fpList, .frList').css('display', 'none');
	});
	
	$('#review').click(function(){
		$('.mrList').css('display', 'block');
		$('.member-detail, .mpList, .mpartyList, .fpList, .frList').css('display', 'none');
	});
	
	$('#my_party').click(function(){
		$('.mpartyList').css('display', 'block');
		$('.member-detail, .mpList, .mrList, .fpList, .frList').css('display', 'none');
	});
	
	$('#fav_party').click(function(){
		$('.fpList').css('display', 'block');
		$('.member-detail, .mpList, .mrList, .mpartyList, .frList').css('display', 'none');
	});
	
	$('#fav_review').click(function(){
		$('.frList').css('display', 'block');
		$('.member-detail, .mpList, .mrList, .mpartyList, .fpList').css('display', 'none');
	});
});