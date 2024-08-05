const inputBoxes = document.querySelectorAll("div.input-box");
const labels = document.querySelectorAll(".input-box label");

const rateBar = document.querySelector('input[type="range"]');
const rateNumber = document.querySelector(".range-input p");

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