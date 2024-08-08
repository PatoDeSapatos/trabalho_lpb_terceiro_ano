const inputBoxes = document.querySelectorAll("div.input-box");
const labels = document.querySelectorAll(".input-box label");

const rateBar = document.querySelector('input[type="range"]');
const rateNumber = document.querySelector(".range-input p");

const searchBox = document.querySelector(".search-box");
const searchIcon = document.querySelector(".search-icon");
const searchBar = document.querySelector(".search-bar");

const gameFormButton = document.querySelector(".add-button");
const gameForm = document.querySelector("form.wrapper");

for (let i = 0; i < inputBoxes.length; i++) {
    inputBoxes[i].addEventListener("focusin", (e) => {
        e.preventDefault();
        e.target.parentElement.classList.add("active-input");
    });

    inputBoxes[i].addEventListener("focusout", (e) => {
        e.preventDefault();
        e.target.parentElement.classList.remove("active-input");
    });
}

rateBar.addEventListener("change", (e) => {
    e.preventDefault();
    rateNumber.innerHTML = e.target.value + "/5";
});

searchIcon.addEventListener("click", (e) => {
    e.preventDefault();
    searchBar.classList.toggle("active-flex");
    searchIcon.classList.toggle("active-flex");
    searchBox.classList.toggle("active-flex");
});

gameFormButton.addEventListener("click", (e) => {
    e.preventDefault();
    gameForm.classList.toggle("active-flex");
});