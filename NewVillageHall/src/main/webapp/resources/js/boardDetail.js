const insertCommentBtn = document.getElementById("insertCommentBtn");
const insertCommentContent = document.getElementById("insertCommentContent");
const updateCommentContent = document.getElementById("updateCommentContent");
const updateButton = document.querySelector('#updateCommentButton');
let commentNo = document.getElementById

insertCommentBtn.addEventListener("click", function(event) {
	if(insertCommentContent.value == "") {
		alert("댓글을 입력해주세요.")
		event.preventDefault();
	}
});

const updateCommentBtns = document.querySelectorAll('.updateCommentButton');

updateCommentBtns.forEach((updateCommentBtn) => {
    updateCommentBtn.addEventListener("click", e => {
        const commentDetail = e.target.closest('.commentDetail');
        commentDetail.querySelector('.comment-content').style.display = 'none';
        commentDetail.querySelector('.commentEditForm').style.display = 'block';
        commentDetail.querySelector('#updateCommentContent').value = commentDetail.querySelector('.comment-content').innerText;
    	commentDetail.querySelector('.createDate').style.display = 'none';
        commentDetail.querySelector('.editButton').style.display = 'none';

    
    });
});

const cancelCommentBtns = document.querySelectorAll('.cancelCommentButton');

cancelCommentBtns.forEach((cancelCommentBtn) => {
    cancelCommentBtn.addEventListener("click", e => {
        const commentDetail = e.target.closest('.commentDetail');
        commentDetail.querySelector('.comment-content').style.display = 'block';
        commentDetail.querySelector('.commentEditForm').style.display = 'none';
    	commentDetail.querySelector('.createDate').style.display = 'block';
        commentDetail.querySelector('.editButton').style.display = 'flex';

    
    });
});

