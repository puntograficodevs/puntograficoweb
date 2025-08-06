document.addEventListener("DOMContentLoaded", () => {
  const cantidadInput = document.querySelector("#carpeta-cantidad");
  const laminadoRadios = document.querySelectorAll("input[name='tipoLaminadoCarpetaSolapa.id']");
  const fazRadios = document.querySelectorAll("input[name='tipoFazCarpetaSolapa.id']");
  const totalInput = document.querySelector("#total");
  const abonadoInput = document.querySelector("#abonado");
  const restaInput = document.querySelector("#resta");
  const medioPagoRadios = document.querySelectorAll("input[name='medioPago.id']");
  const adicionalDisenioCheckbox = document.querySelector("#carpeta-con-adicional-disenio");
  const toggleFechaMuestra = document.querySelector("#toggleFechaMuestra");
  const fechaRow = document.querySelector("#fechaMuestraRow");

  let precioUnitario = null;
  let esCredito = false;
  let totalManualBase = null;

  function getSelectedRadioValue(radioNodeList) {
    for (const radio of radioNodeList) {
      if (radio.checked) return radio.value;
    }
    return null;
  }

  function actualizarPrecio() {
    const cantidad = parseInt(cantidadInput.value);
    const laminadoId = getSelectedRadioValue(laminadoRadios);
    const fazId = getSelectedRadioValue(fazRadios);

    if (!cantidad || !laminadoId || !fazId) {
      precioUnitario = null;
      totalInput.readOnly = false;
      return actualizarTotalYResta();
    }

    fetch(`/api/plantilla-carpeta-solapa/precio?cantidad=${cantidad}&tipoLaminadoCarpetaSolapaId=${laminadoId}&tipoFazCarpetaSolapaId=${fazId}`)
      .then(res => res.ok ? res.json() : null)
      .then(precio => {
        if (precio !== null) {
          precioUnitario = precio;
          totalManualBase = null; // se resetea si viene precio nuevo
          aplicarTotalCalculado();
        } else {
          precioUnitario = null;
          totalInput.readOnly = false;
          totalInput.value = '';
        }
        actualizarTotalYResta();
      })
      .catch(() => {
        precioUnitario = null;
        totalInput.readOnly = false;
        totalInput.value = '';
        actualizarTotalYResta();
      });
  }

  function aplicarTotalCalculado() {
    const cantidad = parseInt(cantidadInput.value) || 0;

    let base = totalManualBase !== null ? totalManualBase : (
      (precioUnitario !== null)
        ? (cantidad < 50 ? precioUnitario * cantidad : precioUnitario)
        : null
    );

    if (base === null) return;

    let total = base;

    if (adicionalDisenioCheckbox.checked) total += 5000;
    if (esCredito) total *= 1.1;

    totalInput.value = Math.round(total);
    totalInput.readOnly = precioUnitario !== null;
  }

  function actualizarTotalYResta() {
    const totalActual = parseFloat(totalInput.value) || 0;
    const abonado = parseFloat(abonadoInput.value) || 0;
    const resta = totalActual - abonado;
    restaInput.value = Math.max(0, Math.round(resta));
  }

  function actualizarAdicionalDisenio() {
    aplicarTotalCalculado();
    actualizarTotalYResta();
  }

  function actualizarCredito() {
    const medioPagoId = getSelectedRadioValue(medioPagoRadios);
    esCredito = medioPagoId === '2';
    aplicarTotalCalculado();
    actualizarTotalYResta();
  }

  function toggleFechaMuestraHandler() {
    fechaRow.classList.toggle("d-none", !toggleFechaMuestra.checked);
  }

  // Detecta entrada manual en total
  totalInput.addEventListener("input", () => {
    const val = parseFloat(totalInput.value);
    if (!isNaN(val)) {
      totalManualBase = val;
      aplicarTotalCalculado();
      actualizarTotalYResta();
    }
  });

  // Listeners
  cantidadInput.addEventListener("input", actualizarPrecio);
  laminadoRadios.forEach(r => r.addEventListener("change", actualizarPrecio));
  fazRadios.forEach(r => r.addEventListener("change", actualizarPrecio));
  adicionalDisenioCheckbox.addEventListener("change", actualizarAdicionalDisenio);
  medioPagoRadios.forEach(r => r.addEventListener("change", actualizarCredito));
  abonadoInput.addEventListener("input", actualizarTotalYResta);
  toggleFechaMuestra.addEventListener("change", toggleFechaMuestraHandler);

  // Inicialización
  actualizarPrecio();
  toggleFechaMuestraHandler();
});
