document.addEventListener('DOMContentLoaded', function() {
  const totalInput = document.getElementById('total');
  const abonadoInput = document.getElementById('abonado');
  const restaInput = document.getElementById('resta');

  function actualizarResta() {
    const total = parseFloat(totalInput.value) || 0;
    const abonado = parseFloat(abonadoInput.value) || 0;
    const resta = total - abonado;

    restaInput.value = resta;
    if (resta < 0) {
      restaInput.style.color = 'red';
    } else {
      restaInput.style.color = 'black';
    }
  }

  totalInput.addEventListener('input', actualizarResta);
  abonadoInput.addEventListener('input', actualizarResta);

  actualizarResta();
});
