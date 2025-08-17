document.addEventListener("DOMContentLoaded", () => {
  const toggleFechaMuestra = document.getElementById("toggleFechaMuestra");
  const fechaMuestraRow = document.getElementById("fechaMuestraRow");

  const tipoFazRadios = document.querySelectorAll("input[name='tipoFazFlybanner.id']");
  const tipoBaseRadios = document.querySelectorAll("input[name='tipoBaseFlybanner.id']");
  const alturaRadios = document.querySelectorAll("input[name='alturaFlybanner.id']");
  const banderaRadios = document.querySelectorAll("input[name='banderaFlybanner.id']");
  const cantidadInput = document.getElementById("cantidad");

  const totalInput = document.getElementById("total");
  const abonadoInput = document.getElementById("abonado");
  const restaInput = document.getElementById("resta");

  const adicionalDisenioCheckbox = document.getElementById("flybanner-con-adicional-disenio");
  const medioPagoRadios = document.querySelectorAll("input[name='medioPago.id']");

  // Variables internas
  let precioBase = 0; // base para cálculos (puede venir de plantilla o manual)
  let adicionalAplicado = false;
  let creditoAplicado = false;

  // 1. Toggle fecha muestra
  toggleFechaMuestra.addEventListener("change", () => {
    fechaMuestraRow.classList.toggle("d-none", !toggleFechaMuestra.checked);
  });

  // 2. Inputs personalizados disparan búsqueda también
  cantidadInput.addEventListener("input", buscarPrecio);
  const inputs = [...tipoFazRadios, ...tipoBaseRadios, ...alturaRadios, ...banderaRadios];
  inputs.forEach(input => input.addEventListener("change", buscarPrecio));


  // 3. Adicional diseño checkbox
  adicionalDisenioCheckbox.addEventListener("change", () => {
    adicionalAplicado = adicionalDisenioCheckbox.checked;
    actualizarTotal();
  });

  // 4. Medio de pago radios
  medioPagoRadios.forEach(radio => {
    radio.addEventListener("change", () => {
      const labelText = radio.nextElementSibling.textContent.trim().toUpperCase();
      creditoAplicado = (labelText === "CRÉDITO");
      actualizarTotal();
    });
  });

  // 5. Abonado input actualiza resta
  abonadoInput.addEventListener("input", actualizarResta);

  // 6. Total editable siempre, si el usuario escribe manualmente, se actualiza precioBase a ese valor
  totalInput.addEventListener("input", () => {
    const manualTotal = parseInt(totalInput.value) || 0;
    // Actualizo precioBase para que los cálculos posteriores usen el valor manual
    precioBase = manualTotal;
    actualizarTotal(); // recalcula con adicional y crédito sobre nuevo precioBase
    actualizarResta();
  });

  // --- Funciones ---

  function getCheckedValue(radios) {
    const checked = Array.from(radios).find(r => r.checked);
    return checked ? checked.value : null;
  }

  function buscarPrecio() {
    // Agarrar todos los IDs seleccionados o personalizados
    const tipoFazId = getCheckedValue(tipoFazRadios);
    const tipoBaseId = getCheckedValue(tipoBaseRadios);
    const alturaId = getCheckedValue(alturaRadios);
    const banderaId = getCheckedValue(banderaRadios);

    if (!tipoFazId || !banderaId || !tipoBaseId || !alturaId) {
      // Falta algo, limpio precio y dejo total editable
      precioBase = 0;
      totalInput.value = "";
      totalInput.disabled = false;
      adicionalDisenioCheckbox.checked = false;
      creditoAplicado = false;
      medioPagoRadios.forEach(r => (r.checked = false));
      actualizarResta();
      return;
    }

    // Armar query params
    const params = new URLSearchParams({
      tipoFazFlybannerId: tipoFazId,
      alturaFlybannerId: alturaId,
      banderaFlybannerId: banderaId,
      tipoBaseFlybannerId: tipoBaseId
    });

    fetch(`/api/plantilla-flybanner/precio?${params.toString()}`)
      .then(res => (res.ok ? res.text() : null))
      .then(data => {
        if (data !== null && data !== "") {
          precioBase = parseInt(data);
          adicionalDisenioCheckbox.checked = false;
          creditoAplicado = false;
          medioPagoRadios.forEach(r => (r.checked = false));
          actualizarTotal();
        } else {
          precioBase = 0;
          totalInput.value = "";
          totalInput.disabled = false;
          adicionalDisenioCheckbox.checked = false;
          creditoAplicado = false;
          medioPagoRadios.forEach(r => (r.checked = false));
          actualizarResta();
        }
      });
  }

  function actualizarTotal() {
    let cantidad = parseInt(cantidadInput.value) || 1;
    let total = precioBase * cantidad; // multiplicar precio unitario por cantidad
    if (adicionalAplicado) total += 5000;
    if (creditoAplicado) total = Math.round(total * 1.10);
    totalInput.value = total;
    totalInput.disabled = false; // Siempre editable
    actualizarResta();
  }

  function actualizarResta() {
    const total = parseInt(totalInput.value) || 0;
    const abonado = parseInt(abonadoInput.value) || 0;
    const resta = Math.max(total - abonado, 0);
    restaInput.value = resta;
  }

  // Inicializo
  buscarPrecio();
});
