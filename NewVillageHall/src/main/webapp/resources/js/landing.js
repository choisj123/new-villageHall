const back = document.querySelector('.back');


const change = document.querySelector('.change');
const change1 = document.querySelector('.change1');

const agree = document.querySelector('.agree');
const disagree = document.querySelector('.disagree');

agree.addEventListener('click', () => {
    window.location.href = "http://192.168.120.32:5501/html/main-page.html";
})

disagree.addEventListener('click', () => {
    change.classList.add('hidden');
    change1.classList.remove('hidden');
})


back.addEventListener('click', () => {
    change.classList.remove('hidden');
    change1.classList.add('hidden');
})