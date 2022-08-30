const renaultUrl = 'https://cloud.info.renault.co.il/catalog?m=';
const daciaUrl = 'https://cloud.info.dacia.co.il/catalog?m=';
const nissanUrl = 'https://cloud.info.lease4u.co.il/platenumber?m=';
const infinitiUrl = 'https://cloud.info.infiniti-cars.co.il/catalog?m=';
const renaultInput = document.getElementById('renault-input');
const renaultLink = document.getElementById('renault-link');

const daciaInput = document.getElementById('dacia-input');
const daciaLink = document.getElementById('dacia-link');

const nissanInput = document.getElementById('nissan-input');
const nissanLink = document.getElementById('nissan-link');

const infinitiInput = document.getElementById('infiniti-input');
const infinitiLink = document.getElementById('infiniti-link');

renaultInput.addEventListener('input', (e) => renaultLink.setAttribute('href', `${renaultUrl}${e.target.value}`));
daciaInput.addEventListener('input', (e) => daciaLink.setAttribute('href', `${daciaUrl}${e.target.value}`));
nissanInput.addEventListener('input', (e) => nissanLink.setAttribute('href', `${nissanUrl}${e.target.value}`));
infinitiInput.addEventListener('input', (e) => infinitiLink.setAttribute('href', `${infinitiUrl}${e.target.value}`));

renaultLink.addEventListener('click', (e) => {
    e.preventDefault();
    if (renaultInput.value.length >= 8) {
        window.open(renaultLink.getAttribute('href', '_blank'));
        renaultInput.value = '';
    } else {
        alert('Invalid number length');
    }
});

daciaLink.addEventListener('click', (e) => {
    e.preventDefault();
    if (daciaInput.value.length >= 8) {
        window.open(daciaLink.getAttribute('href', '_blank'));
        daciaInput.value = '';
    } else {
        alert('Invalid number length');
    }
});

nissanLink.addEventListener('click', (e) => {
    e.preventDefault();
    if (nissanInput.value.length >= 8) {
        window.open(nissanLink.getAttribute('href', '_blank'));
        nissanInput.value = '';
    } else {
        alert('Invalid number length');
    }
});

infinitiLink.addEventListener('click', (e) => {
    e.preventDefault();
    if (infinitiInput.value.length >= 8) {
        window.open(infinitiLink.getAttribute('href', '_blank'));
        infinitiInput.value = '';
    } else {
        alert('Invalid number length');
    }
});