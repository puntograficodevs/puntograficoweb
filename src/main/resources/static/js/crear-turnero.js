document.addEventListener("DOMContentLoaded", () => {
  // Inputs y elementos relevantes
  const toggleFechaMuestra = document.getElementById("toggleFechaMuestra");
  const fechaMuestraRow = document.getElementById("fechaMuestraRow");
  const medidaPersonalizadaGroup = document.getElementById("medidaPersonalizadaGroup");
  const cantidadPersonalizadaGroup = document.getElementById("cantidadPersonalizadaGroup");

  const tipoColorRadios = document.querySelectorAll("input[name='tipoColorTurnero.id']");
  const medidaRadios = document.querySelectorAll("input[name='medidaTurnero.id']");
  const cantidadTurneroRadios = document.querySelectorAll("input[name='cantidadTurnero.id']");
  const cantidadHojasInput = document.getElementById("turnero-cantidad-hojas");

  const totalInput = document.getElementById("total");
  const abonadoInput = document.getElementById("abonado");
  const restaInput = document.getElementById("resta");
  const adicionalDisenioCheckbox = document.getElementById("turnero-con-adicional-disenio");
  const medioPagoRadios = document.querySelectorAll("input[name='medioPago.id']");

  // Variables de control
  let precioBase = 0;
  let adicionalDisenio = 0;
  let creditoAdicional = 0;
  let totalManual = false; // para saber si el usuario editó total manualmente

  // Mostrar/ocultar fecha de muestra
  toggleFechaMuestra.addEventListener("change", () => {
    fechaMuestraRow.classList.toggle("d-none", !toggleFechaMuestra.checked);
  });

  // Mostrar/ocultar medida personalizada
  medidaRadios.forEach(radio => {
    radio.addEventListener("change", () => {
      medidaPersonalizadaGroup.classList.toggle("d-none", radio.dataset.medida.toUpperCase() !== "OTRA");
      fetchPrecio();
    });
  });

  // Mostrar/ocultar cantidad personalizada
  cantidadTurneroRadios.forEach(radio => {
    radio.addEventListener("change", () => {
      cantidadPersonalizadaGroup.classList.toggle("d-none", radio.dataset.cantidad.toUpperCase() !== "OTRA");
      fetchPrecio();
    });
  });

  // Escuchar cambios en tipo color, cantidad de hojas y cantidad de hojas membreteadas
  tipoColorRadios.forEach(r => r.addEventListener("change", fetchPrecio));
  cantidadHojasInput.addEventListener("input", fetchPrecio);

  // Escuchar cambios manuales en total y abonado
  totalInput.addEventListener("input", () => {
    totalManual = true;
    actualizarResta();
  });
  abonadoInput.addEventListener("input", actualizarResta);

  // Adicional diseño
  adicionalDisenioCheckbox.addEventListener("change", () => {
    adicionalDisenio = adicionalDisenioCheckbox.checked ? 5000 : 0;
    actualizarTotal();
  });

  // Medio de pago
  medioPagoRadios.forEach(radio => {
    radio.addEventListener("change", actualizarTotal);
  });

  // Función para actualizar total considerando precio base y adicionales
  function actualizarTotal() {
    let base = totalManual ? parseInt(totalInput.value) - creditoAdicional - adicionalDisenio || 0 : precioBase;
    let total = base + adicionalDisenio;

    // Aplicar crédito si corresponde
    const creditoSeleccionado = Array.from(medioPagoRadios).find(r => r.checked);
    creditoAdicional = creditoSeleccionado && creditoSeleccionado.nextElementSibling.textContent.toLowerCase() === "crédito"
                       ? Math.round(total * 0.1)
                       : 0;

    total += creditoAdicional;
    totalInput.value = total;
    actualizarResta();
  }

  // Función para actualizar resta
  function actualizarResta() {
    const total = parseInt(totalInput.value) || 0;
    const abonado = parseInt(abonadoInput.value) || 0;
    restaInput.value = total - abonado;
  }

  // Función para hacer fetch de precio según selección
  async function fetchPrecio() {
    const tipoColor = document.querySelector("input[name='tipoColorTurnero.id']:checked");
    const medida = document.querySelector("input[name='medidaTurnero.id']:checked");
    const cantidadTurnero = document.querySelector("input[name='cantidadTurnero.id']:checked");
    const cantidadHojas = cantidadHojasInput.value;

    if (!tipoColor || !medida || !cantidadTurnero || !cantidadHojas) return;

    try {
      const response = await fetch(`/api/plantilla-turnero/precio?cantidadHojas=${cantidadHojas}&medidaTurneroId=${medida.value}&tipoColorTurneroId=${tipoColor.value}&cantidadTurneroId=${cantidadTurnero.value}`);
      if (response.ok) {
        const precio = await response.json();
        precioBase = parseInt(precio);
        totalManual = false; // si viene del fetch, dejamos de usar manual
      } else {
        precioBase = 0; // No coincidencia
      }
      actualizarTotal();
    } catch (err) {
      console.error("Error fetch precio:", err);
      precioBase = 0;
      actualizarTotal();
    }
  }
});
