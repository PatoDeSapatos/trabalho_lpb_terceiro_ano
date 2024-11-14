const searchBox = document.querySelector(".search-box");
const searchIcon = document.querySelector(".search-icon");
const searchBar = document.querySelector(".search-bar");
const gameFormButton = document.querySelector(".add-button");
const gameForm = document.querySelector("form.wrapper");

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