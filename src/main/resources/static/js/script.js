function updateDateTime() {
    const now = new Date();
    const formatted = now.toLocaleString();
    document.getElementById('date-time').textContent = formatted;
}
setInterval(updateDateTime, 1000);
updateDateTime();

function showPanel(panel) {
    document.getElementById('game-panel').classList.add('hidden');
    document.getElementById('results-panel').classList.add('hidden');
    if (panel === 'game') {
        document.getElementById('game-panel').classList.remove('hidden');
    } else if (panel === 'results') {
        document.getElementById('results-panel').classList.remove('hidden');
    }
}

document.addEventListener('DOMContentLoaded', function () {
    const depositButtons = document.querySelectorAll('.deposit-btn');
    const paymentDiv = document.querySelector('.payment-methods');
    const paymentButtons = document.querySelectorAll('.payment-btn');
    const form = document.getElementById('deposit-form');
    const amountInput = document.getElementById('deposit-amount');
    const methodInput = document.getElementById('deposit-method');

    depositButtons.forEach(button => {
        button.addEventListener('click', () => {
            const amount = button.getAttribute('data-amount');
            amountInput.value = amount;
            paymentDiv.style.display = 'block';
        });
    });

    paymentButtons.forEach(button => {
        button.addEventListener('click', () => {
            const method = button.getAttribute('data-method');
            methodInput.value = method;
            form.submit();
        });
    });
});

const depositButtons = document.querySelectorAll('.deposit-btn');
const paymentButtons = document.querySelectorAll('.payment-btn');
const paymentContainer = document.querySelector('.payment-methods');

depositButtons.forEach(btn => {
    btn.addEventListener('click', () => {
        paymentContainer.style.display = 'flex';

        paymentButtons.forEach(p => {
            p.classList.remove('green', 'blue');
        });

        const value = btn.dataset.amount;
        const colorClass = value === "10" ? 'green' : 'blue';

        paymentButtons.forEach(p => p.classList.add(colorClass));
    });
});