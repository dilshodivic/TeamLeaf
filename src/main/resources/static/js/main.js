const filterBtn = document.querySelector('.filter');
const searchAdvanced = document.querySelectorAll('.search-advanced');
filterBtn.addEventListener('click', () => {
  searchAdvanced.forEach((item) => {
    item.classList.toggle('d-none');
  });
});
