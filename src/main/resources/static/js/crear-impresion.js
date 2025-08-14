document.addEventListener("DOMContentLoaded", () => {
  const toggleFechaMuestra = document.getElementById("toggleFechaMuestra");
  const fechaMuestraRow = document.getElementById("fechaMuestraRow");

  const tipoPapelRadios = document.querySelectorAll("input[name='tipoPapelImpresion.id']");
  const tipoColorRadios = document.querySelectorAll("input[name='tipoColorImpresion.id']");
  const tipoFazRadios = document.querySelectorAll("input[name='tipoFazImpresion.id']");
  const tamanioHojaRadios = document.querySelectorAll("input[name='tamanioHojaImpresion.id']");
  const tipoRadios = document.querySelectorAll("input[name='tipoImpresion.id']");
  const cantidadOpcion = document.getElementById("cantidadImpresionId");
  const cantidadInput = document.getElementById("cantidad");
  const totalInput = document.getElementById("total");
  const abonadoInput = document.getElementById("abonado");
  const restaInput = document.getElementById("resta");
  const medioPagoRadios = document.querySelectorAll("input[name='medioPago.id']");

  // Variables internas
  let precioBase = 0;
  let adicionalAplicado = false;
  let creditoAplicado = false;

  // 1. Toggle fecha muestra
  toggleFechaMuestra.addEventListener("change", () => {
    fechaMuestraRow.classList.toggle("d-none", !toggleFechaMuestra.checked);
  });

  // 4. Inputs personalizados disparan búsqueda también
  cantidadInput.addEventListener("input", buscarPrecio);
  [tipoPapelRadios, tipoColorRadios, tipoFazRadios, tamanioHojaRadios, tipoRadios].forEach(grupo => {
    grupo.forEach(radio => radio.addEventListener("change", buscarPrecio));
  });

  // 6. Medio de pago radios
  medioPagoRadios.forEach(radio => {
    radio.addEventListener("change", () => {
      const labelText = radio.nextElementSibling.textContent.trim().toUpperCase();
      creditoAplicado = (labelText === "CRÉDITO");
      actualizarTotal();
    });
  });

  // 7. Abonado input actualiza resta
  abonadoInput.addEventListener("input", actualizarResta);

  // 8. Total editable siempre, si el usuario escribe manualmente, se actualiza precioBase a ese valor
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

  function mapearOpcionCantidad() {
    const cantidad = parseInt(cantidadInput.value) || 0;

    if (cantidad <= 10) {
        cantidadOpcion.value = 1;
        return 1;
    }
    if (cantidad <= 100) {
        cantidadOpcion.value = 2;
        return 2;
    }
    if (cantidad > 100) {
        cantidadOpcion.value = 3;
        return 3;
    }
  }

  function buscarPrecio() {
    // Agarrar todos los IDs seleccionados o personalizados
    const cantidadId = mapearOpcionCantidad();
    const tipoPapelId = getCheckedValue(tipoPapelRadios);
    const tipoColorId = getCheckedValue(tipoColorRadios);
    const tipoFazId = getCheckedValue(tipoFazRadios);
    const tamanioHojaId = getCheckedValue(tamanioHojaRadios);
    const tipoId = getCheckedValue(tipoRadios);

    if (!cantidadId || !tipoPapelId || !tipoColorId || !tipoFazId || !tamanioHojaId || !tipoId) {
      // Falta algo, limpio precio y dejo total editable
      precioBase = 0;
      totalInput.value = "";
      totalInput.disabled = false;
      creditoAplicado = false;
      medioPagoRadios.forEach(r => (r.checked = false));
      actualizarResta();
      return;
    }

    // Armar query params
    const params = new URLSearchParams({
      tipoColorImpresionId: tipoColorId,
      tamanioHojaImpresionId: tamanioHojaId,
      tipoFazImpresionId: tipoFazId,
      tipoPapelImpresionId: tipoPapelId,
      cantidadImpresionId: cantidadId,
      tipoImpresionId: tipoId
    });

    fetch(`/api/plantilla-impresion/precio?${params.toString()}`)
      .then(res => (res.ok ? res.text() : null))
      .then(data => {
        if (data) {
          const precioUnitario = parseInt(data) || 0; // precio de la plantilla
          const cantidadReal = parseInt(cantidadInput.value) || 0; // cantidad que puso el usuario
          precioBase = precioUnitario * cantidadReal; // multiplicamos una sola vez
          creditoAplicado = false;
          medioPagoRadios.forEach(r => (r.checked = false));
          actualizarTotal();
        } else {
          precioBase = 0;
          totalInput.value = "";
          totalInput.disabled = false;
          creditoAplicado = false;
          medioPagoRadios.forEach(r => (r.checked = false));
          actualizarResta();
        }
      });
  }

  function actualizarTotal() {
    let total = precioBase;
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
