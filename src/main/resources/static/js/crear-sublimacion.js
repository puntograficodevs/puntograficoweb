document.addEventListener("DOMContentLoaded", () => {
  const toggleFechaMuestra = document.getElementById("toggleFechaMuestra");
  const fechaMuestraRow = document.getElementById("fechaMuestraRow");

  const materialRadios = document.querySelectorAll("input[name='materialSublimacion.id']");
  const cantidadRadios = document.querySelectorAll("input[name='cantidadSublimacion.id']");
  const cantidadPersonalizadaGroup = document.getElementById("cantidadPersonalizadaGroup");
  const cantidadPersonalizadaInput = document.getElementById("cantidad");

  const totalInput = document.getElementById("total");
  const abonadoInput = document.getElementById("abonado");
  const restaInput = document.getElementById("resta");

  const adicionalDisenioCheckbox = document.getElementById("sublimacion-con-adicional-disenio");
  const medioPagoRadios = document.querySelectorAll("input[name='medioPago.id']");

  // Variables internas
  let precioBase = 0; // base para cálculos (puede venir de plantilla o manual)
  let adicionalAplicado = false;
  let creditoAplicado = false;

  // 1. Toggle fecha muestra
  toggleFechaMuestra.addEventListener("change", () => {
    fechaMuestraRow.classList.toggle("d-none", !toggleFechaMuestra.checked);
  });

  // 3. Mostrar input personalizada cantidad si eligieron OTRA
  cantidadRadios.forEach(radio => {
    radio.addEventListener("change", () => {
      const texto = radio.nextElementSibling.textContent.trim().toUpperCase();
      if (texto === "OTRA") {
        cantidadPersonalizadaGroup.classList.remove("d-none");
        cantidadPersonalizadaInput.required = true;
      } else {
        cantidadPersonalizadaGroup.classList.add("d-none");
        cantidadPersonalizadaInput.required = false;
        cantidadPersonalizadaInput.value = "";
      }
      buscarPrecio();
    });
  });

  // 4. Inputs personalizados disparan búsqueda también
  cantidadPersonalizadaInput.addEventListener("input", buscarPrecio);
  [materialRadios, cantidadRadios].forEach(grupo => {
    grupo.forEach(radio => radio.addEventListener("change", buscarPrecio));
  });

  // 5. Adicional diseño checkbox
  adicionalDisenioCheckbox.addEventListener("change", () => {
    adicionalAplicado = adicionalDisenioCheckbox.checked;
    actualizarTotal();
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

  function buscarPrecio() {
    // Agarrar todos los IDs seleccionados o personalizados
    const materialId = getCheckedValue(materialRadios);

    // Medida puede ser seleccionada o personalizada (si es "OTRA")
    let cantidadId = getCheckedValue(cantidadRadios);
    const cantidadSeleccionada = cantidadRadios.length > 0
      ? Array.from(cantidadRadios).find(r => r.checked)?.nextElementSibling.textContent.trim().toUpperCase()
      : null;
    if (cantidadSeleccionada === "OTRA") {
      cantidadId = cantidadPersonalizadaInput.value.trim();
      if (!cantidadId) cantidadId = null;
    }

    if (!materialId || !cantidadId) {
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
      materialSublimacionId: materialId,
      cantidadSublimacionId: cantidadId
    });

    fetch(`/api/plantilla-sublimacion/precio?${params.toString()}`)
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
    let total = precioBase;
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
