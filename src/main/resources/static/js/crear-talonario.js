document.addEventListener("DOMContentLoaded", () => {
  // Inputs y elementos relevantes
  const toggleFechaMuestra = document.getElementById("toggleFechaMuestra");
  const fechaMuestraRow = document.getElementById("fechaMuestraRow");
  const medidaPersonalizadaGroup = document.getElementById("medidaPersonalizadaGroup");
  const cantidadPersonalizadaGroup = document.getElementById("cantidadPersonalizadaGroup");
  const tipoRadios = document.querySelectorAll("input[name='tipoTalonario.id']");
  const tipoTroqueladoRadios = document.querySelectorAll("input[name='tipoTroqueladoTalonario.id']");
  const modoRadios = document.querySelectorAll("input[name='modoTalonario.id']");
  const tipoColorRadios = document.querySelectorAll("input[name='tipoColorTalonario.id']");
  const tipoPapelRadios = document.querySelectorAll("input[name='tipoPapelTalonario.id']");
  const medidaRadios = document.querySelectorAll("input[name='medidaTalonario.id']");
  const cantidadRadios = document.querySelectorAll("input[name='cantidadTalonario.id']");
  const cantidadHojasInput = document.getElementById("talonario-cantidad-hojas");
  const conNumeradoCheckbox = document.getElementById("talonario-con-numerado");
  const medidaPersonalizadaInput = document.getElementById("medidaPersonalizada");
  const cantidadPersonalizadaInput = document.getElementById("cantidad");

  const totalInput = document.getElementById("total");
  const abonadoInput = document.getElementById("abonado");
  const restaInput = document.getElementById("resta");
  const adicionalDisenioCheckbox = document.getElementById("talonario-con-adicional-disenio");
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
  cantidadRadios.forEach(radio => {
    radio.addEventListener("change", () => {
      cantidadPersonalizadaGroup.classList.toggle("d-none", radio.dataset.cantidad.toUpperCase() !== "OTRA");
      fetchPrecio();
    });
  });

  // Escuchar cambios en tipo color, cantidad de hojas y cantidad de hojas membreteadas
  medidaPersonalizadaInput.addEventListener("input", fetchPrecio);
    cantidadPersonalizadaInput.addEventListener("input", fetchPrecio);
    cantidadHojasInput.addEventListener("input", fetchPrecio);
    conNumeradoCheckbox.addEventListener("input", fetchPrecio);
    [tipoRadios, tipoTroqueladoRadios, modoRadios, tipoColorRadios, medidaRadios, tipoPapelRadios, cantidadRadios].forEach(grupo => {
        grupo.forEach(radio => radio.addEventListener("change", fetchPrecio));
      });

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
    const tipo = document.querySelector("input[name='tipoTalonario.id']:checked");
    const tipoTroquelado = document.querySelector("input[name='tipoTroqueladoTalonario.id']:checked");
    const modo = document.querySelector("input[name='modoTalonario.id']:checked");
    const tipoColor = document.querySelector("input[name='tipoColorTalonario.id']:checked");
    const medida = document.querySelector("input[name='medidaTalonario.id']:checked");
    const tipoPapel = document.querySelector("input[name='tipoPapelTalonario.id']:checked");
    const cantidad = document.querySelector("input[name='cantidadTalonario.id']:checked");
    const cantidadHojas = cantidadHojasInput.value;
    const conNumerado = conNumeradoCheckbox.checked;

    if (!tipo || !tipoTroquelado || !modo || !tipoColor || !medida || !tipoPapel || !cantidad) return;

    try {
      const response = await fetch(`/api/plantilla-talonario/precio?cantidadHojas=${cantidadHojas}&conNumerado=${conNumerado}&tipoTalonarioId=${tipo.value}&tipoTroqueladoTalonarioId=${tipoTroquelado.value}&modoTalonarioId=${modo.value}&tipoColorTalonarioId=${tipoColor.value}&medidaTalonarioId=${medida.value}&tipoPapelTalonarioId=${tipoPapel.value}&cantidadTalonarioId=${cantidad.value}`);
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
