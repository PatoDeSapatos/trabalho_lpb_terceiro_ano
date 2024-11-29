const form = document.querySelector("#form");
const info = document.querySelector("#info");

const changePage = document.querySelectorAll(".change-page");

changePage.forEach((button) => {
    button.addEventListener("click", (e) => {
        e.preventDefault();
        
        form.classList.toggle("active");
        info.classList.toggle("active");
    });
})