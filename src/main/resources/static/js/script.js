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