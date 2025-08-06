document.addEventListener('DOMContentLoaded', function () {
  const totalInput = document.getElementById('total');
  const abonadoInput = document.getElementById('abonado');
  const restaInput = document.getElementById('resta');
  const adicionalCheckbox = document.getElementById('anotador-con-adicional-disenio');
  const cantidadInput = document.getElementById('anotador-cantidad');
  const radiosMedioPago = document.querySelectorAll('input[name="medioPago.id"]');

  // Set defaults
  abonadoInput.value = 0;
  cantidadInput.value = 1;

  let incluyeAdicionalDisenio = false;
  let medioPagoEsCredito = false;

  let totalBaseUsuario = 0; // lo que escribe el usuario, sin recargos

  function actualizarTotalConRecargos() {
    let total = totalBaseUsuario;

    if (incluyeAdicionalDisenio) {
      total += 5000;
    }

    if (medioPagoEsCredito) {
      total *= 1.1;
    }

    // Mostramos el nuevo total con recargos
    totalInput.value = Math.round(total);
  }

  function calcularResta() {
    let total = parseFloat(totalInput.value) || 0;
    const abonado = parseFloat(abonadoInput.value) || 0;
    const resta = Math.max(Math.round(total - abonado), 0);
    restaInput.value = resta;
  }

  // Escuchamos cuando el usuario cambia el total (esto reinicia base)
  totalInput.addEventListener('input', () => {
    totalBaseUsuario = parseFloat(totalInput.value) || 0;
    actualizarTotalConRecargos();
    calcularResta();
  });

  abonadoInput.addEventListener('input', calcularResta);
  cantidadInput.addEventListener('input', calcularResta); // opcional, para refrescar

  adicionalCheckbox.addEventListener('change', () => {
    incluyeAdicionalDisenio = adicionalCheckbox.checked;
    actualizarTotalConRecargos();
    calcularResta();
  });

  radiosMedioPago.forEach(radio => {
    radio.addEventListener('change', () => {
      const label = radio.nextElementSibling.textContent.trim().toLowerCase();
      medioPagoEsCredito = label.includes('crédito');
      actualizarTotalConRecargos();
      calcularResta();
    });
  });

  // Mostrar/ocultar fecha de muestra
  const toggleFechaMuestra = document.getElementById('toggleFechaMuestra');
  const fechaMuestraRow = document.getElementById('fechaMuestraRow');

  toggleFechaMuestra.addEventListener('change', () => {
    fechaMuestraRow.classList.toggle('d-none', !toggleFechaMuestra.checked);
  });

  // Si había valores iniciales, se calculan desde ahí
  totalBaseUsuario = parseFloat(totalInput.value) || 0;
  actualizarTotalConRecargos();
  calcularResta();
});
