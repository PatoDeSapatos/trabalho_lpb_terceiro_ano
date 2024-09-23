const registerForm = document.querySelector("#register-form");
const loginForm = document.querySelector("#login-form");

const changePage = document.querySelectorAll(".change-page");

changePage.forEach((button) => {
    button.addEventListener("click", (e) => {
        e.preventDefault();
        
        registerForm.classList.toggle("active");
        loginForm.classList.toggle("active");
    });
})