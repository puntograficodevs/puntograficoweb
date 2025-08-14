document.addEventListener("DOMContentLoaded", () => {
  const toggleFechaMuestra = document.getElementById("toggleFechaMuestra");
  const fechaMuestraRow = document.getElementById("fechaMuestraRow");

  const tipoRadios = document.querySelectorAll("input[name='tipoLonaPublicitaria.id']");
  const medidaRadios = document.querySelectorAll("input[name='medidaLonaPublicitaria.id']");
  const cantidadInput = document.getElementById("cantidad");
  const totalInput = document.getElementById("total");
  const abonadoInput = document.getElementById("abonado");
  const restaInput = document.getElementById("resta");
  const adicionalPortabannerCheckbox = document.getElementById("lona-publicitaria-con-adicional-portabanner");
  const ojalesCheckbox = document.getElementById("lona-publicitaria-con-ojales");
  const ojalesConRefuerzoCheckbox = document.getElementById("lona-publicitaria-con-ojales-con-refuerzo");
  const bolsillosCheckbox = document.getElementById("lona-publicitaria-con-bolsillos");
  const demasiaParaTensadoCheckbox = document.getElementById("lona-publicitaria-con-demasia-para-tensado");
  const solapadoCheckbox = document.getElementById("lona-publicitaria-con-solapado");
  const adicionalDisenioCheckbox = document.getElementById("lona-publicitaria-con-adicional-disenio");
  const medioPagoRadios = document.querySelectorAll("input[name='medioPago.id']");

  // Variables internas
  let precioBase = 0; // base para cálculos (puede venir de plantilla o manual)
  let adicionalAplicado = false;
  let creditoAplicado = false;

  // 1. Toggle fecha muestra
  toggleFechaMuestra.addEventListener("change", () => {
    fechaMuestraRow.classList.toggle("d-none", !toggleFechaMuestra.checked);
  });

  // 4. Inputs personalizados disparan búsqueda también
  cantidadInput.addEventListener("input", buscarPrecio);
  bolsillosCheckbox.addEventListener("change", buscarPrecio);
  adicionalPortabannerCheckbox.addEventListener("change", buscarPrecio);
  ojalesCheckbox.addEventListener("change", buscarPrecio);
  ojalesConRefuerzoCheckbox.addEventListener("change", buscarPrecio);
  demasiaParaTensadoCheckbox.addEventListener("change", buscarPrecio);
  solapadoCheckbox.addEventListener("change", buscarPrecio);
  [tipoRadios, medidaRadios].forEach(grupo => {
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
    const cantidad = parseInt(cantidadInput.value) || 0;
    const conAdicionalPortabanner = adicionalPortabannerCheckbox.checked;
    const conOjales = ojalesCheckbox.checked;
    const conOjalesConRefuerzo = ojalesConRefuerzoCheckbox.checked;
    const conBolsillos = bolsillosCheckbox.checked;
    const conDemasiaParaTensado = demasiaParaTensadoCheckbox.checked;
    const conSolapado = solapadoCheckbox.checked;
    const tipoId = getCheckedValue(tipoRadios);
    const medidaId = getCheckedValue(medidaRadios);

    if (!tipoId || !medidaId || !cantidad) {
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
      conAdicionalPortabanner: conAdicionalPortabanner,
      conOjales: conOjales,
      conOjalesConRefuerzo: conOjalesConRefuerzo,
      conBolsillos: conBolsillos,
      conDemasiaParaTensado: conDemasiaParaTensado,
      conSolapado: conSolapado,
      medidaLonaPublicitariaId: medidaId,
      tipoLonaPublicitariaId: tipoId
    });

    fetch(`/api/plantilla-lona-publicitaria/precio?${params.toString()}`)
      .then(res => (res.ok ? res.text() : null))
      .then(data => {
        if (data !== null && data !== "") {
          const precioUnitario = parseInt(data) || 0; // precio de la plantilla
          const cantidadReal = parseInt(cantidadInput.value) || 0; // cantidad que puso el usuario
          precioBase = precioUnitario * cantidadReal; // multiplicamos una sola vez
          creditoAplicado = false;
          adicionalDisenioCheckbox.checked = false;
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
