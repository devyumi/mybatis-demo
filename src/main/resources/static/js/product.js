function inputField() {
    const type = document.getElementById('type').value;
    const keyword = document.getElementById('keyword');
    const price = document.getElementById('price');
    const priceFrom = document.getElementById('priceFrom');
    const priceTo = document.getElementById('priceTo');

    if (type === 'all' || type === 'name' || type === 'manufacturer') {
        keyword.style.display = 'block';
        price.style.display = 'none';
        keyword.disabled = false;
        priceFrom.disabled = true;
        priceTo.disabled = true;
    } else if (type === 'price') {
        keyword.style.display = 'none';
        price.style.display = 'block';
        keyword.disabled = true;
        priceFrom.disabled = false;
        priceTo.disabled = false;
    }
}