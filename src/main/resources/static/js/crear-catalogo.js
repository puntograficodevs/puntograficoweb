document.addEventListener('DOMContentLoaded', () => {
  const totalInput = document.getElementById('total');
  const abonadoInput = document.getElementById('abonado');
  const restaInput = document.getElementById('resta');
  const adicionalCheckbox = document.getElementById('catalogo-con-adicional-disenio');
  const mediosDePagoRadios = document.querySelectorAll('input[name="medioPago.id"]');

  const etiquetaMediosDePago = {};

  // Mapear radios con sus labels
  mediosDePagoRadios.forEach(radio => {
    const label = document.querySelector(`label[for="${radio.id}"]`);
    if (label) {
      etiquetaMediosDePago[radio.id] = label.innerText.trim().toLowerCase();
    }
  });

  function calcularResta() {
    const base = parseFloat(totalInput.dataset.base) || parseFloat(totalInput.value) || 0;
    let totalCalculado = base;

    if (adicionalCheckbox.checked) {
      totalCalculado += 5000;
    }

    mediosDePagoRadios.forEach(radio => {
      if (radio.checked) {
        const labelText = etiquetaMediosDePago[radio.id];
        if (labelText.includes('crédito') || labelText.includes('credito')) {
          totalCalculado *= 1.10;
        }
      }
    });

    totalCalculado = Math.round(totalCalculado);
    totalInput.value = totalCalculado;

    const abonado = parseFloat(abonadoInput.value) || 0;
    const resta = Math.max(0, totalCalculado - abonado);
    restaInput.value = Math.round(resta);
  }

  // Capturá el valor base original en un atributo
  const baseTotal = parseFloat(totalInput.value) || 0;
  totalInput.dataset.base = baseTotal;

  // Toggle de fecha muestra
  const toggleFechaMuestraCheckbox = document.getElementById('toggleFechaMuestra');
  const fechaMuestraRow = document.getElementById('fechaMuestraRow');
  const fechaMuestraInput = document.getElementById('fechaMuestra');

  toggleFechaMuestraCheckbox.addEventListener('change', () => {
    if (toggleFechaMuestraCheckbox.checked) {
      fechaMuestraRow.classList.remove('d-none');
      fechaMuestraInput.setAttribute('name', 'fechaMuestra');
    } else {
      fechaMuestraRow.classList.add('d-none');
      fechaMuestraInput.removeAttribute('name');
      fechaMuestraInput.value = '';
    }
  });

  // Listeners
  totalInput.addEventListener('input', () => {
    // Si el usuario edita el total manualmente, lo usamos como base nueva
    const nuevoBase = parseFloat(totalInput.value) || 0;
    totalInput.dataset.base = nuevoBase;
    calcularResta();
  });

  abonadoInput.addEventListener('input', calcularResta);
  adicionalCheckbox.addEventListener('change', calcularResta);
  mediosDePagoRadios.forEach(r => r.addEventListener('change', calcularResta));

  // Calcular al inicio
  calcularResta();
});
